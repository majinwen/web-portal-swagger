/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.mapSearch;

import com.toutiao.appV2.model.mapSearch.EsfMapSearchDistrictResponse;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchDoRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-23T06:53:25.085Z")

@Api(value = "地图找房-二手房", description = "地图找房-二手房")
public interface EsfMapSearchApi {

    @ApiOperation(value = "地图找房-二手房", nickname = "mapEsfSearch", notes = "", response = Object.class, tags={ "地图找房-二手房", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = EsfMapSearchDistrictResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/esf/search", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<Object> mapEsfSearch(@ApiParam(value = "esfMapSearchDoRequest", required = true) @Valid EsfMapSearchDoRequest esfMapSearchDoRequest);

}
