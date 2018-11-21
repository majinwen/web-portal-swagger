package com.toutiao.appV2.api.sellhouse;

import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.appV2.model.sellhouse.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ApiOperation(value = "认领二手房房源经纪人", nickname = "getAgentBySellHouseId", notes = "", response = AgentsBySellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgentsBySellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getAgentBySellHouseId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<AgentsBySellHouseResponse> getAgentBySellHouseId(@ApiParam(value = "agentSellHouseRequest", required = true) @Valid AgentSellHouseRequest agentSellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房房源默认列表", nickname = "getSellHouseByChoose", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseByChoose",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getSellHouseByChoose(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid UserFavoriteConditionRequest userFavoriteConditionRequest, BindingResult bindingResult);

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
    ResponseEntity<SellHouseResponse> getRecommendSellHouse(@ApiParam(value = "sellHouseRequest", required = true) @Valid @RequestBody SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "二手房搜索结果列表", nickname = "getSellHouseList", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getSellHouseList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseSearchDomainResponse> getSellHouseList(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "逢出必抢专题页", nickname = "getBeSureToSnatchList", notes = "", response = SellHouseBeSureToSnatchResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseBeSureToSnatchResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getBeSureToSnatchList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseBeSureToSnatchResponse> getBeSureToSnatchList(@ApiParam(value = "sellHouseBeSureToSnatchRequest", required = true) @Valid SellHouseBeSureToSnatchRequest sellHouseBeSureToSnatchRequest, BindingResult bindingResult);

    @ApiOperation(value = "获取推荐房源5条", nickname = "getRecommendEsf5", notes = "", response = SellHouseSearchDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseSearchDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getRecommendEsf5",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "recommendEsf5Request", required = true) @Valid RecommendEsf5Request recommendEsf5Request, BindingResult bindingResult);


}
