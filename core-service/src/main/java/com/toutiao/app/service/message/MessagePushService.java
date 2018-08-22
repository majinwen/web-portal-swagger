package com.toutiao.app.service.message;

import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.web.dao.entity.message.MessagePush;

import java.util.List;

public interface MessagePushService {
    /**
     * 获取消息
     *
     * @param messagePushQuery
     */
    List<MessagePush> getMessage(MessagePushDoQuery messagePushQuery);


}
