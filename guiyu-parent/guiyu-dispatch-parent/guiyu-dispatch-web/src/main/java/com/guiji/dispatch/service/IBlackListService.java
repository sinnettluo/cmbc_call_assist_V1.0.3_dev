package com.guiji.dispatch.service;

import org.springframework.web.multipart.MultipartFile;

import com.guiji.common.model.Page;
import com.guiji.dispatch.dao.entity.BlackList;
import com.guiji.dispatch.dao.entity.BlackListRecords;
import com.guiji.dispatch.dao.entity.DispatchPlan;

public interface IBlackListService {

	boolean save(BlackList blackList, Long userId, String orgCode);

	boolean update(BlackList blackList, Long userId);

	boolean delete(String phone);

	void batchPlanImport(String fileName, Long userId, MultipartFile file, String orgCode) throws Exception;

	public boolean checkPhoneInBlackList(String phone,String orgCode);

	boolean setBlackPhoneStatus(DispatchPlan dispatchPlan);

	Page<BlackList> queryBlackListByParams(int pagenum, int pagesize, String phone, String orgCode);
	
	Page<BlackListRecords> queryBlackListRecords(int pagenum, int pagesize, String orgCode);
}
