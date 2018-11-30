package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.NewHouseAddFavoriteDoQuery;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.NewHouseFavoriteRestService;
import com.toutiao.appV2.api.favorite.NewHouseFavoriteRestApi;
import com.toutiao.appV2.model.favorite.*;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Slf4j
public class NewHouseFavoriteRestController implements NewHouseFavoriteRestApi {

    private final HttpServletRequest request;

    @Autowired
    private NewHouseFavoriteRestService newHouseFavoriteRestService;
    @Autowired
    private FavoriteRestService favoriteRestService;

    @Autowired
    public NewHouseFavoriteRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 新房收藏列表
     *
     * @param newHouseFavoriteListRequest
     * @return
     */
    @Override
    public ResponseEntity<NewHouseFavoriteListResponse> getNewHouseFavoriteByUserId(@ApiParam(value = "newHouseFavoriteListRequest", required = true) @Valid NewHouseFavoriteListRequest newHouseFavoriteListRequest, BindingResult bindingResult) {
        NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery = new NewHouseFavoriteListDoQuery();
        NewHouseFavoriteListResponse newHouseFavoriteListResponse = new NewHouseFavoriteListResponse();
        BeanUtils.copyProperties(newHouseFavoriteListRequest, newHouseFavoriteListDoQuery);
        NewHouseFavoriteDomain newHouseFavoriteDomain = newHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(newHouseFavoriteListDoQuery);
        BeanUtils.copyProperties(newHouseFavoriteDomain, newHouseFavoriteListResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(newHouseFavoriteListResponse));
        return new ResponseEntity<>(newHouseFavoriteListResponse, HttpStatus.OK);
    }


    /**
     * 判断新房是否被收藏
     *
     * @param newHouseIsFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<QueryFavoriteResponse> getNewHouseIsFavorite(@ApiParam(value = "newHouseIsFavoriteRequest", required = true) @Valid NewHouseIsFavoriteRequest newHouseIsFavoriteRequest, BindingResult bindingResult) {
        NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
        BeanUtils.copyProperties(newHouseIsFavoriteRequest, newHouseIsFavoriteDoQuery);
        Boolean newHouseIsFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
        QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
        queryFavoriteResponse.setFlag(newHouseIsFavorite);
        log.info("返回结果:{}", queryFavoriteResponse);
        return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 添加新房收藏
     *
     * @param newHouseAddFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> addNewHouseFavorite(@ApiParam(value = "newHouseAddFavoriteRequest", required = true) @Valid @RequestBody NewHouseAddFavoriteRequest newHouseAddFavoriteRequest, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery = new NewHouseAddFavoriteDoQuery();
        BeanUtils.copyProperties(newHouseAddFavoriteRequest, newHouseAddFavoriteDoQuery);
        newHouseAddFavoriteDoQuery.setUserId(Integer.valueOf(user.getUserId()));
        Integer flag = favoriteRestService.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("添加新房收藏成功");
            changeFavoriteResponse.setMsg("添加新房收藏成功");

        } else if (flag == 0) {
            log.info("添加新房收藏失败");
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_ERROR);
        } else {
            log.info("添加新房收藏重复");
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_REPEAT);
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 新房取消收藏
     *
     * @param cancelFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> cancelFavoriteByNewHouse(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        UserFavoriteNewHouse userFavoriteNewHouse = new UserFavoriteNewHouse();
        BeanUtils.copyProperties(cancelFavoriteRequest, userFavoriteNewHouse);
        userFavoriteNewHouse.setUserId(Integer.valueOf(user.getUserId()));
        Integer flag = favoriteRestService.cancelNewHouseByNewCode(userFavoriteNewHouse);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("新房取消收藏成功");
            changeFavoriteResponse.setMsg("新房取消收藏成功");
        } else {
            log.info("新房取消收藏失败");
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_DELETE_ERROR);
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);

    }

}
