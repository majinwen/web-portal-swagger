package com.toutiao.web.common.util.city;

import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.constant.city.CityEnum;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(CityUtils.class);

    /**
     * 获取header中城市信息
     * @return
     */
    public static String getCity(){
        //从cookie中获取城市信息
        //String cityCode = CookieUtils.getCookie(request,response, CookieUtils.COOKIE_NAME_CITY);
        //从header中获取城市信息12
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cityCode = request.getHeader(CookieUtils.COOKIE_NAME_CITY);
        if(null ==cityCode || "".equals(cityCode)){
            //测试用sh;正式默认bj
            cityCode = CityConstant.ABBREVIATION_BEIJING;
        }

        return cityCode;
    }

    /**
     * 通过城市简写获取城市id
     * @param city
     * @return
     */
    public static Integer returnCityId(String city){

        Integer cityId = CityEnum.getValue(city);
        if(StringTool.isBlank(cityId)){
            return CityEnum.BEIJING.getValue();
        }else{
            return  cityId;
        }

    }


    public static String retuenCityCode(Integer cityId){
        if(StringTool.isBlank(cityId)){
            return CityEnum.BEIJING.getDesc();
        }else{
            String cityCode = CityEnum.getId(cityId);
            return cityCode;
        }
    }


    public static String retuenCityName(Integer cityId){
        if(StringTool.isBlank(cityId)){
            return CityEnum.BEIJING.getName();
        }else{
            return CityEnum.getName(cityId);
        }
    }

}
