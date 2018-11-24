package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/11/23.
 */
@Data
public class PlotListRequest {


    @JsonProperty("areaId")
    @ApiParam("商圈id")
    private Integer areaId;

    @JsonProperty("beginArea")
    @ApiParam("起始面积")
    private double beginArea;

    @JsonProperty("beginPrice")
    @ApiParam("起始价格")
    private double beginPrice;

    @JsonProperty("cityId")
    @ApiParam("城市id")
    private Integer cityId;

    @JsonProperty("distance")
    @ApiParam("附近距离")
    private Integer distance;

    @JsonProperty("districtId")
    @ApiParam("区域")
    private Integer districtId;

    @JsonProperty("isTop")
    @ApiParam("是否热门")
    private Integer isTop;

    @JsonProperty("endArea")
    @ApiParam("结束面积")
    private double endArea;

    @JsonProperty("endPrice")
    @ApiParam("结束价格")
    private double endPrice;

    @JsonProperty("forwardId")
    @ApiParam("朝向")
    private List<Integer> forwardId;

    @JsonProperty("houseYearId")
    @ApiParam("楼龄[0-5]")
    private String houseYearId;

    @JsonProperty("keyword")
    @ApiParam("关键字")
    private String keyword;

    @JsonProperty("labelId")
    @ApiParam("标签")
    private List<Integer> labelId;

    @JsonProperty("lat")
    @ApiParam("维度")
    private double lat;

    @JsonProperty("layoutId")
    @ApiParam("居室")
    private List<Integer> layoutId;

    @JsonProperty("lon")
    @ApiParam("经度")
    private double lon;

    @JsonProperty("pageNum")
    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @JsonProperty("pageSize")
    @ApiParam(value = "页容量", defaultValue = "10")
    private Integer pageSize = 10;

    @JsonProperty("subwayLineId")
    @ApiParam("地铁线Id")
    private Integer subwayLineId;

    @JsonProperty("subwayStationId")
    @ApiParam("地铁站id")
    private Integer subwayStationId;

    @JsonProperty("sort")
    @ApiParam("排序")
    private String sort;
}
