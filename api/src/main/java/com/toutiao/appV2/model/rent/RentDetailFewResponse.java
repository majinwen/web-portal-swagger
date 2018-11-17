package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentDetailFewResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

public class RentDetailFewResponse   {
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

  @JsonProperty("houseTitleImg")
  private String houseTitleImg = null;

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

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  @JsonProperty("zufangId")
  private Integer zufangId = null;

  @JsonProperty("zufangName")
  private String zufangName = null;

  public RentDetailFewResponse areaName(String areaName) {
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

  public RentDetailFewResponse districtName(String districtName) {
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

  public RentDetailFewResponse forward(String forward) {
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

  public RentDetailFewResponse hall(Integer hall) {
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

  public RentDetailFewResponse houseArea(Double houseArea) {
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

  public RentDetailFewResponse houseDesc(String houseDesc) {
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

  public RentDetailFewResponse houseId(String houseId) {
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

  public RentDetailFewResponse housePhoto(List<String> housePhoto) {
    this.housePhoto = housePhoto;
    return this;
  }

  public RentDetailFewResponse addHousePhotoItem(String housePhotoItem) {
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

  public RentDetailFewResponse houseTitleImg(String houseTitleImg) {
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

  public RentDetailFewResponse rentHousePrice(Double rentHousePrice) {
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

  public RentDetailFewResponse rentHouseTagsName(List<String> rentHouseTagsName) {
    this.rentHouseTagsName = rentHouseTagsName;
    return this;
  }

  public RentDetailFewResponse addRentHouseTagsNameItem(String rentHouseTagsNameItem) {
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

  public RentDetailFewResponse rentType(Integer rentType) {
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

  public RentDetailFewResponse rentTypeName(String rentTypeName) {
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

  public RentDetailFewResponse room(String room) {
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

  public RentDetailFewResponse totalNum(Integer totalNum) {
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

  public RentDetailFewResponse zufangId(Integer zufangId) {
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

  public RentDetailFewResponse zufangName(String zufangName) {
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
    RentDetailFewResponse rentDetailFewResponse = (RentDetailFewResponse) o;
    return Objects.equals(this.areaName, rentDetailFewResponse.areaName) &&
        Objects.equals(this.districtName, rentDetailFewResponse.districtName) &&
        Objects.equals(this.forward, rentDetailFewResponse.forward) &&
        Objects.equals(this.hall, rentDetailFewResponse.hall) &&
        Objects.equals(this.houseArea, rentDetailFewResponse.houseArea) &&
        Objects.equals(this.houseDesc, rentDetailFewResponse.houseDesc) &&
        Objects.equals(this.houseId, rentDetailFewResponse.houseId) &&
        Objects.equals(this.housePhoto, rentDetailFewResponse.housePhoto) &&
        Objects.equals(this.houseTitleImg, rentDetailFewResponse.houseTitleImg) &&
        Objects.equals(this.rentHousePrice, rentDetailFewResponse.rentHousePrice) &&
        Objects.equals(this.rentHouseTagsName, rentDetailFewResponse.rentHouseTagsName) &&
        Objects.equals(this.rentType, rentDetailFewResponse.rentType) &&
        Objects.equals(this.rentTypeName, rentDetailFewResponse.rentTypeName) &&
        Objects.equals(this.room, rentDetailFewResponse.room) &&
        Objects.equals(this.totalNum, rentDetailFewResponse.totalNum) &&
        Objects.equals(this.zufangId, rentDetailFewResponse.zufangId) &&
        Objects.equals(this.zufangName, rentDetailFewResponse.zufangName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaName, districtName, forward, hall, houseArea, houseDesc, houseId, housePhoto, houseTitleImg, rentHousePrice, rentHouseTagsName, rentType, rentTypeName, room, totalNum, zufangId, zufangName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentDetailFewResponse {\n");
    
    sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
    sb.append("    forward: ").append(toIndentedString(forward)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    houseArea: ").append(toIndentedString(houseArea)).append("\n");
    sb.append("    houseDesc: ").append(toIndentedString(houseDesc)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhoto: ").append(toIndentedString(housePhoto)).append("\n");
    sb.append("    houseTitleImg: ").append(toIndentedString(houseTitleImg)).append("\n");
    sb.append("    rentHousePrice: ").append(toIndentedString(rentHousePrice)).append("\n");
    sb.append("    rentHouseTagsName: ").append(toIndentedString(rentHouseTagsName)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    rentTypeName: ").append(toIndentedString(rentTypeName)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
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

