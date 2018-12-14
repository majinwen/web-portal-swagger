package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportNewPreferentialResponse {
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
//    @ApiModelProperty(value = "城市Id")
//    private Integer cityId;

    /**
     * 优惠价格
     */
    @ApiModelProperty(value = "优惠价格(万元)")
    private Double offPrice;

    /**
     * 均价
     */
    @ApiModelProperty(value = "均价(元/平米)")
    private Double averagePrice;

    /**
     * 创建时间
     */
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;

}