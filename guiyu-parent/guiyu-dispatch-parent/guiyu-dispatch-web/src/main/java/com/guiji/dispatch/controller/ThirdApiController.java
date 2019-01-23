package com.guiji.dispatch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.guiji.auth.api.IAuth;
import com.guiji.ccmanager.api.ICallManagerOut;
import com.guiji.ccmanager.vo.CallPlanDetailRecordVO;
import com.guiji.common.model.Page;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.dispatch.api.IThirdApiOut;
import com.guiji.dispatch.batchimport.BatchImportErrorCodeEnum;
import com.guiji.dispatch.batchimport.IBatchImportFieRecordErrorService;
import com.guiji.dispatch.bean.ThirdCheckParams;
import com.guiji.dispatch.dao.DispatchPlanBatchMapper;
import com.guiji.dispatch.dao.DispatchPlanMapper;
import com.guiji.dispatch.dao.FileErrorRecordsMapper;
import com.guiji.dispatch.dao.ThirdImportErrorMapper;
import com.guiji.dispatch.dao.ThirdInterfaceRecordsMapper;
import com.guiji.dispatch.dao.entity.DispatchPlanBatch;
import com.guiji.dispatch.dao.entity.DispatchPlanExample;
import com.guiji.dispatch.dao.entity.FileErrorRecords;
import com.guiji.dispatch.dao.entity.FileErrorRecordsExample;
import com.guiji.dispatch.dao.entity.FileRecordsExample;
import com.guiji.dispatch.dao.entity.ThirdImportError;
import com.guiji.dispatch.dao.entity.ThirdInterfaceRecords;
import com.guiji.dispatch.dao.entity.ThirdInterfaceRecordsExample;
import com.guiji.dispatch.model.DispatchPlan;
import com.guiji.dispatch.model.DispatchPlanApi;
import com.guiji.dispatch.model.DispatchPlanList;
import com.guiji.dispatch.model.PlanCallInfoCount;
import com.guiji.dispatch.model.PlanResultInfo;
import com.guiji.dispatch.service.IDispatchPlanService;
import com.guiji.dispatch.thirdapi.ThirdApiImportQueueHandler;
import com.guiji.dispatch.util.Constant;
import com.guiji.user.dao.entity.SysUser;
import com.guiji.utils.DateUtil;
import com.guiji.utils.HttpClientUtil;
import com.guiji.utils.IdGenUtil;

import ai.guiji.botsentence.api.IBotSentenceProcess;
import ai.guiji.botsentence.api.entity.BotSentenceProcess;
import ai.guiji.botsentence.api.entity.ServerResult;

/**
 * 第三方接口
 * 
 * @author Administrator
 *
 */
@RestController
public class ThirdApiController implements IThirdApiOut {

	static Logger logger = LoggerFactory.getLogger(ThirdApiController.class);

	@Autowired
	private IDispatchPlanService dispatchPlanService;
	@Autowired
	private DispatchPlanMapper dispatchPlanMapper;
	@Autowired
	private IAuth auth;
	@Autowired
	private DispatchPlanBatchMapper batchMapper;
	@Autowired
	private ICallManagerOut callManagerOut;
	@Autowired
	private IBotSentenceProcess Process;
	@Autowired
	private ThirdApiImportQueueHandler thirdApiHandler;
	@Autowired
	private IBatchImportFieRecordErrorService fileRecordErrorService;
	@Autowired
	private FileErrorRecordsMapper fileErrorRecordsMapper;
	@Autowired
	private ThirdInterfaceRecordsMapper thirdCallBackMapper;

