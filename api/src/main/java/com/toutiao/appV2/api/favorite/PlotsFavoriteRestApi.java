package com.toutiao.appV2.api.favorite;

import com.toutiao.app.api.chance.response.plot.PlotFavoriteListResponse;
import com.toutiao.appV2.model.favorite.CancelFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotIsFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotsAddFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotsFavoriteListRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "PlotsFavoriteRestApi", description = "查询小区接口")
public interface PlotsFavoriteRestApi {

    @ApiOperation(value = "小区收藏列表", nickname = "getPlotFavoriteByUserId", notes = "小区收藏列表", tags = {"plots-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),

            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/plots/getPlotFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<PlotFavoriteListResponse> getPlotFavoriteByUserId(@ApiParam(value = "plotsFavoriteListRequest", required = true) @Valid @RequestBody PlotsFavoriteListRequest plotsFavoriteListRequest);

    @ApiOperation(value = "判断小区是否被收藏", nickname = "getPlotIsFavoriteByPlotIdAndUserId", notes = "判断小区是否被收藏", tags = {"plots-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/plots/getPlotIsFavoriteByPlotIdAndUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> getPlotIsFavoriteByPlotIdAndUserId(@ApiParam(value = "plotIsFavoriteRequest", required = true) @Valid @RequestBody PlotIsFavoriteRequest plotIsFavoriteRequest);

    @ApiOperation(value = "添加小区收藏", nickname = "addPlotsFavorite", notes = "添加小区收藏", tags = {"plots-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/plots/addPlotsFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> addPlotsFavorite(@ApiParam(value = "plotsAddFavoriteRequest", required = true) @Valid @RequestBody PlotsAddFavoriteRequest plotsAddFavoriteRequest);

    @ApiOperation(value = "小区取消收藏", nickname = "cancelFavoriteByVillage", notes = "小区取消收藏", tags = {"plots-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/plots/cancelFavoriteByVillage",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> cancelFavoriteByVillage(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest);


    @ApiOperation(value = "列表页小区收藏数量", nickname = "getPlotFavoriteCountByPlotId", notes = "列表页小区收藏数量", tags = {"plots-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/plots/getPlotFavoriteCountByPlotId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> getPlotFavoriteCountByPlotId(@ApiParam(value = "buildingId") @Valid @RequestParam(value = "buildingId", required = false) Integer buildingId);


}