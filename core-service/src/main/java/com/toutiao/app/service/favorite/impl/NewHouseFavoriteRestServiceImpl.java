package com.toutiao.app.service.favorite.impl;


import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.NewHouseFavoriteRestService;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewHouseFavoriteRestServiceImpl implements NewHouseFavoriteRestService {

    @Autowired
    private UserFavoriteNewHouseMapper userFavoriteNewHouseMapper;


    /**
     * 新房收藏列表
     * @param newHouseFavoriteListDoQuery
     * @return
     */
    @Override
    public NewHouseFavoriteDomain queryNewHouseFavoriteListByUserId(NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery) {

        NewHouseFavoriteDomain newHouseFavoriteDomain = new NewHouseFavoriteDomain();

        newHouseFavoriteListDoQuery.setFrom((newHouseFavoriteListDoQuery.getPageNum()-1)*newHouseFavoriteListDoQuery.getSize());

        List<NewHouseFavoriteDo> newHouseFavoriteDos = userFavoriteNewHouseMapper.selectNewHouseFavoriteByUserId(newHouseFavoriteListDoQuery);

        if(null!=newHouseFavoriteDos && newHouseFavoriteDos.size()>0){
            newHouseFavoriteDomain.setData(newHouseFavoriteDos);

            int newHouseFavourite = userFavoriteNewHouseMapper.selectFavoriteNewHouseByUserId(newHouseFavoriteListDoQuery.getUserId());
            newHouseFavoriteDomain.setTotalNum(Long.valueOf(newHouseFavourite));

        }else{
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_NOT_FOUND,"新房收藏列表为空");
        }



        return newHouseFavoriteDomain;
    }
}
