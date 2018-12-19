package com.toutiao.appV2.model.userbasic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * LoginVerifyCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T07:22:34.230Z")
@Data
public class LoginVerifyCodeRequest   {
  @JsonProperty("phone")
  @ApiModelProperty("电话")
  private String phone = null;

  @ApiModelProperty(value = "类型(0:普通,1:微信) 默认:0")
  private Integer type = 0;

}

