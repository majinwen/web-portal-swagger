package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

public class RentListResponse   {
  @JsonProperty("rentDetailsList")
  @Valid
  private List<RentDetailsFewDo> rentDetailsList = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  public RentListResponse rentDetailsList(List<RentDetailsFewDo> rentDetailsList) {
    this.rentDetailsList = rentDetailsList;
    return this;
  }

  public RentListResponse addRentDetailsListItem(RentDetailsFewDo rentDetailsListItem) {
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

  public RentListResponse totalCount(Integer totalCount) {
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
    RentListResponse rentListResponse = (RentListResponse) o;
    return Objects.equals(this.rentDetailsList, rentListResponse.rentDetailsList) &&
        Objects.equals(this.totalCount, rentListResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rentDetailsList, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentListResponse {\n");
    
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

