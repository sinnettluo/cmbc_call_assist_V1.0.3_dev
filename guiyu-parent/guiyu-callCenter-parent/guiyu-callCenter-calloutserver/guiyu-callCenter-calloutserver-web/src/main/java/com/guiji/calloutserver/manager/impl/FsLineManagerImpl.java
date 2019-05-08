package com.guiji.calloutserver.manager.impl;

import com.google.common.base.Preconditions;
import com.guiji.calloutserver.helper.RequestHelper;
import com.guiji.calloutserver.manager.FsLineManager;
import com.guiji.component.result.Result;
import com.guiji.fsmanager.api.ILineOperation;
import com.guiji.fsmanager.entity.FsLineInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 魏驰
 * @Date: 2018/11/4 17:58
 * @Project：guiyu-parent
 * @Description:
 */
@Slf4j
@Service
public class FsLineManagerImpl implements FsLineManager {
    @Autowired
    ILineOperation lineOperation;

    /**
     * 获取线路信息
     * @return
     */
    @Override
    public FsLineInfoVO getFsLine(Integer lineId) {
        Result.ReturnData returnData = null;
        try{
            returnData = RequestHelper.loopRequest(new RequestHelper.RequestApi() {
                @Override
                public Result.ReturnData execute() {
                    return lineOperation.getFsInfoByLineId(lineId);
                }

                @Override
                public void onErrorResult(Result.ReturnData result) {
                    //TODO: 报警
                    log.warn("请求fsmanager获取呼叫线路失败，错误码是[{}][{}]", result.getCode(), result.getMsg());
                }
                @Override
                public boolean trueBreakOnCode(String code) {
                    return false;
                }
            }, -1, 1, 1, 60, true);
        }catch (Exception ex){
            log.warn("获取fsline出现异常", ex);
            //TODO: 报警，获取fsline异常
        }

        Preconditions.checkNotNull(returnData, "请求fsmanager获取呼叫线路失败，返回结果为空");
        return (FsLineInfoVO) returnData.getBody();
    }
}
