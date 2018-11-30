package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DeleteEsfFavoriteRequest
 */
@Data
@ApiModel(value = "DeleteEsfFavoriteRequest", description = "DeleteEsfFavoriteRequest")
public class DeleteEsfFavoriteRequest {

    @ApiModelProperty(value = "二手房id", name = "houseId", required = true)
    @NotNull(message = "缺少二手房id")
    private String houseId;

//    @ApiModelProperty(value = "用户id", name = "userId", required = true)
//    @NotNull(message = "缺少用户id")
//    private Integer userId;

}

