package com.toutiao.appV2.apiimpl.report;

import com.alibaba.fastjson.JSONArray;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.appV2.api.report.ReportRestApi;
import com.toutiao.appV2.model.report.*;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.mapper.report.ReportNewGuideAttentionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReportRestController implements ReportRestApi {

    private final HttpServletRequest request;

    @Autowired
    private  ReportCityService reportCityService;
    @Autowired
    public ReportRestController(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public ResponseEntity<ReportCityResponse> selectReportCity() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportCity reportCity = reportCityService.selectReportCityByCityId(cityId);
        ReportCityResponse reportCityResponse = turnToResponse(reportCity);
        return new ResponseEntity<>(reportCityResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
//        return new ResponseEntity<>(reportCityService.selectReportNewGuideAttentionList(cityId), HttpStatus.OK);
        return null;
    }

    @Override
    public ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList() {
        return null;
    }

    @Override
    public ResponseEntity<ReportNewGuidePopularListResponse> selectReportNewGuidePopularList() {
        return null;
    }

    @Override
    public ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList() {
        return null;
    }

    @Override
    public ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList() {
        return null;
    }

    private ReportCityResponse turnToResponse(ReportCity reportCity){
        ReportCityResponse reportCityResponse = new ReportCityResponse();
        BeanUtils.copyProperties(reportCity,reportCityResponse);
        reportCityResponse.setNewGuideAttention(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideAttention.class));
        reportCityResponse.setNewGuideHot(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideHot.class));
        reportCityResponse.setNewGuideSales(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideSales.class));
        reportCityResponse.setNewGuidePopular(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuidePopular.class));
        reportCityResponse.setNewPreferential(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewPreferential.class));
        reportCityResponse.setEsfPlotHot(JSONArray.parseArray(reportCity.getEsfPlotHot(), ReportEsfProjHot.class));
        reportCityResponse.setAreaHot(JSONArray.parseArray(reportCity.getAreaHot(), ReportAreaHot.class));
        reportCityResponse.setZfPriceRange(JSONArray.parseArray(reportCity.getZfPriceRange(), ReportRentPriceDistrbution.class));
        reportCityResponse.setEsfPriceRange(JSONArray.parseArray(reportCity.getEsfPriceRange(), ReportEsfTongbiDescription.class));
//        reportCityResponse.setEsfTeseJiangjia(JSONArray.parseArray(reportCity.getEsfTeseJiangjia(), ReportEsfTongbiDescription.class));
//        reportCityResponse.setEsfTeseJianlou(JSONArray.parseArray(reportCity.getEsfTeseJianlou, ReportEsfTongbiDescription.class));
//        reportCityResponse.setEsfTeseQiangshou(JSONArray.parseArray(reportCity.getEsfTeseQiangshou, ReportEsfTongbiDescription.class));
        return reportCityResponse;
    }
}
