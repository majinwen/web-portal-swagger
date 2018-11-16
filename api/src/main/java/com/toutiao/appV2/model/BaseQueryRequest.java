package com.toutiao.appV2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BaseQueryRequest", description = "BaseQueryRequest")
public class BaseQueryRequest {

    @ApiModelProperty(value = "起始价格", name = "beginPrice")
    private double beginPrice;

    @ApiModelProperty(value = "结束价格", name = "endPrice")
    private double endPrice;

    @ApiModelProperty(value = "起始面积", name = "beginArea")
    private double beginArea;

    @ApiModelProperty(value = "结束面积", name = "endArea")
    private double endArea;

    @ApiModelProperty(value = "居室", name = "layoutId")
    private Integer[] layoutId;

    @ApiModelProperty(value = "朝向", name = "forwardId")
    private Integer[] forwardId;

    @ApiModelProperty(value = "楼龄[0-5]", name = "houseYearId")
    private String houseYearId;

    @ApiModelProperty(value = "标签", name = "labelId")
    private Integer[] labelId;

    @ApiModelProperty(value = "区域", name = "districtId")
    private Integer districtId;

    @ApiModelProperty(value = "商圈id", name = "areaId")
    private Integer areaId;

    @ApiModelProperty(value = "地铁线Id", name = "subwayLineId")
    private Integer subwayLineId;

    @ApiModelProperty(value = "地铁站id", name = "subwayStationId")
    private Integer subwayStationId;

    @ApiModelProperty(value = "关键字", name = "keyword")
    private String keyword;

    @ApiModelProperty(value = "页码", name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", name = "pageSize")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "城市id", name = "cityId")
    private Integer cityId;


}
