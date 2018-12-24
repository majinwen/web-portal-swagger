package com.toutiao.appV2.api.sellhouse;

import com.toutiao.appV2.model.sellhouse.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "二手房", description = "二手房专题")
public interface SellHouseThemeRestApi {

    @ApiOperation(value = "降价房专题", nickname = "getCutPriceShellHouse", notes = "", response = SellHouseThemeResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseThemeResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getCutPriceShellHouse",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseThemeResponse> getCutPriceShellHouse(@ApiParam(value = "sellHouseThemeRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest);


    @ApiOperation(value = "捡漏房专题", nickname = "getLowerPriceShellHouse", notes = "", response = SellHouseThemeResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseThemeResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getLowerPriceShellHouse",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseThemeResponse> getLowerPriceShellHouse(@ApiParam(value = "sellHouseThemeRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest);


    @ApiOperation(value = "逢出必抢专题", nickname = "getBeSureToSnatchShellHouse", notes = "", response = SellHouseThemeResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseThemeResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/getBeSureToSnatchShellHouse",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseThemeResponse> getBeSureToSnatchShellHouse(@ApiParam(value = "sellHouseThemeRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest);

}
