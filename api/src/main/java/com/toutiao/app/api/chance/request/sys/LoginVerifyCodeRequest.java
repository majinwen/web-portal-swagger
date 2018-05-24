package com.toutiao.app.api.chance.request.sys;

import com.toutiao.web.common.assertUtils.PhoneNumber;
import lombok.Data;

@Data
public class LoginVerifyCodeRequest {


    @PhoneNumber(message = "手机号码不正确")
    private String phone;

}
