package com.toutiao.app.service.favorite.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteRentMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteVillageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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


    /**
     *
     * @param newCode
     * @return
     * 获取新房列表页收藏数量
     */

//    @Override
//    public Integer newHouseFavoriteByNewCode(Integer newCode)
//    {
//        int favoriteCount=0;
//        try {
//           favoriteCount= userFavoriteNewHouseMapper.newHouseFavoriteByNewCode(newCode);
//            return favoriteCount;
//        }catch (Exception e)
//        {
//            logger.error("获取新房收藏异常"+newCode.toString()+"={}",e.getStackTrace());
//        }
//       return  favoriteCount;
//    }

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
            int rentCount =userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(isFavoriteDo.getHouseId(),isFavoriteDo.getUserId());
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
        Integer integer = userFavoriteRentMapper.updateRentFavoriteByHouseIdAndUserId(deleteRentFavoriteDoQuery);
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
    public PlotFavoriteListDo getPlotFavoriteByUserId(PlotFavoriteListDoQuery plotFavoriteListDoQuery) {
        PlotFavoriteListDo plotFavoriteListDo = new PlotFavoriteListDo();
        plotFavoriteListDoQuery.setFrom((plotFavoriteListDoQuery.getPageNum()-1)*plotFavoriteListDoQuery.getSize());
        List<UserFavoriteVillage> plotFavoriteByUserId = userFavoriteVillageMapper.getPlotFavoriteByUserId(plotFavoriteListDoQuery);
        if (null!=plotFavoriteByUserId&&plotFavoriteByUserId.size()>0){
            Long count = (long)userFavoriteVillageMapper.getPlotFavoriteCountByUserId(plotFavoriteListDoQuery);
            List<UserFavoritePlotDo> list = JSONArray.parseArray(JSONObject.toJSONString(plotFavoriteByUserId), UserFavoritePlotDo.class);
            plotFavoriteListDo.setData(list);
            plotFavoriteListDo.setTotalNum(count);
        }else {
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_NOT_FOUND,"小区收藏列表为空");
        }
        return plotFavoriteListDo;
    }

    @Override
    public Integer addPlotsFavorite(PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery) {

        Integer integer = userFavoriteVillageMapper.queryCountByUserIdAndHouseId(plotsAddFavoriteDoQuery);
        if (null!=integer&&integer>0){
            Integer code = PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_REPEAT.getValue();
            String desc = PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_REPEAT.getDesc();
//            return NashResult.Fail(code.toString(),desc);
            return -1;
        }
        try {
            Integer result = userFavoriteVillageMapper.addPlotsFavorite(plotsAddFavoriteDoQuery);
            if (result>0){
//                return NashResult.build("收藏成功");
                return 1;
            }
        }catch (Exception e){
            logger.error("小区添加收藏接口异常,plotId:"+plotsAddFavoriteDoQuery.getBuildingId()+", BuildingId:"+plotsAddFavoriteDoQuery.getUserId()+"={}",e.getStackTrace());
        }
//        return NashResult.Fail("收藏失败");
        return 0;
    }

    @Override
    public Integer addNewHouseFavorite(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery) {
        Integer integer = userFavoriteNewHouseMapper.queryCountByUserIdAndHouseId(newHouseAddFavoriteDoQuery);
        if (null!=integer&&integer>0){
//            Integer code = NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_REPEAT.getValue();
//            String desc = NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_REPEAT.getDesc();
//            return NashResult.Fail(code.toString(),desc);
            return -1;
        }
        try {
            Integer result = userFavoriteNewHouseMapper.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
            if (result>0){
//                return NashResult.build("收藏成功");
                return 1;
            }
        }catch (Exception e){
            logger.error("新房添加收藏接口异常,newHouseId:"+newHouseAddFavoriteDoQuery.getBuildingId()+", userId:"+newHouseAddFavoriteDoQuery.getUserId()+"={}",e.getStackTrace());
        }
//        return NashResult.Fail("收藏失败");
        return 0;
    }

    /**
     *新房取消收藏接口
     * @param userFavoriteNewHouse
     * @return
     */
    @Override
    public Integer cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse) {
        try {
            userFavoriteNewHouse.setIsDel((short) 1);
            int result=userFavoriteNewHouseMapper.cancelNewHouseFavoriteByUserIdAndHouseId(userFavoriteNewHouse);
            if(result>0)
            {
//              return NashResult.build(true);
                return 1;
            }
        }catch (Exception e)
        {
            logger.error("取消新房收藏接口异常"+userFavoriteNewHouse.getBuildingId()+"={}",e.getStackTrace());
        }

//        return  NashResult.Fail("收藏取消失败");
        return 0;
    }

    @Override
    public Integer cancelVillageByVillageId(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery) {
        try {
            int result= userFavoriteVillageMapper.cancelVillageByVillageIdAndUserId(plotIsFavoriteDoQuery);
            if (result>0)
            {
//             return  NashResult.build(true);
                return 1;
            }
        }catch (Exception e)
        {
            logger.error("取消小区收藏接口异常"+plotIsFavoriteDoQuery.getBuildingId()+"={}",e.getStackTrace());
        }
//      return NashResult.Fail("收藏取消失败");
        return 0;
    }


    /**
     * 添加二手房收藏
     */
    @Override
    public Integer addEsfFavorite(UserFavoriteEsHouseDoQuery userFavoriteEsHouseDoQuery) {

        //判断重复收藏
        Integer result =0;
        Integer ss1=SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_ERROR.getValue();
        Integer re=userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(userFavoriteEsHouseDoQuery.getHouseId(),userFavoriteEsHouseDoQuery.getUserId());
        if (null!=re && re>0)
        {
            Integer ss= SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_REPEAT.getValue();

//           return NashResult.Fail(ss.toString(),"二手房收藏添加重复");
            return -1;
        }
        try {
            UserFavoriteEsHouse userFavoriteEsHouse = new UserFavoriteEsHouse();
            BeanUtils.copyProperties(userFavoriteEsHouseDoQuery,userFavoriteEsHouse);
            userFavoriteEsHouse.setCreateTime(new Date());
            userFavoriteEsHouse.setPriceIncreaseDecline(Integer.valueOf(userFavoriteEsHouseDoQuery.getPriceIncreaseDecline()));
            result= userFavoriteEsHouseMapper.insertSelective(userFavoriteEsHouse);
        }catch(Exception e)
        {
            logger.error("二手房收藏接口异常："+userFavoriteEsHouseDoQuery.getHouseId()+"={}",e.getStackTrace());
        }
        if (result>0)
        {
//           return NashResult.build("收藏收功");
            return 1;
        }
//       return  NashResult.Fail(ss1.toString(),"二手房添加收藏失败");
        return 0;


    }

    /**
     *
     * @param userFavoriteRentDoQuery
     * @return
     * 添加出租收藏
     */
    @Override
    public Integer addRentFavorite(UserFavoriteRentDoQuery userFavoriteRentDoQuery) {

        //判断重复收藏
        Integer result =0;
        Integer ss1=RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_ERROR.getValue();
        Integer  re= userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(userFavoriteRentDoQuery.getHouseId(),userFavoriteRentDoQuery.getUserId());
        if (null!=re && re>0)
        {
            Integer ss= RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_REPEAT.getValue();
//             return NashResult.Fail(ss.toString(),"租房添加收藏重复");
            return -1;
        }
        try {
            UserFavoriteRent userFavoriteRent = new UserFavoriteRent();
            if("1".equals(userFavoriteRentDoQuery.getRentType()))
            {
                userFavoriteRent.setRentTypeName("整租");
            }
            if ("2".equals(userFavoriteRentDoQuery.getRentType()))
            {
                userFavoriteRent.setRentTypeName("合租");
            }
            BeanUtils.copyProperties(userFavoriteRentDoQuery,userFavoriteRent);
            userFavoriteRent.setCreateTime(new Date());
            result= userFavoriteRentMapper.insertSelective(userFavoriteRent);

        }catch (Exception e)
        {
            logger.error("添加出租收藏异常:"+userFavoriteRentDoQuery.getHouseId()+"={}",e.getStackTrace());
        }
        if (result>0)
        {
//             return NashResult.build("收藏成功");
            return 1;
        }

//        return  NashResult.Fail(ss1.toString(),"租房添加收藏失败");
        return 0;
    }
}
