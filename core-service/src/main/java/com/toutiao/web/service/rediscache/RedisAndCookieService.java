package com.toutiao.web.service.rediscache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RedisAndCookieService {

    /**
     * 删除用户登录状态
     */
    public void deleteAdminStatus(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取用户Cookie
     * @param request
     * @param response
     * @return
     */
    public String getUserCookie(HttpServletRequest request, HttpServletResponse response,String name);



    /**
     * 删除CardCookie
     * @param request
     * @param response
     */
    public void deleteCardCookie(HttpServletRequest request, HttpServletResponse response,String imageCard);

    public String[] getAdminCookie(HttpServletRequest request, HttpServletResponse response);

}
