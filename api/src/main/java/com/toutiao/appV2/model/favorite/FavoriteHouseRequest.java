package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/28.
 */
@Data
public class FavoriteHouseRequest {
    @ApiModelProperty(value = "当前页", name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", name = "size")
    private Integer size = 10;
}


