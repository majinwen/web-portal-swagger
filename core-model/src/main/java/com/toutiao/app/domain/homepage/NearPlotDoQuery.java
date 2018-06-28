package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class NearPlotDoQuery {
    /**
     * y坐标
     */
    private Double lat;
    /**
     * x坐标
     */
    private Double lon;
    /**
     * 默认距离5km
     */
    private double distance = 5;
    /**
     * 默认条数
     */
    private Integer size = 5;

}
