package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * IsFavoriteRequest
 */
@Data
@ApiModel(value = "IsFavoriteRequest", description = "IsFavoriteRequest")
public class IsFavoriteRequest {

    @ApiModelProperty(value = "租房id", name = "houseId", required = true)
    @NotNull(message = "缺少租房id")
    private String houseId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "缺少用户id")
    private Integer userId;

}

