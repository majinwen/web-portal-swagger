package com.toutiao.appV2.model.userbasic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * UserLoginRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")
@Data
public class WXUserLoginRequest {
    @JsonProperty("unionid")
    @ApiModelProperty(value = "微信唯一标识",required = true)
    private String unionid = null;

    @JsonProperty("type")
    @ApiModelProperty(value = "1:app微信,2:web微信,3:微信小程序",required = true)
    private String type = null;
}
