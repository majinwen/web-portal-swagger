package com.toutiao.app.api.chance.request.newhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
    private  Integer[] layout;


    /**
     * 标签
     */
    private  Integer [] labelId;

    /**
     * 页码
     */
    @NotNull(message = "缺少页码")
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
    @NotNull(message ="缺少城市id")
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
