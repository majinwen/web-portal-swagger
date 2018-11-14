package com.toutiao.web.apiimpl.rest.homepage;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.app.api.chance.request.homepage.NearHouseRequest;
import com.toutiao.app.api.chance.request.homepage.NearHouseSpecialPageRequest;
import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
import com.toutiao.app.api.chance.response.homepage.*;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseSearchDomainResponse;
import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.RedisSessionUtils;
import com.toutiao.web.common.util.city.CityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/homePage")
public class HomePageRestController {

    @Autowired
    private HomePageRestService homePageRestService;

    @Autowired
    private SellHouseService sellHouseService;

    @Autowired
    private RedisSessionUtils redis;
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
        NewHouseListDomain newHouseListDomain= homePageRestService.getHomePageNewHouse(CityUtils.getCity());
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
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse =  new SellHouseSearchDomainResponse();
        BeanUtils.copyProperties(baseQueryRequest,sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery, CityUtils.getCity());
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
        HomePageNearPlotListDo homePageNearPlot = homePageRestService.getHomePageNearPlot(nearHouseDoQuery, CityUtils.getCity());
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
        HomePageNearEsfListDo homePageNearEsf = homePageRestService.getHomePageNearEsf(nearHouseDoQuery, CityUtils.getCity());
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
        HomePageNearPlotDo plotSpecialPage = homePageRestService.getPlotSpecialPage(nearHouseSpecialPageDoQuery, CityUtils.getCity());
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
        HomePageNearEsfListDo esfSpecialPage = homePageRestService.getEsfSpecialPage(nearHouseSpecialPageDoQuery,CityUtils.getCity());
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
        Map<String,HomePageTop50Do> homePageTop50Dos= homePageRestService.getHomePageTop50();
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageTop50Dos));
//        List<HomePageTop50Response> homePageTop50ResponseList=JSONObject.parseArray(json.toJSONString(),HomePageTop50Response.class);
        return NashResult.build(homePageTop50Dos);

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

    /**
     * 保存推荐条件
     */
    @RequestMapping(value = "/saveRecommendCondition",method = RequestMethod.GET)
    @ResponseBody
    public NashResult saveRecommendCondition(@Validated(First.class)  UserFavoriteConditionRequest userFavoriteConditionRequest){
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
        Integer integer = homePageRestService.saveRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        return NashResult.build(integer);
    }

    /**
     * 查询推荐条件
     */
    @RequestMapping(value = "/getRecommendCondition",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getRecommendCondition(@Param("userId") Integer userId){
        UserFavoriteConditionDo recommendCondition = homePageRestService.getRecommendCondition(userId, CityUtils.getCity());
        return NashResult.build(recommendCondition);
    }

    /**
     * 更新推荐条件
     */
    @RequestMapping(value = "/updateRecommendCondition",method = RequestMethod.GET)
    @ResponseBody
    public NashResult updateRecommendCondition(@Validated(First.class) UserFavoriteConditionRequest userFavoriteConditionRequest){
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
        Integer integer = homePageRestService.updateRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        return NashResult.build(integer);
    }

    /**
     * 删除推荐条件
     */
    @RequestMapping(value = "/deleteRecommendCondition",method = RequestMethod.GET)
    @ResponseBody
    public NashResult deleteRecommendCondition(@Param("userId") Integer userId){
        Integer city = CityUtils.returnCityId(CityUtils.getCity());
        Integer integer = homePageRestService.deleteRecommendCondition(userId, city);
        return NashResult.build(integer);
    }

    @RequestMapping(value = "/getTradeQuotations")
    @ResponseBody
    public NashResult getRedisCityForJson(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cityCode = CityUtils.getCity();
        String TradeQuotations = "TradeQuotations_"+cityCode;
        JSONObject res =  JSONObject.parseObject(redis.getValue(TradeQuotations));
        return NashResult.build(res);
    }
}
