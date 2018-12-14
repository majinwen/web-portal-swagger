package com.toutiao.app.domain.newhouse;

import lombok.Data;

/**
 * @ClassName CustomConditionDetailsDo
 * @Author jiangweilong
 * @Date 2018/12/12 4:11 PM
 * @Description:
 **/

@Data
public class CustomConditionDetailsDo {

    /**
     * 商圈名称
     */
    private String bizcircleName;

    /**
     * 商圈id
     */
    private Integer bizcircleId;

    /**
     * 商圈均价
     */
    private String averagePrice;

    /**
     * 房源最小面积
     */
    private double houseMinArea;

    /**
     * 房源最大面积
     */
    private double houseMaxArea;

    /**
     * 房源面积描述
     */
    private String houseAreaDesc;

    /**
     * 楼盘数量
     */
    private Integer buildCount;

    /**
     * 房源数量
     */
    private Integer houseCount;

    /**
     * 坐标 纬度
     */
    private Double latitude;

    /**
     * 坐标 经度
     */
    private Double longitude;

}
