package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import lombok.Data;

import java.util.List;

@Data
public class PlotListResponse {

    private List<PlotDetailsFewDo> plotList;

    private Integer totalNum;
}
