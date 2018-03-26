package com.toutiao.app.service.sellhouse;


import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;


public interface SellHouseService {

    /**
     * 二手房房源详情
     * @param houseId
     * @return
     */
    SellHouseDetailsDo getSellHouseByHouseId(Integer houseId);
}
