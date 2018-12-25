package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CutPriceSellHouseThemeDo
 * @Author jiangweilong
 * @Date 2018/12/25 11:13 AM
 * @Description:
 **/

@Data
public class CutPriceSellHouseThemeDo {


    @ApiModelProperty(value = "房源id", name = "houseId")
    private String houseId;

    @ApiModelProperty(value = "房源价格(单位:万)", name = "houseTotalPrices")
    private Double houseTotalPrices;

    @ApiModelProperty(value = "初始价格", name = "initialPrice")
    private Double initialPrice;

    @ApiModelProperty(value = "价格浮动(单位:万)", name = "priceFloat")
    private Double priceFloat;

    @ApiModelProperty(value = "房源标题图片", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    @ApiModelProperty(value = "房源面积(单位:平方米)", name = "buildArea")
    private Double buildArea;

    @ApiModelProperty(value = "上架时间", name = "importTime")
    private String importTime;

    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;

    @ApiModelProperty(value = "小区名称", name = "plotName")
    private String plotName;


}
