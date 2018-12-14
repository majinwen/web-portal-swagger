/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.Intelligence;


import com.toutiao.appV2.model.HomePage.NewHouseDetailResponse;
import com.toutiao.appV2.model.HomePage.PlotDetailsFewDoList;
import com.toutiao.appV2.model.HomePage.RecommendEsf5Request;
import com.toutiao.appV2.model.HomePage.SellHouseSearchDomainResponse;
import com.toutiao.appV2.model.Intelligence.*;
import com.toutiao.appV2.model.StringDataResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:50:01.627Z")

@Api(value = "推荐条件", description = "推荐条件")
public interface ConditionApi {

    @ApiOperation(value = "删除推荐条件", nickname = "deleteRecommendCondition", notes = "", response = Integer.class, tags={ "定制", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Integer.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/homePage/deleteRecommendCondition",
        produces = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<StringDataResponse> deleteRecommendCondition(@ApiParam(value = "userId", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId,
                                                                @ApiParam(value = "conditionType", required = true) @Valid @RequestParam(value = "conditionType", required = true) Integer conditionType);

    @ApiOperation(value = "获取推荐条件", nickname = "getRecommendCondition", notes = "", response = UserFavoriteConditionResponse.class, tags={ "定制", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserFavoriteConditionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/homePage/getRecommendCondition",
        produces = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<UserFavoriteConditionResponse> getRecommendCondition(@ApiParam(value = "userId", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId,
                                                                        @ApiParam(value = "conditionType", required = true) @Valid @RequestParam(value = "conditionType", required = true) Integer conditionType);


    @ApiOperation(value = "保存更新推荐条件", nickname = "getNewHouseList", notes = "",
            response = CustomConditionUserSampleResponse.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomConditionUserSampleResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/homePage/saveRecommendCondition",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = {RequestMethod.POST})
    ResponseEntity<CustomConditionUserSampleResponse> saveRecommendCondition(@RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest);



    @ApiOperation(value = "定制条件筛选结果数量", nickname = "getCustomCondition", notes = "", response = CustomConditionCountResponse.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomConditionCountResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/homePage/getCustomCondition",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<CustomConditionCountResponse> getCustomCondition(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid UserFavoriteConditionRequest userFavoriteConditionRequest);

    @ApiOperation(value = "为您推荐房源", nickname = "getRecommendEsf5", notes = "", response = SellHouseSearchDomainResponse.class, tags={ "定制", })
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

    @ApiOperation(value = "获取推荐小区", nickname = "getPlotByRecommendCondition", notes = "", response = PlotDetailsFewDoList.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotDetailsFewDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/plot/getPlotByRecommendCondition",
            produces = { "application/json" },
            //consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotDetailsFewDoList> getPlotByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest" ,required=true )  @Valid com.toutiao.appV2.model.HomePage.UserFavoriteConditionRequest userFavoriteConditionRequest);

    @ApiOperation(value = "根据推荐条件获取一条新房数据", nickname = "getOneNewHouseByRecommendCondition", notes = "", response = NewHouseDetailResponse.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseDetailResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getOneNewHouseByRecommendCondition",
            produces = { "application/json" },
            //consumes = "application/json",
            method = RequestMethod.GET )
    @ApiIgnore
    ResponseEntity<NewHouseDetailResponse> getOneNewHouseByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest" ,required=true )  @Valid com.toutiao.appV2.model.HomePage.UserFavoriteConditionRequest userFavoriteConditionRequest);


    @ApiOperation(value = "二手房定制条件筛选结果分布", nickname = "getEsfCustomConditionDetails", notes = "", response = CustomConditionCountResponse.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CustomConditionCountResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/esf/getEsfCustomConditionDetails",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<CustomConditionDetailsResponse> getEsfCustomConditionDetails(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid com.toutiao.appV2.model.Intelligence.UserFavoriteConditionRequest userFavoriteConditionRequest);

    @ApiOperation(value = "获取专属报告", nickname = "getHomePageReport", notes = "", response = IntelligenceResponse.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = IntelligenceResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/findhouse/byHouseReport/getHomePageReport",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<com.toutiao.appV2.model.Intelligence.IntelligenceResponse> getHomePageReport(@NotNull @ApiParam(value = "reportId", required = true) @Valid @RequestParam(value = "reportId", required = true) String reportId);


    @ApiOperation(value = "保存专属报告", nickname = "saveHomePageReport", notes = "", response = Integer.class, tags={ "定制", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/findhouse/byHouseReport/saveHomePageReport",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    ResponseEntity<StringDataResponse> saveHomePageReport(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest);

}
