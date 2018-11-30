package com.toutiao.app.domain.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wk on 2018/11/28.
 */
@Data
public class FavoriteHouseDo {

    @ApiModelProperty(value = "id", name = "id")
    private Integer id;

    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    @ApiModelProperty(value = "收藏时间", name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "上架状态(0-上架,1-下架)", name = "status")
    private Integer status;

    @ApiModelProperty(value = "小区id、新房id", name = "buildingId")
    private Integer buildingId;

    @ApiModelProperty(value = "二手房id、租房id", name = "houseId")
    private String houseId;

    @ApiModelProperty(value = "小区均价、新房均价", name = "averagePrice")
    private BigDecimal averagePrice;

    @ApiModelProperty(value = "新房起始面积", name = "houseMinArea")
    private String houseMinArea;

    @ApiModelProperty(value = "新房结束面积", name = "houseMaxArea")
    private String houseMaxArea;

    @ApiModelProperty(value = "小区名称、楼盘名称", name = "buildingName")
    private String buildingName;

    @ApiModelProperty(value = "标题图", name = "buildingTitleImg")
    private String buildingTitleImg;

    @ApiModelProperty(value = "总价", name = "totalPrice")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "城市编号", name = "cityId")
    private Integer cityId;

    @ApiModelProperty(value = "二手房面积", name = "buildArea")
    private BigDecimal buildArea;

    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    @ApiModelProperty(value = "朝向", name = "forward")
    private String forward;

    @ApiModelProperty(value = "二手房总价", name = "houseTotalPrices")
    private BigDecimal houseTotalPrices;

    @ApiModelProperty(value = "二手房标题图", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "租房房源标题", name = "houseTitle")
    private String houseTitle;

    @ApiModelProperty(value = "涨降价标志(0未变动，1涨价，2降价)", name = "priceIncreaseDecline")
    private Integer priceIncreaseDecline;

    @ApiModelProperty(value = "二手房认领(0未认领，1认领)", name = "isClaim")
    private Integer isClaim;

    @ApiModelProperty(value = "租房租金", name = "rentPrice")
    private BigDecimal rentPrice;

    @ApiModelProperty(value = "租赁方式", name = "rentType")
    private Integer rentType;

    @ApiModelProperty(value = "租房中文名称", name = "rentTypeName")
    private String rentTypeName;

    @ApiModelProperty(value = "租房房屋面积", name = "houseArea")
    private BigDecimal houseArea;

    @ApiModelProperty(value = "房源类型(1-小区、2-新房、3-二手房、4-住房)", name = "type")
    private Integer type;

    @ApiModelProperty(value = "区域名称", name = "districtName")
    private String districtName;

    @ApiModelProperty(value = "商圈名称", name = "areaName")
    private String areaName;
}
