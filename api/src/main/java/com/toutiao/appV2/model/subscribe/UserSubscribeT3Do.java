package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserSubscribeT3Do {
    /**
     * 订阅id
     */
    @ApiModelProperty(value = "订阅id", name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 用户订阅关系
     */
    @ApiModelProperty(value = "用户订阅关系", name = "userSubscribeInfoT3")
    private UserSubscribeInfoT3 userSubscribeInfoT3;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private Date updateTime;

    /**
     * 订阅类型（0-房源专题订阅，1-条件订阅，2-小区专题订阅，3-排行榜订阅）
     */
    @ApiModelProperty(value = "订阅类型（0-房源专题订阅，1-条件订阅，2-小区专题订阅，3-排行榜订阅）", name = "subscribeType")
    private Integer subscribeType;

    /**
     * 城市代码
     */
    @ApiModelProperty(value = "城市代码", name = "cityId")
    private Integer cityId;
}
