package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideSalesListResponse {

    private List<ReportNewGuideSalesResponse> data;

    private Integer totalCount;
}
