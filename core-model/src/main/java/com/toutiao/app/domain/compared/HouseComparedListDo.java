package com.toutiao.app.domain.compared;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HouseComparedListDo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 二手房房源id
     */
    @ApiModelProperty(value = "二手房房源id")
    private String houseId;

    /**
     * 房源标题
     */
    @ApiModelProperty(value = "房源标题")
    private String houseTitle;

    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价")
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积")
    private Double buildArea;

    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图")
    private String housePhotoTitle;

    /**
     * 室
     */
    @ApiModelProperty(value = "室")
    private Integer room;

    /**
     * 楼盘名称
     */
    @ApiModelProperty(value = "楼盘名称")
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 是否主力户型(0-否，1-是)
     */
    @ApiModelProperty(value = "是否主力户型(0-否，1-是)")
    private Integer isMainLayout;

    /**
     * 是否降价房(0-否，1-降价房，2-涨价房)
     */
    @ApiModelProperty(value = "是否降价房(0-否，1-降价房，2-涨价房)")
    private Integer isCutPrice;

    /**
     * 总价价格浮动
     */
    @ApiModelProperty(value = "总价价格浮动")
    private Double priceFloat;

    /**
     * 是否是top50小区房源(0-否，1-是)
     */
    @ApiModelProperty(value = "是否是top50小区房源(0-否，1-是)")
    private Integer isCommunityTopHouse;

    /**
     * 是否同户型小区均价最低(0-否，1-是)
     */
    @ApiModelProperty(value = "是否同户型小区均价最低(0-否，1-是)")
    private Integer isLowest;

    /**
     * 是否显示默认图片
     */
    @ApiModelProperty(value = "是否显示默认图片")
    private Integer isDefaultImage;

}

