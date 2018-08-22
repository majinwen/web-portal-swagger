package com.toutiao.app.api.chance.response.hotplot;

import com.toutiao.app.domain.hotplot.SearchHotProjDo;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:38
 * Theme:
 */
@Data
public class HotPlotListResponse {

    private List<SearchHotProjDo> data;
}
