package com.toutiao.app.api.chance.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-18
 * Time:   17:58
 * Theme:
 */
@Data
public class UserInfoActivityResponse {

    @ApiModelProperty(value = "用户称呼名")
    private String userCallName;

    @ApiModelProperty(value = "用户手机号")
    private String phone;
}
