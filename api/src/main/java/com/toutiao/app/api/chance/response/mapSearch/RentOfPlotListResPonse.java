package com.toutiao.app.api.chance.response.mapSearch;

import lombok.Data;

import java.util.List;

@Data
public class RentOfPlotListResPonse {
    /**
     * 租房列表
     */
    private List<RentOfPlotResPonse> data;
    /**
     * 附近的地铁线
     */
    private String nearSubwayLine;
    /**
     * 总套数
     */
    private Integer totalNum;
}
