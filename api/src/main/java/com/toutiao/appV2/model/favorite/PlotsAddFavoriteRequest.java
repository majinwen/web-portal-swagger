package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * PlotsAddFavoriteRequest
 */
@Data
@ApiModel(value = "PlotsAddFavoriteRequest", description = "PlotsAddFavoriteRequest")
public class PlotsAddFavoriteRequest extends FavoriteBaseRequest {


    @ApiModelProperty(value = "小区id", name = "buildingId", required = true)
    @NotNull(message = "小区id不能为空")
    private Integer buildingId;

    @ApiModelProperty(value = "小区名称", name = "buildingName", required = true)
    @NotEmpty(message = "小区名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "小区标题图", name = "buildingImages")
    private String buildingImages;

    @ApiModelProperty(value = "均价", name = "averagePrice", required = true)
    @NotNull(message = "均价不能为空")
    private BigDecimal averagePrice;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Short status = 0;

    @ApiModelProperty(value = "建成年份", name = "buildYears")
    private String buildYears;

    @ApiModelProperty(value = "建筑结构", name = "buildingStructure")
    private String buildingStructure;

}

