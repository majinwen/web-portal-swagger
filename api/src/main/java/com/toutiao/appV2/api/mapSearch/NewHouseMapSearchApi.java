/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.mapSearch;

import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchBuildResponse;
import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchDistrictResponse;
import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:59:38.870Z")

@Api(value = "地图找房-新房", description = "地图找房-新房")
public interface NewHouseMapSearchApi {

    @ApiOperation(value = "新房-楼盘详细信息", nickname = "getNewHouseMapSearchByBuild", notes = "", response = NewHouseMapSearchBuildResponse.class, tags={ "地图找房", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NewHouseMapSearchBuildResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/newhouse/build/search", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<NewHouseMapSearchBuildResponse> getNewHouseMapSearchByBuild(@ApiParam(value = "newHouseMapSearchRequest", required = true) @Valid NewHouseMapSearchRequest newHouseMapSearchRequest);


    @ApiOperation(value = "区域-新房信息", nickname = "getNewHouseMapSearchByDistrict", notes = "", response = NewHouseMapSearchDistrictResponse.class, tags={ "地图找房", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NewHouseMapSearchDistrictResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/newhouse/district/search", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<NewHouseMapSearchDistrictResponse> getNewHouseMapSearchByDistrict(@ApiParam(value = "newHouseMapSearchRequest", required = true) @Valid NewHouseMapSearchRequest newHouseMapSearchRequest);


    @ApiOperation(value = "新房地铁", nickname = "getNewHouseSubway", notes = "", response = NewHouseMapSearchDistrictResponse.class, tags={ "地图找房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseMapSearchDistrictResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/newhouse/subway/search", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<NewHouseMapSearchDistrictResponse> getNewHouseSubway(@ApiParam(value = "newHouseMapSearchRequest", required = true) @Valid NewHouseMapSearchRequest newHouseMapSearchRequest);



}
