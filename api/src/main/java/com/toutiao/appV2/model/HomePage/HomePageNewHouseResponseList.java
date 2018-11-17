package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageNewHouseResponseList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class HomePageNewHouseResponseList   {
  @JsonProperty("newHouseListResponses")
  @Valid
  private List<HomePageNewHouseResponse> newHouseListResponses = null;

  @JsonProperty("total")
  private Integer total = null;

  public HomePageNewHouseResponseList newHouseListResponses(List<HomePageNewHouseResponse> newHouseListResponses) {
    this.newHouseListResponses = newHouseListResponses;
    return this;
  }

  public HomePageNewHouseResponseList addNewHouseListResponsesItem(HomePageNewHouseResponse newHouseListResponsesItem) {
    if (this.newHouseListResponses == null) {
      this.newHouseListResponses = new ArrayList<HomePageNewHouseResponse>();
    }
    this.newHouseListResponses.add(newHouseListResponsesItem);
    return this;
  }

  /**
   * Get newHouseListResponses
   * @return newHouseListResponses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<HomePageNewHouseResponse> getNewHouseListResponses() {
    return newHouseListResponses;
  }

  public void setNewHouseListResponses(List<HomePageNewHouseResponse> newHouseListResponses) {
    this.newHouseListResponses = newHouseListResponses;
  }

  public HomePageNewHouseResponseList total(Integer total) {
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
    HomePageNewHouseResponseList homePageNewHouseResponseList = (HomePageNewHouseResponseList) o;
    return Objects.equals(this.newHouseListResponses, homePageNewHouseResponseList.newHouseListResponses) &&
        Objects.equals(this.total, homePageNewHouseResponseList.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newHouseListResponses, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomePageNewHouseResponseList {\n");
    
    sb.append("    newHouseListResponses: ").append(toIndentedString(newHouseListResponses)).append("\n");
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

