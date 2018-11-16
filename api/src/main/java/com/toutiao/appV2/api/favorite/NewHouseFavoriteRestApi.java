package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "NewHouseFavoriteRestApi", description = "查询新房接口")
public interface NewHouseFavoriteRestApi {

    @ApiOperation(value = "新房收藏列表", nickname = "getNewHouseFavoriteByUserId", notes = "新房收藏列表", tags = {"new-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/getNewHouseFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<NewHouseFavoriteListResponse> getNewHouseFavoriteByUserId(@ApiParam(value = "newHouseFavoriteListRequest", required = true) @Validated NewHouseFavoriteListRequest newHouseFavoriteListRequest);


    @ApiOperation(value = "添加新房收藏", nickname = "addNewHouseFavorite", notes = "添加新房收藏", tags = {"new-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/addNewHouseFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> addNewHouseFavorite(@ApiParam(value = "newHouseAddFavoriteRequest", required = true) @Validated @RequestBody NewHouseAddFavoriteRequest newHouseAddFavoriteRequest);

    @ApiOperation(value = "新房取消收藏", nickname = "cancelFavoriteByNewHouse", notes = "新房取消收藏", tags = {"new-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/cancelFavoriteByNewHouse",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> cancelFavoriteByNewHouse(@ApiParam(value = "cancelFavoriteRequest", required = true) @Validated @RequestBody CancelFavoriteRequest cancelFavoriteRequest);


    @ApiOperation(value = "判断新房是否被收藏", nickname = "getNewHouseIsFavorite", notes = "判断新房是否被收藏", tags = {"new-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/getNewHouseIsFavorite",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> getNewHouseIsFavorite(@ApiParam(value = "newHouseIsFavoriteRequest", required = true) @Validated NewHouseIsFavoriteRequest newHouseIsFavoriteRequest);


}
