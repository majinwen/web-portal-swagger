package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "房价行情/房价走势")
public class ReportPriceQuotations {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 城市ID
     */
    @ApiModelProperty(value = "城市ID")
    private Integer cityId;

    /**
     * 0-新房，1-小区
     */
    @ApiModelProperty(value = "0-新房，1-小区")
    private Short nOrE;

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
    private Integer avgPrice;

    /**
     * 记录时间
     */
    @ApiModelProperty(value = "记录时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String source;
}