package com.guiji.dispatch.pushcallcenter;

import com.guiji.calloutserver.api.ICallPlan;
import com.guiji.component.lock.DistributedLockHandler;
import com.guiji.component.lock.Lock;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.dispatch.bean.UserLineBotenceVO;
import com.guiji.dispatch.constant.RedisConstant;
import com.guiji.dispatch.dao.DispatchPlanMapper;
import com.guiji.dispatch.dao.entity.DispatchBatchLine;
import com.guiji.dispatch.dao.entity.DispatchPlan;
import com.guiji.dispatch.dao.entity.PushRecords;
import com.guiji.dispatch.enums.GateWayLineStatusEnum;
import com.guiji.dispatch.enums.PlanLineTypeEnum;
import com.guiji.dispatch.util.Constant;
import com.guiji.dispatch.util.DateTimeUtils;
import com.guiji.dispatch.vo.GateWayLineOccupyVo;
import com.guiji.robot.model.UserResourceCache;
import com.guiji.robot.service.vo.AiInuseCache;
import com.guiji.utils.DateUtil;
import com.guiji.utils.IdGengerator.IdUtils;
import com.guiji.utils.JsonUtils;
import com.guiji.utils.RedisUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 推送号码
 * 
 * @author Administrator
 *
 */
@Service
public class PushPhonesHandlerImpl implements IPushPhonesHandler {

	private static final Logger logger = LoggerFactory.getLogger(PushPhonesHandlerImpl.class);

	private static final String REDIS_CALL_QUEUE_USER_LINE_ROBOT_COUNT = "REDIS_CALL_QUEUE_USER_LINE_ROBOT_COUNT_";

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private DispatchPlanMapper disMapper;

	@Autowired
	private ICallPlan callPlanCenter;
	@Autowired
	private DistributedLockHandler distributedLockHandler;

