package com.toutiao.appV2.api.subway;

import com.toutiao.appV2.model.subway.SubwayLineResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by CuiShihao on 2018/12/13
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Api(value = "地铁", description = "根据地铁线id获取地铁线信息")
public interface SubwayInfoApi {

    @ApiOperation(value = "获取地铁线信息", nickname = "getRentHouseListByUserFavorite", notes = "", response = SubwayLineResponse.class, tags={ "地铁线信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubwayLineResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subway/getSubwayInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<SubwayLineResponse> getRentHouseListByUserFavorite(@ApiParam(value = "lineId" ,required = true)  @Valid @RequestParam(value = "lineId", required = true) Integer lineId);
}
