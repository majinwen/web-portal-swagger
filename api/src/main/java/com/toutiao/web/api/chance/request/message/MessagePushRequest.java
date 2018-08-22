package com.toutiao.web.api.chance.request.message;

import lombok.Data;

import java.util.Date;

@Data
public class MessagePushRequest {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
     */
    private Integer messageType;

    /**
     * 推送类型(0-系统消息, 1-定向推送)
     */
    private Integer pushType;

    /**
     * 内容类型(0-房产政策知识, 1-提示系统更新, 2-沉睡用户唤醒, 3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)
     */
    private Integer contentType;
}
