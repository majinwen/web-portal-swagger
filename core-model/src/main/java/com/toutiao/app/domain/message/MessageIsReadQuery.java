package com.toutiao.app.domain.message;

import lombok.Data;

@Data
public class MessageIsReadQuery {

    /**
     * 内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)
     */
    private Integer contentType;

    /**
     * 时间
     */
    private Long time;
}
