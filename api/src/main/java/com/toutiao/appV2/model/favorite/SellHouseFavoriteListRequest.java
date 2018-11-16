package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * SellHouseFavoriteListRequest
 */
@Data
@ApiModel(value = "SellHouseFavoriteListRequest", description = "SellHouseFavoriteListRequest")
public class SellHouseFavoriteListRequest {

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "当前页", name = "userId")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", name = "userId")
    private Integer size = 10;

}

