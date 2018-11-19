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
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.JSONUtil;
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
//        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//        log.info("调用方法:{}", thisMethodName);
//        log.info("接收参数:{}", JSONUtil.stringfy(sellHouseFavoriteListRequest));
//        if (bindingResult.hasErrors()) {
//            List<FieldError> allErrors = bindingResult.getFieldErrors();
//            StringBuilder sb = new StringBuilder();
//            allErrors.forEach(error -> {
//                sb.append(error.getDefaultMessage() + ";");
//            });
//            log.error("参数校验错误:{}", sb);
//            throw new IllegalArgumentException(sb.toString());
//        }
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
            } catch (BaseException ex) {
                throw new BaseException(ex.getCode(), ex.getMsg());
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
    public ResponseEntity<ChangeFavoriteResponse> addEsfFavorite(@ApiParam(value = "addFavorite", required = true) @Valid @RequestBody AddFavorite addFavorite, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(addFavorite));
        if (bindingResult.hasErrors()) {
            List<FieldError> allErrors = bindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            allErrors.forEach(error -> {
                sb.append(error.getDefaultMessage() + ";");
            });
            log.error("参数校验错误:{}", sb);
            throw new IllegalArgumentException(sb.toString());
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserFavoriteEsHouseDoQuery userFavoriteEsHouse = new UserFavoriteEsHouseDoQuery();
                BeanUtils.copyProperties(addFavorite, userFavoriteEsHouse);
                userFavoriteEsHouse.setPriceIncreaseDecline(addFavorite.getIsCutPrice());
                Integer flag = favoriteRestService.addEsfFavorite(userFavoriteEsHouse);
                ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
                if (flag == 1) {
                    log.info("添加二手房收藏成功");
                    changeFavoriteResponse.setFlag(Boolean.TRUE);
                    changeFavoriteResponse.setMsg("添加二手房收藏成功");

                } else if (flag == 0) {
                    log.info("添加二手房收藏失败");
                    changeFavoriteResponse.setFlag(Boolean.FALSE);
                    changeFavoriteResponse.setMsg("添加二手房收藏失败");
                } else {
                    log.info("添加二手房收藏重复");
                    changeFavoriteResponse.setFlag(Boolean.FALSE);
                    changeFavoriteResponse.setMsg("添加二手房收藏重复");
                }
                return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
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
    public ResponseEntity<ChangeFavoriteResponse> deleteEsfFavoriteByEsfIdAndUserId(@ApiParam(value = "deleteEsfFavoriteRequest", required = true) @Valid @RequestBody DeleteEsfFavoriteRequest deleteEsfFavoriteRequest, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(deleteEsfFavoriteRequest));
        if (bindingResult.hasErrors()) {
            List<FieldError> allErrors = bindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            allErrors.forEach(error -> {
                sb.append(error.getDefaultMessage() + ";");
            });
            log.error("参数校验错误:{}", sb);
            throw new IllegalArgumentException(sb.toString());
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
                BeanUtils.copyProperties(deleteEsfFavoriteRequest, deleteEsfFavoriteDo);
                Boolean flag = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
                ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
                changeFavoriteResponse.setFlag(flag);
                if (flag) {
                    changeFavoriteResponse.setMsg("二手房取消收藏成功");
                } else {
                    changeFavoriteResponse.setMsg("二手房取消收藏失败");
                }
                log.info("返回结果:{}", changeFavoriteResponse);
                return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
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
    public ResponseEntity<QueryFavoriteResponse> getIsFavoriteByEsf(@ApiParam(value = "isFavoriteRequest", required = true) @Valid IsFavoriteRequest isFavoriteRequest, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(isFavoriteRequest));
        if (bindingResult.hasErrors()) {
            List<FieldError> allErrors = bindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            allErrors.forEach(error -> {
                sb.append(error.getDefaultMessage() + ";");
            });
            log.error("参数校验错误:{}", sb);
            throw new IllegalArgumentException(sb.toString());
        }
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
                BeanUtils.copyProperties(isFavoriteRequest, isFavoriteDo);
                boolean flag = favoriteRestService.getIsFavorite(FAVORITE_ESF, isFavoriteDo);
                QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
                queryFavoriteResponse.setFlag(flag);
                log.info("返回结果:{}", queryFavoriteResponse);
                return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
