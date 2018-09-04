package com.toutiao.app.service.message;

import com.toutiao.app.domain.message.HomeMessageDo;
import com.toutiao.app.domain.message.HomeMessageDoQuery;
import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;

import java.util.List;

public interface MessagePushService {
    /**
     * 房源类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    MessagePushDomain getHouseTypeMessage(MessagePushDoQuery messagePushQuery, String userId);

    /**
     * 专题类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    MessagePushDomain getThemeTypeMessage(MessagePushDoQuery messagePushQuery, String userId);

    /**
     * 首页消息列表
     *
     * @param homeMessageDoQuery
     * @param userId
     * @return
     */
    List<HomeMessageDo> getHomeMessage(HomeMessageDoQuery homeMessageDoQuery, String userId);
}
