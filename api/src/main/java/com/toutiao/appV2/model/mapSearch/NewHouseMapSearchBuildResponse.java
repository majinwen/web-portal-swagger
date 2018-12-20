package com.toutiao.appV2.model.mapSearch;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.mapSearch.NewHouseMapSearchBuildDo;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * NewHouseMapSearchBuildResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:59:38.870Z")

public class NewHouseMapSearchBuildResponse   {
  @JsonProperty("buildDoList")
  @Valid
  private List<NewHouseMapSearchBuildDo> buildDoList = null;

  @JsonProperty("hit")
  private String hit = null;

  public NewHouseMapSearchBuildResponse buildDoList(List<NewHouseMapSearchBuildDo> buildDoList) {
    this.buildDoList = buildDoList;
    return this;
  }

  public NewHouseMapSearchBuildResponse addBuildDoListItem(NewHouseMapSearchBuildDo buildDoListItem) {
    if (this.buildDoList == null) {
      this.buildDoList = new ArrayList<NewHouseMapSearchBuildDo>();
    }
    this.buildDoList.add(buildDoListItem);
    return this;
  }

  /**
   * Get buildDoList
   * @return buildDoList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<NewHouseMapSearchBuildDo> getBuildDoList() {
    return buildDoList;
  }

  public void setBuildDoList(List<NewHouseMapSearchBuildDo> buildDoList) {
    this.buildDoList = buildDoList;
  }

  public NewHouseMapSearchBuildResponse hit(String hit) {
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
    NewHouseMapSearchBuildResponse newHouseMapSearchBuildResponse = (NewHouseMapSearchBuildResponse) o;
    return Objects.equals(this.buildDoList, newHouseMapSearchBuildResponse.buildDoList) &&
        Objects.equals(this.hit, newHouseMapSearchBuildResponse.hit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buildDoList, hit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewHouseMapSearchBuildResponse {\n");
    
    sb.append("    buildDoList: ").append(toIndentedString(buildDoList)).append("\n");
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

