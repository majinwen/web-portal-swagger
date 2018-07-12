package com.toutiao.app.domain.sellhouse;

import lombok.Data;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomDoQuery {
    /**
     * 商圈
     */
    private String houseBusinessName;

    /**
     * 商圈Id
     */
    private Integer houseBusinessId;

    /**
     * 户型
     */
    private Integer room;

    /**
     * 详情页房源编号
     */
    private Integer houseId;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
