package com.toutiao.app.domain.mapSearch;

import lombok.Data;

/**
 * @ClassName EsfMapFindHouseBizcircleDo
 * @Author jiangweilong
 * @Date 2018/11/22 5:03 PM
 * @Description:
 **/

@Data
public class EsfMapSearchBizcircleDo {

    /**
     * 商圈名称
     */
    private String name;

    /**
     * 商圈ID
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
