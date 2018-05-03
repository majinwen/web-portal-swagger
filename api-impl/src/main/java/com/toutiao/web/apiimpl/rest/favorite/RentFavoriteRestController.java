package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.*;
import com.toutiao.app.api.chance.response.favorite.RentFavoriteListResponse;
import com.toutiao.app.domain.favorite.DeleteRentFavoriteDoQuery;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteRent;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.RentFavoriteRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/favorite/rent")
public class RentFavoriteRestController {

    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private RentFavoriteRestService rentFavoriteRestService;
    //租房标识
    private  final  Integer FAVORITE_RENT=1;


    /**
     * 租房收藏列表
     * @param rentFavoriteListRequest
     * @return
     */
    @RequestMapping(value = "/getRentFavoriteByUserId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getRentFavoriteByUserId(@Validated RentFavoriteListRequest rentFavoriteListRequest){

        RentFavoriteListDoQuery rentFavoriteListDoQuery = new RentFavoriteListDoQuery();
        BeanUtils.copyProperties(rentFavoriteListRequest,rentFavoriteListDoQuery);
        RentFavoriteDomain rentFavoriteDomain = rentFavoriteRestService.queryRentFavoriteListByUserId(rentFavoriteListDoQuery);
        RentFavoriteListResponse rentFavoriteListResponse = new RentFavoriteListResponse();
        BeanUtils.copyProperties(rentFavoriteDomain,rentFavoriteListResponse);
        return NashResult.build(rentFavoriteListResponse);
    }


    /**
     * 租房添加收藏
     */
    @RequestMapping(value = "/addRentFavorite" ,method = RequestMethod.POST)
    @ResponseBody

    public NashResult addRentFavorite(@Validated(Second.class) @RequestBody AddFavorite addFavorite)
    {
        UserFavoriteRent userFavoriteRent =new UserFavoriteRent();
        BeanUtils.copyProperties(addFavorite,userFavoriteRent);
        return favoriteRestService.addRentFavorite(userFavoriteRent);
    }


    /**
     * 租房取消收藏
     * @param deleteRentFavoriteRequest
     * @return
     */
    @RequestMapping(value = "/deleteRentFavoriteByRentIdAndUserId",method = RequestMethod.GET)
    public NashResult deleteRentFavoriteByRentIdAndUserId(@Validated DeleteRentFavoriteRequest deleteRentFavoriteRequest){
        DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery = new DeleteRentFavoriteDoQuery();
        BeanUtils.copyProperties(deleteRentFavoriteRequest, deleteRentFavoriteDoQuery);
        Boolean aBoolean = favoriteRestService.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDoQuery);
        return NashResult.build(aBoolean);
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


}
