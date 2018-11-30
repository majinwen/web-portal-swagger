package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * NewHouseAddFavoriteRequest
 */
@Data
@ApiModel(value = "NewHouseAddFavoriteRequest", description = "NewHouseAddFavoriteRequest")
public class NewHouseAddFavoriteRequest extends FavoriteBaseRequest {


    @ApiModelProperty(value = "新房id", name = "buildingId", required = true)
    @NotNull(message = "新房id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "楼盘名称", name = "buildingName", required = true)
    @NotNull(message = "楼盘名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "均价", name = "averagePrice", required = true)
    private BigDecimal averagePrice;

    @ApiModelProperty(value = "总价", name = "totalPrice", required = true)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "标题图片", name = "buildingTitleImg", required = true)
    private String buildingTitleImg;

    @ApiModelProperty(value = "起始面积", name = "houseMinArea", required = true)
    @NotNull(message = "起始面积不能为空")
    private String houseMinArea;

    @ApiModelProperty(value = "结束面积", name = "houseMaxArea", required = true)
    @NotNull(message = "结束面积不能为空")
    private String houseMaxArea;

    @ApiModelProperty(value = "在售户型", name = "roomType", required = true)
    @NotNull(message = "在售户型不能为空")
    private Integer[] roomType;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Short status = 0;

    @ApiModelProperty(value = "是否优惠活动", name = "isActive")
    @NotNull(message = "是否优惠活动不能为空")
    private Integer isActive = 0;


}

