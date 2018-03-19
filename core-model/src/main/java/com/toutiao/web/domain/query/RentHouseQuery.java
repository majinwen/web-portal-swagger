package com.toutiao.web.domain.query;


import lombok.Data;

@Data
public class RentHouseQuery {

    /**
     * 出租房源Id(主键)
     */
    private String houseId;
    /**
     * 房源上级Id
     */
    private String zuFangId;
    /**
     * 房源上级名称
     */
    private String zuFangName;
    /**
     * 关键字
     */
    private String keyword;
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
     * 地铁站
     */
    private String nearbysubway;

    /**
     * 起始价格（总）
     */
    private Double beginPrice;
    /**
     * 结束价格（总）
     */
    private Double endPrice;

    /**
     * 租赁方式
     */
    private Integer ert;//整租
    private Integer jrt;//合租
    /**
     * 户型
     */
    private String elo;//整租户型
    private String jlo;//合租户型
    /**
     * 来源
     */
    private String source;

    /**
     * 面积
     */
    private String houseAreaSize;

    /**
     * 朝向
     */
    private String forward;

    /**
     * 维度
     */
    private double lat;
    /**
     * 经度
     */
    private double lon;

    /**
     * 电梯
     */
    private String elevator;

    /**
     * 供暖方式
     */
    private String ht;
    /**
     * 标签（特色）
     */
    private String tags;

    /**
     * 附近1,3,5km
     *
     */
    private String near;

    /**
     * 小区公寓标志
     */
    private Integer rentSign = -1;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;

    /**
     * 分页起始位置
     */
    private Integer from;

}
