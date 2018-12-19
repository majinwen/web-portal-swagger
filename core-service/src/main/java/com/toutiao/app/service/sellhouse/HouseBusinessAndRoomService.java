package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDomain;

/**
 * 商圈+户型房源专题页服务层
 */
public interface HouseBusinessAndRoomService {
    /**
     * 获取商圈+户型
     */
    HouseBusinessAndRoomDomain getHouseBusinessAndRoomHouses(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery, String city);
}
