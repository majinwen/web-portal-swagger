package com.toutiao.appV2.api.sellhouse;

import com.toutiao.appV2.model.sellhouse.NearBySellHouseDomainResponse;
import com.toutiao.appV2.model.sellhouse.NearBySellHousesRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "二手房", description = "二手房附近5km列表")
public interface NearSellHouseRestApi {

    @ApiOperation(value = "二手房附近5km列表", nickname = "getNearBySellHouses", notes = "", response = NearBySellHouseDomainResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NearBySellHouseDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/nearby/getNearBySellHouses",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<NearBySellHouseDomainResponse> getNearBySellHouses(@ApiParam(value = "nearBySellHousesRequest", required = true) @Valid NearBySellHousesRequest nearBySellHousesRequest, BindingResult bindingResult);


}
