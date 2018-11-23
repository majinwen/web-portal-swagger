package com.toutiao.appV2.model.newhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
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


}
