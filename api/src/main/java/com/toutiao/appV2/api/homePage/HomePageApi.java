package com.toutiao.appV2.api.homePage;

import com.toutiao.app.api.chance.response.homepage.HomePageNearPlotListResponse;
import com.toutiao.app.api.chance.response.homepage.HomePageNearPlotResponse;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;
import com.toutiao.appV2.model.HomePage.*;
import com.toutiao.appV2.model.StringDataResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")

@Api(value = "首页", description = "查询首页接口")
public interface HomePageApi {


//    @ApiOperation(value = "逢出必抢系列", nickname = "beSureToSnatch", notes = "", response = HomeSureToSnatchList.class, tags={ "首页", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = HomeSureToSnatchList.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/homePage/beSureToSnatch",
//            produces = { "application/json" },
//            consumes = "application/json",
//            method = RequestMethod.POST)
//    ResponseEntity<HomeSureToSnatchList> beSureToSnatch(@ApiParam(value = "BaseQueryRequest" ,required=true )  @Valid @RequestBody BaseQueryRequest baseQueryRequest);


    @ApiOperation(value = "专题着陆页-附近二手房", nickname = "esfSpecialPage", notes = "", response = HomePageNearEsfListResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNearEsfListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/esfSpecialPage",
            produces = { "application/json" },
            consumes = "application/json",
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<HomePageNearEsfListResponse> esfSpecialPage(@ApiParam(value = "NearHouseSpecialPageRequest" ,required=true )  @Valid @RequestBody NearHouseSpecialPageRequest nearHouseSpecialPageRequest);


    @ApiOperation(value = "统计二手房的相应数量", nickname = "getEsf", notes = "", response = HomePageEsfCountResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageEsfCountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homepage/count/getEsf",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<HomePageEsfCountResponse> getEsf();


//    @ApiOperation(value = "首页获取降价房8条", nickname = "getHomePageCutPrice", notes = "", response = HomePageMustBuyList.class, tags={ "首页", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = HomePageMustBuyList.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/homePage/getHomePageCutPrice",
//            produces = { "application/json" },
//            //consumes = "application/json",
//            method = RequestMethod.GET)
//    ResponseEntity<HomePageMustBuyList> getHomePageCutPrice();


//    @ApiOperation(value = "首页获取二手房推荐5条", nickname = "getHomePageEsf", notes = "", response = HomePageEsfResponseList.class, tags={ "首页", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = HomePageEsfResponseList.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/homePage/getHomePageEsf",
//            produces = { "application/json" },
//           // consumes = "application/json",
//            method = RequestMethod.GET)
//    ResponseEntity<HomePageEsfResponseList> getHomePageEsf();


//    @ApiOperation(value = "首页获取价格洼地8条", nickname = "getHomePageLowerPrice", notes = "", response = HomePageMustBuyList.class, tags={ "首页", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = HomePageMustBuyList.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/homePage/getHomePageLowerPrice",
//            produces = { "application/json" },
//            //consumes = "application/json",
//            method = RequestMethod.GET)
//    ResponseEntity<HomePageMustBuyList> getHomePageLowerPrice();


    @ApiOperation(value = "首页获取新房5条", nickname = "getHomePageNewHouse", notes = "", response = HomePageNewHouseResponseList.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNewHouseResponseList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/homePage/getHomePageNewHouse",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<HomePageNewHouseResponseList> getHomePageNewHouse();


    @ApiOperation(value = "统计新房的相应数量", nickname = "getNew", notes = "", response = HomePageNewCountResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNewCountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homepage/count/getNew",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<HomePageNewCountResponse> getNew();


    @ApiOperation(value = "getTradeQuotations", nickname = "getTradeQuotations", notes = "", response = String.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/getTradeQuotations",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<StringDataResponse> getTradeQuotations();


//    @ApiOperation(value = "首页搜索接口", nickname = "homePageEsfSearch", notes = "", response = SellHouseSearchDomainResponse.class, tags={ "首页", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/homePage/homePageEsfSearch",
//            produces = { "application/json" },
//            //consumes = "application/json",
//            method = RequestMethod.POST)
//    ResponseEntity<SellHouseSearchDomainResponse> homePageEsfSearch(@ApiParam(value = "BaseQueryRequest" ,required=true )  @Valid BaseQueryRequest baseQueryRequest);


    @ApiOperation(value = "首页附近二手房", nickname = "homePageNearEsf", notes = "", response = HomePageNearEsfListResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNearEsfListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/homePageNearEsf",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<HomePageNearEsfListResponse> homePageNearEsf(@ApiParam(value = "NearHouseRequest" ,required=true )  @Valid NearHouseRequest nearHouseRequest);


    @ApiOperation(value = "首页附近小区", nickname = "homePageNearPlot", notes = "", response = HomePageNearPlotListResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNearPlotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/homePageNearPlot",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<HomePageNearPlotListResponse> homePageNearPlot(@ApiParam(value = "NearHouseRequest" ,required=true )  @Valid @RequestBody NearHouseRequest nearHouseRequest);


    @ApiOperation(value = "专题着陆页-附近小区", nickname = "plotSpecialPage", notes = "", response = HomePageNearPlotResponse.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageNearPlotResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/plotSpecialPage",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<HomePageNearPlotResponse> plotSpecialPage(@ApiParam(value = "NearHouseSpecialPageRequest" ,required=true )  @Valid @RequestBody NearHouseSpecialPageRequest nearHouseSpecialPageRequest);


    @ApiOperation(value = "推荐专题", nickname = "queryRecommendTopic", notes = "", response = RecommendTopicDomain.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RecommendTopicDomain.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/recommendTopic/queryRecommendTopic",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<RecommendTopicDomain> queryRecommendTopic(@ApiParam(value = "RecommendRequest" ,required=true )  @Valid @RequestBody RecommendRequest recommendRequest);


    @ApiOperation(value = "首页top50", nickname = "top50", notes = "", response = HomePageTop50DoMap.class, tags={ "首页", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomePageTop50DoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/top50",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<HomePageTop50DoMap> top50();



    @ApiOperation(value = "为您推荐房源", nickname = "getRecommendEsf5", notes = "", response = SellHouseSearchDomainResponse.class, tags={ "首页推荐条件", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/esf/getRecommendEsf5",
            produces = { "application/json" },
            //consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "RecommendEsf5Request" ,required=true )  @Valid RecommendEsf5Request recommendEsf5Request);


    @ApiOperation(value = "获取推荐小区", nickname = "getPlotByRecommendCondition", notes = "", response = PlotDetailsFewDoList.class, tags={ "首页推荐条件", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotDetailsFewDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/plot/getPlotByRecommendCondition",
            produces = { "application/json" },
            //consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotDetailsFewDoList> getPlotByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest" ,required=true )  @Valid UserFavoriteConditionRequest userFavoriteConditionRequest);

    @ApiOperation(value = "根据推荐条件获取一条新房数据", nickname = "getOneNewHouseByRecommendCondition", notes = "", response = NewHouseDetailResponse.class, tags={ "首页推荐条件", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseDetailResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getOneNewHouseByRecommendCondition",
            produces = { "application/json" },
            //consumes = "application/json",
            method = RequestMethod.GET )
    ResponseEntity<NewHouseDetailResponse> getOneNewHouseByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest" ,required=true )  @Valid UserFavoriteConditionRequest userFavoriteConditionRequest);


}
