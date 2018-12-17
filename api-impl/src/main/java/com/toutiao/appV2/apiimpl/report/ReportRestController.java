package com.toutiao.appV2.apiimpl.report;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.util.Collections;
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
                                                                                                 @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                                     @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                                         @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                                   @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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
                                                                             @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
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

        //新房指南关注榜
        reportCityResponse.setNewGuideAttention(JSONArray.parseArray(reportCity.getNewGuideAttention(), ReportNewGuideAttentionResponse.class));

        //新房指南热门楼盘榜
        reportCityResponse.setNewGuideHot(JSONArray.parseArray(reportCity.getNewGuideHot(), ReportNewGuideHotResponse.class));

        //新房指南销售榜
        reportCityResponse.setNewGuideSales(JSONArray.parseArray(reportCity.getNewGuideSales(), ReportNewGuideSalesResponse.class));

        //新房指南人气榜
        reportCityResponse.setNewGuidePopular(JSONArray.parseArray(reportCity.getNewGuidePopular(), ReportNewGuidePopularResponse.class));

        //优惠楼盘
        reportCityResponse.setNewPreferential(JSONArray.parseArray(reportCity.getNewPreferential(), ReportNewPreferentialResponse.class));

        //二手房热门小区
        List<ReportEsfProjHotResponse> reportEsfProjHotResponseList = JSONArray.parseArray(reportCity.getEsfPlotHot(), ReportEsfProjHotResponse.class);
        Collections.sort(reportEsfProjHotResponseList);
        reportCityResponse.setEsfPlotHot(reportEsfProjHotResponseList);

        //热门商圈
        reportCityResponse.setAreaHot(JSONArray.parseArray(reportCity.getAreaHot(), ReportAreaHotResponse.class));

        //租房价格分布
        reportCityResponse.setZfPriceRange(JSONArray.parseArray(reportCity.getZfPriceRange(), ReportRentPriceDistrbutionResponse.class));

        //二手房价格分布
        reportCityResponse.setEsfPriceRange(JSONArray.parseArray(reportCity.getEsfPriceRange(), ReportEsfTongbiDescriptionResponse.class));

        //二手房特色房源：降价房
        List<ReportTeSeJiangJiaRespose> teSeJiangjiaResposeList = JSONArray.parseArray(reportCity.getEsfTeseJiangjia(), ReportTeSeJiangJiaRespose.class);
        Collections.sort(teSeJiangjiaResposeList);
        reportCityResponse.setEsfTeseJiangjia(teSeJiangjiaResposeList);

        //二手房特色房源：捡漏房
        JSONObject jianolouJson = JSONObject.parseObject(reportCity.getEsfTeseJianlou());
        ReportTeSeJianLouRespose reportTeSeJianLouRespose = new ReportTeSeJianLouRespose();
        //捡漏房日均价
        List<LowerHouseQuotationResponse> lowerHouseQuotationResponseList = JSONArray.parseArray(jianolouJson.getString("lower_house_quotation"),LowerHouseQuotationResponse.class);
        Collections.sort(lowerHouseQuotationResponseList);
        reportTeSeJianLouRespose.setHouseQuotationList(lowerHouseQuotationResponseList);
        //二手房日均价
        List<EsfQuotationRespose> esfQuotationResposeList = JSONArray.parseArray(jianolouJson.getString("esf_quotation"),EsfQuotationRespose.class);
        Collections.sort(esfQuotationResposeList);
        reportTeSeJianLouRespose.setEsfQuotationList(esfQuotationResposeList);

        reportCityResponse.setEsfTeseJianlou(reportTeSeJianLouRespose);

        //二手房特色房源：抢手房
        List<ReportTeSeQiangShouRespose> teSeQiangShouResposeList = JSONArray.parseArray(reportCity.getEsfTeseQiangshou(), ReportTeSeQiangShouRespose.class);
        Collections.sort(teSeQiangShouResposeList);
        reportCityResponse.setEsfTeseQiangshou(teSeQiangShouResposeList);


        //新房价格趋势，近6个月数据
        List<ReportPriceQuotationsResponse> newPriceRange = JSONArray.parseArray(reportCity.getNewPriceRange(), ReportPriceQuotationsResponse.class);
        Collections.sort(newPriceRange);
        reportCityResponse.setNewPriceRange(newPriceRange);

        //二手房均价趋势，近6个月数据
        List<ReportPriceQuotationsResponse> esfPriceFenbu = JSONArray.parseArray(reportCity.getEsfPriceFenbu(), ReportPriceQuotationsResponse.class);
        Collections.sort(esfPriceFenbu);
        reportCityResponse.setEsfPriceFenbu(esfPriceFenbu);

        return reportCityResponse;
    }

}
