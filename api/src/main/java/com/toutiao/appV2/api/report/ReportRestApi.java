package com.toutiao.appV2.api.report;

import com.toutiao.appV2.model.report.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "数据报告", description = "数据报告")
public interface ReportRestApi {

    @ApiOperation(value = "获取数据报告", nickname = "selectReportCity", notes = "", response = ReportCityResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportCityResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportCity",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportCityResponse> selectReportCity();

    @ApiOperation(value = "获取首页数据报告数据", nickname = "selectReportCityForHP", notes = "", response = ReportCityForHPResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportCityForHPResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportCityForHP",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportCityForHPResponse> selectReportCityForHP();

    @ApiOperation(value = "数据报告:新房指南关注榜", nickname = "selectReportNewGuideAttentionList", notes = "", response = ReportNewGuideAttentionListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideAttentionListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideAttentionList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:新房指南热门新盘", nickname = "selectReportNewGuideHotList", notes = "", response = ReportNewGuideHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:新房指南人气榜", nickname = "selectReportNewGuidePopularList", notes = "", response = ReportNewGuidePopularListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuidePopularListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuidePopularList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuidePopularListResponse> selectReportNewGuidePopularList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:新房指南销售榜", nickname = "selectReportNewGuideSalesList", notes = "", response = ReportNewGuideSalesListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideSalesListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideSalesList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:优惠楼盘", nickname = "selectReportNewPreferentialList", notes = "", response = ReportNewPreferentialListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewPreferentialListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewPreferentialList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:二手房热门小区", nickname = "selectReportEsfProjHotList", notes = "", response = ReportEsfProjHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportEsfProjHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportEsfProjHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportEsfProjHotListResponse> selectReportEsfProjHotList(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "数据报告:热门商圈", nickname = "selectReportAreaHotList", notes = "", response = ReportAreaHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportAreaHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportAreaHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportAreaHotListResponse> selectReportAreaHotList(Integer pageNum, Integer pageSize);

}
