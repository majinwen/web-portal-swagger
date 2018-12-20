package com.toutiao.app.domain.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestListDo {
    /**
     * 区域商圈
     */
    private SearchScopeDo searchScope;
    /**
     * 楼盘/房源
     */
    private List<SearchEnginesDo> searchEnginesList;
    /**
     * 楼盘/房源类型(0:新房,1:小区,2:二手房,3:普租,4:公寓)
     */
    private Integer houseType;

}
