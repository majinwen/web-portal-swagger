package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.plot.PlotMarketDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName HotSellHouseThemeDo
 * @Author jiangweilong
 * @Date 2018/12/25 12:33 PM
 * @Description:
 **/

@Data
public class HotSellHouseThemeDo {

    @ApiModelProperty(value = "房源id", name = "houseId")
    private String houseId;

    @ApiModelProperty(value = "小区名称", name = "plotName")
    private String plotName;

    @ApiModelProperty(value = "房源价格(单位:万)", name = "houseTotalPrices")
    private Double houseTotalPrices;

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

//    @ApiModelProperty(value = "小区推荐标签", name = "recommendBuildTagsName")
//    private List<String> recommendBuildTagsName;
//
//    @ApiModelProperty(value = "小区自带标签", name = "label")
//    private List<String> label;

    @ApiModelProperty(value = "小区所在区域热度排名", name = "districtHotSort")
    private Integer districtHotSort;

    @ApiModelProperty(value = "房源热度", name = "hotStarCount")
    private Integer hotStarCount;

    @ApiModelProperty(value = "平均成交天数", name = "avgDealCycle")
    private Integer avgDealCycle;

    @ApiModelProperty(value = "单居室总价", name = "perRoomPrice")
    private Double perRoomPrice;

    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;

    @ApiModelProperty(value = "小区id", name = "newcode")
    private Integer newcode;

    @ApiModelProperty(value = "区域名称", name = "area")
    private String area;

    @ApiModelProperty(value = "小区行情", name = "plotMarketDomain")
    private PlotMarketDomain plotMarketDomain;

    @ApiModelProperty(value = "标签", name = "tagsName")
    private String tagsName;

}
