package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotListResponse {

    private List<PlotDetailsFewResponse> data;

    private Integer totalNum;
}
