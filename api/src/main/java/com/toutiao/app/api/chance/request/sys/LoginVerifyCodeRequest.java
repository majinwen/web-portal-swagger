package com.toutiao.app.api.chance.request.sys;

import com.toutiao.web.common.assertUtils.PhoneNumber;
import lombok.Data;

@Data
public class LoginVerifyCodeRequest {


    @PhoneNumber(message = "手机号码不正确")
    private String phone;
    /**
     * 0:普通, 1:微信
     */
    private Integer type = 0;

}
