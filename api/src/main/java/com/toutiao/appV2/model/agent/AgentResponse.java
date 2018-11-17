package com.toutiao.appV2.model.agent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AgentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:08:16.328Z")

public class AgentResponse   {
  @JsonProperty("agentCompany")
  private String agentCompany = null;

  @JsonProperty("agentName")
  private String agentName = null;

  @JsonProperty("displayPhone")
  private String displayPhone = null;

  @JsonProperty("headPhoto")
  private String headPhoto = null;

  @JsonProperty("userId")
  private String userId = null;

  public AgentResponse agentCompany(String agentCompany) {
    this.agentCompany = agentCompany;
    return this;
  }

  /**
   * 经纪公司
   * @return agentCompany
  **/
  @ApiModelProperty(value = "经纪公司")


  public String getAgentCompany() {
    return agentCompany;
  }

  public void setAgentCompany(String agentCompany) {
    this.agentCompany = agentCompany;
  }

  public AgentResponse agentName(String agentName) {
    this.agentName = agentName;
    return this;
  }

  /**
   * 经纪人名称
   * @return agentName
  **/
  @ApiModelProperty(value = "经纪人名称")


  public String getAgentName() {
    return agentName;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public AgentResponse displayPhone(String displayPhone) {
    this.displayPhone = displayPhone;
    return this;
  }

  /**
   * 经纪人虚拟电话
   * @return displayPhone
  **/
  @ApiModelProperty(value = "经纪人虚拟电话")


  public String getDisplayPhone() {
    return displayPhone;
  }

  public void setDisplayPhone(String displayPhone) {
    this.displayPhone = displayPhone;
  }

  public AgentResponse headPhoto(String headPhoto) {
    this.headPhoto = headPhoto;
    return this;
  }

  /**
   * 经纪人头像
   * @return headPhoto
  **/
  @ApiModelProperty(value = "经纪人头像")


  public String getHeadPhoto() {
    return headPhoto;
  }

  public void setHeadPhoto(String headPhoto) {
    this.headPhoto = headPhoto;
  }

  public AgentResponse userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 经纪人id
   * @return userId
  **/
  @ApiModelProperty(value = "经纪人id")


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
    AgentResponse agentResponse = (AgentResponse) o;
    return Objects.equals(this.agentCompany, agentResponse.agentCompany) &&
        Objects.equals(this.agentName, agentResponse.agentName) &&
        Objects.equals(this.displayPhone, agentResponse.displayPhone) &&
        Objects.equals(this.headPhoto, agentResponse.headPhoto) &&
        Objects.equals(this.userId, agentResponse.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentCompany, agentName, displayPhone, headPhoto, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgentResponse {\n");
    
    sb.append("    agentCompany: ").append(toIndentedString(agentCompany)).append("\n");
    sb.append("    agentName: ").append(toIndentedString(agentName)).append("\n");
    sb.append("    displayPhone: ").append(toIndentedString(displayPhone)).append("\n");
    sb.append("    headPhoto: ").append(toIndentedString(headPhoto)).append("\n");
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

