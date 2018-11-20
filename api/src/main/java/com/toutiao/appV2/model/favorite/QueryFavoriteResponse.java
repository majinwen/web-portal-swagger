package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/19.
 */
@Data
public class QueryFavoriteResponse {

    @ApiModelProperty(value = "是否收藏", name = "flag")
    private Boolean flag;

}
