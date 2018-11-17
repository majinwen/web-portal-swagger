package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.api.chance.response.homepage.HomePageEsfResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageEsfResponseList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class HomePageEsfResponseList   {
  @JsonProperty("homePageEsfResponse")
  @Valid
  private List<HomePageEsfResponse> homePageEsfResponse = null;

  @JsonProperty("total")
  private Integer total = null;

  public HomePageEsfResponseList homePageEsfResponse(List<HomePageEsfResponse> homePageEsfResponse) {
    this.homePageEsfResponse = homePageEsfResponse;
    return this;
  }

  public HomePageEsfResponseList addHomePageEsfResponseItem(HomePageEsfResponse homePageEsfResponseItem) {
    if (this.homePageEsfResponse == null) {
      this.homePageEsfResponse = new ArrayList<HomePageEsfResponse>();
    }
    this.homePageEsfResponse.add(homePageEsfResponseItem);
    return this;
  }

  /**
   * Get homePageEsfResponse
   * @return homePageEsfResponse
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<HomePageEsfResponse> getHomePageEsfResponse() {
    return homePageEsfResponse;
  }

  public void setHomePageEsfResponse(List<HomePageEsfResponse> homePageEsfResponse) {
    this.homePageEsfResponse = homePageEsfResponse;
  }

  public HomePageEsfResponseList total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomePageEsfResponseList homePageEsfResponseList = (HomePageEsfResponseList) o;
    return Objects.equals(this.homePageEsfResponse, homePageEsfResponseList.homePageEsfResponse) &&
        Objects.equals(this.total, homePageEsfResponseList.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homePageEsfResponse, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomePageEsfResponseList {\n");
    
    sb.append("    homePageEsfResponse: ").append(toIndentedString(homePageEsfResponse)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

