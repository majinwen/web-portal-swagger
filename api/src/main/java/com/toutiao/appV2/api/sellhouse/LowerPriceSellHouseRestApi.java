package com.toutiao.appV2.api.sellhouse;

import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseRequest;
import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "二手房", description = "获取捡漏房数据")
public interface LowerPriceSellHouseRestApi {

    @ApiOperation(value = "获取捡漏房数据", nickname = "getLowerPriceShellHouse", notes = "", response = MustBuyShellHouseResponse.class, tags = {"二手房",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MustBuyShellHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/lowerPrice/getLowerPriceShellHouse",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<MustBuyShellHouseResponse> getLowerPriceShellHouse(@ApiParam(value = "mustBuyShellHouseRequest", required = true) @Valid MustBuyShellHouseRequest mustBuyShellHouseRequest, BindingResult bindingResult);


}
