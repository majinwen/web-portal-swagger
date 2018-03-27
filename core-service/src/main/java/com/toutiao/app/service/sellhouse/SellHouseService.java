package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;

import java.util.List;



public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellHouseDetailsDo getSellHouseByHouseId(Integer houseId);
    /**
     * 根据小区id查询二手房
     */
    List<SellHouseDetailsDo> queryEsfByPlotId(Integer plotId);
}
