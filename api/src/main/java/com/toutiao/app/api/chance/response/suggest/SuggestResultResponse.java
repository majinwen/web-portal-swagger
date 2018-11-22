package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResultResponse {
    private List<SuggestListResponse> suggestResultList;
}
