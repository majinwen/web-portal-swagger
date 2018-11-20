package com.toutiao.appV2.model.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchConditionResponse {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

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

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
}
