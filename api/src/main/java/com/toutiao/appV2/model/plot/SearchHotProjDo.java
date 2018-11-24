package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * SearchHotProjDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class SearchHotProjDo   {
  @ApiModelProperty("城市id")
  private Integer cityId = null;

  @ApiModelProperty("小区类型  1表示新房  2表示二手房  3表示租房")
  private Integer houseType = null;

  @ApiModelProperty("id")
  private Integer id = null;

  @ApiModelProperty("小区id")
  private Integer newcode = null;

  @ApiModelProperty("小区名称")
  private String projname = null;

  @ApiModelProperty("时间")
  private Date time = null;

  public SearchHotProjDo cityId(Integer cityId) {
    this.cityId = cityId;
    return this;
  }

  /**
   * Get cityId
   * @return cityId
  **/
  @ApiModelProperty(value = "")


  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public SearchHotProjDo houseType(Integer houseType) {
    this.houseType = houseType;
    return this;
  }

  /**
   * Get houseType
   * @return houseType
  **/
  @ApiModelProperty(value = "")


  public Integer getHouseType() {
    return houseType;
  }

  public void setHouseType(Integer houseType) {
    this.houseType = houseType;
  }

  public SearchHotProjDo id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SearchHotProjDo newcode(Integer newcode) {
    this.newcode = newcode;
    return this;
  }

  /**
   * Get newcode
   * @return newcode
  **/
  @ApiModelProperty(value = "")


  public Integer getNewcode() {
    return newcode;
  }

  public void setNewcode(Integer newcode) {
    this.newcode = newcode;
  }

  public SearchHotProjDo projname(String projname) {
    this.projname = projname;
    return this;
  }

  /**
   * Get projname
   * @return projname
  **/
  @ApiModelProperty(value = "")


  public String getProjname() {
    return projname;
  }

  public void setProjname(String projname) {
    this.projname = projname;
  }

  public SearchHotProjDo time(Date time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchHotProjDo searchHotProjDo = (SearchHotProjDo) o;
    return Objects.equals(this.cityId, searchHotProjDo.cityId) &&
        Objects.equals(this.houseType, searchHotProjDo.houseType) &&
        Objects.equals(this.id, searchHotProjDo.id) &&
        Objects.equals(this.newcode, searchHotProjDo.newcode) &&
        Objects.equals(this.projname, searchHotProjDo.projname) &&
        Objects.equals(this.time, searchHotProjDo.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cityId, houseType, id, newcode, projname, time);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchHotProjDo {\n");
    
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    houseType: ").append(toIndentedString(houseType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    newcode: ").append(toIndentedString(newcode)).append("\n");
    sb.append("    projname: ").append(toIndentedString(projname)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
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

