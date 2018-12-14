package com.toutiao.appV2.apiimpl.report;

import com.alibaba.fastjson.JSONArray;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.appV2.api.report.ReportRestApi;
import com.toutiao.appV2.model.report.*;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                                 @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideAttentionListResponse rsp = new ReportNewGuideAttentionListResponse();
        List<ReportNewGuideAttention> list = reportCityService.selectReportNewGuideAttentionList(cityId, pageNum, pageSize);
        List<ReportNewGuideAttentionResponse> data = new ArrayList<>();
        for (ReportNewGuideAttention reportNewGuideAttention:list) {
            ReportNewGuideAttentionResponse reportNewGuideAttentionResponse = new ReportNewGuideAttentionResponse();
            BeanUtils.copyProperties(reportNewGuideAttention, reportNewGuideAttentionResponse);
            data.add(reportNewGuideAttentionResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                     @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideHotListResponse rsp = new ReportNewGuideHotListResponse();
        List<ReportNewGuideHot> list = reportCityService.selectReportNewGuideHotList(cityId, pageNum, pageSize);
        List<ReportNewGuideHotResponse> data = new ArrayList<>();
        for (ReportNewGuideHot reportNewGuideHot:list) {
            ReportNewGuideHotResponse reportNewGuideHotResponse = new ReportNewGuideHotResponse();
            BeanUtils.copyProperties(reportNewGuideHot, reportNewGuideHotResponse);
            data.add(reportNewGuideHotResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuidePopularListResponse> selectReportNewGuidePopularList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuidePopularListResponse rsp = new ReportNewGuidePopularListResponse();
        List<ReportNewGuidePopular> list = reportCityService.selectReportNewGuidePopularList(cityId, pageNum, pageSize);
        List<ReportNewGuidePopularResponse> data = new ArrayList<>();
        for (ReportNewGuidePopular reportNewGuidePopular:list) {
            ReportNewGuidePopularResponse reportNewGuidePopularResponse = new ReportNewGuidePopularResponse();
            BeanUtils.copyProperties(reportNewGuidePopular, reportNewGuidePopularResponse);
            data.add(reportNewGuidePopularResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                         @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideSalesListResponse rsp = new ReportNewGuideSalesListResponse();
        List<ReportNewGuideSales> list = reportCityService.selectReportNewGuideSalesList(cityId, pageNum, pageSize);
        List<ReportNewGuideSalesResponse> data = new ArrayList<>();
        for (ReportNewGuideSales reportNewGuideSales:list) {
            ReportNewGuideSalesResponse reportNewGuideSalesResponse = new ReportNewGuideSalesResponse();
            BeanUtils.copyProperties(reportNewGuideSales, reportNewGuideSalesResponse);
            data.add(reportNewGuideSalesResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewPreferentialListResponse rsp = new ReportNewPreferentialListResponse();
        List<ReportNewPreferential> list = reportCityService.selectReportNewPreferentialList(cityId, pageNum, pageSize);
        List<ReportNewPreferentialResponse> data = new ArrayList<>();
        for (ReportNewPreferential reportNewPreferential:list) {
            ReportNewPreferentialResponse reportNewPreferentialResponse = new ReportNewPreferentialResponse();
            BeanUtils.copyProperties(reportNewPreferential, reportNewPreferentialResponse);
            data.add(reportNewPreferentialResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportEsfProjHotListResponse> selectReportEsfProjHotList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                   @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportEsfProjHotListResponse rsp = new ReportEsfProjHotListResponse();
        List<ReportEsfProjHot> list = reportCityService.selectReportEsfProjHotList(cityId, pageNum, pageSize);
        List<ReportEsfProjHotResponse> data = new ArrayList<>();
        for (ReportEsfProjHot reportEsfProjHot:list) {
            ReportEsfProjHotResponse reportEsfProjHotResponse = new ReportEsfProjHotResponse();
            BeanUtils.copyProperties(reportEsfProjHot, reportEsfProjHotResponse);
            data.add(reportEsfProjHotResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportAreaHotListResponse> selectReportAreaHotList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportAreaHotListResponse rsp = new ReportAreaHotListResponse();
        List<ReportAreaHot> list = reportCityService.selectReportAreaHotList(cityId, pageNum, pageSize);
        List<ReportAreaHotResponse> data = new ArrayList<>();
        for (ReportAreaHot reportAreaHot:list) {
            ReportAreaHotResponse reportAreaHotResponse = new ReportAreaHotResponse();
            BeanUtils.copyProperties(reportAreaHot, reportAreaHotResponse);
            data.add(reportAreaHotResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }


    private ReportCityResponse turnToResponse(ReportCity reportCity) {
        ReportCityResponse reportCityResponse = new ReportCityResponse();
        reportCityResponse.setToday(DateUtil.format(System.currentTimeMillis(), "yyyy.MM.dd"));
        BeanUtils.copyProperties(reportCity, reportCityResponse);
        reportCityResponse.setNewGuideAttention(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideAttentionResponse.class));
        reportCityResponse.setNewGuideHot(JSONArray.parseArray(reportCity.getNewGuideHot(), ReportNewGuideHotResponse.class));
        reportCityResponse.setNewGuideSales(JSONArray.parseArray(reportCity.getNewGuideSales(), ReportNewGuideSalesResponse.class));
        reportCityResponse.setNewGuidePopular(JSONArray.parseArray(reportCity.getNewGuidePopular(), ReportNewGuidePopularResponse.class));
        reportCityResponse.setNewPreferential(JSONArray.parseArray(reportCity.getNewPreferential(), ReportNewPreferentialResponse.class));
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
