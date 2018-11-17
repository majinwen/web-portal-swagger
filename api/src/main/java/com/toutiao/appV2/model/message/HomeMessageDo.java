package com.toutiao.appV2.model.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * HomeMessageDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T02:55:21.233Z")

public class HomeMessageDo   {
  @JsonProperty("boldMessageContent")
  private String boldMessageContent = null;

  @JsonProperty("contentType")
  private Integer contentType = null;

  @JsonProperty("createTime")
  private Long createTime = null;

  @JsonProperty("messageContent")
  private String messageContent = null;

  @JsonProperty("unReadCount")
  private Long unReadCount = null;

  public HomeMessageDo boldMessageContent(String boldMessageContent) {
    this.boldMessageContent = boldMessageContent;
    return this;
  }

  /**
   * Get boldMessageContent
   * @return boldMessageContent
  **/
  @ApiModelProperty(value = "")


  public String getBoldMessageContent() {
    return boldMessageContent;
  }

  public void setBoldMessageContent(String boldMessageContent) {
    this.boldMessageContent = boldMessageContent;
  }

  public HomeMessageDo contentType(Integer contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * Get contentType
   * @return contentType
  **/
  @ApiModelProperty(value = "")


  public Integer getContentType() {
    return contentType;
  }

  public void setContentType(Integer contentType) {
    this.contentType = contentType;
  }

  public HomeMessageDo createTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")


  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public HomeMessageDo messageContent(String messageContent) {
    this.messageContent = messageContent;
    return this;
  }

  /**
   * Get messageContent
   * @return messageContent
  **/
  @ApiModelProperty(value = "")


  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

  public HomeMessageDo unReadCount(Long unReadCount) {
    this.unReadCount = unReadCount;
    return this;
  }

  /**
   * Get unReadCount
   * @return unReadCount
  **/
  @ApiModelProperty(value = "")


  public Long getUnReadCount() {
    return unReadCount;
  }

  public void setUnReadCount(Long unReadCount) {
    this.unReadCount = unReadCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomeMessageDo homeMessageDo = (HomeMessageDo) o;
    return Objects.equals(this.boldMessageContent, homeMessageDo.boldMessageContent) &&
        Objects.equals(this.contentType, homeMessageDo.contentType) &&
        Objects.equals(this.createTime, homeMessageDo.createTime) &&
        Objects.equals(this.messageContent, homeMessageDo.messageContent) &&
        Objects.equals(this.unReadCount, homeMessageDo.unReadCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boldMessageContent, contentType, createTime, messageContent, unReadCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomeMessageDo {\n");
    
    sb.append("    boldMessageContent: ").append(toIndentedString(boldMessageContent)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    messageContent: ").append(toIndentedString(messageContent)).append("\n");
    sb.append("    unReadCount: ").append(toIndentedString(unReadCount)).append("\n");
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

