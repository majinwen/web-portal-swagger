package com.toutiao.appV2.model.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResultResponse {
    private List<SearchEnginesResponse> searchEnginesList;
    private Integer totalCount;
}
