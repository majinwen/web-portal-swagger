package com.toutiao.app.service.report;

import com.toutiao.app.dao.report.*;
import com.toutiao.app.dao.report.ReportCity;

import java.util.List;

public interface ReportCityService {

    /**
     * 根据城市ID查询最新一条数据
     * @param cityId
     * @return
     */
    ReportCity selectReportCityByCityId(Integer cityId);

    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewGuideAttention> selectReportNewGuideAttentionList(Integer cityId);
    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewGuideHot> selectReportNewGuideHotList(Integer cityId);
    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewGuidePopular> selectReportNewGuidePopularList(Integer cityId);
    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewGuideSales> selectReportNewGuideSalesList(Integer cityId);
    /**
     * 根据城市ID查询全部数据
     *
     * @param cityId
     * @return
     */
    List<ReportNewPreferential> selectReportNewPreferentialList(Integer cityId);

}
