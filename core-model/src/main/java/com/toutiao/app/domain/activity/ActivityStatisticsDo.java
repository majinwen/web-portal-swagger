package com.toutiao.app.domain.activity;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-20
 * Time:   16:57
 * Theme:
 */
@Data
public class ActivityStatisticsDo {

    /**
     * 活动楼盘数量
     */
    private int activityBuildsAmount;

    /**
     * 累计领取数量
     */
    private int cumulativeAmount;

    /**
     * 昨天领取数量
     */
    private int beforeCumulativeAmount;


}
