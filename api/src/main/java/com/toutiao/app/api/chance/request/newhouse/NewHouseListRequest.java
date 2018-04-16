package com.toutiao.app.api.chance.request.newhouse;

import lombok.Data;

@Data
public class NewHouseListRequest {

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 区域
     */
    private Integer district_id;

    /**
     * 最小价格
     */

    private  Double min_price;

    /**
     *最大价格
     */
    private  Double max_price;

    /**
     * 最小面积
     */
    private  Double house_min_area;

    /**
     * 最大面积
     */
    private  Double house_max_area;

    /**
     *
     * 居室
     */
    private  String[] layout;

    /**
     * 销售状态
     */
    private  Integer sale_status_id;

    /**
     * 标签
     */
    private  Integer [] labelId;

    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 每页数量
     */
    private Integer pageSize=10;

    /**
     * 商圈id
     */
    private Integer areaId;

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




}
