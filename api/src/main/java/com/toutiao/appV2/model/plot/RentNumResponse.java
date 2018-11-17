package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RentNumResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class RentNumResponse   {
  @JsonProperty("num")
  private Integer num = null;

  @JsonProperty("rentSign")
  private Integer rentSign = null;

  @JsonProperty("rentSignName")
  private String rentSignName = null;

  public RentNumResponse num(Integer num) {
    this.num = num;
    return this;
  }

  /**
   * Get num
   * @return num
  **/
  @ApiModelProperty(value = "")


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public RentNumResponse rentSign(Integer rentSign) {
    this.rentSign = rentSign;
    return this;
  }

  /**
   * Get rentSign
   * @return rentSign
  **/
  @ApiModelProperty(value = "")


  public Integer getRentSign() {
    return rentSign;
  }

  public void setRentSign(Integer rentSign) {
    this.rentSign = rentSign;
  }

  public RentNumResponse rentSignName(String rentSignName) {
    this.rentSignName = rentSignName;
    return this;
  }

  /**
   * Get rentSignName
   * @return rentSignName
  **/
  @ApiModelProperty(value = "")


  public String getRentSignName() {
    return rentSignName;
  }

  public void setRentSignName(String rentSignName) {
    this.rentSignName = rentSignName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentNumResponse rentNumResponse = (RentNumResponse) o;
    return Objects.equals(this.num, rentNumResponse.num) &&
        Objects.equals(this.rentSign, rentNumResponse.rentSign) &&
        Objects.equals(this.rentSignName, rentNumResponse.rentSignName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(num, rentSign, rentSignName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentNumResponse {\n");
    
    sb.append("    num: ").append(toIndentedString(num)).append("\n");
    sb.append("    rentSign: ").append(toIndentedString(rentSign)).append("\n");
    sb.append("    rentSignName: ").append(toIndentedString(rentSignName)).append("\n");
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

