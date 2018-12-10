package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "租房价格分布")
public class ReportRentPriceDistrbution {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Short id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 房源数量
     */
    @ApiModelProperty(value = "房源数量")
    private Integer rentCount;

    /**
     * 价格范围描述
     */
    @ApiModelProperty(value = "价格范围描述")
    private String rentRange;

    /**
     * 百分数
     */
    @ApiModelProperty(value = "百分数")
    private Double percent;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id")
    private Integer cityId;
}