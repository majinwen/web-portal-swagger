package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class PlotListResponse {
    @ChangeName("data")
    private List<PlotDetailsFewDo> data;

    private Integer totalNum;
}
