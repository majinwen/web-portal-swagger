package com.toutiao.appV2.model.userbasic;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * UserBasicResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

public class UserBasicResponse   {
  @JsonProperty("avatar")
  private String avatar = null;

  @JsonProperty("rongCloudToken")
  private String rongCloudToken = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("userOnlySign")
  private String userOnlySign = null;

  public UserBasicResponse avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  /**
   * Get avatar
   * @return avatar
  **/
  @ApiModelProperty(value = "")


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public UserBasicResponse rongCloudToken(String rongCloudToken) {
    this.rongCloudToken = rongCloudToken;
    return this;
  }

  /**
   * Get rongCloudToken
   * @return rongCloudToken
  **/
  @ApiModelProperty(value = "")


  public String getRongCloudToken() {
    return rongCloudToken;
  }

  public void setRongCloudToken(String rongCloudToken) {
    this.rongCloudToken = rongCloudToken;
  }

  public UserBasicResponse userId(String userId) {
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

  public UserBasicResponse userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserBasicResponse userOnlySign(String userOnlySign) {
    this.userOnlySign = userOnlySign;
    return this;
  }

  /**
   * Get userOnlySign
   * @return userOnlySign
  **/
  @ApiModelProperty(value = "")


  public String getUserOnlySign() {
    return userOnlySign;
  }

  public void setUserOnlySign(String userOnlySign) {
    this.userOnlySign = userOnlySign;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBasicResponse userBasicResponse = (UserBasicResponse) o;
    return Objects.equals(this.avatar, userBasicResponse.avatar) &&
        Objects.equals(this.rongCloudToken, userBasicResponse.rongCloudToken) &&
        Objects.equals(this.userId, userBasicResponse.userId) &&
        Objects.equals(this.userName, userBasicResponse.userName) &&
        Objects.equals(this.userOnlySign, userBasicResponse.userOnlySign);
  }

  @Override
  public int hashCode() {
    return Objects.hash(avatar, rongCloudToken, userId, userName, userOnlySign);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserBasicResponse {\n");
    
    sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
    sb.append("    rongCloudToken: ").append(toIndentedString(rongCloudToken)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userOnlySign: ").append(toIndentedString(userOnlySign)).append("\n");
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

