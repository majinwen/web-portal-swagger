package com.toutiao.app.api.chance.response.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RentOfPlotListResPonse {

    @ApiModelProperty(value = "租房列表")
    private List<RentOfPlotResPonse> data;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id")
    private Integer buildingId;
//    /**
//     * 用户id
//     */
//    @ApiModelProperty(value = "用户id")
//    private Integer userId;
    /**
     * 均价
     */
    @ApiModelProperty(value = "均价")
    private BigDecimal averagePrice;
    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称")
    private String buildingName;
    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图")
    private String buildingImages;
//    /**
//     * 是否下架(0-未下架, 1-下架)
//     */
//    @ApiModelProperty(value = "是否下架(0-未下架, 1-下架)")
//    private Short status;
//    /**
//     * 是否删除(0-未删除，1-已删除)
//     */
//    @ApiModelProperty(value = "是否删除(0-未删除，1-已删除)")
//    private Short isDel = 0;

    @ApiModelProperty(value = "城市")
    private Integer cityId;
    @ApiModelProperty(value = "建筑年代")
    private String buildYears;
    @ApiModelProperty(value = "建筑风格")
    private String buildingStructure;
    @ApiModelProperty(value = "区域名称")
    private String districtName;
    @ApiModelProperty(value = "商圈名称")
    private String areaName;
    @ApiModelProperty(value = "标签名称")
    private List<String> tags;


    @ApiModelProperty(value = "附近的地铁线")
    private String nearSubwayLine;

    @ApiModelProperty(value = "总套数")
    private Integer totalNum;
}
