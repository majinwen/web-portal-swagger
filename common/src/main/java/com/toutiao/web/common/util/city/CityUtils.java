package com.toutiao.web.common.util.city;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-26
 * Time:   11:41
 * Theme:
 */
public class CityUtils {

    /**
     * 获取header中城市信息
     * @return
     */
    public static String getCity(){
        //从cookie中获取城市信息
        //String cityCode = CookieUtils.getCookie(request,response, CookieUtils.COOKIE_NAME_CITY);
        //从header中获取城市信息12
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cityCode = request.getHeader("select_city");
        if(null ==cityCode){
            //测试用sh;正式默认bj
            cityCode = "sh";
        }

        return cityCode;
    }


}
