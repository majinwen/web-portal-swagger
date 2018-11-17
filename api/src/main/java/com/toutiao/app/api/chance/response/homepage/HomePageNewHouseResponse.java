package com.toutiao.app.api.chance.response.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HomePageNewHouseResponse {

    @ApiModelProperty(value = "楼盘名称")
    private  String   buildingName;

    @ApiModelProperty(value = "总价")
    private Double  totalPrice;

    @ApiModelProperty(value = "区域")
    private  String districtName;

    @ApiModelProperty(value = "最小面积")
    private  Double houseMinArea;

    @ApiModelProperty(value = "最大面积")
    private  Double houseMaxArea;

    @ApiModelProperty(value = "销售状态")
    private  String  saleStatusName;

    @ApiModelProperty(value = "标题图")
    private  String  buildingTitleImg;

    @ApiModelProperty(value = "均价")
    private  Double averagePrice;

    @ApiModelProperty(value = "类别")
    private  String propertyType;

    @ApiModelProperty(value = "楼盘id")
    private  Integer buildingNameId;

    @ApiModelProperty(value = "最近交房")
    private  String deliverTime;

    @ChangeName("ringRoad")
    @ApiModelProperty(value = "环路")
    private  String ringRoadName;

}
