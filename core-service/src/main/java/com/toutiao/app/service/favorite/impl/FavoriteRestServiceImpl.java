package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteRestServiceImpl implements FavoriteRestService {
    @Autowired
    private UserFavoriteNewHouseMapper userFavoriteNewHouseMapper;


    @Override
    public Integer newHouseFavoriteByNewCode(Integer newCode)
    {
        int favoriteCount=0;
        try {
           favoriteCount= userFavoriteNewHouseMapper.newHouseFavoriteByNewCode(newCode);
            return favoriteCount;
        }catch (Exception e)
        {

        }
       return  favoriteCount;
    }
}
