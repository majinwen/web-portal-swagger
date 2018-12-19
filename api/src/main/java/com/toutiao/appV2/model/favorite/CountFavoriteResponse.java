package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/19.
 */
@Data
public class CountFavoriteResponse {

    @ApiModelProperty(value = "收藏数量", name = "count")
    private Integer count;

}
