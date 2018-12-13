package com.toutiao.appV2.model.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportAreaHotListResponse {

    private List<ReportAreaHotResponse> data;

    private Integer totalCount;
}
