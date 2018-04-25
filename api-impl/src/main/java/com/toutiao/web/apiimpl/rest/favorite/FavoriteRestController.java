package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.CancelFavoriteRequest;
import com.toutiao.app.api.chance.request.favorite.IsFavoriteRequest;
import com.toutiao.app.api.chance.response.favorite.UserCenterFavoriteCountResponse;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;
import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
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

     //租房标识
    private  final  Integer FAVORITE_RENT=1;
    //二手房标识
    private  final  Integer FAVORITE_ESF=2;


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
    public  NashResult getIsFavoriteByRent(@Validated(First.class) IsFavoriteRequest isFavoriteRequest)
    {
          IsFavoriteDo isFavoriteDo=new IsFavoriteDo();
          BeanUtils.copyProperties(isFavoriteRequest,isFavoriteDo);
          boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_RENT,isFavoriteDo);
          return  NashResult.build(isFavorite);
    }

    /**
     * 判断二手房是否被收藏
     */
    @RequestMapping(value = "getIsFavoriteByEsf",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult getIsFavoriteByEsf(@Validated(Second.class) IsFavoriteRequest isFavoriteRequest)
    {
        IsFavoriteDo isFavoriteDo=new IsFavoriteDo();
        BeanUtils.copyProperties(isFavoriteRequest,isFavoriteDo);
        boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_ESF,isFavoriteDo);
        return  NashResult.build(isFavorite);
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

}
