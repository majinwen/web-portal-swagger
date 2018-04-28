package com.toutiao.app.domain.plot;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class PlotListQuery extends QueryDo {
    /**
     * 维度 附近找房
     */
    private double lat;

    /**
     * 经度 附近找房
     */
    private double lon;
}
