package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportAreaHot;
import lombok.Data;

import java.util.List;

@Data
public class ReportAreaHotListResponse {

    private List<ReportAreaHot> data;

    private Integer totalCount;
}
