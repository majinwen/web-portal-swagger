package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportNewPreferential {
    /**
     * 楼盘Id
     */
    @ApiModelProperty(value = "楼盘Id")
    private Integer newcode;

    /**
     * 楼盘名称
     */
    @ApiModelProperty(value = "楼盘名称")
    private String projname;

    /**
     * 城市Id
     */
    @ApiModelProperty(value = "城市Id")
    private Integer cityId;

    /**
     * 优惠价格
     */
    @ApiModelProperty(value = "优惠价格")
    private Double preferentialPrice;

    /**
     * 均价
     */
    @ApiModelProperty(value = "均价")
    private Double averagePrice;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}