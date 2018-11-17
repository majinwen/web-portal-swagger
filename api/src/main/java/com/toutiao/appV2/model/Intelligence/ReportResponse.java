package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ReportResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class ReportResponse   {
  @ApiModelProperty(name = "backUrl", value = "回调url")
  @JsonProperty("backUrl")
  private String backUrl = null;

  @ApiModelProperty(name = "userReport", value = "用户报告")
  @JsonProperty("userReport")
  @Valid
  private List<Object> userReport = null;

  public ReportResponse backUrl(String backUrl) {
    this.backUrl = backUrl;
    return this;
  }

  /**
   * Get backUrl
   * @return backUrl
  **/
  @ApiModelProperty(value = "")


  public String getBackUrl() {
    return backUrl;
  }

  public void setBackUrl(String backUrl) {
    this.backUrl = backUrl;
  }

  public ReportResponse userReport(List<Object> userReport) {
    this.userReport = userReport;
    return this;
  }

  public ReportResponse addUserReportItem(Object userReportItem) {
    if (this.userReport == null) {
      this.userReport = new ArrayList<Object>();
    }
    this.userReport.add(userReportItem);
    return this;
  }

  /**
   * Get userReport
   * @return userReport
  **/
  @ApiModelProperty(value = "")


  public List<Object> getUserReport() {
    return userReport;
  }

  public void setUserReport(List<Object> userReport) {
    this.userReport = userReport;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportResponse reportResponse = (ReportResponse) o;
    return Objects.equals(this.backUrl, reportResponse.backUrl) &&
        Objects.equals(this.userReport, reportResponse.userReport);
  }

  @Override
  public int hashCode() {
    return Objects.hash(backUrl, userReport);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportResponse {\n");
    
    sb.append("    backUrl: ").append(toIndentedString(backUrl)).append("\n");
    sb.append("    userReport: ").append(toIndentedString(userReport)).append("\n");
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

