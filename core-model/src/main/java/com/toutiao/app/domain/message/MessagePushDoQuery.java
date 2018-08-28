package com.toutiao.app.domain.message;

import lombok.Data;

@Data
public class MessagePushDoQuery {
    /**
     * 内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动)
     */
    private Integer contentType;

    /**
     * 最后一条消息
     */
    private Integer lastMessageId;
}
