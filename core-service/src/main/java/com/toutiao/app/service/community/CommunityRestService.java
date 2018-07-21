package com.toutiao.app.service.community;

import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-21
 * Time:   11:51
 * Theme:
 */
public interface CommunityRestService {


    /**
     * 标签数量
     * @return
     */
    Map<Integer,Map<String,Integer>> getCountByBuildTags();


}
