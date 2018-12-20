package com.toutiao.app.domain.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ActivityInfoDo {

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "活动类型")
    private Integer activityType;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "活动折扣")
    private String activitySubtitle;

}
