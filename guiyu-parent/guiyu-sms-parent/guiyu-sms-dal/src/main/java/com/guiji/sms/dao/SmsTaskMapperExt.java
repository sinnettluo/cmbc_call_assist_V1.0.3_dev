package com.guiji.sms.dao;

import java.util.List;

import com.guiji.sms.dao.entity.SmsTask;

public interface SmsTaskMapperExt 
{
	/**
	 * 一键停止（修改运行状态）
	 */
    int updateRunStatusByPrimaryKey(Integer id);

    /**
	 * 审核（修改审核状态和运行状态）
	 */
	int updateAuditingStatusAndRunStatusByPrimaryKey(Integer id);

	/**
	 * 获取定时任务
	 */
	List<SmsTask> getTasks();
}