package com.toutiao.appV2.model.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchConditionRequest {

    /**
     * 城市代码
     */
    @ApiModelProperty(value = "城市代码")
    private Integer cityId;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;

}
