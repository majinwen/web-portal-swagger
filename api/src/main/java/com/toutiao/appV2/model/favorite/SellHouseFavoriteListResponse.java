package com.toutiao.appV2.model.favorite;

import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * SellHouseFavoriteListResponse
 */
@Data
@ApiModel(value = "SellHouseFavoriteListResponse", description = "SellHouseFavoriteListResponse")
public class SellHouseFavoriteListResponse {

    @ApiModelProperty(value = "返回结果", name = "data")
    private List<SellHouseFavoriteDo> data;

    @ApiModelProperty(value = "总页数", name = "totalNum")
    private Long totalNum;

}

