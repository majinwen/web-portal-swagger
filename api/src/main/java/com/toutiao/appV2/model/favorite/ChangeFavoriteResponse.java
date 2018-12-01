package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/19.
 */
@Data
public class ChangeFavoriteResponse {

    @ApiModelProperty(value = "房源id", name = "id")
    private String id;

    @ApiModelProperty(value = "msg", name = "msg")
    private String msg;

}
