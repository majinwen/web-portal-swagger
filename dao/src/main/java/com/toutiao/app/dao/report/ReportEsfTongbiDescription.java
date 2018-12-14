package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportEsfTongbiDescription {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 二手房在线数量
     */
    @ApiModelProperty(value = "二手房在线数量")
    private Integer esfCount;

    /**
     * 价格区间
     */
    @ApiModelProperty(value = "价格区间")
    private String esfRange;

    /**
     * 同比
     */
    @ApiModelProperty(value = "同比")
    private Double percent;
}