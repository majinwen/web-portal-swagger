package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * NewHouseFavoriteListResponse
 */
@Data
@ApiModel(value = "NewHouseFavoriteListResponse", description = "NewHouseFavoriteListResponse")
public class NewHouseIsFavoriteRequest {

    @ApiModelProperty(value = "新房id", name = "buildingId", required = true)
    @NotNull(message = "新房id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
