package com.toutiao.app.domain.message;

import lombok.Data;

@Data
public class HomeMessageDoQuery {
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

    /**
     * 关注房源小区二手房新上消息最后阅读时间
     */
    private long favoriteVillageEsfDate;
    /**
     * 关注房源小区租房新上消息最后阅读时间
     */
    private long favoriteVillageRentDate;
    /**
     * 关注二手房下架消息最后阅读时间
     */
    private long esfShelvesDate;
    /**
     * 关注租房下架消息最后阅读时间
     */
    private long rentShelvesDate;
    /**
     * 关注租房价格变动消息最后阅读时间
     */
    private long rentChangePriceDate;

}
