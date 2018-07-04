package com.toutiao.app.service.subscribe.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import com.toutiao.app.service.sellhouse.CutPriceSellHouseRestService;
import com.toutiao.app.service.sellhouse.LowerPriceSellHouseRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.subscribe.SubscribeService;
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
    private CutPriceSellHouseRestService cutPriceSellHouseRestService;
    @Autowired
    private LowerPriceSellHouseRestService lowerPriceSellHouseRestService;

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

    @Override
    public UserSubscribe selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo, Integer userId) {
        return userSubscribeMapper.selectByUserSubscribeMap(userSubscribeDetailDo, userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserSubscribe record) {
        return userSubscribeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<UserSubscribeListDo> getMySubscribeInfo(Integer userId) {
        return selectByUserId(userId, Boolean.FALSE);
    }

    @Override
    public List<UserSubscribeListDo> getIndexSubscribeInfo(Integer userId) {
        return selectByUserId(userId, Boolean.TRUE);
    }

    /**
     * @param userId           用户ID
     * @param isGetHouseDetail 是否获取符合订阅条件的房源详情
     * @return
     */
    public List<UserSubscribeListDo> selectByUserId(Integer userId, Boolean isGetHouseDetail) {
        List<UserSubscribeListDo> userSubscribeListDoList = new ArrayList<>();
        List<UserSubscribe> userSubscribeList = userSubscribeMapper.selectByUserId(userId);
        for (UserSubscribe userSubscribe : userSubscribeList) {
            UserSubscribeListDo userSubscribeListDo = new UserSubscribeListDo();
            BeanUtils.copyProperties(userSubscribe, userSubscribeListDo);
            UserSubscribeDetailDo userSubscribeDetailDo = JSONObject.parseObject(userSubscribe.getUserSubscribeMap(), UserSubscribeDetailDo.class);
            userSubscribeListDo.setUserSubscribeDetail(userSubscribeDetailDo);
            //填充新增数量
            userSubscribeListDo.setNewCount(getNewCountBySubscribe(userSubscribeDetailDo));
            if (isGetHouseDetail) {
                //填充房源列表数据
                userSubscribeListDo.setHouseList(getHouseListBySubscribe(userSubscribeDetailDo));
            }
            userSubscribeListDoList.add(userSubscribeListDo);
        }
        return userSubscribeListDoList;
    }

    private Long getNewCountBySubscribe(UserSubscribeDetailDo userSubscribeDetailDo) {
        Integer pageIndex = 1;
        Integer pageSize = 1;
        if (userSubscribeDetailDo.getTopicType() == 3) {
            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
            BeanUtils.copyProperties(userSubscribeDetailDo, sellHouseBeSureToSnatchDoQuery);
            sellHouseBeSureToSnatchDoQuery.setIsNew(1);
            sellHouseBeSureToSnatchDoQuery.setPageSize(pageSize);
            sellHouseBeSureToSnatchDoQuery.setPageNum(pageIndex);
            SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery);
            return sellHouseBeSureToSnatchDos.getTotalCount();
        } else if (userSubscribeDetailDo.getTopicType() == 1) {
//            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
//            BeanUtils.copyProperties(userSubscribeDetailDo, sellHouseBeSureToSnatchDoQuery);
//            sellHouseBeSureToSnatchDoQuery.setIsNew(1);
//            SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery);
//            return sellHouseBeSureToSnatchDos.getTotalCount();
        } else if (userSubscribeDetailDo.getTopicType() == 2) {
//            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
//            BeanUtils.copyProperties(userSubscribeDetailDo, sellHouseBeSureToSnatchDoQuery);
//            sellHouseBeSureToSnatchDoQuery.setIsNew(1);
//            SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery);
//            return sellHouseBeSureToSnatchDos.getTotalCount();
        }
        return 0L;
    }

    private Object getHouseListBySubscribe(UserSubscribeDetailDo userSubscribeDetailDo) {
        Integer pageIndex = 1;
        Integer pageSize = 5;
        if (userSubscribeDetailDo.getTopicType() == 3) {
            SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
            BeanUtils.copyProperties(userSubscribeDetailDo, sellHouseBeSureToSnatchDoQuery);
            sellHouseBeSureToSnatchDoQuery.setPageSize(pageSize);
            sellHouseBeSureToSnatchDoQuery.setPageNum(pageIndex);
            return sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery);
        } else if (userSubscribeDetailDo.getTopicType() == 1) {
            CutPriceShellHouseDoQuery cutPriceShellHouseDoQuery = new CutPriceShellHouseDoQuery();
            BeanUtils.copyProperties(userSubscribeDetailDo, cutPriceShellHouseDoQuery);
            cutPriceShellHouseDoQuery.setPageSize(pageSize);
            cutPriceShellHouseDoQuery.setPageNum(pageIndex);
            return cutPriceSellHouseRestService.getCutPriceHouse(cutPriceShellHouseDoQuery);
        } else if (userSubscribeDetailDo.getTopicType() == 2) {
            LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery = new LowerPriceShellHouseDoQuery();
            BeanUtils.copyProperties(userSubscribeDetailDo, lowerPriceShellHouseDoQuery);
            lowerPriceShellHouseDoQuery.setPageSize(pageSize);
            lowerPriceShellHouseDoQuery.setPageNum(pageIndex);
            return lowerPriceSellHouseRestService.getLowerPriceHouse(lowerPriceShellHouseDoQuery);
        }
        return null;
    }
}
