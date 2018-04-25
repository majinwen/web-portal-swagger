package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.UserFavoritePlot;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.impl.NewHouseRestServiceImpl;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoritePlotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteRestServiceImpl implements FavoriteRestService {


    private Logger logger = LoggerFactory.getLogger(FavoriteRestServiceImpl.class);
    @Autowired
    private UserFavoriteNewHouseMapper userFavoriteNewHouseMapper;

    @Autowired
    private UserFavoritePlotMapper userFavoritePlotMapper;
    /**
     *
     * @param newCode
     * @return
     * 获取新房收藏数量
     */

    @Override
    public Integer newHouseFavoriteByNewCode(Integer newCode)
    {
        int favoriteCount=0;
        try {
           favoriteCount= userFavoriteNewHouseMapper.newHouseFavoriteByNewCode(newCode);
            return favoriteCount;
        }catch (Exception e)
        {
            logger.error("获取新房收藏异常"+newCode.toString()+"={}",e.getStackTrace());
        }
       return  favoriteCount;
    }

    @Override
    public Integer queryPlotFavoriteByUserId(Integer userId) {
        int favoriteCount=0;
        try {
            favoriteCount= userFavoritePlotMapper.selectPlotFavoriteByUserId(userId);
            return favoriteCount;
        }catch (Exception e)
        {
            logger.error("获取个人中心小区数量异常"+userId.toString()+"={}",e.getStackTrace());
        }
        return  favoriteCount;
    }

    @Override
    public Integer queryPlotFavoriteByPlotId(Integer plotId) {
        int favoriteCount=0;
        try {
            favoriteCount= userFavoritePlotMapper.queryPlotFavoriteByPlotId(plotId);
            return favoriteCount;
        }catch (Exception e)
        {
            logger.error("获取小区总收藏数量异常"+plotId.toString()+"={}",e.getStackTrace());
        }
        return  favoriteCount;
    }
}
