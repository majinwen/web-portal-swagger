package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

/**
 * @ClassName RentMapFindHouseCommunityDo
 * @Author jiangweilong
 * @Date 2018/11/23 1:22 PM
 * @Description:
 **/

@Data
public class RentMapFindHouseCommunityDo {

    /**
     * 社区名称
     */
    private String name;

    /**
     * 社区ID
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
