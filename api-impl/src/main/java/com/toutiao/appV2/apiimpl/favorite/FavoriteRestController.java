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
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @return NashResult
     */
    @Override
    public ResponseEntity<UserCenterFavoriteCountResponse> getFavoriteCountByUser(@ApiParam(value = "userId", required = true) @RequestParam(value = "userId") Integer userId) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", userId);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserCenterFavoriteCountResponse userCenterFavoriteCountResponse = new UserCenterFavoriteCountResponse();
                UserCenterFavoriteCountDo userCenterFavoriteCountDo = favoriteRestService.getFavoriteCountByUser(userId);
                BeanUtils.copyProperties(userCenterFavoriteCountDo, userCenterFavoriteCountResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(userCenterFavoriteCountResponse));
                return new ResponseEntity<>(userCenterFavoriteCountResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}

