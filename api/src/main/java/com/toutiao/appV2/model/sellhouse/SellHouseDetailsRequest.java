package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "SellHouseDerailsRequest", description = "SellHouseDerailsRequest")
public class SellHouseDetailsRequest {

    @ApiModelProperty(value = "二手房id", name = "houseId")
    @NotNull(message = "二手房房源id不能为空")
    private String houseId;

    @ApiModelProperty(value = "经纪人id", name = "userId")
    private String userId;
}
