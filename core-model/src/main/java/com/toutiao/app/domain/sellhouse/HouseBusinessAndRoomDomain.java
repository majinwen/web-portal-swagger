package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class HouseBusinessAndRoomDomain {
    /**
     * 商圈+户型专题数据
     */
    private List<HouseBusinessAndRoomDo> data;

    /**
     * 商圈+户型均价
     */
    private Double averagePrice;

    /**
     * 房源总数
     */
    private long totalCount;
}
