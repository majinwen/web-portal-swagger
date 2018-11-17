package com.toutiao.app.domain.message;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageSellHouseDo {
    @ApiModelProperty(value = "房源Id", name = "houseId")
    private String houseId;

    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图", name = "housePhotoTitle")
    private String housePhotoTitle;

    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积", name = "buildArea")
    private Double buildArea;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    /**
     * 朝向
     */
    @ApiModelProperty(value = "朝向", name = "forwardName")
    @ChangeName("forward")
    private String forwardName;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", name = "area")
    @ChangeName("districtName")
    private String area;

    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id", name = "areaId")
    @ChangeName("districtId")
    private String areaId;

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "houseBusinessNameId")
    @ChangeName("areaId")
    private String houseBusinessNameId;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "houseBusinessName")
    @ChangeName("area")
    private String houseBusinessName;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "plotName")
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "newcode")
    @ChangeName("buildingId")
    private Integer newcode;

    /**
     * 是否价格洼地(0-否，1-是)
     */
    @ApiModelProperty(value = "是否价格洼地(0-否，1-是)", name = "isLowPrice")
    private Integer isLowPrice;

    /**
     * 是否降价房(0-否，1-降价房，2-涨价房)
     */
    @ApiModelProperty(value = "是否降价房(0-否，1-降价房，2-涨价房)", name = "isCutPrice")
    private Integer isCutPrice;

    /**
     * 价格浮动(单位:万)
     */
    @ApiModelProperty(value = "价格浮动(单位:万)", name = "priceFloat")
    private Double priceFloat;

    /**
     * 是否逢出必抢房(0-否，1-是)
     */
    @ApiModelProperty(value = "是否逢出必抢房(0-否，1-是)", name = "isMustRob")
    private Integer isMustRob;

    /**
     * 状态(0-未发布/1-已发布)
     */
    @ApiModelProperty(value = "状态(0-未发布/1-已发布)", name = "status")
    @ChangeName("releaseStatus")
    private Short status;

    /**
     * 房源详情Url
     */
    @ApiModelProperty(value = "房源详情Url", name = "houseDetailUrl")
    private String houseDetailUrl;

    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "hall")
    private String hall;
}
