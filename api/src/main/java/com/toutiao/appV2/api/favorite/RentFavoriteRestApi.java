package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "RentFavoriteRestApi", description = "查询租房接口")
public interface RentFavoriteRestApi {

    @ApiOperation(value = "租房收藏列表", nickname = "getRentFavoriteByUserId", notes = "租房收藏列表", tags = {"rent-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/rent/getRentFavoriteByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<RentFavoriteListResponse> getRentFavoriteByUserId(@ApiParam(value = "rentFavoriteListRequest", required = true) @Valid @RequestBody RentFavoriteListRequest rentFavoriteListRequest);


    @ApiOperation(value = "租房添加收藏", nickname = "addRentFavorite", notes = "租房添加收藏", tags = {"rent-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/rent/addRentFavorite",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> addRentFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody AddFavorite addFavorite);

    @ApiOperation(value = "租房取消收藏", nickname = "deleteRentFavoriteByRentIdAndUserId", notes = "租房取消收藏", tags = {"rent-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/rent/deleteRentFavoriteByRentIdAndUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> deleteRentFavoriteByRentIdAndUserId(@ApiParam(value = "deleteRentFavoriteRequest", required = true) @Valid @RequestBody DeleteRentFavoriteRequest deleteRentFavoriteRequest);

    @ApiOperation(value = "判断租房是否被收藏", nickname = "getIsFavoriteByRent", notes = "判断租房是否被收藏", tags = {"rent-favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/rent/getIsFavoriteByRent",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Boolean> getIsFavoriteByRent(@ApiParam(value = "isFavoriteRequest", required = true) @Valid @RequestBody IsFavoriteRequest isFavoriteRequest);


}
