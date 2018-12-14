package com.toutiao.app.domain.rent;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class RentCustomConditionDo {

    /**
     * 区域ID
     */
    private Integer id;

    /**
     * 区域名称
     */
    private String name;

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
     * 小区数量
     */
    private Integer communityCount;

    /**
     * 房源数量
     */
    private Integer houseCount;
}
