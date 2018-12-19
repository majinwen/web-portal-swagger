package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.DeleteRentFavoriteDoQuery;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteRentDoQuery;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.RentFavoriteRestService;
import com.toutiao.appV2.api.favorite.RentFavoriteRestApi;
import com.toutiao.appV2.model.favorite.*;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Slf4j
@Validated
public class RentFavoriteRestController implements RentFavoriteRestApi {
    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private RentFavoriteRestService rentFavoriteRestService;
    //租房标识
    private final Integer FAVORITE_RENT = 1;

    private final HttpServletRequest request;

    @Autowired
    public RentFavoriteRestController(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 租房收藏列表
     *
     * @param rentFavoriteListRequest
     * @return
     */
    @Override
    public ResponseEntity<RentFavoriteListResponse> getRentFavoriteByUserId(@ApiParam(value = "rentFavoriteListRequest", required = true) @Valid RentFavoriteListRequest rentFavoriteListRequest, BindingResult bindingResult) {

        RentFavoriteListDoQuery rentFavoriteListDoQuery = new RentFavoriteListDoQuery();
        BeanUtils.copyProperties(rentFavoriteListRequest, rentFavoriteListDoQuery);
        RentFavoriteDomain rentFavoriteDomain = rentFavoriteRestService.queryRentFavoriteListByUserId(rentFavoriteListDoQuery);
        RentFavoriteListResponse rentFavoriteListResponse = new RentFavoriteListResponse();
        BeanUtils.copyProperties(rentFavoriteDomain, rentFavoriteListResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(rentFavoriteListResponse));
        return new ResponseEntity<>(rentFavoriteListResponse, HttpStatus.OK);
    }


    /**
     * 租房添加收藏
     *
     * @param addFavorite
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> addRentFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody RentHouseAddFavoriteRequest addFavorite, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        UserFavoriteRentDoQuery userFavoriteRent = new UserFavoriteRentDoQuery();
        BeanUtils.copyProperties(addFavorite, userFavoriteRent);
        userFavoriteRent.setUserId(Integer.valueOf(user.getUserId()));
        Integer flag = favoriteRestService.addRentFavorite(userFavoriteRent);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("添加租房收藏成功");
            changeFavoriteResponse.setId(userFavoriteRent.getHouseId());
            changeFavoriteResponse.setMsg("添加租房收藏成功");
        } else if (flag == 0) {
            log.info("添加租房收藏失败");
            throw new BaseException(RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_ERROR);
        } else {
            log.info("添加租房收藏重复");
//            throw new BaseException(RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_REPEAT);
            changeFavoriteResponse.setId(userFavoriteRent.getHouseId());
            changeFavoriteResponse.setMsg("添加租房收藏重复");
        }
        return new ResponseEntity<ChangeFavoriteResponse>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 租房取消收藏
     *
     * @param deleteRentFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> deleteRentFavoriteByRentIdAndUserId(@ApiParam(value = "deleteRentFavoriteRequest", required = true) @Valid @RequestBody DeleteRentFavoriteRequest deleteRentFavoriteRequest, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery = new DeleteRentFavoriteDoQuery();
        BeanUtils.copyProperties(deleteRentFavoriteRequest, deleteRentFavoriteDoQuery);
        deleteRentFavoriteDoQuery.setUserId(Integer.valueOf(user.getUserId()));
        Boolean flag = favoriteRestService.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDoQuery);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag) {
            changeFavoriteResponse.setMsg("租房取消收藏成功");
        } else {
            throw new BaseException(RentInterfaceErrorCodeEnum.RENT_FAVORITE_DELETE_ERROR);
        }
        log.info("返回结果:{}", changeFavoriteResponse);
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 判断租房是否被收藏
     *
     * @param isFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<QueryFavoriteResponse> getIsFavoriteByRent(@ApiParam(value = "isFavoriteRequest", required = true) @Valid IsFavoriteRequest isFavoriteRequest, BindingResult bindingResult) {
        IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
        BeanUtils.copyProperties(isFavoriteRequest, isFavoriteDo);
        Boolean flag = favoriteRestService.getIsFavorite(FAVORITE_RENT, isFavoriteDo);
        QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
        queryFavoriteResponse.setFlag(flag);
        log.info("返回结果:{}", queryFavoriteResponse);
        return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);
    }


}
