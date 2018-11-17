package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RentDetailsListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class RentDetailsListResponse   {
  @JsonProperty("rentDetailsList")
  @Valid
  private List<RentDetailsFewDo> rentDetailsList = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  public RentDetailsListResponse rentDetailsList(List<RentDetailsFewDo> rentDetailsList) {
    this.rentDetailsList = rentDetailsList;
    return this;
  }

  public RentDetailsListResponse addRentDetailsListItem(RentDetailsFewDo rentDetailsListItem) {
    if (this.rentDetailsList == null) {
      this.rentDetailsList = new ArrayList<RentDetailsFewDo>();
    }
    this.rentDetailsList.add(rentDetailsListItem);
    return this;
  }

  /**
   * Get rentDetailsList
   * @return rentDetailsList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<RentDetailsFewDo> getRentDetailsList() {
    return rentDetailsList;
  }

  public void setRentDetailsList(List<RentDetailsFewDo> rentDetailsList) {
    this.rentDetailsList = rentDetailsList;
  }

  public RentDetailsListResponse totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentDetailsListResponse rentDetailsListResponse = (RentDetailsListResponse) o;
    return Objects.equals(this.rentDetailsList, rentDetailsListResponse.rentDetailsList) &&
        Objects.equals(this.totalCount, rentDetailsListResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rentDetailsList, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentDetailsListResponse {\n");
    
    sb.append("    rentDetailsList: ").append(toIndentedString(rentDetailsList)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

