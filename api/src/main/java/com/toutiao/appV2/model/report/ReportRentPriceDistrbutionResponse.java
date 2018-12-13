package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportRentPriceDistrbutionResponse {

    /**
     * ID
     */
//    @ApiModelProperty(value = "ID")
//    private Short id;

    /**
     * 创建时间
     */
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;

    /**
     * 房源数量
     */
    @ApiModelProperty(value = "房源数量")
    private Integer count;

    /**
     * 价格范围描述
     */
    @ApiModelProperty(value = "价格范围描述")
    private String rangeZ;

    /**
     * 环比
     */
    @ApiModelProperty(value = "环比")
    private Double percent;

    /**
     * 城市id
     */
//    @ApiModelProperty(value = "城市id")
//    private Integer cityId;
}