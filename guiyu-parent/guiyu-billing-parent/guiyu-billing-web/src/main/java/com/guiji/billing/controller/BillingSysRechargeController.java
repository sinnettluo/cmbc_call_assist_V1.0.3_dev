package com.guiji.billing.controller;

import com.guiji.billing.dto.QueryRechargeDto;
import com.guiji.billing.service.BillingSysRechargeService;
import com.guiji.billing.sys.ResultPage;
import com.guiji.billing.vo.SysRechargeTotalVo;
import com.guiji.billing.vo.UserRechargeTotalVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员查询充值记录
 */
@RestController
@RequestMapping(value = "/billing/sysRecharge")
public class BillingSysRechargeController {

    @Autowired
    private BillingSysRechargeService billingSysRechargeService;

    //查询公司账户充值记录
    @ApiOperation(value="查询公司账户充值记录", notes="查询公司账户充值记录")
    @RequestMapping(value = "/queryCompanyRechargeTotal", method = {RequestMethod.POST})
    public ResultPage<SysRechargeTotalVo> queryCompanyRechargeTotal(@RequestBody QueryRechargeDto queryRechargeDto){
        ResultPage<SysRechargeTotalVo> page = new ResultPage<SysRechargeTotalVo>(queryRechargeDto);
        List<SysRechargeTotalVo> list = billingSysRechargeService.queryCompanyRechargeTotal(queryRechargeDto, page);
        page.setList(list);
        page.setTotalItemAndPageNumber(billingSysRechargeService.queryCompanyRechargeCount(queryRechargeDto));
        return page;
    }
}