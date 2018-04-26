package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteRentMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteVillageMapper;
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
    private UserFavoriteEsHouseMapper userFavoriteEsHouseMapper;

    @Autowired
    private UserFavoriteRentMapper userFavoriteRentMapper;

    @Autowired
    private UserFavoriteVillageMapper userFavoriteVillageMapper;



    /**
     *
     * @param newCode
     * @return
     * 获取新房列表页收藏数量
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

    /**
     *
     * @param userId
     * @return
     * 个人中心收藏数量
     */
    @Override
    public UserCenterFavoriteCountDo getFavoriteCountByUser(Integer userId) {
        UserCenterFavoriteCountDo userCenterFavoriteCountDo=new  UserCenterFavoriteCountDo();
        try {
            //新房
            Integer favoriteNewHouse=userFavoriteNewHouseMapper.selectFavoriteNewHouseByUserId(userId);
            userCenterFavoriteCountDo.setNewHouseFavoriteCount(favoriteNewHouse);
            //二手房
            Integer favoriteEsHouse= userFavoriteEsHouseMapper.selectEsHouseFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setEsfHouseFavoriteCount(favoriteEsHouse);
            //租房
            Integer favoriteRent=userFavoriteRentMapper.selectRentFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setRentHouseFavoriteCount(favoriteRent);
            //小区
            Integer favoriteVillage=userFavoriteVillageMapper.selectVillageFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setPlotFavoriteCount(favoriteVillage);

        }catch (Exception e)
        {
            logger.error("获取个人中心收藏数量异常"+userId.toString()+"={}",e.getStackTrace());
        }


       return userCenterFavoriteCountDo;

    }

    @Override
    public Boolean getIsFavorite(Integer type, IsFavoriteDo isFavoriteDo) {
        boolean isFavorite=false;
        //判断租房是否被收藏
        if (null!=type && type==1)
        {
          int rentCount =userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(isFavoriteDo.getRentId(),isFavoriteDo.getUserId());
          if(rentCount>0)
          {
              isFavorite=true;
              return isFavorite;
          }
        }

     return  isFavorite;
    }

    @Override
    public Integer getPlotFavoriteCountByPlotId(Integer plotId) {
        int favoriteCount=0;
        try {
            favoriteCount= userFavoriteVillageMapper.selectPlotFavoriteCountByPlotId(plotId);
            return favoriteCount;
        }catch (Exception e)
        {
            logger.error("获取小区收藏数量异常"+plotId.toString()+"={}",e.getStackTrace());
        }
        return  favoriteCount;
    }

    @Override
    public Boolean updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo) {
        boolean flag = false;
        Integer integer = userFavoriteEsHouseMapper.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        if (integer>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDo deleteRentFavoriteDo) {
        boolean flag = false;
        Integer integer = userFavoriteRentMapper.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDo);
        if (integer>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean getPlotIsFavorite(PlotIsFavoriteDo plotIsFavoriteDo) {
        boolean isFavorite=false;
        //判断小区是否被收藏
        Integer integer = userFavoriteVillageMapper.selectPlotIsFavorite(plotIsFavoriteDo);
        if (integer>0){
            isFavorite = true;
        }
        return  isFavorite;
    }

    @Override
    public Boolean getNewHouseIsFavorite(NewHouseIsFavoriteDo newHouseIsFavoriteDo) {
        boolean isFavorite=false;
        //判断新房是否被收藏
        Integer newHouseIsFavorite = userFavoriteNewHouseMapper.getNewHouseIsFavorite(newHouseIsFavoriteDo);
        if (newHouseIsFavorite>0){
            isFavorite = true;
        }
        return isFavorite;
    }
}
