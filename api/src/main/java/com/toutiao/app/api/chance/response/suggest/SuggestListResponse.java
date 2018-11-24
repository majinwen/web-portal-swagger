package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestListResponse {
    /**
     * 区域商圈
     */
    private SearchScopeResponse searchScope;
    /**
     * 楼盘/房源
     */
    private List<SearchEnginesResponse> searchEnginesList;
    /**
     * 楼盘/房源类型(0:新房,1:小区,2:二手房,3:普租,4:公寓)
     */
    private Integer houseType;

}
