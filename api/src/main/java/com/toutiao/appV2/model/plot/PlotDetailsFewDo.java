package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotDetailsFewDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")
@Data
public class PlotDetailsFewDo   {
  @JsonProperty("abbreviatedAge")
  @ApiModelProperty("建成年代")
  private String abbreviatedAge = null;

  @JsonProperty("address")
  @ApiModelProperty("地址")
  private String address = null;

  @JsonProperty("alias")
  @ApiModelProperty("别名")
  private String alias = null;

  @ApiModelProperty("区域")
  @JsonProperty("area")
  private String area = null;

  @JsonProperty("areaId")
  @ApiModelProperty("商圈编号")
  private String areaId = null;

  @JsonProperty("avgPrice")
  @ApiModelProperty("均价")
  private Double avgPrice = null;

  @JsonProperty("carPositionRatio")
  @ApiModelProperty("车位配比")
  private String carPositionRatio = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("label")
  @Valid
  @ApiModelProperty("标签")
  private List<String> label = null;

  @JsonProperty("labelId")
  @Valid
  @ApiModelProperty("标签编号")
  //@ChangeName("tagsId")
  private List<String> labelId = null;

  @JsonProperty("location")
  @ApiModelProperty("坐标")
  private String location = null;


  @ApiModelProperty("地铁站")
  @JsonProperty("metroStation")
  @Valid
  private List<String> metroStation = null;

  @JsonProperty("metroStationId")
  @Valid
  @ApiModelProperty("地铁站编号")
  private List<String> metroStationId = null;

  @JsonProperty("metroWithPlotsDistance")
  @ApiModelProperty("地铁站与小区的距离")
  private Object metroWithPlotsDistance = null;

  @JsonProperty("photo")
  @Valid
  @ApiModelProperty("小区照片")
  private List<String> photo = null;

  @JsonProperty("propertyType")
  @ApiModelProperty("物业类型")
  private String propertyType = null;

  @JsonProperty("propertyTypeName")
  @ApiModelProperty("物业类型名称")
  private String propertyTypeName = null;

  @JsonProperty("rc")
  @ApiModelProperty("小区名称/楼盘名称")
  private String rc = null;

  @JsonProperty("rentTotalNum")
  @ApiModelProperty("小区下租房总数")
  private Integer rentTotalNum = null;

  @JsonProperty("saleHouse")
  @ApiModelProperty("待售房源")
  @Valid
  private List<String> saleHouse = null;

  @ApiModelProperty("小区下二手房总数")
  @JsonProperty("sellHouseTotalNum")
  private Integer sellHouseTotalNum = null;

  @ApiModelProperty("房源与地铁站的距离")
  @JsonProperty("subwayDistanceInfo")
  private String subwayDistanceInfo = null;

  @ApiModelProperty("地铁线路")
  @JsonProperty("subwayLine")
  @Valid
  private List<String> subwayLine = null;

  @ApiModelProperty("地铁线路编号")
  @JsonProperty("subwayLineId")
  @Valid
  private List<String> subwayLineId = null;

  @ApiModelProperty("总价")
  @JsonProperty("sumPrice")
  private Double sumPrice = null;

  @ApiModelProperty("小区总个数")
  @JsonProperty("totalNum")
  private Integer totalNum = null;

  @ApiModelProperty("商圈")
  @JsonProperty("tradingArea")
  private String tradingArea = null;

  @ApiModelProperty("商圈编号")
  @JsonProperty("tradingAreaId")
  private String tradingAreaId = null;

  @ApiModelProperty("交通信息")
  @JsonProperty("trafficInformation")
  private String trafficInformation = null;

  @ApiModelProperty("标题图")
  @JsonProperty("titlePhoto")
  private String titlePhoto;

  @ApiModelProperty("附近距离XX千米")
  @JsonProperty("nearbyDistance")
  private String nearbyDistance;

  /**
   * 建筑类型
   */
  @ApiModelProperty("建筑类型")
  @JsonProperty("buildingStructure")
  private String buildingStructure;

  public PlotDetailsFewDo abbreviatedAge(String abbreviatedAge) {
    this.abbreviatedAge = abbreviatedAge;
    return this;
  }
}

