package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseThemeDo {
    /**
     * 房源id
     */
    @ApiModelProperty(value = "房源id", name = "houseId")
    private String houseId;

    /**
     * 房源面积(单位:平方米)
     */
    @ApiModelProperty(value = "房源面积(单位:平方米)", name = "buildArea")
    private Double buildArea;

    /**
     * 房源价格(单位:万)
     */
    @ApiModelProperty(value = "房源价格(单位:万)", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 价格浮动(单位:万)
     */
    @ApiModelProperty(value = "价格浮动(单位:万)", name = "priceFloat")
    private Double priceFloat;

    /**
     * 房源标题图片
     */
    @ApiModelProperty(value = "房源标题图片", name = "housePhotoTitle")
    private String housePhotoTitle;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    @ApiModelProperty(value = "在同商圈同户型范围内做低价排名", name = "rankLowInBizcircleLayout")
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankInLowCommunityLayout")
    private Integer rankInLowCommunityLayout;

    /**
     * 与小区平均单价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均单价的绝对值差", name = "avgAbsoluteWithCommunity")
    private Double avgAbsoluteWithCommunity;

    /**
     * 与商圈平均单价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均单价的绝对值差", name = "avgAbsoluteWithBizcircle")
    private Double avgAbsoluteWithBizcircle;

    /**
     * 与区县平均单价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均单价的绝对值差", name = "avgAbsoluteWithDistrict")
    private Double avgAbsoluteWithDistrict;

    /**
     * 与小区平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均单价的相对值(百分比)", name = "avgRelativeWithCommunity")
    private Double avgRelativeWithCommunity;

    /**
     * 与商圈平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均单价的相对值(百分比)", name = "avgRelativeWithBizcircle")
    private Double avgRelativeWithBizcircle;

    /**
     * 与区县平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均单价的相对值(百分比)", name = "avgRelativeWithDistrict")
    private Double avgRelativeWithDistrict;

    /**
     * 与小区平均总价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均总价的绝对值差", name = "totalAbsoluteWithCommunity")
    private Double totalAbsoluteWithCommunity;

    /**
     * 与商圈平均总价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均总价的绝对值差", name = "totalAbsoluteWithBizcircle")
    private Double totalAbsoluteWithBizcircle;

    /**
     * 与区县平均总价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均总价的绝对值差", name = "totalAbsoluteWithDistrict")
    private Double totalAbsoluteWithDistrict;

    /**
     * 与小区平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均总价的相对值(百分比)", name = "totalRelativeWithCommunity")
    private Double totalRelativeWithCommunity;

    /**
     * 与商圈平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均总价的相对值(百分比)", name = "totalRelativeWithBizcircle")
    private Double totalRelativeWithBizcircle;

    /**
     * 与区县平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均总价的相对值(百分比)", name = "totalRelativeWithDistrict")
    private Double totalRelativeWithDistrict;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;

    /**
     * 是否认领(0-否,1-是)
     */
    @ApiModelProperty(value = "是否认领(0-否,1-是)", name = "isClaim")
    private Integer isClaim;

    /**
     * 房源均价
     */
    @ApiModelProperty(value = "房源均价", name = "houseUnitCost")
    private Double houseUnitCost;

    /**
     * 平均成交天数
     */
    @ApiModelProperty(value = "平均成交天数", name = "avgDealCycle")
    private Integer avgDealCycle;

    /**
     * 小区专题标签
     */
    @ApiModelProperty(value = "小区专题标签", name = "recommendBuildTagsName")
    private List recommendBuildTagsName;

    /**
     * 房源导入时间
     */
    @ApiModelProperty(value = "房源导入时间", name = "importTime")
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;


}
