package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class MustBuyShellHouseDoQuery extends QueryDo {
    /**
     * 区域Ids
     */
    private Integer[] districtIds;

    /**
     * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-降价房-涨幅升, 4-降价房-涨幅降, 3-捡漏房-面积升, 4-捡漏房-面积降)
     */
    private Integer sort;

    /**
     * 新导入房源
     */
    private Integer isNew;
}
