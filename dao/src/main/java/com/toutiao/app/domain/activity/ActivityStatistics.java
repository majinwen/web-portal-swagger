package com.toutiao.app.domain.activity;

import lombok.Data;

import java.util.Date;

/**
 *优惠活动统计
 *
 */
@Data
public class ActivityStatistics {
    /**
     * 活动统计id
     */
    private Integer id;

    /**
     * 活动累计总量
     */
    private Integer activityGrandTotal;

    /**
     * 昨天领取量
     */
    private Integer yesterdayActivityTotal;

    /**
     * 统计时间
     */
    private Date statisticalDate;

}