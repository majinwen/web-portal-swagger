package com.toutiao.app.domain.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   13:57
 * Theme:  用户&新楼活动信息表
 */

@Data
public class UserNewBuildingActivity {

    @ApiModelProperty(value = "用户活动id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "用户昵称")
    private String userCallName;

    @ApiModelProperty(value = "参与活动楼盘id")
    private Integer activityBuildingId;

    @ApiModelProperty(value = "参与活动楼盘名称")
    private String activityBuildingName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "城市代码")
    private Integer cityId;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动类型,1:折扣")
    private Integer activityType;

    @ApiModelProperty(value = "活动文案")
    private String activityTitle;

    @ApiModelProperty(value = "活动优惠信息")
    private String activitySubtitle;

}