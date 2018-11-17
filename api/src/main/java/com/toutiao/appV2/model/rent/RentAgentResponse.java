package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentAgentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

public class RentAgentResponse   {
  @JsonProperty("agentHeadphoto")
  private String agentHeadphoto = null;

  @JsonProperty("agentId")
  private Integer agentId = null;

  @JsonProperty("agentName")
  private String agentName = null;

  @JsonProperty("agentPhone")
  private String agentPhone = null;

  @JsonProperty("corpHouseId")
  private String corpHouseId = null;

  @JsonProperty("houseDesc")
  private String houseDesc = null;

  @JsonProperty("houseId")
  private String houseId = null;

  @JsonProperty("houseImg")
  @Valid
  private List<String> houseImg = null;

  @JsonProperty("houseTagsName")
  @Valid
  private List<String> houseTagsName = null;

  @JsonProperty("houseTitle")
  private String houseTitle = null;

  @JsonProperty("houseTitleImg")
  private String houseTitleImg = null;

  @JsonProperty("ofCompany")
  private String ofCompany = null;

  public RentAgentResponse agentHeadphoto(String agentHeadphoto) {
    this.agentHeadphoto = agentHeadphoto;
    return this;
  }

  /**
   * Get agentHeadphoto
   * @return agentHeadphoto
  **/
  @ApiModelProperty(value = "")


  public String getAgentHeadphoto() {
    return agentHeadphoto;
  }

  public void setAgentHeadphoto(String agentHeadphoto) {
    this.agentHeadphoto = agentHeadphoto;
  }

  public RentAgentResponse agentId(Integer agentId) {
    this.agentId = agentId;
    return this;
  }

  /**
   * Get agentId
   * @return agentId
  **/
  @ApiModelProperty(value = "")


  public Integer getAgentId() {
    return agentId;
  }

  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  public RentAgentResponse agentName(String agentName) {
    this.agentName = agentName;
    return this;
  }

  /**
   * Get agentName
   * @return agentName
  **/
  @ApiModelProperty(value = "")


  public String getAgentName() {
    return agentName;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public RentAgentResponse agentPhone(String agentPhone) {
    this.agentPhone = agentPhone;
    return this;
  }

  /**
   * Get agentPhone
   * @return agentPhone
  **/
  @ApiModelProperty(value = "")


  public String getAgentPhone() {
    return agentPhone;
  }

  public void setAgentPhone(String agentPhone) {
    this.agentPhone = agentPhone;
  }

  public RentAgentResponse corpHouseId(String corpHouseId) {
    this.corpHouseId = corpHouseId;
    return this;
  }

  /**
   * Get corpHouseId
   * @return corpHouseId
  **/
  @ApiModelProperty(value = "")


  public String getCorpHouseId() {
    return corpHouseId;
  }

  public void setCorpHouseId(String corpHouseId) {
    this.corpHouseId = corpHouseId;
  }

  public RentAgentResponse houseDesc(String houseDesc) {
    this.houseDesc = houseDesc;
    return this;
  }

  /**
   * Get houseDesc
   * @return houseDesc
  **/
  @ApiModelProperty(value = "")


  public String getHouseDesc() {
    return houseDesc;
  }

  public void setHouseDesc(String houseDesc) {
    this.houseDesc = houseDesc;
  }

  public RentAgentResponse houseId(String houseId) {
    this.houseId = houseId;
    return this;
  }

  /**
   * Get houseId
   * @return houseId
  **/
  @ApiModelProperty(value = "")


  public String getHouseId() {
    return houseId;
  }

  public void setHouseId(String houseId) {
    this.houseId = houseId;
  }

  public RentAgentResponse houseImg(List<String> houseImg) {
    this.houseImg = houseImg;
    return this;
  }

  public RentAgentResponse addHouseImgItem(String houseImgItem) {
    if (this.houseImg == null) {
      this.houseImg = new ArrayList<String>();
    }
    this.houseImg.add(houseImgItem);
    return this;
  }

  /**
   * Get houseImg
   * @return houseImg
  **/
  @ApiModelProperty(value = "")


  public List<String> getHouseImg() {
    return houseImg;
  }

  public void setHouseImg(List<String> houseImg) {
    this.houseImg = houseImg;
  }

  public RentAgentResponse houseTagsName(List<String> houseTagsName) {
    this.houseTagsName = houseTagsName;
    return this;
  }

  public RentAgentResponse addHouseTagsNameItem(String houseTagsNameItem) {
    if (this.houseTagsName == null) {
      this.houseTagsName = new ArrayList<String>();
    }
    this.houseTagsName.add(houseTagsNameItem);
    return this;
  }

  /**
   * Get houseTagsName
   * @return houseTagsName
  **/
  @ApiModelProperty(value = "")


  public List<String> getHouseTagsName() {
    return houseTagsName;
  }

  public void setHouseTagsName(List<String> houseTagsName) {
    this.houseTagsName = houseTagsName;
  }

  public RentAgentResponse houseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
    return this;
  }

