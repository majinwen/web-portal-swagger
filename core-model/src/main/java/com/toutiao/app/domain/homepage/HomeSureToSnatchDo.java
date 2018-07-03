package com.toutiao.app.domain.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class HomeSureToSnatchDo {
    /**
     * top50
     */
    private  Integer isCommunityTopHouse;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 室
     */
    private Integer room;

    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 房源与商圈的均价比
     */
     private  Double avgRelativeWithBizcircle;


    /**
     * 主力户型
     */
    private  Integer isMainLayout;


    /**
     * 普通房源标题图
     */
    private  String housePhotoTitle;

    /**
     * 认领房源标题图
     */
    private String claimHousePhotoTitle;

    /**
     * 是否是认领
     */
    private  Integer isClaim;
}
