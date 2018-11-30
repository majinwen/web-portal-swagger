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
public interface SellHouseFavoriteRestApi {

    @ApiOperation(value = "二手房收藏列表", nickname = "getEsfFavoriteByUserId", notes = "二手房收藏列表", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/getEsfFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<SellHouseFavoriteListResponse> getEsfFavoriteByUserId(@ApiParam(value = "sellHouseFavoriteListRequest", required = true) @Valid SellHouseFavoriteListRequest sellHouseFavoriteListRequest, BindingResult bindingResult);


    @ApiOperation(value = "二手房添加收藏", nickname = "addEsfFavorite", notes = "二手房添加收藏", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/addEsfFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ChangeFavoriteResponse> addEsfFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody SellHouseAddFavoriteRequest addFavorite, BindingResult bindingResult);

    @ApiOperation(value = "二手房取消收藏", nickname = "cancelEsfFavorite", notes = "二手房取消收藏", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/cancelEsfFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    @ApiIgnore
    ResponseEntity<ChangeFavoriteResponse> deleteEsfFavoriteByEsfIdAndUserId(@ApiParam(value = "deleteEsfFavoriteRequest", required = true) @Valid @RequestBody DeleteEsfFavoriteRequest deleteEsfFavoriteRequest, BindingResult bindingResult);

    @ApiOperation(value = "判断二手房是否被收藏", nickname = "getIsFavoriteByEsf", notes = "判断二手房是否被收藏", tags = {"收藏",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/getIsFavoriteByEsf",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<QueryFavoriteResponse> getIsFavoriteByEsf(@ApiParam(value = "isFavoriteRequest", required = true) @Valid IsFavoriteRequest isFavoriteRequest, BindingResult bindingResult);


}
