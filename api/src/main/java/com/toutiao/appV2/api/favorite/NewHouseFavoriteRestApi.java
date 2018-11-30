package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "收藏", description = "收藏接口")
public interface NewHouseFavoriteRestApi {

    @ApiOperation(value = "新房收藏列表", nickname = "getNewHouseFavoriteByUserId", notes = "新房收藏列表", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/getNewHouseFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<NewHouseFavoriteListResponse> getNewHouseFavoriteByUserId(@ApiParam(value = "newHouseFavoriteListRequest", required = true) @Valid NewHouseFavoriteListRequest newHouseFavoriteListRequest, BindingResult bindingResult);


    @ApiOperation(value = "新房添加收藏", nickname = "addNewHouseFavorite", notes = "新房添加收藏", tags = {"收藏",})
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
    ResponseEntity<ChangeFavoriteResponse> addNewHouseFavorite(@ApiParam(value = "newHouseAddFavoriteRequest", required = true) @Valid @RequestBody NewHouseAddFavoriteRequest newHouseAddFavoriteRequest, BindingResult bindingResult);

    @ApiOperation(value = "新房取消收藏", nickname = "cancelNewHouseFavorite", notes = "新房取消收藏", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/cancelNewHouseFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<ChangeFavoriteResponse> cancelFavoriteByNewHouse(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest, BindingResult bindingResult);


    @ApiOperation(value = "判断新房是否被收藏", nickname = "getNewHouseIsFavorite", notes = "判断新房是否被收藏", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/newhouse/getNewHouseIsFavorite",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<QueryFavoriteResponse> getNewHouseIsFavorite(@ApiParam(value = "newHouseIsFavoriteRequest", required = true) @Valid NewHouseIsFavoriteRequest newHouseIsFavoriteRequest, BindingResult bindingResult);


}
