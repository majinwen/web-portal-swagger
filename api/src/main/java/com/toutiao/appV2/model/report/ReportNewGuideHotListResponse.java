package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideHotListResponse {

    private List<ReportNewGuideHotResponse> data;

    private Integer totalCount;
}
