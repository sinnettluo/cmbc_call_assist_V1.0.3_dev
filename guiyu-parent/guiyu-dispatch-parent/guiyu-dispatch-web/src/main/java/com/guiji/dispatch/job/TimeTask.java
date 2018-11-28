package com.guiji.dispatch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guiji.ccmanager.api.ICallManagerOut;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.dispatch.bean.IdsDto;
import com.guiji.dispatch.dao.entity.DispatchPlan;
import com.guiji.dispatch.service.IDispatchPlanService;
import com.guiji.dispatch.util.Constant;
import com.guiji.robot.api.IRobotRemote;
import com.guiji.robot.model.CheckParamsReq;
import com.guiji.robot.model.CheckResult;
import com.guiji.robot.model.HsParam;
import com.guiji.robot.model.TtsComposeCheckRsp;
import com.guiji.utils.RedisUtil;

@Component
// @RestController
public class TimeTask {

	private static final Logger logger = LoggerFactory.getLogger(TimeTask.class);

	@Autowired
	private IDispatchPlanService dispatchPlanService;
	@Autowired
	private ICallManagerOut callManagerOutApi;
	@Autowired
	private IRobotRemote robotRemote;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 捞取号码初始化资源
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void selectPhoneInit() {
		logger.info("捞取号码初始化资源..");
		List<DispatchPlan> list = dispatchPlanService.selectPhoneByDate();

		List<HsParam> sendData = new ArrayList<>();
		for (DispatchPlan dispatchPlan : list) {
			HsParam hsParam = new HsParam();
			hsParam.setParams(dispatchPlan.getParams());
			hsParam.setSeqid(dispatchPlan.getPlanUuid());
			hsParam.setTemplateId(dispatchPlan.getRobot());
			sendData.add(hsParam);
		}
		CheckParamsReq req = new CheckParamsReq();
		req.setCheckers(sendData);
		req.setNeedResourceInit(true);
		robotRemote.checkParams(req);

		// 批量修改状态flag
		boolean batchUpdateFlag = false;
		if (list.size() > 0) {
			batchUpdateFlag = dispatchPlanService.batchUpdateFlag(list, Constant.IS_FLAG_1);
		}
		logger.info("有一批数据调用初始化资源结果....." + batchUpdateFlag);
	}

	/**
	 * 获取当前资源情况
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void getResourceResult() {
		logger.info("获取当前初始化号码的请求资源结果");
		List<DispatchPlan> selectPhoneByDateAndFlag = dispatchPlanService.selectPhoneByDateAndFlag(Constant.IS_FLAG_1);
		List<String> ids = new ArrayList<>();

		for (DispatchPlan dis : selectPhoneByDateAndFlag) {
			ids.add(dis.getPlanUuid());
		}
		ReturnData<List<TtsComposeCheckRsp>> ttsComposeCheck = robotRemote.ttsComposeCheck(ids);
		List<DispatchPlan> successList = new ArrayList<>();

		if (ttsComposeCheck.success) {
			if (ttsComposeCheck.getBody() != null) {
				List<TtsComposeCheckRsp> body = ttsComposeCheck.getBody();
				for (TtsComposeCheckRsp tts : body) {
					if (tts.getStatus().equals("S")) {
						DispatchPlan dis = new DispatchPlan();
						dis.setFlag(Constant.IS_FLAG_2);
						dis.setPlanUuid(tts.getSeqId());
						successList.add(dis);
					}
				}
			}
		} else {
			logger.info("获取当前初始化号码的请求资源结果失败了");
		}
		logger.info("当前以及准备好的资源号码有:" + successList.size());
		boolean batchUpdateFlag = false;
		if (successList.size() > 0) {
			batchUpdateFlag = dispatchPlanService.batchUpdateFlag(successList, Constant.IS_FLAG_2);
		}
		logger.info("修改当前以及准备好的资源号码的flag" + batchUpdateFlag);
	}

	/**
	 * 获取可以拨打的号码
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void getSuccessPhones() {
		List<DispatchPlan> list = dispatchPlanService.selectPhoneByDateAndFlag(Constant.IS_FLAG_2);
		// 分组排序
		Map<String, List<DispatchPlan>> collect = list.stream().collect(Collectors.groupingBy(d -> fetchGroupKey(d)));
		for (Entry<String, List<DispatchPlan>> entry : collect.entrySet()) {
			if (redisUtil.get("robotId") != null) {
				String str = (String) redisUtil.get("robotId");
				if (str.contains(entry.getKey().split("-")[1])) {
					logger.info("当前模板id正在升级中...." + entry.getKey().split("- ")[1]);
					continue;
				}
			}
			logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			checkRedisAndDate(entry.getKey());
		}
	}

	// 每天凌晨1点执行一次
	@Scheduled(cron = "0 0 1 * * ?")
	public void replayPhone() {
		boolean result = dispatchPlanService.updateReplayDate();
		logger.info("当前凌晨一点执行日期刷新操作了！" + result);
	}

	/**
	 * 判断redis是否在当前key
	 * 
	 * @param key
	 */
	private void checkRedisAndDate(String key) {
		Object object = redisUtil.get(key);
		logger.info("checkRedisAndDate key result:" + object);
		if (object != null && object != "") {
			logger.info("当前推送已经推送过：在失效时间内，不重复推送:" + key);
		} else {
			String[] split = key.split("-");
			ReturnData<Boolean> startcallplan = callManagerOutApi.startCallPlan(split[0], split[1], split[2]);
			logger.info("启动客户呼叫计划结果" + startcallplan.success);
			logger.info("启动客户呼叫计划结果详情 " + startcallplan.msg);
			if (startcallplan.success) {
				// 5分钟失效时间
				redisUtil.set(key, new Date(), 300);
			}
		}
	}

	/**
	 * 分组排序字段
	 * 
	 * @param detail
	 * @return
	 */
	private static String fetchGroupKey(DispatchPlan detail) {
		return String.valueOf(detail.getUserId() + "-" + detail.getRobot() + "-" + detail.getLine());
	}
}
