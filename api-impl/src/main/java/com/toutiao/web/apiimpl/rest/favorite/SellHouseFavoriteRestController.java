package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.*;
import com.toutiao.app.api.chance.response.favorite.SellHouseFavoriteListResponse;
import com.toutiao.app.domain.favorite.DeleteEsfFavoriteDo;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteEsHouse;
import com.toutiao.app.domain.favorite.UserFavoriteEsHouseDoQuery;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.SellHouseFavoriteRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/favorite/esf")
public class SellHouseFavoriteRestController {

    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private SellHouseFavoriteRestService sellHouseFavoriteRestService;
    //二手房标识
    private  final  Integer FAVORITE_ESF=2;

    /**
     * 二手房收藏列表
     * @param sellHouseFavoriteListRequest
     * @return
     */
    @RequestMapping(value = "/getEsfFavoriteByUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getEsfFavoriteByUserId(@Validated SellHouseFavoriteListRequest sellHouseFavoriteListRequest){

        SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
        BeanUtils.copyProperties(sellHouseFavoriteListRequest,sellHouseFavoriteListDoQuery);
        SellHouseFavoriteDomain sellHouseFavoriteDomain = sellHouseFavoriteRestService.queryNewHouseFavoriteListByUserId(sellHouseFavoriteListDoQuery);
        SellHouseFavoriteListResponse sellHouseFavoriteListResponse = new SellHouseFavoriteListResponse();
        BeanUtils.copyProperties(sellHouseFavoriteDomain,sellHouseFavoriteListResponse);
        return NashResult.build(sellHouseFavoriteListResponse);
    }




    /**
     * 二手房添加收藏
     */
    @RequestMapping(value = "/addEsfFavorite",method = RequestMethod.POST)
    @ResponseBody
    public NashResult addEsfFavorite(@Validated(First.class) @RequestBody AddFavorite addFavorite)
    {
        UserFavoriteEsHouseDoQuery userFavoriteEsHouse= new UserFavoriteEsHouseDoQuery();
        BeanUtils.copyProperties(addFavorite,userFavoriteEsHouse);
        userFavoriteEsHouse.setPriceIncreaseDecline(addFavorite.getIsCutPrice());
        return favoriteRestService.addEsfFavorite(userFavoriteEsHouse);
    }

    /**
     * 二手房取消收藏
     * @param deleteEsfFavoriteResquest
     * @return
     */
    @RequestMapping(value = "/deleteEsfFavoriteByEsfIdAndUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult deleteEsfFavoriteByEsfIdAndUserId(@Validated DeleteEsfFavoriteResquest deleteEsfFavoriteResquest){
        DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
        BeanUtils.copyProperties(deleteEsfFavoriteResquest,deleteEsfFavoriteDo);
        Boolean aBoolean = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        return NashResult.build(aBoolean);
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

}
