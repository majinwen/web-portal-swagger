package com.toutiao.app.domain.mapSearch;

import lombok.Data;

/**
 * @ClassName EsfMapFindHouseDistrictDo
 * @Author jiangweilong
 * @Date 2018/11/22 4:34 PM
 * @Description:
 **/

@Data
public class EsfMapSearchDistrictDo {

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


}
