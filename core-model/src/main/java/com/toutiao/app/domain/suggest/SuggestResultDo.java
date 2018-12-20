package com.toutiao.app.domain.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SuggestResultDo {
    private List<SuggestListDo> suggestResultList;
}
