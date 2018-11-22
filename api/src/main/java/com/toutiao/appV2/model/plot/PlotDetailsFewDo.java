package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
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

  public PlotDetailsFewDo abbreviatedAge(String abbreviatedAge) {
    this.abbreviatedAge = abbreviatedAge;
    return this;
  }

  /**
   * Get abbreviatedAge
   * @return abbreviatedAge
  **/
  @ApiModelProperty(value = "")


  public String getAbbreviatedAge() {
    return abbreviatedAge;
  }

  public void setAbbreviatedAge(String abbreviatedAge) {
    this.abbreviatedAge = abbreviatedAge;
  }

  public PlotDetailsFewDo address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PlotDetailsFewDo alias(String alias) {
    this.alias = alias;
    return this;
  }

  /**
   * Get alias
   * @return alias
  **/
  @ApiModelProperty(value = "")


  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public PlotDetailsFewDo area(String area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  **/
  @ApiModelProperty(value = "")


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public PlotDetailsFewDo areaId(String areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * Get areaId
   * @return areaId
  **/
  @ApiModelProperty(value = "")


  public String getAreaId() {
    return areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  public PlotDetailsFewDo avgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
    return this;
  }

  /**
   * Get avgPrice
   * @return avgPrice
  **/
  @ApiModelProperty(value = "")


  public Double getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
  }

  public PlotDetailsFewDo carPositionRatio(String carPositionRatio) {
    this.carPositionRatio = carPositionRatio;
    return this;
  }

  /**
   * Get carPositionRatio
   * @return carPositionRatio
  **/
  @ApiModelProperty(value = "")


  public String getCarPositionRatio() {
    return carPositionRatio;
  }

  public void setCarPositionRatio(String carPositionRatio) {
    this.carPositionRatio = carPositionRatio;
  }

  public PlotDetailsFewDo id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PlotDetailsFewDo label(List<String> label) {
    this.label = label;
    return this;
  }

  public PlotDetailsFewDo addLabelItem(String labelItem) {
    if (this.label == null) {
      this.label = new ArrayList<String>();
    }
    this.label.add(labelItem);
    return this;
  }

  /**
   * Get label
   * @return label
  **/
  @ApiModelProperty(value = "")


  public List<String> getLabel() {
    return label;
  }

  public void setLabel(List<String> label) {
    this.label = label;
  }

  public PlotDetailsFewDo labelId(List<String> labelId) {
    this.labelId = labelId;
    return this;
  }

  public PlotDetailsFewDo addLabelIdItem(String labelIdItem) {
    if (this.labelId == null) {
      this.labelId = new ArrayList<String>();
    }
    this.labelId.add(labelIdItem);
    return this;
  }

  /**
   * Get labelId
   * @return labelId
  **/
  @ApiModelProperty(value = "")


  public List<String> getLabelId() {
    return labelId;
  }

  public void setLabelId(List<String> labelId) {
    this.labelId = labelId;
  }

  public PlotDetailsFewDo location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public PlotDetailsFewDo metroStation(List<String> metroStation) {
    this.metroStation = metroStation;
    return this;
  }

  public PlotDetailsFewDo addMetroStationItem(String metroStationItem) {
    if (this.metroStation == null) {
      this.metroStation = new ArrayList<String>();
    }
    this.metroStation.add(metroStationItem);
    return this;
  }

  /**
   * Get metroStation
   * @return metroStation
  **/
  @ApiModelProperty(value = "")


  public List<String> getMetroStation() {
    return metroStation;
  }

  public void setMetroStation(List<String> metroStation) {
    this.metroStation = metroStation;
  }

  public PlotDetailsFewDo metroStationId(List<String> metroStationId) {
    this.metroStationId = metroStationId;
    return this;
  }

  public PlotDetailsFewDo addMetroStationIdItem(String metroStationIdItem) {
    if (this.metroStationId == null) {
      this.metroStationId = new ArrayList<String>();
    }
    this.metroStationId.add(metroStationIdItem);
    return this;
  }

  /**
   * Get metroStationId
   * @return metroStationId
  **/
  @ApiModelProperty(value = "")


  public List<String> getMetroStationId() {
    return metroStationId;
  }

  public void setMetroStationId(List<String> metroStationId) {
    this.metroStationId = metroStationId;
  }

  public PlotDetailsFewDo metroWithPlotsDistance(Object metroWithPlotsDistance) {
    this.metroWithPlotsDistance = metroWithPlotsDistance;
    return this;
  }

  /**
   * Get metroWithPlotsDistance
   * @return metroWithPlotsDistance
  **/
  @ApiModelProperty(value = "")


  public Object getMetroWithPlotsDistance() {
    return metroWithPlotsDistance;
  }

  public void setMetroWithPlotsDistance(Object metroWithPlotsDistance) {
    this.metroWithPlotsDistance = metroWithPlotsDistance;
  }

  public PlotDetailsFewDo photo(List<String> photo) {
    this.photo = photo;
    return this;
  }

  public PlotDetailsFewDo addPhotoItem(String photoItem) {
    if (this.photo == null) {
      this.photo = new ArrayList<String>();
    }
    this.photo.add(photoItem);
    return this;
  }

  /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(value = "")


  public List<String> getPhoto() {
    return photo;
  }

  public void setPhoto(List<String> photo) {
    this.photo = photo;
  }

  public PlotDetailsFewDo propertyType(String propertyType) {
    this.propertyType = propertyType;
    return this;
  }

  /**
   * Get propertyType
   * @return propertyType
  **/
  @ApiModelProperty(value = "")


  public String getPropertyType() {
    return propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  public PlotDetailsFewDo propertyTypeName(String propertyTypeName) {
    this.propertyTypeName = propertyTypeName;
    return this;
  }

  /**
   * Get propertyTypeName
   * @return propertyTypeName
  **/
  @ApiModelProperty(value = "")


  public String getPropertyTypeName() {
    return propertyTypeName;
  }

  public void setPropertyTypeName(String propertyTypeName) {
    this.propertyTypeName = propertyTypeName;
  }

  public PlotDetailsFewDo rc(String rc) {
    this.rc = rc;
    return this;
  }

  /**
   * Get rc
   * @return rc
  **/
  @ApiModelProperty(value = "")


  public String getRc() {
    return rc;
  }

  public void setRc(String rc) {
    this.rc = rc;
  }

  public PlotDetailsFewDo rentTotalNum(Integer rentTotalNum) {
    this.rentTotalNum = rentTotalNum;
    return this;
  }

  /**
   * Get rentTotalNum
   * @return rentTotalNum
  **/
  @ApiModelProperty(value = "")


  public Integer getRentTotalNum() {
    return rentTotalNum;
  }

  public void setRentTotalNum(Integer rentTotalNum) {
    this.rentTotalNum = rentTotalNum;
  }

  public PlotDetailsFewDo saleHouse(List<String> saleHouse) {
    this.saleHouse = saleHouse;
    return this;
  }

  public PlotDetailsFewDo addSaleHouseItem(String saleHouseItem) {
    if (this.saleHouse == null) {
      this.saleHouse = new ArrayList<String>();
    }
    this.saleHouse.add(saleHouseItem);
    return this;
  }

  /**
   * Get saleHouse
   * @return saleHouse
  **/
  @ApiModelProperty(value = "")


  public List<String> getSaleHouse() {
    return saleHouse;
  }

  public void setSaleHouse(List<String> saleHouse) {
    this.saleHouse = saleHouse;
  }

  public PlotDetailsFewDo sellHouseTotalNum(Integer sellHouseTotalNum) {
    this.sellHouseTotalNum = sellHouseTotalNum;
    return this;
  }

  /**
   * Get sellHouseTotalNum
   * @return sellHouseTotalNum
  **/
  @ApiModelProperty(value = "")


  public Integer getSellHouseTotalNum() {
    return sellHouseTotalNum;
  }

  public void setSellHouseTotalNum(Integer sellHouseTotalNum) {
    this.sellHouseTotalNum = sellHouseTotalNum;
  }

  public PlotDetailsFewDo subwayDistanceInfo(String subwayDistanceInfo) {
    this.subwayDistanceInfo = subwayDistanceInfo;
    return this;
  }

  /**
   * Get subwayDistanceInfo
   * @return subwayDistanceInfo
  **/
  @ApiModelProperty(value = "")


  public String getSubwayDistanceInfo() {
    return subwayDistanceInfo;
  }

  public void setSubwayDistanceInfo(String subwayDistanceInfo) {
    this.subwayDistanceInfo = subwayDistanceInfo;
  }

  public PlotDetailsFewDo subwayLine(List<String> subwayLine) {
    this.subwayLine = subwayLine;
    return this;
  }

  public PlotDetailsFewDo addSubwayLineItem(String subwayLineItem) {
    if (this.subwayLine == null) {
      this.subwayLine = new ArrayList<String>();
    }
    this.subwayLine.add(subwayLineItem);
    return this;
  }

  /**
   * Get subwayLine
   * @return subwayLine
  **/
  @ApiModelProperty(value = "")


  public List<String> getSubwayLine() {
    return subwayLine;
  }

  public void setSubwayLine(List<String> subwayLine) {
    this.subwayLine = subwayLine;
  }

  public PlotDetailsFewDo subwayLineId(List<String> subwayLineId) {
    this.subwayLineId = subwayLineId;
    return this;
  }

  public PlotDetailsFewDo addSubwayLineIdItem(String subwayLineIdItem) {
    if (this.subwayLineId == null) {
      this.subwayLineId = new ArrayList<String>();
    }
    this.subwayLineId.add(subwayLineIdItem);
    return this;
  }

  /**
   * Get subwayLineId
   * @return subwayLineId
  **/
  @ApiModelProperty(value = "")


  public List<String> getSubwayLineId() {
    return subwayLineId;
  }

  public void setSubwayLineId(List<String> subwayLineId) {
    this.subwayLineId = subwayLineId;
  }

  public PlotDetailsFewDo sumPrice(Double sumPrice) {
    this.sumPrice = sumPrice;
    return this;
  }

  /**
   * Get sumPrice
   * @return sumPrice
  **/
  @ApiModelProperty(value = "")


  public Double getSumPrice() {
    return sumPrice;
  }

  public void setSumPrice(Double sumPrice) {
    this.sumPrice = sumPrice;
  }

  public PlotDetailsFewDo totalNum(Integer totalNum) {
    this.totalNum = totalNum;
    return this;
  }

  /**
   * Get totalNum
   * @return totalNum
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public PlotDetailsFewDo tradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
    return this;
  }

  /**
   * Get tradingArea
   * @return tradingArea
  **/
  @ApiModelProperty(value = "")


  public String getTradingArea() {
    return tradingArea;
  }

  public void setTradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
  }

  public PlotDetailsFewDo tradingAreaId(String tradingAreaId) {
    this.tradingAreaId = tradingAreaId;
    return this;
  }

  /**
   * Get tradingAreaId
   * @return tradingAreaId
  **/
  @ApiModelProperty(value = "")


  public String getTradingAreaId() {
    return tradingAreaId;
  }

  public void setTradingAreaId(String tradingAreaId) {
    this.tradingAreaId = tradingAreaId;
  }

  public PlotDetailsFewDo trafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
    return this;
  }

  /**
   * Get trafficInformation
   * @return trafficInformation
  **/
  @ApiModelProperty(value = "")


  public String getTrafficInformation() {
    return trafficInformation;
  }

  public void setTrafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotDetailsFewDo plotDetailsFewDo = (PlotDetailsFewDo) o;
    return Objects.equals(this.abbreviatedAge, plotDetailsFewDo.abbreviatedAge) &&
        Objects.equals(this.address, plotDetailsFewDo.address) &&
        Objects.equals(this.alias, plotDetailsFewDo.alias) &&
        Objects.equals(this.area, plotDetailsFewDo.area) &&
        Objects.equals(this.areaId, plotDetailsFewDo.areaId) &&
        Objects.equals(this.avgPrice, plotDetailsFewDo.avgPrice) &&
        Objects.equals(this.carPositionRatio, plotDetailsFewDo.carPositionRatio) &&
        Objects.equals(this.id, plotDetailsFewDo.id) &&
        Objects.equals(this.label, plotDetailsFewDo.label) &&
        Objects.equals(this.labelId, plotDetailsFewDo.labelId) &&
        Objects.equals(this.location, plotDetailsFewDo.location) &&
        Objects.equals(this.metroStation, plotDetailsFewDo.metroStation) &&
        Objects.equals(this.metroStationId, plotDetailsFewDo.metroStationId) &&
        Objects.equals(this.metroWithPlotsDistance, plotDetailsFewDo.metroWithPlotsDistance) &&
        Objects.equals(this.photo, plotDetailsFewDo.photo) &&
        Objects.equals(this.propertyType, plotDetailsFewDo.propertyType) &&
        Objects.equals(this.propertyTypeName, plotDetailsFewDo.propertyTypeName) &&
        Objects.equals(this.rc, plotDetailsFewDo.rc) &&
        Objects.equals(this.rentTotalNum, plotDetailsFewDo.rentTotalNum) &&
        Objects.equals(this.saleHouse, plotDetailsFewDo.saleHouse) &&
        Objects.equals(this.sellHouseTotalNum, plotDetailsFewDo.sellHouseTotalNum) &&
        Objects.equals(this.subwayDistanceInfo, plotDetailsFewDo.subwayDistanceInfo) &&
        Objects.equals(this.subwayLine, plotDetailsFewDo.subwayLine) &&
        Objects.equals(this.subwayLineId, plotDetailsFewDo.subwayLineId) &&
        Objects.equals(this.sumPrice, plotDetailsFewDo.sumPrice) &&
        Objects.equals(this.totalNum, plotDetailsFewDo.totalNum) &&
        Objects.equals(this.tradingArea, plotDetailsFewDo.tradingArea) &&
        Objects.equals(this.tradingAreaId, plotDetailsFewDo.tradingAreaId) &&
        Objects.equals(this.trafficInformation, plotDetailsFewDo.trafficInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abbreviatedAge, address, alias, area, areaId, avgPrice, carPositionRatio, id, label, labelId, location, metroStation, metroStationId, metroWithPlotsDistance, photo, propertyType, propertyTypeName, rc, rentTotalNum, saleHouse, sellHouseTotalNum, subwayDistanceInfo, subwayLine, subwayLineId, sumPrice, totalNum, tradingArea, tradingAreaId, trafficInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotDetailsFewDo {\n");
    
    sb.append("    abbreviatedAge: ").append(toIndentedString(abbreviatedAge)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    carPositionRatio: ").append(toIndentedString(carPositionRatio)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    metroStation: ").append(toIndentedString(metroStation)).append("\n");
    sb.append("    metroStationId: ").append(toIndentedString(metroStationId)).append("\n");
    sb.append("    metroWithPlotsDistance: ").append(toIndentedString(metroWithPlotsDistance)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    propertyType: ").append(toIndentedString(propertyType)).append("\n");
    sb.append("    propertyTypeName: ").append(toIndentedString(propertyTypeName)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
    sb.append("    rentTotalNum: ").append(toIndentedString(rentTotalNum)).append("\n");
    sb.append("    saleHouse: ").append(toIndentedString(saleHouse)).append("\n");
    sb.append("    sellHouseTotalNum: ").append(toIndentedString(sellHouseTotalNum)).append("\n");
    sb.append("    subwayDistanceInfo: ").append(toIndentedString(subwayDistanceInfo)).append("\n");
    sb.append("    subwayLine: ").append(toIndentedString(subwayLine)).append("\n");
    sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
    sb.append("    sumPrice: ").append(toIndentedString(sumPrice)).append("\n");
    sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
    sb.append("    tradingArea: ").append(toIndentedString(tradingArea)).append("\n");
    sb.append("    tradingAreaId: ").append(toIndentedString(tradingAreaId)).append("\n");
    sb.append("    trafficInformation: ").append(toIndentedString(trafficInformation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

