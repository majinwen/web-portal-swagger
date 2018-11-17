package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SearchHotProjDomain
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class SearchHotProjDomain   {
  @JsonProperty("data")
  @Valid
  private List<SearchHotProjDo> data = null;

  public SearchHotProjDomain data(List<SearchHotProjDo> data) {
    this.data = data;
    return this;
  }

  public SearchHotProjDomain addDataItem(SearchHotProjDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<SearchHotProjDo>();
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

  public List<SearchHotProjDo> getData() {
    return data;
  }

  public void setData(List<SearchHotProjDo> data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchHotProjDomain searchHotProjDomain = (SearchHotProjDomain) o;
    return Objects.equals(this.data, searchHotProjDomain.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchHotProjDomain {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

