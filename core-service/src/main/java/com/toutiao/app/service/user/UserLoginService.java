package com.toutiao.app.service.user;


import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserBasicDoQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserLoginService {


    /**
     * 手机号+动态验证码登录检查
     * @param userBasicDo
     * @return
     */
    UserBasicDo checkUserVerifyCodeLogin(UserBasicDoQuery userBasicDo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 手机号+动态验证码+绑定微信登录检查
     * @param userBasicDo
     * @return
     */
    UserBasicDo checkUserVerifyCodeBindWXLogin(UserBasicDoQuery userBasicDo, HttpServletRequest request, HttpServletResponse response);
}
