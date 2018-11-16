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
@Api(value = "SellHouseFavoriteRestApi", description = "查询二手房接口")
public interface SellHouseFavoriteRestApi {

    @ApiOperation(value = "二手房收藏列表", nickname = "getEsfFavoriteByUserId", notes = "二手房收藏列表", tags = {"sell-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/getEsfFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseFavoriteListResponse> getEsfFavoriteByUserId(@ApiParam(value = "sellHouseFavoriteListRequest", required = true) @Validated SellHouseFavoriteListRequest sellHouseFavoriteListRequest);


    @ApiOperation(value = "二手房添加收藏", nickname = "addEsfFavorite", notes = "二手房添加收藏", tags = {"sell-house-favorite-rest-controller",})
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
    ResponseEntity<String> addEsfFavorite(@ApiParam(value = "addFavorite", required = true) @Validated @RequestBody AddFavorite addFavorite);

    @ApiOperation(value = "二手房取消收藏", nickname = "deleteEsfFavoriteByEsfIdAndUserId", notes = "二手房取消收藏", tags = {"sell-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/deleteEsfFavoriteByEsfIdAndUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> deleteEsfFavoriteByEsfIdAndUserId(@ApiParam(value = "deleteEsfFavoriteRequest", required = true) @Validated DeleteEsfFavoriteRequest deleteEsfFavoriteRequest);

    @ApiOperation(value = "判断二手房是否被收藏", nickname = "getIsFavoriteByEsf", notes = "判断二手房是否被收藏", tags = {"sell-house-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/esf/getIsFavoriteByEsf",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> getIsFavoriteByEsf(@ApiParam(value = "isFavoriteRequest", required = true) @Validated IsFavoriteRequest isFavoriteRequest);


}