	@Override
	@GetMapping(value = "out/getCalldetail")
	public ReturnData<Page<com.guiji.dispatch.model.CallPlanDetailRecordVO>> getCalldetail(String phone,
			String batchNumber, int pagenum, int pagesize) {
		Page<com.guiji.dispatch.model.CallPlanDetailRecordVO> page = new Page<>();
		List<CallPlanDetailRecordVO> queryDispatchPlanByPhoens = dispatchPlanService.queryDispatchPlanByPhoens(phone,
				batchNumber, pagenum, pagesize);
		DispatchPlanExample ex = new DispatchPlanExample();
		ex.createCriteria().andPhoneEqualTo(phone).andBatchNameEqualTo(batchNumber);
		int countByExample = dispatchPlanMapper.countByExample(ex);
		List<com.guiji.dispatch.model.CallPlanDetailRecordVO> list = new ArrayList<>();
		for (CallPlanDetailRecordVO vo : queryDispatchPlanByPhoens) {
			com.guiji.dispatch.model.CallPlanDetailRecordVO record = new com.guiji.dispatch.model.CallPlanDetailRecordVO();
			BeanUtils.copyProperties(vo, record);
			list.add(record);
		}
		page.setRecords(list);
		page.setPageNo(pagenum);
		page.setPageSize(pagesize);
		page.setTotal(countByExample);
		ReturnData<Page<com.guiji.dispatch.model.CallPlanDetailRecordVO>> returndata = new ReturnData<>();
		returndata.setBody(page);
		return returndata;
	}

	@Override
	@GetMapping(value = "out/getcall4BatchName")
	public ReturnData<PlanCallInfoCount> getcall4BatchName(String batchName, int pagenum, int pagesize) {
		int countAlready = dispatchPlanService.getcall4BatchName(batchName, Constant.STATUSPLAN_1);
		int countNo = dispatchPlanService.getcall4BatchName(batchName, Constant.STATUSPLAN_2);
		PlanCallInfoCount info = new PlanCallInfoCount();
		info.setSuccCount(countAlready);
		info.setPlanCount(countNo);
		FileErrorRecords record = new FileErrorRecords();
		record.setBatchName(batchName);
		record.setDataType(Constant.IMPORT_DATA_TYPE_API);
		Page<FileErrorRecords> queryFileErrorRecordsPage = fileRecordErrorService.queryFileErrorRecordsPage(pagenum,
				pagesize, record);
		List<com.guiji.dispatch.model.FileErrorRecords> list = new ArrayList<>();
		for (FileErrorRecords vo : queryFileErrorRecordsPage.getRecords()) {
			com.guiji.dispatch.model.FileErrorRecords bean = new com.guiji.dispatch.model.FileErrorRecords();
			BeanUtils.copyProperties(vo, bean);
			list.add(bean);
		}
		Page<com.guiji.dispatch.model.FileErrorRecords> page = new Page<>();
		page.setRecords(list);
		page.setPageNo(queryFileErrorRecordsPage.getPageNo());
		page.setPageSize(queryFileErrorRecordsPage.getPageSize());
		FileErrorRecordsExample errorEx = new FileErrorRecordsExample();
		errorEx.createCriteria().andBatchNameEqualTo(batchName);
		int countByExample = fileErrorRecordsMapper.countByExample(errorEx);
		page.setTotal(countByExample);
		info.setErrorRecordsList(page);
		ReturnData<PlanCallInfoCount> returndata = new ReturnData<>();
		returndata.setBody(info);
		return returndata;
	}

	@Override
	@GetMapping(value = "out/queryDispatchPlan")
	public ReturnData<Page<DispatchPlanApi>> queryDispatchPlan(String batchName, int pagenum, int pagesize) {
		Page<DispatchPlanApi> page = new Page<>();
		page.setPageNo(pagenum);
		page.setPageSize((pagesize));
		DispatchPlanExample example = new DispatchPlanExample();
		example.setLimitStart((pagenum - 1) * pagesize);
		example.setLimitEnd(pagesize);
		example.createCriteria().andBatchNameEqualTo(batchName).andIsDelEqualTo(Constant.IS_DEL_0);
		List<com.guiji.dispatch.dao.entity.DispatchPlan> list = dispatchPlanMapper.selectByExample(example);
		int countByExample = dispatchPlanMapper.countByExample(example);
		page.setTotal(countByExample);
		List<DispatchPlanApi> copyBean = new ArrayList<>();
		for (com.guiji.dispatch.dao.entity.DispatchPlan plan : list) {
			DispatchPlanApi bean = new DispatchPlanApi();
			BeanUtils.copyProperties(plan, bean);
			copyBean.add(bean);
		}
		page.setRecords(copyBean);
		ReturnData<Page<DispatchPlanApi>> returnData = new ReturnData<>();
		returnData.setBody(page);
		return returnData;
	}

