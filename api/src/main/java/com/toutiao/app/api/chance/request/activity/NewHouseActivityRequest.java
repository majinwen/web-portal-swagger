package com.toutiao.app.api.chance.request.activity;

import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
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

    /**
     * 用户id
     */
//    @NotNull(groups ={First.class,Second.class},message = "缺少用户id")
    private Integer userId;

    /**
     * 用户手机号
     */
    @NotEmpty(groups ={First.class},message = "缺少用户手机号码")
    private String userPhone;

    /**
     * 用户昵称
     */
    //@NotEmpty(groups ={First.class},message = "缺少用户称呼名")
    private String userCallName;

    /**
     * 参与活动楼盘id
     */
    @NotNull(groups ={First.class},message = "缺少活动楼盘id")
    private Integer buildingId;

    /**
     * 参与活动楼盘名称
     */
    @NotEmpty(groups ={First.class},message = "缺少活动楼盘名称")
    private String buildingName;

    /**
     * 活动id
     */
    //@NotNull(groups ={First.class},message = "缺少活动id")
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
