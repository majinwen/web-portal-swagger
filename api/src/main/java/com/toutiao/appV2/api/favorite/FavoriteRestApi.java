package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "收藏", description = "收藏接口")
public interface FavoriteRestApi {

    @ApiOperation(value = "个人中心租房，二手房，新房，小区，收藏数量", nickname = "getFavoriteCountByUser", notes = "个人中心租房，二手房，新房，小区，收藏数量", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/getFavoriteCountByUser",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<UserCenterFavoriteCountResponse> getFavoriteCountByUser(@ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = false) Integer userId);

    @ApiOperation(value = "查询我的收藏房源", nickname = "getFavoriteCountByUser", notes = "查询我的收藏房源", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/queryFavoriteHouseList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<FavoriteHouseResponse> queryFavoriteHouseList(@ApiParam(value = "favoriteHouseRequest", required = true) FavoriteHouseRequest favoriteHouseRequest);

    @ApiOperation(value = "取消收藏房源", nickname = "cancelFavoriteHouse", notes = "取消收藏房源", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/cancelFavoriteHouse",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ChangeFavoriteResponse> cancelFavoriteHouse(@ApiParam(value = "cancelFavoriteHouseRequest", required = true) @Valid @RequestBody CancelFavoriteHouseRequest cancelFavoriteHouseRequest);

    @ApiOperation(value = "收藏房源", nickname = "addFavoriteHouse", notes = "收藏房源", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/addFavoriteHouse",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<ChangeFavoriteResponse> addFavoriteHouse(@ApiParam(value = "addFavoriteHouseRequest", required = true) @Valid @RequestBody AddFavoriteHouseRequest addFavoriteHouseRequest);

    @ApiOperation(value = "查询用户收藏房源统计", nickname = "queryFavoriteHouseCount", notes = "查询用户收藏房源统计", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/queryFavoriteHouseCount",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<FavoriteHouseCountResponse> queryFavoriteHouseCount();

}
