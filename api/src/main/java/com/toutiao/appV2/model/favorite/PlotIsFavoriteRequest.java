package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * PlotIsFavoriteRequest
 */
@Data
@ApiModel(value = "PlotIsFavoriteRequest", description = "PlotIsFavoriteRequest")
public class PlotIsFavoriteRequest {

    @ApiModelProperty(value = "小区id", name = "buildingId", required = true)
    @NotNull(message = "小区id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

}

