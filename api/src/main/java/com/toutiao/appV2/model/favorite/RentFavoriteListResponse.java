package com.toutiao.appV2.model.favorite;

import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * RentFavoriteListResponse
 */
@Data
@ApiModel(value = "RentFavoriteListResponse", description = "RentFavoriteListResponse")
public class RentFavoriteListResponse {

    @ApiModelProperty(value = "返回结果", name = "data")
    private List<RentFavoriteDo> data;

    @ApiModelProperty(value = "总页数", name = "totalNum")
    private Long totalNum;

}

