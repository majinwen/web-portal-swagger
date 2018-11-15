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
import com.toutiao.web.common.util.JSONUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public ResponseEntity<SellHouseFavoriteListResponse> getEsfFavoriteByUserId(@ApiParam(value = "sellHouseFavoriteListRequest", required = true) @Valid @RequestBody SellHouseFavoriteListRequest sellHouseFavoriteListRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(sellHouseFavoriteListRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
                BeanUtils.copyProperties(sellHouseFavoriteListRequest, sellHouseFavoriteListDoQuery);
                SellHouseFavoriteDomain sellHouseFavoriteDomain = sellHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(sellHouseFavoriteListDoQuery);
                SellHouseFavoriteListResponse sellHouseFavoriteListResponse = new SellHouseFavoriteListResponse();
                BeanUtils.copyProperties(sellHouseFavoriteDomain, sellHouseFavoriteListResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseFavoriteListResponse));
                return new ResponseEntity<SellHouseFavoriteListResponse>(sellHouseFavoriteListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 二手房添加收藏
     *
     * @param addFavorite
     * @return
     */
    @Override
    public ResponseEntity<String> addEsfFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody AddFavorite addFavorite) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(addFavorite));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserFavoriteEsHouseDoQuery userFavoriteEsHouse = new UserFavoriteEsHouseDoQuery();
                BeanUtils.copyProperties(addFavorite, userFavoriteEsHouse);
                userFavoriteEsHouse.setPriceIncreaseDecline(addFavorite.getIsCutPrice());
                Integer flag = favoriteRestService.addEsfFavorite(userFavoriteEsHouse);
                if (flag == 1) {
                    log.info("添加二手房收藏成功");
                    return new ResponseEntity<String>("添加二手房收藏成功", HttpStatus.OK);
                } else if (flag == 0) {
                    log.info("添加二手房收藏成功");
                    return new ResponseEntity<String>("添加二手房收藏失败",HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    log.info("添加二手房收藏成功");
                    return new ResponseEntity<String>("添加二手房收藏重复",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 二手房取消收藏
     *
     * @param deleteEsfFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<Boolean> deleteEsfFavoriteByEsfIdAndUserId(@ApiParam(value = "deleteEsfFavoriteRequest", required = true) @Valid @RequestBody DeleteEsfFavoriteRequest deleteEsfFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(deleteEsfFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
                BeanUtils.copyProperties(deleteEsfFavoriteRequest, deleteEsfFavoriteDo);
                Boolean aBoolean = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
                log.info("返回结果:{}", aBoolean);
                return new ResponseEntity<>(aBoolean, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 判断二手房是否被收藏
     *
     * @param isFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<Boolean> getIsFavoriteByEsf(@ApiParam(value = "isFavoriteRequest", required = true) @Valid @RequestBody IsFavoriteRequest isFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(isFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
                BeanUtils.copyProperties(isFavoriteRequest, isFavoriteDo);
                boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_ESF, isFavoriteDo);
                log.info("返回结果:{}", isFavorite);
                return new ResponseEntity<>(isFavorite, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
