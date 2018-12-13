package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportPriceQuotationsResponse {

    /**
     * 主键ID
     */
//    @ApiModelProperty(value = "主键ID")
//    private Integer id;

    /**
     * 城市ID
     */
//    @ApiModelProperty(value = "城市ID")
//    private Integer cityId;

    /**
     * 0:新房 1:二手房
     */
//    @ApiModelProperty(value = "0:新房,1:二手房")
//    private Short nOrE;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private Integer year;

    /**
     * 月份
     */
    @ApiModelProperty(value = "月份")
    private Integer month;

    /**
     * 均价(单位：元/㎡)
     */
    @ApiModelProperty(value = "均价(单位：元/㎡)")
    private Integer price;

    /**
     * 记录时间
     */
//    @ApiModelProperty(value = "记录时间")
//    private Date createTime;

    /**
     * 更新时间
     */
//    @ApiModelProperty(value = "更新时间")
//    private Date updateTime;

    /**
     * 数据来源
     */
//    @ApiModelProperty(value = "数据来源")
//    private String source;
}