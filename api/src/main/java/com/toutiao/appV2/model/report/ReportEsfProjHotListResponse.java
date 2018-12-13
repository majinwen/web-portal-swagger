package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportEsfProjHotListResponse {

    private List<ReportEsfProjHotResponse> data;

    private Integer totalCount;
}
