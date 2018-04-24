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
    private Integer districtId;

    /**
     * 最小价格
     */

    private  Double minPrice;

    /**
     *最大价格
     */
    private  Double maxPrice;

    /**
     * 最小面积
     */
    private  Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

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
