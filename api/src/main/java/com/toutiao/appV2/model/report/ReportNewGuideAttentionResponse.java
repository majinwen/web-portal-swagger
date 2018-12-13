package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportNewGuideAttentionResponse {
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
     * 区域Id
     */
    @ApiModelProperty(value = "区域Id")
    private Integer districtId;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String districtName;

    /**
     * 商圈Id
     */
    @ApiModelProperty(value = "商圈Id")
    private Integer areaId;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称")
    private String areaName;

    /**
     * 交房时间
     */
    @ApiModelProperty(value = "交房时间")
    private String livindate;

    /**
     * 城市Id
     */
//    @ApiModelProperty(value = "城市Id")
//    private Integer cityId;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Double averagePrice;

    /**
     * 创建时间
     */
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;
}