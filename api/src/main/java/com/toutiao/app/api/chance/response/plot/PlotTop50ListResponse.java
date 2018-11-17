package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class PlotTop50ListResponse {

    /**
     * 小区附近列表
     */
    private List<PlotTop50Response> plotTop50ResponseList;

    /**
     * 查询结果总量
     */
    private Integer totalNum;

}
