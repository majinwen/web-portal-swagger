package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class NewHouseListDo {


    /**
     * 最小面积
     */
    private Double house_min_area;

    /**
     * 最大面积
     */
    private  Double hosue_max_area;

    /**
     * 楼盘名称
     */

    private String building_name;

    /**
     * 户型数量
     */
    private Integer layoutCount;


    /**
     * 大楼标题图
     */
    private  String building_title_img;


    /**
     * 周边配套数
     */
     private  Integer matchingCount=100;

    /**
     * 销售状态
     */
     private  String sale_status_name;

    /**
     *区域名称
     */
    private  String  district_name;

    /**
     * 楼盘id
     */
    private Integer building_name_id;

    /**
     * 附近地铁信息
     */
    private  String roundstation;

    /**
     * 交房日期
     */
    private  String deliver_time;


    /**
     * 车位配比
     */
     private String park_radio;

    /**
     * 收藏数量
     */
     private  Integer collectionCount;

    /**
     * 最小价格
     */

    private  Double min_price;

    /**
     *最大价格
     */
    private  Double max_price;

    /**
     * 页码
     */
    private Integer pageNum = 1;


    /**
     *
     * 居室
     */
    private  Integer[] layout;


    /**
     * 标签
     */
    private  Integer [] labelId;


    /**
     * 附近5km
     */
    private  Boolean isNearby;


    /**
     *
     * 区域
     */
    private Integer district_id;


    /**
     * 商圈id
     */
    private Integer areaId;


    /**
     * /**
     * 关键字
     */
    private String keyword;

    /**
     *
     * 城市id
     */
    private Integer cityId;


    /**
     * 地铁线Id
     */
    private Integer subwayLineId;


    /**
     * 地铁站Id
     */
    private Integer subwayStationId;


    /**
     * 每页数量
     */
    private Integer pageSize=10;

    /**
     * y坐标
     */
    private  Double  lat;
    /**
     * X坐标
     */
    private  Double lon;

    /**
     * 距离
     */
    private  Integer distance;

}
