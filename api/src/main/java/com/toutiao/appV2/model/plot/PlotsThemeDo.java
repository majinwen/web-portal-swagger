package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotsThemeDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotsThemeDo   {
  @JsonProperty("abbreviatedAge")
  @ApiModelProperty("建成年代")
  private String abbreviatedAge = null;

  @JsonProperty("address")
  @ApiModelProperty("地址")
  private String address = null;

  @JsonProperty("age")
  @ApiModelProperty("楼龄")
  private Integer age = null;

  @JsonProperty("area")
  @ApiModelProperty("区域")
  private String area = null;

  @JsonProperty("areaId")
  @ApiModelProperty("区域编号")
  private String areaId = null;

  @JsonProperty("avgPrice")
  @ApiModelProperty("均价")
  private Double avgPrice = null;

  @JsonProperty("buildingAreaSize")
  @ApiModelProperty("均价")
  private Double buildingAreaSize = null;

  @JsonProperty("developers")
  @ApiModelProperty("开发商")
  private String developers = null;

  @JsonProperty("houseCount")
  @ApiModelProperty("在售房源套数")
  private Integer houseCount = null;

  @JsonProperty("houseMaxArea")
  @ApiModelProperty("最大面积")
  private Double houseMaxArea = null;

  @JsonProperty("houseMinArea")
  @ApiModelProperty("最小面积")
  private Double houseMinArea = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("isTop")
  @ApiModelProperty("是否top50小区")
  private Integer isTop = null;

  @JsonProperty("location")
  @ApiModelProperty("坐标")
  private String location = null;

  @JsonProperty("lowestPrice")
  @ApiModelProperty("最低价")
  private Double lowestPrice = null;

  @JsonProperty("metroStation")
  @Valid
  @ApiModelProperty("地铁站")
  private List<String> metroStation = null;

  @JsonProperty("metroStationId")
  @Valid
  @ApiModelProperty("地铁站编号")
  private List<String> metroStationId = null;

  @JsonProperty("metroWithPlotsDistance")
  @ApiModelProperty("地铁站与小区的距离")
  private Object metroWithPlotsDistance = null;

  @JsonProperty("nearestPark")
  @ApiModelProperty("距离最近的大型公园")
  private String nearestPark = null;

  @JsonProperty("nearestParkDistance")
  @ApiModelProperty("距最近公园的距离")
  private Double nearestParkDistance = null;

  @JsonProperty("photo")
  @Valid
  @ApiModelProperty("小区照片")
  private List<String> photo = null;

  @JsonProperty("rankAvgInBizcircle")
  @ApiModelProperty("在商圈中排名")
  private Integer rankAvgInBizcircle = null;

  @JsonProperty("rc")
  @ApiModelProperty("小区名称")
  private String rc = null;

  @JsonProperty("recommendBuildTagsId")
  @Valid
  @ApiModelProperty("小区推荐标签Id")
  private List<Integer> recommendBuildTagsId = null;

  @JsonProperty("recommendBuildTagsName")
  @Valid
  @ApiModelProperty("小区推荐标签名称")
  private List<String> recommendBuildTagsName = null;

  @JsonProperty("sumPrice")
  @ApiModelProperty("总价")
  private Double sumPrice = null;

  @JsonProperty("tradingArea")
  @ApiModelProperty("商圈")
  private String tradingArea = null;

  @JsonProperty("tradingAreaId")
  @ApiModelProperty("商圈编号")
  private String tradingAreaId = null;

  @JsonProperty("trafficInformation")
  @ApiModelProperty("近地铁描述")
  private String trafficInformation = null;

  public PlotsThemeDo abbreviatedAge(String abbreviatedAge) {
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

  public PlotsThemeDo address(String address) {
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

  public PlotsThemeDo age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * @return age
  **/
  @ApiModelProperty(value = "")


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public PlotsThemeDo area(String area) {
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

  public PlotsThemeDo areaId(String areaId) {
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

  public PlotsThemeDo avgPrice(Double avgPrice) {
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

  public PlotsThemeDo buildingAreaSize(Double buildingAreaSize) {
    this.buildingAreaSize = buildingAreaSize;
    return this;
  }

  /**
   * Get buildingAreaSize
   * @return buildingAreaSize
  **/
  @ApiModelProperty(value = "")


  public Double getBuildingAreaSize() {
    return buildingAreaSize;
  }

  public void setBuildingAreaSize(Double buildingAreaSize) {
    this.buildingAreaSize = buildingAreaSize;
  }

  public PlotsThemeDo developers(String developers) {
    this.developers = developers;
    return this;
  }

  /**
   * Get developers
   * @return developers
  **/
  @ApiModelProperty(value = "")


  public String getDevelopers() {
    return developers;
  }

  public void setDevelopers(String developers) {
    this.developers = developers;
  }

  public PlotsThemeDo houseCount(Integer houseCount) {
    this.houseCount = houseCount;
    return this;
  }

  /**
   * Get houseCount
   * @return houseCount
  **/
  @ApiModelProperty(value = "")


  public Integer getHouseCount() {
    return houseCount;
  }

  public void setHouseCount(Integer houseCount) {
    this.houseCount = houseCount;
  }

  public PlotsThemeDo houseMaxArea(Double houseMaxArea) {
    this.houseMaxArea = houseMaxArea;
    return this;
  }

  /**
   * Get houseMaxArea
   * @return houseMaxArea
  **/
  @ApiModelProperty(value = "")


  public Double getHouseMaxArea() {
    return houseMaxArea;
  }

  public void setHouseMaxArea(Double houseMaxArea) {
    this.houseMaxArea = houseMaxArea;
  }

  public PlotsThemeDo houseMinArea(Double houseMinArea) {
    this.houseMinArea = houseMinArea;
    return this;
  }

  /**
   * Get houseMinArea
   * @return houseMinArea
  **/
  @ApiModelProperty(value = "")


  public Double getHouseMinArea() {
    return houseMinArea;
  }

  public void setHouseMinArea(Double houseMinArea) {
    this.houseMinArea = houseMinArea;
  }

  public PlotsThemeDo id(Integer id) {
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

  public PlotsThemeDo isTop(Integer isTop) {
    this.isTop = isTop;
    return this;
  }

  /**
   * Get isTop
   * @return isTop
  **/
  @ApiModelProperty(value = "")


  public Integer getIsTop() {
    return isTop;
  }

  public void setIsTop(Integer isTop) {
    this.isTop = isTop;
  }

  public PlotsThemeDo location(String location) {
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

  public PlotsThemeDo lowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
    return this;
  }

  /**
   * Get lowestPrice
   * @return lowestPrice
  **/
  @ApiModelProperty(value = "")


  public Double getLowestPrice() {
    return lowestPrice;
  }

  public void setLowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
  }

  public PlotsThemeDo metroStation(List<String> metroStation) {
    this.metroStation = metroStation;
    return this;
  }

  public PlotsThemeDo addMetroStationItem(String metroStationItem) {
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

  public PlotsThemeDo metroStationId(List<String> metroStationId) {
    this.metroStationId = metroStationId;
    return this;
  }

  public PlotsThemeDo addMetroStationIdItem(String metroStationIdItem) {
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

  public PlotsThemeDo metroWithPlotsDistance(Object metroWithPlotsDistance) {
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

  public PlotsThemeDo nearestPark(String nearestPark) {
    this.nearestPark = nearestPark;
    return this;
  }

  /**
   * Get nearestPark
   * @return nearestPark
  **/
  @ApiModelProperty(value = "")


  public String getNearestPark() {
    return nearestPark;
  }

  public void setNearestPark(String nearestPark) {
    this.nearestPark = nearestPark;
  }

  public PlotsThemeDo nearestParkDistance(Double nearestParkDistance) {
    this.nearestParkDistance = nearestParkDistance;
    return this;
  }

  /**
   * Get nearestParkDistance
   * @return nearestParkDistance
  **/
  @ApiModelProperty(value = "")


  public Double getNearestParkDistance() {
    return nearestParkDistance;
  }

  public void setNearestParkDistance(Double nearestParkDistance) {
    this.nearestParkDistance = nearestParkDistance;
  }

  public PlotsThemeDo photo(List<String> photo) {
    this.photo = photo;
    return this;
  }

  public PlotsThemeDo addPhotoItem(String photoItem) {
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

  public PlotsThemeDo rankAvgInBizcircle(Integer rankAvgInBizcircle) {
    this.rankAvgInBizcircle = rankAvgInBizcircle;
    return this;
  }

  /**
   * Get rankAvgInBizcircle
   * @return rankAvgInBizcircle
  **/
  @ApiModelProperty(value = "")


  public Integer getRankAvgInBizcircle() {
    return rankAvgInBizcircle;
  }

  public void setRankAvgInBizcircle(Integer rankAvgInBizcircle) {
    this.rankAvgInBizcircle = rankAvgInBizcircle;
  }

  public PlotsThemeDo rc(String rc) {
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

  public PlotsThemeDo recommendBuildTagsId(List<Integer> recommendBuildTagsId) {
    this.recommendBuildTagsId = recommendBuildTagsId;
    return this;
  }

  public PlotsThemeDo addRecommendBuildTagsIdItem(Integer recommendBuildTagsIdItem) {
    if (this.recommendBuildTagsId == null) {
      this.recommendBuildTagsId = new ArrayList<Integer>();
    }
    this.recommendBuildTagsId.add(recommendBuildTagsIdItem);
    return this;
  }

  /**
   * Get recommendBuildTagsId
   * @return recommendBuildTagsId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getRecommendBuildTagsId() {
    return recommendBuildTagsId;
  }

  public void setRecommendBuildTagsId(List<Integer> recommendBuildTagsId) {
    this.recommendBuildTagsId = recommendBuildTagsId;
  }

  public PlotsThemeDo recommendBuildTagsName(List<String> recommendBuildTagsName) {
    this.recommendBuildTagsName = recommendBuildTagsName;
    return this;
  }

  public PlotsThemeDo addRecommendBuildTagsNameItem(String recommendBuildTagsNameItem) {
    if (this.recommendBuildTagsName == null) {
      this.recommendBuildTagsName = new ArrayList<String>();
    }
    this.recommendBuildTagsName.add(recommendBuildTagsNameItem);
    return this;
  }

  /**
   * Get recommendBuildTagsName
   * @return recommendBuildTagsName
  **/
  @ApiModelProperty(value = "")


  public List<String> getRecommendBuildTagsName() {
    return recommendBuildTagsName;
  }

  public void setRecommendBuildTagsName(List<String> recommendBuildTagsName) {
    this.recommendBuildTagsName = recommendBuildTagsName;
  }

  public PlotsThemeDo sumPrice(Double sumPrice) {
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

  public PlotsThemeDo tradingArea(String tradingArea) {
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

  public PlotsThemeDo tradingAreaId(String tradingAreaId) {
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

  public PlotsThemeDo trafficInformation(String trafficInformation) {
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
    PlotsThemeDo plotsThemeDo = (PlotsThemeDo) o;
    return Objects.equals(this.abbreviatedAge, plotsThemeDo.abbreviatedAge) &&
        Objects.equals(this.address, plotsThemeDo.address) &&
        Objects.equals(this.age, plotsThemeDo.age) &&
        Objects.equals(this.area, plotsThemeDo.area) &&
        Objects.equals(this.areaId, plotsThemeDo.areaId) &&
        Objects.equals(this.avgPrice, plotsThemeDo.avgPrice) &&
        Objects.equals(this.buildingAreaSize, plotsThemeDo.buildingAreaSize) &&
        Objects.equals(this.developers, plotsThemeDo.developers) &&
        Objects.equals(this.houseCount, plotsThemeDo.houseCount) &&
        Objects.equals(this.houseMaxArea, plotsThemeDo.houseMaxArea) &&
        Objects.equals(this.houseMinArea, plotsThemeDo.houseMinArea) &&
        Objects.equals(this.id, plotsThemeDo.id) &&
        Objects.equals(this.isTop, plotsThemeDo.isTop) &&
        Objects.equals(this.location, plotsThemeDo.location) &&
        Objects.equals(this.lowestPrice, plotsThemeDo.lowestPrice) &&
        Objects.equals(this.metroStation, plotsThemeDo.metroStation) &&
        Objects.equals(this.metroStationId, plotsThemeDo.metroStationId) &&
        Objects.equals(this.metroWithPlotsDistance, plotsThemeDo.metroWithPlotsDistance) &&
        Objects.equals(this.nearestPark, plotsThemeDo.nearestPark) &&
        Objects.equals(this.nearestParkDistance, plotsThemeDo.nearestParkDistance) &&
        Objects.equals(this.photo, plotsThemeDo.photo) &&
        Objects.equals(this.rankAvgInBizcircle, plotsThemeDo.rankAvgInBizcircle) &&
        Objects.equals(this.rc, plotsThemeDo.rc) &&
        Objects.equals(this.recommendBuildTagsId, plotsThemeDo.recommendBuildTagsId) &&
        Objects.equals(this.recommendBuildTagsName, plotsThemeDo.recommendBuildTagsName) &&
        Objects.equals(this.sumPrice, plotsThemeDo.sumPrice) &&
        Objects.equals(this.tradingArea, plotsThemeDo.tradingArea) &&
        Objects.equals(this.tradingAreaId, plotsThemeDo.tradingAreaId) &&
        Objects.equals(this.trafficInformation, plotsThemeDo.trafficInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abbreviatedAge, address, age, area, areaId, avgPrice, buildingAreaSize, developers, houseCount, houseMaxArea, houseMinArea, id, isTop, location, lowestPrice, metroStation, metroStationId, metroWithPlotsDistance, nearestPark, nearestParkDistance, photo, rankAvgInBizcircle, rc, recommendBuildTagsId, recommendBuildTagsName, sumPrice, tradingArea, tradingAreaId, trafficInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotsThemeDo {\n");
    
    sb.append("    abbreviatedAge: ").append(toIndentedString(abbreviatedAge)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    buildingAreaSize: ").append(toIndentedString(buildingAreaSize)).append("\n");
    sb.append("    developers: ").append(toIndentedString(developers)).append("\n");
    sb.append("    houseCount: ").append(toIndentedString(houseCount)).append("\n");
    sb.append("    houseMaxArea: ").append(toIndentedString(houseMaxArea)).append("\n");
    sb.append("    houseMinArea: ").append(toIndentedString(houseMinArea)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isTop: ").append(toIndentedString(isTop)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    lowestPrice: ").append(toIndentedString(lowestPrice)).append("\n");
    sb.append("    metroStation: ").append(toIndentedString(metroStation)).append("\n");
    sb.append("    metroStationId: ").append(toIndentedString(metroStationId)).append("\n");
    sb.append("    metroWithPlotsDistance: ").append(toIndentedString(metroWithPlotsDistance)).append("\n");
    sb.append("    nearestPark: ").append(toIndentedString(nearestPark)).append("\n");
    sb.append("    nearestParkDistance: ").append(toIndentedString(nearestParkDistance)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    rankAvgInBizcircle: ").append(toIndentedString(rankAvgInBizcircle)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
    sb.append("    recommendBuildTagsId: ").append(toIndentedString(recommendBuildTagsId)).append("\n");
    sb.append("    recommendBuildTagsName: ").append(toIndentedString(recommendBuildTagsName)).append("\n");
    sb.append("    sumPrice: ").append(toIndentedString(sumPrice)).append("\n");
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

