package com.toutiao.web.api.chance.request.message;

import lombok.Data;

@Data
public class HomePageMessageRequest {
    /**
     * 符合找房条件的房源上新消息最后阅读时间
     */
    private long conditionHouseDate;

    /**
     * 关注小区房源上新消息最后阅读时间
     */
    private long favoritePlotDate;

    /**
     * 关注房源价格变动消息最后阅读时间
     */
    private long favoriteHouseDate;

    /**
     * 订阅的主题有更新消息最后阅读时间
     */
    private long subscribeThemeDate;
}
