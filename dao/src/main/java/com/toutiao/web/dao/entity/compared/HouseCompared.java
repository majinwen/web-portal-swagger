package com.toutiao.web.dao.entity.compared;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class HouseCompared {
    @ApiModelProperty(value = "对比id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "二手房房源id")
    private String houseId;

    @ApiModelProperty(value = "房源状态是否是下架，0未下架，1下架")
    private Short houseStatus;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否删除(0-未删除，1-已删除)")
    private Short isDel;

    @ApiModelProperty(value = "城市代码")
    private Integer cityId;
}