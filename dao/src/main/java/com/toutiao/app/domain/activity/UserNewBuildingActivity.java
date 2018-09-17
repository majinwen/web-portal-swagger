package com.toutiao.app.domain.activity;

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
    /**
     * 用户活动id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 参与活动楼盘id
     */
    private Integer activityBuildingId;

    /**
     * 参与活动楼盘名称
     */
    private String activityBuildingName;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 城市代码
     */
    private Integer cityId;

    /**
     * 活动id
     */
    private Integer activityId;

}