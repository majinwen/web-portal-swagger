package com.toutiao.appV2.model.report;

import com.toutiao.web.common.util.StringTool;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DecimalFormat;

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
    private String preferentialPrice;

    public void setPreferentialPrice(Double preferentialPrice) {
        DecimalFormat df = new DecimalFormat("0.0");
        if (StringTool.isEmpty(preferentialPrice)){
            preferentialPrice = 0.0;
        }

        this.preferentialPrice = df.format(preferentialPrice);
    }

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

    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    private Double totalPrice;
}