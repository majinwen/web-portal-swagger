package com.toutiao.web.common.util.city;

import com.toutiao.web.common.util.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-26
 * Time:   11:41
 * Theme:
 */
public class CityUtils {

    /**
     * 获取cookie中城市信息
     * @param request
     * @param response
     * @return
     */
    public static String getCity(HttpServletRequest request, HttpServletResponse response){
        //从cookie中获取城市信息
        String cityCode = CookieUtils.getCookie(request,response, CookieUtils.COOKIE_NAME_CITY);
        //从header中获取城市信息
        //String cityCode = request.getHeader("");

        return cityCode;
    }


}