  /**
   * Get houseTitle
   * @return houseTitle
  **/
  @ApiModelProperty(value = "")


  public String getHouseTitle() {
    return houseTitle;
  }

  public void setHouseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
  }

  public RentAgentResponse houseTitleImg(String houseTitleImg) {
    this.houseTitleImg = houseTitleImg;
    return this;
  }

  /**
   * Get houseTitleImg
   * @return houseTitleImg
  **/
  @ApiModelProperty(value = "")


  public String getHouseTitleImg() {
    return houseTitleImg;
  }

  public void setHouseTitleImg(String houseTitleImg) {
    this.houseTitleImg = houseTitleImg;
  }

  public RentAgentResponse ofCompany(String ofCompany) {
    this.ofCompany = ofCompany;
    return this;
  }

  /**
   * Get ofCompany
   * @return ofCompany
  **/
  @ApiModelProperty(value = "")


  public String getOfCompany() {
    return ofCompany;
  }

  public void setOfCompany(String ofCompany) {
    this.ofCompany = ofCompany;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentAgentResponse rentAgentResponse = (RentAgentResponse) o;
    return Objects.equals(this.agentHeadphoto, rentAgentResponse.agentHeadphoto) &&
        Objects.equals(this.agentId, rentAgentResponse.agentId) &&
        Objects.equals(this.agentName, rentAgentResponse.agentName) &&
        Objects.equals(this.agentPhone, rentAgentResponse.agentPhone) &&
        Objects.equals(this.corpHouseId, rentAgentResponse.corpHouseId) &&
        Objects.equals(this.houseDesc, rentAgentResponse.houseDesc) &&
        Objects.equals(this.houseId, rentAgentResponse.houseId) &&
        Objects.equals(this.houseImg, rentAgentResponse.houseImg) &&
        Objects.equals(this.houseTagsName, rentAgentResponse.houseTagsName) &&
        Objects.equals(this.houseTitle, rentAgentResponse.houseTitle) &&
        Objects.equals(this.houseTitleImg, rentAgentResponse.houseTitleImg) &&
        Objects.equals(this.ofCompany, rentAgentResponse.ofCompany);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentHeadphoto, agentId, agentName, agentPhone, corpHouseId, houseDesc, houseId, houseImg, houseTagsName, houseTitle, houseTitleImg, ofCompany);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentAgentResponse {\n");
    
    sb.append("    agentHeadphoto: ").append(toIndentedString(agentHeadphoto)).append("\n");
    sb.append("    agentId: ").append(toIndentedString(agentId)).append("\n");
    sb.append("    agentName: ").append(toIndentedString(agentName)).append("\n");
    sb.append("    agentPhone: ").append(toIndentedString(agentPhone)).append("\n");
    sb.append("    corpHouseId: ").append(toIndentedString(corpHouseId)).append("\n");
    sb.append("    houseDesc: ").append(toIndentedString(houseDesc)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    houseImg: ").append(toIndentedString(houseImg)).append("\n");
    sb.append("    houseTagsName: ").append(toIndentedString(houseTagsName)).append("\n");
    sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
    sb.append("    houseTitleImg: ").append(toIndentedString(houseTitleImg)).append("\n");
    sb.append("    ofCompany: ").append(toIndentedString(ofCompany)).append("\n");
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

