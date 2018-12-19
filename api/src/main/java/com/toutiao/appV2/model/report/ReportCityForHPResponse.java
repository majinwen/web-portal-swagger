package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReportCityForHPResponse {

    /**
     * 二手房上月均价
     */
    @ApiModelProperty(value = "二手房上月均价")
    private String esfMonthPrice;

    /**
     * 二手房均价环比
     */
    @ApiModelProperty(value = "二手房均价环比")
    private String esfPriceHuanbi;

    /**
     * 新房上月均价
     */
    @ApiModelProperty(value = "新房上月均价")
    private String newMonthPrice;

    /**
     * 新房均价环比
     */
    @ApiModelProperty(value = "新房均价环比")
    private String newPriceHuanbi;

    /**
     * 当前日期
     */
    @ApiModelProperty(value = "当前日期")
    private String today;
}
