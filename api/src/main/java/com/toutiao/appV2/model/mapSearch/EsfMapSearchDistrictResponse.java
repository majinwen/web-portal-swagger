package com.toutiao.appV2.model.mapSearch;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.mapSearch.EsfMapSearchDistrictDo;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * EsfMapSearchDistrictResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-23T06:53:25.085Z")

public class EsfMapSearchDistrictResponse   {
  @JsonProperty("data")
  @Valid
  private List<EsfMapSearchDistrictDo> data = null;

  @JsonProperty("hit")
  private String hit = null;

  public EsfMapSearchDistrictResponse data(List<EsfMapSearchDistrictDo> data) {
    this.data = data;
    return this;
  }

  public EsfMapSearchDistrictResponse addDataItem(EsfMapSearchDistrictDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<EsfMapSearchDistrictDo>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<EsfMapSearchDistrictDo> getData() {
    return data;
  }

  public void setData(List<EsfMapSearchDistrictDo> data) {
    this.data = data;
  }

  public EsfMapSearchDistrictResponse hit(String hit) {
    this.hit = hit;
    return this;
  }

  /**
   * Get hit
   * @return hit
  **/
  @ApiModelProperty(value = "")


  public String getHit() {
    return hit;
  }

  public void setHit(String hit) {
    this.hit = hit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsfMapSearchDistrictResponse esfMapSearchDistrictResponse = (EsfMapSearchDistrictResponse) o;
    return Objects.equals(this.data, esfMapSearchDistrictResponse.data) &&
        Objects.equals(this.hit, esfMapSearchDistrictResponse.hit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, hit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsfMapSearchDistrictResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    hit: ").append(toIndentedString(hit)).append("\n");
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

