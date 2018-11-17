package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * IntelligenceFhRes
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class IntelligenceFhRes   {
  @ApiModelProperty(name = "collectStatus", value = "收藏状态")
  @JsonProperty("collectStatus")
  private Integer collectStatus = null;

  @ApiModelProperty(name = "createTime", value = "创建时间")
  @JsonProperty("createTime")
  private String createTime = null;

  @ApiModelProperty(name = "districtArray", value = "区域列表")
  @JsonProperty("districtArray")
  private String districtArray = null;

  @ApiModelProperty(name = "districtId", value = "区域id")
  @JsonProperty("districtId")
  private String districtId = null;

  @ApiModelProperty(name = "downPayment",value = "首付")
  @JsonProperty("downPayment")
  private Integer downPayment = null;

  @ApiModelProperty(name = "fhResult",value = "智能找房结果")
  @JsonProperty("fhResult")
  private Object fhResult = null;

  @ApiModelProperty(name = "hasChild",value = "是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）")
  @JsonProperty("hasChild")
  private String hasChild = null;

  @ApiModelProperty(name = "hasOldman", value = "是否有老人(0-无，1-有)")
  @JsonProperty("hasOldman")
  private String hasOldman = null;

  @ApiModelProperty(name = "id", value = "id")
  @JsonProperty("id")
  private Integer id = null;

  @ApiModelProperty(name = "layout", value = "户型id")
  @JsonProperty("layout")
  private String layout = null;

  @ApiModelProperty(name = "layoutArray", value = "户型数组")
  @JsonProperty("layoutArray")
  private String layoutArray = null;

  @ApiModelProperty(name = "metroWithPlotdistance", value = "小区到地铁站的距离")
  @JsonProperty("metroWithPlotdistance")
  private String metroWithPlotdistance = null;

  @ApiModelProperty(name = "monthPayment", value = "月供")
  @JsonProperty("monthPayment")
  private Integer monthPayment = null;

  @ApiModelProperty(name = "phone", value = "用户手机号")
  @JsonProperty("phone")
  private String phone = null;

  @ApiModelProperty(name = "totalPrice", value = "总价")
  @JsonProperty("totalPrice")
  private Integer totalPrice = null;

  @ApiModelProperty(name = "userPortrait", value = "用户画像")
  @JsonProperty("userPortrait")
  private Integer userPortrait = null;

  @ApiModelProperty(name = "userType", value = "用户类型（1-自住刚需，2-自住改善，3-投资出租）")
  @JsonProperty("userType")
  private Integer userType = null;

  public IntelligenceFhRes collectStatus(Integer collectStatus) {
    this.collectStatus = collectStatus;
    return this;
  }

  /**
   * Get collectStatus
   * @return collectStatus
  **/
  @ApiModelProperty(value = "")


  public Integer getCollectStatus() {
    return collectStatus;
  }

  public void setCollectStatus(Integer collectStatus) {
    this.collectStatus = collectStatus;
  }

  public IntelligenceFhRes createTime(String createTime) {
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

  public IntelligenceFhRes districtArray(String districtArray) {
    this.districtArray = districtArray;
    return this;
  }

  /**
   * Get districtArray
   * @return districtArray
  **/
  @ApiModelProperty(value = "")


  public String getDistrictArray() {
    return districtArray;
  }

  public void setDistrictArray(String districtArray) {
    this.districtArray = districtArray;
  }

  public IntelligenceFhRes districtId(String districtId) {
    this.districtId = districtId;
    return this;
  }

  /**
   * Get districtId
   * @return districtId
  **/
  @ApiModelProperty(value = "")


  public String getDistrictId() {
    return districtId;
  }

  public void setDistrictId(String districtId) {
    this.districtId = districtId;
  }

  public IntelligenceFhRes downPayment(Integer downPayment) {
    this.downPayment = downPayment;
    return this;
  }

  /**
   * Get downPayment
   * @return downPayment
  **/
  @ApiModelProperty(value = "")


  public Integer getDownPayment() {
    return downPayment;
  }

  public void setDownPayment(Integer downPayment) {
    this.downPayment = downPayment;
  }

  public IntelligenceFhRes fhResult(Object fhResult) {
    this.fhResult = fhResult;
    return this;
  }

  /**
   * Get fhResult
   * @return fhResult
  **/
  @ApiModelProperty(value = "")


  public Object getFhResult() {
    return fhResult;
  }

  public void setFhResult(Object fhResult) {
    this.fhResult = fhResult;
  }

  public IntelligenceFhRes hasChild(String hasChild) {
    this.hasChild = hasChild;
    return this;
  }

  /**
   * Get hasChild
   * @return hasChild
  **/
  @ApiModelProperty(value = "")


  public String getHasChild() {
    return hasChild;
  }

  public void setHasChild(String hasChild) {
    this.hasChild = hasChild;
  }

  public IntelligenceFhRes hasOldman(String hasOldman) {
    this.hasOldman = hasOldman;
    return this;
  }

  /**
   * Get hasOldman
   * @return hasOldman
  **/
  @ApiModelProperty(value = "")


  public String getHasOldman() {
    return hasOldman;
  }

  public void setHasOldman(String hasOldman) {
    this.hasOldman = hasOldman;
  }

  public IntelligenceFhRes id(Integer id) {
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

  public IntelligenceFhRes layout(String layout) {
    this.layout = layout;
    return this;
  }

  /**
   * Get layout
   * @return layout
  **/
  @ApiModelProperty(value = "")


  public String getLayout() {
    return layout;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public IntelligenceFhRes layoutArray(String layoutArray) {
    this.layoutArray = layoutArray;
    return this;
  }

  /**
   * Get layoutArray
   * @return layoutArray
  **/
  @ApiModelProperty(value = "")


  public String getLayoutArray() {
    return layoutArray;
  }

  public void setLayoutArray(String layoutArray) {
    this.layoutArray = layoutArray;
  }

  public IntelligenceFhRes metroWithPlotdistance(String metroWithPlotdistance) {
    this.metroWithPlotdistance = metroWithPlotdistance;
    return this;
  }

  /**
   * Get metroWithPlotdistance
   * @return metroWithPlotdistance
  **/
  @ApiModelProperty(value = "")


  public String getMetroWithPlotdistance() {
    return metroWithPlotdistance;
  }

  public void setMetroWithPlotdistance(String metroWithPlotdistance) {
    this.metroWithPlotdistance = metroWithPlotdistance;
  }

  public IntelligenceFhRes monthPayment(Integer monthPayment) {
    this.monthPayment = monthPayment;
    return this;
  }

  /**
   * Get monthPayment
   * @return monthPayment
  **/
  @ApiModelProperty(value = "")


  public Integer getMonthPayment() {
    return monthPayment;
  }

  public void setMonthPayment(Integer monthPayment) {
    this.monthPayment = monthPayment;
  }

  public IntelligenceFhRes phone(String phone) {
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

  public IntelligenceFhRes totalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
  }

  public IntelligenceFhRes userPortrait(Integer userPortrait) {
    this.userPortrait = userPortrait;
    return this;
  }

  /**
   * Get userPortrait
   * @return userPortrait
  **/
  @ApiModelProperty(value = "")


  public Integer getUserPortrait() {
    return userPortrait;
  }

  public void setUserPortrait(Integer userPortrait) {
    this.userPortrait = userPortrait;
  }

  public IntelligenceFhRes userType(Integer userType) {
    this.userType = userType;
    return this;
  }

  /**
   * Get userType
   * @return userType
  **/
  @ApiModelProperty(value = "")


  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntelligenceFhRes intelligenceFhRes = (IntelligenceFhRes) o;
    return Objects.equals(this.collectStatus, intelligenceFhRes.collectStatus) &&
        Objects.equals(this.createTime, intelligenceFhRes.createTime) &&
        Objects.equals(this.districtArray, intelligenceFhRes.districtArray) &&
        Objects.equals(this.districtId, intelligenceFhRes.districtId) &&
        Objects.equals(this.downPayment, intelligenceFhRes.downPayment) &&
        Objects.equals(this.fhResult, intelligenceFhRes.fhResult) &&
        Objects.equals(this.hasChild, intelligenceFhRes.hasChild) &&
        Objects.equals(this.hasOldman, intelligenceFhRes.hasOldman) &&
        Objects.equals(this.id, intelligenceFhRes.id) &&
        Objects.equals(this.layout, intelligenceFhRes.layout) &&
        Objects.equals(this.layoutArray, intelligenceFhRes.layoutArray) &&
        Objects.equals(this.metroWithPlotdistance, intelligenceFhRes.metroWithPlotdistance) &&
        Objects.equals(this.monthPayment, intelligenceFhRes.monthPayment) &&
        Objects.equals(this.phone, intelligenceFhRes.phone) &&
        Objects.equals(this.totalPrice, intelligenceFhRes.totalPrice) &&
        Objects.equals(this.userPortrait, intelligenceFhRes.userPortrait) &&
        Objects.equals(this.userType, intelligenceFhRes.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectStatus, createTime, districtArray, districtId, downPayment, fhResult, hasChild, hasOldman, id, layout, layoutArray, metroWithPlotdistance, monthPayment, phone, totalPrice, userPortrait, userType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelligenceFhRes {\n");
    
    sb.append("    collectStatus: ").append(toIndentedString(collectStatus)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    districtArray: ").append(toIndentedString(districtArray)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    downPayment: ").append(toIndentedString(downPayment)).append("\n");
    sb.append("    fhResult: ").append(toIndentedString(fhResult)).append("\n");
    sb.append("    hasChild: ").append(toIndentedString(hasChild)).append("\n");
    sb.append("    hasOldman: ").append(toIndentedString(hasOldman)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    layout: ").append(toIndentedString(layout)).append("\n");
    sb.append("    layoutArray: ").append(toIndentedString(layoutArray)).append("\n");
    sb.append("    metroWithPlotdistance: ").append(toIndentedString(metroWithPlotdistance)).append("\n");
    sb.append("    monthPayment: ").append(toIndentedString(monthPayment)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    userPortrait: ").append(toIndentedString(userPortrait)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
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