	/**
	 * @param dispatchPlanList
	 * @return
	 */
	@Override
	@PostMapping(value = "out/insertDispatchPlanList")
	public ReturnData<PlanResultInfo> insertDispatchPlanList(@RequestBody DispatchPlanList dispatchPlanList) {
		// 检验基本参数
		ThirdCheckParams checkBaseParams = checkBaseParams(dispatchPlanList);
		if (!checkBaseParams.isResult()) {
			PlanResultInfo info = new PlanResultInfo();
			info.setMsg(checkBaseParams.getMsg());
			ReturnData<PlanResultInfo> returnData = new ReturnData<>();
			returnData.setBody(info);
			return returnData;
		}

		// 检查批次名字是否存在
		if (dispatchPlanService.checkBatchId(dispatchPlanList.getBatchName())) {
			PlanResultInfo info = new PlanResultInfo();
			info.setMsg("批次已经存在");
			ReturnData<PlanResultInfo> returnData = new ReturnData<>();
			returnData.setBody(info);
			return returnData;
		}

		ReturnData<SysUser> user = auth.getUserById(Long.valueOf(dispatchPlanList.getUserId()));
		String username = user.getBody().getUsername();
		String lineName = callManagerOut.getLineInfoById(Integer.valueOf(dispatchPlanList.getLine())).getBody();

		DispatchPlanBatch batch = new DispatchPlanBatch();
		batch.setName(dispatchPlanList.getBatchName());
		batch.setUserId(Integer.valueOf(dispatchPlanList.getUserId()));
		batch.setStatusShow(Constant.BATCH_STATUS_SHOW);
		batch.setGmtCreate(DateUtil.getCurrent4Time());
		batch.setGmtModified(DateUtil.getCurrent4Time());
		batch.setOrgCode(user.getBody().getOrgCode());
		batchMapper.insert(batch);

		List<com.guiji.dispatch.dao.entity.DispatchPlan> fails = new ArrayList<>();
		List<com.guiji.dispatch.dao.entity.DispatchPlan> succ = new ArrayList<>();
		List<String> phones = new ArrayList<>();
		for (int i = 0; i < dispatchPlanList.getMobile().size(); i++) {
			DispatchPlan dispatchPlan = dispatchPlanList.getMobile().get(i);
			com.guiji.dispatch.dao.entity.DispatchPlan bean = new com.guiji.dispatch.dao.entity.DispatchPlan();
			BeanUtils.copyProperties(dispatchPlan, bean);
			if (bean.getPhone() == null || bean.getPhone() == "" || !isInteger(bean.getPhone())) {
				// 记录错误信息
				saveErrorRecords(dispatchPlan, BatchImportErrorCodeEnum.UNKNOWN, i);
				fails.add(bean);
				continue;
			}
			bean.setPlanUuid(IdGenUtil.uuid());
			bean.setBatchId(batch.getId());
			bean.setUserId(Integer.valueOf(dispatchPlanList.getUserId()));
			bean.setLine(Integer.valueOf(dispatchPlanList.getLine()));
			bean.setRobot(dispatchPlanList.getRobot());
			bean.setClean(Integer.valueOf(dispatchPlanList.getIsClean()));
			bean.setCallHour(dispatchPlanList.getCallHour());
			bean.setCallData(Integer.valueOf(dispatchPlanList.getCallDate()));
			bean.setFlag(Constant.IS_FLAG_0);
			bean.setGmtCreate(DateUtil.getCurrent4Time());
			bean.setGmtModified(DateUtil.getCurrent4Time());
			bean.setUsername(username);
			bean.setLineName(lineName);
			bean.setIsDel(Constant.IS_DEL_0);
			bean.setStatusPlan(Constant.STATUSPLAN_1);
			bean.setStatusSync(Constant.STATUS_SYNC_0);
			bean.setOrgCode(user.getBody().getOrgCode());
			bean.setBatchName(dispatchPlanList.getBatchName());
			if (phones.contains(bean.getPhone())) {
				saveErrorRecords(dispatchPlan, BatchImportErrorCodeEnum.DUPLICATE, i);
				continue;
			}
			thirdApiHandler.add(bean);
			succ.add(bean);
			phones.add(bean.getPhone());
		}

		PlanResultInfo info = new PlanResultInfo();
		info.setSuccCount(succ.size());
		info.setErrorCount(fails.size());
		ReturnData<PlanResultInfo> returnData = new ReturnData<>();
		returnData.setBody(info);
		return returnData;
	}

