package com.toutiao.app.service.homepage;

import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:40
 * Theme:
 */
public interface RecommendRestService {

    /**
     * 首页推荐专题
     * @return
     */
//    RecommendTopicDomain getRecommendTopic(RecommendTopicDoQuery recommendTopicDoQuery);

    RecommendTopicDomain getRecommendTopic(RecommendTopicDoQuery recommendTopicDoQuery, String city);
}

