package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RecommendRentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

public class RecommendRentResponse   {
  @JsonProperty("agentBaseDo")
  private AgentBaseDo agentBaseDo = null;

  @JsonProperty("areaName")
  private String areaName = null;

  @JsonProperty("districtName")
  private String districtName = null;

  @JsonProperty("forward")
  private String forward = null;

  @JsonProperty("hall")
  private Integer hall = null;

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

  @JsonProperty("nearestSubway")
  private String nearestSubway = null;

  @JsonProperty("rentHousePrice")
  private Double rentHousePrice = null;

  @JsonProperty("rentHouseTagsName")
  @Valid
  private List<String> rentHouseTagsName = null;

  @JsonProperty("rentType")
  private Integer rentType = null;

  @JsonProperty("rentTypeName")
  private String rentTypeName = null;

  @JsonProperty("room")
  private String room = null;

  @JsonProperty("uid")
  private String uid = null;

  @JsonProperty("zufangId")
  private Integer zufangId = null;

  @JsonProperty("zufangName")
  private String zufangName = null;

  public RecommendRentResponse agentBaseDo(AgentBaseDo agentBaseDo) {
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

  public RecommendRentResponse areaName(String areaName) {
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

  public RecommendRentResponse districtName(String districtName) {
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

  public RecommendRentResponse forward(String forward) {
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

  public RecommendRentResponse hall(Integer hall) {
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

  public RecommendRentResponse houseArea(Double houseArea) {
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

  public RecommendRentResponse houseDesc(String houseDesc) {
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

  public RecommendRentResponse houseId(String houseId) {
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

  public RecommendRentResponse housePhoto(List<String> housePhoto) {
    this.housePhoto = housePhoto;
    return this;
  }

  public RecommendRentResponse addHousePhotoItem(String housePhotoItem) {
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

  public RecommendRentResponse houseTitle(String houseTitle) {
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

  public RecommendRentResponse houseTitleImg(String houseTitleImg) {
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

  public RecommendRentResponse nearestSubway(String nearestSubway) {
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

  public RecommendRentResponse rentHousePrice(Double rentHousePrice) {
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

  public RecommendRentResponse rentHouseTagsName(List<String> rentHouseTagsName) {
    this.rentHouseTagsName = rentHouseTagsName;
    return this;
  }

  public RecommendRentResponse addRentHouseTagsNameItem(String rentHouseTagsNameItem) {
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

  public RecommendRentResponse rentType(Integer rentType) {
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

  public RecommendRentResponse rentTypeName(String rentTypeName) {
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

  public RecommendRentResponse room(String room) {
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

  public RecommendRentResponse uid(String uid) {
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

  public RecommendRentResponse zufangId(Integer zufangId) {
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

  public RecommendRentResponse zufangName(String zufangName) {
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
    RecommendRentResponse recommendRentResponse = (RecommendRentResponse) o;
    return Objects.equals(this.agentBaseDo, recommendRentResponse.agentBaseDo) &&
        Objects.equals(this.areaName, recommendRentResponse.areaName) &&
        Objects.equals(this.districtName, recommendRentResponse.districtName) &&
        Objects.equals(this.forward, recommendRentResponse.forward) &&
        Objects.equals(this.hall, recommendRentResponse.hall) &&
        Objects.equals(this.houseArea, recommendRentResponse.houseArea) &&
        Objects.equals(this.houseDesc, recommendRentResponse.houseDesc) &&
        Objects.equals(this.houseId, recommendRentResponse.houseId) &&
        Objects.equals(this.housePhoto, recommendRentResponse.housePhoto) &&
        Objects.equals(this.houseTitle, recommendRentResponse.houseTitle) &&
        Objects.equals(this.houseTitleImg, recommendRentResponse.houseTitleImg) &&
        Objects.equals(this.nearestSubway, recommendRentResponse.nearestSubway) &&
        Objects.equals(this.rentHousePrice, recommendRentResponse.rentHousePrice) &&
        Objects.equals(this.rentHouseTagsName, recommendRentResponse.rentHouseTagsName) &&
        Objects.equals(this.rentType, recommendRentResponse.rentType) &&
        Objects.equals(this.rentTypeName, recommendRentResponse.rentTypeName) &&
        Objects.equals(this.room, recommendRentResponse.room) &&
        Objects.equals(this.uid, recommendRentResponse.uid) &&
        Objects.equals(this.zufangId, recommendRentResponse.zufangId) &&
        Objects.equals(this.zufangName, recommendRentResponse.zufangName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentBaseDo, areaName, districtName, forward, hall, houseArea, houseDesc, houseId, housePhoto, houseTitle, houseTitleImg, nearestSubway, rentHousePrice, rentHouseTagsName, rentType, rentTypeName, room, uid, zufangId, zufangName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecommendRentResponse {\n");
    
    sb.append("    agentBaseDo: ").append(toIndentedString(agentBaseDo)).append("\n");
    sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    forward: ").append(toIndentedString(forward)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    houseArea: ").append(toIndentedString(houseArea)).append("\n");
    sb.append("    houseDesc: ").append(toIndentedString(houseDesc)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhoto: ").append(toIndentedString(housePhoto)).append("\n");
    sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
    sb.append("    houseTitleImg: ").append(toIndentedString(houseTitleImg)).append("\n");
    sb.append("    nearestSubway: ").append(toIndentedString(nearestSubway)).append("\n");
    sb.append("    rentHousePrice: ").append(toIndentedString(rentHousePrice)).append("\n");
    sb.append("    rentHouseTagsName: ").append(toIndentedString(rentHouseTagsName)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    rentTypeName: ").append(toIndentedString(rentTypeName)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
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

