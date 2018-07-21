package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomDoQuery extends QueryDo {
    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 详情页房源编号
     */
    private String houseId;
}
