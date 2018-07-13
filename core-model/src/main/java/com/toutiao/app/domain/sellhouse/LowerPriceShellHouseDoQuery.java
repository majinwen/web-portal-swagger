package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class LowerPriceShellHouseDoQuery extends QueryDo {
    /**
     * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-面积升, 4-面积降)
     */
    private Integer sort;

    /**
     * 新导入房源
     */
    private Integer isNew;
}
