package com.toutiao.appV2.api.sellhouse;

import com.toutiao.appV2.model.sellhouse.SellHouseRequest;
import com.toutiao.appV2.model.sellhouse.SellHouseResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "二手房", description = "二手房专题接口")
public interface SellHouseDetailTopicsRestApi {

    @ApiOperation(value = "小区附近专题", nickname = "getNearbyTopicsSellHouseDetail", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/topic/getNearbyTopicsSellHouseDetail",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getNearbyTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "降价专题", nickname = "getCutPriceTopicsSellHouseDetail", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/topic/getCutPriceTopicsSellHouseDetail",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getCutPriceTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "洼地专题", nickname = "getLowPriceTopicsSellHouseDetail", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/topic/getLowPriceTopicsSellHouseDetail",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getLowPriceTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "逢出必抢", nickname = "getMustRobTopicsSellHouseDetail", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/topic/getMustRobTopicsSellHouseDetail",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getMustRobTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

    @ApiOperation(value = "商圈户型", nickname = "getAreaRoomTopicsSellHouseDetail", notes = "", response = SellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/topic/getAreaRoomTopicsSellHouseDetail",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseResponse> getAreaRoomTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult);

}
