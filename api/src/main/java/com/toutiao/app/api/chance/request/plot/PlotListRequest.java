package com.toutiao.app.api.chance.request.plot;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class PlotListRequest extends BaseQueryRequest {
    /**
     * 维度 附近找房
     */
    private double lat;

    /**
     * 经度 附近找房
     */
    private double lon;
    /**
     * 是否top50小区
     */
    private  Integer  isTop;
    /**
     * 距离
     */
    private Double distance = 1.6;
}
