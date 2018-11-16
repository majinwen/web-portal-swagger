package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * NewHouseAddFavoriteRequest
 */
@Data
@ApiModel(value = "NewHouseAddFavoriteRequest", description = "NewHouseAddFavoriteRequest")
public class NewHouseAddFavoriteRequest {

    @ApiModelProperty(value = "均价", name = "averagePrice", required = true)
    @NotNull(message = "均价不能为空")
    private BigDecimal averagePrice;

    @ApiModelProperty(value = "新房id", name = "buildingId", required = true)
    @NotNull(message = "新房id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "楼盘名称", name = "buildingName", required = true)
    @NotNull(message = "楼盘名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "图片", name = "buildingTitleImg", required = true)
    private String buildingTitleImg;

    @ApiModelProperty(value = "结束面积", name = "houseMaxArea", required = true)
    @NotNull(message = "结束面积不能为空")
    private String houseMaxArea;

    @ApiModelProperty(value = "起始面积", name = "houseMinArea", required = true)
    @NotNull(message = "起始面积不能为空")
    private String houseMinArea;

    @ApiModelProperty(value = "是否删除", name = "isDel")
    private Integer isDel;

    @ApiModelProperty(value = "状态", name = "status")
    private Integer status;

    @ApiModelProperty(value = "总价", name = "totalPrice", required = true)
    @NotNull(message = "总价不能为空")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

}

