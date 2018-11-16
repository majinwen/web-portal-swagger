package com.toutiao.appV2.model.favorite;

import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * NewHouseFavoriteListResponse
 */
@Data
@ApiModel(value = "NewHouseFavoriteListResponse", description = "NewHouseFavoriteListResponse")
public class NewHouseFavoriteListResponse {

    @ApiModelProperty(value = "返回结果", name = "data")
    private List<NewHouseFavoriteDo> data;

    @ApiModelProperty(value = "总页数", name = "totalNum")
    private Long totalNum;

}

