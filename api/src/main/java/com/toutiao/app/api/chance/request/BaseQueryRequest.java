package com.toutiao.app.api.chance.request;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BaseQueryRequest {

    @ApiParam(value = "起始价格")
    private  double beginPrice;

    @ApiParam(value = "结束价格")
    private double endPrice;

    @ApiParam(value = "起始面积")
    private  double beginArea;

    @ApiParam(value = "结束面积")
    private  double endArea;

    @ApiParam(value = "居室")
    private  Integer [] layoutId;

    @ApiParam(value = "朝向")
    private  Integer[] forwardId;

    @ApiParam(value = "楼龄[0-5]")
    private String houseYearId;

    @ApiParam(value = "标签")
    private  Integer [] labelId;

    @ApiParam(value = "区域")
    private Integer districtId;

    @ApiParam(value = "商圈id")
    private Integer areaId;

    @ApiParam(value = "地铁线Id")
    private Integer subwayLineId;

    @ApiParam(value = "地铁站id")
    private Integer subwayStationId;

    @ApiParam(value = "关键字")
    private String keyword;

    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @ApiParam(value = "每页数量", defaultValue = "10")
    private Integer pageSize=10;

    @ApiParam(value = "城市id")
    private  Integer cityId;

}
