package com.toutiao.app.domain.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by wk on 2018/11/29.
 */
@Data
public class FavoriteHouseVo {


    @ApiModelProperty(value = "id", name = "id")
    private String id;

    @ApiModelProperty(value = "小区名称", name = "buildingName")
    private String buildingName;

    @ApiModelProperty(value = "标题", name = "title")
    private String title;

    @ApiModelProperty(value = "标题图", name = "titleImg")
    private String titleImg;

    @ApiModelProperty(value = "类型(1 新房 2 二手房 3 小区 4 租房)", name = "type")
    private Integer type;

    @ApiModelProperty(value = "价格", name = "price")
    private Integer price;

    @ApiModelProperty(value = "新房总价", name = "totalPrice")
    private Integer totalPrice;

    @ApiModelProperty(value = "区域名称", name = "districtName")
    private String districtName;

    @ApiModelProperty(value = "商圈名称", name = "areaName")
    private String areaName;

    @ApiModelProperty(value = "标签名称", name = "tags")
    private String[] tags;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Integer status;

    @ApiModelProperty(value = "城市编号", name = "cityId")
    private Integer cityId;

    /**
     * 小区描述
     **/
    @ApiModelProperty(value = "小区建成时间", name = "buildYears")
    private String buildYears;

    @ApiModelProperty(value = "小区建成类型", name = "buildingStructure")
    private String buildingStructure;

    /**
     * 新房描述
     **/

    @ApiModelProperty(value = "新房起始面积", name = "houseMinArea")
    private String houseMinArea;

    @ApiModelProperty(value = "新房结束面积", name = "houseMaxArea")
    private String houseMaxArea;

    @ApiModelProperty(value = "新房在售户型", name = "roomType")
    private Integer[] roomType;

    @ApiModelProperty(value = "新房是否活动", name = "isActive")
    private Integer isActive;

    /**
     * 二手房描述
     **/
    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    @ApiModelProperty(value = "二手房面积", name = "buildArea")
    private BigDecimal buildArea;

    @ApiModelProperty(value = "朝向", name = "forward")
    private String forward;

    @ApiModelProperty(value = "经纪公司图标", name = "companyIcon")
    private String companyIcon;

    /**
     * 租房描述
     **/

    @ApiModelProperty(value = "租房中文名称", name = "rentTypeName")
    private String rentTypeName;

    @ApiModelProperty(value = "租房房屋面积", name = "houseArea")
    private BigDecimal houseArea;


}
