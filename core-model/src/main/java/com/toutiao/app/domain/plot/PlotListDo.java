package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotListDo{

    private List<PlotDetailsFewDo> data;

    private Integer totalNum;

}
