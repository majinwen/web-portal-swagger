package com.toutiao.web.apiimpl.rest.homepage;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.app.api.chance.response.homepage.HomePageEsfResponse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListResponse;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseSearchDomainResponse;
import com.toutiao.app.domain.homepage.HomePageEsfDo;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/homePage")
public class HomePageRestController {

    @Autowired
    private HomePageRestService homePageRestService;

    @Autowired
    private SellHouseService sellHouseService;
    /**
     * 首页获取二手房推荐5条
     */
    @RequestMapping(value = "getHomePageEsf",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomePageEsf()
    {
       List<HomePageEsfDo> homePageEsfDos= homePageRestService.getHomePageEsf();
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageEsfDos));
        List<HomePageEsfResponse> homePageEsfResponseList= JSONObject.parseArray(json.toJSONString(),HomePageEsfResponse.class);
        return  NashResult.build(homePageEsfResponseList);
    }


    /**
     * 首页获取新房5条
     */

    @RequestMapping(value = "getHomePageNewHouse",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult getHomePageNewHouse()
    {
        NewHouseListDomain newHouseListDomain= homePageRestService.getHomePageNewHouse();
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseListDomain.getData()));
        List<HomePageNewHouseResponse> newHouseListResponses=JSONObject.parseArray(json.toJSONString(),HomePageNewHouseResponse.class);
        return  NashResult.build(newHouseListResponses);
    }

    /**
     * 首页搜索接口
     */
    @RequestMapping(value = "/HomePageEsfSearch",method = RequestMethod.GET)
    public  NashResult homePageEsfSearch(BaseQueryRequest baseQueryRequest)
    {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse =  new SellHouseSearchDomainResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(baseQueryRequest,sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseSearchDomain,sellHouseSearchDomainResponse);
        return NashResult.build(sellHouseSearchDomainResponse);

    }

}
