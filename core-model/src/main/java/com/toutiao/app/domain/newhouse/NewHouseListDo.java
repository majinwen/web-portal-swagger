package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class NewHouseListDo {


    /**
     * 最小面积
     */
    private Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

    /**
     * 楼盘名称
     */

    private String buildingName;


    /**
     * 大楼标题图
     */
    private  String buildingTitleImg;


    /**
     * 销售状态
     */
     private  String saleStatusName;

    /**
     *区域名称
     */
    private  String  districtName;

    /**
     * 楼盘id
     */
    private Integer buildingNameId;

    /**
     * 附近地铁信息
     */
    private  String roundStation;

    /**
     * 交房日期
     */
    private  String deliverTime;


    /**
     * 车位配比
     */
     private String parkRadio;

    /**
     * 最小价格
     */

    private  Double minPrice;

    /**
     *最大价格
     */
    private  Double maxPrice;

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
     *
     * 区域
     */
    private Integer districtId;


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
    private Integer  subwayLineId;


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

    /**
     * 均价
     */
    private  String averagePrice;


    /**
     *  户型数量
     */
    private  long roomTotalCount;


    /**
     * 新房收藏数量
     */
    private Integer newHouseFavorite;


    /**
     * 总价
     */
    private Double  totalPrice;


    /**
     * 标签
     */
    private  String []  buildingTags;


}
