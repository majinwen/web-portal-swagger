package com.toutiao.app.domain.message;

import lombok.Data;

@Data
public class HomeMessageDo {
    /**
     * 内容
     */
//    private JSONObject content;

    /**
     * 房源
     */
//    private JSONObject houseDate;

    /**
     * 未读条数
     */
    private long unReadCount;

    /**
     * 内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)
     */
    private Integer contentType;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 加粗消息内容
     */
    private String boldMessageContent;
}
