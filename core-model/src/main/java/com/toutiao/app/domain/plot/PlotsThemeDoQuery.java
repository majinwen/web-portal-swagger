package com.toutiao.app.domain.plot;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

/**
 * 小区专题
 */
@Data
public class PlotsThemeDoQuery extends QueryDo {
    /**
     * 最近的公园
     */
    private String nearestPark;

    /**
     * 标签Id(2-首次置业，3-改善生活，4-豪宅，5-别墅，6-近公园)
     */
    private Integer recommendBuildTagsId;
}
