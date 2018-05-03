package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.api.chance.request.favorite.NewHouseFavoriteListRequest;
import com.toutiao.app.api.chance.response.favorite.NewHouseFavoriteListResponse;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.NewHouseFavoriteRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/favorite")
public class NewHouseFavoriteRestController {


    @Autowired
    private NewHouseFavoriteRestService newHouseFavoriteRestService;


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


}
