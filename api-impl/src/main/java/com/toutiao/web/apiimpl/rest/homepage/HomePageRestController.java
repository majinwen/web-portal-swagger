package com.toutiao.web.apiimpl.rest.homepage;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.app.api.chance.request.homepage.NearHouseRequest;
import com.toutiao.app.api.chance.request.homepage.NearHouseSpecialPageRequest;
import com.toutiao.app.api.chance.response.homepage.*;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseSearchDomainResponse;
import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/homePageEsfSearch",method = RequestMethod.GET)
    public  NashResult homePageEsfSearch(BaseQueryRequest baseQueryRequest)
    {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse =  new SellHouseSearchDomainResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(baseQueryRequest,sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseSearchDomain,sellHouseSearchDomainResponse);
        return NashResult.build(sellHouseSearchDomainResponse);

    }

    /**
     * 首页附近小区
     */
    @RequestMapping(value = "/homePageNearPlot",method = RequestMethod.GET)
    public NashResult homePageNearPlot(@Validated NearHouseRequest nearHouseRequest){
        NearHouseDoQuery nearHouseDoQuery = new NearHouseDoQuery();
        BeanUtils.copyProperties(nearHouseRequest,nearHouseDoQuery);
        HomePageNearPlotListDo homePageNearPlot = homePageRestService.getHomePageNearPlot(nearHouseDoQuery);
        HomePageNearPlotListResponse homePageNearPlotListResponse = JSON.parseObject(JSON.toJSONString(homePageNearPlot), HomePageNearPlotListResponse.class);
        return NashResult.build(homePageNearPlotListResponse);
    }

    /**
     * 首页附近二手房
     */
    @RequestMapping(value = "/homePageNearEsf",method = RequestMethod.GET)
    public NashResult homePageNearEsf(@Validated NearHouseRequest nearHouseRequest){
        NearHouseDoQuery nearHouseDoQuery = new NearHouseDoQuery();
        BeanUtils.copyProperties(nearHouseRequest,nearHouseDoQuery);
        HomePageNearEsfListDo homePageNearEsf = homePageRestService.getHomePageNearEsf(nearHouseDoQuery);
        HomePageNearEsfListResponse homePageNearEsfListResponse = JSON.parseObject(JSON.toJSONString(homePageNearEsf), HomePageNearEsfListResponse.class);
        return NashResult.build(homePageNearEsfListResponse);
    }

    /**
     * 专题着陆页-附近小区
     */
    @RequestMapping(value = "/plotSpecialPage",method = RequestMethod.GET)
    public NashResult plotSpecialPage(@Validated NearHouseSpecialPageRequest nearHouseSpecialPageRequest){
        NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery = new NearHouseSpecialPageDoQuery();
        BeanUtils.copyProperties(nearHouseSpecialPageRequest, nearHouseSpecialPageDoQuery);
        HomePageNearPlotDo plotSpecialPage = homePageRestService.getPlotSpecialPage(nearHouseSpecialPageDoQuery);
        HomePageNearPlotResponse homePageNearPlotResponse = JSON.parseObject(JSON.toJSONString(plotSpecialPage), HomePageNearPlotResponse.class);
        return NashResult.build(homePageNearPlotResponse);
    }

    /**
     * 专题着陆页-附近二手房
     */
    @RequestMapping(value = "/esfSpecialPage",method = RequestMethod.GET)
    public NashResult esfSpecialPage(@Validated NearHouseSpecialPageRequest nearHouseSpecialPageRequest){
        NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery = new NearHouseSpecialPageDoQuery();
        BeanUtils.copyProperties(nearHouseSpecialPageRequest, nearHouseSpecialPageDoQuery);
        HomePageNearEsfListDo esfSpecialPage = homePageRestService.getEsfSpecialPage(nearHouseSpecialPageDoQuery);
        HomePageNearEsfListResponse homePageNearEsfListResponse = JSON.parseObject(JSON.toJSONString(esfSpecialPage), HomePageNearEsfListResponse.class);
        return NashResult.build(homePageNearEsfListResponse);
    }
    /**
     * @return
     * 首页top50
     */

    @RequestMapping(value ="/top50",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult homePageTop50()
    {
        List<HomePageTop50Do> homePageTop50Dos= homePageRestService.getHomePageTop50();
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageTop50Dos));
        List<HomePageTop50Response> homePageTop50ResponseList=JSONObject.parseArray(json.toJSONString(),HomePageTop50Response.class);
        return NashResult.build(homePageTop50ResponseList);

    }


    /**
     * 逢出必抢系列
     */
    @RequestMapping(value = "/beSureToSnatch",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult beSureToSnatch(BaseQueryRequest baseQueryRequest)
    {
        HomeSureToSnatchDoQuery homeSureToSnatchDoQuery=new HomeSureToSnatchDoQuery();
        BeanUtils.copyProperties(baseQueryRequest,homeSureToSnatchDoQuery);
        List<HomeSureToSnatchDo>  homeSureToSnatchDos =homePageRestService.getHomeBeSureToSnatch(homeSureToSnatchDoQuery);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homeSureToSnatchDos));
        List<HomeSureToSnatchResponse> newHouseListResponses=JSONObject.parseArray(json.toJSONString(),HomeSureToSnatchResponse.class);
        return  NashResult.build(newHouseListResponses);
    }

    /**
     * 首页获取降价房8条
     */
    @RequestMapping(value = "/getHomePageCutPrice", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomePageCutPrice() {
        List<HomePageMustBuyDo> homePageCutPriceDos = homePageRestService.getHomePageMustBuy(1);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageCutPriceDos));
        List<HomePageMustBuyResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageMustBuyResponse.class);
        return NashResult.build(newHouseListResponses);
    }

    /**
     * 首页获取价格洼地8条
     */
    @RequestMapping(value = "/getHomePageLowerPrice", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomePageLowerPrice() {
        List<HomePageMustBuyDo> homePageLowerPriceDos = homePageRestService.getHomePageMustBuy(2);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageLowerPriceDos));
        List<HomePageMustBuyResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageMustBuyResponse.class);
        return NashResult.build(newHouseListResponses);
    }

}
