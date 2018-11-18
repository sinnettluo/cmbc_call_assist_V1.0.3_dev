package com.guiji.calloutserver.service.impl;

import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.callcenter.dao.entity.CallOutRecord;
import com.guiji.calloutserver.fs.LocalFsServer;
import com.guiji.calloutserver.manager.FsLineManager;
import com.guiji.calloutserver.config.AliAsrConfig;
import com.guiji.calloutserver.service.CallService;
import com.guiji.fsline.entity.FsLineVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 魏驰
 * @Date: 2018/11/4 17:12
 * @Project：guiyu-parent
 * @Description:
 */
@Slf4j
@Service
public class CallServiceImpl implements CallService {
    @Autowired
    AliAsrConfig aliAsrConfig;

    @Autowired
    FsLineManager fsLineManager;

    @Autowired
    LocalFsServer fsManager;

    /**
     * 调用底层FreeSWITCH接口，发起外呼
     */
    @Override
    public void makeCall(CallOutPlan callplan, CallOutRecord callRecord) {
        //获取线路服务器
        FsLineVO fsLine = fsLineManager.getFsLine();

        //构建外呼命令
        String cmd = String.format("originate {origination_uuid=%s,origination_caller_id_name=%s}" +
                            "sofia/external/%s@%s:%s 'start_asr:%s %s" +
                            ", record_session:/recordings/%s" +
                            ", park' inline",
                    callplan.getCallId(),
                    callplan.getLineId(),
                    callplan.getPhoneNum(),
                    fsLine.getFsIp(),
                    fsLine.getFsInPort(),
                    aliAsrConfig.getAccessId(),
                    aliAsrConfig.getAccessSecret(),
                    callRecord.getRecordFile());

        log.debug("开始执行呼叫命令[{}]", cmd);
        fsManager.executeAsync(cmd);
    }
}