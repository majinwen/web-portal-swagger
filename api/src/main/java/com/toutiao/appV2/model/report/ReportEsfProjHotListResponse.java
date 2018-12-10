package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportEsfProjHot;
import lombok.Data;

import java.util.List;

@Data
public class ReportEsfProjHotListResponse {

    private List<ReportEsfProjHot> data;

    private Integer totalCount;
}
