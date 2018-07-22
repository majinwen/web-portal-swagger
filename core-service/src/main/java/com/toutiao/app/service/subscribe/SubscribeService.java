package com.toutiao.app.service.subscribe;

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;

import java.util.List;

public interface SubscribeService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserSubscribe record);

    UserSubscribe selectByPrimaryKey(Integer id);

    UserSubscribe selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo, Integer userId);

    int updateByPrimaryKeySelective(UserSubscribe record);

    /**
     * 获取我的订阅页数据
     * @param userId
     * @return
     */
    List<UserSubscribeListDo> getMySubscribeInfo(Integer userId);

    /**
     * 获取首页订阅数据
     * @param userId
     * @return
     */
    List<UserSubscribeListDo> getIndexSubscribeInfo(Integer userId);
}
