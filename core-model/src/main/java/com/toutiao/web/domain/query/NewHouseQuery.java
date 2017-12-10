package com.toutiao.web.domain.query;


import lombok.Data;

@Data
public class NewHouseQuery {

    /**
     * 关键字
     */
    private String keywords;
    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 商圈id
     */
    private Integer areaId;

    /**
     * 地铁线Id
     */
    private Integer subwayLineId;

    /**
     * 地铁站Id
     */
    private Integer subwayStationId;

    /**
     * 起始价格（总）
     */
    private Double beginPrice;
    /**
     * 结束价格（总）
     */
    private Double endPrice;

    /**
     * 户型
     */
    private String layoutId;

    /**
     * 物业类型
     */
    private String propertyTypeId;

    /**
     * 是否有电梯
     */
    private Integer elevatorFlag;

    /**
     * 建筑类型
     */
    private String buildingType;

    /**
     * 销售状态
     */
    private String saleType;

    /**
     * 楼盘特色
     */
    private String buildingFeature;

    /**
     * 装修标准
     */
    private String deliverStyle;

    /**
     * 面积
     */
    private String houseAreaSize;
    /**
     * 产权年限
     */
    private String propertyRightLife;
    /**
     * 排序  0--默认（按楼盘级别（广告优先））--1均价升排序--2均价降排序--3开盘时间升排序--4开盘时间降排序
     */
    private Integer sort;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

}
