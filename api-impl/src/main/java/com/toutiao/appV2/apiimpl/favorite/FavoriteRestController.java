package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.appV2.api.favorite.FavoriteRestApi;
import com.toutiao.appV2.model.favorite.UserCenterFavoriteCountResponse;
import com.toutiao.web.common.util.JSONUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
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
}

