package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class MustBuyShellHouseDomain {
    /**
     * 不买亏二手房数据
     */
    private List<MustBuyShellHouseDo> data;

    /**
     * 订阅Id(-1代表未订阅)
     */
    private Integer subscribeId;

    private long totalCount;
}
