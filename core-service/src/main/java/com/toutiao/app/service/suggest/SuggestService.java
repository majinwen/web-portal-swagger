package com.toutiao.app.service.suggest;

import com.toutiao.app.domain.suggest.SuggestDo;
import com.toutiao.app.domain.suggest.SuggestResultDo;

public interface SuggestService {
    /**
     * 搜索联想词提示
     * @param keyword
     * @param property
     * @return
     */
    SuggestDo suggest(String keyword, String property, String city);

    /**
     * 搜索联想词提示v2
     * @param keyword
     * @param property
     * @return
     */
    SuggestResultDo suggest_v2(String keyword, String property, String city);
}
