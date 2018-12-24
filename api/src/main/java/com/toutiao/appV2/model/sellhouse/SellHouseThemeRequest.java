package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SellHouseThemeRequest
 * @Author jiangweilong
 * @Date 2018/12/24 4:29 PM
 * @Description:
 **/

@Data
public class SellHouseThemeRequest {

    @ApiModelProperty(value = "区域Ids", name = "districtIds")
    private Integer[] districtIds;

    @ApiModelProperty(value = "起始价格", name = "beginPrice")
    private double beginPrice;

    @ApiModelProperty(value = "结束价格", name = "endPrice")
    private double endPrice;

    @ApiModelProperty(value = "居室", name = "layoutId")
    private Integer[] layoutId;

    @ApiModelProperty(value = "页码", name = "pageNum")
    private Integer pageNum;

    @ApiModelProperty(value = "数量", name = "pageSize")
    private Integer pageSize;

    @ApiModelProperty(value = "主题类型", name = "themeType")
    private Integer themeType;
}


