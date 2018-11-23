package com.toutiao.app.domain.mapSearch;

import lombok.Data;

/**
 * @ClassName EsfMapFindHouseCommunityDo
 * @Author jiangweilong
 * @Date 2018/11/22 9:32 PM
 * @Description:
 **/

@Data
public class EsfMapSearchCommunityDo {

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
