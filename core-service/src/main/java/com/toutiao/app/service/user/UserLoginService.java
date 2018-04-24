package com.toutiao.app.service.user;


import com.toutiao.app.domain.user.UserBasicDo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserLoginService {


    /**
     * 手机号+动态验证码登录检查
     * @param userBasicDo
     * @return
     */
    UserBasicDo checkUserVerifyCodeLogin(UserBasicDo userBasicDo, HttpServletRequest request, HttpServletResponse response);
}
