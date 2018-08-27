package com.toutiao.app.service.message;

import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;

public interface MessagePushService {
    /**
     * 获取消息
     * @param messagePushQuery
     *
     */
    MessagePushDomain getMessage(MessagePushDoQuery messagePushQuery);


}
