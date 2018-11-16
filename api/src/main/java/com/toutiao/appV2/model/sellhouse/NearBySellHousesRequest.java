package com.toutiao.appV2.model.sellhouse;


import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "NearBySellHousesRequest", description = "NearBySellHousesRequest")
public class NearBySellHousesRequest extends BaseQueryRequest {

    @ApiModelProperty(value = "y坐标", name = "lat")
    @NotNull(message = "缺少坐标y")
    private Double lat;

    @ApiModelProperty(value = "x坐标", name = "lon")
    @NotNull(message = "缺少坐标x")
    private Double lon;

    @ApiModelProperty(value = "附近距离", name = "distance")
    @NotNull(message = "缺少附近距离")
    private Integer distance;

}
