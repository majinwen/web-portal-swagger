package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.*;
import com.toutiao.app.api.chance.response.favorite.UserCenterFavoriteCountResponse;
import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/favorite")
public class FavoriteRestController {

     @Autowired
     private FavoriteRestService favoriteRestService;

     private  final  Integer FAVORITE_RENT=1;


    /**
     *
     * @param userId
     * @return
     * 个人中心租房，二手房，新房，小区，收藏数量
     */
    @RequestMapping(value = "/getFavoriteCountByUser",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getFavoriteCountByUser(@RequestParam("userId") Integer userId)
    {
        UserCenterFavoriteCountResponse userCenterFavoriteCountResponse=new UserCenterFavoriteCountResponse();
         UserCenterFavoriteCountDo userCenterFavoriteCountDo= favoriteRestService.getFavoriteCountByUser(userId);
        BeanUtils.copyProperties(userCenterFavoriteCountDo,userCenterFavoriteCountResponse);
        return  NashResult.build(userCenterFavoriteCountResponse);
    }

    /**
     *
     *
     * @return
     * 判断租房是否被收藏
     */
    @RequestMapping(value = "/getIsFavoriteByRent",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult getIsFavoriteByRentHouseId(@Validated(First.class) IsFavoriteRequest isFavoriteRequest)
    {
          IsFavoriteDo isFavoriteDo=new IsFavoriteDo();
          BeanUtils.copyProperties(isFavoriteRequest,isFavoriteDo);
          boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_RENT,isFavoriteDo);
        return  NashResult.build(isFavorite);
    }

    /**
     * 列表页小区收藏数量
     * @param plotId
     * @return
     */
    @RequestMapping(value = "/getPlotFavoriteCountByPlotId",method = RequestMethod.GET)
    public NashResult getPlotFavoriteCountByPlotId(@RequestParam("plotId") Integer plotId){
        Integer plotFavoriteCountByPlotId = favoriteRestService.getPlotFavoriteCountByPlotId(plotId);
        return NashResult.build(plotFavoriteCountByPlotId);
    }

    /**
     * 二手房取消收藏
     * @param deleteEsfFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/deleteEsfFavoriteByEsfIdAndUserId",method = RequestMethod.GET)
    public NashResult deleteEsfFavoriteByEsfIdAndUserId(@Validated DeleteEsfFavoriteResquest deleteEsfFavoriteResquest){
        DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
        BeanUtils.copyProperties(deleteEsfFavoriteResquest,deleteEsfFavoriteDo);
        Boolean aBoolean = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        return NashResult.build(aBoolean);
    }

    /**
     * 租房取消收藏
     * @param deleteRentFavoriteRequest
     * @return
     */
    @RequestMapping(value = "/deleteRentFavoriteByRentIdAndUserId",method = RequestMethod.GET)
    public NashResult deleteRentFavoriteByRentIdAndUserId(@Validated DeleteRentFavoriteRequest deleteRentFavoriteRequest){
        DeleteRentFavoriteDo deleteRentFavoriteDo = new DeleteRentFavoriteDo();
        BeanUtils.copyProperties(deleteRentFavoriteRequest,deleteRentFavoriteDo);
        Boolean aBoolean = favoriteRestService.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDo);
        return NashResult.build(aBoolean);
    }

    /**
     * 判断小区是否被收藏
     * @param plotIsFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/getPlotIsFavoriteByPlotIdAndUserId",method = RequestMethod.GET)
    public NashResult getPlotIsFavorite(PlotIsFavoriteResquest plotIsFavoriteResquest){
        PlotIsFavoriteDo plotIsFavoriteDo = new PlotIsFavoriteDo();
        BeanUtils.copyProperties(plotIsFavoriteResquest,plotIsFavoriteDo);
        Boolean plotIsFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDo);
        return NashResult.build(plotIsFavorite);
    }

    /**
     * 判断新房是否被收藏
     * @param newHouseIsFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/getNewHouseIsFavorite",method = RequestMethod.GET)
    public NashResult getNewHouseIsFavorite(@Validated NewHouseIsFavoriteResquest newHouseIsFavoriteResquest){
        NewHouseIsFavoriteDo newHouseIsFavoriteDo = new NewHouseIsFavoriteDo();
        BeanUtils.copyProperties(newHouseIsFavoriteResquest,newHouseIsFavoriteDo);
        Boolean newHouseIsFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDo);
        return NashResult.build(newHouseIsFavorite);
    }

}
