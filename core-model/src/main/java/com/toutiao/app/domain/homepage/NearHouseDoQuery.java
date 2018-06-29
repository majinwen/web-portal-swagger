package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class NearHouseDoQuery {
    /**
     * y坐标
     */
    private Double lat;
    /**
     * x坐标
     */
    private Double lon;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 默认距离5km
     */
    private double distance = 5;
    /**
     * 默认条数
     */
    private Integer size = 5;

}
