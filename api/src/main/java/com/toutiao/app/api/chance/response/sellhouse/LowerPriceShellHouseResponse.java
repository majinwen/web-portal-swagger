package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDo;
import lombok.Data;

import java.util.List;

@Data
public class LowerPriceShellHouseResponse {
    /**
     * 捡漏房数据
     */
    private List<LowerPriceShellHouseDo> data;

    /**
     * 订阅Id(-1代表没有订阅)
     */
    private Integer subscribeId;

    private long totalCount;
}
