package com.toutiao.web.service.repository.admin.impl;

import com.toutiao.web.common.util.Com35Aes;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.RedisNameUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.service.RedisAndCookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class RedisAndCookieServiceImpl implements RedisAndCookieService {

    private CookieUtils cookieUtils;

    @Override
    public void deleteAdminStatus(HttpServletRequest request, HttpServletResponse response) {

        cookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_User_LOGIN);

    }
    @Override
    public String getUserCookie(HttpServletRequest request, HttpServletResponse response,String name) {
        return cookieUtils.getCookie(request, response,name);
    }

    @Override
    public void deleteCardCookie(HttpServletRequest request, HttpServletResponse response,String imageCard) {
        cookieUtils.deleteCookie(request, response, imageCard);
    }
    @Override
    public String[] getAdminCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cookie = cookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_User_LOGIN);
            if (StringTool.isNotEmpty(cookie)) {
                String str = Com35Aes.decrypt(Com35Aes.KEYCODE, cookie);
                if (StringTool.isNotEmpty(str)) {
                    String[] strs = str.split(RedisNameUtil.separativeSign);
                    if (strs.length < 1) { // cookie验证不合法
                        return null;
                    } else {
                        return strs;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


}
