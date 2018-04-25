package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.impl.NewHouseRestServiceImpl;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.NashResult;
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

    /**
     *新房取消收藏接口
     * @param userFavoriteNewHouse
     * @return
     */
    @Override
    public NashResult cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse) {
        try {
            userFavoriteNewHouse.setIsDel(1);
          int result=userFavoriteNewHouseMapper.cancelNewHouseFavoriteByUserIdAndHouseId(userFavoriteNewHouse);
          if(result>0)
          {
              return NashResult.build(true);
          }
        }catch (Exception e)
        {
            logger.error("取消新房收藏接口异常"+userFavoriteNewHouse.getNewHouseId()+"={}",e.getStackTrace());
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
}
