package com.toutiao.appV2.model.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * MessagePushDomain
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T02:55:21.233Z")

public class MessagePushDomain   {
  @JsonProperty("data")
  @Valid
  private List<MessagePushDo> data = null;

  @JsonProperty("lastMessageId")
  private Integer lastMessageId = null;

  @JsonProperty("totalCount")
  private Long totalCount = null;

  public MessagePushDomain data(List<MessagePushDo> data) {
    this.data = data;
    return this;
  }

  public MessagePushDomain addDataItem(MessagePushDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<MessagePushDo>();
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

  public List<MessagePushDo> getData() {
    return data;
  }

  public void setData(List<MessagePushDo> data) {
    this.data = data;
  }

  public MessagePushDomain lastMessageId(Integer lastMessageId) {
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

  public MessagePushDomain totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessagePushDomain messagePushDomain = (MessagePushDomain) o;
    return Objects.equals(this.data, messagePushDomain.data) &&
        Objects.equals(this.lastMessageId, messagePushDomain.lastMessageId) &&
        Objects.equals(this.totalCount, messagePushDomain.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, lastMessageId, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagePushDomain {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    lastMessageId: ").append(toIndentedString(lastMessageId)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

