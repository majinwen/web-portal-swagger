package com.toutiao.appV2.model.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * MessageSellHouseDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T02:55:21.233Z")

public class MessageSellHouseDo   {
  @JsonProperty("area")
  private String area = null;

  @JsonProperty("areaId")
  private String areaId = null;

  @JsonProperty("buildArea")
  private Double buildArea = null;

  @JsonProperty("forwardName")
  private String forwardName = null;

  @JsonProperty("hall")
  private String hall = null;

  @JsonProperty("houseBusinessName")
  private String houseBusinessName = null;

  @JsonProperty("houseBusinessNameId")
  private String houseBusinessNameId = null;

  @JsonProperty("houseDetailUrl")
  private String houseDetailUrl = null;

  @JsonProperty("houseId")
  private String houseId = null;

  @JsonProperty("housePhotoTitle")
  private String housePhotoTitle = null;

  @JsonProperty("houseTotalPrices")
  private Double houseTotalPrices = null;

  @JsonProperty("isCutPrice")
  private Integer isCutPrice = null;

  @JsonProperty("isLowPrice")
  private Integer isLowPrice = null;

  @JsonProperty("isMustRob")
  private Integer isMustRob = null;

  @JsonProperty("newcode")
  private Integer newcode = null;

  @JsonProperty("plotName")
  private String plotName = null;

  @JsonProperty("priceFloat")
  private Double priceFloat = null;

  @JsonProperty("room")
  private Integer room = null;

  @JsonProperty("status")
  private Integer status = null;

