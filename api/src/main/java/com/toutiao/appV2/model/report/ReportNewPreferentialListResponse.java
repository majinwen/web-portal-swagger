package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportNewPreferentialListResponse {

    private List<ReportNewPreferentialResponse> data;

    private Integer totalCount;
}
