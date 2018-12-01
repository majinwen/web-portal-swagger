package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AddFavorite
 */
@Data
@ApiModel(value = "SellHouseAddFavoriteRequest", description = "SellHouseAddFavoriteRequest")
public class SellHouseAddFavoriteRequest extends FavoriteBaseRequest {

    @ApiModelProperty(value = "二手房房源id", name = "houseId", required = true)
    @NotNull(message = "缺少二手房房源id")
    private String houseId;

    @ApiModelProperty(value = "小区名称", name = "buildingName", required = true)
    @NotNull(message = "小区名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "二手房总价", name = "houseTotalPrices", required = true)
    @NotNull(message = "二手房总价不能为空")
    private BigDecimal houseTotalPrices;

    @ApiModelProperty(value = "二手房标题图", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "二手房标题", name = "houseTitle", required = true)
    @NotNull(message = "缺少二手房标题")
    private String houseTitle;

    @ApiModelProperty(value = "室", name = "room", required = true)
    @NotNull(message = "缺少室")
    private Integer room;

    @ApiModelProperty(value = "厅", name = "hall", required = true)
    @NotNull(message = "缺少厅")
    private Integer hall;

    @ApiModelProperty(value = "二手房面积", name = "buildArea", required = true)
    @NotNull(message = "面积不能为空")
    private Double buildArea;

    @ApiModelProperty(value = "朝向", name = "forward", required = true)
    @NotNull(message = "缺少朝向")
    private String forward;

    @ApiModelProperty(value = "二手房价格涨降标志(0未变动，1降价，2涨价)", name = "priceIncreaseDecline")
    private Integer priceIncreaseDecline;

    @ApiModelProperty(value = "认领标志", name = "isClaim")
    private Integer isClaim;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Short status = 0;

    @ApiModelProperty(value = "经纪公司图标", name = "companyIcon")
    private String companyIcon;


}

