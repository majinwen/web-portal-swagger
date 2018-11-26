package com.toutiao.app.domain.mapSearch;

import lombok.Data;

/**
 * @ClassName NewHouseMapFindHouseDistrictDo
 * @Author jiangweilong
 * @Date 2018/11/22 9:55 PM
 * @Description:
 **/


@Data
public class NewHouseMapSearchDistrictDo {


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
     * 楼盘数量
     */
    private Integer count;

}
