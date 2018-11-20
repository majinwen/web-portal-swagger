package com.toutiao.appV2.apiimpl.favorite;


import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.PlotsAddFavoriteDoQuery;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.appV2.api.favorite.PlotsFavoriteRestApi;
import com.toutiao.appV2.model.favorite.*;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.JSONUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
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
    public ResponseEntity<PlotFavoriteListResponse> getPlotFavoriteByUserId(@ApiParam(value = "plotsFavoriteListRequest", required = true) @Valid PlotsFavoriteListRequest plotsFavoriteListRequest, BindingResult bindingResult) {
        PlotFavoriteListDoQuery plotFavoriteListDoQuery = new PlotFavoriteListDoQuery();
        BeanUtils.copyProperties(plotsFavoriteListRequest, plotFavoriteListDoQuery);
        PlotFavoriteListResponse plotFavoriteListResponse = new PlotFavoriteListResponse();
        PlotFavoriteListDo plotFavoriteListDo = favoriteRestService.getPlotFavoriteByUserId(plotFavoriteListDoQuery);
        BeanUtils.copyProperties(plotFavoriteListDo, plotFavoriteListResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(plotFavoriteListResponse));
        return new ResponseEntity<>(plotFavoriteListResponse, HttpStatus.OK);
    }


    /**
     * 判断小区是否被收藏
     *
     * @param plotIsFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<QueryFavoriteResponse> getPlotIsFavoriteByPlotIdAndUserId(@ApiParam(value = "plotIsFavoriteRequest", required = true) @Valid PlotIsFavoriteRequest plotIsFavoriteRequest, BindingResult bindingResult) {
        PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
        BeanUtils.copyProperties(plotIsFavoriteRequest, plotIsFavoriteDoQuery);
        Boolean plotIsFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
        QueryFavoriteResponse queryFavoriteResponse = new QueryFavoriteResponse();
        queryFavoriteResponse.setFlag(plotIsFavorite);
        log.info("返回结果:{}", queryFavoriteResponse);
        return new ResponseEntity<>(queryFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 添加小区收藏
     *
     * @param plotsAddFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> addPlotsFavorite(@ApiParam(value = "plotsAddFavoriteRequest", required = true) @Valid @RequestBody PlotsAddFavoriteRequest plotsAddFavoriteRequest, BindingResult bindingResult) {

        PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery = new PlotsAddFavoriteDoQuery();
        BeanUtils.copyProperties(plotsAddFavoriteRequest, plotsAddFavoriteDoQuery);
        Integer flag = favoriteRestService.addPlotsFavorite(plotsAddFavoriteDoQuery);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("添加小区收藏成功");
            changeFavoriteResponse.setMsg("添加小区收藏成功");
        } else if (flag == 0) {
            log.info("添加小区收藏失败");
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_ERROR);
        } else {
            log.info("添加小区收藏重复");
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_REPEAT);
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }


    /**
     * 小区取消收藏
     *
     * @param cancelFavoriteRequest
     * @return
     */
    @Override
    public ResponseEntity<ChangeFavoriteResponse> cancelFavoriteByVillage(@ApiParam(value = "cancelFavoriteRequest", required = true) @Valid @RequestBody CancelFavoriteRequest cancelFavoriteRequest, BindingResult bindingResult) {

        PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
        BeanUtils.copyProperties(cancelFavoriteRequest, plotIsFavoriteDoQuery);
        Integer flag = favoriteRestService.cancelVillageByVillageId(plotIsFavoriteDoQuery);
        ChangeFavoriteResponse changeFavoriteResponse = new ChangeFavoriteResponse();
        if (flag == 1) {
            log.info("小区取消收藏成功");
            changeFavoriteResponse.setMsg("小区取消收藏成功");
        } else {
            log.info("小区取消收藏失败");
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_DELETE_ERROR);
        }
        return new ResponseEntity<>(changeFavoriteResponse, HttpStatus.OK);
    }

    /**
     * 列表页小区收藏数量
     *
     * @param buildingId
     * @return
     */
    @Override
    public ResponseEntity<CountFavoriteResponse> getPlotFavoriteCountByPlotId(@ApiParam(value = "buildingId", required = true) @RequestParam(value = "buildingId", required = false) Integer buildingId) {
        Integer plotFavoriteCountByPlotId = favoriteRestService.getPlotFavoriteCountByPlotId(buildingId);
        CountFavoriteResponse countFavoriteResponse = new CountFavoriteResponse();
        countFavoriteResponse.setCount(plotFavoriteCountByPlotId);
        log.info("返回结果:{}", countFavoriteResponse);
        return new ResponseEntity<>(countFavoriteResponse, HttpStatus.OK);
    }


}
