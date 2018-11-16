//package com.toutiao.web.apiimpl.rest.favorite;
//
//
//import com.toutiao.app.api.chance.response.favorite.UserCenterFavoriteCountResponse;
//import com.toutiao.app.domain.favorite.*;
//import com.toutiao.app.service.favorite.FavoriteRestService;
//import com.toutiao.web.common.restmodel.NashResult;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("rest/favorite")
//public class FavoriteRestController {
//
//     @Autowired
//     private FavoriteRestService favoriteRestService;
//
//
//
//
//
//    /**
//     *
//     * @param userId
//     * @return
//     * 个人中心租房，二手房，新房，小区，收藏数量
//     */
//    @RequestMapping(value = "/getFavoriteCountByUser",method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult getFavoriteCountByUser(@RequestParam("userId") Integer userId)
//    {
//        UserCenterFavoriteCountResponse userCenterFavoriteCountResponse=new UserCenterFavoriteCountResponse();
//         UserCenterFavoriteCountDo userCenterFavoriteCountDo= favoriteRestService.getFavoriteCountByUser(userId);
//        BeanUtils.copyProperties(userCenterFavoriteCountDo,userCenterFavoriteCountResponse);
//        return  NashResult.build(userCenterFavoriteCountResponse);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//
