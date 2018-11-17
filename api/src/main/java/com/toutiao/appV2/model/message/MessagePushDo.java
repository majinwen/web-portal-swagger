package com.toutiao.appV2.model.message;

import java.time.OffsetDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * MessagePushDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T02:55:21.233Z")

public class MessagePushDo   {
  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("contentType")
  private Integer contentType = null;

  @JsonProperty("createTime")
  private OffsetDateTime createTime = null;

  @JsonProperty("esfInfo")
  @Valid
  private Map<String, Object> esfInfo = null;

  @JsonProperty("houseData")
  @Valid
  private Map<String, Object> houseData = null;

  @JsonProperty("houseId")
  private String houseId = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("isDel")
  private Integer isDel = null;

  @JsonProperty("isPush")
  private Integer isPush = null;

  @JsonProperty("messageSellHouseDos")
  @Valid
  private List<MessageSellHouseDo> messageSellHouseDos = null;

  @JsonProperty("messageTheme")
  @Valid
  private Map<String, Object> messageTheme = null;

  @JsonProperty("messageType")
  private Integer messageType = null;

  @JsonProperty("newcode")
  private Integer newcode = null;

  @JsonProperty("pushType")
  private Integer pushType = null;

  @JsonProperty("subscribeType")
  private Integer subscribeType = null;

  @JsonProperty("themeDetailUrl")
  private String themeDetailUrl = null;

  @JsonProperty("userId")
  private Integer userId = null;