	@Override
	public void pushHandler() {

		Lock pushHandlerLock = new Lock("pushHandler", "pushHandler");
		while (true) {
			try {
				Lock redisPlanQueueLock = new Lock("redisPlanQueueLock", "redisPlanQueueLock");
				if (distributedLockHandler.isLockExist(redisPlanQueueLock)) {
					logger.info("redis锁了  redisPlanQueueLock");
					Thread.sleep(500);
					continue;
				}
				List<UserLineBotenceVO> userLineRobotList = (List<UserLineBotenceVO>) redisUtil
						.get(RedisConstant.RedisConstantKey.REDIS_USER_ROBOT_LINE_MAX_PLAN);
				if (userLineRobotList != null) {
					// 根据用户、模板、线路组合插入拨打电话队列，如果队列长度小于最大并发数的2倍，则往队列中填充3倍最大并发数的计划
					for (UserLineBotenceVO dto : userLineRobotList) {
						String queue = RedisConstant.RedisConstantKey.REDIS_PLAN_QUEUE_USER_LINE_ROBOT + dto.getUserId() + "_" + dto.getBotenceName();
						String queueCount = REDIS_CALL_QUEUE_USER_LINE_ROBOT_COUNT + dto.getUserId() + "_"
								+ dto.getBotenceName();
						Lock queueLock = new Lock("dispatch.callphone.lock" + queue, "dispatch.callphone.lock" + queue);
						if (distributedLockHandler.tryLock(queueLock, 100L)) {
							try {

								Integer redisUserIdCount = (Integer) redisUtil.get(queueCount);
								if (redisUserIdCount == null) {
									redisUserIdCount = 0;
								}

								Integer callMax = null !=dto.getMaxRobotCount()?dto.getMaxRobotCount():0;
							//	logger.info("用户:{},模板:{},callMax:{},redisUserIdCount:{}", dto.getUserId()+"", dto.getBotenceName(),  callMax, redisUserIdCount);
								if (callMax <= redisUserIdCount) {
									continue;
								}

								//判断用户模板是否有可用机器人
								if(!checkUserAvailableRobot(dto.getUserId()+"", dto.getBotenceName())){
									continue;
								}

								Object obj = (Object) redisUtil.lrightPop(queue);
								if(null != obj) {
								//	logger.info("redis REDIS_PLAN_QUEUE_USER_LINE_ROBOT_user_id_templId :{}", JsonUtils.bean2Json(obj));
								}
								if (obj == null || !(obj instanceof DispatchPlan)) {
									continue;
								}
								DispatchPlan dispatchRedis = (DispatchPlan) obj;

								com.guiji.calloutserver.entity.DispatchPlan callBean = new com.guiji.calloutserver.entity.DispatchPlan();
                                GateWayLineOccupyVo occupyLine = null;
								try {
									BeanUtils.copyProperties(callBean, dispatchRedis);
									callBean.setTempId(dispatchRedis.getRobot());
									callBean.setAgentGroupId(dispatchRedis.getCallAgent());
									callBean.setRemarks(dispatchRedis.getAttach());
									List<Integer> lines = new ArrayList<>();
									boolean isSimPush = false;//是否是SIM卡推送

									for (DispatchBatchLine line : dispatchRedis.getLines()) {
									    //判断是否网关路线，如果是网关路线则需要判断线路是否被占用
									    if(PlanLineTypeEnum.GATEWAY.getType() == line.getLineType()){//网关路线
										//	logger.info("推送网关SIM卡拨打用户网关线路:{}", JsonUtils.bean2Json(line));
									    	callBean.setSimCall(true);//  simCall  true:是SIM卡  false：不是SIM卡
									        Integer lineId = line.getLineId();
                                            GateWayLineOccupyVo gateWayLine = (GateWayLineOccupyVo) redisUtil.get(RedisConstant.RedisConstantKey.gatewayLineKey+lineId);
                                            if(GateWayLineStatusEnum.LEISURE.getState() == gateWayLine.getStatus()){//状态闲置未被占用   0-闲置  1-占用
                                                lines.add(line.getLineId());
                                                occupyLine = gateWayLine;
												isSimPush = true;
                                                logger.info("推送SIM卡网关拨打用户:{},话术模板:{},网关线路:{}", callBean.getUserId(), callBean.getTempId(), lineId);
                                                break;//有闲置，则推送，网关路线只能推送一个
                                            }
                                        }else {
                                            lines.add(line.getLineId());
											callBean.setSimCall(false);
                                        }
									}

									//SIM网关路线，不推送呼叫中，重新入栈推送队列
									if(PlanLineTypeEnum.GATEWAY.getType() == dispatchRedis.getLineType()
										&& !isSimPush){
										redisUtil.leftPush(queue, dispatchRedis);
									}

									if(null == lines || lines.size()==0){//没有需要推送的线路,继续循环下条任务计划 用户模板
                                        continue;
                                    }
									callBean.setLineList(lines);
								} catch (IllegalAccessException e) {
									updateStatusSync(dispatchRedis.getPlanUuidLong());
									logger.info("---------BeanUtils.copyProperties转换失败-----------", e);
									continue;
								} catch (InvocationTargetException e) {
									updateStatusSync(dispatchRedis.getPlanUuidLong());
									logger.info("---------BeanUtils.copyProperties转换失败-----------", e);
									continue;
								}

								List<String> userIdList = (List<String>) redisUtil.get("USER_BILLING_DATA");
								if(userIdList!=null){
									if (userIdList.contains(String.valueOf(callBean.getUserId()))) {
										logger.info("startMakeCall>>>>>>>>>>>>>>>>>>>当前用户处于欠费" + callBean.getUserId());
										updateStatusSync(dispatchRedis.getPlanUuidLong());
										continue;
									}
								}

								if(userIdList == null){
									logger.info(">>>>>>>>>>>>>>>>>>...当前userIdList为null");
									Thread.sleep(2000);
									updateStatusSync(dispatchRedis.getPlanUuidLong());
									continue;
								}
								// 增加推送次数
								addVariable(callBean, queueCount);
								logger.info("通知呼叫中心开始打电话:" + callBean.getPlanUuid() + "-----" + callBean.getPhone()
										+ "---------" + callBean.getLineList() + ", 通知呼叫中心时间:" + DateTimeUtils.getCurrentDateString(DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_FULL));
								ReturnData startMakeCall = callPlanCenter.startMakeCall(callBean);
								// 记录推送记录
								insertPush(dispatchRedis);
								if (!startMakeCall.success) {
									updateStatusSync(dispatchRedis.getPlanUuidLong());
									logger.info("启动呼叫中心任务失败");
									// 减少推送次数
									cutVariable(callBean, queueCount);
									//休眠10S
									Thread.sleep(10000);
									continue;
								}else{
								    //推送成功，则标识网关SIM卡路线被占用
									occupyGateWayLine(occupyLine, dto.getUserId()+"", dto.getBotenceName());
                                }
							}
							catch (Exception e) {
								logger.info("pushHandler代码异常了", e);
							}
							finally {
								distributedLockHandler.releaseLock(queueLock); // 释放锁
							}
						}
					}
				}
			} catch (Exception e) {
				logger.info("pushHandler代码异常了", e);
			} finally {
				distributedLockHandler.releaseLock(pushHandlerLock); // 释放锁
			}
		}
	}


