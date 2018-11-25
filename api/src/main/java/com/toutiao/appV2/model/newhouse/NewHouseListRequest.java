package com.toutiao.appV2.model.newhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;


@Data
public class NewHouseListRequest extends BaseQueryRequest {

    @ApiParam(value = "销售状态")
    private  Integer[] saleStatusId;

    @ApiParam(value = "环线")
    private String ringRoad;

    @ApiParam(value = "楼盘特色")
    private String buildingFeature;

    @JsonProperty("sort")
    @ApiParam("排序")
    private String sort;

    @ApiModelProperty(value = "起始总价格", name = "beginTotalPrice")
    private double beginTotalPrice;

    @ApiModelProperty(value = "结束总价格", name = "endTotalPrice")
    private double endTotalPrice;
}
