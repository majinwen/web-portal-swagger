package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class CutPriceShellHouseDomain {
    /**
     * 降价房数据
     */
    private List<CutPriceShellHouseDo> data;

    /**
     * 订阅Id(-1代表未订阅)
     */
    private Integer subscribeId;

    private long totalCount;
}
