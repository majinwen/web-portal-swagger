package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportNewGuideHot;
import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideHotListResponse {

    private List<ReportNewGuideHot> data;

    private Integer totalCount;
}
