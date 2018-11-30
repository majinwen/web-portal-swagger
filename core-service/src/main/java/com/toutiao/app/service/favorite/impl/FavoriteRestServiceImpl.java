package com.toutiao.app.service.favorite.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.dao.mapper.officeweb.favorite.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private FavoriteRestMapper favoriteRestMapper;

    @Value("${qiniu.img_domain}")
    private String qiNiuImgDomain;

    private final String suffix = "-dongfangdi400x300";

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
     * @param userId
     * @return 个人中心收藏数量
     */
    @Override
    public UserCenterFavoriteCountDo getFavoriteCountByUser(Integer userId) {
        UserCenterFavoriteCountDo userCenterFavoriteCountDo = new UserCenterFavoriteCountDo();
        try {
            //新房
            Integer favoriteNewHouse = userFavoriteNewHouseMapper.selectFavoriteNewHouseByUserId(userId);
            userCenterFavoriteCountDo.setNewHouseFavoriteCount(favoriteNewHouse);
            //二手房
            Integer favoriteEsHouse = userFavoriteEsHouseMapper.selectEsHouseFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setEsfHouseFavoriteCount(favoriteEsHouse);
            //租房
            Integer favoriteRent = userFavoriteRentMapper.selectRentFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setRentHouseFavoriteCount(favoriteRent);
            //小区
            Integer favoriteVillage = userFavoriteVillageMapper.selectVillageFavoriteByUserId(userId);
            userCenterFavoriteCountDo.setPlotFavoriteCount(favoriteVillage);

        } catch (Exception e) {
            logger.error("获取个人中心收藏数量异常" + userId.toString() + "={}", e.getStackTrace());
        }


        return userCenterFavoriteCountDo;

    }

    /**
     * @param type
     * @param isFavoriteDo
     * @return 出租和二手房查看是否被收藏
     */
    @Override
    public Boolean getIsFavorite(Integer type, IsFavoriteDo isFavoriteDo) {
        boolean isFavorite = false;
        //判断租房是否被收藏
        if (null != type && type == 1) {
            int rentCount = userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(isFavoriteDo.getHouseId(), isFavoriteDo.getUserId());
            if (rentCount > 0) {
                isFavorite = true;
                return isFavorite;
            }
        }
        if (null != type && type == 2) {
            int esfCount = userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(isFavoriteDo.getHouseId(), isFavoriteDo.getUserId());
            if (esfCount > 0) {
                isFavorite = true;
                return isFavorite;
            }
        }
        return isFavorite;
    }

    @Override
    public Integer getPlotFavoriteCountByPlotId(Integer plotId) {
        int favoriteCount = 0;
        try {
            favoriteCount = userFavoriteVillageMapper.selectPlotFavoriteCountByPlotId(plotId);
            return favoriteCount;
        } catch (Exception e) {
            logger.error("获取小区收藏数量异常" + plotId.toString() + "={}", e.getStackTrace());
        }
        return favoriteCount;
    }

    @Override
    public Boolean updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo) {
        boolean flag = false;
        Integer integer = userFavoriteEsHouseMapper.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery) {
        boolean flag = false;
        Integer integer = userFavoriteRentMapper.updateRentFavoriteByHouseIdAndUserId(deleteRentFavoriteDoQuery);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean getPlotIsFavorite(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery) {
        boolean isFavorite = false;
        //判断小区是否被收藏
        Integer integer = userFavoriteVillageMapper.selectPlotIsFavorite(plotIsFavoriteDoQuery);
        if (integer > 0) {
            isFavorite = true;
        }
        return isFavorite;
    }

    @Override
    public Boolean getNewHouseIsFavorite(NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery) {
        boolean isFavorite = false;
        //判断新房是否被收藏
        Integer newHouseIsFavorite = userFavoriteNewHouseMapper.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
        if (newHouseIsFavorite > 0) {
            isFavorite = true;
        }
        return isFavorite;
    }

    @Override
    public PlotFavoriteListDo getPlotFavoriteByUserId(PlotFavoriteListDoQuery plotFavoriteListDoQuery) {
        PlotFavoriteListDo plotFavoriteListDo = new PlotFavoriteListDo();
        plotFavoriteListDoQuery.setFrom((plotFavoriteListDoQuery.getPageNum() - 1) * plotFavoriteListDoQuery.getSize());
        List<UserFavoriteVillage> plotFavoriteByUserId = userFavoriteVillageMapper.getPlotFavoriteByUserId(plotFavoriteListDoQuery);
        if (null != plotFavoriteByUserId && plotFavoriteByUserId.size() > 0) {
            Long count = (long) userFavoriteVillageMapper.getPlotFavoriteCountByUserId(plotFavoriteListDoQuery);
            List<UserFavoritePlotDo> list = JSONArray.parseArray(JSONObject.toJSONString(plotFavoriteByUserId), UserFavoritePlotDo.class);
            plotFavoriteListDo.setData(list);
            plotFavoriteListDo.setTotalNum(count);
        } else {
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_NOT_FOUND, "小区收藏列表为空");
        }
        return plotFavoriteListDo;
    }

    @Override
    public Integer addPlotsFavorite(PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery) {

        Integer integer = userFavoriteVillageMapper.queryCountByUserIdAndHouseId(plotsAddFavoriteDoQuery);
        if (null != integer && integer > 0) {
            Integer code = PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_REPEAT.getValue();
            String desc = PlotsInterfaceErrorCodeEnum.PLOTS_FAVORITE_ADD_REPEAT.getDesc();
//            return NashResult.Fail(code.toString(),desc);
            return -1;
        }
        try {
            Integer result = userFavoriteVillageMapper.addPlotsFavorite(plotsAddFavoriteDoQuery);
            if (result > 0) {
//                return NashResult.build("收藏成功");
                return 1;
            }
        } catch (Exception e) {
            logger.error("小区添加收藏接口异常,plotId:" + plotsAddFavoriteDoQuery.getBuildingId() + ", BuildingId:" + plotsAddFavoriteDoQuery.getUserId() + "={}", e.getStackTrace());
        }
//        return NashResult.Fail("收藏失败");
        return 0;
    }

    @Override
    public Integer addNewHouseFavorite(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery) {
        Integer integer = userFavoriteNewHouseMapper.queryCountByUserIdAndHouseId(newHouseAddFavoriteDoQuery);
        if (null != integer && integer > 0) {
//            Integer code = NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_REPEAT.getValue();
//            String desc = NewHouseInterfaceErrorCodeEnum.NEWHOUSE_FAVORITE_ADD_REPEAT.getDesc();
//            return NashResult.Fail(code.toString(),desc);
            return -1;
        }
        try {
            Integer result = userFavoriteNewHouseMapper.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
            if (result > 0) {
//                return NashResult.build("收藏成功");
                return 1;
            }
        } catch (Exception e) {
            logger.error("新房添加收藏接口异常,newHouseId:" + newHouseAddFavoriteDoQuery.getBuildingId() + ", userId:" + newHouseAddFavoriteDoQuery.getUserId() + "={}", e.getStackTrace());
        }
//        return NashResult.Fail("收藏失败");
        return 0;
    }

    /**
     * 新房取消收藏接口
     *
     * @param userFavoriteNewHouse
     * @return
     */
    @Override
    public Integer cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse) {
        try {
            userFavoriteNewHouse.setIsDel((short) 1);
            int result = userFavoriteNewHouseMapper.cancelNewHouseFavoriteByUserIdAndHouseId(userFavoriteNewHouse);
            if (result > 0) {
                return 1;
            }
        } catch (Exception e) {
            logger.error("取消新房收藏接口异常" + userFavoriteNewHouse.getBuildingId() + "={}", e.getStackTrace());
        }

        return 0;
    }

    @Override
    public Integer cancelVillageByVillageId(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery) {
        try {
            int result = userFavoriteVillageMapper.cancelVillageByVillageIdAndUserId(plotIsFavoriteDoQuery);
            if (result > 0) {
//             return  NashResult.build(true);
                return 1;
            }
        } catch (Exception e) {
            logger.error("取消小区收藏接口异常" + plotIsFavoriteDoQuery.getBuildingId() + "={}", e.getStackTrace());
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
        Integer result = 0;
        Integer ss1 = SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_ERROR.getValue();
        Integer re = userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(userFavoriteEsHouseDoQuery.getHouseId(), userFavoriteEsHouseDoQuery.getUserId());
        if (null != re && re > 0) {
            Integer ss = SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_ADD_REPEAT.getValue();

//           return NashResult.Fail(ss.toString(),"二手房收藏添加重复");
            return -1;
        }
        try {
            UserFavoriteEsHouse userFavoriteEsHouse = new UserFavoriteEsHouse();
            BeanUtils.copyProperties(userFavoriteEsHouseDoQuery, userFavoriteEsHouse);
            userFavoriteEsHouse.setCreateTime(new Date());
            userFavoriteEsHouse.setPriceIncreaseDecline(Integer.valueOf(userFavoriteEsHouseDoQuery.getPriceIncreaseDecline()));
            result = userFavoriteEsHouseMapper.insertSelective(userFavoriteEsHouse);
        } catch (Exception e) {
            logger.error("二手房收藏接口异常：" + userFavoriteEsHouseDoQuery.getHouseId() + "={}", e.getStackTrace());
        }
        if (result > 0) {
//           return NashResult.build("收藏收功");
            return 1;
        }
//       return  NashResult.Fail(ss1.toString(),"二手房添加收藏失败");
        return 0;


    }

    /**
     * @param userFavoriteRentDoQuery
     * @return 添加出租收藏
     */
    @Override
    public Integer addRentFavorite(UserFavoriteRentDoQuery userFavoriteRentDoQuery) {

        //判断重复收藏
        Integer result = 0;
        Integer ss1 = RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_ERROR.getValue();
        Integer re = userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(userFavoriteRentDoQuery.getHouseId(), userFavoriteRentDoQuery.getUserId());
        if (null != re && re > 0) {
            Integer ss = RentInterfaceErrorCodeEnum.RENT_FAVORITE_ADD_REPEAT.getValue();
//             return NashResult.Fail(ss.toString(),"租房添加收藏重复");
            return -1;
        }
        try {
            UserFavoriteRent userFavoriteRent = new UserFavoriteRent();
            if ("1".equals(userFavoriteRentDoQuery.getRentType())) {
                userFavoriteRent.setRentTypeName("整租");
            }
            if ("2".equals(userFavoriteRentDoQuery.getRentType())) {
                userFavoriteRent.setRentTypeName("合租");
            }
            BeanUtils.copyProperties(userFavoriteRentDoQuery, userFavoriteRent);
            userFavoriteRent.setCreateTime(new Date());
            result = userFavoriteRentMapper.insertSelective(userFavoriteRent);

        } catch (Exception e) {
            logger.error("添加出租收藏异常:" + userFavoriteRentDoQuery.getHouseId() + "={}", e.getStackTrace());
        }
        if (result > 0) {
//             return NashResult.build("收藏成功");
            return 1;
        }

//        return  NashResult.Fail(ss1.toString(),"租房添加收藏失败");
        return 0;
    }

    /**
     * 查询我的收藏房源
     *
     * @param favoriteHouseDoQuery
     * @return
     */
    @Override
    public FavoriteHouseDomain queryFavoriteHouseList(FavoriteHouseListDoQuery favoriteHouseDoQuery) {

        FavoriteHouseDomain favoriteHouseDomain = new FavoriteHouseDomain();
        Page<Object> page = PageHelper.startPage(favoriteHouseDoQuery.getPageNum(), favoriteHouseDoQuery.getSize());
        List<FavoriteHouseDo> favoriteHouseDoList = favoriteRestMapper.queryFavoriteList(favoriteHouseDoQuery.getUserId());

        List<FavoriteHouseVo> favoriteHouseVoList = new ArrayList<>();

        FavoriteHouseVo favoriteHouseVo = null;

        for (FavoriteHouseDo favoriteHouseDo : favoriteHouseDoList) {
            favoriteHouseVo = new FavoriteHouseVo();
            Integer type = favoriteHouseDo.getType(); // 1 新房 2 二手房 3 小区 4 租房
            favoriteHouseVo.setType(type);
            favoriteHouseVo.setStatus(favoriteHouseDo.getStatus());
            favoriteHouseVo.setAreaName(favoriteHouseDo.getAreaName());
            favoriteHouseVo.setDistrictName(favoriteHouseDo.getDistrictName());
            favoriteHouseVo.setCityId(favoriteHouseDo.getCityId());
            // TODO 添加标签
            favoriteHouseVo.setTagNames(null);

            switch (type) {
                case 1:// 新房
                    favoriteHouseVo.setId(favoriteHouseDo.getBuildingId().toString());
                    favoriteHouseVo.setBuildingName(favoriteHouseDo.getBuildingName());
                    String buildingTitleImg = favoriteHouseDo.getBuildingTitleImg();

                    if (null != buildingTitleImg && !buildingTitleImg.startsWith("http")) {
                        favoriteHouseVo.setTitleImg(qiNiuImgDomain + "/" + buildingTitleImg + suffix);
                    } else {
                        favoriteHouseVo.setTitleImg(buildingTitleImg);
                    }
                    favoriteHouseVo.setPrice(favoriteHouseDo.getAveragePrice());
                    favoriteHouseVo.setHouseMinArea(favoriteHouseDo.getHouseMinArea());
                    favoriteHouseVo.setHouseMaxArea(favoriteHouseDo.getHouseMaxArea());
                    favoriteHouseVo.setTotalPrice(favoriteHouseDo.getTotalPrice());
                    // TODO 销售居室
                    favoriteHouseVo.setRoomType(null);
                    break;
                case 2: // 二手房
                    favoriteHouseVo.setId(favoriteHouseDo.getHouseId());
                    favoriteHouseVo.setBuildingName(favoriteHouseDo.getBuildingName());
                    favoriteHouseVo.setTitle(favoriteHouseDo.getBuildingName());
                    String housePhotoTitle = favoriteHouseDo.getHousePhotoTitle();

                    if (null != housePhotoTitle && !housePhotoTitle.startsWith("http")) {
                        favoriteHouseVo.setTitleImg(qiNiuImgDomain + "/" + housePhotoTitle + suffix);
                    } else {
                        favoriteHouseVo.setTitleImg(housePhotoTitle);
                    }
                    favoriteHouseVo.setPrice(favoriteHouseDo.getHouseTotalPrices());
                    favoriteHouseVo.setRoom(favoriteHouseDo.getRoom());
                    // TODO 厅
                    favoriteHouseVo.setHall(1);
                    favoriteHouseVo.setForward(favoriteHouseDo.getForward());
                    favoriteHouseVo.setBuildArea(favoriteHouseDo.getBuildArea());
                    break;
                case 3: // 小区
                    favoriteHouseVo.setId(favoriteHouseDo.getBuildingId().toString());
                    favoriteHouseVo.setBuildingName(favoriteHouseDo.getBuildingName());
                    String buildingTitleImgPlot = favoriteHouseDo.getBuildingTitleImg();

                    if (null != buildingTitleImgPlot && !buildingTitleImgPlot.startsWith("http")) {
                        favoriteHouseVo.setTitleImg(qiNiuImgDomain + "/" + buildingTitleImgPlot + suffix);
                    } else {
                        favoriteHouseVo.setTitleImg(buildingTitleImgPlot);
                    }
                    favoriteHouseVo.setPrice(favoriteHouseDo.getAveragePrice());
                    // TODO 添加小区建成时间、类型
                    favoriteHouseVo.setBuildTime(null);
                    favoriteHouseVo.setBuildType(null);
                    break;
                case 4: // 租房
                    favoriteHouseVo.setId(favoriteHouseDo.getHouseId());
                    favoriteHouseVo.setBuildingName(favoriteHouseDo.getBuildingName());
                    favoriteHouseVo.setTitle(favoriteHouseDo.getHouseTitle());
                    String housePhotoTitleRent = favoriteHouseDo.getHousePhotoTitle();

                    if (null != housePhotoTitleRent && !housePhotoTitleRent.startsWith("http")) {
                        favoriteHouseVo.setTitleImg(qiNiuImgDomain + "/" + housePhotoTitleRent + suffix);
                    } else {
                        favoriteHouseVo.setTitleImg(housePhotoTitleRent);
                    }
                    favoriteHouseVo.setPrice(favoriteHouseDo.getRentPrice());
                    favoriteHouseVo.setRentTypeName(favoriteHouseDo.getRentTypeName());
                    favoriteHouseVo.setRoom(favoriteHouseDo.getRoom());
                    // TODO 厅
                    favoriteHouseVo.setHall(1);
                    favoriteHouseVo.setHouseArea(favoriteHouseDo.getHouseArea());
                    favoriteHouseVo.setForward(favoriteHouseDo.getForward());

                    break;
            }

            favoriteHouseVoList.add(favoriteHouseVo);
        }

        favoriteHouseDomain.setList(favoriteHouseVoList);
        favoriteHouseDomain.setTotalCount((int) page.getTotal());
        return favoriteHouseDomain;

    }
}
