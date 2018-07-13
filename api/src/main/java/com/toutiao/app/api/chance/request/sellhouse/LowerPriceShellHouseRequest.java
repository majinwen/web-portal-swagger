package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class LowerPriceShellHouseRequest extends BaseQueryRequest {
    /**
     * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-面积升, 4-面积降)
     */
    private Integer sort = 0;
}
