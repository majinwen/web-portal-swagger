package com.toutiao.app.api.chance.request.activity;

import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   14:01
 * Theme:
 */

@Data
public class NewHouseActivityRequest {

    @NotNull(groups ={Second.class},message = "缺少用户id")
    @ApiParam(value = "用户id")
    private Integer userId;

    @ApiParam(value = "用户手机号")
    private String userPhone;

    @NotEmpty(groups ={First.class},message = "缺少用户称呼名")
    @ApiParam(value = "用户昵称")
    private String userCallName;

    @NotNull(groups ={First.class},message = "缺少活动楼盘id")
    @ApiParam(value = "参与活动楼盘id")
    private Integer buildingId;

    @NotEmpty(groups ={First.class},message = "缺少活动楼盘名称")
    @ApiParam(value = "参与活动楼盘名称")
    private String buildingName;

    @ApiParam(value = "活动id")
    private Integer activityId;

    @ApiParam(value = "活动类型,1:折扣")
    private Integer activityType;

    @ApiParam(value = "活动文案")
    private String activityTitle;

    @ApiParam(value = "活动优惠信息")
    private String activitySubtitle;

    private Integer cityId;

    private int pageNum;

    private int pageSize;


}
