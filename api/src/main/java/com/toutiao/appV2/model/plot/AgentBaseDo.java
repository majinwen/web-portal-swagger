package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AgentBaseDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class AgentBaseDo   {
  @JsonProperty("agentBusinessCard")
  private String agentBusinessCard = null;

  @JsonProperty("agentCompany")
  private String agentCompany = null;

  @JsonProperty("agentName")
  private String agentName = null;

  @JsonProperty("displayPhone")
  private String displayPhone = null;

  @JsonProperty("headPhoto")
  private String headPhoto = null;

  @JsonProperty("rcToken")
  private String rcToken = null;

  @JsonProperty("userId")
  private String userId = null;

  public AgentBaseDo agentBusinessCard(String agentBusinessCard) {
    this.agentBusinessCard = agentBusinessCard;
    return this;
  }

  /**
   * Get agentBusinessCard
   * @return agentBusinessCard
  **/
  @ApiModelProperty(value = "")


  public String getAgentBusinessCard() {
    return agentBusinessCard;
  }

  public void setAgentBusinessCard(String agentBusinessCard) {
    this.agentBusinessCard = agentBusinessCard;
  }

  public AgentBaseDo agentCompany(String agentCompany) {
    this.agentCompany = agentCompany;
    return this;
  }

  /**
   * Get agentCompany
   * @return agentCompany
  **/
  @ApiModelProperty(value = "")


  public String getAgentCompany() {
    return agentCompany;
  }

  public void setAgentCompany(String agentCompany) {
    this.agentCompany = agentCompany;
  }

  public AgentBaseDo agentName(String agentName) {
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

  public AgentBaseDo displayPhone(String displayPhone) {
    this.displayPhone = displayPhone;
    return this;
  }

  /**
   * Get displayPhone
   * @return displayPhone
  **/
  @ApiModelProperty(value = "")


  public String getDisplayPhone() {
    return displayPhone;
  }

  public void setDisplayPhone(String displayPhone) {
    this.displayPhone = displayPhone;
  }

  public AgentBaseDo headPhoto(String headPhoto) {
    this.headPhoto = headPhoto;
    return this;
  }

  /**
   * Get headPhoto
   * @return headPhoto
  **/
  @ApiModelProperty(value = "")


  public String getHeadPhoto() {
    return headPhoto;
  }

  public void setHeadPhoto(String headPhoto) {
    this.headPhoto = headPhoto;
  }

  public AgentBaseDo rcToken(String rcToken) {
    this.rcToken = rcToken;
    return this;
  }

  /**
   * Get rcToken
   * @return rcToken
  **/
  @ApiModelProperty(value = "")


  public String getRcToken() {
    return rcToken;
  }

  public void setRcToken(String rcToken) {
    this.rcToken = rcToken;
  }

  public AgentBaseDo userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AgentBaseDo agentBaseDo = (AgentBaseDo) o;
    return Objects.equals(this.agentBusinessCard, agentBaseDo.agentBusinessCard) &&
        Objects.equals(this.agentCompany, agentBaseDo.agentCompany) &&
        Objects.equals(this.agentName, agentBaseDo.agentName) &&
        Objects.equals(this.displayPhone, agentBaseDo.displayPhone) &&
        Objects.equals(this.headPhoto, agentBaseDo.headPhoto) &&
        Objects.equals(this.rcToken, agentBaseDo.rcToken) &&
        Objects.equals(this.userId, agentBaseDo.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentBusinessCard, agentCompany, agentName, displayPhone, headPhoto, rcToken, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgentBaseDo {\n");
    
    sb.append("    agentBusinessCard: ").append(toIndentedString(agentBusinessCard)).append("\n");
    sb.append("    agentCompany: ").append(toIndentedString(agentCompany)).append("\n");
    sb.append("    agentName: ").append(toIndentedString(agentName)).append("\n");
    sb.append("    displayPhone: ").append(toIndentedString(displayPhone)).append("\n");
    sb.append("    headPhoto: ").append(toIndentedString(headPhoto)).append("\n");
    sb.append("    rcToken: ").append(toIndentedString(rcToken)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

