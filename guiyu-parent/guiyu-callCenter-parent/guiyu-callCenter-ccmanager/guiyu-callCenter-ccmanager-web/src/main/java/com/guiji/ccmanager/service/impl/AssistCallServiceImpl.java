package com.guiji.ccmanager.service.impl;

import com.guiji.callcenter.dao.CallOutPlanMapper;
import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.ccmanager.service.AssistCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AssistCallServiceImpl implements AssistCallService {


    @Autowired
    CallOutPlanMapper callOutPlanMapper;

    @Override
    public CallOutPlan getCallOutplan(BigInteger callId){
        CallOutPlan callOutPlan = callOutPlanMapper.selectByPrimaryKey(callId);

        return callOutPlan;
    }

}
