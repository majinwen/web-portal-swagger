package com.toutiao.web.dao.mapper.report;

import org.springframework.stereotype.Repository;

/**
 * Created by wk on 2018/12/25.
 */
@Repository
public interface ReportEsfGuideMapper {

    Integer queryReportEsfGuideByCityId(Integer cityId);

}
