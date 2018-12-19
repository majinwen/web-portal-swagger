package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class RecommendEsf5DoQuery extends QueryDo {
    /**
     * 区域
     */
    private Integer[] districtIds;

//    /**
//     * 排序字段
//     */
//    private String sortFile;

    /**
     * 排序
     */
    private String sort;
}
