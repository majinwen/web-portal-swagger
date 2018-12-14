package com.toutiao.app.service.report.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.web.dao.mapper.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${qiniu.img_domain}")
    private String qinniuImg;

    /**
     * 根据城市ID查询最新一条数据
     *
     * @param cityId
     * @return
     */
    public ReportCity selectReportCityByCityId(Integer cityId) {
        ReportCity reportCity = reportCityMapper.selectOne(cityId);
        reportCity.setNewGuideAttention(getImgPath(reportCity.getNewGuideAttention()));
        reportCity.setNewGuideHot(getImgPath(reportCity.getNewGuideHot()));
        reportCity.setNewGuideSales(getImgPath(reportCity.getNewGuideSales()));
        reportCity.setNewGuidePopular(getImgPath(reportCity.getNewGuidePopular()));
        return reportCity;
    }

    /**
     * 处理数据报告中的图片访问路径
     * @param jsonStr
     * @return
     */
    private String getImgPath(String jsonStr) {
        String s1 = jsonStr.replaceAll(" +","");
        String pattern = "(\"img_path\":\")([^\"]+)(\")";
        String s2 = s1.replaceAll(pattern,"$1"+qinniuImg+"/$2-dongfangdi400x300$3");
        return s2;
    }

    @Override
    public List<ReportNewGuideAttention> selectReportNewGuideAttentionList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportNewGuideAttentionMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuideHot> selectReportNewGuideHotList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportNewGuideHotMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuidePopular> selectReportNewGuidePopularList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportNewGuidePopularMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewGuideSales> selectReportNewGuideSalesList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportNewGuideSalesMapper.selectAll(cityId);
    }

    @Override
    public List<ReportNewPreferential> selectReportNewPreferentialList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportNewPreferentialMapper.selectAll(cityId);
    }

    @Override
    public List<ReportEsfProjHot> selectReportEsfProjHotList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportEsfProjHotMapper.selectAll(cityId);
    }

    @Override
    public List<ReportAreaHot> selectReportAreaHotList(Integer cityId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return reportAreaHotMapper.selectAll(cityId);
    }
}
