package com.toutiao.appV2.model.userbasic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/11/24.
 */
@Data
public class LoginVerifyCodeResponse {

    @ApiModelProperty(value = "信息", name = "msg")
    private String msg;

}
