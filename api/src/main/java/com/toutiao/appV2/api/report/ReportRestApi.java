package com.toutiao.appV2.api.report;

import com.toutiao.appV2.model.report.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

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

    @ApiOperation(value = "数据报告:新房指南关注榜", nickname = "selectReportNewGuideAttentionList", notes = "", response = ReportNewGuideAttentionListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideAttentionListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideAttentionList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideAttentionListResponse> selectReportNewGuideAttentionList();

    @ApiOperation(value = "数据报告:新房指南热门新盘", nickname = "selectReportNewGuideHotList", notes = "", response = ReportNewGuideHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideHotListResponse> selectReportNewGuideHotList();

    @ApiOperation(value = "数据报告:新房指南人气榜", nickname = "selectReportNewGuidePopularList", notes = "", response = ReportNewGuidePopularListResponse.class, tags = {"数据报告",})
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

    @ApiOperation(value = "数据报告:新房指南销售榜", nickname = "selectReportNewGuideSalesList", notes = "", response = ReportNewGuideSalesListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewGuideSalesListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewGuideSalesList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewGuideSalesListResponse> selectReportNewGuideSalesList();

    @ApiOperation(value = "数据报告:优惠楼盘", nickname = "selectReportNewPreferentialList", notes = "", response = ReportNewPreferentialListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportNewPreferentialListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportNewPreferentialList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportNewPreferentialListResponse> selectReportNewPreferentialList();

    @ApiOperation(value = "数据报告:二手房热门小区", nickname = "selectReportEsfProjHotList", notes = "", response = ReportEsfProjHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportEsfProjHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportEsfProjHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportEsfProjHotListResponse> selectReportEsfProjHotList();

    @ApiOperation(value = "数据报告:热门商圈", nickname = "selectReportAreaHotList", notes = "", response = ReportAreaHotListResponse.class, tags = {"数据报告",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReportAreaHotListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/report/selectReportAreaHotList",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<ReportAreaHotListResponse> selectReportAreaHotList(@ApiParam(value = "pageNum") @Valid @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                      @ApiParam(value = "pageSize") @Valid @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);

}
