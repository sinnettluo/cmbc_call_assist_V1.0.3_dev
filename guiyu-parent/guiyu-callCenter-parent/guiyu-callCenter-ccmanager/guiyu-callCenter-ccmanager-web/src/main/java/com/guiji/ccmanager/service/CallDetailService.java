package com.guiji.ccmanager.service;

import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.callcenter.dao.entity.CallOutRecord;
import com.guiji.ccmanager.vo.CallDetailUpdateReq;
import com.guiji.ccmanager.vo.CallOutPlan4ListSelect;
import com.guiji.ccmanager.vo.CallPlanDetailRecordVO;
import com.guiji.ccmanager.vo.CallRecordReq;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/29 0029 17:32
 * @Description:
 */
public interface CallDetailService {
    List<CallOutPlan4ListSelect> callrecord(Date startDate, Date endDate, Boolean isSuperAdmin, String customerId, String orgCode,
                                            int pageSize, int pageNo, String phoneNum, String durationMin, String durationMax,
                                            String accurateIntent, String freason, String callId, String tempId, String isRead, Integer isDesensitization);

    CallPlanDetailRecordVO getCallDetail(BigInteger callId);

    int callrecordCount(Date start, Date end, Boolean isSuperAdmin, String customerId, String orgCode, String phoneNum, String durationMin, String durationMax,
                        String accurateIntent, String freason, String callId, String tempId, String isRead);

    String getDialogue(String callId);

    Map<String, String> getDialogues(List<BigInteger> callIds);

    String getRecordFileUrl(String callId);

    List<CallOutRecord> getRecords(String callIds);

    void delRecord(String callId);

    List<CallPlanDetailRecordVO> getCallPlanDetailRecord(List<String> uuids);

    List<String> getFtypes();

    void updateIsRead(String callId);

    void updateCallDetailCustomerSayText(CallDetailUpdateReq callDetailUpdateReq, Long userId);

    List<Map> getCallRecordList(CallRecordReq callRecordReq);

    int countCallRecordList(CallRecordReq callRecordReq);

    List<CallOutPlan> getCallRecordListByPhone(String phone);

    List<CallOutPlan4ListSelect> getCallPlanList(List<BigInteger> callIds, Long userId, Boolean isSuperAdmin, Integer isDesensitization);
}