package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportEsfProjHotResponse implements Comparable<ReportEsfProjHotResponse>{

    /**
     * ID
     */
//    @ApiModelProperty(value = "ID")
//    private Integer id;

    /**
     * 城市id
     */
//    @ApiModelProperty(value = "城市id")
//    private Integer cityId;

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

    public void setAveragePrice(Double averagePrice) {
        String doubleStr = averagePrice.toString();
        if(doubleStr.contains(".")){
            doubleStr = doubleStr.substring(0,doubleStr.indexOf(".")+1);
        }
        this.averagePrice = Double.parseDouble(doubleStr);
    }

    /**
     * 挂牌量
     */
    @ApiModelProperty(value = "挂牌量")
    private Integer houseCount;

    /**
     * 城市名称
     */
//    @ApiModelProperty(value = "城市名称")
//    private String cityName;

    /**
     * 更新时间
     */
//    @ApiModelProperty(value = "更新时间")
//    private Date time;

     /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;


    @Override
    public int compareTo(ReportEsfProjHotResponse o) {
        return this.getSort()-o.getSort();
    }
}