package com.toutiao.app.service.subscribe;

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;

public interface SubscribeService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserSubscribe record);

    UserSubscribe selectByPrimaryKey(Integer id);

    UserSubscribe selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo, Integer userId);

    int updateByPrimaryKeySelective(UserSubscribe record);


}
