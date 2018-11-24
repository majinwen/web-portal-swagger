package com.toutiao.app.api.chance.request.newhouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.api.chance.request.BaseQueryRequest;
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
}
