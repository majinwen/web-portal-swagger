package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotDetailsFewDomain {


    private List<PlotDetailsFewDo> nearbyPlots;

    private Long totals;
}
