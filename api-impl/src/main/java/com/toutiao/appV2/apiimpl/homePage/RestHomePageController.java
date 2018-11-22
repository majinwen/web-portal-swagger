package com.toutiao.appV2.apiimpl.homePage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.homepage.HomePageNearPlotListResponse;
import com.toutiao.app.api.chance.response.homepage.HomePageNearPlotResponse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.sellhouse.RecommendEsf5DoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.homepage.HomePageCountService;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.homepage.RecommendRestService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.appV2.api.homePage.HomePageApi;
import com.toutiao.appV2.model.HomePage.*;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.web.common.util.RedisSessionUtils;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")

@Controller
public class RestHomePageController implements HomePageApi {
    @Autowired
    private RecommendRestService recommendRestService;

    @Autowired
    private NewHouseRestService newHouseService;

    @Autowired
    private PlotsRestService appPlotService;

    @Autowired
    private HomePageRestService homePageRestService;

    @Autowired
    private SellHouseService sellHouseService;

    @Autowired
    private RedisSessionUtils redis;

    @Autowired
    private HomePageCountService countService;

    private static final Logger log = LoggerFactory.getLogger(RestHomePageController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RestHomePageController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

//    @Override
//    public ResponseEntity<HomeSureToSnatchList> beSureToSnatch(@ApiParam(value = "BaseQueryRequest", required = true) @Valid @RequestBody BaseQueryRequest baseQueryRequest) {
//        HomeSureToSnatchDoQuery homeSureToSnatchDoQuery = new HomeSureToSnatchDoQuery();
//        BeanUtils.copyProperties(baseQueryRequest, homeSureToSnatchDoQuery);
//        List<HomeSureToSnatchDo> homeSureToSnatchDos = homePageRestService.getHomeBeSureToSnatch(homeSureToSnatchDoQuery);
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homeSureToSnatchDos));
//        List<HomeSureToSnatchResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomeSureToSnatchResponse.class);
//        HomeSureToSnatchList homeSureToSnatchList = new HomeSureToSnatchList();
//        homeSureToSnatchList.setHomeSureToSnatchResponses(newHouseListResponses);
//        homeSureToSnatchList.setTotal(newHouseListResponses.size());
//        return new ResponseEntity<HomeSureToSnatchList>(homeSureToSnatchList, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<HomePageNearEsfListResponse> esfSpecialPage(@ApiParam(value = "NearHouseSpecialPageRequest", required = true) @Valid @RequestBody NearHouseSpecialPageRequest nearHouseSpecialPageRequest) {
        NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery = new NearHouseSpecialPageDoQuery();
        BeanUtils.copyProperties(nearHouseSpecialPageRequest, nearHouseSpecialPageDoQuery);
        HomePageNearEsfListDo esfSpecialPage = homePageRestService.getEsfSpecialPage(nearHouseSpecialPageDoQuery, CityUtils.getCity());
        HomePageNearEsfListResponse homePageNearEsfListResponse = JSON.parseObject(JSON.toJSONString(esfSpecialPage), HomePageNearEsfListResponse.class);
        return new ResponseEntity<HomePageNearEsfListResponse>(homePageNearEsfListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomePageEsfCountResponse> getEsf() {

        HomePageEsfCountDo pageEsfCountDo = countService.getEsfCount(CityUtils.getCity());
        HomePageEsfCountResponse response = new HomePageEsfCountResponse();
        BeanUtils.copyProperties(pageEsfCountDo, response);
        return new ResponseEntity<HomePageEsfCountResponse>(response, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<HomePageMustBuyList> getHomePageCutPrice() {
//        List<HomePageMustBuyDo> homePageCutPriceDos = homePageRestService.getHomePageMustBuy(1);
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageCutPriceDos));
//        List<HomePageMustBuyResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageMustBuyResponse.class);
//        HomePageMustBuyList homePageMustBuyList = new HomePageMustBuyList();
//        homePageMustBuyList.setHomePageMustBuyResponses(newHouseListResponses);
//        homePageMustBuyList.setTotal(newHouseListResponses.size());
//        return new ResponseEntity<HomePageMustBuyList>(homePageMustBuyList, HttpStatus.OK);
//
//    }

//    @Override
//    public ResponseEntity<HomePageEsfResponseList> getHomePageEsf() {
//        List<HomePageEsfDo> homePageEsfDos = homePageRestService.getHomePageEsf();
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageEsfDos));
//        List<HomePageEsfResponse> homePageEsfResponseList = JSONObject.parseArray(json.toJSONString(), HomePageEsfResponse.class);
//        HomePageEsfResponseList homePageEsfResponseList1 = new HomePageEsfResponseList();
//        homePageEsfResponseList1.setHomePageEsfResponse(homePageEsfResponseList);
//        homePageEsfResponseList1.setTotal(homePageEsfResponseList.size());
//        return new ResponseEntity<HomePageEsfResponseList>(homePageEsfResponseList1, HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<HomePageMustBuyList> getHomePageLowerPrice() {
//        List<HomePageMustBuyDo> homePageLowerPriceDos = homePageRestService.getHomePageMustBuy(2);
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageLowerPriceDos));
//        List<HomePageMustBuyResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageMustBuyResponse.class);
//        HomePageMustBuyList homePageMustBuyList = new HomePageMustBuyList();
//        homePageMustBuyList.setHomePageMustBuyResponses(newHouseListResponses);
//        homePageMustBuyList.setTotal(newHouseListResponses.size());
//        return new ResponseEntity<HomePageMustBuyList>(homePageMustBuyList, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<HomePageNewHouseResponseList> getHomePageNewHouse() {
        NewHouseListDomain newHouseListDomain = homePageRestService.getHomePageNewHouse(CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseListDomain.getData()));
        List<HomePageNewHouseResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageNewHouseResponse.class);
        HomePageNewHouseResponseList homePageNewHouseResponseList = new HomePageNewHouseResponseList();
        homePageNewHouseResponseList.setNewHouseListResponses(newHouseListResponses);
        homePageNewHouseResponseList.setTotal(newHouseListResponses.size());
        return new ResponseEntity<HomePageNewHouseResponseList>(homePageNewHouseResponseList, HttpStatus.OK);
    }

    //1
    @Override
    public ResponseEntity<HomePageNewCountResponse> getNew() {
        HomePageNewCountDo pageNewCountDo = countService.getNewCount(CityUtils.getCity());
        HomePageNewCountResponse response = new HomePageNewCountResponse();
        BeanUtils.copyProperties(pageNewCountDo, response);
        return new ResponseEntity<HomePageNewCountResponse>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<StringDataResponse> getTradeQuotations() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String cityCode = CityUtils.getCity();
        String TradeQuotations = "TradeQuotations_" + cityCode;
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData(redis.getValue(TradeQuotations));
        //JSONObject res =  JSONObject.parseObject(redis.getValue(TradeQuotations));
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<SellHouseSearchDomainResponse> homePageEsfSearch(@ApiParam(value = "BaseQueryRequest", required = true) @Valid @RequestBody BaseQueryRequest baseQueryRequest) {
//        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
//        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
//        BeanUtils.copyProperties(baseQueryRequest, sellHouseDoQuery);
//        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery, CityUtils.getCity());
//        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
//        return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<HomePageNearEsfListResponse> homePageNearEsf(@ApiParam(value = "NearHouseRequest", required = true) @Valid @RequestBody NearHouseRequest nearHouseRequest) {
        NearHouseDoQuery nearHouseDoQuery = new NearHouseDoQuery();
        BeanUtils.copyProperties(nearHouseRequest, nearHouseDoQuery);
        HomePageNearEsfListDo homePageNearEsf = homePageRestService.getHomePageNearEsf(nearHouseDoQuery, CityUtils.getCity());
        HomePageNearEsfListResponse homePageNearEsfListResponse = JSON.parseObject(JSON.toJSONString(homePageNearEsf), HomePageNearEsfListResponse.class);
        return new ResponseEntity<HomePageNearEsfListResponse>(homePageNearEsfListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomePageNearPlotListResponse> homePageNearPlot(@ApiParam(value = "NearHouseRequest", required = true) @Valid @RequestBody NearHouseRequest nearHouseRequest) {
        NearHouseDoQuery nearHouseDoQuery = new NearHouseDoQuery();
        BeanUtils.copyProperties(nearHouseRequest, nearHouseDoQuery);
        HomePageNearPlotListDo homePageNearPlot = homePageRestService.getHomePageNearPlot(nearHouseDoQuery, CityUtils.getCity());
        HomePageNearPlotListResponse homePageNearPlotListResponse = JSON.parseObject(JSON.toJSONString(homePageNearPlot), HomePageNearPlotListResponse.class);
        return new ResponseEntity<HomePageNearPlotListResponse>(homePageNearPlotListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomePageNearPlotResponse> plotSpecialPage(@ApiParam(value = "NearHouseSpecialPageRequest", required = true) @Valid @RequestBody NearHouseSpecialPageRequest nearHouseSpecialPageRequest) {
        NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery = new NearHouseSpecialPageDoQuery();
        BeanUtils.copyProperties(nearHouseSpecialPageRequest, nearHouseSpecialPageDoQuery);
        HomePageNearPlotDo plotSpecialPage = homePageRestService.getPlotSpecialPage(nearHouseSpecialPageDoQuery, CityUtils.getCity());
        HomePageNearPlotResponse homePageNearPlotResponse = JSON.parseObject(JSON.toJSONString(plotSpecialPage), HomePageNearPlotResponse.class);
        return new ResponseEntity<HomePageNearPlotResponse>(homePageNearPlotResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecommendTopicDomain> queryRecommendTopic(@ApiParam(value = "RecommendRequest", required = true) @Valid @RequestBody RecommendRequest recommendRequest) {
        RecommendTopicDoQuery recommendTopicDoQuery = new RecommendTopicDoQuery();
        BeanUtils.copyProperties(recommendRequest, recommendTopicDoQuery);
        RecommendTopicDomain recommendTopicDomain = recommendRestService.getRecommendTopic(recommendTopicDoQuery, CityUtils.getCity());
        return new ResponseEntity<RecommendTopicDomain>(recommendTopicDomain, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomePageTop50DoMap> top50() {
        Map<String, HomePageTop50Do> homePageTop50Dos = homePageRestService.getHomePageTop50();
        HomePageTop50DoMap homePageTop50DoMap = new HomePageTop50DoMap();
        homePageTop50DoMap.setHomePageTop50DoMap(homePageTop50Dos);
        homePageTop50DoMap.setTotal(homePageTop50Dos.size());
        return new ResponseEntity<HomePageTop50DoMap>(homePageTop50DoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "RecommendEsf5Request", required = true) @Valid @RequestBody RecommendEsf5Request recommendEsf5Request) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        RecommendEsf5DoQuery recommendEsf5DoQuery = new RecommendEsf5DoQuery();
        BeanUtils.copyProperties(recommendEsf5Request, recommendEsf5DoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getRecommendEsf5(recommendEsf5DoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotDetailsFewDoList> getPlotByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
        List<PlotDetailsFewDo> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewDo.class);
        PlotDetailsFewDoList plotDetailsFewDoList = new PlotDetailsFewDoList();
        plotDetailsFewDoList.setPlotDetailsFewDos(plotDetailsFewDos);
        plotDetailsFewDoList.setTotal(plotDetailsFewDos.size());
        return new ResponseEntity<PlotDetailsFewDoList>(plotDetailsFewDoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewHouseDetailResponse> getOneNewHouseByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        NewHouseDetailDo newHouseDetailDo = newHouseService.getOneNewHouseByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
        return new ResponseEntity<NewHouseDetailResponse>(newHouseDetailResponse, HttpStatus.OK);
    }

}
