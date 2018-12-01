package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.DeleteEsfFavoriteDo;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteEsHouseDoQuery;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.SellHouseFavoriteRestService;
import com.toutiao.appV2.api.favorite.SellHouseFavoriteRestApi;
import com.toutiao.appV2.model.favorite.*;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class SellHouseFavoriteRestController implements SellHouseFavoriteRestApi {


    private final HttpServletRequest request;

    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private SellHouseFavoriteRestService sellHouseFavoriteRestService;
    //二手房标识
    private final Integer FAVORITE_ESF = 2;

    @Autowired
    public SellHouseFavoriteRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 二手房收藏列表
     *
     * @param
     * @return
     */
    @Override
    public ResponseEntity<SellHouseFavoriteListResponse> getEsfFavoriteByUserId(@ApiParam(value = "sellHouseFavoriteListRequest", required = true) @Valid SellHouseFavoriteListRequest sellHouseFavoriteListRequest, BindingResult bindingResult) {

        SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
        BeanUtils.copyProperties(sellHouseFavoriteListRequest, sellHouseFavoriteListDoQuery);
        SellHouseFavoriteDomain sellHouseFavoriteDomain = sellHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(sellHouseFavoriteListDoQuery);
        SellHouseFavoriteListResponse sellHouseFavoriteListResponse = new SellHouseFavoriteListResponse();
        BeanUtils.copyProperties(sellHouseFavoriteDomain, sellHouseFavoriteListResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseFavoriteListResponse));
        return new ResponseEntity<SellHouseFavoriteListResponse>(sellHouseFavoriteListResponse, HttpStatus.OK);
    }


    /**
     * 二手房添加收藏
     *
     * @param addFavorite
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> addEsfFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody SellHouseAddFavoriteRequest addFavorite, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        UserFavoriteEsHouseDoQuery userFavoriteEsHouse = new UserFavoriteEsHouseDoQuery();
        BeanUtils.copyProperties(addFavorite, userFavoriteEsHouse);
        userFavoriteEsHouse.setUserId(Integer.valueOf(user.getUserId()));
        Integer flag = favoriteRestService.addEsfFavorite(userFavoriteEsHouse);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("添加二手房收藏成功");
            changeFavoriteResponse.setId(userFavoriteEsHouse.getHouseId());
            changeFavoriteResponse.setMsg("添加二手房收藏成功");

        } else if (flag == 0) {
            log.info("添加二手房收藏失败");
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_ERROR);
        } else {
            log.info("添加二手房收藏重复");
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_REPEAT);
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 二手房取消收藏
     *
     * @param deleteEsfFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> deleteEsfFavoriteByEsfIdAndUserId(@ApiParam(value = "deleteEsfFavoriteRequest", required = true) @Valid @RequestBody DeleteEsfFavoriteRequest deleteEsfFavoriteRequest, BindingResult bindingResult) {

        // 查询登录用户信息
        UserBasic user = UserBasic.getCurrent();

        if (null == user) {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
        BeanUtils.copyProperties(deleteEsfFavoriteRequest, deleteEsfFavoriteDo);
        deleteEsfFavoriteDo.setUserId(Integer.valueOf(user.getUserId()));
        Boolean flag = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag) {
            changeFavoriteResponse.setMsg("二手房取消收藏成功");
        } else {
            changeFavoriteResponse.setMsg("二手房取消收藏失败");
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_DELETE_ERROR);
        }
        log.info("返回结果:{}", changeFavoriteResponse);
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 判断二手房是否被收藏
     *
     * @param isFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<QueryFavoriteResponse> getIsFavoriteByEsf(@ApiParam(value = "isFavoriteRequest", required = true) @Valid IsFavoriteRequest isFavoriteRequest, BindingResult bindingResult) {
        IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
        BeanUtils.copyProperties(isFavoriteRequest, isFavoriteDo);
        boolean flag = favoriteRestService.getIsFavorite(FAVORITE_ESF, isFavoriteDo);
        QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
        queryFavoriteResponse.setFlag(flag);
        log.info("返回结果:{}", queryFavoriteResponse);
        return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);

    }

}
