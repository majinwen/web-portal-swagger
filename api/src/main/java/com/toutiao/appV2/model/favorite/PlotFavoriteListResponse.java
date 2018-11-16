package com.toutiao.appV2.model.favorite;

import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * PlotFavoriteListResponse
 */
@Data
@ApiModel(value = "PlotFavoriteListResponse", description = "PlotFavoriteListResponse")
public class PlotFavoriteListResponse {

    @ApiModelProperty(value = "返回结果", name = "data")
    private List<UserFavoritePlotDo> data;

    @ApiModelProperty(value = "总页数", name = "totalNum")
    private Long totalNum;

}

