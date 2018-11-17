package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentDetailResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

public class RentDetailResponse   {
  @JsonProperty("agentBaseDo")
  private AgentBaseDo agentBaseDo = null;

  @JsonProperty("agentBusinessCard")
  private String agentBusinessCard = null;

  @JsonProperty("areaName")
  private String areaName = null;

  @JsonProperty("balcony")
  private Integer balcony = null;

  @JsonProperty("companyCard")
  private String companyCard = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("dataSourceName")
  private String dataSourceName = null;

  @JsonProperty("dataSourceSign")
  private Integer dataSourceSign = null;

  @JsonProperty("demand")
  private String demand = null;

  @JsonProperty("districtName")
  private String districtName = null;

  @JsonProperty("floor")
  private Integer floor = null;

  @JsonProperty("floorLevel")
  private String floorLevel = null;

  @JsonProperty("forward")
  private String forward = null;

  @JsonProperty("hall")
  private Integer hall = null;

  @JsonProperty("hasLift")
  private String hasLift = null;

  @JsonProperty("houseArea")
  private Double houseArea = null;

  @JsonProperty("houseDesc")
  private String houseDesc = null;

  @JsonProperty("houseId")
  private String houseId = null;

  @JsonProperty("housePhoto")
  @Valid
  private List<String> housePhoto = null;

  @JsonProperty("houseTitle")
  private String houseTitle = null;

  @JsonProperty("houseTitleImg")
  private String houseTitleImg = null;

  @JsonProperty("importTime")
  private String importTime = null;

  @JsonProperty("isDefaultImage")
  private Integer isDefaultImage = null;

  @JsonProperty("isFavorite")
  private Boolean isFavorite = null;

  @JsonProperty("isRecommend")
  private Integer isRecommend = null;

  @JsonProperty("kitchen")
  private Integer kitchen = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("nearestSubway")
  private String nearestSubway = null;

  @JsonProperty("payModeName")
  private String payModeName = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("publishTime")
  private String publishTime = null;

  @JsonProperty("rentHousePrice")
  private Double rentHousePrice = null;

  @JsonProperty("rentHouseTagsName")
  @Valid
  private List<String> rentHouseTagsName = null;

  @JsonProperty("rentHouseType")
  private Integer rentHouseType = null;

  @JsonProperty("rentSign")
  private Integer rentSign = null;

  @JsonProperty("rentSignName")
  private String rentSignName = null;

  @JsonProperty("rentType")
  private Integer rentType = null;

  @JsonProperty("rentTypeName")
  private String rentTypeName = null;

  @JsonProperty("room")
  private String room = null;

  @JsonProperty("supportingFacilities")
  @Valid
  private List<String> supportingFacilities = null;

  @JsonProperty("toilet")
  private Integer toilet = null;

  @JsonProperty("totalFloor")
  private Integer totalFloor = null;

  @JsonProperty("upStringTime")
  private String upStringTime = null;

  @JsonProperty("zufangId")
  private Integer zufangId = null;

  @JsonProperty("zufangName")
  private String zufangName = null;

  public RentDetailResponse agentBaseDo(AgentBaseDo agentBaseDo) {
    this.agentBaseDo = agentBaseDo;
    return this;
  }

  /**
   * Get agentBaseDo
   * @return agentBaseDo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AgentBaseDo getAgentBaseDo() {
    return agentBaseDo;
  }

  public void setAgentBaseDo(AgentBaseDo agentBaseDo) {
    this.agentBaseDo = agentBaseDo;
  }

  public RentDetailResponse agentBusinessCard(String agentBusinessCard) {
    this.agentBusinessCard = agentBusinessCard;
    return this;
  }

  /**
   * Get agentBusinessCard
   * @return agentBusinessCard
  **/
  @ApiModelProperty(value = "")


  public String getAgentBusinessCard() {
    return agentBusinessCard;
  }

  public void setAgentBusinessCard(String agentBusinessCard) {
    this.agentBusinessCard = agentBusinessCard;
  }

