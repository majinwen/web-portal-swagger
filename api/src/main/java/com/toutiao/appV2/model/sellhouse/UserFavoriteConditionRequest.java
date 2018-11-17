package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "UserFavoriteConditionRequest", description = "UserFavoriteConditionRequest")
public class UserFavoriteConditionRequest {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id", name = "districtId")
    private String[] districtId;
    /**
     * 起始价格
     */
    @ApiModelProperty(value = "起始价格", name = "beginPrice")
    private Double beginPrice = 0.0;
    /**
     * 结束价格
     */
    @ApiModelProperty(value = "结束价格", name = "endPrice")
    private Double endPrice;
    /**
     * 户型id
     */
    @ApiModelProperty(value = "户型id", name = "layoutId")
    private String[] layoutId;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市", name = "city")
    private String city;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "pageNum")
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", name = "pageSize")
    private Integer pageSize = 10;
    /**
     * 是否有预设条件(0:无,1:有 默认0)
     */
    @ApiModelProperty(value = "是否有预设条件(0:无,1:有 默认0)", name = "flag")
    private Integer flag = 0;
}

