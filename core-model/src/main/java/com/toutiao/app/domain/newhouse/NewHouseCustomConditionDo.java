package com.toutiao.app.domain.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName NewHouseCustomConditionDo
 * @Author jiangweilong
 * @Date 2018/12/14 6:19 PM
 * @Description:
 **/

@Data
public class NewHouseCustomConditionDo {

    @ApiModelProperty(value = "楼盘名称")
    private String buildingName;

    @ApiModelProperty(value = "楼盘id")
    private  Integer buildingNameId;

    @ApiModelProperty(value = "均价")
    private Double averagePrice;

    @ApiModelProperty(value = "总价")
    private Double  totalPrice;

    @ApiModelProperty(value = "价格描述")
    private String priceDesc;

    @ApiModelProperty(value = "区域名称")
    private  String  districtName;

    @ApiModelProperty(value = "区域id")
    private Integer districtId;

    @ApiModelProperty(value = "标题图")
    private String buildingTitleImg;


}
