package com.toutiao.app.service.activity;

import com.github.pagehelper.PageInfo;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.web.common.restmodel.NashResult;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   14:22
 * Theme:
 */
public interface NewHouseActivityRestService {


    /**
     * 表单提交--用户提交参与的活动楼盘信息
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    NashResult isActivity(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery);

    /**
     * 表单提交--用户提交参与的活动楼盘信息
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    NashResult saveUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery);

    /**
     * 查询用户参与新房活动信息--后台使用
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    PageInfo<UserNewBuildingActivityDo> listUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery);

    /**
     * 查询用户参与新房活动信息--前台个人中心使用
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    PageInfo<UserNewBuildingActivityDo> listActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery);


    ActivityStatisticsDo getActivityCount();

}
