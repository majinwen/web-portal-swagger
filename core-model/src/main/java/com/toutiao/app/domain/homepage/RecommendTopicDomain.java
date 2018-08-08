package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   13:08
 * Theme:
 */
@Data
public class RecommendTopicDomain {


    private Map<String,Map<String,RecommendTopicDo>> data;
}
