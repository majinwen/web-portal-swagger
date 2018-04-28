package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotFavoriteListDo {

    private List<PlotDetailsFewDo> plotFavoriteList;

    private Long totalCount;
}
