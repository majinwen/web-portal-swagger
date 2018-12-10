package com.toutiao.app.service.report.impl;

import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.web.dao.mapper.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportCityServiceImpl implements ReportCityService {

    @Autowired
    private ReportCityMapper reportCityMapper;
    @Autowired
    private ReportNewGuideAttentionMapper reportNewGuideAttentionMapper;
    @Autowired
    private ReportNewGuideHotMapper reportNewGuideHotMapper;
    @Autowired
    private ReportNewGuidePopularMapper reportNewGuidePopularMapper;
    @Autowired
    private ReportNewGuideSalesMapper reportNewGuideSalesMapper;
    @Autowired
    private ReportNewPreferentialMapper reportNewPreferentialMapper;
    @Autowired
    private ReportEsfProjHotMapper reportEsfProjHotMapper;
    @Autowired
    private ReportAreaHotMapper reportAreaHotMapper;

    /**
     * 根据城市ID查询最新一条数据
     *
     * @param cityId
     * @return
     */
    public ReportCity selectReportCityByCityId(Integer cityId) {
        return reportCityMapper.selectOne(cityId);
    }

    @Override
    public List<ReportNewGuideAttention> selectReportNewGuideAttentionList(Integer cityId) {
        return reportNewGuideAttentionMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuideHot> selectReportNewGuideHotList(Integer cityId) {
        return reportNewGuideHotMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuidePopular> selectReportNewGuidePopularList(Integer cityId) {
        return reportNewGuidePopularMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuideSales> selectReportNewGuideSalesList(Integer cityId) {
        return reportNewGuideSalesMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewPreferential> selectReportNewPreferentialList(Integer cityId) {
        return reportNewPreferentialMapper.selectAll(cityId);
    }

    @Override
    public List<ReportEsfProjHot> selectReportEsfProjHotList(Integer cityId) {
        return reportEsfProjHotMapper.selectAll(cityId);
    }

    @Override
    public List<ReportAreaHot> selectReportAreaHotList(Integer cityId) {
        return reportAreaHotMapper.selectAll(cityId);
    }
}
