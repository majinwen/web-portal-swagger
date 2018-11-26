package com.toutiao.appV2.model.mapSearch;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.mapSearch.EsfMapSearchDo;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * EsfMapSearchResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T09:35:01.637Z")

public class EsfMapSearchResponse   {
  @JsonProperty("data")
  @Valid
  private List<EsfMapSearchDo> data = null;

  @JsonProperty("hit")
  private String hit = null;

  public EsfMapSearchResponse data(List<EsfMapSearchDo> data) {
    this.data = data;
    return this;
  }

  public EsfMapSearchResponse addDataItem(EsfMapSearchDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<EsfMapSearchDo>();
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

  public List<EsfMapSearchDo> getData() {
    return data;
  }

  public void setData(List<EsfMapSearchDo> data) {
    this.data = data;
  }

  public EsfMapSearchResponse hit(String hit) {
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
    EsfMapSearchResponse esfMapSearchResponse = (EsfMapSearchResponse) o;
    return Objects.equals(this.data, esfMapSearchResponse.data) &&
        Objects.equals(this.hit, esfMapSearchResponse.hit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, hit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsfMapSearchResponse {\n");
    
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

