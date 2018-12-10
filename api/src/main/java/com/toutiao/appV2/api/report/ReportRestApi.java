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

    @ApiOperation(value = "", nickname = "selectReportNewGuideAttentionList", notes = "", response = ReportNewGuideAttentionListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideAttentionListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideAttentionList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList();

    @ApiOperation(value = "", nickname = "selectReportNewGuideHotList", notes = "", response = ReportNewGuideHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList();

    @ApiOperation(value = "", nickname = "selectReportNewGuidePopularList", notes = "", response = ReportNewGuidePopularListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuidePopularListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuidePopularList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<ReportNewGuidePopularListResponse> selectReportNewGuidePopularList();

    @ApiOperation(value = "", nickname = "selectReportNewGuideSalesList", notes = "", response = ReportNewGuideSalesListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideSalesListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideSalesList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList();

    @ApiOperation(value = "", nickname = "selectReportNewPreferentialList", notes = "", response = ReportNewPreferentialListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewPreferentialListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewPreferentialList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList();

}
