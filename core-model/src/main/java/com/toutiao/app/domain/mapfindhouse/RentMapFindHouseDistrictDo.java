package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

/**
 * @ClassName RentMapFindHouseDistrictDo
 * @Author jiangweilong
 * @Date 2018/11/23 12:45 PM
 * @Description:
 **/

@Data
public class RentMapFindHouseDistrictDo {


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
}
