package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * RentDetailsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

public class RentDetailsRequest   {
  @JsonProperty("rentId")
  @NotNull(message = "缺少房源Id")
  @ApiParam(value = "房源id", required = true)
  private String rentId = null;

  public RentDetailsRequest rentId(String rentId) {
    this.rentId = rentId;
    return this;
  }

  /**
   * Get rentId
   * @return rentId
  **/
  @ApiModelProperty(value = "")


  public String getRentId() {
    return rentId;
  }

  public void setRentId(String rentId) {
    this.rentId = rentId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentDetailsRequest rentDetailsRequest = (RentDetailsRequest) o;
    return Objects.equals(this.rentId, rentDetailsRequest.rentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentDetailsRequest {\n");
    
    sb.append("    rentId: ").append(toIndentedString(rentId)).append("\n");
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

