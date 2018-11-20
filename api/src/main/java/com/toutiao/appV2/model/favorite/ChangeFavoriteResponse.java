package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/19.
 */
@Data
public class ChangeFavoriteResponse {

    @ApiModelProperty(value = "收藏结果", name = "flag")
    private Boolean flag;

    @ApiModelProperty(value = "收藏信息", name = "msg")
    private String msg;

}
