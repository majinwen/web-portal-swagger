package com.toutiao.app.api.chance.request.sellhouse;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearBySellHousesRequest {

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

    private  Double beginPrice;

    /**
     *最大价格
     */
    private  Double endPrice;

    /**
     * 最小面积
     */
    private  Double beginArea;

    /**
     * 最大面积
     */
    private  Double endArea;

    /**
     *
     * 居室
     */
    private  Integer[] layout;


    /**
     * 标签
     */
    private  Integer [] houseLabelId;


    /**
     * 朝向
     */
    private  Integer []  forward;

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


    /**
     * 楼龄[0-5]
     */
    private String houseYearId;

    /**
     * y坐标
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * x坐标
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;
    /**
     * 附近距离
     */
    @NotNull(message = "缺少附近距离")
    private Integer distance;

}
