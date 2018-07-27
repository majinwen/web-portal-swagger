package com.toutiao.web.apiimpl.rest;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-26
 * Time:   11:35
 * Theme:
 */

@RestController
@RequestMapping("/rest/city")
public class CityRestController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 城市
     * @param cityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{cityId}",method = RequestMethod.GET)
    public NashResult getNewHouseDetailByNewCode(@PathVariable String cityId,@RequestHeader ("User-Agent") String userAgent,@CookieValue("select_city")String city)
    {

        System.out.println(city);
        Cookie cookie = CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_CITY, cityId);

//        esIndexUtils.getESByCity1(request, response, cookie.getValue());
        return NashResult.build(cookie.getValue());
    }


}
