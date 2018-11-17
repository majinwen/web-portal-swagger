package com.toutiao.appV2.apiimpl.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.hotplot.HotPlotListResponse;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.plot.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.appV2.model.plot.*;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.appV2.api.plot.PlotApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

@Controller
public class PlotApiController implements PlotApi {

    private static final Logger log = LoggerFactory.getLogger(PlotApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HotPlotsRestService hotPlotsRestService;

    @Autowired
    private NearbyPlotsRestService nearbyPlotsRestService;

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;

    @Autowired
    private RentRestService appRentRestService;

    @Autowired
    private PlotsThemeRestService plotsThemeRestService;

    @Autowired
    private PlotsRestService appPlotService;

    @org.springframework.beans.factory.annotation.Autowired
    public PlotApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<PlotTrafficResponse> getAroundInfoByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotTrafficResponse plotTrafficResponse = new PlotTrafficResponse();
                PlotTrafficDo plotTrafficDo = appPlotService.queryPlotDataInfo(plotId.get());
                BeanUtils.copyProperties(plotTrafficDo, plotTrafficResponse);
                return new ResponseEntity<PlotTrafficResponse>(plotTrafficResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotTrafficResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotTrafficResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<PlotEsfListResponse> getEsfByPlotsIdAndRoom(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "room", required = false) Optional<Integer> room, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer plotsId = plotId.get();
                Integer roomQuery = room.get();
                Integer pageNumQuery = pageNum.get();
                Integer pageSizeQuery = pageSize.get();
                List<SellAndClaimHouseDetailsDo> sellHouseDoList = plotsEsfRestService.getEsfByPlotsIdAndRoom(plotsId, roomQuery, pageNumQuery, pageSizeQuery, CityUtils.getCity());
                JSONArray json = JSONArray.parseArray(JSON.toJSONString(sellHouseDoList));
                List<PlotEsfResponse> plotEsfResponses = JSONObject.parseArray(json.toJSONString(), PlotEsfResponse.class);
                PlotEsfListResponse plotEsfListResponse = new PlotEsfListResponse();
                plotEsfListResponse.setPlotEsfResponseList(plotEsfResponses);
                plotEsfListResponse.setTotalNum(plotEsfResponses.size());
                return new ResponseEntity<PlotEsfListResponse>(plotEsfListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotEsfListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<PlotEsfListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NearbyPlotsListResponse> getNearbyPlotsList(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Optional<String> distance) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NearbyPlotsDoQuery nearbyPlotsDoQuery = new NearbyPlotsDoQuery();
                nearbyPlotsDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
                nearbyPlotsDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
                nearbyPlotsDoQuery.setDistance(distance.get());
                nearbyPlotsDoQuery.setLat(lat.get());
                nearbyPlotsDoQuery.setLon(lon.get());
                nearbyPlotsDoQuery.setAreaId(areaId.get());
                nearbyPlotsDoQuery.setBeginArea(beginArea.get());
                nearbyPlotsDoQuery.setBeginPrice(beginPrice.get());
                nearbyPlotsDoQuery.setCityId(cityId.get());
                nearbyPlotsDoQuery.setDistrictId(districtId.get());
                nearbyPlotsDoQuery.setEndArea(endArea.get());
                nearbyPlotsDoQuery.setEndPrice(endPrice.get());
                nearbyPlotsDoQuery.setHouseYearId(houseYearId.get());
                nearbyPlotsDoQuery.setKeyword(keyword.get());
                nearbyPlotsDoQuery.setLabelId((Integer[]) labelId.get().toArray());
                nearbyPlotsDoQuery.setPageNum(pageNum.get());
                nearbyPlotsDoQuery.setPageSize(pageSize.get());
                nearbyPlotsDoQuery.setSubwayLineId(subwayLineId.get());
                nearbyPlotsDoQuery.setSubwayStationId(subwayStationId.get());
                PlotDetailsFewDomain plotDetailsFewDomain = nearbyPlotsRestService.queryNearbyPlotsListByUserCoordinate(nearbyPlotsDoQuery);
                NearbyPlotsListResponse newHouseLayoutCountResponse = new NearbyPlotsListResponse();
                BeanUtils.copyProperties(plotDetailsFewDomain, newHouseLayoutCountResponse);

                return new ResponseEntity<NearbyPlotsListResponse>(newHouseLayoutCountResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<NearbyPlotsListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<NearbyPlotsListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<PlotDetailsFewListResponse> getPlotAroundByLocation(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                List<PlotDetailsFewDo> plotDetailsFewDoList = appPlotService.queryAroundPlotByLocation(lat.get(),
                        lon.get(), plotId.get(), CityUtils.getCity());
                JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotDetailsFewDoList));
                List<PlotDetailsFewResponse> plotDetailsFewResponseList = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
                PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
                plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewResponseList);
                plotDetailsFewListResponse.setTotalNum(plotDetailsFewResponseList.size());
                return new ResponseEntity<PlotDetailsFewListResponse>(plotDetailsFewListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotDetailsFewListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotDetailsFewListResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<PlotDetailsFewListResponse> getPlotByRecommendCondition(@ApiParam(value = "") @Valid @RequestParam(value = "districtId", required = false) Optional<List<String>> districtId, @ApiParam(value = "") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<String>> layoutId, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<Integer> userId, @ApiParam(value = "") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "") @Valid @RequestParam(value = "city", required = false) Optional<String> city, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize, @ApiParam(value = "") @Valid @RequestParam(value = "flag", required = false) Optional<Integer> flag) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
                userFavoriteConditionDoQuery.setBeginPrice(beginPrice.get());
                userFavoriteConditionDoQuery.setCity(city.get());
                userFavoriteConditionDoQuery.setDistrictId((String[]) districtId.get().toArray());
                userFavoriteConditionDoQuery.setEndPrice(endPrice.get());
                userFavoriteConditionDoQuery.setFlag(flag.get());
                userFavoriteConditionDoQuery.setLayoutId((String[]) layoutId.get().toArray());
                userFavoriteConditionDoQuery.setPageNum(pageNum.get());
                userFavoriteConditionDoQuery.setPageSize(pageSize.get());
                userFavoriteConditionDoQuery.setUserId(userId.get());
                List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
                JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
                List<PlotDetailsFewResponse> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
                PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
                plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewDos);
                plotDetailsFewListResponse.setTotalNum(plotDetailsFewDos.size());
                return new ResponseEntity<PlotDetailsFewListResponse>(plotDetailsFewListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotDetailsFewListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<PlotDetailsFewListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<PlotDetailsResponse> getPlotDetailByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotId.get(), CityUtils.getCity());
                PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
                BeanUtils.copyProperties(plotDetailsDo, plotDetailsResponse);
                return new ResponseEntity<PlotDetailsResponse>(plotDetailsResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotDetailsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotDetailsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<PlotListResponse> getPlotListByRequirement(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon, @ApiParam(value = "") @Valid @RequestParam(value = "isTop", required = false) Optional<Integer> isTop, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Optional<Double> distance) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
                plotListDoQuery.setIsTop(isTop.get());
                plotListDoQuery.setDistance(distance.get());
                plotListDoQuery.setLat(lat.get());
                plotListDoQuery.setLon(lon.get());

                plotListDoQuery.setAreaId(areaId.get());
                plotListDoQuery.setBeginArea(beginArea.get());
                plotListDoQuery.setBeginPrice(beginPrice.get());
                plotListDoQuery.setCityId(cityId.get());
                plotListDoQuery.setDistrictId(districtId.get());
                plotListDoQuery.setEndArea(endArea.get());
                plotListDoQuery.setEndPrice(endPrice.get());
                plotListDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
                plotListDoQuery.setHouseYearId(houseYearId.get());
                plotListDoQuery.setKeyword(keyword.get());
                plotListDoQuery.setLabelId((Integer[]) labelId.get().toArray());
                plotListDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
                plotListDoQuery.setPageNum(pageNum.get());
                plotListDoQuery.setPageSize(pageSize.get());
                plotListDoQuery.setSubwayLineId(subwayLineId.get());
                plotListDoQuery.setSubwayStationId(subwayStationId.get());

                PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
                return new ResponseEntity<PlotListResponse>(JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotListResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<PlotsEsfRoomCountResponse> getPlotsEsfList(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotId.get(), CityUtils.getCity());
                PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = new PlotsEsfRoomCountResponse();
                BeanUtils.copyProperties(plotsEsfRoomCountDomain, plotsEsfRoomCountResponse);
                return new ResponseEntity<PlotsEsfRoomCountResponse>(plotsEsfRoomCountResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotsEsfRoomCountResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotsEsfRoomCountResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<PlotsThemeResponse> getPlotsTheme(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "") @Valid @RequestParam(value = "districtIds", required = false) Optional<List<Integer>> districtIds, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "nearestPark", required = false) Optional<String> nearestPark, @ApiParam(value = "") @Valid @RequestParam(value = "recommendBuildTagsId", required = false) Optional<Integer> recommendBuildTagsId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotsThemeDoQuery plotsThemeDoQuery = new PlotsThemeDoQuery();
                plotsThemeDoQuery.setDistrictIds((Integer[]) districtIds.get().toArray());
                plotsThemeDoQuery.setDistrictId(districtId.get());
                plotsThemeDoQuery.setNearestPark(nearestPark.get());
                plotsThemeDoQuery.setRecommendBuildTagsId(recommendBuildTagsId.get());
                plotsThemeDoQuery.setAreaId(areaId.get());
                plotsThemeDoQuery.setBeginArea(beginArea.get());
                plotsThemeDoQuery.setBeginPrice(beginPrice.get());
                plotsThemeDoQuery.setCityId(cityId.get());
                plotsThemeDoQuery.setEndArea(endArea.get());
                plotsThemeDoQuery.setEndPrice(endPrice.get());
                plotsThemeDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
                plotsThemeDoQuery.setHouseYearId(houseYearId.get());
                plotsThemeDoQuery.setKeyword(keyword.get());
                plotsThemeDoQuery.setLabelId((Integer[]) labelId.get().toArray());
                plotsThemeDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
                plotsThemeDoQuery.setPageNum(pageNum.get());
                plotsThemeDoQuery.setPageSize(pageSize.get());
                plotsThemeDoQuery.setSubwayLineId(subwayLineId.get());
                plotsThemeDoQuery.setSubwayStationId(subwayStationId.get());
                PlotsThemeDomain plotsThemeDos = plotsThemeRestService.getPlotsThemeList(plotsThemeDoQuery, CityUtils.getCity());
                PlotsThemeResponse plotsThemeResponse = new PlotsThemeResponse();
                BeanUtils.copyProperties(plotsThemeDos, plotsThemeResponse);
                return new ResponseEntity<PlotsThemeResponse>(plotsThemeResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotsThemeResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotsThemeResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<RentDetailsListResponse> getRentListByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "rentType", required = false) Optional<Integer> rentType, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                RentDetailsListDo rentDetailsListDo = appRentRestService.queryRentListByPlotId(plotId.get(),
                        rentType.get(), pageNum.get(), CityUtils.getCity());
                RentDetailsListResponse rentDetailsListResponse = new RentDetailsListResponse();
                BeanUtils.copyProperties(rentDetailsListDo, rentDetailsListResponse);
                return new ResponseEntity<RentDetailsListResponse>(rentDetailsListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<RentDetailsListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RentDetailsListResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<RentNumListResponse> getRentNumByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                RentNumListDo rentNumListDo = appRentRestService.queryRentNumByPlotId(plotId.get(), CityUtils.getCity());
                return new ResponseEntity<RentNumListResponse>(JSON.parseObject(JSON.toJSONString(rentNumListDo), RentNumListResponse.class), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<RentNumListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RentNumListResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<SearchHotProjDomain> getHotPlotByCityId(@ApiParam(value = "houseType") @Valid @RequestParam(value = "houseType", required = false) Optional<Integer> houseType) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                String city = "";
                if (StringTool.isNotEmpty(CityUtils.getCity())) {
                    city = CityUtils.returnCityId(CityUtils.getCity()).toString();
                }
                com.toutiao.app.domain.hotplot.SearchHotProjDomain hotPlotsByCityId = hotPlotsRestService.getHotPlotsByCityId(houseType.get(), city);
                SearchHotProjDomain searchHotProjDomain = new SearchHotProjDomain();
                BeanUtils.copyProperties(hotPlotsByCityId, searchHotProjDomain);
                return new ResponseEntity<SearchHotProjDomain>(searchHotProjDomain, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<SearchHotProjDomain>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SearchHotProjDomain>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<PlotTop50ListResponse> getTop50List(@ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                PlotTop50ListDoQuery plotTop50ListDoQuery = new PlotTop50ListDoQuery();
                plotTop50ListDoQuery.setDistrictId(districtId.get());
                plotTop50ListDoQuery.setPageNum(pageNum.get());
                plotTop50ListDoQuery.setPageSize(pageSize.get());
                List<PlotTop50Do> plotTop50Dos = appPlotService.getPlotTop50List(plotTop50ListDoQuery, CityUtils.getCity());
                JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotTop50Dos));
                PlotTop50ListResponse plotTop50ListResponse = new PlotTop50ListResponse();
                List<PlotTop50Response> plotTop50Responses = JSONObject.parseArray(json.toJSONString(), PlotTop50Response.class);
                plotTop50ListResponse.setPlotTop50ResponseList(plotTop50Responses);
                plotTop50ListResponse.setTotalNum(plotTop50Responses.size());
                return new ResponseEntity<PlotTop50ListResponse>(plotTop50ListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<PlotTop50ListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlotTop50ListResponse>(HttpStatus.NOT_IMPLEMENTED);

    }

}
