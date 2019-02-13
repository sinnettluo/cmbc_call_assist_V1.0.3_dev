package com.guiji.billing.service;

import com.guiji.billing.dto.QueryTotalChargingItemDto;
import com.guiji.billing.sys.ResultPage;
import com.guiji.billing.vo.TotalChargingItemVo;

import java.util.List;

public interface BillingCompanyTotalService {

    //话费分析
    List<TotalChargingItemVo> totalCompanyChargingItem(QueryTotalChargingItemDto queryTotalChargingItemDto, ResultPage<TotalChargingItemVo> page);

    int totalCompanyChargingCount(QueryTotalChargingItemDto queryTotalChargingItemDto);

    //话费分析详情
    List<TotalChargingItemVo> totalChargingItemList(QueryTotalChargingItemDto queryTotalChargingItemDto, ResultPage<TotalChargingItemVo> page);

    int totalChargingItemCount(QueryTotalChargingItemDto queryTotalChargingItemDto);
}