	/**
	 * 记录第三方的错误信息
	 * 
	 * @param vo
	 * @param unknown
	 * @param i
	 */
	private void saveErrorRecords(DispatchPlan vo, BatchImportErrorCodeEnum errorCodeEnum, int i) {
		FileErrorRecords records = new FileErrorRecords();
		records.setAttach(vo.getAttach());
		records.setCreateTime(DateUtil.getCurrent4Time());
		records.setParams(vo.getParams());
		records.setPhone(vo.getPhone());
		records.setFileRecordsId(Long.valueOf(vo.getFileRecordId()));
		records.setErrorType(errorCodeEnum.getValue());
		records.setDataType(Constant.IMPORT_DATA_TYPE_API);
		records.setBatchId(vo.getBatchId());
		records.setBatchName(vo.getBatchName());
		fileRecordErrorService.save(records);
	}

	/**
	 * 校验基本参数
	 * 
	 * @param dispatchPlanList
	 * @return
	 */
	private ThirdCheckParams checkBaseParams(DispatchPlanList dispatchPlanList) {
		ThirdCheckParams checkResult = new ThirdCheckParams();

		if (dispatchPlanList.getRobot() == null || dispatchPlanList.getRobot() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("robot参数必填,请检查");
			return checkResult;
		}

		if (dispatchPlanList.getLine() == null || dispatchPlanList.getLine() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("line参数必填,请检查");
			return checkResult;
		}

		if (dispatchPlanList.getIsClean() == null || dispatchPlanList.getIsClean() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("isClean参数必填,请检查");
			return checkResult;
		}

		if (dispatchPlanList.getCallDate() == null || dispatchPlanList.getCallDate() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("callDate参数必填,请检查");
			return checkResult;
		}

		if (dispatchPlanList.getCallHour() == null || dispatchPlanList.getCallHour() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("callHour参数必填,请检查");
			return checkResult;
		}

		if (dispatchPlanList.getBatchName() == null || dispatchPlanList.getBatchName() == "") {
			checkResult.setResult(false);
			checkResult.setMsg("batchName参数必填,请检查");
			return checkResult;
		}
		// 检查用户
		ReturnData<SysUser> user = auth.getUserById(Long.valueOf(dispatchPlanList.getUserId()));
		// 检查线路
		String lineName = callManagerOut.getLineInfoById(Integer.valueOf(dispatchPlanList.getLine())).getBody();
		// 检查话术
		ServerResult<List<BotSentenceProcess>> templateById = Process.getTemplateById(dispatchPlanList.getRobot());

		if (user.getBody() == null) {
			checkResult.setResult(false);
			checkResult.setMsg("用户id不存在");
			return checkResult;
		}

		if (lineName == null) {
			checkResult.setResult(false);
			checkResult.setMsg("线路id不存在");
			return checkResult;
		}
		if (templateById == null) {
			if (templateById.getData().size() == 0) {
				checkResult.setResult(false);
				checkResult.setMsg("话术模板不存在");
				return checkResult;
			}
			checkResult.setResult(false);
			checkResult.setMsg("话术模板不存在");
			return checkResult;
		}

		return checkResult;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^(?!11)\\d{11}$");
		return pattern.matcher(str).matches();
	}

	@Override
	public ReturnData<Boolean> reTryThirdApi(Long userId) {
		// 查询出失败的记录
		ThirdInterfaceRecordsExample ex = new ThirdInterfaceRecordsExample();
		ex.createCriteria().andUserIdEqualTo(userId.intValue()).andTypeEqualTo(Constant.THIRDAPIFAILURE);
		List<ThirdInterfaceRecords> selectByExample = thirdCallBackMapper.selectByExample(ex);
		for (ThirdInterfaceRecords record : selectByExample) {
			String url = record.getUrl();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", record.getParams());
			try {
				HttpClientUtil.doPostJson(url, jsonObject.toString());
				record.setType(Constant.THIRDAPISUCCESS);
				thirdCallBackMapper.updateByPrimaryKey(record);
			} catch (Exception e) {
				logger.error("reTryThirdApiError", e);
			}
		}
		ReturnData<Boolean> returndata = new ReturnData<>();
		return returndata;
	}

}