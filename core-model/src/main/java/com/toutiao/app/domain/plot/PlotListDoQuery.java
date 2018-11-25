package com.toutiao.app.domain.plot;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class PlotListDoQuery extends QueryDo {
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
    private Integer isTop;
    /**
     * 距离
     */
    private Double distance;
    /**
     * 排序
     */
    private String sort;
}
