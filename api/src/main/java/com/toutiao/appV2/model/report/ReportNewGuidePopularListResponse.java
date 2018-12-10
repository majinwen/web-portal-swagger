package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportNewGuidePopular;
import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuidePopularListResponse {

    private List<ReportNewGuidePopular> data;

    private Integer totalCount;
}
