package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserCenterFavoriteCountResponse
 */
@Data
@ApiModel(value = "UserCenterFavoriteCountResponse", description = "UserCenterFavoriteCountResponse")
public class UserCenterFavoriteCountResponse {

    @ApiModelProperty(value = "二手房收藏数量", name = "esfHouseFavoriteCount")
    private Integer esfHouseFavoriteCount;

    @ApiModelProperty(value = "新房收藏数量", name = "newHouseFavoriteCount")
    private Integer newHouseFavoriteCount;

    @ApiModelProperty(value = "小区收藏数量", name = "plotFavoriteCount")
    private Integer plotFavoriteCount;

    @ApiModelProperty(value = "租房收藏数量", name = "rentHouseFavoriteCount")
    private Integer rentHouseFavoriteCount;

}

