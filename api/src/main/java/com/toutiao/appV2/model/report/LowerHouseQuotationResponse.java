package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LowerHouseQuotationResponse implements Comparable<LowerHouseQuotationResponse> {

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
//    @ApiModelProperty(value = "抢手房数量")
//    private Integer robHouseCount;

    /**
     * 捡漏房均价
     */
    @ApiModelProperty(value = "捡漏房均价")
    private Integer price;

    /**
     * 降价房均价
     */
//    @ApiModelProperty(value = "降价房均价")
//    private Integer cutHouseAvgPrice;

    /**
     * 创建时间
     */
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;

    /**
     * 更新时间
     */
//    @ApiModelProperty(value = "更新时间")
//    private Date updateTime;

    /**
     * 天
     */
    @ApiModelProperty(value = "天")
    private Integer day;

    /**
     * 每日行情描述
     */
//    @ApiModelProperty(value = "每日行情描述")
//    private String desc;

    /**
     * 每日行情左侧标题
     */
//    @ApiModelProperty(value = "每日行情左侧标题")
//    private String title;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @Override
    public int compareTo(LowerHouseQuotationResponse o) {
        return o.getSort()-this.getSort();
    }
}