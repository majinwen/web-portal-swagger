package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * CancelFavoriteRequest
 */
@Data
@ApiModel(value = "CancelFavoriteRequest", description = "CancelFavoriteRequest")
public class CancelFavoriteRequest {

    @ApiModelProperty(value = "新房id", name = "buildingId", required = true)
    @NotNull(message = "缺少新房id")
    private Integer buildingId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "缺少用户id")
    private Integer userId;

    @ApiModelProperty(value = "小区id", name = "villageId", required = true)
    @NotNull(message = "缺少小区id")
    private Integer villageId;

}

