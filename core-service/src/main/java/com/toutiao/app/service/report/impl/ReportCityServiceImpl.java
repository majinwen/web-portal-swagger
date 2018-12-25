package com.toutiao.app.service.report.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.web.common.constant.syserror.CityReportErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.mapper.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
    @Autowired
    private ReportEsfGuideMapper reportEsfGuideMapper;

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
        if (reportCity == null) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        if (StringTool.isEmpty(reportCity.getEsfMonthPrice())) {
            throw new BaseException(CityReportErrorCodeEnum.NO_COTENT);//通过字段，简单判断内容为空
        }
        reportCity.setNewGuideAttention(getImgPath(reportCity.getNewGuideAttention()));
        reportCity.setNewGuideHot(getImgPath(reportCity.getNewGuideHot()));
        reportCity.setNewGuideSales(getImgPath(reportCity.getNewGuideSales()));
        reportCity.setNewGuidePopular(getImgPath(reportCity.getNewGuidePopular()));
        return reportCity;
    }

    /**
     * 处理数据报告中的图片访问路径
     *
     * @param jsonStr
     * @return
     */
    private String getImgPath(String jsonStr) {
        String s1 = jsonStr.replaceAll(" +", "");
        String pattern = "(\"img_path\":\")([^\"]+)(\")";
        String s2 = s1.replaceAll(pattern, "$1" + qinniuImg + "/$2-dongfangdi400x300$3");
        return s2;
    }

    @Override
    public List<ReportNewGuideAttention> selectReportNewGuideAttentionList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportNewGuideAttention> list = reportNewGuideAttentionMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        for (ReportNewGuideAttention report : list) {
            report.setImgPath(qinniuImg + "/" + report.getImgPath() + "-dongfangdi400x300");
        }
        return list;
    }

    @Override
    public List<ReportNewGuideHot> selectReportNewGuideHotList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportNewGuideHot> list = reportNewGuideHotMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        for (ReportNewGuideHot report : list) {
            report.setImgPath(qinniuImg + "/" + report.getImgPath() + "-dongfangdi400x300");
        }
        return list;
    }

    @Override
    public List<ReportNewGuidePopular> selectReportNewGuidePopularList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportNewGuidePopular> list = reportNewGuidePopularMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        for (ReportNewGuidePopular report : list) {
            report.setImgPath(qinniuImg + "/" + report.getImgPath() + "-dongfangdi400x300");
        }
        return list;
    }

    @Override
    public List<ReportNewGuideSales> selectReportNewGuideSalesList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportNewGuideSales> list = reportNewGuideSalesMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        for (ReportNewGuideSales report : list) {
            report.setImgPath(qinniuImg + "/" + report.getImgPath() + "-dongfangdi400x300");
        }
        return list;
    }

    @Override
    public List<ReportNewPreferential> selectReportNewPreferentialList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportNewPreferential> list = reportNewPreferentialMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        return list;
    }

    @Override
    public List<ReportEsfProjHot> selectReportEsfProjHotList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportEsfProjHot> list = reportEsfProjHotMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        return list;
    }

    @Override
    public List<ReportAreaHot> selectReportAreaHotList(Integer cityId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReportAreaHot> list = reportAreaHotMapper.selectAll(cityId);
        if (StringTool.isEmpty(list)) {
            throw new BaseException(CityReportErrorCodeEnum.NO_RECORD);//查询结果为空
        }
        return list;
    }

    /**
     * 查询举报统计
     *
     * @param cityId
     * @return
     */
    @Override
    public ReportStatisticsDo queryReportStatistics(Integer cityId) {
        ReportStatisticsDo reportStatisticsDo = new ReportStatisticsDo();
        Integer temp = 0;
        Integer temp1 = 0;
        Integer count = reportEsfGuideMapper.queryReportEsfGuideByCityId(cityId);
        if (null == count) {
            count = 0;
        }
        Calendar calendar = Calendar.getInstance();
        int m = calendar.get(Calendar.DAY_OF_WEEK);

        switch (m) {
            case 1 : temp = 1;temp1 = 2;break;
            case 2 : temp = 2;temp1 = 3;break;
            case 3 : temp = 3;temp1 = 4;break;
            case 4 : temp = 4;temp1 = 3;break;
            case 5 : temp = 3;temp1 = 2;break;
            case 6 : temp = 2;temp1 = 1;break;
            case 7 : temp = 1;temp1 = 4;break;
        }
        reportStatisticsDo.setReportPeople(count + temp);
        reportStatisticsDo.setReportCount(count + temp1);
        reportStatisticsDo.setUnShelveCount(count);
        reportStatisticsDo.setPayMoney(count * 1000);
        return reportStatisticsDo;
    }
}
