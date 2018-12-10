package com.toutiao.appV2.model.userbasic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * UserVerifyCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")
@Data
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

  @JsonProperty("unionid")
  private String unionid = null;

  @JsonProperty("avatar")
  private String avatar = null;

  @JsonProperty("type")
  @ApiModelProperty(value = "1:app微信,2:web微信,3:微信小程序",required = true)
  private String type = null;

}

