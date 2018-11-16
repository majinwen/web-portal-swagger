package com.toutiao.appV2.api.newhouse;

import com.toutiao.app.api.chance.request.newhouse.NewHouseLayoutRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutCountResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseLayoutResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zym
 */
@Api(value = "NewHouseLayoutApi", description = "新房户型控制层")
public interface NewHouseLayoutApi {
    @ApiOperation(value = "获取楼盘下户型数", nickname = "getLayoutCountByNewHouseId", notes = "获取楼盘下户型数", response = NewHouseLayoutCountResponse.class,
            tags={ "new-house-layout-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseLayoutCountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/layout/getLayoutCountByNewHouseId",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseLayoutCountResponse> getLayoutCountByNewHouseId(NewHouseLayoutRequest newHouseLayoutRequest);

    @ApiOperation(value = "获取楼盘下某户型房源", nickname = "getNewHouseLayoutByNewCode", notes = "获取楼盘下某户型房源", response = GetNewHouseLayoutResponse.class, tags={ "new-house-layout-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetNewHouseLayoutResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/layout/getLayoutByNewHouseId",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<GetNewHouseLayoutResponse> getNewHouseLayoutByNewCode(NewHouseLayoutRequest newHouseLayoutRequest);
}
