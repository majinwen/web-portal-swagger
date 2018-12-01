/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.mapSearch;

import com.toutiao.app.api.chance.response.mapSearch.RentOfPlotListResPonse;
import com.toutiao.appV2.model.mapSearch.RentMapSearchDoRequest;
import com.toutiao.appV2.model.mapSearch.RentMapSearchDomainResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")
@Api(value = "rest", description = "the rest API")
public interface RentMapSearchApi {

    @ApiOperation(value = "地图找房-租房", nickname = "mapRentSearch", notes = "", response = RentMapSearchDomainResponse.class, tags={ "地图找房", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = RentMapSearchDomainResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/rent/search",
        produces = "application/json",

        method = RequestMethod.GET)
    ResponseEntity<RentMapSearchDomainResponse> mapRentSearch(@ApiParam(value = "rentMapSearchDoRequest", required = true) @Valid RentMapSearchDoRequest rentMapSearchDoRequest);

    @ApiOperation(value = "地图找房-租房列表", nickname = "getRentOfPlot", notes = "", response = RentOfPlotListResPonse.class, tags={ "地图找房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentOfPlotListResPonse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/rest/map/rent/getRentOfPlot",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<RentOfPlotListResPonse> getRentOfPlot(@ApiParam(value = "rentMapSearchDoRequest", required = true) @Valid  RentMapSearchDoRequest rentMapSearchDoRequest);

}
