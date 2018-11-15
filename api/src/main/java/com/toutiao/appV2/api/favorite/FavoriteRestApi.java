package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.UserCenterFavoriteCountResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by wk on 2018/11/14.
 */
@Api(value = "FavoriteRestApi", description = " 查询收藏数量接口")
public interface FavoriteRestApi {

    @ApiOperation(value = "个人中心租房，二手房，新房，小区，收藏数量", nickname = "getFavoriteCountByUser", notes = "个人中心租房，二手房，新房，小区，收藏数量", tags = {"favorite-rest-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/favorite/getFavoriteCountByUser",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<UserCenterFavoriteCountResponse> getFavoriteCountByUser(@NotNull @ApiParam(value = "userId", name = "用户ID", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId);

}
