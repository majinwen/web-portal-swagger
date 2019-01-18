package com.toutiao.appV2.apiimpl.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.report.*;
import com.toutiao.app.service.report.ReportCityService;
import com.toutiao.appV2.api.report.ReportRestApi;
import com.toutiao.appV2.model.report.*;
import com.toutiao.web.common.constant.syserror.CityReportErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
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
import java.util.Calendar;
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
        ReportCityResponse reportCityResponse;
        try {
            reportCityResponse = turnToResponse(reportCity);
        } catch (Exception e) {
            throw new BaseException(CityReportErrorCodeEnum.PARSE_FAILED);
        }
        return new ResponseEntity<>(reportCityResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportCityForHPResponse> selectReportCityForHP() {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportCity reportCity = reportCityService.selectReportCityByCityId(cityId);
        ReportCityForHPResponse reportCityForHPResponse = new ReportCityForHPResponse();
        BeanUtils.copyProperties(reportCity, reportCityForHPResponse);
        reportCityForHPResponse.setToday(DateUtil.format(System.currentTimeMillis(), "yyyy.MM.dd"));
        return new ResponseEntity<>(reportCityForHPResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList(@ApiParam(value = "pageNum") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                                 @ApiParam(value = "pageSize") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        Integer cityId = CityUtils.returnCityId(CityUtils.getCity());
        ReportNewGuideAttentionListResponse rsp = new ReportNewGuideAttentionListResponse();
        List<ReportNewGuideAttention> list = reportCityService.selectReportNewGuideAttentionList(cityId, pageNum, pageSize);
        List<ReportNewGuideAttentionResponse> data = new ArrayList<>();
        for (ReportNewGuideAttention reportNewGuideAttention : list) {
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
        for (ReportNewGuideHot reportNewGuideHot : list) {
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
        for (ReportNewGuidePopular reportNewGuidePopular : list) {
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
        for (ReportNewGuideSales reportNewGuideSales : list) {
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
        for (ReportNewPreferential reportNewPreferential : list) {
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
        for (ReportEsfProjHot reportEsfProjHot : list) {
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
        for (ReportAreaHot reportAreaHot : list) {
            ReportAreaHotResponse reportAreaHotResponse = new ReportAreaHotResponse();
            BeanUtils.copyProperties(reportAreaHot, reportAreaHotResponse);
            data.add(reportAreaHotResponse);
        }
        rsp.setData(data);
        rsp.setTotalCount(data.size());
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    /**
     * 查询举报统计
     * @return
     */
    @Override
    public ResponseEntity<ReportStatisticsResponse> queryReportStatistics() {
        ReportStatisticsResponse response = new ReportStatisticsResponse();
        ReportStatisticsDo reportStatisticsDo = reportCityService.queryReportStatistics(CityUtils.returnCityId(CityUtils.getCity()));
        BeanUtils.copyProperties(reportStatisticsDo, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        if (StringTool.isNotEmptyList(reportEsfProjHotResponseList)) {
            Collections.sort(reportEsfProjHotResponseList);
        }
        reportCityResponse.setEsfPlotHot(reportEsfProjHotResponseList);

        //热门商圈
        reportCityResponse.setAreaHot(JSONArray.parseArray(reportCity.getAreaHot(), ReportAreaHotResponse.class));

        //租房价格分布
        reportCityResponse.setZfPriceRange(JSONArray.parseArray(reportCity.getZfPriceRange(), ReportRentPriceDistrbutionResponse.class));

        //二手房价格分布
        reportCityResponse.setEsfPriceRange(JSONArray.parseArray(reportCity.getEsfPriceRange(), ReportEsfTongbiDescriptionResponse.class));

        //二手房特色房源：降价房
        List<ReportTeSeJiangJiaRespose> teSeJiangjiaResposeList = JSONArray.parseArray(reportCity.getEsfTeseJiangjia(), ReportTeSeJiangJiaRespose.class);
        reportCityResponse.setEsfTeseJiangjia(doWellSortForJJ(teSeJiangjiaResposeList, 6));

        //二手房特色房源：捡漏房
        Object jsonObject = JSON.parse(reportCity.getEsfTeseJianlou());
        JSONObject jianolouJson;
        if (jsonObject instanceof JSONObject) {
            jianolouJson = (JSONObject) jsonObject;
        } else {
            jianolouJson = new JSONObject();
        }
        ReportTeSeJianLouRespose reportTeSeJianLouRespose = new ReportTeSeJianLouRespose();
        //捡漏房日均价
        if (StringTool.isEmpty(jianolouJson.getString("lower_house_quotation"))) {
            jianolouJson.put("lower_house_quotation", "[]");
        }
        List<LowerHouseQuotationResponse> lowerHouseQuotationResponseList = JSONArray.parseArray(jianolouJson.getString("lower_house_quotation"), LowerHouseQuotationResponse.class);
        reportTeSeJianLouRespose.setHouseQuotationList(doWellSortForJL(lowerHouseQuotationResponseList, 6));


        //二手房日均价
        if (StringTool.isEmpty(jianolouJson.getString("esf_quotation"))) {
            jianolouJson.put("esf_quotation", "[]");
        }
        List<EsfQuotationRespose> esfQuotationResposeList = JSONArray.parseArray(jianolouJson.getString("esf_quotation"), EsfQuotationRespose.class);
        reportTeSeJianLouRespose.setEsfQuotationList(doWellSortForJLE(esfQuotationResposeList, 6));
        reportCityResponse.setEsfTeseJianlou(reportTeSeJianLouRespose);

        //二手房特色房源：抢手房
        List<ReportTeSeQiangShouRespose> teSeQiangShouResposeList = JSONArray.parseArray(reportCity.getEsfTeseQiangshou(), ReportTeSeQiangShouRespose.class);
        reportCityResponse.setEsfTeseQiangshou(doWellSortForQS(teSeQiangShouResposeList,6));


        //新房价格趋势，近6个月数据
        List<ReportPriceQuotationsResponse> newPriceRange = JSONArray.parseArray(reportCity.getNewPriceRange(), ReportPriceQuotationsResponse.class);
        reportCityResponse.setNewPriceRange(doWellSortForNE(newPriceRange, 6));

        //二手房均价趋势，近6个月数据
        List<ReportPriceQuotationsResponse> esfPriceFenbu = JSONArray.parseArray(reportCity.getEsfPriceFenbu(), ReportPriceQuotationsResponse.class);
        reportCityResponse.setEsfPriceFenbu(doWellSortForNE(esfPriceFenbu, 6));

        return reportCityResponse;
    }

    /**
     * 新房或者二手房趋势
     * @param list
     * @param size
     * @return
     */
    private List<ReportPriceQuotationsResponse> doWellSortForNE(List<ReportPriceQuotationsResponse> list, Integer size) {
        List<ReportPriceQuotationsResponse> returnList = new ArrayList<>();

        for (Integer i = size; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            ReportPriceQuotationsResponse report = new ReportPriceQuotationsResponse();
            report.setMonth(calendar.get(Calendar.MONTH)+1);
            report.setYear(calendar.get(Calendar.YEAR));
            report.setSort(size + 1 - i);
            report.setPrice(0);
            for (ReportPriceQuotationsResponse li : list) {
                if (report.getMonth().equals(li.getMonth()) && report.getYear().equals(li.getYear())) {
                    report.setPrice(li.getPrice());
                }
            }
            returnList.add(report);
        }
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * 降价房
     * @param list
     * @param size
     * @return
     */
    private List<ReportTeSeJiangJiaRespose> doWellSortForJJ(List<ReportTeSeJiangJiaRespose> list, Integer size){
        List<ReportTeSeJiangJiaRespose> returnList = new ArrayList<>();

        for (Integer i = size; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            ReportTeSeJiangJiaRespose report = new ReportTeSeJiangJiaRespose();
            report.setMonth(calendar.get(Calendar.MONTH)+1);
            report.setYear(calendar.get(Calendar.YEAR));
            report.setDay(calendar.get(Calendar.DATE));
            report.setSort(size + 1 - i);
            report.setRatio(0);
            for (ReportTeSeJiangJiaRespose li : list) {
                if (report.getDay().equals(li.getDay()) && report.getMonth().equals(li.getMonth()) && report.getYear().equals(li.getYear())) {
                    report.setRatio(li.getRatio());
                }
            }
            returnList.add(report);
        }
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * 抢手房
     * @param list
     * @param size
     * @return
     */
    private List<ReportTeSeQiangShouRespose> doWellSortForQS(List<ReportTeSeQiangShouRespose> list, Integer size){
        List<ReportTeSeQiangShouRespose> returnList = new ArrayList<>();

        for (Integer i = size; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            ReportTeSeQiangShouRespose report = new ReportTeSeQiangShouRespose();
            report.setMonth(calendar.get(Calendar.MONTH)+1);
            report.setYear(calendar.get(Calendar.YEAR));
            report.setDay(calendar.get(Calendar.DATE));
            report.setSort(size + 1 - i);
            report.setCount(0);
            for (ReportTeSeQiangShouRespose li : list) {
                if (report.getDay().equals(li.getDay()) && report.getMonth().equals(li.getMonth()) && report.getYear().equals(li.getYear())) {
                    report.setCount(li.getCount());
                }
            }
            returnList.add(report);
        }
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * 捡漏二手日均
     * @param list
     * @param size
     * @return
     */
    private List<EsfQuotationRespose> doWellSortForJLE(List<EsfQuotationRespose> list, Integer size){
        List<EsfQuotationRespose> returnList = new ArrayList<>();

        for (Integer i = size; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            EsfQuotationRespose report = new EsfQuotationRespose();
            report.setMonth(calendar.get(Calendar.MONTH)+1);
            report.setYear(calendar.get(Calendar.YEAR));
            report.setDay(calendar.get(Calendar.DATE));
            report.setSort(size + 1 - i);
            report.setPrice(0);
            for (EsfQuotationRespose li : list) {
                if (report.getDay().equals(li.getDay()) && report.getMonth().equals(li.getMonth()) && report.getYear().equals(li.getYear())) {
                    report.setPrice(li.getPrice());
                }
            }
            returnList.add(report);
        }
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * 捡漏房
     * @param list
     * @param size
     * @return
     */
    private List<LowerHouseQuotationResponse> doWellSortForJL(List<LowerHouseQuotationResponse> list, Integer size){
        List<LowerHouseQuotationResponse> returnList = new ArrayList<>();

        for (Integer i = size; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            LowerHouseQuotationResponse report = new LowerHouseQuotationResponse();
            report.setMonth(calendar.get(Calendar.MONTH)+1);
            report.setYear(calendar.get(Calendar.YEAR));
            report.setDay(calendar.get(Calendar.DATE));
            report.setSort(size + 1 - i);
            report.setPrice(0);
            for (LowerHouseQuotationResponse li : list) {
                if (report.getDay().equals(li.getDay()) && report.getMonth().equals(li.getMonth()) && report.getYear().equals(li.getYear())) {
                    report.setPrice(li.getPrice());
                }
            }
            returnList.add(report);
        }
        Collections.sort(returnList);
        return returnList;
    }

}