  public RentDetailResponse areaName(String areaName) {
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

  public RentDetailResponse balcony(Integer balcony) {
    this.balcony = balcony;
    return this;
  }

  /**
   * Get balcony
   * @return balcony
  **/
  @ApiModelProperty(value = "")


  public Integer getBalcony() {
    return balcony;
  }

  public void setBalcony(Integer balcony) {
    this.balcony = balcony;
  }

  public RentDetailResponse companyCard(String companyCard) {
    this.companyCard = companyCard;
    return this;
  }

  /**
   * Get companyCard
   * @return companyCard
  **/
  @ApiModelProperty(value = "")


  public String getCompanyCard() {
    return companyCard;
  }

  public void setCompanyCard(String companyCard) {
    this.companyCard = companyCard;
  }

  public RentDetailResponse createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public RentDetailResponse dataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
    return this;
  }

  /**
   * Get dataSourceName
   * @return dataSourceName
  **/
  @ApiModelProperty(value = "")


  public String getDataSourceName() {
    return dataSourceName;
  }

  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }

  public RentDetailResponse dataSourceSign(Integer dataSourceSign) {
    this.dataSourceSign = dataSourceSign;
    return this;
  }

  /**
   * Get dataSourceSign
   * @return dataSourceSign
  **/
  @ApiModelProperty(value = "")


  public Integer getDataSourceSign() {
    return dataSourceSign;
  }

  public void setDataSourceSign(Integer dataSourceSign) {
    this.dataSourceSign = dataSourceSign;
  }

  public RentDetailResponse demand(String demand) {
    this.demand = demand;
    return this;
  }

  /**
   * Get demand
   * @return demand
  **/
  @ApiModelProperty(value = "")


  public String getDemand() {
    return demand;
  }

  public void setDemand(String demand) {
    this.demand = demand;
  }

  public RentDetailResponse districtName(String districtName) {
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

  public RentDetailResponse floor(Integer floor) {
    this.floor = floor;
    return this;
  }

  /**
   * Get floor
   * @return floor
  **/
  @ApiModelProperty(value = "")


  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public RentDetailResponse floorLevel(String floorLevel) {
    this.floorLevel = floorLevel;
    return this;
  }

  /**
   * Get floorLevel
   * @return floorLevel
  **/
  @ApiModelProperty(value = "")


  public String getFloorLevel() {
    return floorLevel;
  }

  public void setFloorLevel(String floorLevel) {
    this.floorLevel = floorLevel;
  }

  public RentDetailResponse forward(String forward) {
    this.forward = forward;
    return this;
  }

  /**
   * Get forward
   * @return forward
  **/
  @ApiModelProperty(value = "")


  public String getForward() {
    return forward;
  }

  public void setForward(String forward) {
    this.forward = forward;
  }

  public RentDetailResponse hall(Integer hall) {
    this.hall = hall;
    return this;
  }

  /**
   * Get hall
   * @return hall
  **/
  @ApiModelProperty(value = "")


  public Integer getHall() {
    return hall;
  }

  public void setHall(Integer hall) {
    this.hall = hall;
  }

  public RentDetailResponse hasLift(String hasLift) {
    this.hasLift = hasLift;
    return this;
  }

  /**
   * Get hasLift
   * @return hasLift
  **/
  @ApiModelProperty(value = "")


  public String getHasLift() {
    return hasLift;
  }

  public void setHasLift(String hasLift) {
    this.hasLift = hasLift;
  }

  public RentDetailResponse houseArea(Double houseArea) {
    this.houseArea = houseArea;
    return this;
  }

  /**
   * Get houseArea
   * @return houseArea
  **/
  @ApiModelProperty(value = "")


  public Double getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(Double houseArea) {
    this.houseArea = houseArea;
  }

  public RentDetailResponse houseDesc(String houseDesc) {
    this.houseDesc = houseDesc;
    return this;
  }

  /**
   * Get houseDesc
   * @return houseDesc
  **/
  @ApiModelProperty(value = "")


  public String getHouseDesc() {
    return houseDesc;
  }

  public void setHouseDesc(String houseDesc) {
    this.houseDesc = houseDesc;
  }

  public RentDetailResponse houseId(String houseId) {
    this.houseId = houseId;
    return this;
  }

  /**
   * Get houseId
   * @return houseId
  **/
  @ApiModelProperty(value = "")


  public String getHouseId() {
    return houseId;
  }

  public void setHouseId(String houseId) {
    this.houseId = houseId;
  }

  public RentDetailResponse housePhoto(List<String> housePhoto) {
    this.housePhoto = housePhoto;
    return this;
  }

  public RentDetailResponse addHousePhotoItem(String housePhotoItem) {
    if (this.housePhoto == null) {
      this.housePhoto = new ArrayList<String>();
    }
    this.housePhoto.add(housePhotoItem);
    return this;
  }

  /**
   * Get housePhoto
   * @return housePhoto
  **/
  @ApiModelProperty(value = "")


  public List<String> getHousePhoto() {
    return housePhoto;
  }

  public void setHousePhoto(List<String> housePhoto) {
    this.housePhoto = housePhoto;
  }

  public RentDetailResponse houseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
    return this;
  }

  /**
   * Get houseTitle
   * @return houseTitle
  **/
  @ApiModelProperty(value = "")


  public String getHouseTitle() {
    return houseTitle;
  }

  public void setHouseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
  }

  public RentDetailResponse houseTitleImg(String houseTitleImg) {
    this.houseTitleImg = houseTitleImg;
    return this;
  }

  /**
   * Get houseTitleImg
   * @return houseTitleImg
  **/
  @ApiModelProperty(value = "")


  public String getHouseTitleImg() {
    return houseTitleImg;
  }

  public void setHouseTitleImg(String houseTitleImg) {
    this.houseTitleImg = houseTitleImg;
  }

  public RentDetailResponse importTime(String importTime) {
    this.importTime = importTime;
    return this;
  }

  /**
   * Get importTime
   * @return importTime
  **/
  @ApiModelProperty(value = "")


  public String getImportTime() {
    return importTime;
  }

  public void setImportTime(String importTime) {
    this.importTime = importTime;
  }

  public RentDetailResponse isDefaultImage(Integer isDefaultImage) {
    this.isDefaultImage = isDefaultImage;
    return this;
  }

  /**
   * Get isDefaultImage
   * @return isDefaultImage
  **/
  @ApiModelProperty(value = "")


  public Integer getIsDefaultImage() {
    return isDefaultImage;
  }

  public void setIsDefaultImage(Integer isDefaultImage) {
    this.isDefaultImage = isDefaultImage;
  }

  public RentDetailResponse isFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
    return this;
  }

  /**
   * Get isFavorite
   * @return isFavorite
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsFavorite() {
    return isFavorite;
  }

  public void setIsFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public RentDetailResponse isRecommend(Integer isRecommend) {
    this.isRecommend = isRecommend;
    return this;
  }

  /**
   * Get isRecommend
   * @return isRecommend
  **/
  @ApiModelProperty(value = "")


  public Integer getIsRecommend() {
    return isRecommend;
  }

  public void setIsRecommend(Integer isRecommend) {
    this.isRecommend = isRecommend;
  }

  public RentDetailResponse kitchen(Integer kitchen) {
    this.kitchen = kitchen;
    return this;
  }

  /**
   * Get kitchen
   * @return kitchen
  **/
  @ApiModelProperty(value = "")


  public Integer getKitchen() {
    return kitchen;
  }

  public void setKitchen(Integer kitchen) {
    this.kitchen = kitchen;
  }

  public RentDetailResponse location(String location) {
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

  public RentDetailResponse nearestSubway(String nearestSubway) {
    this.nearestSubway = nearestSubway;
    return this;
  }

  /**
   * Get nearestSubway
   * @return nearestSubway
  **/
  @ApiModelProperty(value = "")


  public String getNearestSubway() {
    return nearestSubway;
  }

  public void setNearestSubway(String nearestSubway) {
    this.nearestSubway = nearestSubway;
  }

  public RentDetailResponse payModeName(String payModeName) {
    this.payModeName = payModeName;
    return this;
  }

  /**
   * Get payModeName
   * @return payModeName
  **/
  @ApiModelProperty(value = "")


  public String getPayModeName() {
    return payModeName;
  }

  public void setPayModeName(String payModeName) {
    this.payModeName = payModeName;
  }

  public RentDetailResponse phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  **/
  @ApiModelProperty(value = "")


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public RentDetailResponse publishTime(String publishTime) {
    this.publishTime = publishTime;
    return this;
  }

  /**
   * Get publishTime
   * @return publishTime
  **/
  @ApiModelProperty(value = "")


  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public RentDetailResponse rentHousePrice(Double rentHousePrice) {
    this.rentHousePrice = rentHousePrice;
    return this;
  }

  /**
   * Get rentHousePrice
   * @return rentHousePrice
  **/
  @ApiModelProperty(value = "")


  public Double getRentHousePrice() {
    return rentHousePrice;
  }

  public void setRentHousePrice(Double rentHousePrice) {
    this.rentHousePrice = rentHousePrice;
  }

  public RentDetailResponse rentHouseTagsName(List<String> rentHouseTagsName) {
    this.rentHouseTagsName = rentHouseTagsName;
    return this;
  }

  public RentDetailResponse addRentHouseTagsNameItem(String rentHouseTagsNameItem) {
    if (this.rentHouseTagsName == null) {
      this.rentHouseTagsName = new ArrayList<String>();
    }
    this.rentHouseTagsName.add(rentHouseTagsNameItem);
    return this;
  }

  /**
   * Get rentHouseTagsName
   * @return rentHouseTagsName
  **/
  @ApiModelProperty(value = "")


  public List<String> getRentHouseTagsName() {
    return rentHouseTagsName;
  }

  public void setRentHouseTagsName(List<String> rentHouseTagsName) {
    this.rentHouseTagsName = rentHouseTagsName;
  }

  public RentDetailResponse rentHouseType(Integer rentHouseType) {
    this.rentHouseType = rentHouseType;
    return this;
  }

  /**
   * Get rentHouseType
   * @return rentHouseType
  **/
  @ApiModelProperty(value = "")


  public Integer getRentHouseType() {
    return rentHouseType;
  }

  public void setRentHouseType(Integer rentHouseType) {
    this.rentHouseType = rentHouseType;
  }

  public RentDetailResponse rentSign(Integer rentSign) {
    this.rentSign = rentSign;
    return this;
  }

  /**
   * Get rentSign
   * @return rentSign
  **/
  @ApiModelProperty(value = "")


  public Integer getRentSign() {
    return rentSign;
  }

  public void setRentSign(Integer rentSign) {
    this.rentSign = rentSign;
  }

  public RentDetailResponse rentSignName(String rentSignName) {
    this.rentSignName = rentSignName;
    return this;
  }

  /**
   * Get rentSignName
   * @return rentSignName
  **/
  @ApiModelProperty(value = "")


  public String getRentSignName() {
    return rentSignName;
  }

  public void setRentSignName(String rentSignName) {
    this.rentSignName = rentSignName;
  }

  public RentDetailResponse rentType(Integer rentType) {
    this.rentType = rentType;
    return this;
  }

  /**
   * Get rentType
   * @return rentType
  **/
  @ApiModelProperty(value = "")


  public Integer getRentType() {
    return rentType;
  }

  public void setRentType(Integer rentType) {
    this.rentType = rentType;
  }

  public RentDetailResponse rentTypeName(String rentTypeName) {
    this.rentTypeName = rentTypeName;
    return this;
  }

  /**
   * Get rentTypeName
   * @return rentTypeName
  **/
  @ApiModelProperty(value = "")


  public String getRentTypeName() {
    return rentTypeName;
  }

  public void setRentTypeName(String rentTypeName) {
    this.rentTypeName = rentTypeName;
  }

  public RentDetailResponse room(String room) {
    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
  **/
  @ApiModelProperty(value = "")


  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public RentDetailResponse supportingFacilities(List<String> supportingFacilities) {
    this.supportingFacilities = supportingFacilities;
    return this;
  }

  public RentDetailResponse addSupportingFacilitiesItem(String supportingFacilitiesItem) {
    if (this.supportingFacilities == null) {
      this.supportingFacilities = new ArrayList<String>();
    }
    this.supportingFacilities.add(supportingFacilitiesItem);
    return this;
  }

  /**
   * Get supportingFacilities
   * @return supportingFacilities
  **/
  @ApiModelProperty(value = "")


  public List<String> getSupportingFacilities() {
    return supportingFacilities;
  }

  public void setSupportingFacilities(List<String> supportingFacilities) {
    this.supportingFacilities = supportingFacilities;
  }

  public RentDetailResponse toilet(Integer toilet) {
    this.toilet = toilet;
    return this;
  }

  /**
   * Get toilet
   * @return toilet
  **/
  @ApiModelProperty(value = "")


  public Integer getToilet() {
    return toilet;
  }

  public void setToilet(Integer toilet) {
    this.toilet = toilet;
  }

  public RentDetailResponse totalFloor(Integer totalFloor) {
    this.totalFloor = totalFloor;
    return this;
  }

  /**
   * Get totalFloor
   * @return totalFloor
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalFloor() {
    return totalFloor;
  }

  public void setTotalFloor(Integer totalFloor) {
    this.totalFloor = totalFloor;
  }

  public RentDetailResponse upStringTime(String upStringTime) {
    this.upStringTime = upStringTime;
    return this;
  }

  /**
   * Get upStringTime
   * @return upStringTime
  **/
  @ApiModelProperty(value = "")


  public String getUpStringTime() {
    return upStringTime;
  }

  public void setUpStringTime(String upStringTime) {
    this.upStringTime = upStringTime;
  }

  public RentDetailResponse zufangId(Integer zufangId) {
    this.zufangId = zufangId;
    return this;
  }

  /**
   * Get zufangId
   * @return zufangId
  **/
  @ApiModelProperty(value = "")


  public Integer getZufangId() {
    return zufangId;
  }

  public void setZufangId(Integer zufangId) {
    this.zufangId = zufangId;
  }

  public RentDetailResponse zufangName(String zufangName) {
    this.zufangName = zufangName;
    return this;
  }

  /**
   * Get zufangName
   * @return zufangName
  **/
  @ApiModelProperty(value = "")


  public String getZufangName() {
    return zufangName;
  }

  public void setZufangName(String zufangName) {
    this.zufangName = zufangName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentDetailResponse rentDetailResponse = (RentDetailResponse) o;
    return Objects.equals(this.agentBaseDo, rentDetailResponse.agentBaseDo) &&
        Objects.equals(this.agentBusinessCard, rentDetailResponse.agentBusinessCard) &&
        Objects.equals(this.areaName, rentDetailResponse.areaName) &&
        Objects.equals(this.balcony, rentDetailResponse.balcony) &&
        Objects.equals(this.companyCard, rentDetailResponse.companyCard) &&
        Objects.equals(this.createTime, rentDetailResponse.createTime) &&
        Objects.equals(this.dataSourceName, rentDetailResponse.dataSourceName) &&
        Objects.equals(this.dataSourceSign, rentDetailResponse.dataSourceSign) &&
        Objects.equals(this.demand, rentDetailResponse.demand) &&
        Objects.equals(this.districtName, rentDetailResponse.districtName) &&
        Objects.equals(this.floor, rentDetailResponse.floor) &&
        Objects.equals(this.floorLevel, rentDetailResponse.floorLevel) &&
        Objects.equals(this.forward, rentDetailResponse.forward) &&
        Objects.equals(this.hall, rentDetailResponse.hall) &&
        Objects.equals(this.hasLift, rentDetailResponse.hasLift) &&
        Objects.equals(this.houseArea, rentDetailResponse.houseArea) &&
        Objects.equals(this.houseDesc, rentDetailResponse.houseDesc) &&
        Objects.equals(this.houseId, rentDetailResponse.houseId) &&
        Objects.equals(this.housePhoto, rentDetailResponse.housePhoto) &&
        Objects.equals(this.houseTitle, rentDetailResponse.houseTitle) &&
        Objects.equals(this.houseTitleImg, rentDetailResponse.houseTitleImg) &&
        Objects.equals(this.importTime, rentDetailResponse.importTime) &&
        Objects.equals(this.isDefaultImage, rentDetailResponse.isDefaultImage) &&
        Objects.equals(this.isFavorite, rentDetailResponse.isFavorite) &&
        Objects.equals(this.isRecommend, rentDetailResponse.isRecommend) &&
        Objects.equals(this.kitchen, rentDetailResponse.kitchen) &&
        Objects.equals(this.location, rentDetailResponse.location) &&
        Objects.equals(this.nearestSubway, rentDetailResponse.nearestSubway) &&
        Objects.equals(this.payModeName, rentDetailResponse.payModeName) &&
        Objects.equals(this.phone, rentDetailResponse.phone) &&
        Objects.equals(this.publishTime, rentDetailResponse.publishTime) &&
        Objects.equals(this.rentHousePrice, rentDetailResponse.rentHousePrice) &&
        Objects.equals(this.rentHouseTagsName, rentDetailResponse.rentHouseTagsName) &&
        Objects.equals(this.rentHouseType, rentDetailResponse.rentHouseType) &&
        Objects.equals(this.rentSign, rentDetailResponse.rentSign) &&
        Objects.equals(this.rentSignName, rentDetailResponse.rentSignName) &&
        Objects.equals(this.rentType, rentDetailResponse.rentType) &&
        Objects.equals(this.rentTypeName, rentDetailResponse.rentTypeName) &&
        Objects.equals(this.room, rentDetailResponse.room) &&
        Objects.equals(this.supportingFacilities, rentDetailResponse.supportingFacilities) &&
        Objects.equals(this.toilet, rentDetailResponse.toilet) &&
        Objects.equals(this.totalFloor, rentDetailResponse.totalFloor) &&
        Objects.equals(this.upStringTime, rentDetailResponse.upStringTime) &&
        Objects.equals(this.zufangId, rentDetailResponse.zufangId) &&
        Objects.equals(this.zufangName, rentDetailResponse.zufangName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentBaseDo, agentBusinessCard, areaName, balcony, companyCard, createTime, dataSourceName, dataSourceSign, demand, districtName, floor, floorLevel, forward, hall, hasLift, houseArea, houseDesc, houseId, housePhoto, houseTitle, houseTitleImg, importTime, isDefaultImage, isFavorite, isRecommend, kitchen, location, nearestSubway, payModeName, phone, publishTime, rentHousePrice, rentHouseTagsName, rentHouseType, rentSign, rentSignName, rentType, rentTypeName, room, supportingFacilities, toilet, totalFloor, upStringTime, zufangId, zufangName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentDetailResponse {\n");
    
    sb.append("    agentBaseDo: ").append(toIndentedString(agentBaseDo)).append("\n");
    sb.append("    agentBusinessCard: ").append(toIndentedString(agentBusinessCard)).append("\n");
    sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
    sb.append("    balcony: ").append(toIndentedString(balcony)).append("\n");
    sb.append("    companyCard: ").append(toIndentedString(companyCard)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    dataSourceName: ").append(toIndentedString(dataSourceName)).append("\n");
    sb.append("    dataSourceSign: ").append(toIndentedString(dataSourceSign)).append("\n");
    sb.append("    demand: ").append(toIndentedString(demand)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    floor: ").append(toIndentedString(floor)).append("\n");
    sb.append("    floorLevel: ").append(toIndentedString(floorLevel)).append("\n");
    sb.append("    forward: ").append(toIndentedString(forward)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    hasLift: ").append(toIndentedString(hasLift)).append("\n");
    sb.append("    houseArea: ").append(toIndentedString(houseArea)).append("\n");
    sb.append("    houseDesc: ").append(toIndentedString(houseDesc)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhoto: ").append(toIndentedString(housePhoto)).append("\n");
    sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
    sb.append("    houseTitleImg: ").append(toIndentedString(houseTitleImg)).append("\n");
    sb.append("    importTime: ").append(toIndentedString(importTime)).append("\n");
    sb.append("    isDefaultImage: ").append(toIndentedString(isDefaultImage)).append("\n");
    sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
    sb.append("    isRecommend: ").append(toIndentedString(isRecommend)).append("\n");
    sb.append("    kitchen: ").append(toIndentedString(kitchen)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    nearestSubway: ").append(toIndentedString(nearestSubway)).append("\n");
    sb.append("    payModeName: ").append(toIndentedString(payModeName)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    publishTime: ").append(toIndentedString(publishTime)).append("\n");
    sb.append("    rentHousePrice: ").append(toIndentedString(rentHousePrice)).append("\n");
    sb.append("    rentHouseTagsName: ").append(toIndentedString(rentHouseTagsName)).append("\n");
    sb.append("    rentHouseType: ").append(toIndentedString(rentHouseType)).append("\n");
    sb.append("    rentSign: ").append(toIndentedString(rentSign)).append("\n");
    sb.append("    rentSignName: ").append(toIndentedString(rentSignName)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    rentTypeName: ").append(toIndentedString(rentTypeName)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    supportingFacilities: ").append(toIndentedString(supportingFacilities)).append("\n");
    sb.append("    toilet: ").append(toIndentedString(toilet)).append("\n");
    sb.append("    totalFloor: ").append(toIndentedString(totalFloor)).append("\n");
    sb.append("    upStringTime: ").append(toIndentedString(upStringTime)).append("\n");
    sb.append("    zufangId: ").append(toIndentedString(zufangId)).append("\n");
    sb.append("    zufangName: ").append(toIndentedString(zufangName)).append("\n");
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

