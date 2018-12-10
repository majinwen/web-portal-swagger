package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportNewPreferential;
import lombok.Data;

import java.util.List;

@Data
public class ReportNewPreferentialListResponse {

    private List<ReportNewPreferential> data;

    private Integer totalCount;
}