	/**
	 * 校验用户某个模板是否有可用机器人
	 * @param userId
	 * @param templateId
	 * @return true : 有可用机器人，可以继续拨打电话
	 */
	public boolean checkUserAvailableRobot(String userId,String templateId) {
		boolean bool = false;
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(templateId)) {
			//用户资源
			UserResourceCache userResource = getUserResource(userId);
			if(null == userResource){//如果修改机器人配置数量，ROBOT_USER_RESOURCE延迟获取
				return bool;
			}
			//该模板配置的机器人数量，默认0
			int templateCfgAiNum = 0;
			//用户分配的机器人数量，默认0
			int templateAssignAiNum = 0;
			if(userResource.getTempAiNumMap()!=null && userResource.getTempAiNumMap().get(templateId)!=null) {
				//获取该模板配置的机器人数量
				templateCfgAiNum = userResource.getTempAiNumMap().get(templateId);
			}
			if(templateCfgAiNum<=0) {
			//	logger.error("用户:{},模板：{},机器人数量为：0");
				return false;
			}
			//获取用户目前在忙的机器人列表
			List<AiInuseCache> userAiList = queryUserAiInUseList(userId);
			if(userAiList!=null && !userAiList.isEmpty()) {
				for(AiInuseCache robot : userAiList) {
					if(templateId.equals(robot.getTemplateIds())) {
						templateAssignAiNum++;
					}
				}
			}

	//		logger.info("在忙机器人数:{},总共分配机器人数:{}", templateAssignAiNum, templateCfgAiNum);

			if(templateAssignAiNum+1>templateCfgAiNum) {
				logger.error("用户{}模板编号：{}配置的机器人数量{}即将超过了目前并发的数量{},无法继续分配拨打电话..",userId,templateId,templateCfgAiNum,templateAssignAiNum);
				bool = false;
			}else {
				bool = true;
			}
		}
	//	logger.info("用户:{},模板:{}是否有机器人可用:{}",userId, templateId, bool);
		return bool;
	}

	/**
	 * 根据用户id查询用户的资源缓存信息
	 * @param userId
	 * @return
	 */
	public UserResourceCache getUserResource(String userId) {
		Object cacheObj = redisUtil.hget("ROBOT_USER_RESOURCE", userId);
		if(cacheObj != null) {
			return (UserResourceCache)cacheObj;
		}
		return null;
	}

	/**
	 * 查询用户现在已分配的机器人
	 * @param userId
	 * @return
	 */
	public List<AiInuseCache> queryUserAiInUseList(String userId){
		Map<Object,Object> allMap = redisUtil.hmget("ROBOT_USER_AI_"+userId);
		if(allMap != null && !allMap.isEmpty()) {
			List<AiInuseCache> list = new ArrayList<AiInuseCache>();
			for (Map.Entry<Object,Object> aiEntry : allMap.entrySet()) {
				AiInuseCache aiInuse = (AiInuseCache) aiEntry.getValue();
				list.add(aiInuse);
			}
			return list;
		}
		return null;
	}



	/**
	 * 标识网关SIM卡路线被占用
	 * @param occupyLine
	 * @param userId
	 * @param botstenceId
	 */
	private void occupyGateWayLine(GateWayLineOccupyVo occupyLine, String userId, String botstenceId){
		if(null != occupyLine){
			try {
				occupyLine.setStatus(GateWayLineStatusEnum.OCCUPY.getState());
				occupyLine.setOccupyTime(DateTimeUtils.getDateString(new Date(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_FULL));
				occupyLine.setUserId(userId);
				occupyLine.setBotstenceId(botstenceId);
				redisUtil.set(RedisConstant.RedisConstantKey.gatewayLineKey + occupyLine.getLineId(),
						occupyLine);
			}catch(Exception e){
				logger.error("标识网关路线被占用异常", e);
			}
		}
	}

	private void cutVariable(com.guiji.calloutserver.entity.DispatchPlan callBean, String queueName) {
		Integer currentCount = (Integer) redisUtil.get(queueName);
		if (currentCount == null) {
			currentCount = 0;
		}
		if (currentCount > 0) {
			// currentCount = currentCount - 1;
			// redisUtil.set(queueName, currentCount);
			// 递减1
			redisUtil.decr(queueName, 1);
			redisUtil.expire(queueName, 300);
		}
	}

	private void addVariable(com.guiji.calloutserver.entity.DispatchPlan callBean, String queueName) {
		// Integer currentCount = (Integer) redisUtil.get(queueName);
		// if (currentCount == null) {
		// currentCount = 0;
		// }
		// currentCount = currentCount + 1;
		// redisUtil.set(queueName, currentCount);
		redisUtil.incr(queueName, 1);
		redisUtil.expire(queueName, 300);
	}

	public void updateStatusSync(long planUUID) {
		List<Long> list = new ArrayList<>();
		list.add(planUUID);
		if (list.size() > 0) {
			List<Integer> orgIds = new ArrayList<>();
			orgIds.add(IdUtils.doParse(Long.valueOf(planUUID)).getOrgId());
			disMapper.updateDispatchPlanListByStatusSYNC(list, Constant.STATUS_SYNC_0, orgIds);
		}
	}

	public void insertPush(DispatchPlan dispatchPlan) {
		PushRecords record = new PushRecords();
		try {
			record.setCreateTime(DateUtil.getCurrent4Time());
		} catch (Exception e) {
			logger.error("error", e);
		}
		record.setPhone(dispatchPlan.getPhone());
		record.setPlanuuid(dispatchPlan.getPlanUuidLong());
		record.setUserId(dispatchPlan.getUserId());
		record.setLine(dispatchPlan.getLine());
		record.setRobot(dispatchPlan.getRobot());
		record.setCallbackStatus(Constant.NOCALLBACK);
		rabbitTemplate.convertAndSend("dispatch.PushPhonesRecords", JsonUtils.bean2Json(record));
	}

}
