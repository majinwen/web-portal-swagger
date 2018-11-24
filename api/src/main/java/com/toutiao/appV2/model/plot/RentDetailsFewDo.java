package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

/**
 * RentDetailsFewDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class RentDetailsFewDo   {
  @JsonProperty("agentBaseDo")
  private AgentBaseDo agentBaseDo = null;

  @JsonProperty("areaName")
  @ApiModelProperty("商圈名称")
  private String areaName = null;

  @JsonProperty("createTime")
  @ApiModelProperty("入库时间")
  private String createTime = null;

  @JsonProperty("districtName")
  @ApiModelProperty("区域名称")
  private String districtName = null;

  @JsonProperty("forward")
  @ApiModelProperty("朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）")
  private String forward = null;

  @JsonProperty("hall")
  @ApiModelProperty("几厅")
  private Integer hall = null;

  @JsonProperty("houseArea")
  @ApiModelProperty("房源面积")
  private Double houseArea = null;

  @JsonProperty("houseDesc")
  @ApiModelProperty("房源描述")
  private String houseDesc = null;

  @JsonProperty("houseId")
  @ApiModelProperty("出租房源Id")
  private String houseId = null;

  @JsonProperty("housePhoto")
  @Valid
  @ApiModelProperty("房源图片")
  private List<String> housePhoto = null;

  @JsonProperty("houseTitle")
  @ApiModelProperty("录入/导入房源标题")
  private String houseTitle = null;

  @JsonProperty("houseTitleImg")
  @ApiModelProperty("房源标题图")
  //@ChangeName("housePhotoTitle")
  private String houseTitleImg = null;

  @JsonProperty("isDefaultImage")
  @ApiModelProperty("是否显示默认图片标志")
  private Integer isDefaultImage = null;

  @JsonProperty("nearbySubway")
  @Valid
  @ApiModelProperty("地铁信息")
  //@ChangeName("nearbyStationsDistance")
  private Map<String, String> nearbySubway = null;

  @JsonProperty("nearestSubway")
  @ApiModelProperty("最近的地铁")
  //@ChangeName("nearBySubway")
  private String nearestSubway = null;

  @JsonProperty("parkRatio")
  @ApiModelProperty("车位配比")
  private String parkRatio = null;

  @JsonProperty("rentHousePrice")
  @ApiModelProperty("租金(元/月)")
  //@ChangeName("rentPrice")
  private Double rentHousePrice = null;

  @JsonProperty("rentHouseTagsName")
  @Valid
  @ApiModelProperty("出租房源标签名称")
  //@ChangeName("tags")
  private List<String> rentHouseTagsName = null;

  @JsonProperty("rentHouseType")
  @ApiModelProperty("房源来源类型(录入/导入)")
  private Integer rentHouseType = null;

  @JsonProperty("rentType")
  @ApiModelProperty("出租类型")
  private Integer rentType = null;

  @JsonProperty("rentTypeName")
  @ApiModelProperty("租赁方式名称")
  private String rentTypeName = null;

  @JsonProperty("room")
  @ApiModelProperty("几室")
  private String room = null;

  @JsonProperty("subwayDistanceInfo")
  @ApiModelProperty("地铁到房子间的距离")
  private String subwayDistanceInfo = null;

  @JsonProperty("totalNum")
  @ApiModelProperty("总数")
  private Integer totalNum = null;

  @JsonProperty("uid")
  @ApiModelProperty("租房推优查询uid")
  private String uid = null;

  @JsonProperty("userId")
  @ApiModelProperty("出租房源Id")
  private Integer userId = null;

  @JsonProperty("zufangId")
  @ApiModelProperty("小区Id")
  //@ChangeName("buildingId")
  private Integer zufangId = null;

  @JsonProperty("zufangName")
  @ApiModelProperty("小区名称")
  //@ChangeName("buildingName")
  private String zufangName = null;

  public RentDetailsFewDo agentBaseDo(AgentBaseDo agentBaseDo) {
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

  public RentDetailsFewDo areaName(String areaName) {
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

  public RentDetailsFewDo createTime(String createTime) {
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

  public RentDetailsFewDo districtName(String districtName) {
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

  public RentDetailsFewDo forward(String forward) {
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

  public RentDetailsFewDo hall(Integer hall) {
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

  public RentDetailsFewDo houseArea(Double houseArea) {
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

  public RentDetailsFewDo houseDesc(String houseDesc) {
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

  public RentDetailsFewDo houseId(String houseId) {
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

  public RentDetailsFewDo housePhoto(List<String> housePhoto) {
    this.housePhoto = housePhoto;
    return this;
  }

  public RentDetailsFewDo addHousePhotoItem(String housePhotoItem) {
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

  public RentDetailsFewDo houseTitle(String houseTitle) {
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

  public RentDetailsFewDo houseTitleImg(String houseTitleImg) {
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

  public RentDetailsFewDo isDefaultImage(Integer isDefaultImage) {
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

  public RentDetailsFewDo nearbySubway(Map<String, String> nearbySubway) {
    this.nearbySubway = nearbySubway;
    return this;
  }

  public RentDetailsFewDo putNearbySubwayItem(String key, String nearbySubwayItem) {
    if (this.nearbySubway == null) {
      this.nearbySubway = new HashMap<String, String>();
    }
    this.nearbySubway.put(key, nearbySubwayItem);
    return this;
  }

  /**
   * Get nearbySubway
   * @return nearbySubway
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getNearbySubway() {
    return nearbySubway;
  }

  public void setNearbySubway(Map<String, String> nearbySubway) {
    this.nearbySubway = nearbySubway;
  }

  public RentDetailsFewDo nearestSubway(String nearestSubway) {
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

  public RentDetailsFewDo parkRatio(String parkRatio) {
    this.parkRatio = parkRatio;
    return this;
  }

  /**
   * Get parkRatio
   * @return parkRatio
  **/
  @ApiModelProperty(value = "")


  public String getParkRatio() {
    return parkRatio;
  }

  public void setParkRatio(String parkRatio) {
    this.parkRatio = parkRatio;
  }

  public RentDetailsFewDo rentHousePrice(Double rentHousePrice) {
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

  public RentDetailsFewDo rentHouseTagsName(List<String> rentHouseTagsName) {
    this.rentHouseTagsName = rentHouseTagsName;
    return this;
  }

  public RentDetailsFewDo addRentHouseTagsNameItem(String rentHouseTagsNameItem) {
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

  public RentDetailsFewDo rentHouseType(Integer rentHouseType) {
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

  public RentDetailsFewDo rentType(Integer rentType) {
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

  public RentDetailsFewDo rentTypeName(String rentTypeName) {
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

  public RentDetailsFewDo room(String room) {
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

  public RentDetailsFewDo subwayDistanceInfo(String subwayDistanceInfo) {
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

  public RentDetailsFewDo totalNum(Integer totalNum) {
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

  public RentDetailsFewDo uid(String uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get uid
   * @return uid
  **/
  @ApiModelProperty(value = "")


  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public RentDetailsFewDo userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public RentDetailsFewDo zufangId(Integer zufangId) {
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

  public RentDetailsFewDo zufangName(String zufangName) {
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
    RentDetailsFewDo rentDetailsFewDo = (RentDetailsFewDo) o;
    return Objects.equals(this.agentBaseDo, rentDetailsFewDo.agentBaseDo) &&
        Objects.equals(this.areaName, rentDetailsFewDo.areaName) &&
        Objects.equals(this.createTime, rentDetailsFewDo.createTime) &&
        Objects.equals(this.districtName, rentDetailsFewDo.districtName) &&
        Objects.equals(this.forward, rentDetailsFewDo.forward) &&
        Objects.equals(this.hall, rentDetailsFewDo.hall) &&
        Objects.equals(this.houseArea, rentDetailsFewDo.houseArea) &&
        Objects.equals(this.houseDesc, rentDetailsFewDo.houseDesc) &&
        Objects.equals(this.houseId, rentDetailsFewDo.houseId) &&
        Objects.equals(this.housePhoto, rentDetailsFewDo.housePhoto) &&
        Objects.equals(this.houseTitle, rentDetailsFewDo.houseTitle) &&
        Objects.equals(this.houseTitleImg, rentDetailsFewDo.houseTitleImg) &&
        Objects.equals(this.isDefaultImage, rentDetailsFewDo.isDefaultImage) &&
        Objects.equals(this.nearbySubway, rentDetailsFewDo.nearbySubway) &&
        Objects.equals(this.nearestSubway, rentDetailsFewDo.nearestSubway) &&
        Objects.equals(this.parkRatio, rentDetailsFewDo.parkRatio) &&
        Objects.equals(this.rentHousePrice, rentDetailsFewDo.rentHousePrice) &&
        Objects.equals(this.rentHouseTagsName, rentDetailsFewDo.rentHouseTagsName) &&
        Objects.equals(this.rentHouseType, rentDetailsFewDo.rentHouseType) &&
        Objects.equals(this.rentType, rentDetailsFewDo.rentType) &&
        Objects.equals(this.rentTypeName, rentDetailsFewDo.rentTypeName) &&
        Objects.equals(this.room, rentDetailsFewDo.room) &&
        Objects.equals(this.subwayDistanceInfo, rentDetailsFewDo.subwayDistanceInfo) &&
        Objects.equals(this.totalNum, rentDetailsFewDo.totalNum) &&
        Objects.equals(this.uid, rentDetailsFewDo.uid) &&
        Objects.equals(this.userId, rentDetailsFewDo.userId) &&
        Objects.equals(this.zufangId, rentDetailsFewDo.zufangId) &&
        Objects.equals(this.zufangName, rentDetailsFewDo.zufangName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentBaseDo, areaName, createTime, districtName, forward, hall, houseArea, houseDesc, houseId, housePhoto, houseTitle, houseTitleImg, isDefaultImage, nearbySubway, nearestSubway, parkRatio, rentHousePrice, rentHouseTagsName, rentHouseType, rentType, rentTypeName, room, subwayDistanceInfo, totalNum, uid, userId, zufangId, zufangName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentDetailsFewDo {\n");
    
    sb.append("    agentBaseDo: ").append(toIndentedString(agentBaseDo)).append("\n");
    sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    forward: ").append(toIndentedString(forward)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    houseArea: ").append(toIndentedString(houseArea)).append("\n");
    sb.append("    houseDesc: ").append(toIndentedString(houseDesc)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhoto: ").append(toIndentedString(housePhoto)).append("\n");
    sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
    sb.append("    houseTitleImg: ").append(toIndentedString(houseTitleImg)).append("\n");
    sb.append("    isDefaultImage: ").append(toIndentedString(isDefaultImage)).append("\n");
    sb.append("    nearbySubway: ").append(toIndentedString(nearbySubway)).append("\n");
    sb.append("    nearestSubway: ").append(toIndentedString(nearestSubway)).append("\n");
    sb.append("    parkRatio: ").append(toIndentedString(parkRatio)).append("\n");
    sb.append("    rentHousePrice: ").append(toIndentedString(rentHousePrice)).append("\n");
    sb.append("    rentHouseTagsName: ").append(toIndentedString(rentHouseTagsName)).append("\n");
    sb.append("    rentHouseType: ").append(toIndentedString(rentHouseType)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    rentTypeName: ").append(toIndentedString(rentTypeName)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    subwayDistanceInfo: ").append(toIndentedString(subwayDistanceInfo)).append("\n");
    sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

