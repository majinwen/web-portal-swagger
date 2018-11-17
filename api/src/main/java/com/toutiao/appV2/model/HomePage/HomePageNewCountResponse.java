package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageNewCountResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class HomePageNewCountResponse   {
  @JsonProperty("buildingCount")
  private Long buildingCount = null;

  @JsonProperty("forthcomingCount")
  private Long forthcomingCount = null;

  @JsonProperty("onsaleCount")
  private Long onsaleCount = null;

  @JsonProperty("preferentialCount")
  private Long preferentialCount = null;

  public HomePageNewCountResponse buildingCount(Long buildingCount) {
    this.buildingCount = buildingCount;
    return this;
  }

  /**
   * Get buildingCount
   * @return buildingCount
  **/
  @ApiModelProperty(value = "")


  public Long getBuildingCount() {
    return buildingCount;
  }

  public void setBuildingCount(Long buildingCount) {
    this.buildingCount = buildingCount;
  }

  public HomePageNewCountResponse forthcomingCount(Long forthcomingCount) {
    this.forthcomingCount = forthcomingCount;
    return this;
  }

  /**
   * Get forthcomingCount
   * @return forthcomingCount
  **/
  @ApiModelProperty(value = "")


  public Long getForthcomingCount() {
    return forthcomingCount;
  }

  public void setForthcomingCount(Long forthcomingCount) {
    this.forthcomingCount = forthcomingCount;
  }

  public HomePageNewCountResponse onsaleCount(Long onsaleCount) {
    this.onsaleCount = onsaleCount;
    return this;
  }

  /**
   * Get onsaleCount
   * @return onsaleCount
  **/
  @ApiModelProperty(value = "")


  public Long getOnsaleCount() {
    return onsaleCount;
  }

  public void setOnsaleCount(Long onsaleCount) {
    this.onsaleCount = onsaleCount;
  }

  public HomePageNewCountResponse preferentialCount(Long preferentialCount) {
    this.preferentialCount = preferentialCount;
    return this;
  }

  /**
   * Get preferentialCount
   * @return preferentialCount
  **/
  @ApiModelProperty(value = "")


  public Long getPreferentialCount() {
    return preferentialCount;
  }

  public void setPreferentialCount(Long preferentialCount) {
    this.preferentialCount = preferentialCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomePageNewCountResponse homePageNewCountResponse = (HomePageNewCountResponse) o;
    return Objects.equals(this.buildingCount, homePageNewCountResponse.buildingCount) &&
        Objects.equals(this.forthcomingCount, homePageNewCountResponse.forthcomingCount) &&
        Objects.equals(this.onsaleCount, homePageNewCountResponse.onsaleCount) &&
        Objects.equals(this.preferentialCount, homePageNewCountResponse.preferentialCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildingCount, forthcomingCount, onsaleCount, preferentialCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomePageNewCountResponse {\n");
    
    sb.append("    buildingCount: ").append(toIndentedString(buildingCount)).append("\n");
    sb.append("    forthcomingCount: ").append(toIndentedString(forthcomingCount)).append("\n");
    sb.append("    onsaleCount: ").append(toIndentedString(onsaleCount)).append("\n");
    sb.append("    preferentialCount: ").append(toIndentedString(preferentialCount)).append("\n");
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

