package com.toutiao.appV2.model.userbasic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Created by 18710 on 2018/11/29.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

@Data
public class UserSubscribeEtcCountResponse {
    @JsonProperty("userFavoriteCount")
    @ApiModelProperty(value = "用户收藏数量", name = "userFavoriteCount")
    private Integer userFavoriteCount;

    @JsonProperty("userSubscribeCount")
    @ApiModelProperty(value = "用户订阅数量", name = "userSubscribeCount")
    private Integer userSubscribeCount;

    @JsonProperty("csAccount")
    @ApiModelProperty(value = "客服账号", name = "csAccount")
    private String csAccount;
}
