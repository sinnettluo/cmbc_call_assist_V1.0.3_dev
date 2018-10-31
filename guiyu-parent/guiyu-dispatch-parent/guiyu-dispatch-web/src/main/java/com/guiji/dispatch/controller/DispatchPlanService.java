package com.guiji.dispatch.controller;

import com.guiji.common.result.Result;
import com.guiji.dispatch.api.IDispatchPlanService;
import com.guiji.dispatch.dao.DispatchPlanMapper;
import com.guiji.dispatch.dao.entity.DispatchPlanExample;
import com.guiji.dispatch.model.CommonResponse;
import com.guiji.dispatch.model.DispatchPlan;
import com.guiji.dispatch.model.Schedule;
import com.guiji.dispatch.model.ScheduleList;
import com.guiji.dispatch.util.Log;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

public class DispatchPlanService implements IDispatchPlanService
{
    @Autowired
    private DispatchPlanMapper dispatchPlanMapper;
    
    
    @Override
	public boolean addSchedule(DispatchPlan dispatchPlan) throws Exception {
    	com.guiji.dispatch.dao.entity.DispatchPlan localBean = new com.guiji.dispatch.dao.entity.DispatchPlan();
    	BeanUtils.copyProperties(dispatchPlan, localBean);
    	dispatchPlanMapper.insert(localBean);
    	return true;
	}

	@Override
	public List<DispatchPlan> querySchedules(String userId) throws Exception {
		DispatchPlanExample ex = new DispatchPlanExample();
		ex.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
		List<com.guiji.dispatch.dao.entity.DispatchPlan> selectByExample = dispatchPlanMapper.selectByExample(ex);
		 List<DispatchPlan> list = new ArrayList<>();
		BeanUtils.copyProperties(selectByExample, list);
		return list;
	}

	@Override
	public boolean pauseSchedule(String planUuid) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resumeSchedule(String planUuid) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopSchedule(String planUuid) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DispatchPlan> queryAvailableSchedules(Schedule schedule) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DispatchPlan> queryExecuteResult(String planUuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePlanBatch(ScheduleList scheduleList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

//    /**
//     * 向调度中心提交任务
//     *
//     * @param schedule 任务
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "提交任务", notes = "向调度中心提交任务")
//    @RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
////    CommonResponse addSchedule(@RequestBody final DispatchPlan dispatchPlan) throws Exception {
//    CommonResponse addSchedule() throws Exception {
//    	for(int i =0;i<10;i++){
//    		DispatchPlan dispatchPlan = new DispatchPlan();
//    		dispatchPlan.setUserId(i);
//    		dispatchPlan.setPhone(String.valueOf(i));
//    		jssPlanService.addSchedule(dispatchPlan);
//    	}
//    	return null;
//       
//    }
//
//    /**
//     * 查询任务列表
//     *
//     * @param userId 用户id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "查询任务列表", notes = "查询任务列表")
//    @RequestMapping(value = "/querySchedules", method = RequestMethod.GET)
//    CommonResponse querySchedules(@RequestParam final String userId) throws Exception {
//        return jssPlanService.querySchedules(userId);
//    }
//
//    /**
//     * 暂停任务
//     *
//     * @param planUuid 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "暂停任务", notes = "暂停任务")
//    @RequestMapping(value = "/pauseSchedule", method = RequestMethod.POST)
//    CommonResponse pauseSchedule(@RequestBody final String planUuid) throws Exception {
//        return jssPlanService.pauseSchedule(planUuid);
//    }
//
//    /**
//     * 恢复任务
//     *
//     * @param planUuid 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "恢复任务", notes = "恢复任务")
//    @RequestMapping(value = "/resumeSchedule", method = RequestMethod.POST)
//    CommonResponse resumeSchedule(@RequestBody final String planUuid) throws Exception {
//        return jssPlanService.resumeSchedule(planUuid);
//    }
//
//    /**
//     * 停止任务
//     *
//     * @param planUuid 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "停止任务", notes = "停止任务")
//    @RequestMapping(value = "/stopSchedule", method = RequestMethod.POST)
//    CommonResponse stopSchedule(@RequestBody final String planUuid) throws Exception {
//        return jssPlanService.stopSchedule(planUuid);
//    }
//
//    /**
//     * 返回可以拨打的任务给呼叫中心
//     *
//     * @param schedule 用户id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "返回可以拨打的任务给呼叫中心", notes = "返回可以拨打的任务给呼叫中心")
//    @RequestMapping(value = "/queryAvailableSchedules", method = RequestMethod.POST)
//    CommonResponse queryAvailableSchedule(@RequestBody final Schedule schedule) throws Exception {
//        return jssPlanService.queryAvailableSchedules(schedule);
//    }
//
//    /**
//     * 查询任务提交处理结果
//     *
//     * @param planUuid 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "查询任务提交处理结果", notes = "查询任务提交处理结果")
//    @RequestMapping(value = "/queryScheduleResult", method = RequestMethod.GET)
//    CommonResponse queryScheduleResult(@RequestParam final String planUuid) throws Exception {
//        return jssPlanService.querySchedules(planUuid);
//    }
//
//    /**
//     * 查询任务提交处理结果
//     *
//     * @param planUuid 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "获取任务执行情况", notes = "获取任务执行情况")
//    @RequestMapping(value = "/queryExecuteResult", method = RequestMethod.GET)
//    CommonResponse queryExecuteResult(@RequestParam final String planUuid) throws Exception {
//        return jssPlanService.queryExecuteResult(planUuid);
//    }
//
//    /**
//     * 查询任务提交处理结果
//     *
//     * @param scheduleList 任务id
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "更新任务状态", notes = "更新任务状态")
//    @RequestMapping(value = "/updatePlanBatch", method = RequestMethod.POST)
//    CommonResponse updatePlanBatch(@RequestBody final ScheduleList scheduleList) throws Exception {
//        return jssPlanService.updatePlanBatch(scheduleList);
//    }
//
//    /**
//     * 查看app运行情况
//     *
//     * @return 响应报文
//     * @throws Exception 异常
//     */
//    @ApiOperation(value = "查看app运行情况", notes = "查看app运行情况")
//    @RequestMapping(value = "/appver", method = RequestMethod.GET)
//    CommonResponse appver() throws Exception {
//        return new CommonResponse("00000000", "success");
//    }

	
}

