package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportTopicHouseTrend {

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
     * 抢手房数量
     */
    @ApiModelProperty(value = "抢手房数量")
    private Integer robHouseCount;

    /**
     * 捡漏房均价
     */
    @ApiModelProperty(value = "捡漏房均价")
    private Integer lowerHouseAvgPrice;

    /**
     * 降价房均价
     */
    @ApiModelProperty(value = "降价房均价")
    private Integer cutHouseAvgPrice;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 天
     */
    @ApiModelProperty(value = "天")
    private Integer day;

    /**
     * 每日行情描述
     */
    @ApiModelProperty(value = "每日行情描述")
    private Integer desc;
}