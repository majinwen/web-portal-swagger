package com.toutiao.web.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class CookieUtils {

    /** 平台用户登录CookieName */
    public final static String COOKIE_NAME_User_LOGIN = "user_phone";
    /**
     * 获取cookie中的数据
     * @param request
     * @param response
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request,
                            HttpServletResponse response, String cookieName) {
        Cookie[] cookies=request.getCookies();
        String cookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    cookie = cookies[i].getValue();
                }
            }
        }
        return cookie;
    }

    /**
     *
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request,
                                HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    response.addCookie(cookies[i]);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static Cookie setCookie(HttpServletRequest request,
                                HttpServletResponse response, String cookieName,
                                String cookieValue) {
        Cookie newCookie = new Cookie(cookieName, cookieValue);
        newCookie.setMaxAge(-1);
        newCookie.setPath("/");
        response.addCookie(newCookie);
        return newCookie;
    }




}
