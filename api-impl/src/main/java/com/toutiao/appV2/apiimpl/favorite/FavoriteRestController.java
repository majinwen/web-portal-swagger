package com.toutiao.appV2.apiimpl.favorite;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.favorite.FavoriteHouseDomain;
import com.toutiao.app.domain.favorite.FavoriteHouseListDoQuery;
import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.appV2.api.favorite.FavoriteRestApi;
import com.toutiao.appV2.model.favorite.FavoriteHouseRequest;
import com.toutiao.appV2.model.favorite.FavoriteHouseResponse;
import com.toutiao.appV2.model.favorite.UserCenterFavoriteCountResponse;
import com.toutiao.appV2.model.userbasic.UserLoginResponse;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.JSONUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class FavoriteRestController implements FavoriteRestApi {


    private final HttpServletRequest request;

    @Autowired
    private FavoriteRestService favoriteRestService;

    @Autowired
    public FavoriteRestController(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 个人中心租房，二手房，新房，小区，收藏数量
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity<UserCenterFavoriteCountResponse> getFavoriteCountByUser(@ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = false) Integer userId) {
        UserCenterFavoriteCountResponse userCenterFavoriteCountResponse = new UserCenterFavoriteCountResponse();
        UserCenterFavoriteCountDo userCenterFavoriteCountDo = favoriteRestService.getFavoriteCountByUser(userId);
        BeanUtils.copyProperties(userCenterFavoriteCountDo, userCenterFavoriteCountResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(userCenterFavoriteCountResponse));
        return new ResponseEntity<>(userCenterFavoriteCountResponse, HttpStatus.OK);
    }

    /**
     * 查询我的收藏房源
     *
     * @param favoriteHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<FavoriteHouseResponse> queryFavoriteHouseList(@ApiParam(value = "favoriteHouseRequest", required = true) FavoriteHouseRequest favoriteHouseRequest) {

        // 查询登录用户信息
        String userString = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse user = JSONObject.parseObject(userString, UserLoginResponse.class);
        FavoriteHouseResponse favoriteHouseResponse = new FavoriteHouseResponse();

        if (null != user) {
            FavoriteHouseListDoQuery favoriteHouseDoQuery = new FavoriteHouseListDoQuery();
            favoriteHouseDoQuery.setUserId(Integer.valueOf(user.getUserId()));
            favoriteHouseDoQuery.setPageNum(favoriteHouseRequest.getPageNum());
            favoriteHouseDoQuery.setSize(favoriteHouseRequest.getSize());
            FavoriteHouseDomain favoriteHouseDomain = favoriteRestService.queryFavoriteHouseList(favoriteHouseDoQuery);
            favoriteHouseResponse.setList(favoriteHouseDomain.getList());
            favoriteHouseResponse.setTotalCount(favoriteHouseDomain.getTotalCount());
            return new ResponseEntity<>(favoriteHouseResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN);
        }
    }
}

