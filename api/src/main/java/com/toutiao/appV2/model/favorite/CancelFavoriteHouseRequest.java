package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by wk on 2018/11/30.
 */
@Data
public class CancelFavoriteHouseRequest {

    @ApiModelProperty(value = "id", name = "id")
    @NotNull(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "类型(1 新房 2 二手房 3 小区 4 租房)", name = "type")
    @NotNull(message = "type不能为空")
    private Integer type;

}
