package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.CancelFavoriteHouseDto;
import com.toutiao.app.domain.favorite.FavoriteHouseDomain;
import com.toutiao.app.domain.favorite.FavoriteHouseListDoQuery;
import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.appV2.api.favorite.FavoriteRestApi;
import com.toutiao.appV2.model.favorite.*;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
        UserBasic user = UserBasic.getCurrent();
        FavoriteHouseResponse favoriteHouseResponse = new FavoriteHouseResponse();

        if (null != user) {
            FavoriteHouseListDoQuery favoriteHouseDoQuery = new FavoriteHouseListDoQuery();
            favoriteHouseDoQuery.setUserId(Integer.valueOf(user.getUserId()));
            favoriteHouseDoQuery.setPageNum(favoriteHouseRequest.getPageNum());
            favoriteHouseDoQuery.setSize(favoriteHouseRequest.getPageSize());
            FavoriteHouseDomain favoriteHouseDomain = favoriteRestService.queryFavoriteHouseList(favoriteHouseDoQuery);
            favoriteHouseResponse.setList(favoriteHouseDomain.getList());
            favoriteHouseResponse.setTotalNum(favoriteHouseDomain.getTotalCount());
            return new ResponseEntity<>(favoriteHouseResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
    }

    /**
     * 取消收藏房源
     * @param cancelFavoriteHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> cancelFavoriteHouse(@ApiParam(value = "cancelFavoriteHouseRequest", required = true) @Valid @RequestBody CancelFavoriteHouseRequest cancelFavoriteHouseRequest) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }

        CancelFavoriteHouseDto cancelFavoriteHouseDto = new CancelFavoriteHouseDto();
        BeanUtils.copyProperties(cancelFavoriteHouseRequest, cancelFavoriteHouseDto);
        cancelFavoriteHouseDto.setUserId(Integer.valueOf(user.getUserId()));
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        Integer flag = favoriteRestService.cancelFavoriteHouse(cancelFavoriteHouseDto);
        if (flag == 1) {
            log.info("取消收藏成功");
            changeFavoriteResponse.setMsg("取消收藏成功");
        } else {
            log.info("取消收藏失败");
            throw new BaseException(40001,"取消收藏失败");
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }
}

