/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.plot;

import com.toutiao.appV2.model.plot.NearbyPlotsListResponse;
import com.toutiao.appV2.model.plot.PlotDetailsFewListResponse;
import com.toutiao.appV2.model.plot.PlotDetailsResponse;
import com.toutiao.appV2.model.plot.PlotEsfListResponse;
import com.toutiao.appV2.model.plot.PlotListResponse;
import com.toutiao.appV2.model.plot.PlotTop50ListResponse;
import com.toutiao.appV2.model.plot.PlotTrafficResponse;
import com.toutiao.appV2.model.plot.PlotsEsfRoomCountResponse;
import com.toutiao.appV2.model.plot.PlotsThemeResponse;
import com.toutiao.appV2.model.plot.RentDetailsListResponse;
import com.toutiao.appV2.model.plot.RentNumListResponse;
import com.toutiao.appV2.model.plot.SearchHotProjDomain;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

@Api(value = "plotcontroller", description = "小区信息接口")
public interface PlotApi {

    @ApiOperation(value = "小区周边配套", nickname = "getAroundInfoByPlotId", notes = "", response = PlotTrafficResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotTrafficResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getAroundInfoByPlotId",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotTrafficResponse> getAroundInfoByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId);


    @ApiOperation(value = "根据小区id，户型查询房源列表", nickname = "getEsfByPlotsIdAndRoom", notes = "", response = PlotEsfListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotEsfListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getEsfByPlotsIdAndRoom",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotEsfListResponse> getEsfByPlotsIdAndRoom(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "room", required = false) Optional<Integer> room, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize);


    @ApiOperation(value = "查询小区附近列表", nickname = "getNearbyPlotsList", notes = "", response = NearbyPlotsListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NearbyPlotsListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getNearbyPlotsList",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<NearbyPlotsListResponse> getNearbyPlotsList(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Optional<String> distance);


    @ApiOperation(value = "获取周边小区", nickname = "getPlotAroundByLocation", notes = "", response = PlotDetailsFewListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotDetailsFewListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getAroundPlotByLocation",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotDetailsFewListResponse> getPlotAroundByLocation(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon);


    @ApiOperation(value = "获取推荐小区", nickname = "getPlotByRecommendCondition", notes = "", response = PlotDetailsFewListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotDetailsFewListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getPlotByRecommendCondition",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotDetailsFewListResponse> getPlotByRecommendCondition(@ApiParam(value = "") @Valid @RequestParam(value = "districtId", required = false) Optional<List<String>> districtId, @ApiParam(value = "") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<String>> layoutId, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<Integer> userId, @ApiParam(value = "") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "") @Valid @RequestParam(value = "city", required = false) Optional<String> city, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize, @ApiParam(value = "") @Valid @RequestParam(value = "flag", required = false) Optional<Integer> flag);


    @ApiOperation(value = "获取小区详情信息", nickname = "getPlotDetailByPlotId", notes = "", response = PlotDetailsResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotDetailsResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getPlotDetailByPlotId",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotDetailsResponse> getPlotDetailByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId);


    @ApiOperation(value = "根据条件获取小区列表", nickname = "getPlotListByRequirement", notes = "", response = PlotListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getPlotListByRequirement",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotListResponse> getPlotListByRequirement(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon, @ApiParam(value = "") @Valid @RequestParam(value = "isTop", required = false) Optional<Integer> isTop, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Optional<Double> distance);


    @ApiOperation(value = "根据小区id获取小区下房源数量", nickname = "getPlotsEsfList", notes = "", response = PlotsEsfRoomCountResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotsEsfRoomCountResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getPlotsEsfList",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotsEsfRoomCountResponse> getPlotsEsfList(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId);


    @ApiOperation(value = "小区专题落地页控制器", nickname = "getPlotsTheme", notes = "", response = PlotsThemeResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotsThemeResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getPlotsTheme",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotsThemeResponse> getPlotsTheme(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "") @Valid @RequestParam(value = "districtIds", required = false) Optional<List<Integer>> districtIds, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "nearestPark", required = false) Optional<String> nearestPark, @ApiParam(value = "") @Valid @RequestParam(value = "recommendBuildTagsId", required = false) Optional<Integer> recommendBuildTagsId);


    @ApiOperation(value = "查询小区下出租房", nickname = "getRentListByPlotId", notes = "", response = RentDetailsListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentDetailsListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getRentOfPlotByPlotId",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<RentDetailsListResponse> getRentListByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "rentType", required = false) Optional<Integer> rentType, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum);


    @ApiOperation(value = "查询小区下出租房的个数", nickname = "getRentNumByPlotId", notes = "", response = RentNumListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentNumListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/queryRentNumByPlotId",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<RentNumListResponse> getRentNumByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId);


    @ApiOperation(value = "根据城市id获取城市热门小区", nickname = "getHotPlotByCityId", notes = "", response = SearchHotProjDomain.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SearchHotProjDomain.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getHotPlotByCityId",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<SearchHotProjDomain> getHotPlotByCityId(@ApiParam(value = "houseType") @Valid @RequestParam(value = "houseType", required = false) Optional<Integer> houseType);


    @ApiOperation(value = "小区top50查询", nickname = "getTop50List", notes = "", response = PlotTop50ListResponse.class, tags = {"plotcontroller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PlotTop50ListResponse.class)})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/plot/getTop50List",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<PlotTop50ListResponse> getTop50List(@ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize);

}
