package com.toutiao.appV2.api.sellhouse;

import com.toutiao.appV2.model.sellhouse.HouseBusinessAndRoomRequest;
import com.toutiao.appV2.model.sellhouse.HouseBusinessAndRoomResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/16.
 */
@Api(value = "HouseBusinessAndRoomRestApi", description = "获取商圈+户型房源专题列表")
public interface HouseBusinessAndRoomRestApi {

    @ApiOperation(value = "获取商圈+户型房源专题列表", nickname = "getHouseBusinessAndRoomHouses", notes = "", response = HouseBusinessAndRoomResponse.class, tags = {"sell-house-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseBusinessAndRoomResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/esf/houseBusinessAndRoom/getHouseBusinessAndRoomHouses",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseBusinessAndRoomResponse> getHouseBusinessAndRoomHouses(@ApiParam(value = "houseBusinessAndRoomRequest", required = true) @Valid HouseBusinessAndRoomRequest houseBusinessAndRoomRequest, BindingResult bindingResult);


}
