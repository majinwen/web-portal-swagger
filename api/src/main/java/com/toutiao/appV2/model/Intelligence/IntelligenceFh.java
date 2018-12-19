package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * IntelligenceFh
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class IntelligenceFh   {
  @ApiModelProperty(name = "distictInfo", value ="区域集合" )
  @JsonProperty("distictInfo")
  @Valid
  private List<DistictInfo> distictInfo = null;

  @ApiModelProperty(name = "districtId", value = "区域id")
  @JsonProperty("districtId")
  private String districtId = null;

  @ApiModelProperty(name = "downPayMent", value = "首付")
  @JsonProperty("downPayMent")
  private String downPayMent = null;

  @ApiModelProperty(name = "fhResult", value = "智能找房结果")
  @JsonProperty("fhResult")
  private Object fhResult = null;

  @ApiModelProperty(name = "hasChild", value = "是否有小孩（0-（无、10+岁） ，1-（0-3岁），2-（4-10岁））")
  @JsonProperty("hasChild")
  private String hasChild = null;

  @ApiModelProperty(name = "hasOldman", value = "是否有老人(0-无，1-有)")
  @JsonProperty("hasOldman")
  private String hasOldman = null;

  @ApiModelProperty(name = "hospitalFlag", value = "医疗配套标签标记(有1,无0)")
  @JsonProperty("hospitalFlag")
  private Integer hospitalFlag = null;

  @ApiModelProperty(name = "layOut", value = "户型")
  @JsonProperty("layOut")
  private Integer layOut = null;

  @ApiModelProperty(name = "monthPayMent", value = "月供")
  @JsonProperty("monthPayMent")
  private String monthPayMent = null;

  @ApiModelProperty(name = "phone", value = "用户手机号")
  @JsonProperty("phone")
  private String phone = null;

  @ApiModelProperty(name = "plotCount", value = "赛选出来的小区个数")
  @JsonProperty("plotCount")
  private Integer plotCount = null;

  @ApiModelProperty(name = "plotTotalEnd", value = "结束价格")
  @JsonProperty("plotTotalEnd")
  private Double plotTotalEnd = null;

  @ApiModelProperty(name = "plotTotalFirst", value = "开始价格")
  @JsonProperty("plotTotalFirst")
  private Double plotTotalFirst = null;

  @ApiModelProperty(name = "preconcTotal", value = "用户填入的页面总价信息")
  @JsonProperty("preconcTotal")
  private String preconcTotal = null;

  @ApiModelProperty(name = "ratio", value = "用户与历史数据百分比")
  @JsonProperty("ratio")
  private Double ratio = null;

  @ApiModelProperty(name = "schoolFlag", value = "教育配套标签标记(有1,无0)")
  @JsonProperty("schoolFlag")
  private Integer schoolFlag = null;

  @ApiModelProperty(name = "schoolType", value = "学校类型 KG:幼儿园 L:小学")
  @JsonProperty("schoolType")
  private String schoolType = null;

  @ApiModelProperty(name = "userPortrayalType", value = "用户画像类型1,2,3,4,5,6,7")
  @JsonProperty("userPortrayalType")
  private Integer userPortrayalType = null;

  @ApiModelProperty(name = "userType",value = "用户类型(1--自住 刚需,2--自住 改善,3--出租 投资)")
  @JsonProperty("userType")
  private String userType = null;

  public IntelligenceFh distictInfo(List<DistictInfo> distictInfo) {
    this.distictInfo = distictInfo;
    return this;
  }

  public IntelligenceFh addDistictInfoItem(DistictInfo distictInfoItem) {
    if (this.distictInfo == null) {
      this.distictInfo = new ArrayList<DistictInfo>();
    }
    this.distictInfo.add(distictInfoItem);
    return this;
  }

  /**
   * Get distictInfo
   * @return distictInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<DistictInfo> getDistictInfo() {
    return distictInfo;
  }

  public void setDistictInfo(List<DistictInfo> distictInfo) {
    this.distictInfo = distictInfo;
  }

  public IntelligenceFh districtId(String districtId) {
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

  public IntelligenceFh downPayMent(String downPayMent) {
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

  public IntelligenceFh fhResult(Object fhResult) {
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

  public IntelligenceFh hasChild(String hasChild) {
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

  public IntelligenceFh hasOldman(String hasOldman) {
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

  public IntelligenceFh hospitalFlag(Integer hospitalFlag) {
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

  public IntelligenceFh layOut(Integer layOut) {
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

  public IntelligenceFh monthPayMent(String monthPayMent) {
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

  public IntelligenceFh phone(String phone) {
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

  public IntelligenceFh plotCount(Integer plotCount) {
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

  public IntelligenceFh plotTotalEnd(Double plotTotalEnd) {
    this.plotTotalEnd = plotTotalEnd;
    return this;
  }

  /**
   * Get plotTotalEnd
   * @return plotTotalEnd
  **/
  @ApiModelProperty(value = "")


  public Double getPlotTotalEnd() {
    return plotTotalEnd;
  }

  public void setPlotTotalEnd(Double plotTotalEnd) {
    this.plotTotalEnd = plotTotalEnd;
  }

  public IntelligenceFh plotTotalFirst(Double plotTotalFirst) {
    this.plotTotalFirst = plotTotalFirst;
    return this;
  }

  /**
   * Get plotTotalFirst
   * @return plotTotalFirst
  **/
  @ApiModelProperty(value = "")


  public Double getPlotTotalFirst() {
    return plotTotalFirst;
  }

  public void setPlotTotalFirst(Double plotTotalFirst) {
    this.plotTotalFirst = plotTotalFirst;
  }

  public IntelligenceFh preconcTotal(String preconcTotal) {
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

  public IntelligenceFh ratio(Double ratio) {
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

  public IntelligenceFh schoolFlag(Integer schoolFlag) {
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

  public IntelligenceFh schoolType(String schoolType) {
    this.schoolType = schoolType;
    return this;
  }

  /**
   * Get schoolType
   * @return schoolType
  **/
  @ApiModelProperty(value = "")


  public String getSchoolType() {
    return schoolType;
  }

  public void setSchoolType(String schoolType) {
    this.schoolType = schoolType;
  }

  public IntelligenceFh userPortrayalType(Integer userPortrayalType) {
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

  public IntelligenceFh userType(String userType) {
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
    IntelligenceFh intelligenceFh = (IntelligenceFh) o;
    return Objects.equals(this.distictInfo, intelligenceFh.distictInfo) &&
        Objects.equals(this.districtId, intelligenceFh.districtId) &&
        Objects.equals(this.downPayMent, intelligenceFh.downPayMent) &&
        Objects.equals(this.fhResult, intelligenceFh.fhResult) &&
        Objects.equals(this.hasChild, intelligenceFh.hasChild) &&
        Objects.equals(this.hasOldman, intelligenceFh.hasOldman) &&
        Objects.equals(this.hospitalFlag, intelligenceFh.hospitalFlag) &&
        Objects.equals(this.layOut, intelligenceFh.layOut) &&
        Objects.equals(this.monthPayMent, intelligenceFh.monthPayMent) &&
        Objects.equals(this.phone, intelligenceFh.phone) &&
        Objects.equals(this.plotCount, intelligenceFh.plotCount) &&
        Objects.equals(this.plotTotalEnd, intelligenceFh.plotTotalEnd) &&
        Objects.equals(this.plotTotalFirst, intelligenceFh.plotTotalFirst) &&
        Objects.equals(this.preconcTotal, intelligenceFh.preconcTotal) &&
        Objects.equals(this.ratio, intelligenceFh.ratio) &&
        Objects.equals(this.schoolFlag, intelligenceFh.schoolFlag) &&
        Objects.equals(this.schoolType, intelligenceFh.schoolType) &&
        Objects.equals(this.userPortrayalType, intelligenceFh.userPortrayalType) &&
        Objects.equals(this.userType, intelligenceFh.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distictInfo, districtId, downPayMent, fhResult, hasChild, hasOldman, hospitalFlag, layOut, monthPayMent, phone, plotCount, plotTotalEnd, plotTotalFirst, preconcTotal, ratio, schoolFlag, schoolType, userPortrayalType, userType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelligenceFh {\n");
    
    sb.append("    distictInfo: ").append(toIndentedString(distictInfo)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    downPayMent: ").append(toIndentedString(downPayMent)).append("\n");
    sb.append("    fhResult: ").append(toIndentedString(fhResult)).append("\n");
    sb.append("    hasChild: ").append(toIndentedString(hasChild)).append("\n");
    sb.append("    hasOldman: ").append(toIndentedString(hasOldman)).append("\n");
    sb.append("    hospitalFlag: ").append(toIndentedString(hospitalFlag)).append("\n");
    sb.append("    layOut: ").append(toIndentedString(layOut)).append("\n");
    sb.append("    monthPayMent: ").append(toIndentedString(monthPayMent)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    plotCount: ").append(toIndentedString(plotCount)).append("\n");
    sb.append("    plotTotalEnd: ").append(toIndentedString(plotTotalEnd)).append("\n");
    sb.append("    plotTotalFirst: ").append(toIndentedString(plotTotalFirst)).append("\n");
    sb.append("    preconcTotal: ").append(toIndentedString(preconcTotal)).append("\n");
    sb.append("    ratio: ").append(toIndentedString(ratio)).append("\n");
    sb.append("    schoolFlag: ").append(toIndentedString(schoolFlag)).append("\n");
    sb.append("    schoolType: ").append(toIndentedString(schoolType)).append("\n");
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

