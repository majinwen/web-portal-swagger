package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideAttentionListResponse {

    private List<ReportNewGuideAttentionResponse> data;

    private Integer totalCount;
}
