package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * IntelligenceRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class IntelligenceRequest   {
  @ApiModelProperty(name = "createTime", value = "创建时间")
  @JsonProperty("createTime")
  private String createTime = null;

  @ApiModelProperty(name = "districtArray", value = "用户传递的区域id数组")
  @JsonProperty("districtArray")
  private String districtArray = null;

  @ApiModelProperty(name = "districtId", value = "用户传递的区域id")
  @JsonProperty("districtId")
  private String districtId = null;

  @ApiModelProperty(name = "docCount", value = "用户赛选出来的商圈个数")
  @JsonProperty("docCount")
  private Long docCount = null;

  @ApiModelProperty(name = "downPayMent", value = "首付(起始)")
  @JsonProperty("downPayMent")
  private String downPayMent = null;

  @ApiModelProperty(name = "hasChild", value = "是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）")
  @JsonProperty("hasChild")
  private String hasChild = null;

  @ApiModelProperty(name = "hasOldman", value = "是否有老人(0-无，1-有)")
  @JsonProperty("hasOldman")
  private String hasOldman = null;

  @ApiModelProperty(name = "hospital", value = "医院")
  @JsonProperty("hospital")
  private String hospital = null;

  @ApiModelProperty(name = "hospitalFlag", value = "医疗配套标签标记")
  @JsonProperty("hospitalFlag")
  private Integer hospitalFlag = null;

  @ApiModelProperty(name = "layOut", value = "用户选择的户型")
  @JsonProperty("layOut")
  private Integer layOut = null;

  @ApiModelProperty(name = "layoutArray", value = "用户选择户型id数组")
  @JsonProperty("layoutArray")
  private String layoutArray = null;

  @ApiModelProperty(name = "maxTotalPrice", value = "最大值")
  @JsonProperty("maxTotalPrice")
  private Double maxTotalPrice = null;

  @ApiModelProperty(name = "minTotalPrice", value = "最小值")
  @JsonProperty("minTotalPrice")
  private Double minTotalPrice = null;

  @ApiModelProperty(name = "monthPayMent", value = "月供(起始)")
  @JsonProperty("monthPayMent")
  private String monthPayMent = null;

  @ApiModelProperty(name = "plotCount", value = "赛选出来的小区个数")
  @JsonProperty("plotCount")
  private Integer plotCount = null;

  @ApiModelProperty(name = "preconcTotal", value = "用户填入的页面总价信息")
  @JsonProperty("preconcTotal")
  private String preconcTotal = null;

  @ApiModelProperty(name = "ratio", value = "赛选出来的百分比")
  @JsonProperty("ratio")
  private Double ratio = null;

  @ApiModelProperty(name = "school", value = "学校")
  @JsonProperty("school")
  private String school = null;

  @ApiModelProperty(name = "schoolFlag", value = "教育配套标签标记")
  @JsonProperty("schoolFlag")
  private Integer schoolFlag = null;

  @ApiModelProperty(name = "schoolTypeName", value = "通过年龄判定传递的学校类型")
  @JsonProperty("schoolTypeName")
  private String schoolTypeName = null;

  @ApiModelProperty(name = "totalPrice", value = "总价")
  @JsonProperty("totalPrice")
  private String totalPrice = null;

  @ApiModelProperty(name = "userId", value = "用户id")
  @JsonProperty("userId")
  private Integer userId = null;

  @ApiModelProperty(name = "userPortrait", value = "用户画像")
  @JsonProperty("userPortrait")
  private Integer userPortrait = null;

  @ApiModelProperty(name = "userPortrayalType", value = "用户画像类型1,2,3,4,5,6,7")
  @JsonProperty("userPortrayalType")
  private Integer userPortrayalType = null;

  @ApiModelProperty(name = "userType", value = "用户类型(1--自住 刚需,2--自住 改善,3--出租 投资)")
  @JsonProperty("userType")
  private String userType = null;

  public IntelligenceRequest createTime(String createTime) {
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

  public IntelligenceRequest districtArray(String districtArray) {
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

  public IntelligenceRequest districtId(String districtId) {
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

  public IntelligenceRequest docCount(Long docCount) {
    this.docCount = docCount;
    return this;
  }

  /**
   * Get docCount
   * @return docCount
  **/
  @ApiModelProperty(value = "")


  public Long getDocCount() {
    return docCount;
  }

  public void setDocCount(Long docCount) {
    this.docCount = docCount;
  }

  public IntelligenceRequest downPayMent(String downPayMent) {
    this.downPayMent = downPayMent;
    return this;
  }

  /**
   * Get downPayMent
   * @return downPayMent
  **/
  @ApiModelProperty(value = "")


  public String getDownPayMent() {
    return downPayMent;
  }

  public void setDownPayMent(String downPayMent) {
    this.downPayMent = downPayMent;
  }

  public IntelligenceRequest hasChild(String hasChild) {
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

  public IntelligenceRequest hasOldman(String hasOldman) {
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

  public IntelligenceRequest hospital(String hospital) {
    this.hospital = hospital;
    return this;
  }

  /**
   * Get hospital
   * @return hospital
  **/
  @ApiModelProperty(value = "")


  public String getHospital() {
    return hospital;
  }

  public void setHospital(String hospital) {
    this.hospital = hospital;
  }

  public IntelligenceRequest hospitalFlag(Integer hospitalFlag) {
    this.hospitalFlag = hospitalFlag;
    return this;
  }

  /**
   * Get hospitalFlag
   * @return hospitalFlag
  **/
  @ApiModelProperty(value = "")


  public Integer getHospitalFlag() {
    return hospitalFlag;
  }

  public void setHospitalFlag(Integer hospitalFlag) {
    this.hospitalFlag = hospitalFlag;
  }

  public IntelligenceRequest layOut(Integer layOut) {
    this.layOut = layOut;
    return this;
  }

  /**
   * Get layOut
   * @return layOut
  **/
  @ApiModelProperty(value = "")


  public Integer getLayOut() {
    return layOut;
  }

  public void setLayOut(Integer layOut) {
    this.layOut = layOut;
  }

  public IntelligenceRequest layoutArray(String layoutArray) {
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

  public IntelligenceRequest maxTotalPrice(Double maxTotalPrice) {
    this.maxTotalPrice = maxTotalPrice;
    return this;
  }

  /**
   * Get maxTotalPrice
   * @return maxTotalPrice
  **/
  @ApiModelProperty(value = "")


  public Double getMaxTotalPrice() {
    return maxTotalPrice;
  }

  public void setMaxTotalPrice(Double maxTotalPrice) {
    this.maxTotalPrice = maxTotalPrice;
  }

  public IntelligenceRequest minTotalPrice(Double minTotalPrice) {
    this.minTotalPrice = minTotalPrice;
    return this;
  }

  /**
   * Get minTotalPrice
   * @return minTotalPrice
  **/
  @ApiModelProperty(value = "")


  public Double getMinTotalPrice() {
    return minTotalPrice;
  }

  public void setMinTotalPrice(Double minTotalPrice) {
    this.minTotalPrice = minTotalPrice;
  }

  public IntelligenceRequest monthPayMent(String monthPayMent) {
    this.monthPayMent = monthPayMent;
    return this;
  }

  /**
   * Get monthPayMent
   * @return monthPayMent
  **/
  @ApiModelProperty(value = "")


  public String getMonthPayMent() {
    return monthPayMent;
  }

  public void setMonthPayMent(String monthPayMent) {
    this.monthPayMent = monthPayMent;
  }

  public IntelligenceRequest plotCount(Integer plotCount) {
    this.plotCount = plotCount;
    return this;
  }

  /**
   * Get plotCount
   * @return plotCount
  **/
  @ApiModelProperty(value = "")


  public Integer getPlotCount() {
    return plotCount;
  }

  public void setPlotCount(Integer plotCount) {
    this.plotCount = plotCount;
  }

  public IntelligenceRequest preconcTotal(String preconcTotal) {
    this.preconcTotal = preconcTotal;
    return this;
  }

  /**
   * Get preconcTotal
   * @return preconcTotal
  **/
  @ApiModelProperty(value = "")


  public String getPreconcTotal() {
    return preconcTotal;
  }

  public void setPreconcTotal(String preconcTotal) {
    this.preconcTotal = preconcTotal;
  }

  public IntelligenceRequest ratio(Double ratio) {
    this.ratio = ratio;
    return this;
  }

  /**
   * Get ratio
   * @return ratio
  **/
  @ApiModelProperty(value = "")


  public Double getRatio() {
    return ratio;
  }

  public void setRatio(Double ratio) {
    this.ratio = ratio;
  }

  public IntelligenceRequest school(String school) {
    this.school = school;
    return this;
  }

  /**
   * Get school
   * @return school
  **/
  @ApiModelProperty(value = "")


  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public IntelligenceRequest schoolFlag(Integer schoolFlag) {
    this.schoolFlag = schoolFlag;
    return this;
  }

  /**
   * Get schoolFlag
   * @return schoolFlag
  **/
  @ApiModelProperty(value = "")


  public Integer getSchoolFlag() {
    return schoolFlag;
  }

  public void setSchoolFlag(Integer schoolFlag) {
    this.schoolFlag = schoolFlag;
  }

  public IntelligenceRequest schoolTypeName(String schoolTypeName) {
    this.schoolTypeName = schoolTypeName;
    return this;
  }

  /**
   * Get schoolTypeName
   * @return schoolTypeName
  **/
  @ApiModelProperty(value = "")


  public String getSchoolTypeName() {
    return schoolTypeName;
  }

  public void setSchoolTypeName(String schoolTypeName) {
    this.schoolTypeName = schoolTypeName;
  }

  public IntelligenceRequest totalPrice(String totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  **/
  @ApiModelProperty(value = "")


  public String getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(String totalPrice) {
    this.totalPrice = totalPrice;
  }

  public IntelligenceRequest userId(Integer userId) {
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

  public IntelligenceRequest userPortrait(Integer userPortrait) {
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

  public IntelligenceRequest userPortrayalType(Integer userPortrayalType) {
    this.userPortrayalType = userPortrayalType;
    return this;
  }

  /**
   * Get userPortrayalType
   * @return userPortrayalType
  **/
  @ApiModelProperty(value = "")


  public Integer getUserPortrayalType() {
    return userPortrayalType;
  }

  public void setUserPortrayalType(Integer userPortrayalType) {
    this.userPortrayalType = userPortrayalType;
  }

  public IntelligenceRequest userType(String userType) {
    this.userType = userType;
    return this;
  }

  /**
   * Get userType
   * @return userType
  **/
  @ApiModelProperty(value = "")


  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
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
    IntelligenceRequest intelligenceRequest = (IntelligenceRequest) o;
    return Objects.equals(this.createTime, intelligenceRequest.createTime) &&
        Objects.equals(this.districtArray, intelligenceRequest.districtArray) &&
        Objects.equals(this.districtId, intelligenceRequest.districtId) &&
        Objects.equals(this.docCount, intelligenceRequest.docCount) &&
        Objects.equals(this.downPayMent, intelligenceRequest.downPayMent) &&
        Objects.equals(this.hasChild, intelligenceRequest.hasChild) &&
        Objects.equals(this.hasOldman, intelligenceRequest.hasOldman) &&
        Objects.equals(this.hospital, intelligenceRequest.hospital) &&
        Objects.equals(this.hospitalFlag, intelligenceRequest.hospitalFlag) &&
        Objects.equals(this.layOut, intelligenceRequest.layOut) &&
        Objects.equals(this.layoutArray, intelligenceRequest.layoutArray) &&
        Objects.equals(this.maxTotalPrice, intelligenceRequest.maxTotalPrice) &&
        Objects.equals(this.minTotalPrice, intelligenceRequest.minTotalPrice) &&
        Objects.equals(this.monthPayMent, intelligenceRequest.monthPayMent) &&
        Objects.equals(this.plotCount, intelligenceRequest.plotCount) &&
        Objects.equals(this.preconcTotal, intelligenceRequest.preconcTotal) &&
        Objects.equals(this.ratio, intelligenceRequest.ratio) &&
        Objects.equals(this.school, intelligenceRequest.school) &&
        Objects.equals(this.schoolFlag, intelligenceRequest.schoolFlag) &&
        Objects.equals(this.schoolTypeName, intelligenceRequest.schoolTypeName) &&
        Objects.equals(this.totalPrice, intelligenceRequest.totalPrice) &&
        Objects.equals(this.userId, intelligenceRequest.userId) &&
        Objects.equals(this.userPortrait, intelligenceRequest.userPortrait) &&
        Objects.equals(this.userPortrayalType, intelligenceRequest.userPortrayalType) &&
        Objects.equals(this.userType, intelligenceRequest.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createTime, districtArray, districtId, docCount, downPayMent, hasChild, hasOldman, hospital, hospitalFlag, layOut, layoutArray, maxTotalPrice, minTotalPrice, monthPayMent, plotCount, preconcTotal, ratio, school, schoolFlag, schoolTypeName, totalPrice, userId, userPortrait, userPortrayalType, userType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelligenceRequest {\n");
    
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    districtArray: ").append(toIndentedString(districtArray)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    docCount: ").append(toIndentedString(docCount)).append("\n");
    sb.append("    downPayMent: ").append(toIndentedString(downPayMent)).append("\n");
    sb.append("    hasChild: ").append(toIndentedString(hasChild)).append("\n");
    sb.append("    hasOldman: ").append(toIndentedString(hasOldman)).append("\n");
    sb.append("    hospital: ").append(toIndentedString(hospital)).append("\n");
    sb.append("    hospitalFlag: ").append(toIndentedString(hospitalFlag)).append("\n");
    sb.append("    layOut: ").append(toIndentedString(layOut)).append("\n");
    sb.append("    layoutArray: ").append(toIndentedString(layoutArray)).append("\n");
    sb.append("    maxTotalPrice: ").append(toIndentedString(maxTotalPrice)).append("\n");
    sb.append("    minTotalPrice: ").append(toIndentedString(minTotalPrice)).append("\n");
    sb.append("    monthPayMent: ").append(toIndentedString(monthPayMent)).append("\n");
    sb.append("    plotCount: ").append(toIndentedString(plotCount)).append("\n");
    sb.append("    preconcTotal: ").append(toIndentedString(preconcTotal)).append("\n");
    sb.append("    ratio: ").append(toIndentedString(ratio)).append("\n");
    sb.append("    school: ").append(toIndentedString(school)).append("\n");
    sb.append("    schoolFlag: ").append(toIndentedString(schoolFlag)).append("\n");
    sb.append("    schoolTypeName: ").append(toIndentedString(schoolTypeName)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userPortrait: ").append(toIndentedString(userPortrait)).append("\n");
    sb.append("    userPortrayalType: ").append(toIndentedString(userPortrayalType)).append("\n");
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

