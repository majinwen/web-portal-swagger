package com.toutiao.appV2.model.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MessagePushRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T04:11:04.091Z")

public class MessagePushRequest   {
  @JsonProperty("contentType")
  @NotNull(message = "内容类型不能为空")
  @ApiParam(value = "内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)", required = true)
  private Integer contentType = null;

  @JsonProperty("lastMessageId")
  @ApiParam(value = "")
  private Integer lastMessageId = null;

  public MessagePushRequest contentType(Integer contentType) {
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

  public MessagePushRequest lastMessageId(Integer lastMessageId) {
    this.lastMessageId = lastMessageId;
    return this;
  }

  /**
   * Get lastMessageId
   * @return lastMessageId
  **/
  @ApiModelProperty(value = "")


  public Integer getLastMessageId() {
    return lastMessageId;
  }

  public void setLastMessageId(Integer lastMessageId) {
    this.lastMessageId = lastMessageId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessagePushRequest messagePushRequest = (MessagePushRequest) o;
    return Objects.equals(this.contentType, messagePushRequest.contentType) &&
        Objects.equals(this.lastMessageId, messagePushRequest.lastMessageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentType, lastMessageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagePushRequest {\n");
    
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    lastMessageId: ").append(toIndentedString(lastMessageId)).append("\n");
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

