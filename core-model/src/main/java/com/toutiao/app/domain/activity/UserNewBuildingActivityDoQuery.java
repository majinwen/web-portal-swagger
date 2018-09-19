package com.toutiao.app.domain.activity;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   14:01
 * Theme:
 */

@Data
public class UserNewBuildingActivityDoQuery {

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
    private String userCallName;

    /**
     * 参与活动楼盘id
     */
    private Integer buildingId;

    /**
     * 参与活动楼盘名称
     */
    private String buildingName;
//
//    /**
//     * 参与活动类型标志
//     */
//    private Integer activityType;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页记录数
     */
    private int pageSize;


}
