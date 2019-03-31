package com.guiji.dispatch.controller;

import com.guiji.component.jurisdiction.Jurisdiction;
import com.guiji.dispatch.dto.OptPlanDto;
import com.guiji.dispatch.service.IPlanBatchService;
import com.guiji.dispatch.util.Log;
import com.guiji.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dispatch/batch/controller")
public class DispatchBatchController {

    private Logger logger = LoggerFactory.getLogger(DispatchBatchController.class);

    @Autowired
    private IPlanBatchService planBatchService;

 //   @Jurisdiction("taskCenter_phonelist_batchDelete,taskCenter_phonelist_delete")
    @ApiOperation(value="删除计划任务", notes="删除计划任务")
    @Log(info ="删除计划任务")
    @RequestMapping(value = "/delPlanBatch", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public boolean delPlanBatch(@RequestHeader String userId, @RequestHeader String orgCode,
                                @RequestHeader Integer orgId, @RequestHeader Integer authLevel,
                                @RequestBody OptPlanDto optPlanDto){
        logger.info("/dispatch/batch/controller/delPlanBatch入参:{}", JsonUtils.bean2Json(optPlanDto));
        if(null == optPlanDto){
            optPlanDto = new OptPlanDto();
        }
        optPlanDto.setOperUserId(userId);
        optPlanDto.setOperOrgCode(orgCode);
        optPlanDto.setOperOrgId(orgId);
        optPlanDto.setAuthLevel(authLevel);
        boolean bool = planBatchService.delPlanBatch(optPlanDto);
        return bool;
    }


    @ApiOperation(value="暂停计划任务", notes="暂停计划任务")
    @Log(info ="暂停计划任务")
    @RequestMapping(value = "/suspendPlanBatch", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public boolean suspendPlanBatch(@RequestHeader String userId, @RequestHeader String orgCode,
                                @RequestHeader Integer orgId, @RequestHeader Integer authLevel,
                                @RequestBody OptPlanDto optPlanDto){
        if(null == optPlanDto){
            optPlanDto = new OptPlanDto();
        }
        optPlanDto.setOperUserId(userId);
        optPlanDto.setOperOrgCode(orgCode);
        optPlanDto.setOperOrgId(orgId);
        optPlanDto.setAuthLevel(authLevel);
        logger.info("/dispatch/batch/controller/suspendPlanBatch入参:{}", JsonUtils.bean2Json(optPlanDto));
        boolean bool = planBatchService.suspendPlanBatch(optPlanDto);
        return bool;
    }

    @ApiOperation(value="停止计划任务", notes="停止计划任务")
    @Log(info ="停止计划任务")
    @RequestMapping(value = "/stopPlanBatch", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public boolean stopPlanBatch(@RequestHeader String userId, @RequestHeader String orgCode,
                             @RequestHeader Integer orgId, @RequestHeader Integer authLevel,
                             @RequestBody OptPlanDto optPlanDto){
        if(null == optPlanDto){
            optPlanDto = new OptPlanDto();
        }
        optPlanDto.setOperUserId(userId);
        optPlanDto.setOperOrgCode(orgCode);
        optPlanDto.setOperOrgId(orgId);
        optPlanDto.setAuthLevel(authLevel);
        logger.info("/dispatch/batch/controller/stopPlanBatch:{}", JsonUtils.bean2Json(optPlanDto));
        boolean bool = planBatchService.stopPlanBatch(optPlanDto);
        return bool;
    }
}
