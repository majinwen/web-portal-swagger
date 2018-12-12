package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportEsfProjHot {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称")
    private String projname;

    /**
     * 小区code
     */
    @ApiModelProperty(value = "小区code")
    private Integer newcode;

    /**
     * 均价
     */
    @ApiModelProperty(value = "均价")
    private Double averagePrice;

    /**
     * 挂牌量
     */
    @ApiModelProperty(value = "挂牌量")
    private Integer hosueCount;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date time;
}