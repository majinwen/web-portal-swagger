package com.toutiao.app.api.chance.response.homepage;


import com.toutiao.app.domain.homepage.RecommendTopicDo;
import lombok.Data;

import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:33
 * Theme:
 */
@Data
public class RecommendTopicResponse {


    private Map<String,Map<String,RecommendTopicDo>> data;
}
