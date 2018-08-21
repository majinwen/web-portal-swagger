package com.toutiao.app.domain.subscribe;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-21
 * Time:   17:19
 * Theme:
 */
@Data
public class UserConditionSubscribeDetailDo {

    /**
     * 区域Id
     */
    private String districtId;
    /**
     * 区域
     */
    private String districtName;
    /**
     * 商圈id
     */
    private String areaId;
    /**
     * 商圈
     */
    private String areaName;
    /**
     * 地铁线id
     */
    private String subwayLineId;
    /**
     * 地铁线
     */
    private String subwayLineName;
    /**
     * 地铁站id
     */
    private String subwayStationId;
    /**
     * 地铁站
     */
    private String subwayStationName;
    /**
     * 开始价格
     */
    private Integer beginPrice;
    /**
     * 结束价格
     */
    private Integer endPrice;
    /**
     * 户型
     */
    private String layoutId;
    /**
     * 最小建筑面积
     */
    private Double beginArea;
    /**
     * 最大建筑面积
     */
    private Double endArea;
    /**
     * 朝向
     */
    private String forwardId;

    /**
     * 楼龄[0-5]
     */
    private String houseYearId;

    /**
     * 标签
     */
    private String labelId;



}
