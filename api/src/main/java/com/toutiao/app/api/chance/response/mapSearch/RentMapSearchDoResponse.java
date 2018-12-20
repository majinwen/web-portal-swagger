package com.toutiao.app.api.chance.response.mapSearch;

import lombok.Data;

@Data
public class RentMapSearchDoResponse {

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域ID
     */
    private Integer id;

    /**
     * 坐标 纬度
     */
    private Double latitude;

    /**
     * 坐标 经度
     */
    private Double longitude;

    /**
     * 描述
     */
    private String desc;

    /**
     * 房源数量
     */
    private Integer count;

    /**
     * 价格
     */
    private Double price;
}
