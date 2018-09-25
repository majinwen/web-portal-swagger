package com.toutiao.app.service.suggest;

import com.toutiao.app.domain.suggest.SuggestDo;

public interface SuggestService {
    /**
     * 搜索联想词提示
     * @param keyword
     * @param property
     * @return
     */
    SuggestDo suggest(String keyword, String property, String city);
}
