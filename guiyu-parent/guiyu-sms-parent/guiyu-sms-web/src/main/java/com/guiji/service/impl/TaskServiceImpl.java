package com.guiji.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiji.auth.api.IAuth;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.entity.SmsConstants;
import com.guiji.model.TaskReq;
import com.guiji.service.SendSmsService;
import com.guiji.service.TaskService;
import com.guiji.sms.dao.SmsTaskMapper;
import com.guiji.sms.dao.SmsTaskMapperExt;
import com.guiji.sms.dao.entity.SmsTask;
import com.guiji.sms.dao.entity.SmsTaskExample;
import com.guiji.sms.dao.entity.SmsTaskExample.Criteria;
import com.guiji.sms.vo.TaskListReqVO;
import com.guiji.sms.vo.TaskListRspVO;
import com.guiji.sms.vo.TaskReqVO;
import com.guiji.user.dao.entity.SysOrganization;
import com.guiji.utils.ParseFileUtil;
import com.guiji.utils.RedisUtil;

@Service
public class TaskServiceImpl implements TaskService
{
	@Autowired
	IAuth auth;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	SendSmsService sendSmsService;
	@Autowired
	SmsTaskMapper taskMapper;
	@Autowired
	SmsTaskMapperExt taskMapperExt;

	/**
	 * 获取短信任务列表
	 * @throws Exception 
	 */
	@Override
	public TaskListRspVO getTaskList(TaskListReqVO taskListReq, Long userId) throws Exception
	{
		TaskListRspVO taskListRsp = new TaskListRspVO();
		
		SmsTaskExample example = new SmsTaskExample();
		Criteria criteria = example.createCriteria();
		ReturnData<SysOrganization> sysOrganization = auth.getOrgByUserId(userId);
		criteria.andOrgCodeLike(sysOrganization.body.getCode()+"%");
		if(taskListReq.getStatus() != null){
			criteria.andSendStatusEqualTo(taskListReq.getStatus()); //任务状态
		}
		if(StringUtils.isNotEmpty(taskListReq.getTaskName())){
			criteria.andTaskNameLike(taskListReq.getTaskName()); //任务名称
		}
		if(StringUtils.isNotEmpty(taskListReq.getStartDate())){
			criteria.andSendDateGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
														.parse(taskListReq.getStartDate()+" 00:00:00"));
		}
		if(StringUtils.isNotEmpty(taskListReq.getEndDate())){
			criteria.andSendDateLessThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
														.parse(taskListReq.getEndDate()+" 23:59:59"));
		}
		taskListRsp.setTotalCount(taskMapper.selectByExampleWithBLOBs(example).size()); //总条数
		
		example.setLimitStart((taskListReq.getPageNum() - 1) * taskListReq.getPageSize());
		example.setLimitEnd(taskListReq.getPageSize());
		example.setOrderByClause("id desc");
		taskListRsp.setSmsTaskList(taskMapper.selectByExampleWithBLOBs(example)); // 分页返回的记录
		
		return taskListRsp;
	}

	/**
	 * 短信任务一键停止
	 */
	@Override
	public void stopTask(Integer id)
	{
		taskMapperExt.updateRunStatusByPrimaryKey(id);
	}

	/**
	 * 删除短信任务
	 */
	@Override
	public void delTask(Integer id)
	{
		taskMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 新增短信任务
	 */
	@Override
	public void addTask(TaskReqVO taskReqVO, Long userId) throws Exception
	{
		SmsTask smsTask = new SmsTask();
		setParams(taskReqVO, smsTask, userId); //设置字段值
		
		// 解析excel文件
		List<String> phoneList = null;
		phoneList = ParseFileUtil.parseExcelFile(taskReqVO.getFile());
		 
		smsTask.setPhoneNum(phoneList.size());
		smsTask.setFileName(taskReqVO.getFile().getOriginalFilename());
		
		TaskReq taskReq = null;
		Date currentDate = new Date();
		if(taskReqVO.getSendType() == SmsConstants.HandSend)
		{
			if(smsTask.getAuditingStatus() == SmsConstants.UnAuditing) {
				smsTask.setSendStatus(SmsConstants.UnStart); // 0-未开始
			} else {
				//组装发送请求
				taskReq = new TaskReq(taskReqVO.getTaskName(), taskReqVO.getSendType(), phoneList, 
						taskReqVO.getTunnelName(), taskReqVO.getSmsTemplateId(), taskReqVO.getSmsContent());
				taskReq.setSendTime(currentDate);
				taskReq.setCompanyName(smsTask.getCompanyName());
				taskReq.setUserId(userId);
//				sendSmsService.pushTaskToMQ(taskReq); // 发送
				smsTask.setSendStatus(SmsConstants.UnStart); // 0-未开始
				smsTask.setSendDate(currentDate);
			}
		} 
		else {
			smsTask.setSendStatus(SmsConstants.UnStart); // 0-未开始
		}
		taskMapper.insertSelective(smsTask); //新增
		
		if(taskReq != null)
		{
			taskReq.setTaskId(smsTask.getId());
			sendSmsService.pushTaskToMQ(taskReq); // 发送
		}
		if(smsTask.getSendStatus() == SmsConstants.UnStart){
			redisUtil.set(smsTask.getId().toString(), phoneList); //未发送名单存入Redis
		}
	}
	
	/**
	 * 编辑短信任务
	 */
	@Override
	public void updateTask(TaskReqVO taskReqVO, Long userId) throws Exception
	{
		SmsTask smsTask = taskMapper.selectByPrimaryKey(taskReqVO.getId());
		setParams(taskReqVO, smsTask, userId);
		
		if(taskReqVO.getSendType() == SmsConstants.HandSend) //手动发送
		{
			if(smsTask.getAuditingStatus() == SmsConstants.UnAuditing) {
				smsTask.setSendStatus(SmsConstants.UnStart); // 0-未开始
			} else {
				List<String> phoneList = (List<String>) redisUtil.get(smsTask.getId().toString());
				//组装发送请求
				TaskReq taskReq = new TaskReq(taskReqVO.getTaskName(), taskReqVO.getSendType(), phoneList, 
						taskReqVO.getTunnelName(), taskReqVO.getSmsTemplateId(), taskReqVO.getSmsContent());
				taskReq.setSendTime(new Date());
				taskReq.setCompanyName(smsTask.getCompanyName());
				taskReq.setUserId(userId);
				sendSmsService.pushTaskToMQ(taskReq); // 发送
				redisUtil.del(smsTask.getId().toString());
			}
		}
		taskMapper.updateByPrimaryKeySelective(smsTask); //编辑
	}
	
	/**
	 * 设置字段值
	 */
	private void setParams(TaskReqVO taskReqVO, SmsTask smsTask, Long userId) throws Exception
	{
		smsTask.setTaskName(taskReqVO.getTaskName());
		smsTask.setSendType(taskReqVO.getSendType());
		smsTask.setTunnelName(taskReqVO.getTunnelName());
		smsTask.setSmsTemplateId(taskReqVO.getSmsTemplateId());
		smsTask.setSmsContent(taskReqVO.getSmsContent());
		if(taskReqVO.getSendDate() != null){
			smsTask.setSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(taskReqVO.getSendDate()));
		}else{
			smsTask.setSendDate(null);
		}
		if(taskReqVO.getSmsTemplateId() != null) { //短信模版
			smsTask.setAuditingStatus(1);
			smsTask.setRunStatus(1);
		} else { //自定义短信 
			smsTask.setAuditingStatus(0);
			smsTask.setRunStatus(0);
		}
		ReturnData<SysOrganization> sysOrganization = auth.getOrgByUserId(userId);
		smsTask.setCompanyId(sysOrganization.body.getId().intValue());
		smsTask.setCompanyName(sysOrganization.body.getName());
		if(taskReqVO.getId() == null) {
			smsTask.setCreateId(userId.intValue());
			smsTask.setCreateTime(new Date());
		}
		smsTask.setUpdateId(userId.intValue());
		smsTask.setUpdateTime(new Date());
		smsTask.setOrgCode(sysOrganization.body.getCode());
	}
	
	/**
	 * 审核短信任务
	 */
	@Override
	public void auditingTask(Integer id)
	{
		taskMapperExt.updateAuditingStatusAndRunStatusByPrimaryKey(id);
		SmsTask smsTask = taskMapper.selectByPrimaryKey(id);
		if (smsTask.getSendType() == SmsConstants.HandSend) // 手动发送
		{
			smsTask.setSendDate(new Date());
			List<String> phoneList = (List<String>) redisUtil.get(smsTask.getId().toString());
			// 组装发送请求
			TaskReq taskReq = new TaskReq(smsTask.getTaskName(), smsTask.getSendType(), phoneList,
					smsTask.getTunnelName(), smsTask.getSmsTemplateId(), smsTask.getSmsContent());
			taskReq.setSendTime(new Date());
			taskReq.setCompanyName(smsTask.getCompanyName());
			taskReq.setUserId(smsTask.getCreateId());
			sendSmsService.pushTaskToMQ(taskReq); // 发送
			redisUtil.del(smsTask.getId().toString());
		}
		taskMapper.updateByPrimaryKeySelective(smsTask); //编辑
		
	}

	/**
	 * 获取定时任务
	 */
	@Override
	public List<SmsTask> getTimeTaskList()
	{
		return taskMapperExt.getTasks();
	}

	/**
	 * 更新发送状态
	 */
	@Override
	public void updateSendStatusById(Integer sendStatus, Integer id)
	{
		taskMapperExt.updateSendStatusById(sendStatus, id);
	}

}
