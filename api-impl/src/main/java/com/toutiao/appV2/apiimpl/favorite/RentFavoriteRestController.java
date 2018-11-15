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
@RequestMapping("/rest/favorite/rent")
@Slf4j
public class RentFavoriteRestController implements RentFavoriteRestApi {

    private final HttpServletRequest request;

    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private RentFavoriteRestService rentFavoriteRestService;
    //租房标识
    private final Integer FAVORITE_RENT = 1;

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
    public ResponseEntity<RentFavoriteListResponse> getRentFavoriteByUserId(@ApiParam(value = "rentFavoriteListRequest", required = true) @Valid @RequestBody RentFavoriteListRequest rentFavoriteListRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(rentFavoriteListRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                RentFavoriteListDoQuery rentFavoriteListDoQuery = new RentFavoriteListDoQuery();
                BeanUtils.copyProperties(rentFavoriteListRequest, rentFavoriteListDoQuery);
                RentFavoriteDomain rentFavoriteDomain = rentFavoriteRestService.queryRentFavoriteListByUserId(rentFavoriteListDoQuery);
                RentFavoriteListResponse rentFavoriteListResponse = new RentFavoriteListResponse();
                BeanUtils.copyProperties(rentFavoriteDomain, rentFavoriteListResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(rentFavoriteListResponse));
                return new ResponseEntity<>(rentFavoriteListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 租房添加收藏
     *
     * @param addFavorite
     * @return
     */
    @Override
    public ResponseEntity<String> addRentFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody AddFavorite addFavorite) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(addFavorite));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserFavoriteRentDoQuery userFavoriteRent = new UserFavoriteRentDoQuery();
                BeanUtils.copyProperties(addFavorite, userFavoriteRent);
                Integer flag = favoriteRestService.addRentFavorite(userFavoriteRent);
                if (flag == 1) {
                    log.info("添加租房收藏成功");
                    return new ResponseEntity<String>("添加租房收藏成功", HttpStatus.OK);
                } else if (flag == 0) {
                    log.info("添加租房收藏失败");
                    return new ResponseEntity<String>("添加租房收藏失败", HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    log.info("添加租房收藏重复");
                    return new ResponseEntity<String>("添加租房收藏重复", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 租房取消收藏
     *
     * @param deleteRentFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<Boolean> deleteRentFavoriteByRentIdAndUserId(@ApiParam(value = "deleteRentFavoriteRequest", required = true) @Valid @RequestBody DeleteRentFavoriteRequest deleteRentFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(deleteRentFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery = new DeleteRentFavoriteDoQuery();
                BeanUtils.copyProperties(deleteRentFavoriteRequest, deleteRentFavoriteDoQuery);
                Boolean aBoolean = favoriteRestService.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDoQuery);
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
     * 判断租房是否被收藏
     *
     * @param isFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<Boolean> getIsFavoriteByRent(@ApiParam(value = "isFavoriteRequest", required = true) @Valid @RequestBody IsFavoriteRequest isFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(isFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
                BeanUtils.copyProperties(isFavoriteRequest, isFavoriteDo);
                boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_RENT, isFavoriteDo);
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
