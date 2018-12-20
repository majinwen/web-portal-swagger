package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class UserFavoriteRentListRequest {

    @JsonProperty("beginPrice")
    @ApiParam("起始价格")
    private double beginPrice;

    @JsonProperty("districtId")
    @ApiParam("区域")
    private Integer[] districtId = null;

    @JsonProperty("elo")
    @ApiParam("整租户型")
    private String elo = null;

    @JsonProperty("endPrice")
    @ApiParam("结束价格")
    private double endPrice;

    @JsonProperty("jlo")
    @ApiParam("合租户型")
    private String jlo = null;

    @JsonProperty("layoutId")
    @ApiParam("居室")
    private Integer[] layoutId = null;

    @JsonProperty("pageNum")
    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @JsonProperty("pageSize")
    @ApiParam(value = "页容量", defaultValue = "10")
    private Integer pageSize = 10;

    @JsonProperty("rentType")
    @ApiParam("整租:1/合租:2/未知:3")
    private String rentType = null;

    @JsonProperty("subwayLineId")
    @ApiParam("地铁线Id")
    private Integer[] subwayLineId = null;

    @JsonProperty("sort")
    @ApiParam("排序")
    private String sort;
}
