package com.toutiao.app.domain.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewHouseLayoutDo {

    @ApiModelProperty(value = "销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)")
    private  Integer isSale;

    @ApiModelProperty(value = "楼盘id")
    private  Integer buildingId;

    @ApiModelProperty(value = "户型id")
    private  Integer layoutId;

    @ApiModelProperty(value = "厅")
    private  Integer hall;

    @ApiModelProperty(value = "建筑面积")
    private  Double buildingArea;

    @ApiModelProperty(value = "参考均价")
    private  Double referencePrice;

    @ApiModelProperty(value = "户型标题")
     private  String layoutTitle;

    @ApiModelProperty(value = "户型图片")
    private  String layoutImg;

    @ApiModelProperty(value = "室")
    private  Integer room;

    @ApiModelProperty(value = "厕所")
    private  Integer toilet;

    @ApiModelProperty(value = "参考总价")
    private  Double referenceTotalPrice;

    @ApiModelProperty(value = "销售面积")
    private  Double saleArea;

    @ApiModelProperty(value = "是否推荐")
    private  Integer isRecommend;

    @ApiModelProperty(value = "户型描述/户型解析")
    private  String layoutDesc;

    @ApiModelProperty(value = "居住面积")
    private  Double livingArea;

    @ApiModelProperty(value = "厨房")
    private  Integer kitchen;

}
