package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * AddFavorite
 */
@Data
@ApiModel(value = "AddFavorite", description = "AddFavorite")
public class AddFavorite {

    @ApiModelProperty(value = "室", name = "room", required = true)
    @NotNull(message = "缺少室")
    private Integer room;

    @ApiModelProperty(value = "租房面积", name = "houseArea", required = true)
    @NotNull(message = "面积不为空")
    private Double houseArea;

    @ApiModelProperty(value = "二手房面积", name = "buildArea", required = true)
    @NotNull(message = "面积不能为空")
    private Double buildArea;


    @ApiModelProperty(value = "大楼名称", name = "buildingName", required = true)
    @NotNull(message = "大楼名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "总价", name = "houseTotalPrices", required = true)
    @NotNull(message = "总价不能为空")
    private Double houseTotalPrices;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "缺少用户id")
    private Integer userId;


    @ApiModelProperty(value = "房源id", name = "houseId", required = true)
    @NotNull(message = "缺少房源id")
    private String houseId;

    @ApiModelProperty(value = "二手房标题图", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "二手房标题", name = "houseTitle", required = true)
    @NotNull(message = "缺少二手房标题")
    private String houseTitle;

    @ApiModelProperty(value = "租金", name = "rentPrice", required = true)
    @NotNull(message = "缺少租金")
    private Double rentPrice;

    @ApiModelProperty(value = "出租类型", name = "rentType", required = true)
    @NotNull(message = "缺少出租类型")
    private String rentType;

    @ApiModelProperty(value = "朝向", name = "forward", required = true)
    @NotNull(message = "缺少朝向")
    private String forward;

    @ApiModelProperty(value = "二手房价格涨降标志", name = "isCutPrice")
    private String isCutPrice;

    @ApiModelProperty(value = "认领标志", name = "isClaim")
    private Integer isClaim;


}

