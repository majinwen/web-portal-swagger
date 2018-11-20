package com.toutiao.appV2.model.userbasic;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * UserVerifyCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

public class UserVerifyCodeRequest   {
  @JsonProperty("backUrl")
  private String backUrl = null;

  @JsonProperty("identityType")
  private short identityType = 0;

  @JsonProperty("imageCode")
  private String imageCode = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("userPhone")
  private String userPhone = null;

  @JsonProperty("verifyCode")
  private String verifyCode = null;

  public UserVerifyCodeRequest backUrl(String backUrl) {
    this.backUrl = backUrl;
    return this;
  }

  /**
   * Get backUrl
   * @return backUrl
  **/
  @ApiModelProperty(value = "")


  public String getBackUrl() {
    return backUrl;
  }

  public void setBackUrl(String backUrl) {
    this.backUrl = backUrl;
  }

  public UserVerifyCodeRequest identityType(short identityType) {
    this.identityType = identityType;
    return this;
  }

  /**
   * Get identityType
   * @return identityType
  **/
  @ApiModelProperty(value = "")


  public short getIdentityType() {
    return identityType;
  }

  public void setIdentityType(short identityType) {
    this.identityType = identityType;
  }

  public UserVerifyCodeRequest imageCode(String imageCode) {
    this.imageCode = imageCode;
    return this;
  }

  /**
   * Get imageCode
   * @return imageCode
  **/
  @ApiModelProperty(value = "")


  public String getImageCode() {
    return imageCode;
  }

  public void setImageCode(String imageCode) {
    this.imageCode = imageCode;
  }

  public UserVerifyCodeRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UserVerifyCodeRequest userPhone(String userPhone) {
    this.userPhone = userPhone;
    return this;
  }

  /**
   * Get userPhone
   * @return userPhone
  **/
  @ApiModelProperty(value = "")


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public UserVerifyCodeRequest verifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
    return this;
  }

  /**
   * Get verifyCode
   * @return verifyCode
  **/
  @ApiModelProperty(value = "")


  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserVerifyCodeRequest userVerifyCodeRequest = (UserVerifyCodeRequest) o;
    return Objects.equals(this.backUrl, userVerifyCodeRequest.backUrl) &&
        Objects.equals(this.identityType, userVerifyCodeRequest.identityType) &&
        Objects.equals(this.imageCode, userVerifyCodeRequest.imageCode) &&
        Objects.equals(this.title, userVerifyCodeRequest.title) &&
        Objects.equals(this.userPhone, userVerifyCodeRequest.userPhone) &&
        Objects.equals(this.verifyCode, userVerifyCodeRequest.verifyCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(backUrl, identityType, imageCode, title, userPhone, verifyCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserVerifyCodeRequest {\n");
    
    sb.append("    backUrl: ").append(toIndentedString(backUrl)).append("\n");
    sb.append("    identityType: ").append(toIndentedString(identityType)).append("\n");
    sb.append("    imageCode: ").append(toIndentedString(imageCode)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    userPhone: ").append(toIndentedString(userPhone)).append("\n");
    sb.append("    verifyCode: ").append(toIndentedString(verifyCode)).append("\n");
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

