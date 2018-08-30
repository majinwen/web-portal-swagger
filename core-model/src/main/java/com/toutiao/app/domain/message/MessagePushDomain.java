package com.toutiao.app.domain.message;

import lombok.Data;

import java.util.List;

@Data
public class MessagePushDomain {
    private List<MessagePushDo> data;

    private long totalCount;

    private Integer lastMessageId;
}
