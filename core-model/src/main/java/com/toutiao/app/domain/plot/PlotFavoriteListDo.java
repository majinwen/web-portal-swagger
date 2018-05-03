package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotFavoriteListDo {

    private List<UserFavoritePlotDo> data;

    private Long totalNum;

}
