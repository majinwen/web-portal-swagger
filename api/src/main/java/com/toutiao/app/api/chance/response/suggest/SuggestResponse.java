package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;

import java.util.List;

@Data
public class SuggestResponse {
    /**
     * 区域商圈
     */
//    private List<SearchScopeResponse> searchScopeList;
    /**
     * 关键词
     */
    private List<SearchEnginesResponse> searchEnginesList;

    /**
     * 小区个数
     */
    private Integer plotNum;
    /**
     * 二手房个数
     */
    private Integer esfNum;
    /**
     * 新房个数
     */
    private Integer newHouseNum;
    /**
     * 普租个数
     */
    private Integer rentNum;
    /**
     * 公寓个数
     */
    private Integer apartmentNum;

}
