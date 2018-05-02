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
    @NotNull(message = "缺少室")
    private  Integer  room;

    /**
     * 面积
     */

    @NotNull(message = "面积不为空")
    private  Double  houseArea;


    /**
     *  大楼名称
     */
    @NotNull(message = "大楼名称不能为空")
    private  String buildingName;

    /**
     * 总价
     */
    @NotNull( groups ={First.class},message = "总价不能为空")
    private  Double totalPrice;

    /**
     * 用户id
     */
    @NotNull(message = "缺少用户id")
    private Integer UserId;


    /**
     * 房源id
     */
    @NotNull(message = "缺少房源id")
    private String HouseId;

    /**
     * 二手房标题图
     */
    @NotNull(message = "缺少二手房标题图")
    private  String  housePhotoTitle;

    /**
     * 二手房标题
     */
    @NotNull(message = "缺少二手房标题")
    private  String  houseTitle;

    @NotNull(groups = {Second.class}, message ="缺少租金")
    private  Double rentPrice;

    @NotNull(groups = {Second.class},message = "缺少出租类型")
    private  String rentType;

}
