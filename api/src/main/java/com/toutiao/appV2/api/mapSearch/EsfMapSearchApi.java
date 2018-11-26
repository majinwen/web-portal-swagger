/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.mapSearch;

import com.toutiao.appV2.model.mapSearch.EsfHouseListResponse;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchRequest;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T09:35:01.637Z")

@Api(value = "二手房", description = "地图找房-二手房")
public interface EsfMapSearchApi {

    @ApiOperation(value = "二手房", nickname = "mapEsfSearch", notes = "", response = EsfMapSearchResponse.class, tags={ "地图找房", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = EsfMapSearchResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/esf/search", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<EsfMapSearchResponse> mapEsfSearch(@ApiParam(value = "esfMapSearchDoRequest", required = true) @Valid  EsfMapSearchRequest esfMapSearchRequest);

    @ApiOperation(value = "二手房-小区房源列表", nickname = "mapEsfSearchList", notes = "", response = EsfHouseListResponse.class, tags={ "地图找房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EsfHouseListResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/esf/list", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<EsfHouseListResponse> mapEsfHouseList(@ApiParam(value = "esfMapSearchDoRequest", required = true) @Valid  EsfMapSearchRequest esfMapSearchRequest);

}
