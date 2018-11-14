package com.guiji.callcenter.fsmanager.controller;

import com.guiji.callcenter.fsmanager.config.Constant;
import com.guiji.callcenter.fsmanager.service.TempService;
import com.guiji.component.result.Result;
import com.guiji.fsmanager.api.ITemp;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController implements ITemp {
    private final Logger logger = LoggerFactory.getLogger(TempController.class);
    @Autowired
    TempService tempService;
    @Override
    public Result.ReturnData downloadtempwav(String tempId) {
        logger.debug("收到模板是否存在接口请求，tempId[{}]", tempId);
        if(StringUtils.isBlank(tempId)){
            logger.info("模板是否存在接口请求失败，参数错误，为null或空");
            return Result.error(Constant.ERROR_CODE_PARAM);
        }
        return Result.ok(tempService.downloadtempwav(tempId));
    }

    @Override
    public Result.ReturnData<Boolean> istempexist(String tempId) {
        logger.debug("收到下载模板录音请求，tempId[{}]", tempId);
        if(StringUtils.isBlank(tempId)){
            logger.info("下载模板录音失败，参数错误，为null或空");
            return Result.error(Constant.ERROR_CODE_PARAM);
        }
        return Result.ok(tempService.istempexist(tempId));
    }
}
