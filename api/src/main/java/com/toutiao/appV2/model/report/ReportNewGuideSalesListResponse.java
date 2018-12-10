package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportNewGuideSales;
import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideSalesListResponse {

    private List<ReportNewGuideSales> data;

    private Integer totalCount;
}
