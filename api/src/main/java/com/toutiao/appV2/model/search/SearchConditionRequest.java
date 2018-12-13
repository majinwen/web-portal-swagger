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
     * 城市代码
     */
    @ApiModelProperty(value = "城市代码")
    private String cityDomain;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型(0首页 1新房 2二手房 3租房 4小区)")
    private Integer type;

}
