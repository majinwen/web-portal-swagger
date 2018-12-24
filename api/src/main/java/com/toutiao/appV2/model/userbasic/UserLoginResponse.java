package com.toutiao.appV2.model.userbasic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * UserLoginResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")
@Data
public class UserLoginResponse {
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

    @JsonProperty("unionid")
    private String unionid=null;

    @JsonProperty("isWxBind")
    private Boolean isWxBind = null;
}

