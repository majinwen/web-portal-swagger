package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteRentMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteVillageMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.List;

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

    @Autowired
    private PlotsRestService plotsRestService;

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

    /**
     *
     * @param type
     * @param isFavoriteDo
     * @return 出租和二手房查看是否被收藏
     */
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
        if(null!=type && type==2)
        {
            int esfCount=userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(isFavoriteDo.getHouseId(),isFavoriteDo.getUserId());
            if (esfCount>0)
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
    public Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery) {
        boolean flag = false;
        Integer integer = userFavoriteRentMapper.updateRentFavoriteByRentIdAndUserId(deleteRentFavoriteDoQuery);
        if (integer>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean getPlotIsFavorite(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery) {
        boolean isFavorite=false;
        //判断小区是否被收藏
        Integer integer = userFavoriteVillageMapper.selectPlotIsFavorite(plotIsFavoriteDoQuery);
        if (integer>0){
            isFavorite = true;
        }
        return  isFavorite;
    }

    @Override
    public Boolean getNewHouseIsFavorite(NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery) {
        boolean isFavorite=false;
        //判断新房是否被收藏
        Integer newHouseIsFavorite = userFavoriteNewHouseMapper.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
        if (newHouseIsFavorite>0){
            isFavorite = true;
        }
        return isFavorite;
    }

    @Override
    public PlotFavoriteListDo getPlotFavoriteByUserId(Integer userId,Integer pageNum,Integer size) {
        List plotId = userFavoriteVillageMapper.getPlotFavoriteByUserId(userId);
        PlotFavoriteListDo plotFavoriteListDo = plotsRestService.queryPlotListByPlotIdList(plotId, pageNum, size);
        return plotFavoriteListDo;
    }

    /**
     *新房取消收藏接口
     * @param userFavoriteNewHouse
     * @return
     */
    @Override
    public NashResult cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse) {
        try {
            userFavoriteNewHouse.setIsDel((short) 1);
          int result=userFavoriteNewHouseMapper.cancelNewHouseFavoriteByUserIdAndHouseId(userFavoriteNewHouse);
          if(result>0)
          {
              return NashResult.build(true);
          }
        }catch (Exception e)
        {
            logger.error("取消新房收藏接口异常"+userFavoriteNewHouse.getNewCode()+"={}",e.getStackTrace());
        }

        return  NashResult.Fail("收藏取消失败");
    }

    @Override
    public NashResult cancelVillageByVillageId(UserFavoriteVillage userFavoriteVillage) {
      try {
          userFavoriteVillage.setIsDel((short) 1);
         int result= userFavoriteVillageMapper.cancelVillageByVillageIdAndUserId(userFavoriteVillage);
         if (result>0)
         {
             return  NashResult.build(true);
         }
      }catch (Exception e)
      {
          logger.error("取消小区收藏接口异常"+userFavoriteVillage.getVillageId()+"={}",e.getStackTrace());
      }
      return NashResult.Fail("收藏取消失败");
    }


    /**
     * 添加二手房收藏
     */
    @Override
    public NashResult addEsfFavorite(UserFavoriteEsHouse userFavoriteEsHouse) {

        //判断重复收藏
       Integer result =0;
       Integer re=userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(userFavoriteEsHouse.getHouseId(),userFavoriteEsHouse.getUserId());
       if (null!=re && re>0)
       {
           return NashResult.Fail("重复收藏");
       }
       try {
           userFavoriteEsHouse.setCreateTime(new Date());
           result= userFavoriteEsHouseMapper.insertSelective(userFavoriteEsHouse);
       }catch(Exception e)
       {
           logger.error("二手房收藏接口异常："+userFavoriteEsHouse.getHouseId()+"={}",e.getStackTrace());
       }
       if (result>0)
       {
           return NashResult.build("收藏收功");
       }
       return  NashResult.Fail("收藏失败");


    }

    /**
     *
     * @param userFavoriteRent
     * @return
     * 添加出租收藏
     */
    @Override
    public NashResult addRentFavorite(UserFavoriteRent userFavoriteRent) {

       //判断重复收藏
        Integer result =0;
        Integer  re= userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(userFavoriteRent.getHouseId(),userFavoriteRent.getUserId());
        if (null!=re && re>0)
         {
             return NashResult.Fail("重复收藏");
         }
         try {
             userFavoriteRent.setCreateTime(new Date());
             result= userFavoriteRentMapper.insertSelective(userFavoriteRent);

         }catch (Exception e)
         {
             logger.error("添加出租收藏异常:"+userFavoriteRent.getHouseId()+"={}",e.getStackTrace());
         }
         if (result>0)
         {
             return NashResult.build("收藏收功");
         }

        return  NashResult.Fail("收藏失败");
    }
}
