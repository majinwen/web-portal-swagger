package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuidePopularListResponse {

    private List<ReportNewGuidePopularResponse> data;

    private Integer totalCount;
}
