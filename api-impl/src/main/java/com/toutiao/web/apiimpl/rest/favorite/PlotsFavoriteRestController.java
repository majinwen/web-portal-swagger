package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.*;
import com.toutiao.app.api.chance.response.plot.PlotFavoriteListResponse;
import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.PlotsAddFavoriteDoQuery;
import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/favorite/plots")
public class PlotsFavoriteRestController {

    @Autowired
    private FavoriteRestService favoriteRestService;


    /**
     * 小区收藏列表
     * @param plotsFavoriteListRequest
     * @return
     */
    @RequestMapping(value = "/getPlotFavoriteByUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getPlotFavoriteByUserId(@Validated PlotsFavoriteListRequest plotsFavoriteListRequest){
        PlotFavoriteListDoQuery plotFavoriteListDoQuery = new PlotFavoriteListDoQuery();
        BeanUtils.copyProperties(plotsFavoriteListRequest,plotFavoriteListDoQuery);
        PlotFavoriteListResponse plotFavoriteListResponse = new PlotFavoriteListResponse();
        PlotFavoriteListDo plotFavoriteListDo = favoriteRestService.getPlotFavoriteByUserId(plotFavoriteListDoQuery);
        BeanUtils.copyProperties(plotFavoriteListDo, plotFavoriteListResponse);
        return NashResult.build(plotFavoriteListResponse);
    }


    /**
     * 判断小区是否被收藏
     * @param plotIsFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/getPlotIsFavoriteByPlotIdAndUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getPlotIsFavorite(PlotIsFavoriteResquest plotIsFavoriteResquest){
        PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
        BeanUtils.copyProperties(plotIsFavoriteResquest, plotIsFavoriteDoQuery);
        Boolean plotIsFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
        return NashResult.build(plotIsFavorite);
    }

    /**
     * 添加小区收藏
     * @param plotsAddFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/addPlotsFavorite" ,method = RequestMethod.POST)
    @ResponseBody
    public NashResult addPlotsFavorite(@Validated @RequestBody PlotsAddFavoriteResquest plotsAddFavoriteResquest){
        PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery = new PlotsAddFavoriteDoQuery();
        BeanUtils.copyProperties(plotsAddFavoriteResquest,plotsAddFavoriteDoQuery);
        Boolean aBoolean = favoriteRestService.addPlotsFavorite(plotsAddFavoriteDoQuery);
        return NashResult.build(aBoolean);
    }


    /**
     * 小区取消收藏
     */

    @RequestMapping(value = "cancelFavoriteByVillage",method = RequestMethod.POST)
    @ResponseBody
    public NashResult cancelFavoriteByVillage(@Validated(Second.class) CancelFavoriteRequest cancelFavoriteRequest)
    {
        UserFavoriteVillage userFavoriteVillage=new UserFavoriteVillage();
        BeanUtils.copyProperties(cancelFavoriteRequest,userFavoriteVillage);
        return favoriteRestService.cancelVillageByVillageId(userFavoriteVillage);

    }

    /**
     * 列表页小区收藏数量
     * @param plotsFavoriteNumRequest
     * @return
     */
    @RequestMapping(value = "/getPlotFavoriteCountByPlotId",method = RequestMethod.GET)
    public NashResult getPlotFavoriteCountByPlotId(@Validated PlotsFavoriteNumRequest plotsFavoriteNumRequest){
        Integer plotFavoriteCountByPlotId = favoriteRestService.getPlotFavoriteCountByPlotId(plotsFavoriteNumRequest.getPlotId());
        return NashResult.build(plotFavoriteCountByPlotId);
    }

}
