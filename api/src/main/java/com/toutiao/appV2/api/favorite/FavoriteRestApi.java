package com.toutiao.appV2.api.favorite;

import com.toutiao.appV2.model.favorite.UserCenterFavoriteCountResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



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
    ResponseEntity<UserCenterFavoriteCountResponse> getFavoriteCountByUser(@ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = false) Integer userId);

}
