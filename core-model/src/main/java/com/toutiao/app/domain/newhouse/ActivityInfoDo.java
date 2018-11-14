package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class ActivityInfoDo {
    /**
     * 活动id
     */
    private String activityId;
    /**
     * 活动类型
     */
    private Integer activityType;
    /**
     * 活动标题
     */
    private String activityTitle;
    /**
     * 活动折扣
     */
    private String activitySubtitle;

}
