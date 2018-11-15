package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.api.chance.response.plot.PlotFavoriteListResponse;
import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.PlotsAddFavoriteDoQuery;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.appV2.api.favorite.PlotsFavoriteRestApi;
import com.toutiao.appV2.model.favorite.CancelFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotIsFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotsAddFavoriteRequest;
import com.toutiao.appV2.model.favorite.PlotsFavoriteListRequest;
import com.toutiao.web.common.util.JSONUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/rest/favorite/plots")
@Slf4j
public class PlotsFavoriteRestController implements PlotsFavoriteRestApi {

    private final HttpServletRequest request;

    @Autowired
    private FavoriteRestService favoriteRestService;

    @Autowired
    public PlotsFavoriteRestController(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 小区收藏列表
     *
     * @param plotsFavoriteListRequest
     * @return
     */
    @Override
    public ResponseEntity<PlotFavoriteListResponse> getPlotFavoriteByUserId(@ApiParam(value = "plotsFavoriteListRequest", required = true) @Valid @RequestBody PlotsFavoriteListRequest plotsFavoriteListRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(plotsFavoriteListRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotFavoriteListDoQuery plotFavoriteListDoQuery = new PlotFavoriteListDoQuery();
                BeanUtils.copyProperties(plotsFavoriteListRequest,plotFavoriteListDoQuery);
                PlotFavoriteListResponse plotFavoriteListResponse = new PlotFavoriteListResponse();
                PlotFavoriteListDo plotFavoriteListDo = favoriteRestService.getPlotFavoriteByUserId(plotFavoriteListDoQuery);
                BeanUtils.copyProperties(plotFavoriteListDo, plotFavoriteListResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(plotFavoriteListResponse));
                return new ResponseEntity<>(plotFavoriteListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 判断小区是否被收藏
     *
     * @param plotIsFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<Boolean> getPlotIsFavoriteByPlotIdAndUserId(@ApiParam(value = "plotIsFavoriteRequest", required = true) @Valid @RequestBody PlotIsFavoriteRequest plotIsFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(plotIsFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                BeanUtils.copyProperties(plotIsFavoriteRequest,plotIsFavoriteDoQuery);
                Boolean plotIsFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
                log.info("返回结果:{}", plotIsFavorite);
                return new ResponseEntity<>(plotIsFavorite, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 添加小区收藏
     *
     * @param plotsAddFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<String> addPlotsFavorite(@ApiParam(value = "plotsAddFavoriteRequest", required = true) @Valid @RequestBody PlotsAddFavoriteRequest plotsAddFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(plotsAddFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery = new PlotsAddFavoriteDoQuery();
                BeanUtils.copyProperties(plotsAddFavoriteRequest, plotsAddFavoriteDoQuery);
                Integer flag = favoriteRestService.addPlotsFavorite(plotsAddFavoriteDoQuery);
                if (flag == 1) {
                    log.info("添加小区收藏成功");
                    return new ResponseEntity<String>("添加小区收藏成功", HttpStatus.OK);
                } else if (flag == 0) {
                    log.info("添加小区收藏成功");
                    return new ResponseEntity<String>("添加小区收藏失败",HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    log.info("添加小区收藏成功");
                    return new ResponseEntity<String>("添加小区收藏重复",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 小区取消收藏
     *
     * @param cancelFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<String> cancelFavoriteByVillage(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(cancelFavoriteRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                BeanUtils.copyProperties(cancelFavoriteRequest,plotIsFavoriteDoQuery);
                Integer flag = favoriteRestService.cancelVillageByVillageId(plotIsFavoriteDoQuery);
                if (flag == 1) {
                    log.info("小区取消收藏成功");
                    return new ResponseEntity<>("小区取消收藏成功", HttpStatus.OK);
                } else {
                    log.info("小区取消收藏失败");
                    return new ResponseEntity<>("小区取消收藏失败", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 列表页小区收藏数量
     *
     * @param buildingId
     * @return
     */
    @Override
    public ResponseEntity<Integer> getPlotFavoriteCountByPlotId(@ApiParam(value = "buildingId") @Valid @RequestParam(value = "buildingId", required = false) Integer buildingId) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(buildingId));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer plotFavoriteCountByPlotId = favoriteRestService.getPlotFavoriteCountByPlotId(buildingId);
                log.info("返回结果:{}" , plotFavoriteCountByPlotId);
                return new ResponseEntity<>(plotFavoriteCountByPlotId, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
