package com.toutiao.app.service.subscribe.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDomain;
import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import com.toutiao.app.service.sellhouse.MustBuySellHouseRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import com.toutiao.web.dao.mapper.subscribe.UserSubscribeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    UserSubscribeMapper userSubscribeMapper;
    @Autowired
    private SellHouseService sellHouseService;
    @Autowired
    private MustBuySellHouseRestService mustBuySellHouseRestService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userSubscribeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserSubscribe record) {
        return userSubscribeMapper.insertSelective(record);
    }

    @Override
    public UserSubscribe selectByPrimaryKey(Integer id) {
        return userSubscribeMapper.selectByPrimaryKey(id);
    }

    /**
     * 转换字符串数组到Integer数组
     *
     * @param strArray
     * @return Integer[]
     */
    private static Integer[] getIntArrayFromStringArray(String[] strArray) {
        if (strArray == null) {
            return new Integer[0];
        }
        Integer[] result = new Integer[strArray.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.valueOf(strArray[i]);
        }
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(UserSubscribe record) {
        return userSubscribeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<UserSubscribeListDo> getMySubscribeInfo(Integer userId, String city) {
        return selectByUserId(userId, Boolean.FALSE, city);
    }

    @Override
    public List<UserSubscribeListDo> getIndexSubscribeInfo(Integer userId, String city) {
        return selectByUserId(userId, Boolean.TRUE, city);
    }

    @Override
    public List<UserSubscribe> getMyConditionSubscribeInfo(Integer userId) {
        return userSubscribeMapper.selectConditionSubscribeByUserId(userId);
    }

    @Override
    public UserSubscribe selectConditionSubscribeByUserSubscribeMap(UserConditionSubscribeDetailDo userConditionSubscribeDetailDo, Integer userId, String city) {
        if (userConditionSubscribeDetailDo.getBeginPrice() == null) {
            userConditionSubscribeDetailDo.setBeginPrice(0);
        }
        if (userConditionSubscribeDetailDo.getEndPrice() == null) {
            userConditionSubscribeDetailDo.setEndPrice(0);
        }
        Integer cityId = CityUtils.returnCityId(city);
        return userSubscribeMapper.selectConditionSubscribeByUserSubscribeMap(userConditionSubscribeDetailDo, userId, cityId);
    }

    /**
     * @param userId           用户ID
     * @param isGetHouseDetail 是否获取符合订阅条件的房源详情
     * @return
     */
    public List<UserSubscribeListDo> selectByUserId(Integer userId, Boolean isGetHouseDetail, String city) {
        List<UserSubscribeListDo> userSubscribeListDoList = new ArrayList<>();
        List<UserSubscribe> userSubscribeList = userSubscribeMapper.selectByUserId(userId);
        for (UserSubscribe userSubscribe : userSubscribeList) {
            UserSubscribeListDo userSubscribeListDo = new UserSubscribeListDo();
            BeanUtils.copyProperties(userSubscribe, userSubscribeListDo);
            UserSubscribeDetailDo userSubscribeDetailDo = JSONObject.parseObject(userSubscribe.getUserSubscribeMap(), UserSubscribeDetailDo.class);
            userSubscribeListDo.setUserSubscribeDetail(userSubscribeDetailDo);
            //填充新增数量
            userSubscribeListDo.setNewCount(getNewCountBySubscribe(userSubscribeDetailDo,city));
            if (isGetHouseDetail) {
                //填充房源列表数据
                userSubscribeListDo.setHouseList(getHouseListBySubscribe(userSubscribeDetailDo,city));
            }
            userSubscribeListDoList.add(userSubscribeListDo);
        }
        return userSubscribeListDoList;
    }

    @Override
    public UserSubscribe selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo, Integer userId) {
//        if (StringTool.isEmpty(userSubscribeDetailDo.getDistrictId())) {
//            userSubscribeDetailDo.setDistrictId(ZERO);
//        }
        if (userSubscribeDetailDo.getBeginPrice() == null) {
            userSubscribeDetailDo.setBeginPrice(0);
        }
        if (userSubscribeDetailDo.getEndPrice() == null) {
            userSubscribeDetailDo.setEndPrice(0);
        }
        return userSubscribeMapper.selectByUserSubscribeMap(userSubscribeDetailDo, userId);
    }

    private Long getNewCountBySubscribe(UserSubscribeDetailDo userSubscribeDetailDo, String city) {
        Integer pageIndex = 1;
        Integer pageSize = 1;
        if (userSubscribeDetailDo.getTopicType() == 3) {
            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
            sellHouseBeSureToSnatchDoQuery.setIsNew(1);
            sellHouseBeSureToSnatchDoQuery.setPageSize(pageSize);
            sellHouseBeSureToSnatchDoQuery.setPageNum(pageIndex);
            if (StringTool.isNotEmpty(userSubscribeDetailDo.getDistrictId())) {
                Integer[] intArrayFromStringArray = getIntArrayFromStringArray(userSubscribeDetailDo.getDistrictId().split(","));
                sellHouseBeSureToSnatchDoQuery.setDistrictIds(intArrayFromStringArray);
            }
            if (userSubscribeDetailDo.getBeginPrice() != null && userSubscribeDetailDo.getBeginPrice() != 0) {
                sellHouseBeSureToSnatchDoQuery.setBeginPrice(userSubscribeDetailDo.getBeginPrice());
            }
            if (userSubscribeDetailDo.getEndPrice() != null && userSubscribeDetailDo.getEndPrice() != 0) {
                sellHouseBeSureToSnatchDoQuery.setEndPrice(userSubscribeDetailDo.getEndPrice());
            }
            SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery,city);
            return sellHouseBeSureToSnatchDos.getTotalCount();
        } else if (userSubscribeDetailDo.getTopicType() == 1 || userSubscribeDetailDo.getTopicType() == 2) {
            MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
            if (StringTool.isNotEmpty(userSubscribeDetailDo.getDistrictId())) {
                Integer[] intArrayFromStringArray = getIntArrayFromStringArray(userSubscribeDetailDo.getDistrictId().split(","));
                mustBuyShellHouseDoQuery.setDistrictIds(intArrayFromStringArray);
            }
            if (userSubscribeDetailDo.getBeginPrice() != null && userSubscribeDetailDo.getBeginPrice() != 0) {
                mustBuyShellHouseDoQuery.setBeginPrice(userSubscribeDetailDo.getBeginPrice());
            }
            if (userSubscribeDetailDo.getEndPrice() != null && userSubscribeDetailDo.getEndPrice() != 0) {
                mustBuyShellHouseDoQuery.setEndPrice(userSubscribeDetailDo.getEndPrice());
            }
            mustBuyShellHouseDoQuery.setIsNew(1);
            mustBuyShellHouseDoQuery.setPageSize(pageSize);
            mustBuyShellHouseDoQuery.setPageNum(pageIndex);
            return mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, userSubscribeDetailDo.getTopicType(), city).getTotalCount();
        }
        return 0L;
    }

    private Object getHouseListBySubscribe(UserSubscribeDetailDo userSubscribeDetailDo, String city) {
        Integer pageIndex = 1;
        Integer pageSize = 5;
        if (userSubscribeDetailDo.getTopicType() == 3) {
            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
            sellHouseBeSureToSnatchDoQuery.setPageSize(pageSize);
            sellHouseBeSureToSnatchDoQuery.setPageNum(pageIndex);
            if (StringTool.isNotEmpty(userSubscribeDetailDo.getDistrictId())) {
                Integer[] intArrayFromStringArray = getIntArrayFromStringArray(userSubscribeDetailDo.getDistrictId().split(","));
                sellHouseBeSureToSnatchDoQuery.setDistrictIds(intArrayFromStringArray);
            }
            if (userSubscribeDetailDo.getBeginPrice() != null && userSubscribeDetailDo.getBeginPrice() != 0) {
                sellHouseBeSureToSnatchDoQuery.setBeginPrice(userSubscribeDetailDo.getBeginPrice());
            }
            if (userSubscribeDetailDo.getEndPrice() != null && userSubscribeDetailDo.getEndPrice() != 0) {
                sellHouseBeSureToSnatchDoQuery.setEndPrice(userSubscribeDetailDo.getEndPrice());
            }
            return sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery, city);
        } else if (userSubscribeDetailDo.getTopicType() == 1 || userSubscribeDetailDo.getTopicType() == 2) {
            MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
            if (StringTool.isNotEmpty(userSubscribeDetailDo.getDistrictId())) {
                Integer[] intArrayFromStringArray = getIntArrayFromStringArray(userSubscribeDetailDo.getDistrictId().split(","));
                mustBuyShellHouseDoQuery.setDistrictIds(intArrayFromStringArray);
            }
            if (userSubscribeDetailDo.getBeginPrice() != null && userSubscribeDetailDo.getBeginPrice() != 0) {
                mustBuyShellHouseDoQuery.setBeginPrice(userSubscribeDetailDo.getBeginPrice());
            }
            if (userSubscribeDetailDo.getEndPrice() != null && userSubscribeDetailDo.getEndPrice() != 0) {
                mustBuyShellHouseDoQuery.setEndPrice(userSubscribeDetailDo.getEndPrice());
            }
            mustBuyShellHouseDoQuery.setPageSize(pageSize);
            mustBuyShellHouseDoQuery.setPageNum(pageIndex);
            return mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, userSubscribeDetailDo.getTopicType(), city);
        }
        return null;
    }
}
