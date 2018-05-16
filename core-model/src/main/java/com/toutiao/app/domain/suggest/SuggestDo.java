package com.toutiao.app.domain.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestDo {
    /**
     * 区域商圈
     */
//    private List<SearchScopeDo> searchScopeList;
    /**
     * 关键词
     */
    private List<SearchEnginesDo> searchEnginesList;
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
