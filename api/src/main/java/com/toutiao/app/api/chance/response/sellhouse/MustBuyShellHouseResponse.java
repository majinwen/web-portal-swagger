package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDo;
import lombok.Data;

import java.util.List;

@Data
public class MustBuyShellHouseResponse {
    /**
     * 降价房数据
     */
    private List<MustBuyShellHouseDo> data;

    /**
     * 是否被订阅
     */
    private Integer subscribeId;

    private long totalCount;
}