  public MessageSellHouseDo area(String area) {
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

  public MessageSellHouseDo areaId(String areaId) {
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

  public MessageSellHouseDo buildArea(Double buildArea) {
    this.buildArea = buildArea;
    return this;
  }

  /**
   * Get buildArea
   * @return buildArea
  **/
  @ApiModelProperty(value = "")


  public Double getBuildArea() {
    return buildArea;
  }

  public void setBuildArea(Double buildArea) {
    this.buildArea = buildArea;
  }

  public MessageSellHouseDo forwardName(String forwardName) {
    this.forwardName = forwardName;
    return this;
  }

  /**
   * Get forwardName
   * @return forwardName
  **/
  @ApiModelProperty(value = "")


  public String getForwardName() {
    return forwardName;
  }

  public void setForwardName(String forwardName) {
    this.forwardName = forwardName;
  }

  public MessageSellHouseDo hall(String hall) {
    this.hall = hall;
    return this;
  }

  /**
   * Get hall
   * @return hall
  **/
  @ApiModelProperty(value = "")


  public String getHall() {
    return hall;
  }

  public void setHall(String hall) {
    this.hall = hall;
  }

  public MessageSellHouseDo houseBusinessName(String houseBusinessName) {
    this.houseBusinessName = houseBusinessName;
    return this;
  }

  /**
   * Get houseBusinessName
   * @return houseBusinessName
  **/
  @ApiModelProperty(value = "")


  public String getHouseBusinessName() {
    return houseBusinessName;
  }

  public void setHouseBusinessName(String houseBusinessName) {
    this.houseBusinessName = houseBusinessName;
  }

  public MessageSellHouseDo houseBusinessNameId(String houseBusinessNameId) {
    this.houseBusinessNameId = houseBusinessNameId;
    return this;
  }

  /**
   * Get houseBusinessNameId
   * @return houseBusinessNameId
  **/
  @ApiModelProperty(value = "")


  public String getHouseBusinessNameId() {
    return houseBusinessNameId;
  }

  public void setHouseBusinessNameId(String houseBusinessNameId) {
    this.houseBusinessNameId = houseBusinessNameId;
  }

  public MessageSellHouseDo houseDetailUrl(String houseDetailUrl) {
    this.houseDetailUrl = houseDetailUrl;
    return this;
  }

  /**
   * Get houseDetailUrl
   * @return houseDetailUrl
  **/
  @ApiModelProperty(value = "")


  public String getHouseDetailUrl() {
    return houseDetailUrl;
  }

  public void setHouseDetailUrl(String houseDetailUrl) {
    this.houseDetailUrl = houseDetailUrl;
  }

  public MessageSellHouseDo houseId(String houseId) {
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

  public MessageSellHouseDo housePhotoTitle(String housePhotoTitle) {
    this.housePhotoTitle = housePhotoTitle;
    return this;
  }

  /**
   * Get housePhotoTitle
   * @return housePhotoTitle
  **/
  @ApiModelProperty(value = "")


  public String getHousePhotoTitle() {
    return housePhotoTitle;
  }

  public void setHousePhotoTitle(String housePhotoTitle) {
    this.housePhotoTitle = housePhotoTitle;
  }

  public MessageSellHouseDo houseTotalPrices(Double houseTotalPrices) {
    this.houseTotalPrices = houseTotalPrices;
    return this;
  }

  /**
   * Get houseTotalPrices
   * @return houseTotalPrices
  **/
  @ApiModelProperty(value = "")


  public Double getHouseTotalPrices() {
    return houseTotalPrices;
  }

  public void setHouseTotalPrices(Double houseTotalPrices) {
    this.houseTotalPrices = houseTotalPrices;
  }

  public MessageSellHouseDo isCutPrice(Integer isCutPrice) {
    this.isCutPrice = isCutPrice;
    return this;
  }

  /**
   * Get isCutPrice
   * @return isCutPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getIsCutPrice() {
    return isCutPrice;
  }

  public void setIsCutPrice(Integer isCutPrice) {
    this.isCutPrice = isCutPrice;
  }

  public MessageSellHouseDo isLowPrice(Integer isLowPrice) {
    this.isLowPrice = isLowPrice;
    return this;
  }

  /**
   * Get isLowPrice
   * @return isLowPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getIsLowPrice() {
    return isLowPrice;
  }

  public void setIsLowPrice(Integer isLowPrice) {
    this.isLowPrice = isLowPrice;
  }

  public MessageSellHouseDo isMustRob(Integer isMustRob) {
    this.isMustRob = isMustRob;
    return this;
  }

  /**
   * Get isMustRob
   * @return isMustRob
  **/
  @ApiModelProperty(value = "")


  public Integer getIsMustRob() {
    return isMustRob;
  }

  public void setIsMustRob(Integer isMustRob) {
    this.isMustRob = isMustRob;
  }

  public MessageSellHouseDo newcode(Integer newcode) {
    this.newcode = newcode;
    return this;
  }

  /**
   * Get newcode
   * @return newcode
  **/
  @ApiModelProperty(value = "")


  public Integer getNewcode() {
    return newcode;
  }

  public void setNewcode(Integer newcode) {
    this.newcode = newcode;
  }

  public MessageSellHouseDo plotName(String plotName) {
    this.plotName = plotName;
    return this;
  }

  /**
   * Get plotName
   * @return plotName
  **/
  @ApiModelProperty(value = "")


  public String getPlotName() {
    return plotName;
  }

  public void setPlotName(String plotName) {
    this.plotName = plotName;
  }

  public MessageSellHouseDo priceFloat(Double priceFloat) {
    this.priceFloat = priceFloat;
    return this;
  }

  /**
   * Get priceFloat
   * @return priceFloat
  **/
  @ApiModelProperty(value = "")


  public Double getPriceFloat() {
    return priceFloat;
  }

  public void setPriceFloat(Double priceFloat) {
    this.priceFloat = priceFloat;
  }

  public MessageSellHouseDo room(Integer room) {
    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
  **/
  @ApiModelProperty(value = "")


  public Integer getRoom() {
    return room;
  }

  public void setRoom(Integer room) {
    this.room = room;
  }

  public MessageSellHouseDo status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageSellHouseDo messageSellHouseDo = (MessageSellHouseDo) o;
    return Objects.equals(this.area, messageSellHouseDo.area) &&
        Objects.equals(this.areaId, messageSellHouseDo.areaId) &&
        Objects.equals(this.buildArea, messageSellHouseDo.buildArea) &&
        Objects.equals(this.forwardName, messageSellHouseDo.forwardName) &&
        Objects.equals(this.hall, messageSellHouseDo.hall) &&
        Objects.equals(this.houseBusinessName, messageSellHouseDo.houseBusinessName) &&
        Objects.equals(this.houseBusinessNameId, messageSellHouseDo.houseBusinessNameId) &&
        Objects.equals(this.houseDetailUrl, messageSellHouseDo.houseDetailUrl) &&
        Objects.equals(this.houseId, messageSellHouseDo.houseId) &&
        Objects.equals(this.housePhotoTitle, messageSellHouseDo.housePhotoTitle) &&
        Objects.equals(this.houseTotalPrices, messageSellHouseDo.houseTotalPrices) &&
        Objects.equals(this.isCutPrice, messageSellHouseDo.isCutPrice) &&
        Objects.equals(this.isLowPrice, messageSellHouseDo.isLowPrice) &&
        Objects.equals(this.isMustRob, messageSellHouseDo.isMustRob) &&
        Objects.equals(this.newcode, messageSellHouseDo.newcode) &&
        Objects.equals(this.plotName, messageSellHouseDo.plotName) &&
        Objects.equals(this.priceFloat, messageSellHouseDo.priceFloat) &&
        Objects.equals(this.room, messageSellHouseDo.room) &&
        Objects.equals(this.status, messageSellHouseDo.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(area, areaId, buildArea, forwardName, hall, houseBusinessName, houseBusinessNameId, houseDetailUrl, houseId, housePhotoTitle, houseTotalPrices, isCutPrice, isLowPrice, isMustRob, newcode, plotName, priceFloat, room, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageSellHouseDo {\n");
    
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    buildArea: ").append(toIndentedString(buildArea)).append("\n");
    sb.append("    forwardName: ").append(toIndentedString(forwardName)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    houseBusinessName: ").append(toIndentedString(houseBusinessName)).append("\n");
    sb.append("    houseBusinessNameId: ").append(toIndentedString(houseBusinessNameId)).append("\n");
    sb.append("    houseDetailUrl: ").append(toIndentedString(houseDetailUrl)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhotoTitle: ").append(toIndentedString(housePhotoTitle)).append("\n");
    sb.append("    houseTotalPrices: ").append(toIndentedString(houseTotalPrices)).append("\n");
    sb.append("    isCutPrice: ").append(toIndentedString(isCutPrice)).append("\n");
    sb.append("    isLowPrice: ").append(toIndentedString(isLowPrice)).append("\n");
    sb.append("    isMustRob: ").append(toIndentedString(isMustRob)).append("\n");
    sb.append("    newcode: ").append(toIndentedString(newcode)).append("\n");
    sb.append("    plotName: ").append(toIndentedString(plotName)).append("\n");
    sb.append("    priceFloat: ").append(toIndentedString(priceFloat)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

