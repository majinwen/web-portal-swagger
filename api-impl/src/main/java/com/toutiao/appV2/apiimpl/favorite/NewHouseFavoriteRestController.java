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
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(newHouseFavoriteListRequest));

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
                NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery = new NewHouseFavoriteListDoQuery();
                NewHouseFavoriteListResponse newHouseFavoriteListResponse = new NewHouseFavoriteListResponse();
                BeanUtils.copyProperties(newHouseFavoriteListRequest, newHouseFavoriteListDoQuery);
                NewHouseFavoriteDomain newHouseFavoriteDomain = newHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(newHouseFavoriteListDoQuery);
                BeanUtils.copyProperties(newHouseFavoriteDomain, newHouseFavoriteListResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(newHouseFavoriteListResponse));
                return new ResponseEntity<>(newHouseFavoriteListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 判断新房是否被收藏
     *
     * @param newHouseIsFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<QueryFavoriteResponse> getNewHouseIsFavorite(@ApiParam(value = "newHouseIsFavoriteRequest", required = true) @Valid NewHouseIsFavoriteRequest newHouseIsFavoriteRequest, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(newHouseIsFavoriteRequest));
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
                NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
                BeanUtils.copyProperties(newHouseIsFavoriteRequest, newHouseIsFavoriteDoQuery);
                Boolean newHouseIsFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
                QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
                queryFavoriteResponse.setFlag(newHouseIsFavorite);
                log.info("返回结果:{}", queryFavoriteResponse);
                return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 添加新房收藏
     *
     * @param newHouseAddFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> addNewHouseFavorite(@ApiParam(value = "newHouseAddFavoriteRequest", required = true) @Valid @RequestBody NewHouseAddFavoriteRequest newHouseAddFavoriteRequest, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(newHouseAddFavoriteRequest));
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
                NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery = new NewHouseAddFavoriteDoQuery();
                BeanUtils.copyProperties(newHouseAddFavoriteRequest, newHouseAddFavoriteDoQuery);
                Integer flag = favoriteRestService.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
                ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
                if (flag == 1) {
                    log.info("添加新房收藏成功");
                    changeFavoriteResponse.setFlag(Boolean.TRUE);
                    changeFavoriteResponse.setMsg("添加新房收藏成功");

                } else if (flag == 0) {
                    log.info("添加新房收藏失败");
                    changeFavoriteResponse.setFlag(Boolean.FALSE);
                    changeFavoriteResponse.setMsg("添加新房收藏失败");
                } else {
                    log.info("添加新房收藏重复");
                    changeFavoriteResponse.setFlag(Boolean.FALSE);
                    changeFavoriteResponse.setMsg("添加新房收藏重复");
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
     * 新房取消收藏
     *
     * @param cancelFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> cancelFavoriteByNewHouse(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest, BindingResult bindingResult) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(cancelFavoriteRequest));
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
                UserFavoriteNewHouse userFavoriteNewHouse = new UserFavoriteNewHouse();
                BeanUtils.copyProperties(cancelFavoriteRequest, userFavoriteNewHouse);
                Integer flag = favoriteRestService.cancelNewHouseByNewCode(userFavoriteNewHouse);
                ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
                if (flag == 1) {
                    log.info("新房取消收藏成功");
                    changeFavoriteResponse.setFlag(Boolean.TRUE);
                    changeFavoriteResponse.setMsg("新房取消收藏成功");
                } else {
                    log.info("新房取消收藏失败");
                    changeFavoriteResponse.setFlag(Boolean.FALSE);
                    changeFavoriteResponse.setMsg("新房取消收藏失败");
                }
                return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
