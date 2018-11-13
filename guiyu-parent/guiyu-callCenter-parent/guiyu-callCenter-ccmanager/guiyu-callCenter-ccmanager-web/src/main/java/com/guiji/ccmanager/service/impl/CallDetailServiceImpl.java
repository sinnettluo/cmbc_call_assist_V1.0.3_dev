package com.guiji.ccmanager.service.impl;

import com.guiji.callcenter.dao.CallOutDetailMapper;
import com.guiji.callcenter.dao.CallOutDetailRecordMapper;
import com.guiji.callcenter.dao.CallOutPlanMapper;
import com.guiji.callcenter.dao.entity.*;
import com.guiji.ccmanager.service.CallDetailService;
import com.guiji.ccmanager.vo.CallOutDetailVO;
import com.guiji.ccmanager.vo.CallOutPlanVO;
import com.guiji.utils.BeanUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/29 0029 17:32
 * @Description:
 */
@Service
public class CallDetailServiceImpl implements CallDetailService {

    @Autowired
    private CallOutPlanMapper callOutPlanMapper;

    @Autowired
    private CallOutDetailMapper callOutDetailMapper;

    @Autowired
    private CallOutDetailRecordMapper callOutDetailRecordMapper;

    @Override
    public List<CallOutPlan> callrecord(Date startDate, Date endDate, String customerId, int pageSize, int pageNo ){

        CallOutPlanExample example = new CallOutPlanExample();
        CallOutPlanExample.Criteria criteria = example.createCriteria();
        if(startDate!=null){
            criteria.andCallStartTimeGreaterThan(startDate);
        }
        if(endDate!=null){
            criteria.andCallStartTimeLessThan(endDate);
        }
        if(StringUtils.isNotBlank(customerId)){
            criteria.andCustomerIdEqualTo(customerId);
        }

        int limitStart = (pageNo-1)*pageSize;
        example.setLimitStart(limitStart);
        example.setLimitEnd(pageSize);

        List<CallOutPlan> list = callOutPlanMapper.selectByExample(example);
        return list;
    }

    @Override
    public int callrecordCount(Date startDate, Date endDate, String customerId) {
        CallOutPlanExample example = new CallOutPlanExample();
        CallOutPlanExample.Criteria criteria = example.createCriteria();
        if(startDate!=null){
            criteria.andCallStartTimeGreaterThan(startDate);
        }
        if(endDate!=null){
            criteria.andCallStartTimeLessThan(endDate);
        }
        if(StringUtils.isNotBlank(customerId)){
            criteria.andCustomerIdEqualTo(customerId);
        }
        return callOutPlanMapper.countByExample(example);
    }

    @Override
    public CallOutPlanVO getCallDetail(String callId) {

        CallOutPlan callOutPlan = callOutPlanMapper.selectByPrimaryKey(callId);

        if(callOutPlan!=null){

            CallOutDetailExample example = new CallOutDetailExample();
            CallOutDetailExample.Criteria criteria = example.createCriteria();
            criteria.andCallIdEqualTo(callId);
            List<CallOutDetail> details = callOutDetailMapper.selectByExample(example);

            CallOutDetailRecordExample exampleRecord = new CallOutDetailRecordExample();
            CallOutDetailRecordExample.Criteria criteriaRecord = exampleRecord.createCriteria();
            criteriaRecord.andCallIdEqualTo(callId);
            List<CallOutDetailRecord> records = callOutDetailRecordMapper.selectByExample(exampleRecord);

            List<CallOutDetailVO> resList = new ArrayList<CallOutDetailVO>();
            for (CallOutDetail callOutDetail:details){
                CallOutDetailVO callOutDetailVO = new CallOutDetailVO();
                BeanUtil.copyProperties(callOutDetail,callOutDetailVO);
                for(CallOutDetailRecord callOutDetailRecord:records){
                    if(callOutDetailRecord.getCallDetailId() == callOutDetail.getCallDetailId() ){
                        BeanUtil.copyProperties(callOutDetailRecord,callOutDetailVO);
                    }
                }
                resList.add(callOutDetailVO);
            }

            CallOutPlanVO callOutPlanVO = new CallOutPlanVO();
            BeanUtil.copyProperties(callOutPlan,callOutPlanVO);
            callOutPlanVO.setDetailList(resList);
            return callOutPlanVO;
        }

        return null;
    }

    @Override
    public String getDialogue(String callId) {
        CallOutDetailExample example = new CallOutDetailExample();
        CallOutDetailExample.Criteria criteria = example.createCriteria();
        criteria.andCallIdEqualTo(callId);
        example.setOrderByClause("bot_answer_time");
        List<CallOutDetail> list = callOutDetailMapper.selectByExample(example);
        String result="";
        if(list!=null && list.size()>0){
            for(CallOutDetail callOutDetail:list){
                String botSay = callOutDetail.getBotAnswerText();
                String customerSay = callOutDetail.getCustomerSayText();
                result += "机器人："+(botSay==null ? "":botSay)+"\r\n客户："+ (customerSay==null ? "":customerSay)+"\r\n";
            }
        }
        return  result;
    }

}
