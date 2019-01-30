package com.guiji.fs;

import com.guiji.cache.CallCache;
import com.guiji.config.FsBotConfig;
import com.guiji.entity.*;
import com.guiji.eventbus.SimpleEventSender;
import com.guiji.eventbus.event.*;
import com.guiji.service.CallPlanService;
import com.guiji.util.CommonUtil;
import com.guiji.util.DateUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class FsEventHandler {
    @Autowired
    CallPlanService callPlanService;

    @Autowired
    SimpleEventSender simpleEventSender;

    @Autowired
    FsBotConfig fsBotConfig;

    @Autowired
    CallCache callCache;

    public void handleEvent(EslEvent eslEvent) {
        String eventName = eslEvent.getEventName();
        Map<String, String> eventHeaders = eslEvent.getEventHeaders();
        String subEventName = eventHeaders.get("Event-Subclass");
        log.info("收到事件[{}][{}]", eventName, subEventName);

        if (eventName.equalsIgnoreCase("custom")) {
            eventName = subEventName;
        }

        try {
            EslEventType eventType;
            try {
                eventType = EslEventType.getByValue(eventName);
            } catch (Exception ex) {
                log.info("该事件目前不用处理，跳过" + eventName);
                throw new Exception("invalid event " + eventName);
            }

            if (eventType == EslEventType.EV_ALIASR) {
                log.info("zrg_开始处理EV_ALIASR事件[{}]", eslEvent);
                postAliAsrEvent(eventHeaders);
            } else if (eventType == EslEventType.CHANNEL_ANSWER) {
                log.info("zrg_开始处理CHANNEL_ANSWER事件[{}]", eslEvent);
                postChannelAnswerEvent(eventHeaders);
            } else if (eventType == EslEventType.CHANNEL_HANGUP_COMPLETE) {
                log.info("zrg_开始处理CHANNEL_HANGUP事件[{}]", eslEvent);
                postHangupEvent(eventHeaders);
            } else if (eventType == EslEventType.CALLCENTER_INFO) {
                log.info("zrg_开始处理CALLCENTER_INFO事件[{}]", eslEvent);
                postCallCenterEvent(eslEvent);
            }
        } catch (Exception ex) {
            log.warn("处理事件出现异常", ex);
        }
    }

    private void postCallCenterEvent(EslEvent eslEvent) {
        Map<String, String> eventHeaders = eslEvent.getEventHeaders();
        String action = eventHeaders.get("CC-Action");
        if (action.equals("bridge-agent-start")) {    //座席应答事件
            AgentAnswerEvent event = new AgentAnswerEvent();
            event.setAgentId(eventHeaders.get("CC-Agent"));
            event.setAgentUuid(eventHeaders.get("CC-Agent-UUID"));
            event.setAgentAnswerTime(DateUtil.getCurrentDateTime());
            event.setCustomerNum(eventHeaders.get("CC-Member-CID-Number"));
            event.setCustomerUuid(eventHeaders.get("CC-Member-Session-UUID"));
            event.setAgentGroupId(eventHeaders.get("CC-Queue"));

            CallCache.CallInfo callInfo = callCache.getCallInfo(event.getCustomerUuid());
            if(callInfo !=null){
                event.setCallDirection(callInfo.getCallDirection());
                event.setSeqId(callInfo.getSeqId());
                callCache.put(event.getAgentUuid(), callInfo.getSeqId(), callInfo.getCallDirection(), EUserType.AGENT);
            }

            simpleEventSender.sendEvent(event);
        }
    }

    private void postChannelAnswerEvent(Map<String, String> eventHeaders) {
        ChannelAnswerEvent event = new ChannelAnswerEvent();
        event.setUuid(eventHeaders.get("Unique-ID"));
        event.setCallerNum(eventHeaders.get("Caller-Caller-ID-Number"));
        event.setCalledNum(eventHeaders.get("Caller-Destination-Number"));
        event.setSeqId(eventHeaders.get("variable_ai_seq_id"));

        ECallDirection callDirection = null;
        String direction = eventHeaders.get("variable_ai_call_direction");
        if("in".equals(direction)){
            callDirection = ECallDirection.INBOUND;
        }else if("out".equals(direction)){
            callDirection = ECallDirection.OUTBOUND;
        }else{
            log.info("该channelAnswer事件没有ai_call_direction, 跳过处理");
            return;
        }

        callCache.put(event.getUuid(), event.getSeqId(), callDirection, EUserType.CUSTOMER);
        event.setCallDirection(callDirection);

        String queueId = eventHeaders.get("variable_ai_queue_id");
        if(!Strings.isNullOrEmpty(queueId)){
            event.setQueueId(Long.parseLong(queueId));
        }

        simpleEventSender.sendEvent(event);
    }


    private void postHangupEvent(Map<String, String> eventHeaders) {
        ChannelHangupEvent event = ChannelHangupEvent.builder()
                .uuid(eventHeaders.get("Unique-ID"))
                .billSec(Integer.parseInt(eventHeaders.get("variable_billsec")))
                .duration(Integer.parseInt(eventHeaders.get("variable_duration")))
                .startStamp(eventHeaders.get("variable_start_stamp"))
                .progressStamp(eventHeaders.get("variable_progress_media_stamp"))
                .answerStamp(eventHeaders.get("variable_answer_stamp"))
                .hangupStamp(eventHeaders.get("variable_end_stamp"))
                .hangupDisposition(eventHeaders.get("variable_sip_hangup_disposition"))
                .sipHangupCause(eventHeaders.get("variable_sip_term_status"))
                .build();


        CallCache.CallInfo callInfo = callCache.getCallInfo(event.getUuid());
        if(callInfo !=null){
            event.setCallDirection(callInfo.getCallDirection());
            callCache.invalidate(event.getUuid());
        }

        simpleEventSender.sendEvent(event);
    }


    private void postAliAsrEvent(Map<String, String> eventHeaders) {
        AsrBaseEvent event = new AsrBaseEvent();
        event.setAnswered(eventHeaders.get("Answered"));
        event.setChannel(eventHeaders.get("Channel"));
        event.setTimestamp(eventHeaders.get("Timestamp"));

        String fileName = eventHeaders.get("FileName");
        if (!Strings.isNullOrEmpty(fileName)) {
            fileName = fileName.trim();
            event.setFileName(fileName);
            event.setFilePath(fileName);
        }

        InnerAsrResponse innerAsrResponse = CommonUtil.jsonToJavaBean(eventHeaders.get("ASR-Response"), InnerAsrResponse.class);
        if (innerAsrResponse != null) {
            event.setAsrText(innerAsrResponse.getAsrText());
            event.setAsrEndTime(innerAsrResponse.getEndTime());
            event.setAsrStartTime(innerAsrResponse.getBeginTime());
            event.setAsrDuration(innerAsrResponse.getDuration());
        }

        String uuid = eventHeaders.get("UUID");
        CallCache.CallInfo callInfo = callCache.getCallInfo(uuid);

        event.setCallDirection(callInfo.getCallDirection());
        CallPlan callPlan = callPlanService.findByCallId(callInfo.getSeqId(), event.getCallDirection());
        if(callPlan.getCallState() != CallState.agent_answer.ordinal()){
            log.info("座席尚未接听，忽略该asr， seqId[{}]", callInfo.getSeqId());
            return;
        }

        if (callPlan != null) {
            event.setCallPlan(callPlan);
            if (callInfo.getUserType() == EUserType.CUSTOMER) {
                event.setUuid(uuid);
                AsrCustomerEvent asrCustomerEvent = new AsrCustomerEvent();
                BeanUtils.copyProperties(event, asrCustomerEvent);
                simpleEventSender.sendEvent(asrCustomerEvent);
            } else if (callInfo.getUserType() == EUserType.AGENT) {
                if (callPlan.getCallState() == CallState.agent_answer.ordinal()) {
                    event.setUuid(callPlan.getAgentChannelUuid());
                    AsrAgentEvent asrAgentEvent = new AsrAgentEvent();
                    BeanUtils.copyProperties(event, asrAgentEvent);
                    simpleEventSender.sendEvent(asrAgentEvent);
                } else {
                    log.warn("callPlan[{}]尚未进入座席接听状态，忽略掉该座席的asr事件。", callPlan.getPlanUuid());
                }
            }
        } else {
            log.warn("收到的Asr事件不属于当前系统(没有根据agentChannelUuid查到计划)，跳过处理，eventHeaders:[{}]", eventHeaders);
        }

        log.info("结束处理ALI_ASR事件[{}]", event);
    }
}