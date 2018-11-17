package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.domain.newhouse.ActivityInfoDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewHouseDetailResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class NewHouseDetailResponse   {
  @JsonProperty("activityDesc")
  private String activityDesc = null;

  @JsonProperty("activityInfo")
  @Valid
  private List<ActivityInfoDo> activityInfo = null;

  @JsonProperty("airQuality")
  private String airQuality = null;

  @JsonProperty("areaName")
  private String areaName = null;

  @JsonProperty("averagePrice")
  private Double averagePrice = null;

  @JsonProperty("buildCount")
  private Integer buildCount = null;

  @JsonProperty("buildingAddress")
  private String buildingAddress = null;

  @JsonProperty("buildingFeature")
  private String buildingFeature = null;

  @JsonProperty("buildingImg")
  @Valid
  private List<String> buildingImg = null;

  @JsonProperty("buildingLife")
  private Integer buildingLife = null;

  @JsonProperty("buildingName")
  private String buildingName = null;

  @JsonProperty("buildingNameId")
  private Integer buildingNameId = null;

  @JsonProperty("buildingNickname")
  private String buildingNickname = null;

  @JsonProperty("buildingSort")
  private Integer buildingSort = null;

  @JsonProperty("buildingTags")
  @Valid
  private List<String> buildingTags = null;

  @JsonProperty("buildingTitleImg")
  private String buildingTitleImg = null;

  @JsonProperty("buildingType")
  private String buildingType = null;

  @JsonProperty("carRentPrice")
  private Double carRentPrice = null;

  @JsonProperty("deliverTime")
  private String deliverTime = null;

  @JsonProperty("developers")
  private String developers = null;

  @JsonProperty("dimension")
  private Double dimension = null;

  @JsonProperty("districtId")
  private Integer districtId = null;

  @JsonProperty("districtName")
  private String districtName = null;

  @JsonProperty("electricSupply")
  private String electricSupply = null;

  @JsonProperty("groundArea")
  private Double groundArea = null;

  @JsonProperty("heatingType")
  private String heatingType = null;

  @JsonProperty("houseMaxArea")
  private Double houseMaxArea = null;

  @JsonProperty("houseMinArea")
  private Double houseMinArea = null;

  @JsonProperty("isActive")
  private Integer isActive = null;

  @JsonProperty("isApprove")
  private Integer isApprove = null;

  @JsonProperty("liftDoorRadio")
  private String liftDoorRadio = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("nearbysubway")
  @Valid
  private Map<String, String> nearbysubway = null;

  @JsonProperty("openedTime")
  private String openedTime = null;

  @JsonProperty("parkRadio")
  private String parkRadio = null;

  @JsonProperty("parkSpace")
  private Integer parkSpace = null;

  @JsonProperty("propertyType")
  private String propertyType = null;

  @JsonProperty("propertyfee")
  private Double propertyfee = null;

  @JsonProperty("propertymanage")
  private String propertymanage = null;

  @JsonProperty("purposeArea")
  private Double purposeArea = null;

  @JsonProperty("redecorateTypeId")
  @Valid
  private List<Integer> redecorateTypeId = null;

  @JsonProperty("ringRoadDistance")
  private Integer ringRoadDistance = null;

  @JsonProperty("ringRoadName")
  private String ringRoadName = null;

  @JsonProperty("saleAddress")
  private String saleAddress = null;

  @JsonProperty("saleStatusId")
  private Integer saleStatusId = null;

  @JsonProperty("saleStatusName")
  private String saleStatusName = null;

  @JsonProperty("saletelphone")
  private String saletelphone = null;

  @JsonProperty("sellLicence")
  @Valid
  private Map<String, String> sellLicence = null;

  @JsonProperty("subwayStationId")
  @Valid
  private List<Integer> subwayStationId = null;

  @JsonProperty("totalPrice")
  private Double totalPrice = null;

  @JsonProperty("totaldoor")
  private Integer totaldoor = null;

  @JsonProperty("virescencerate")
  private Double virescencerate = null;

  @JsonProperty("waterSupply")
  private String waterSupply = null;

  public NewHouseDetailResponse activityDesc(String activityDesc) {
    this.activityDesc = activityDesc;
    return this;
  }

  /**
   * Get activityDesc
   * @return activityDesc
  **/
  @ApiModelProperty(value = "")


  public String getActivityDesc() {
    return activityDesc;
  }

  public void setActivityDesc(String activityDesc) {
    this.activityDesc = activityDesc;
  }

  public NewHouseDetailResponse activityInfo(List<ActivityInfoDo> activityInfo) {
    this.activityInfo = activityInfo;
    return this;
  }

  public NewHouseDetailResponse addActivityInfoItem(ActivityInfoDo activityInfoItem) {
    if (this.activityInfo == null) {
      this.activityInfo = new ArrayList<ActivityInfoDo>();
    }
    this.activityInfo.add(activityInfoItem);
    return this;
  }

  /**
   * Get activityInfo
   * @return activityInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ActivityInfoDo> getActivityInfo() {
    return activityInfo;
  }

  public void setActivityInfo(List<ActivityInfoDo> activityInfo) {
    this.activityInfo = activityInfo;
  }

  public NewHouseDetailResponse airQuality(String airQuality) {
    this.airQuality = airQuality;
    return this;
  }

  /**
   * Get airQuality
   * @return airQuality
  **/
  @ApiModelProperty(value = "")


  public String getAirQuality() {
    return airQuality;
  }

  public void setAirQuality(String airQuality) {
    this.airQuality = airQuality;
  }

  public NewHouseDetailResponse areaName(String areaName) {
    this.areaName = areaName;
    return this;
  }

  /**
   * Get areaName
   * @return areaName
  **/
  @ApiModelProperty(value = "")


  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public NewHouseDetailResponse averagePrice(Double averagePrice) {
    this.averagePrice = averagePrice;
    return this;
  }

  /**
   * Get averagePrice
   * @return averagePrice
  **/
  @ApiModelProperty(value = "")


  public Double getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(Double averagePrice) {
    this.averagePrice = averagePrice;
  }

  public NewHouseDetailResponse buildCount(Integer buildCount) {
    this.buildCount = buildCount;
    return this;
  }

  /**
   * Get buildCount
   * @return buildCount
  **/
  @ApiModelProperty(value = "")


  public Integer getBuildCount() {
    return buildCount;
  }

  public void setBuildCount(Integer buildCount) {
    this.buildCount = buildCount;
  }

  public NewHouseDetailResponse buildingAddress(String buildingAddress) {
    this.buildingAddress = buildingAddress;
    return this;
  }

  /**
   * Get buildingAddress
   * @return buildingAddress
  **/
  @ApiModelProperty(value = "")


  public String getBuildingAddress() {
    return buildingAddress;
  }

  public void setBuildingAddress(String buildingAddress) {
    this.buildingAddress = buildingAddress;
  }

  public NewHouseDetailResponse buildingFeature(String buildingFeature) {
    this.buildingFeature = buildingFeature;
    return this;
  }

  /**
   * Get buildingFeature
   * @return buildingFeature
  **/
  @ApiModelProperty(value = "")


  public String getBuildingFeature() {
    return buildingFeature;
  }

  public void setBuildingFeature(String buildingFeature) {
    this.buildingFeature = buildingFeature;
  }

  public NewHouseDetailResponse buildingImg(List<String> buildingImg) {
    this.buildingImg = buildingImg;
    return this;
  }

  public NewHouseDetailResponse addBuildingImgItem(String buildingImgItem) {
    if (this.buildingImg == null) {
      this.buildingImg = new ArrayList<String>();
    }
    this.buildingImg.add(buildingImgItem);
    return this;
  }

  /**
   * Get buildingImg
   * @return buildingImg
  **/
  @ApiModelProperty(value = "")


  public List<String> getBuildingImg() {
    return buildingImg;
  }

  public void setBuildingImg(List<String> buildingImg) {
    this.buildingImg = buildingImg;
  }

  public NewHouseDetailResponse buildingLife(Integer buildingLife) {
    this.buildingLife = buildingLife;
    return this;
  }

  /**
   * Get buildingLife
   * @return buildingLife
  **/
  @ApiModelProperty(value = "")


  public Integer getBuildingLife() {
    return buildingLife;
  }

  public void setBuildingLife(Integer buildingLife) {
    this.buildingLife = buildingLife;
  }

  public NewHouseDetailResponse buildingName(String buildingName) {
    this.buildingName = buildingName;
    return this;
  }

  /**
   * Get buildingName
   * @return buildingName
  **/
  @ApiModelProperty(value = "")


  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public NewHouseDetailResponse buildingNameId(Integer buildingNameId) {
    this.buildingNameId = buildingNameId;
    return this;
  }

  /**
   * Get buildingNameId
   * @return buildingNameId
  **/
  @ApiModelProperty(value = "")


  public Integer getBuildingNameId() {
    return buildingNameId;
  }

  public void setBuildingNameId(Integer buildingNameId) {
    this.buildingNameId = buildingNameId;
  }

  public NewHouseDetailResponse buildingNickname(String buildingNickname) {
    this.buildingNickname = buildingNickname;
    return this;
  }

  /**
   * Get buildingNickname
   * @return buildingNickname
  **/
  @ApiModelProperty(value = "")


  public String getBuildingNickname() {
    return buildingNickname;
  }

  public void setBuildingNickname(String buildingNickname) {
    this.buildingNickname = buildingNickname;
  }

  public NewHouseDetailResponse buildingSort(Integer buildingSort) {
    this.buildingSort = buildingSort;
    return this;
  }

  /**
   * Get buildingSort
   * @return buildingSort
  **/
  @ApiModelProperty(value = "")


  public Integer getBuildingSort() {
    return buildingSort;
  }

  public void setBuildingSort(Integer buildingSort) {
    this.buildingSort = buildingSort;
  }

  public NewHouseDetailResponse buildingTags(List<String> buildingTags) {
    this.buildingTags = buildingTags;
    return this;
  }

  public NewHouseDetailResponse addBuildingTagsItem(String buildingTagsItem) {
    if (this.buildingTags == null) {
      this.buildingTags = new ArrayList<String>();
    }
    this.buildingTags.add(buildingTagsItem);
    return this;
  }

  /**
   * Get buildingTags
   * @return buildingTags
  **/
  @ApiModelProperty(value = "")


  public List<String> getBuildingTags() {
    return buildingTags;
  }

  public void setBuildingTags(List<String> buildingTags) {
    this.buildingTags = buildingTags;
  }

  public NewHouseDetailResponse buildingTitleImg(String buildingTitleImg) {
    this.buildingTitleImg = buildingTitleImg;
    return this;
  }

  /**
   * Get buildingTitleImg
   * @return buildingTitleImg
  **/
  @ApiModelProperty(value = "")


  public String getBuildingTitleImg() {
    return buildingTitleImg;
  }

  public void setBuildingTitleImg(String buildingTitleImg) {
    this.buildingTitleImg = buildingTitleImg;
  }

  public NewHouseDetailResponse buildingType(String buildingType) {
    this.buildingType = buildingType;
    return this;
  }

  /**
   * Get buildingType
   * @return buildingType
  **/
  @ApiModelProperty(value = "")


  public String getBuildingType() {
    return buildingType;
  }

  public void setBuildingType(String buildingType) {
    this.buildingType = buildingType;
  }

  public NewHouseDetailResponse carRentPrice(Double carRentPrice) {
    this.carRentPrice = carRentPrice;
    return this;
  }

  /**
   * Get carRentPrice
   * @return carRentPrice
  **/
  @ApiModelProperty(value = "")


  public Double getCarRentPrice() {
    return carRentPrice;
  }

  public void setCarRentPrice(Double carRentPrice) {
    this.carRentPrice = carRentPrice;
  }

  public NewHouseDetailResponse deliverTime(String deliverTime) {
    this.deliverTime = deliverTime;
    return this;
  }

  /**
   * Get deliverTime
   * @return deliverTime
  **/
  @ApiModelProperty(value = "")


  public String getDeliverTime() {
    return deliverTime;
  }

  public void setDeliverTime(String deliverTime) {
    this.deliverTime = deliverTime;
  }

  public NewHouseDetailResponse developers(String developers) {
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

  public NewHouseDetailResponse dimension(Double dimension) {
    this.dimension = dimension;
    return this;
  }

  /**
   * Get dimension
   * @return dimension
  **/
  @ApiModelProperty(value = "")


  public Double getDimension() {
    return dimension;
  }

  public void setDimension(Double dimension) {
    this.dimension = dimension;
  }

  public NewHouseDetailResponse districtId(Integer districtId) {
    this.districtId = districtId;
    return this;
  }

  /**
   * Get districtId
   * @return districtId
  **/
  @ApiModelProperty(value = "")


  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public NewHouseDetailResponse districtName(String districtName) {
    this.districtName = districtName;
    return this;
  }

  /**
   * Get districtName
   * @return districtName
  **/
  @ApiModelProperty(value = "")


  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public NewHouseDetailResponse electricSupply(String electricSupply) {
    this.electricSupply = electricSupply;
    return this;
  }

  /**
   * Get electricSupply
   * @return electricSupply
  **/
  @ApiModelProperty(value = "")


  public String getElectricSupply() {
    return electricSupply;
  }

  public void setElectricSupply(String electricSupply) {
    this.electricSupply = electricSupply;
  }

  public NewHouseDetailResponse groundArea(Double groundArea) {
    this.groundArea = groundArea;
    return this;
  }

  /**
   * Get groundArea
   * @return groundArea
  **/
  @ApiModelProperty(value = "")


  public Double getGroundArea() {
    return groundArea;
  }

  public void setGroundArea(Double groundArea) {
    this.groundArea = groundArea;
  }

  public NewHouseDetailResponse heatingType(String heatingType) {
    this.heatingType = heatingType;
    return this;
  }

  /**
   * Get heatingType
   * @return heatingType
  **/
  @ApiModelProperty(value = "")


  public String getHeatingType() {
    return heatingType;
  }

  public void setHeatingType(String heatingType) {
    this.heatingType = heatingType;
  }

  public NewHouseDetailResponse houseMaxArea(Double houseMaxArea) {
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

  public NewHouseDetailResponse houseMinArea(Double houseMinArea) {
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

  public NewHouseDetailResponse isActive(Integer isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
  **/
  @ApiModelProperty(value = "")


  public Integer getIsActive() {
    return isActive;
  }

  public void setIsActive(Integer isActive) {
    this.isActive = isActive;
  }

  public NewHouseDetailResponse isApprove(Integer isApprove) {
    this.isApprove = isApprove;
    return this;
  }

  /**
   * Get isApprove
   * @return isApprove
  **/
  @ApiModelProperty(value = "")


  public Integer getIsApprove() {
    return isApprove;
  }

  public void setIsApprove(Integer isApprove) {
    this.isApprove = isApprove;
  }

  public NewHouseDetailResponse liftDoorRadio(String liftDoorRadio) {
    this.liftDoorRadio = liftDoorRadio;
    return this;
  }

  /**
   * Get liftDoorRadio
   * @return liftDoorRadio
  **/
  @ApiModelProperty(value = "")


  public String getLiftDoorRadio() {
    return liftDoorRadio;
  }

  public void setLiftDoorRadio(String liftDoorRadio) {
    this.liftDoorRadio = liftDoorRadio;
  }

  public NewHouseDetailResponse location(String location) {
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

  public NewHouseDetailResponse nearbysubway(Map<String, String> nearbysubway) {
    this.nearbysubway = nearbysubway;
    return this;
  }

  public NewHouseDetailResponse putNearbysubwayItem(String key, String nearbysubwayItem) {
    if (this.nearbysubway == null) {
      this.nearbysubway = new HashMap<String, String>();
    }
    this.nearbysubway.put(key, nearbysubwayItem);
    return this;
  }

  /**
   * Get nearbysubway
   * @return nearbysubway
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getNearbysubway() {
    return nearbysubway;
  }

  public void setNearbysubway(Map<String, String> nearbysubway) {
    this.nearbysubway = nearbysubway;
  }

  public NewHouseDetailResponse openedTime(String openedTime) {
    this.openedTime = openedTime;
    return this;
  }

  /**
   * Get openedTime
   * @return openedTime
  **/
  @ApiModelProperty(value = "")


  public String getOpenedTime() {
    return openedTime;
  }

  public void setOpenedTime(String openedTime) {
    this.openedTime = openedTime;
  }

  public NewHouseDetailResponse parkRadio(String parkRadio) {
    this.parkRadio = parkRadio;
    return this;
  }

  /**
   * Get parkRadio
   * @return parkRadio
  **/
  @ApiModelProperty(value = "")


  public String getParkRadio() {
    return parkRadio;
  }

  public void setParkRadio(String parkRadio) {
    this.parkRadio = parkRadio;
  }

  public NewHouseDetailResponse parkSpace(Integer parkSpace) {
    this.parkSpace = parkSpace;
    return this;
  }

  /**
   * Get parkSpace
   * @return parkSpace
  **/
  @ApiModelProperty(value = "")


  public Integer getParkSpace() {
    return parkSpace;
  }

  public void setParkSpace(Integer parkSpace) {
    this.parkSpace = parkSpace;
  }

  public NewHouseDetailResponse propertyType(String propertyType) {
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

  public NewHouseDetailResponse propertyfee(Double propertyfee) {
    this.propertyfee = propertyfee;
    return this;
  }

  /**
   * Get propertyfee
   * @return propertyfee
  **/
  @ApiModelProperty(value = "")


  public Double getPropertyfee() {
    return propertyfee;
  }

  public void setPropertyfee(Double propertyfee) {
    this.propertyfee = propertyfee;
  }

  public NewHouseDetailResponse propertymanage(String propertymanage) {
    this.propertymanage = propertymanage;
    return this;
  }

  /**
   * Get propertymanage
   * @return propertymanage
  **/
  @ApiModelProperty(value = "")


  public String getPropertymanage() {
    return propertymanage;
  }

  public void setPropertymanage(String propertymanage) {
    this.propertymanage = propertymanage;
  }

  public NewHouseDetailResponse purposeArea(Double purposeArea) {
    this.purposeArea = purposeArea;
    return this;
  }

  /**
   * Get purposeArea
   * @return purposeArea
  **/
  @ApiModelProperty(value = "")


  public Double getPurposeArea() {
    return purposeArea;
  }

  public void setPurposeArea(Double purposeArea) {
    this.purposeArea = purposeArea;
  }

  public NewHouseDetailResponse redecorateTypeId(List<Integer> redecorateTypeId) {
    this.redecorateTypeId = redecorateTypeId;
    return this;
  }

  public NewHouseDetailResponse addRedecorateTypeIdItem(Integer redecorateTypeIdItem) {
    if (this.redecorateTypeId == null) {
      this.redecorateTypeId = new ArrayList<Integer>();
    }
    this.redecorateTypeId.add(redecorateTypeIdItem);
    return this;
  }

  /**
   * Get redecorateTypeId
   * @return redecorateTypeId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getRedecorateTypeId() {
    return redecorateTypeId;
  }

  public void setRedecorateTypeId(List<Integer> redecorateTypeId) {
    this.redecorateTypeId = redecorateTypeId;
  }

  public NewHouseDetailResponse ringRoadDistance(Integer ringRoadDistance) {
    this.ringRoadDistance = ringRoadDistance;
    return this;
  }

  /**
   * Get ringRoadDistance
   * @return ringRoadDistance
  **/
  @ApiModelProperty(value = "")


  public Integer getRingRoadDistance() {
    return ringRoadDistance;
  }

  public void setRingRoadDistance(Integer ringRoadDistance) {
    this.ringRoadDistance = ringRoadDistance;
  }

  public NewHouseDetailResponse ringRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
    return this;
  }

  /**
   * Get ringRoadName
   * @return ringRoadName
  **/
  @ApiModelProperty(value = "")


  public String getRingRoadName() {
    return ringRoadName;
  }

  public void setRingRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
  }

  public NewHouseDetailResponse saleAddress(String saleAddress) {
    this.saleAddress = saleAddress;
    return this;
  }

  /**
   * Get saleAddress
   * @return saleAddress
  **/
  @ApiModelProperty(value = "")


  public String getSaleAddress() {
    return saleAddress;
  }

  public void setSaleAddress(String saleAddress) {
    this.saleAddress = saleAddress;
  }

  public NewHouseDetailResponse saleStatusId(Integer saleStatusId) {
    this.saleStatusId = saleStatusId;
    return this;
  }

  /**
   * Get saleStatusId
   * @return saleStatusId
  **/
  @ApiModelProperty(value = "")


  public Integer getSaleStatusId() {
    return saleStatusId;
  }

  public void setSaleStatusId(Integer saleStatusId) {
    this.saleStatusId = saleStatusId;
  }

  public NewHouseDetailResponse saleStatusName(String saleStatusName) {
    this.saleStatusName = saleStatusName;
    return this;
  }

  /**
   * Get saleStatusName
   * @return saleStatusName
  **/
  @ApiModelProperty(value = "")


  public String getSaleStatusName() {
    return saleStatusName;
  }

  public void setSaleStatusName(String saleStatusName) {
    this.saleStatusName = saleStatusName;
  }

  public NewHouseDetailResponse saletelphone(String saletelphone) {
    this.saletelphone = saletelphone;
    return this;
  }

  /**
   * Get saletelphone
   * @return saletelphone
  **/
  @ApiModelProperty(value = "")


  public String getSaletelphone() {
    return saletelphone;
  }

  public void setSaletelphone(String saletelphone) {
    this.saletelphone = saletelphone;
  }

  public NewHouseDetailResponse sellLicence(Map<String, String> sellLicence) {
    this.sellLicence = sellLicence;
    return this;
  }

  public NewHouseDetailResponse putSellLicenceItem(String key, String sellLicenceItem) {
    if (this.sellLicence == null) {
      this.sellLicence = new HashMap<String, String>();
    }
    this.sellLicence.put(key, sellLicenceItem);
    return this;
  }

  /**
   * Get sellLicence
   * @return sellLicence
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getSellLicence() {
    return sellLicence;
  }

  public void setSellLicence(Map<String, String> sellLicence) {
    this.sellLicence = sellLicence;
  }

  public NewHouseDetailResponse subwayStationId(List<Integer> subwayStationId) {
    this.subwayStationId = subwayStationId;
    return this;
  }

  public NewHouseDetailResponse addSubwayStationIdItem(Integer subwayStationIdItem) {
    if (this.subwayStationId == null) {
      this.subwayStationId = new ArrayList<Integer>();
    }
    this.subwayStationId.add(subwayStationIdItem);
    return this;
  }

  /**
   * Get subwayStationId
   * @return subwayStationId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getSubwayStationId() {
    return subwayStationId;
  }

  public void setSubwayStationId(List<Integer> subwayStationId) {
    this.subwayStationId = subwayStationId;
  }

  public NewHouseDetailResponse totalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  **/
  @ApiModelProperty(value = "")


  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public NewHouseDetailResponse totaldoor(Integer totaldoor) {
    this.totaldoor = totaldoor;
    return this;
  }

  /**
   * Get totaldoor
   * @return totaldoor
  **/
  @ApiModelProperty(value = "")


  public Integer getTotaldoor() {
    return totaldoor;
  }

  public void setTotaldoor(Integer totaldoor) {
    this.totaldoor = totaldoor;
  }

  public NewHouseDetailResponse virescencerate(Double virescencerate) {
    this.virescencerate = virescencerate;
    return this;
  }

  /**
   * Get virescencerate
   * @return virescencerate
  **/
  @ApiModelProperty(value = "")


  public Double getVirescencerate() {
    return virescencerate;
  }

  public void setVirescencerate(Double virescencerate) {
    this.virescencerate = virescencerate;
  }

  public NewHouseDetailResponse waterSupply(String waterSupply) {
    this.waterSupply = waterSupply;
    return this;
  }

  /**
   * Get waterSupply
   * @return waterSupply
  **/
  @ApiModelProperty(value = "")


  public String getWaterSupply() {
    return waterSupply;
  }

  public void setWaterSupply(String waterSupply) {
    this.waterSupply = waterSupply;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewHouseDetailResponse newHouseDetailResponse = (NewHouseDetailResponse) o;
    return Objects.equals(this.activityDesc, newHouseDetailResponse.activityDesc) &&
        Objects.equals(this.activityInfo, newHouseDetailResponse.activityInfo) &&
        Objects.equals(this.airQuality, newHouseDetailResponse.airQuality) &&
        Objects.equals(this.areaName, newHouseDetailResponse.areaName) &&
        Objects.equals(this.averagePrice, newHouseDetailResponse.averagePrice) &&
        Objects.equals(this.buildCount, newHouseDetailResponse.buildCount) &&
        Objects.equals(this.buildingAddress, newHouseDetailResponse.buildingAddress) &&
        Objects.equals(this.buildingFeature, newHouseDetailResponse.buildingFeature) &&
        Objects.equals(this.buildingImg, newHouseDetailResponse.buildingImg) &&
        Objects.equals(this.buildingLife, newHouseDetailResponse.buildingLife) &&
        Objects.equals(this.buildingName, newHouseDetailResponse.buildingName) &&
        Objects.equals(this.buildingNameId, newHouseDetailResponse.buildingNameId) &&
        Objects.equals(this.buildingNickname, newHouseDetailResponse.buildingNickname) &&
        Objects.equals(this.buildingSort, newHouseDetailResponse.buildingSort) &&
        Objects.equals(this.buildingTags, newHouseDetailResponse.buildingTags) &&
        Objects.equals(this.buildingTitleImg, newHouseDetailResponse.buildingTitleImg) &&
        Objects.equals(this.buildingType, newHouseDetailResponse.buildingType) &&
        Objects.equals(this.carRentPrice, newHouseDetailResponse.carRentPrice) &&
        Objects.equals(this.deliverTime, newHouseDetailResponse.deliverTime) &&
        Objects.equals(this.developers, newHouseDetailResponse.developers) &&
        Objects.equals(this.dimension, newHouseDetailResponse.dimension) &&
        Objects.equals(this.districtId, newHouseDetailResponse.districtId) &&
        Objects.equals(this.districtName, newHouseDetailResponse.districtName) &&
        Objects.equals(this.electricSupply, newHouseDetailResponse.electricSupply) &&
        Objects.equals(this.groundArea, newHouseDetailResponse.groundArea) &&
        Objects.equals(this.heatingType, newHouseDetailResponse.heatingType) &&
        Objects.equals(this.houseMaxArea, newHouseDetailResponse.houseMaxArea) &&
        Objects.equals(this.houseMinArea, newHouseDetailResponse.houseMinArea) &&
        Objects.equals(this.isActive, newHouseDetailResponse.isActive) &&
        Objects.equals(this.isApprove, newHouseDetailResponse.isApprove) &&
        Objects.equals(this.liftDoorRadio, newHouseDetailResponse.liftDoorRadio) &&
        Objects.equals(this.location, newHouseDetailResponse.location) &&
        Objects.equals(this.nearbysubway, newHouseDetailResponse.nearbysubway) &&
        Objects.equals(this.openedTime, newHouseDetailResponse.openedTime) &&
        Objects.equals(this.parkRadio, newHouseDetailResponse.parkRadio) &&
        Objects.equals(this.parkSpace, newHouseDetailResponse.parkSpace) &&
        Objects.equals(this.propertyType, newHouseDetailResponse.propertyType) &&
        Objects.equals(this.propertyfee, newHouseDetailResponse.propertyfee) &&
        Objects.equals(this.propertymanage, newHouseDetailResponse.propertymanage) &&
        Objects.equals(this.purposeArea, newHouseDetailResponse.purposeArea) &&
        Objects.equals(this.redecorateTypeId, newHouseDetailResponse.redecorateTypeId) &&
        Objects.equals(this.ringRoadDistance, newHouseDetailResponse.ringRoadDistance) &&
        Objects.equals(this.ringRoadName, newHouseDetailResponse.ringRoadName) &&
        Objects.equals(this.saleAddress, newHouseDetailResponse.saleAddress) &&
        Objects.equals(this.saleStatusId, newHouseDetailResponse.saleStatusId) &&
        Objects.equals(this.saleStatusName, newHouseDetailResponse.saleStatusName) &&
        Objects.equals(this.saletelphone, newHouseDetailResponse.saletelphone) &&
        Objects.equals(this.sellLicence, newHouseDetailResponse.sellLicence) &&
        Objects.equals(this.subwayStationId, newHouseDetailResponse.subwayStationId) &&
        Objects.equals(this.totalPrice, newHouseDetailResponse.totalPrice) &&
        Objects.equals(this.totaldoor, newHouseDetailResponse.totaldoor) &&
        Objects.equals(this.virescencerate, newHouseDetailResponse.virescencerate) &&
        Objects.equals(this.waterSupply, newHouseDetailResponse.waterSupply);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activityDesc, activityInfo, airQuality, areaName, averagePrice, buildCount, buildingAddress, buildingFeature, buildingImg, buildingLife, buildingName, buildingNameId, buildingNickname, buildingSort, buildingTags, buildingTitleImg, buildingType, carRentPrice, deliverTime, developers, dimension, districtId, districtName, electricSupply, groundArea, heatingType, houseMaxArea, houseMinArea, isActive, isApprove, liftDoorRadio, location, nearbysubway, openedTime, parkRadio, parkSpace, propertyType, propertyfee, propertymanage, purposeArea, redecorateTypeId, ringRoadDistance, ringRoadName, saleAddress, saleStatusId, saleStatusName, saletelphone, sellLicence, subwayStationId, totalPrice, totaldoor, virescencerate, waterSupply);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewHouseDetailResponse {\n");
    
    sb.append("    activityDesc: ").append(toIndentedString(activityDesc)).append("\n");
    sb.append("    activityInfo: ").append(toIndentedString(activityInfo)).append("\n");
    sb.append("    airQuality: ").append(toIndentedString(airQuality)).append("\n");
    sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
    sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
    sb.append("    buildCount: ").append(toIndentedString(buildCount)).append("\n");
    sb.append("    buildingAddress: ").append(toIndentedString(buildingAddress)).append("\n");
    sb.append("    buildingFeature: ").append(toIndentedString(buildingFeature)).append("\n");
    sb.append("    buildingImg: ").append(toIndentedString(buildingImg)).append("\n");
    sb.append("    buildingLife: ").append(toIndentedString(buildingLife)).append("\n");
    sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
    sb.append("    buildingNameId: ").append(toIndentedString(buildingNameId)).append("\n");
    sb.append("    buildingNickname: ").append(toIndentedString(buildingNickname)).append("\n");
    sb.append("    buildingSort: ").append(toIndentedString(buildingSort)).append("\n");
    sb.append("    buildingTags: ").append(toIndentedString(buildingTags)).append("\n");
    sb.append("    buildingTitleImg: ").append(toIndentedString(buildingTitleImg)).append("\n");
    sb.append("    buildingType: ").append(toIndentedString(buildingType)).append("\n");
    sb.append("    carRentPrice: ").append(toIndentedString(carRentPrice)).append("\n");
    sb.append("    deliverTime: ").append(toIndentedString(deliverTime)).append("\n");
    sb.append("    developers: ").append(toIndentedString(developers)).append("\n");
    sb.append("    dimension: ").append(toIndentedString(dimension)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    electricSupply: ").append(toIndentedString(electricSupply)).append("\n");
    sb.append("    groundArea: ").append(toIndentedString(groundArea)).append("\n");
    sb.append("    heatingType: ").append(toIndentedString(heatingType)).append("\n");
    sb.append("    houseMaxArea: ").append(toIndentedString(houseMaxArea)).append("\n");
    sb.append("    houseMinArea: ").append(toIndentedString(houseMinArea)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    isApprove: ").append(toIndentedString(isApprove)).append("\n");
    sb.append("    liftDoorRadio: ").append(toIndentedString(liftDoorRadio)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    nearbysubway: ").append(toIndentedString(nearbysubway)).append("\n");
    sb.append("    openedTime: ").append(toIndentedString(openedTime)).append("\n");
    sb.append("    parkRadio: ").append(toIndentedString(parkRadio)).append("\n");
    sb.append("    parkSpace: ").append(toIndentedString(parkSpace)).append("\n");
    sb.append("    propertyType: ").append(toIndentedString(propertyType)).append("\n");
    sb.append("    propertyfee: ").append(toIndentedString(propertyfee)).append("\n");
    sb.append("    propertymanage: ").append(toIndentedString(propertymanage)).append("\n");
    sb.append("    purposeArea: ").append(toIndentedString(purposeArea)).append("\n");
    sb.append("    redecorateTypeId: ").append(toIndentedString(redecorateTypeId)).append("\n");
    sb.append("    ringRoadDistance: ").append(toIndentedString(ringRoadDistance)).append("\n");
    sb.append("    ringRoadName: ").append(toIndentedString(ringRoadName)).append("\n");
    sb.append("    saleAddress: ").append(toIndentedString(saleAddress)).append("\n");
    sb.append("    saleStatusId: ").append(toIndentedString(saleStatusId)).append("\n");
    sb.append("    saleStatusName: ").append(toIndentedString(saleStatusName)).append("\n");
    sb.append("    saletelphone: ").append(toIndentedString(saletelphone)).append("\n");
    sb.append("    sellLicence: ").append(toIndentedString(sellLicence)).append("\n");
    sb.append("    subwayStationId: ").append(toIndentedString(subwayStationId)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    totaldoor: ").append(toIndentedString(totaldoor)).append("\n");
    sb.append("    virescencerate: ").append(toIndentedString(virescencerate)).append("\n");
    sb.append("    waterSupply: ").append(toIndentedString(waterSupply)).append("\n");
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

