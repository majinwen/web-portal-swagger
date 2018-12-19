package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "AgentSellHouseRequest", description = "AgentSellHouseRequest")
public class AgentSellHouseRequest {

    @ApiModelProperty(value = "房源id", name = "houseId")
    @NotNull(message = "缺少房源Id")
    private Integer houseId;

}
