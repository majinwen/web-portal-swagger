package com.toutiao.app.api.chance.request.favorite;

import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddFavorite {

    /**
     * 室
     */
    @NotNull(groups ={First.class,Second.class},message = "缺少室")
    private  Integer  room;

    /**
     * 面积
     */
    /**
     * 租房面积
     */
    @NotNull(groups ={Second.class},message = "面积不为空")
    private  Double  houseArea;

    /**
     * 二手房面积
     */
    @NotNull(groups = {First.class},message = "面积不能为空")
    private  Double  buildArea;


    /**
     *  大楼名称
     */
    @NotNull(groups ={First.class,Second.class},message = "大楼名称不能为空")
    private  String buildingName;

    /**
     * 总价
     */
    @NotNull(groups ={First.class},message = "总价不能为空")
    private  Double houseTotalPrices;

    /**
     * 用户id
     */
    @NotNull(groups ={First.class,Second.class},message = "缺少用户id")
    private Integer userId;


    /**
     * 房源id
     */
    @NotNull(groups ={First.class,Second.class},message = "缺少房源id")
    private String houseId;

    /**
     * 二手房标题图
     */
    @NotNull(groups ={First.class,Second.class},message = "缺少二手房标题图")
    private  String  housePhotoTitle;

    /**
     * 二手房标题
     */
    @NotNull(groups ={First.class,Second.class},message = "缺少二手房标题")
    private  String  houseTitle;

    @NotNull(groups = {Second.class}, message ="缺少租金")
    private  Double rentPrice;

    @NotNull(groups = {Second.class},message = "缺少出租类型")
    private  String rentType;

    @NotNull(groups = {First.class,Second.class},message = "缺少朝向")
    private String forward;
}
