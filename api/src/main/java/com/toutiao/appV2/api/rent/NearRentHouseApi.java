package com.toutiao.appV2.api.rent;

import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.appV2.model.rent.NearHouseListRequest;
import com.toutiao.web.common.restmodel.NashResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by CuiShihao on 2018/11/16
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Api(value = "rest", description = "租房附近5km")
public interface NearRentHouseApi {

    @ApiOperation(value = "附近5km出租房源", nickname = "getNearRentHouseByLocation", notes = "", response = RentDetailsListDo.class, tags={ "near-rent-house-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentDetailsListDo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/nearby/getNearRentHouseByLocation",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RentDetailsListDo> getNearRentHouseByLocation(@ApiParam(value = "nearHouseListRequest"  )  @Valid @RequestBody NearHouseListRequest nearHouseListRequest);
}
