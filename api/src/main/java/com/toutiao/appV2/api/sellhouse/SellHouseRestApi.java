package com.toutiao.appV2.api.sellhouse;

import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.appV2.model.Intelligence.CustomConditionCountResponse;
import com.toutiao.appV2.model.Intelligence.CustomConditionDetailsResponse;
import com.toutiao.appV2.model.sellhouse.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "二手房", description = "二手房")
public interface SellHouseRestApi {

    @ApiOperation(value = "消息推送二手房列表信息", nickname = "querySellHouseByHouseId", notes = "", response = MessageSellHouseDo.class, responseContainer = "List", tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MessageSellHouseDo.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/querySellHouseByHouseId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<MessageSellHouseResponse> querySellHouseByHouseId(@ApiParam(value = "houseId", required = true) @RequestParam(value = "houseId", required = false) String houseId);

    @ApiOperation(value = "二手房房源详情", nickname = "getSellHouseByHouseId", notes = "", response = SellHouseDetailsResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseDetailsResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseByHouseId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseDetailsResponse> getSellHouseByHouseId(@ApiParam(value = "sellHouseDerailsRequest", required = true) @Valid SellHouseDetailsRequest sellHouseDerailsRequest, BindingResult bindingResult);

    @ApiOperation(value = "逛逛二手房列表", nickname = "getSellHouseByChoose", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseByChoose",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<SellHouseSearchDomainResponse> getSellHouseByChoose(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid UserFavoriteConditionRequest userFavoriteConditionRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房默认列表推荐（广告）", nickname = "getRecommendSellHouse", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getRecommendSellHouse",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<SellHouseResponse> getRecommendSellHouse(@ApiParam(value = "sellHouseRequest", required = true) @Valid @RequestBody SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房搜索结果列表", nickname = "getSellHouseList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseList",
            produces = {"application/json"},
            method = {RequestMethod.GET})
    ResponseEntity<SellHouseSearchDomainResponse> getSellHouseListGet(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房搜索结果列表", nickname = "getSellHouseList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseList",
            produces = {"application/json"},
            method = {RequestMethod.POST})
    ResponseEntity<SellHouseSearchDomainResponse> getSellHouseListPost(@ApiParam(value = "sellHouseRequest", required = true) @Valid @RequestBody SellHouseRequest sellHouseRequest, BindingResult bindingResult);

//    @ApiOperation(value = "逢出必抢专题页", nickname = "getBeSureToSnatchList", notes = "", response = SellHouseBeSureToSnatchResponse.class, tags = {"二手房",})
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = SellHouseBeSureToSnatchResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found")})
//    @RequestMapping(value = "/rest/esf/getBeSureToSnatchList",
//            produces = {"application/json"},
//            method = RequestMethod.GET)
//    ResponseEntity<SellHouseBeSureToSnatchResponse> getBeSureToSnatchList(@ApiParam(value = "sellHouseBeSureToSnatchRequest", required = true) @Valid SellHouseBeSureToSnatchRequest sellHouseBeSureToSnatchRequest, BindingResult bindingResult);

//    @ApiOperation(value = "获取推荐房源5条", nickname = "getRecommendEsf5", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found")})
//    @RequestMapping(value = "/rest/esf/getRecommendEsf5",
//            produces = {"application/json"},
//            method = RequestMethod.GET)
//    @ApiIgnore
//    ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "recommendEsf5Request", required = true) @Valid RecommendEsf5Request recommendEsf5Request, BindingResult bindingResult);

    @ApiOperation(value = "猜你喜欢:二手房列表", nickname = "getGuessList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getGuessList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<SellHouseSearchDomainResponse> getGuessList(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "逛逛二手房附近列表", nickname = "getNearBySellHouses", notes = "", response = NearBySellHouseDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NearBySellHouseDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/nearby/getNearBySellHouses",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<SellHouseSearchDomainResponse> getNearBySellHouses(@ApiParam(value = "nearBySellHousesRequest", required = true) @Valid NearBySellHousesRequest nearBySellHousesRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房详情:相似好房", nickname = "getSimilarSellHouseList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSimilarSellHouseList",
            produces = {"application/json"},
            method = {RequestMethod.GET})
    ResponseEntity<SellHouseSearchDomainResponse> getSimilarSellHouseListGet(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房详情:相似好房", nickname = "getSimilarSellHouseList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSimilarSellHouseList",
            produces = {"application/json"},
            method = {RequestMethod.POST})
    ResponseEntity<SellHouseSearchDomainResponse> getSimilarSellHouseListPost(@ApiParam(value = "sellHouseRequest", required = true) @Valid @RequestBody SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "猜你喜欢:二手房列表", nickname = "getGuessList", notes = "", response = SellHouseGuessLikeResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseGuessLikeResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getGuessList",
            produces = {"application/json"},
            method = {RequestMethod.POST})
    ResponseEntity<SellHouseGuessLikeResponse> getGuessList(@ApiParam(value = "sellHouseGuessLikeRequest", required = true) @RequestBody SellHouseGuessLikeRequest sellHouseGuessLikeRequest);



}