  public MessagePushDo cityId(Integer cityId) {
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

  public MessagePushDo contentType(Integer contentType) {
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

  public MessagePushDo createTime(OffsetDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(OffsetDateTime createTime) {
    this.createTime = createTime;
  }

  public MessagePushDo esfInfo(Map<String, Object> esfInfo) {
    this.esfInfo = esfInfo;
    return this;
  }

  public MessagePushDo putEsfInfoItem(String key, Object esfInfoItem) {
    if (this.esfInfo == null) {
      this.esfInfo = new HashMap<String, Object>();
    }
    this.esfInfo.put(key, esfInfoItem);
    return this;
  }

  /**
   * Get esfInfo
   * @return esfInfo
  **/
  @ApiModelProperty(value = "")


  public Map<String, Object> getEsfInfo() {
    return esfInfo;
  }

  public void setEsfInfo(Map<String, Object> esfInfo) {
    this.esfInfo = esfInfo;
  }

  public MessagePushDo houseData(Map<String, Object> houseData) {
    this.houseData = houseData;
    return this;
  }

  public MessagePushDo putHouseDataItem(String key, Object houseDataItem) {
    if (this.houseData == null) {
      this.houseData = new HashMap<String, Object>();
    }
    this.houseData.put(key, houseDataItem);
    return this;
  }

  /**
   * Get houseData
   * @return houseData
  **/
  @ApiModelProperty(value = "")


  public Map<String, Object> getHouseData() {
    return houseData;
  }

  public void setHouseData(Map<String, Object> houseData) {
    this.houseData = houseData;
  }

  public MessagePushDo houseId(String houseId) {
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

  public MessagePushDo id(Integer id) {
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

  public MessagePushDo isDel(Integer isDel) {
    this.isDel = isDel;
    return this;
  }

  /**
   * Get isDel
   * @return isDel
  **/
  @ApiModelProperty(value = "")


  public Integer getIsDel() {
    return isDel;
  }

  public void setIsDel(Integer isDel) {
    this.isDel = isDel;
  }

  public MessagePushDo isPush(Integer isPush) {
    this.isPush = isPush;
    return this;
  }

  /**
   * Get isPush
   * @return isPush
  **/
  @ApiModelProperty(value = "")


  public Integer getIsPush() {
    return isPush;
  }

  public void setIsPush(Integer isPush) {
    this.isPush = isPush;
  }

  public MessagePushDo messageSellHouseDos(List<MessageSellHouseDo> messageSellHouseDos) {
    this.messageSellHouseDos = messageSellHouseDos;
    return this;
  }

  public MessagePushDo addMessageSellHouseDosItem(MessageSellHouseDo messageSellHouseDosItem) {
    if (this.messageSellHouseDos == null) {
      this.messageSellHouseDos = new ArrayList<MessageSellHouseDo>();
    }
    this.messageSellHouseDos.add(messageSellHouseDosItem);
    return this;
  }

  /**
   * Get messageSellHouseDos
   * @return messageSellHouseDos
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<MessageSellHouseDo> getMessageSellHouseDos() {
    return messageSellHouseDos;
  }

  public void setMessageSellHouseDos(List<MessageSellHouseDo> messageSellHouseDos) {
    this.messageSellHouseDos = messageSellHouseDos;
  }

  public MessagePushDo messageTheme(Map<String, Object> messageTheme) {
    this.messageTheme = messageTheme;
    return this;
  }

  public MessagePushDo putMessageThemeItem(String key, Object messageThemeItem) {
    if (this.messageTheme == null) {
      this.messageTheme = new HashMap<String, Object>();
    }
    this.messageTheme.put(key, messageThemeItem);
    return this;
  }

  /**
   * Get messageTheme
   * @return messageTheme
  **/
  @ApiModelProperty(value = "")


  public Map<String, Object> getMessageTheme() {
    return messageTheme;
  }

  public void setMessageTheme(Map<String, Object> messageTheme) {
    this.messageTheme = messageTheme;
  }

  public MessagePushDo messageType(Integer messageType) {
    this.messageType = messageType;
    return this;
  }

  /**
   * Get messageType
   * @return messageType
  **/
  @ApiModelProperty(value = "")


  public Integer getMessageType() {
    return messageType;
  }

  public void setMessageType(Integer messageType) {
    this.messageType = messageType;
  }

  public MessagePushDo newcode(Integer newcode) {
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

  public MessagePushDo pushType(Integer pushType) {
    this.pushType = pushType;
    return this;
  }

  /**
   * Get pushType
   * @return pushType
  **/
  @ApiModelProperty(value = "")


  public Integer getPushType() {
    return pushType;
  }

  public void setPushType(Integer pushType) {
    this.pushType = pushType;
  }

  public MessagePushDo subscribeType(Integer subscribeType) {
    this.subscribeType = subscribeType;
    return this;
  }

  /**
   * Get subscribeType
   * @return subscribeType
  **/
  @ApiModelProperty(value = "")


  public Integer getSubscribeType() {
    return subscribeType;
  }

  public void setSubscribeType(Integer subscribeType) {
    this.subscribeType = subscribeType;
  }

  public MessagePushDo themeDetailUrl(String themeDetailUrl) {
    this.themeDetailUrl = themeDetailUrl;
    return this;
  }

  /**
   * Get themeDetailUrl
   * @return themeDetailUrl
  **/
  @ApiModelProperty(value = "")


  public String getThemeDetailUrl() {
    return themeDetailUrl;
  }

  public void setThemeDetailUrl(String themeDetailUrl) {
    this.themeDetailUrl = themeDetailUrl;
  }

  public MessagePushDo userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
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
    MessagePushDo messagePushDo = (MessagePushDo) o;
    return Objects.equals(this.cityId, messagePushDo.cityId) &&
        Objects.equals(this.contentType, messagePushDo.contentType) &&
        Objects.equals(this.createTime, messagePushDo.createTime) &&
        Objects.equals(this.esfInfo, messagePushDo.esfInfo) &&
        Objects.equals(this.houseData, messagePushDo.houseData) &&
        Objects.equals(this.houseId, messagePushDo.houseId) &&
        Objects.equals(this.id, messagePushDo.id) &&
        Objects.equals(this.isDel, messagePushDo.isDel) &&
        Objects.equals(this.isPush, messagePushDo.isPush) &&
        Objects.equals(this.messageSellHouseDos, messagePushDo.messageSellHouseDos) &&
        Objects.equals(this.messageTheme, messagePushDo.messageTheme) &&
        Objects.equals(this.messageType, messagePushDo.messageType) &&
        Objects.equals(this.newcode, messagePushDo.newcode) &&
        Objects.equals(this.pushType, messagePushDo.pushType) &&
        Objects.equals(this.subscribeType, messagePushDo.subscribeType) &&
        Objects.equals(this.themeDetailUrl, messagePushDo.themeDetailUrl) &&
        Objects.equals(this.userId, messagePushDo.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cityId, contentType, createTime, esfInfo, houseData, houseId, id, isDel, isPush, messageSellHouseDos, messageTheme, messageType, newcode, pushType, subscribeType, themeDetailUrl, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagePushDo {\n");
    
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    esfInfo: ").append(toIndentedString(esfInfo)).append("\n");
    sb.append("    houseData: ").append(toIndentedString(houseData)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isDel: ").append(toIndentedString(isDel)).append("\n");
    sb.append("    isPush: ").append(toIndentedString(isPush)).append("\n");
    sb.append("    messageSellHouseDos: ").append(toIndentedString(messageSellHouseDos)).append("\n");
    sb.append("    messageTheme: ").append(toIndentedString(messageTheme)).append("\n");
    sb.append("    messageType: ").append(toIndentedString(messageType)).append("\n");
    sb.append("    newcode: ").append(toIndentedString(newcode)).append("\n");
    sb.append("    pushType: ").append(toIndentedString(pushType)).append("\n");
    sb.append("    subscribeType: ").append(toIndentedString(subscribeType)).append("\n");
    sb.append("    themeDetailUrl: ").append(toIndentedString(themeDetailUrl)).append("\n");
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

