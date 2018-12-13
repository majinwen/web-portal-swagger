package com.toutiao.appV2.apiimpl.report;

import com.alibaba.fastjson.JSONArray;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.appV2.api.report.ReportRestApi;
import com.toutiao.appV2.model.report.*;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ReportRestController implements ReportRestApi {

    private final HttpServletRequest request;

    @Autowired
    private ReportCityService reportCityService;

    @Autowired
    public ReportRestController(HttpServletRequest request) {
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
        ReportNewGuideAttentionListResponse rsp = new ReportNewGuideAttentionListResponse();
        List<ReportNewGuideAttention> data = reportCityService.selectReportNewGuideAttentionList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideHotListResponse rsp = new ReportNewGuideHotListResponse();
        List<ReportNewGuideHot> data = reportCityService.selectReportNewGuideHotList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuidePopularListResponse> selectReportNewGuidePopularList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuidePopularListResponse rsp = new ReportNewGuidePopularListResponse();
        List<ReportNewGuidePopular> data = reportCityService.selectReportNewGuidePopularList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideSalesListResponse rsp = new ReportNewGuideSalesListResponse();
        List<ReportNewGuideSales> data = reportCityService.selectReportNewGuideSalesList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewPreferentialListResponse rsp = new ReportNewPreferentialListResponse();
        List<ReportNewPreferential> data = reportCityService.selectReportNewPreferentialList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportEsfProjHotListResponse> selectReportEsfProjHotList() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportEsfProjHotListResponse rsp = new ReportEsfProjHotListResponse();
        List<ReportEsfProjHot> data = reportCityService.selectReportEsfProjHotList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportAreaHotListResponse> selectReportAreaHotList(Integer pageNum, Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportAreaHotListResponse rsp = new ReportAreaHotListResponse();
        List<ReportAreaHot> data = reportCityService.selectReportAreaHotList(cityId);
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }


    private ReportCityResponse turnToResponse(ReportCity reportCity) {
        ReportCityResponse reportCityResponse = new ReportCityResponse();
        reportCityResponse.setToday(DateUtil.getCurrStrDate());
        BeanUtils.copyProperties(reportCity, reportCityResponse);
        reportCityResponse.setNewGuideAttention(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideAttentionResponse.class));
        reportCityResponse.setNewGuideHot(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideHotResponse.class));
        reportCityResponse.setNewGuideSales(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideSalesResponse.class));
        reportCityResponse.setNewGuidePopular(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuidePopularResponse.class));
        reportCityResponse.setNewPreferential(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewPreferentialResponse.class));
        reportCityResponse.setEsfPlotHot(JSONArray.parseArray(reportCity.getEsfPlotHot(), ReportEsfProjHotResponse.class));
        reportCityResponse.setAreaHot(JSONArray.parseArray(reportCity.getAreaHot(), ReportAreaHotResponse.class));
        reportCityResponse.setZfPriceRange(JSONArray.parseArray(reportCity.getZfPriceRange(), ReportRentPriceDistrbutionResponse.class));
        reportCityResponse.setEsfPriceRange(JSONArray.parseArray(reportCity.getEsfPriceRange(), ReportEsfTongbiDescriptionResponse.class));
        reportCityResponse.setEsfTeseJiangjia(JSONArray.parseArray(reportCity.getEsfTeseJiangjia(), ReportTeSeJiangJiaRespose.class));
        reportCityResponse.setEsfTeseJianlou(JSONArray.parseArray(reportCity.getEsfTeseJianlou(), ReportTeSeJianLouRespose.class));
        reportCityResponse.setEsfTeseQiangshou(JSONArray.parseArray(reportCity.getEsfTeseQiangshou(), ReportTeSeQiangShouRespose.class));
        reportCityResponse.setNewPriceRange(JSONArray.parseArray(reportCity.getNewPriceRange(), ReportPriceQuotationsResponse.class));
        reportCityResponse.setEsfPriceFenbu(JSONArray.parseArray(reportCity.getNewPriceRange(), ReportPriceQuotationsResponse.class));
        return reportCityResponse;
    }
}
