package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * PlotsAddFavoriteRequest
 */
@Data
@ApiModel(value = "PlotsAddFavoriteRequest", description = "PlotsAddFavoriteRequest")
public class PlotsAddFavoriteRequest {


    @ApiModelProperty(value = "小区id", name = "buildingId", required = true)
    @NotNull(message = "小区id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "均价", name = "averagePrice", required = true)
    @NotNull(message = "均价不能为空")
    private Double averagePrice;

    @ApiModelProperty(value = "小区名称", name = "buildingName", required = true)
    @NotEmpty(message = "小区名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "标题图", name = "buildingImages")
    private String buildingImages;

    @ApiModelProperty(value = "是否下架(0-未下架, 1-下架)", name = "status")
    private Short status = 0;

    @ApiModelProperty(value = "是否删除(0-未删除，1-已删除)", name = "isDel")
    private Short isDel = 0;


}

