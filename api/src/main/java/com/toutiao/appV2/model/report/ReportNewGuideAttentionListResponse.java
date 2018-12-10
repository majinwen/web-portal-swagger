package com.toutiao.appV2.model.report;

import com.toutiao.app.dao.report.ReportNewGuideAttention;
import lombok.Data;

import java.util.List;

@Data
public class ReportNewGuideAttentionListResponse {

    private List<ReportNewGuideAttention> data;

    private Integer totalCount;
}
