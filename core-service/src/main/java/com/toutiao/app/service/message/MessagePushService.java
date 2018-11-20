package com.toutiao.app.service.message;

import com.toutiao.app.domain.message.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MessagePushService {
    /**
     * 房源类消息列表(旧版本)
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
     * @param request
     * @return
     */
    MessagePushDomain getThemeTypeMessage(MessagePushDoQuery messagePushQuery, String userId, HttpServletRequest request);

    /**
     * 首页消息列表
     *
     * @param homeMessageDoQuery
     * @param userId
     * @return
     */
    List<HomeMessageDo> getHomeMessage(HomeMessageDoQuery homeMessageDoQuery, String userId);

    /**
     * 获取消息列表(新版本)
     *
     * @param messagePushQuery
     * @param userId
     * @param request
     * @return
     */
    MessagePushDomain getHouseTypeMessageNew(MessagePushDoQuery messagePushQuery, String userId, HttpServletRequest request);

    /**
     * 首页消息列表(新)
     *
     * @param homeMessageDoQuery
     * @param userId
     * @return
     */
    List<HomeMessageDo> getHomeMessageNew(HomeMessageDoQuery homeMessageDoQuery, String userId);

    /**
     * 修改消息已读
     *
     * @param contentType
     * @param userId
     */
//    int updateIsRead(Integer contentType, String userId);
}
