package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDo;
import lombok.Data;

import java.util.List;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomResponse {
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
