package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportAreaHotResponse {
    /**
     * ID
     */
//    @ApiModelProperty(value = "ID")
//    private Integer id;

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id")
    private Integer areaId;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 商圈均价
     */
    @ApiModelProperty(value = "商圈均价")
    private Double averagePrice;

    /**
     * 城市id
     */
//    @ApiModelProperty(value = "城市id")
//    private Integer cityId;

    /**
     * 城市名称
     */
//    @ApiModelProperty(value = "城市名称")
//    private String cityName;

    /**
     * 执行时间
     */
//    @ApiModelProperty(value = "执行时间")
//    private Date time;

    /**
     * 房子数量
     */
    @ApiModelProperty(value = "房子数量")
    private Integer houseCount;

    public void setAveragePrice(Double averagePrice) {
        String doubleStr = averagePrice.toString();
        if(doubleStr.contains(".")){
            doubleStr = doubleStr.substring(0,doubleStr.indexOf(".")+1);
        }
        this.averagePrice = Double.parseDouble(doubleStr);
    }
}