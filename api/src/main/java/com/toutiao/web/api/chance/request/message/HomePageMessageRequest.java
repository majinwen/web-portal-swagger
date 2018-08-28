package com.toutiao.web.api.chance.request.message;

import lombok.Data;

import java.util.Date;

@Data
public class HomePageMessageRequest {
    private String userId;
    /**
     * 符合找房条件的房源上新消息最后阅读时间
     */
    private Date conditionHouseDate;

    /**
     * 关注小区房源上新消息最后阅读时间
     */
    private Date favoritePlotDate;

    /**
     * 关注房源价格变动消息最后阅读时间
     */
    private Date favoriteHouseDate;

    /**
     * 订阅的主题有更新消息最后阅读时间
     */
    private Date subscribeThemeDate;

}
