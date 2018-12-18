package com.toutiao.app.service.favorite.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.mapper.officeweb.favorite.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    private NewHouseRestService newHouseService;

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
    @Transactional
    public Boolean updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo) {
        boolean flag = false;
        Integer integer = userFavoriteEsHouseMapper.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    @Transactional
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
    @Transactional
    public Integer addPlotsFavorite(PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery) {

        Integer integer = userFavoriteVillageMapper.queryCountByUserIdAndHouseId(plotsAddFavoriteDoQuery);
        if (null != integer && integer > 0) {
            return -1;
        }
        try {
            Integer result = userFavoriteVillageMapper.addPlotsFavorite(plotsAddFavoriteDoQuery);
            if (result > 0) {
                return 1;
            }
        } catch (Exception e) {
            logger.error("小区添加收藏接口异常,plotId:" + plotsAddFavoriteDoQuery.getBuildingId() + ", BuildingId:" + plotsAddFavoriteDoQuery.getUserId() + "={}", e.getStackTrace());
        }
        return 0;
    }

    @Override
    @Transactional
    public Integer addNewHouseFavorite(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery) {
        Integer i = userFavoriteNewHouseMapper.queryCountByUserIdAndHouseId(newHouseAddFavoriteDoQuery);
        if (null != i && i > 0) {
            return -1;
        }
        try {
            Integer result = userFavoriteNewHouseMapper.addNewHouseFavorite(newHouseAddFavoriteDoQuery);
            if (result > 0) {
                return 1;
            }
        } catch (Exception e) {
            logger.error("新房添加收藏接口异常,newHouseId:" + newHouseAddFavoriteDoQuery.getBuildingId() + ", userId:" + newHouseAddFavoriteDoQuery.getUserId() + "={}", e.getStackTrace());
        }
        return 0;
    }

    /**
     * 新房取消收藏接口
     *
     * @param userFavoriteNewHouse
     * @return
     */
    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public Integer addEsfFavorite(UserFavoriteEsHouseDoQuery userFavoriteEsHouseDoQuery) {

        //判断重复收藏
        Integer result = 0;
        Integer re = userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(userFavoriteEsHouseDoQuery.getHouseId(), userFavoriteEsHouseDoQuery.getUserId());
        if (null != re && re > 0) {

            return -1;
        }
        try {
            userFavoriteEsHouseDoQuery.setCreateTime(new Date());
            result = userFavoriteEsHouseMapper.insertSellHouseSelective(userFavoriteEsHouseDoQuery);
        } catch (Exception e) {
            logger.error("二手房收藏接口异常：" + userFavoriteEsHouseDoQuery.getHouseId() + "={}", e.getStackTrace());
        }
        if (result > 0) {
            return 1;
        }
        return 0;


    }

    /**
     * @param userFavoriteRentDoQuery
     * @return 添加出租收藏
     */
    @Override
    @Transactional
    public Integer addRentFavorite(UserFavoriteRentDoQuery userFavoriteRentDoQuery) {

        //判断重复收藏
        Integer result = 0;
        Integer i = userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(userFavoriteRentDoQuery.getHouseId(), userFavoriteRentDoQuery.getUserId());
        if (null != i && i > 0) {
            return -1;
        }
        try {
            if ("1".equals(userFavoriteRentDoQuery.getRentType())) {
                userFavoriteRentDoQuery.setRentTypeName("整租");
            }
            if ("2".equals(userFavoriteRentDoQuery.getRentType())) {
                userFavoriteRentDoQuery.setRentTypeName("合租");
            }
            userFavoriteRentDoQuery.setCreateTime(new Date());
            result = userFavoriteRentMapper.insertRentHouseSelective(userFavoriteRentDoQuery);

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
            favoriteHouseVo.setTags(favoriteHouseDo.getTags());

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
                    favoriteHouseVo.setPrice(favoriteHouseDo.getAveragePrice().intValue());
                    favoriteHouseVo.setHouseMinArea(favoriteHouseDo.getHouseMinArea());
                    favoriteHouseVo.setHouseMaxArea(favoriteHouseDo.getHouseMaxArea());
                    favoriteHouseVo.setTotalPrice(favoriteHouseDo.getTotalPrice().intValue());
                    favoriteHouseVo.setRoomType(favoriteHouseDo.getRoomType());
                    favoriteHouseVo.setIsActive(favoriteHouseDo.getIsActive());
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
                    favoriteHouseVo.setPrice(favoriteHouseDo.getHouseTotalPrices().intValue());
                    favoriteHouseVo.setRoom(favoriteHouseDo.getRoom());
                    favoriteHouseVo.setHall(favoriteHouseDo.getHall());
                    favoriteHouseVo.setForward(favoriteHouseDo.getForward());
                    favoriteHouseVo.setBuildArea(favoriteHouseDo.getBuildArea());
                    favoriteHouseVo.setCompanyIcon(favoriteHouseDo.getCompanyIcon());
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
                    favoriteHouseVo.setPrice(favoriteHouseDo.getAveragePrice().intValue());
                    favoriteHouseVo.setBuildYears(favoriteHouseDo.getBuildYears());
                    favoriteHouseVo.setBuildingStructure(favoriteHouseDo.getBuildingStructure());
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
                    favoriteHouseVo.setPrice(favoriteHouseDo.getRentPrice().intValue());
                    favoriteHouseVo.setRentTypeName(favoriteHouseDo.getRentTypeName());
                    favoriteHouseVo.setRoom(favoriteHouseDo.getRoom());
                    favoriteHouseVo.setHall(favoriteHouseDo.getHall());
                    favoriteHouseVo.setHouseArea(favoriteHouseDo.getHouseArea());
                    favoriteHouseVo.setForward(favoriteHouseDo.getForward());
                    favoriteHouseVo.setCompanyIcon(favoriteHouseDo.getCompanyIcon());
                    break;
            }

            favoriteHouseVoList.add(favoriteHouseVo);
        }

        favoriteHouseDomain.setList(favoriteHouseVoList);
        favoriteHouseDomain.setTotalCount((int) page.getTotal());
        return favoriteHouseDomain;

    }

    /**
     * 取消收藏房源
     *
     * @param cancelFavoriteHouseDto
     * @return
     */
    @Override
    @Transactional
    public Integer cancelFavoriteHouse(CancelFavoriteHouseDto cancelFavoriteHouseDto) {

        Integer flag = 0;
        Integer type = cancelFavoriteHouseDto.getType(); // 1 新房 2 二手房 3 小区 4 租房
        Integer userId = cancelFavoriteHouseDto.getUserId();
        String id = cancelFavoriteHouseDto.getId();
        switch (type) {
            case 1:// 新房

                NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
                newHouseIsFavoriteDoQuery.setUserId(userId);
                newHouseIsFavoriteDoQuery.setBuildingId(Integer.valueOf(id));

                if (this.userFavoriteNewHouseMapper.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery) > 0) {
                    UserFavoriteNewHouse userFavoriteNewHouse = new UserFavoriteNewHouse();
                    userFavoriteNewHouse.setUserId(userId);
                    userFavoriteNewHouse.setBuildingId(Integer.valueOf(id));
                    userFavoriteNewHouse.setIsDel((short) 1);
                    flag = this.userFavoriteNewHouseMapper.cancelNewHouseFavoriteByUserIdAndHouseId(userFavoriteNewHouse);
                } else {
                    flag = 2;
                }
                break;
            case 2:// 二手房

                if (this.userFavoriteEsHouseMapper.isEsfFavoriteByHouseIdAndUserId(id, userId) > 0) {
                    DeleteEsfFavoriteDo deleteEsfFavoriteDo = new DeleteEsfFavoriteDo();
                    deleteEsfFavoriteDo.setUserId(userId);
                    deleteEsfFavoriteDo.setHouseId(id);
                    flag = this.userFavoriteEsHouseMapper.updateEsfFavoriteByEsfIdAndUserId(deleteEsfFavoriteDo);
                } else {
                    flag = 2;
                }
                break;
            case 3:// 小区

                PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                plotIsFavoriteDoQuery.setUserId(userId);
                plotIsFavoriteDoQuery.setBuildingId(Integer.valueOf(id));

                if (this.userFavoriteVillageMapper.selectPlotIsFavorite(plotIsFavoriteDoQuery) > 0) {

                    plotIsFavoriteDoQuery.setUserId(userId);
                    plotIsFavoriteDoQuery.setBuildingId(Integer.valueOf(id));
                    flag = this.userFavoriteVillageMapper.cancelVillageByVillageIdAndUserId(plotIsFavoriteDoQuery);
                } else {
                    flag = 2;
                }
                break;
            case 4:// 租房

                if (this.userFavoriteRentMapper.isRentFavoriteByRentIdAndUserId(id, userId) > 0) {

                    DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery = new DeleteRentFavoriteDoQuery();
                    deleteRentFavoriteDoQuery.setUserId(userId);
                    deleteRentFavoriteDoQuery.setHouseId(id);
                    flag = this.userFavoriteRentMapper.updateRentFavoriteByHouseIdAndUserId(deleteRentFavoriteDoQuery);
                } else {
                    flag = 2;
                }
                break;
        }
        return flag;
    }

    @Override
    @Transactional
    public Integer addFavoriteHouse(CancelFavoriteHouseDto cancelFavoriteHouseDto) {
        Integer flag = 0;
        Integer type = cancelFavoriteHouseDto.getType(); // 1 新房 2 二手房 3 小区 4 租房
        Integer userId = cancelFavoriteHouseDto.getUserId();
        String id = cancelFavoriteHouseDto.getId();
        switch (type) {
            case 1:// 新房
                NewHouseDetailDo newHouseDetailDo = newHouseService.getNewHouseBuildByNewCode(Integer.valueOf(id), CityUtils.getCity());
                NewHouseAddFavoriteDoQuery newHouseAddFavoriteDo = new NewHouseAddFavoriteDoQuery();
                newHouseAddFavoriteDo.setUserId(userId);
                newHouseAddFavoriteDo.setBuildingId(newHouseDetailDo.getBuildingNameId());
                newHouseAddFavoriteDo.setAveragePrice(BigDecimal.valueOf(newHouseDetailDo.getAveragePrice()));
                newHouseAddFavoriteDo.setHouseMinArea(String.valueOf(newHouseDetailDo.getHouseMinArea()));
                newHouseAddFavoriteDo.setHouseMaxArea(String.valueOf(newHouseDetailDo.getHouseMaxArea()));
                newHouseAddFavoriteDo.setBuildingName(newHouseDetailDo.getBuildingName());
                newHouseAddFavoriteDo.setBuildingTitleImg(newHouseDetailDo.getBuildingTitleImg());
                newHouseAddFavoriteDo.setTotalPrice(BigDecimal.valueOf(newHouseDetailDo.getTotalPrice()));
                newHouseAddFavoriteDo.setCityId(newHouseDetailDo.getCityId());
                newHouseAddFavoriteDo.setRoomType(newHouseDetailDo.getRedecorateTypeId());
                flag = userFavoriteNewHouseMapper.addNewHouseFavorite(newHouseAddFavoriteDo);
                break;
            case 2:// 二手房
                break;
            case 3:// 小区
                break;
            case 4:// 租房
                break;
        }
        return flag;
    }

    @Override
    public FavoriteHouseCountDto queryFavoriteHouseCount(Integer userId) {
        return favoriteRestMapper.queryFavoriteHouseCount(userId);
    }

    @Override
    public FavoriteIdDo queryFavoriteId(Integer type) {
        return favoriteRestMapper.queryFavoriteId(type);
    }
}
