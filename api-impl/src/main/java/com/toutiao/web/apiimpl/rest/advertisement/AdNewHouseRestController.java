package com.toutiao.web.apiimpl.rest.advertisement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.advertisement.AdNewHouse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.advertisement.AdNewHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   15:12
 * Theme:
 */

@RestController
@RequestMapping("/rest/ad/newHouse")
public class AdNewHouseRestController {

    @Autowired
    private AdNewHouseRestService adNewHouseRestService;

    /**
     * 新房首页--获取推荐新房列表页面广告信息
     */
    @ResponseBody
    @RequestMapping(value = "/getAdRecommendNewHouseByIds",method = RequestMethod.GET)
    public NashResult getAdRecommendNewHouseByIds(@Validated AdNewHouse adNewHouse) {
        NewHouseListDomain adNewHouseHomePage = adNewHouseRestService.getAdNewHouseHomePage(adNewHouse.getNewHouseIds(), CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(adNewHouseHomePage.getData()));
        List<HomePageNewHouseResponse> newHouseListResponses= JSONObject.parseArray(json.toJSONString(),HomePageNewHouseResponse.class);
        return NashResult.build(newHouseListResponses);
    }


    @ResponseBody
    @RequestMapping(value = "/getAdNewHouseListByIds",method = RequestMethod.GET)
    public NashResult getAdNewHouseListByIds(@Validated AdNewHouse adNewHouse){

        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseListDomain newHouseListDomain = adNewHouseRestService.getAdNewHouseList(adNewHouse.getNewHouseIds(), CityUtils.getCity());
        BeanUtils.copyProperties(newHouseListDomain,newHouseListDomainResponse);
        return  NashResult.build(newHouseListDomainResponse);

    }

}
