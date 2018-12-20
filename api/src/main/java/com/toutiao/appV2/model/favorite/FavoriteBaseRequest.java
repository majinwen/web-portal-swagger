package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by wk on 2018/11/29.
 */
@Data
public class FavoriteBaseRequest {

    @ApiModelProperty(value = "区域", name = "districtName", required = true)
    @NotNull(message = "区域不能为空")
    private String districtName;

    @ApiModelProperty(value = "商圈", name = "areaName", required = true)
//    @NotNull(message = "商圈不能为空")
    private String areaName;

    @ApiModelProperty(value = "标签", name = "tags", required = true)
    @NotNull(message = "标签不能为空")
    private String[] tags;

    @ApiModelProperty(value = "城市id", name = "cityId", required = true)
    private Integer cityId;

}
