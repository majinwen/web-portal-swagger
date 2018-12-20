package com.toutiao.app.service.advertisement;

import com.toutiao.app.domain.plot.PlotTop50Do;

import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   18:15
 * Theme:
 */

public interface AdCommunityRestService {


    List<PlotTop50Do> getExcellentCommunityByIds(Integer[] communityIds, String city);
}
