package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by wk on 2018/11/29.
 */
@Data
public class RentHouseAddFavoriteRequest extends FavoriteBaseRequest {

    @ApiModelProperty(value = "租房房源id", name = "houseId", required = true)
    @NotNull(message = "缺少租房房源id")
    private String houseId;

    @ApiModelProperty(value = "小区名称", name = "buildingName", required = true)
    @NotNull(message = "小区名称不能为空")
    private String buildingName;

    @ApiModelProperty(value = "租金", name = "rentPrice", required = true)
    @NotNull(message = "缺少租金")
    private BigDecimal rentPrice;

    @ApiModelProperty(value = "出租类型(1整租 2合租)", name = "rentType", required = true)
    @NotNull(message = "缺少出租类型")
    private String rentType;

    @ApiModelProperty(value = "室", name = "room", required = true)
    @NotNull(message = "缺少室")
    private Integer room;

    @ApiModelProperty(value = "厅", name = "hall", required = true)
    private Integer hall;

    @ApiModelProperty(value = "租房面积", name = "houseArea", required = true)
    @NotNull(message = "面积不为空")
    private Double houseArea;

    @ApiModelProperty(value = "朝向", name = "forward", required = true)
    @NotNull(message = "缺少朝向")
    private String forward;

    @ApiModelProperty(value = "租房房标题", name = "houseTitle", required = true)
    @NotNull(message = "缺少租房房标题")
    private String houseTitle;

    @ApiModelProperty(value = "租房标题图", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Short status = 0;



}
