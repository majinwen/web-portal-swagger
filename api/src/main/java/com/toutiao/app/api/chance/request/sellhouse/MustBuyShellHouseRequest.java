package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class MustBuyShellHouseRequest extends BaseQueryRequest {
    /**
     * 区域Ids
     */
    private Integer[] districtIds;

    /**
     * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-降价房-涨幅升, 4-降价房-涨幅降, 3-捡漏房-面积升, 4-捡漏房-面积降)
     */
    private Integer sort = 0;
}
