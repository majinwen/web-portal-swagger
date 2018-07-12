package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class CutPriceShellHouseRequest extends BaseQueryRequest {
    /**
     * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-涨幅升, 4-涨幅降)
     */
    private Integer sort = 0;
}
