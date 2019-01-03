package com.toutiao.appV2.model.plot;

import com.toutiao.app.domain.plot.PlotMarketDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * PlotDetailsResponse
 */
@Validated
@Data
public class PlotDetailsResponse {

    @ApiModelProperty("建成年代buildYears")
    private String abbreviatedAge = null;

    @ApiModelProperty("空气质量")
    private String airQuality = null;

    @ApiModelProperty("占地面积")
    private Double areaSize = null;

    @ApiModelProperty("绿化率")
    private Double avgGreening = null;

    @ApiModelProperty("均价averagePrice")
    private Double avgPrice = null;

    @ApiModelProperty("建筑面积")
    private Double buildingAreaSize = null;

    @ApiModelProperty("建筑类型结构")
    private String buildingStructure = null;

    @ApiModelProperty("车位配比parkRatio")
    private String carPositionRatio = null;

    @ApiModelProperty("容积率")
    private Double dimension = null;

    @ApiModelProperty("电费")
    private Double electricFee = null;

    @ApiModelProperty("供电")
    private String electricSupply = null;

    @ApiModelProperty("电梯配备类型")
    private String elevator = null;

    @ApiModelProperty("电梯配备中文")
    private String hasElevator = null;

    @ApiModelProperty("供暖方式")
    private String heatingMode = null;

    @ApiModelProperty("小区buildingId")
    private Integer id = null;

    @ApiModelProperty("标签tags")
    @Valid
    private List<String> label = null;

    @ApiModelProperty("位置")
    private String location = null;

    @ApiModelProperty("停车费")
    private String parkingRate = null;

    @Valid
    @ApiModelProperty("小区照片buildingImages")
    private String[] photo = null;

    @ApiModelProperty("二手房，租房信息")
    private PlotsHousesDomain plotsHousesDomain = null;

    @ApiModelProperty("物业公司")
    private String property = null;

    @ApiModelProperty("物业费")
    private String propertyFee = null;

    @ApiModelProperty("物业类型propertyTypeId")
    private String propertyType = null;

    @ApiModelProperty("物业类型名称propertyType")
    private String propertyTypeName = null;

    @ApiModelProperty("小区名称/楼盘名称buildingName")
    private String rc = null;

    @ApiModelProperty("详情环线")
    private String ringRoadName = null;

    @ApiModelProperty("总栋数")
    private String sumBuilding = null;

    @ApiModelProperty("总户数")
    private String sumHousehold = null;

    @ApiModelProperty("水费")
    private Double waterFee = null;

    @ApiModelProperty("供水")
    private String waterSupply = null;

    @ApiModelProperty("标签名称")
    private List<String> recommendBuildTagsName = null;

    @ApiModelProperty("标签id")
    private List<Integer> recommendBuildTagsId = null;

    @ApiModelProperty("小区行情")
    private PlotMarketDomain plotMarketDomain;

    @ApiModelProperty("小区别名")
    private String rcNickname;

    @ApiModelProperty("区域ID")
    private String areaId;

    @ApiModelProperty("区域名称")
    private String area;

    @ApiModelProperty("商圈ID")
    private String tradingAreaId;

    @ApiModelProperty("商圈名称")
    private String tradingArea;

    @ApiModelProperty("上架房源")
    private Integer increaseHouse;

    @ApiModelProperty("下架房源")
    private Integer decreaseHouse;

    @ApiModelProperty(value = "是否收藏")
    private Boolean isFavorite;

    @ApiModelProperty(value = "小区标题图")
    private String titlePhoto;

//    @ApiModelProperty(value = "小区均价浮动比率")
//    private Double averagePriceFloatRatio;
//
//    @ApiModelProperty(value = "上月成交二手房数量")
//    private Integer lastMonthsDeal;
    @ApiModelProperty(value = "环比")
    private Double ringRatio;
    @ApiModelProperty(value = "地址")
    private String address;
}

