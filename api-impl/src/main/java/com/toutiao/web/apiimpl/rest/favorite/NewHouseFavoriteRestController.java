package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.CancelFavoriteRequest;
import com.toutiao.app.api.chance.request.favorite.NewHouseAddFavoriteRequest;
import com.toutiao.app.api.chance.request.favorite.NewHouseFavoriteListRequest;
import com.toutiao.app.api.chance.request.favorite.NewHouseIsFavoriteResquest;
import com.toutiao.app.api.chance.response.favorite.NewHouseFavoriteListResponse;
import com.toutiao.app.domain.favorite.NewHouseAddFavoriteDoQuery;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.NewHouseFavoriteRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/favorite/newhouse")
public class NewHouseFavoriteRestController {


    @Autowired
    private NewHouseFavoriteRestService newHouseFavoriteRestService;
    @Autowired
    private FavoriteRestService favoriteRestService;


    /**
     * 新房收藏列表
     * @param newHouseFavoriteListRequest
     * @return
     */
    @RequestMapping(value = "/getNewHouseFavoriteByUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getPlotFavoriteByUserId(NewHouseFavoriteListRequest newHouseFavoriteListRequest){
        NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery = new NewHouseFavoriteListDoQuery();
        NewHouseFavoriteListResponse newHouseFavoriteListResponse = new NewHouseFavoriteListResponse();
        BeanUtils.copyProperties(newHouseFavoriteListRequest,newHouseFavoriteListDoQuery);
        NewHouseFavoriteDomain newHouseFavoriteDomain = newHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(newHouseFavoriteListDoQuery);
        BeanUtils.copyProperties(newHouseFavoriteDomain,newHouseFavoriteListResponse);
        return NashResult.build(newHouseFavoriteListResponse);
    }

    /**
     * 判断新房是否被收藏
     * @param newHouseIsFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/getNewHouseIsFavorite",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getNewHouseIsFavorite(@Validated NewHouseIsFavoriteResquest newHouseIsFavoriteResquest){
        NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
        BeanUtils.copyProperties(newHouseIsFavoriteResquest, newHouseIsFavoriteDoQuery);
        Boolean newHouseIsFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
        return NashResult.build(newHouseIsFavorite);
    }

    /**
     * 添加新房收藏
     * @param newHouseAddFavoriteRequest
     * @return
     */
    @RequestMapping(value = "/addNewHouseFavorite",method = RequestMethod.GET)
    @ResponseBody
    public NashResult addNewHouseFavorite(NewHouseAddFavoriteRequest newHouseAddFavoriteRequest){
        NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery = new NewHouseAddFavoriteDoQuery();
        BeanUtils.copyProperties(newHouseAddFavoriteRequest,newHouseAddFavoriteDoQuery);
        Boolean aBoolean = favoriteRestService.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
        return NashResult.build(aBoolean);
    }


    /**
     * 新房取消收藏
     */
    @RequestMapping(value = "cancelFavoriteByNewHouse",method = RequestMethod.POST)
    @ResponseBody
    public NashResult cancelFavoriteNewHouse(@Validated(First.class) CancelFavoriteRequest cancelFavoriteRequest)
    {
        UserFavoriteNewHouse userFavoriteNewHouse=new UserFavoriteNewHouse();
        BeanUtils.copyProperties(cancelFavoriteRequest,userFavoriteNewHouse);
        return  favoriteRestService.cancelNewHouseByNewCode(userFavoriteNewHouse);
    }

}
