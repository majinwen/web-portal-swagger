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
    @ApiModelProperty(value = "朝向", name = "forward")
    @ChangeName("forward")
    private String forwardName;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", name = "districtName")
    @ChangeName("districtName")
    private String area;

    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id", name = "districtId")
    @ChangeName("districtId")
    private String areaId;

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "areaId")
    @ChangeName("areaId")
    private String houseBusinessNameId;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "area")
    @ChangeName("area")
    private String houseBusinessName;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "buildingName")
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "buildingId")
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
    @ApiModelProperty(value = "状态(0-未发布/1-已发布)", name = "releaseStatus")
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

    @ApiModelProperty(value = "公司", name = "ofCompany")
    private String ofCompany;

    @ApiModelProperty(value = "公司Icon", name = "companyIcon")
    private String companyIcon;


    /********************************租房********************************************/


    @ApiModelProperty(value = "（出租）同户型低于小区总价", name = "avgDealCycle")
    private Integer avgDealCycle;

    @ApiModelProperty(value = "（出租）租赁方式", name = "rentType")
    private Integer rentType;

    @ApiModelProperty(value = "（出租）租赁方式名称", name = "rentTypeName")
    private String rentTypeName;

    @ApiModelProperty(value = "（出租）租金，单位：元/月", name = "rentPrice")
    private Double rentPrice;

    @ApiModelProperty(value = "（出租）标签", name = "tags")
    private String[] tags;
}
