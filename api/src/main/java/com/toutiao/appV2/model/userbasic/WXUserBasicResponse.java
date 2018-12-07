package com.toutiao.appV2.model.userbasic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WXUserBasicResponse {

    @ApiModelProperty(value = "用户名称")
    private String nickname;

    @ApiModelProperty(value = "性别,1:男性,2:女性")
    private String gender;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "用户头像")
    private String headimgurl;

}
