package com.toutiao.app.domain.message;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class MessageSellHouseDo {
    private String houseId;

    /**
     * 标题图
     */
    private String housePhotoTitle;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

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
     * 区域名称
     */
    @ChangeName("districtName")
    private String area;

    /**
     * 区域id
     */
    @ChangeName("districtId")
    private String areaId;

    /**
     * 商圈id
     */
    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 商圈名称
     */
    @ChangeName("areaName")
    private String houseBusinessName;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ChangeName("buildingId")
    private Integer newcode;

    /**
     * 是否价格洼地(0-否，1-是)
     */
    private Integer isLowPrice;

    /**
     * 是否降价房(0-否，1-降价房，2-涨价房)
     */
    private Integer isCutPrice;

    /**
     * 是否逢出必抢房(0-否，1-是)
     */
    private Integer isMustRob;

    /**
     * 状态(0-未发布/1-已发布)
     */
    @ChangeName("releaseStatus")
    private Short status;
}
