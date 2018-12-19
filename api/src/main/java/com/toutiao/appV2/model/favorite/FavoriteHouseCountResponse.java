package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/30.
 */
@Data
public class FavoriteHouseCountResponse {

    @ApiModelProperty(value = "新房收藏", name = "newHouseIds")
    private Integer[] newHouseIds;

    @ApiModelProperty(value = "二手房收藏", name = "sellHouseIds")
    private String[] sellHouseIds;

    @ApiModelProperty(value = "小区收藏", name = "plotIds")
    private Integer[] plotIds;

    @ApiModelProperty(value = "租房收藏", name = "rentHouseIds")
    private String[] rentHouseIds;

}
