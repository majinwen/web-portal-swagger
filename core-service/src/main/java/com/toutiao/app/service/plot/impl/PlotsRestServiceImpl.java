package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.report.ReportPipelineRecordEveryMonth;
import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.common.NearbyDistanceService;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.plot.PlotsHomesRestService;
import com.toutiao.app.service.plot.PlotsMarketService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.report.ReportPipelineRecordEveryMonthMapper;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.service.map.MapService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.join.query.ParentIdQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.*;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class PlotsRestServiceImpl implements PlotsRestService {
    private static final Logger logger = LoggerFactory.getLogger(PlotsRestServiceImpl.class);

    @Value("${distance}")
    private Double distance;
    @Value("${plot.child.type}")
    private String childType;
    @Autowired
    private PlotEsDao plotEsDao;
    @Autowired
    private PlotsRestService plotsRestService;
    @Autowired
    private MapService mapService;
    @Autowired
    private PlotsEsfRestService plotsEsfRestService;
    @Autowired
    private RentRestService rentRestService;
    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private PlotsHomesRestService plotsHomesRestService;
    @Autowired
    private PlotsMarketService plotsMarketService;
    @Autowired
    private ReportPipelineRecordEveryMonthMapper reportPipelineRecordEveryMonthMapper;
    @Autowired
    private NearbyDistanceService nearbyDistanceService;


    /**
     * 小区详情信息
     *
     * @param plotId
     * @return
     */
    @Override
    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId, String city) {
        String details = "";
        PlotDetailsDo plotDetailsDo = new PlotDetailsDo();
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id", plotId));
            SearchResponse searchResponse = plotEsDao.queryPlotDetail(boolQueryBuilder, city);
            SearchHit[] hits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : hits) {
                details = searchHit.getSourceAsString();
            }

            if (StringUtils.isNotEmpty(details)) {

                plotDetailsDo = JSON.parseObject(details, PlotDetailsDo.class);
//                String[] photos = plotDetailsDo.getPhoto().get(0).split(",");
                plotDetailsDo.setPhoto(plotDetailsDo.getPhoto());

                try {
                    UserBasic userBasic = UserBasic.getCurrent();
                    if (StringTool.isNotEmpty(userBasic)) {
                        PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                        plotIsFavoriteDoQuery.setUserId(Integer.valueOf(userBasic.getUserId()));
                        plotIsFavoriteDoQuery.setBuildingId(plotDetailsDo.getId());
                        Boolean isFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
                        plotDetailsDo.setIsFavorite(isFavorite);
                    }
                } catch (BaseException e) {
                    logger.info("用户未登录");
                    plotDetailsDo.setIsFavorite(Boolean.FALSE);
                }
                plotDetailsDo.setAvgPrice((double) Math.round(plotDetailsDo.getAvgPrice()));
                if ("商电".equals(plotDetailsDo.getElectricSupply())) {
                    plotDetailsDo.setElectricFee(1.33);
                } else {
                    plotDetailsDo.setElectricFee(0.48);
                }
                if ("商水".equals(plotDetailsDo.getWaterSupply())) {
                    plotDetailsDo.setWaterFee(6.00);
                } else {
                    plotDetailsDo.setWaterFee(5.00);
                }
                if ("0".equals(plotDetailsDo.getHeatingMode())) {
                    plotDetailsDo.setHeatingMode("未知");
                }
                if ("1".equals(plotDetailsDo.getHeatingMode())) {
                    plotDetailsDo.setHeatingMode("集中供暖");
                }
                if ("2".equals(plotDetailsDo.getHeatingMode())) {
                    plotDetailsDo.setHeatingMode("自供暖");
                }
                if (null != plotDetailsDo.getElevator() && "1".equals(plotDetailsDo.getElevator())) {
                    plotDetailsDo.setHasElevator("有");
                }
                if (null != plotDetailsDo.getElevator() && "2".equals(plotDetailsDo.getElevator())) {
                    plotDetailsDo.setHasElevator("无");
                }
//                return plotDetailsDo;
            }

            PlotsHousesDomain plotsHousesDomain = plotsHomesRestService.queryPlotsHomesByPlotId(plotId, city);
            PlotMarketDo plotMarketDo = plotsMarketService.queryPlotMarketByPlotId(plotId);

            PlotMarketDomain plotMarketDomain = null;
            if (null != plotMarketDo) {
                plotMarketDomain = new PlotMarketDomain();
                org.springframework.beans.BeanUtils.copyProperties(plotMarketDo, plotMarketDomain);
                plotMarketDomain.setDistrictName(plotDetailsDo.getArea());
                plotDetailsDo.setPlotMarketDomain(plotMarketDomain);
            }
            plotsHousesDomain.setAvgPrice(plotDetailsDo.getAvgPrice());
            plotDetailsDo.setPlotsHousesDomain(plotsHousesDomain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (details.isEmpty()) {
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_DETAILS_NOT_FOUND);
        }
        return plotDetailsDo;
    }

    /**
     * 获取小区周边配套
     *
     * @param plotId
     * @return
     */
    @Override
    public PlotTrafficDo queryPlotDataInfo(Integer plotId) {
        MapInfo mapInfo = new MapInfo();
        PlotTrafficDo plotTrafficDo = new PlotTrafficDo();
        mapInfo = mapService.getMapInfo(plotId);
        if (mapInfo == null) {
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_TRAFFIC_NOT_FOUND);
        }
        JSONObject datainfo = JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
        //获取小区交通
        JSONObject businfo = (JSONObject) datainfo.get("gongjiao");
        if (businfo.size() > 0) {
            plotTrafficDo.setBusStation(businfo.get("name").toString());
            plotTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
        }
        //获取地铁和环线位置
        PlotDetailsDo plotDetailsDo = plotsRestService.queryPlotDetailByPlotId(plotId, CityUtils.getCity());
        if (!"".equals(plotDetailsDo.getTrafficInformation())) {
            String[] trafficInfo = plotDetailsDo.getTrafficInformation().split("\\$");
            plotTrafficDo.setSubwayStation(trafficInfo[1]);
            plotTrafficDo.setSubwayLine(trafficInfo[0]);
            plotTrafficDo.setSubwayDistance(Double.valueOf(trafficInfo[2]));
        }
        if (null != plotDetailsDo.getRingRoadName() && !"".equals(plotDetailsDo.getRingRoadName())) {
            plotTrafficDo.setRingRoadName(plotDetailsDo.getRingRoadName());
        }
        if (null != plotDetailsDo.getRingRoadDistance()) {
            plotTrafficDo.setRingRoadDistance(Double.valueOf(plotDetailsDo.getRingRoadDistance().toString()));
        }
        return plotTrafficDo;
    }

//    /**
//     * 小区收藏列表
//     * @param list
//     * @return
//     */
//    @Override
//    public PlotFavoriteListDo queryPlotListByPlotIdList(List list, Integer pageNum , Integer size) {
//        PlotFavoriteListDo plotFavoriteListDo = new PlotFavoriteListDo();
//        List<UserFavoritePlotDo> list1 = new ArrayList<>();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termsQuery("id",list));
//        SearchResponse searchResponse = plotEsDao.queryPlotListByPlotIdList(boolQueryBuilder, (pageNum-1)*size, size);
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        if (hits.length>0){
//            for (SearchHit hit:hits){
//                String sourceAsString = hit.getSourceAsString();
//                UserFavoritePlotDo userFavoritePlotDo = JSON.parseObject(sourceAsString, UserFavoritePlotDo.class);
//                list1.add(userFavoritePlotDo);
//            }
//            plotFavoriteListDo.setData(list1);
//            plotFavoriteListDo.setTotalNum(searchResponse.getHits().getTotalHits());
//        }
//        return plotFavoriteListDo;
//    }


    /**
     * 附近小区
     *
     * @param lat
     * @param lon
     * @param plotId
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId, String city) {
        try {
            List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //从该坐标查询距离为distance内的小区
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
            //按照距离排序由近到远并获取小区之间的距离
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);
            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", plotId));
            SearchResponse searchResponse = plotEsDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort, city);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length > 0) {
                for (SearchHit hit : hits) {
                    Map<String, Object> source = hit.getSourceAsMap();
                    PlotDetailsFewDo plotDetailsFewDo = PlotDetailsFewDo.class.newInstance();
                    BeanUtils.populate(plotDetailsFewDo, source);
                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                    plotDetailsFewDoList.add(plotDetailsFewDo);
                }
                return plotDetailsFewDoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 小区列表条件筛选
     *
     * @param plotListDoQuery
     * @return
     */
    @Override
    public PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery, String city) {
        String key = "";
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //关键字
        if (StringTool.isNotEmpty(plotListDoQuery.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart").boost(2));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(plotListDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("rc_nickname", plotListDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //区域id
        if (StringTool.isNotEmpty(plotListDoQuery.getDistrictId()) && plotListDoQuery.getDistrictId() != 0) {
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId", plotListDoQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(plotListDoQuery.getAreaId()) && plotListDoQuery.getAreaId().length != 0) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("tradingAreaId", plotListDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayLineId()) && plotListDoQuery.getSubwayLineId() != 0) {
            boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", plotListDoQuery.getSubwayLineId()));
            key = String.valueOf(plotListDoQuery.getSubwayLineId());
        }
        //地铁站id
        List<String> keyList = new ArrayList<>();
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayStationId()) && plotListDoQuery.getSubwayStationId().length != 0) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("metroStationId", plotListDoQuery.getSubwayStationId()));
            for (int i = 0; i < plotListDoQuery.getSubwayStationId().length; i++) {
                String stationKey = key + "$" + plotListDoQuery.getSubwayStationId()[i];
                keyList.add(stationKey);
            }
        }
        //均价
        if (plotListDoQuery.getBeginPrice() != 0 && plotListDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()).lte(plotListDoQuery.getEndPrice()));
        } else if (plotListDoQuery.getBeginPrice() != 0 && plotListDoQuery.getEndPrice() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()));
        } else if (plotListDoQuery.getBeginPrice() == 0 && plotListDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").lte(plotListDoQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(plotListDoQuery.getHouseYearId())) {
            String[] age = plotListDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(plotListDoQuery.getLabelId())) {
            Integer[] labelId = plotListDoQuery.getLabelId();
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            for (int i = 0; i < labelId.length; i++) {
                if (labelId[i].equals(0)) {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", 1));
                } else {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("recommendBuildTagsId", labelId[i]));
                }
            }
            boolQueryBuilder.must(booleanQueryBuilder);
        }

        //房源面积大小
        if (plotListDoQuery.getBeginArea() != 0 && plotListDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()).lte(plotListDoQuery.getEndArea()), ScoreMode.None));

        } else if (plotListDoQuery.getBeginArea() != 0 && plotListDoQuery.getEndArea() == 0) {
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()), ScoreMode.None));
        } else if (plotListDoQuery.getBeginArea() == 0 && plotListDoQuery.getEndArea() != 0) {

            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("sellHouseArea")
                    .lte(plotListDoQuery.getEndArea()), ScoreMode.None));
        }

//        GeoDistanceSortBuilder geoDistanceSort = null;
        //坐标
        if (StringTool.isNotEmpty(plotListDoQuery.getLat()) && plotListDoQuery.getLat() > 0 && plotListDoQuery.getLon() > 0 && StringTool.isNotEmpty(plotListDoQuery.getLon())) {
            if (StringTool.isEmpty(plotListDoQuery.getDistance())) {
                plotListDoQuery.setDistance(3.0);
            }
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(plotListDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
            //排序
//            geoDistanceSort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
//            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
//            geoDistanceSort.geoDistance(GeoDistance.ARC);
            //sort.point(plotListDoQuery.getLat(), plotListDoQuery.getLon());

        }


        Integer from = 0;
        //分页起始位置
        if (StringTool.isNotEmpty(plotListDoQuery.getPageNum()) && plotListDoQuery.getPageNum() > 1 && StringTool.isNotEmpty(plotListDoQuery.getPageSize()) && plotListDoQuery.getPageSize() > 0) {
            from = (plotListDoQuery.getPageNum() - 1) * plotListDoQuery.getPageSize();
        }
        //是否上架
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));

        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));

        //级别排序
        FieldSortBuilder levelSort = SortBuilders.fieldSort("level").order(SortOrder.ASC);

        //小区分数排序
        FieldSortBuilder plotScoreSort = SortBuilders.fieldSort("plotScore").order(SortOrder.DESC);

        PlotListDo plotListDo = new PlotListDo();
        SearchResponse searchResponse = null;
        if ((StringTool.isNotEmpty(plotListDoQuery.getLat()) && plotListDoQuery.getLat() > 0 && plotListDoQuery.getLon() > 0 && StringTool.isNotEmpty(plotListDoQuery.getLon()))) {
            //排序
            GeoDistanceSortBuilder geoDistanceSort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
            searchResponse = plotEsDao.queryPlotListByRequirementAndKeywordV1(from, boolQueryBuilder, plotListDoQuery.getPageSize(), geoDistanceSort, levelSort, plotScoreSort, city, plotListDoQuery.getSort());

        } else {
            searchResponse = plotEsDao.queryCommonPlotList(from, boolQueryBuilder, plotListDoQuery.getPageSize(), plotListDoQuery.getKeyword(), city, plotListDoQuery.getSort());
//            if (searchResponse != null) {
//                SearchHit[] hits = searchResponse.getHits().getHits();
//
//                if (hits.length > 0) {
//                    for (SearchHit hit : hits) {
//                        commonMethod(hit, keyList, plotDetailsFewDoList, city, null);
//                    }
//                }
//            }
//            plotListDo.setPlotList(plotDetailsFewDoList);
//            plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
//            return plotListDo;
        }

//        long oneKM_size = searchResponse.getHits().getTotalHits();
////        if (searchResponse != null) {
//            int resLocationInfo = searchResponse.getHits().getHits().length;
//            if (resLocationInfo == 10) {
//                SearchHits hits = searchResponse.getHits();
//                SearchHit[] searchHists = hits.getHits();
//                for (SearchHit hit : searchHists) {
//                    commonMethod(hit, keyList, plotDetailsFewDoList, city, plotListDoQuery.getDistance());
//                }
//            } else if (resLocationInfo < 10 && resLocationInfo > 0) {
//                SearchHits hits = searchResponse.getHits();
//                SearchHit[] searchHists = hits.getHits();
//                for (SearchHit hit : searchHists) {
//                    commonMethod(hit, keyList, plotDetailsFewDoList, city, plotListDoQuery.getDistance());
//                }
//                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//                SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(0, booleanQueryBuilder, plotListDoQuery.getPageSize() - resLocationInfo, plotListDoQuery.getKeyword(), city, plotListDoQuery.getSort());
//                SearchHit[] hits1 = searchResponse1.getHits().getHits();
//                for (SearchHit hit : hits1) {
//                    commonMethod(hit, keyList, plotDetailsFewDoList, city, plotListDoQuery.getDistance());
//                }
//            } else if (resLocationInfo == 0) {
//                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//                Integer newFrom = (plotListDoQuery.getPageNum() - 1) * plotListDoQuery.getPageSize();
//                if (oneKM_size > 0) {
//                    newFrom = (int) ((plotListDoQuery.getPageNum() - 1) * plotListDoQuery.getPageSize() - (oneKM_size / plotListDoQuery.getPageSize() + 1) * plotListDoQuery.getPageSize());
//                }
//                SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(newFrom, booleanQueryBuilder, plotListDoQuery.getPageSize(), plotListDoQuery.getKeyword(), city, plotListDoQuery.getSort());
//                SearchHit[] hits1 = searchResponse1.getHits().getHits();
//                for (SearchHit hit : hits1) {
//                    commonMethod(hit, keyList, plotDetailsFewDoList, city, plotListDoQuery.getDistance());
//                }
//            }
//        }

        if (searchResponse != null) {
            SearchHit[] hits = searchResponse.getHits().getHits();

            if (hits.length > 0) {
                for (SearchHit hit : hits) {
                    commonMethod(hit,plotDetailsFewDoList, city, plotListDoQuery.getDistance(), plotListDoQuery);
                }
            }
        }

        plotListDo.setPlotList(plotDetailsFewDoList);
        plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return plotListDo;
    }

    /**
     * 遍历结果
     *
     * @return
     */
    public void commonMethod(SearchHit hit, List<PlotDetailsFewDo> plotDetailsFewDoList, String city, Double distance,PlotListDoQuery plotListDoQuery) {
        String sourceAsString = hit.getSourceAsString();
        PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
        plotDetailsFewDo.setAvgPrice((double) Math.round(plotDetailsFewDo.getAvgPrice()));

//        String nearbyDistance = StringTool.nullToString(plotDetailsFewDo.getArea()) + " " + StringTool.nullToString(plotDetailsFewDo.getTradingArea());
//        String traffic = plotDetailsFewDo.getTrafficInformation();
//        String[] trafficArr = traffic.split("\\$");
//        if (trafficArr.length == 3) {
//            int i = Integer.parseInt(trafficArr[2]);
//            if (i < 1000) {
////                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
//                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
//            } else if (i < 2000) {
//                DecimalFormat df = new DecimalFormat("0.0");
////                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
//                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
//            }
//        }


        String frontName = StringTool.nullToString(plotDetailsFewDo.getArea()) + " " + StringTool.nullToString(plotDetailsFewDo.getTradingArea());
        // 1.默认地铁最近
        String traffic = plotDetailsFewDo.getTrafficInformation();
        // 2.如果有地铁筛选信息，会返回对应的地铁信息
        String trafficWithSubway = nearbyDistanceService.getTrafficWithOneSubwayLine
                (plotDetailsFewDo.getMetroWithPlotsDistance(),plotListDoQuery.getSubwayLineId(),plotListDoQuery.getSubwayStationId());

        if(StringTool.isNotEmpty(trafficWithSubway)) {
            traffic = trafficWithSubway;
        }

        String nearbyDistance = nearbyDistanceService.getNearbyDistanceByTraffic(traffic,frontName);
        plotDetailsFewDo.setSubwayDistanceInfo(traffic);

        // 3.选择附近 的距离
        if (StringTool.isNotEmpty(distance) && distance > 0) {
            if (hit.getSortValues().length == 3) {
                BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[2]);
                String distances = geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                nearbyDistance = "距您" + distances;
            }else if(hit.getSortValues().length == 2){
                BigDecimal geoDis= new BigDecimal((Double) hit.getSortValues()[1]);
                String distances = geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                nearbyDistance = "距您" + distances;
            }
        }

        if (StringTool.isNotEmpty(nearbyDistance)) {
            plotDetailsFewDo.setNearbyDistance(nearbyDistance);
        }

        //增加地铁线选择，地铁站选择不限
//        if(StringTool.isNotEmpty(subwayLineId)){
//            if(StringTool.isNotEmpty(plotDetailsFewDo.getMetroWithPlotsDistance().get(subwayLineId))){
//                trafficArr = plotDetailsFewDo.getMetroWithPlotsDistance().get(subwayLineId).toString().split("\\$");
//                if (trafficArr.length == 3) {
////                    nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
//                    nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
//                }
//            }
//        }

//        if (null != plotDetailsFewDo.getMetroWithPlotsDistance() && key.size() > 0) {
//            Map<Integer, String> map = new HashMap<>();
//            List<Integer> sortDistance = new ArrayList<>();
//            for (int i = 0; i < key.size(); i++) {
//                String stationKey = key.get(i);
//                if (StringTool.isNotEmpty(plotDetailsFewDo.getMetroWithPlotsDistance().get(stationKey))) {
//                    String stationValue = plotDetailsFewDo.getMetroWithPlotsDistance().get(stationKey).toString();
//                    String[] stationValueSplit = stationValue.split("\\$");
//                    Integer stationDis = Integer.valueOf(stationValueSplit[2]);
//                    sortDistance.add(stationDis);
//                    map.put(stationDis, stationKey);
//                }
//            }
//            Integer minDistance = Collections.min(sortDistance);
//            plotDetailsFewDo.setSubwayDistanceInfo(plotDetailsFewDo.getMetroWithPlotsDistance().get(map.get(minDistance)).toString());
//
//            trafficArr = plotDetailsFewDo.getSubwayDistanceInfo().split("\\$");
//            if (trafficArr.length == 3) {
////                nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
//                nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
//
//            }
//        }
        plotDetailsFewDo.setMetroWithPlotsDistance(null);

//        if (StringTool.isNotEmpty(nearbyDistance)) {
//            plotDetailsFewDo.setNearbyDistance(nearbyDistance);
//        }

        //二手房总数
        try {
            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotDetailsFewDo.getId(), city);
            plotDetailsFewDo.setSellHouseTotalNum(Math.toIntExact(plotsEsfRoomCountDomain.getTotalCount()));
        } catch (BaseException e) {
            // logger.error("获取小区下二手房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
            if (e.getCode() == 50301) {
                plotDetailsFewDo.setSellHouseTotalNum(0);
            }
        }
        //租房总数
        try {
            RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotDetailsFewDo.getId(), city);
            plotDetailsFewDo.setRentTotalNum(rentNumListDo.getTotalNum());
        } catch (BaseException e) {
            // logger.error("获取小区下租房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
            if (e.getCode() == 50401) {
                plotDetailsFewDo.setRentTotalNum(0);
            }
        }
        //小区标题图处理
        if (plotDetailsFewDo.getPhoto().length > 0) {
            String titlePhoto = plotDetailsFewDo.getPhoto()[0];
            if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                titlePhoto = "http://s1-qn.bidewu.com/" + titlePhoto + "-dfdo400x300";
            }
            plotDetailsFewDo.setTitlePhoto(titlePhoto);
        }
        plotDetailsFewDoList.add(plotDetailsFewDo);
    }

//    /**
//     * 小区列表含坐标
//     * @param plotListDoQuery
//     * @return
//     */
//    @Override
//    public List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDoQuery plotListDoQuery) {
//        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if (plotListDoQuery.getLat()!=0&& plotListDoQuery.getLon()!=0){
//            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(1600, DistanceUnit.METERS);
//            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
//            sort.unit(DistanceUnit.METERS);
//            sort.order(SortOrder.ASC);
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
//            SearchResponse searchResponse = plotEsDao.queryNearPlotListByLocationAndDistance(boolQueryBuilder, location, sort);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length==10){
//                for (SearchHit hit:hits){
//                    String sourceAsString = hit.getSourceAsString();
//                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
//                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                    plotDetailsFewDoList.add(plotDetailsFewDo);
//                }
//            }else if (hits.length<10&&hits.length>0){
//                for (SearchHit hit:hits){
//                    String sourceAsString = hit.getSourceAsString();
//                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
//                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                    plotDetailsFewDoList.add(plotDetailsFewDo);
//                }
//
//                plotListDoQuery.setPageSize(10-hits.length);
//                PlotListDo plotListDo = plotsRestService.queryPlotListByRequirement(plotListDoQuery);
//                plotDetailsFewDoList.addAll(plotListDo.getPlotList());
//            }else if (hits.length==0){
//                plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
//            }
//        }else {
//            plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
//        }
//        return plotDetailsFewDoList;
//    }


    /**
     * @param plotTop50ListDoQuery
     * @return
     */

    @Override
    public List<PlotTop50Do> getPlotTop50List(PlotTop50ListDoQuery plotTop50ListDoQuery, String city) {
        List<PlotTop50Do> plotTop50Dos = new ArrayList<>();
        int[] isTop = {1};
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId", isTop));
        if (null != plotTop50ListDoQuery.getDistrictId()) {
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId", plotTop50ListDoQuery.getDistrictId()));
        }

        SearchResponse searchResponse = plotEsDao.getPlotTop50List(boolQueryBuilder, plotTop50ListDoQuery.getPageNum(), plotTop50ListDoQuery.getPageSize(), city);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            PlotTop50Do plotTop50Do = JSON.parseObject(sourceAsString, PlotTop50Do.class);

            String nearbyDistance = StringTool.nullToString(plotTop50Do.getArea()) + " " + StringTool.nullToString(plotTop50Do.getTradingArea());
            String traffic = plotTop50Do.getTrafficInformation();
            String[] trafficArr = traffic.split("\\$");
            if (trafficArr.length == 3) {
                DecimalFormat df = new DecimalFormat("0.0");
//                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
            }
            plotTop50Do.setTrafficInformation(nearbyDistance);
            if (plotTop50Do.getPhoto().length > 0) {
                String titlePhoto = plotTop50Do.getPhoto()[0];
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1-qn.bidewu.com/" + titlePhoto + "-dfdo400x300";
                }
                plotTop50Do.setTitlePhoto(titlePhoto);
            }

            //推荐理由
            CommunityReviewDo communityReviewDo = plotsRestService.getReviewById(plotTop50Do.getId(), city);
            plotTop50Do.setRecommendReason(communityReviewDo);


            plotTop50Do.setRecommendBuildTagsName((List<String>) hit.getSourceAsMap().get("recommendBuildTagsName"));
            plotTop50Do.setLabel((List<String>) hit.getSourceAsMap().get("label"));

            List<String> tagsName = new ArrayList<>();

            PlotMarketDo plotMarketDo = plotsMarketService.queryPlotMarketByPlotId(plotTop50Do.getId());

            PlotMarketDomain plotMarketDomain = null;
            if (null != plotMarketDo) {
                plotMarketDomain = new PlotMarketDomain();
                org.springframework.beans.BeanUtils.copyProperties(plotMarketDo, plotMarketDomain);
                plotMarketDomain.setDistrictName(plotTop50Do.getArea());
                plotTop50Do.setPlotMarketDomain(plotMarketDomain);
            }

            if (null != plotMarketDo) {
                tagsName.add(plotTop50Do.getArea()+"热度榜第"+plotMarketDo.getTotalSort()+"名");
            }

            List<String> recommendTags = (List<String>) hit.getSourceAsMap().get("recommendBuildTagsName");
            List<String> label = (List<String>) hit.getSourceAsMap().get("label");
//                List<String> districtHotList = (List<String>) searchHit.getSourceAsMap().get("districtHotSort");
            if(StringTool.isNotEmpty(recommendTags) && recommendTags.size() > 0){
                tagsName.addAll(recommendTags);
            }
            if(StringTool.isNotEmpty(label) && label.size() > 0){
                tagsName.addAll(label);
            }
            String tagName = org.apache.commons.lang3.StringUtils.join(tagsName, " ");
            plotTop50Do.setTagsName(tagName.trim());



            //plotTop50Do.setDistrictHotSort(Integer.valueOf(hit.getSourceAsMap().get("districtHotSort")==null?"0":hit.getSourceAsMap().get("districtHotSort").toString()));

//            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(plotTop50Do.getId(), CityUtils.getCity());
//            if (plotsEsfRoomCountDomain.getTotalCount() != null) {
//                plotTop50Do.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
//            } else {
//                plotTop50Do.setHouseCount(0);
//            }

            plotTop50Dos.add(plotTop50Do);
        }
        return plotTop50Dos;
    }

    @Override
    public List<PlotDetailsDo> getPlotByRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        List<PlotDetailsDo> list = new ArrayList<>();
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        //组装条件
        //区域
        if (null != userFavoriteConditionDoQuery.getDistrictId() && userFavoriteConditionDoQuery.getDistrictId().length > 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", userFavoriteConditionDoQuery.getDistrictId()));
        }
        //户型
        if (null != userFavoriteConditionDoQuery.getLayoutId() && userFavoriteConditionDoQuery.getLayoutId().length > 0) {
//            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("house",QueryBuilders.termsQuery("room",userFavoriteConditionDoQuery.getLayoutId()),ScoreMode.None));

            bqb.must(QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()));

        }
        //价格
        if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice()) {
//            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("house",QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice()*0.9)
//                    .lte(userFavoriteConditionDoQuery.getEndPrice()*1.1),ScoreMode.None));

            bqb.must(QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice() * 0.9).lte(userFavoriteConditionDoQuery.getEndPrice() * 1.1));

        } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null == userFavoriteConditionDoQuery.getEndPrice()) {
            //booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("house",QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice()*0.9),ScoreMode.None));

            bqb.must(QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice() * 0.9));
        }

        booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, bqb, ScoreMode.None));
        //二手房个数
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("house_count").gt(0));

        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        SearchResponse plotByRecommendCondition = plotEsDao.getPlotByRecommendCondition(booleanQueryBuilder, scrip, city);
        SearchHit[] hits = plotByRecommendCondition.getHits().getHits();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                PlotDetailsDo plotDetailsDo = JSON.parseObject(sourceAsString, PlotDetailsDo.class);

                if (plotDetailsDo.getPhoto().length > 0) {
                    String titlePhoto = plotDetailsDo.getPhoto()[0];
                    if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                        titlePhoto = "http://s1-qn.bidewu.com/" + titlePhoto + "-dfdo400x300";
                    }
                    plotDetailsDo.setTitlePhoto(titlePhoto);
                }


                list.add(plotDetailsDo);
            }
        }
        return list;
    }

    @Override
    public PlotDetailsDo queryPlotByPlotId(String PlotId, String city) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("id",PlotId));

        SearchResponse searchResponse = plotEsDao.queryPlotByPlotId(boolQueryBuilder, city);
        PlotDetailsDo plotDetailsDo = new PlotDetailsDo();
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            String sourceAsString = hits[0].getSourceAsString();
            plotDetailsDo = JSON.parseObject(sourceAsString, PlotDetailsDo.class);
        }
        return plotDetailsDo;
    }

    @Override
    public JSONArray getFoldLineInfo(String newcode, String districtId, Integer type) {

        List<ReportPipelineRecordEveryMonth> newcodeInfo = reportPipelineRecordEveryMonthMapper.selectFoldLineDistrictInfo(newcode,0);
        List<ReportPipelineRecordEveryMonth> districtInfo = reportPipelineRecordEveryMonthMapper.selectFoldLineDistrictInfo(districtId,type);
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        List newcodePrice = new ArrayList();
        List districtPrice = new ArrayList();
        List newcodeMonth = new ArrayList();
        List districtMonth = new ArrayList();
        Map newcodeResponse = new HashMap();
        Map districtResponse = new HashMap();
        String newcodeRindratio = "0%";
        String districtRindratio = "0%";
        String plotName = "";
        String districtName = "";
        if (null!=newcodeInfo&&newcodeInfo.size()>0){
            for (ReportPipelineRecordEveryMonth newcodeEntity :newcodeInfo){
                newcodePrice.add(newcodeEntity.getAvgPrice());
                newcodeMonth.add(formatter.format(newcodeEntity.getCreateTime()));

            }

            newcodeRindratio = String.format("%.2f", (newcodeInfo.get(newcodeInfo.size()-1).getRingRatio())*100)+"%";
            plotName = newcodeInfo.get(newcodeInfo.size()-1).getDisplayName();
        }

        if (null!=districtInfo&&districtInfo.size()>0){
            for (ReportPipelineRecordEveryMonth districtEntity :districtInfo){
                districtPrice.add(districtEntity.getAvgPrice());
                districtMonth.add(formatter.format(districtEntity.getCreateTime()));
            }
            districtRindratio = String.format("%.2f", (districtInfo.get(districtInfo.size()-1).getRingRatio())*100)+"%";
            districtName = districtInfo.get(districtInfo.size()-1).getDisplayName();
        }


        newcodeResponse.put("name",plotName);
        newcodeResponse.put("price",newcodePrice);
        newcodeResponse.put("date",newcodeMonth);
        newcodeResponse.put("rindRatio",newcodeRindratio);


        districtResponse.put("name",districtName);
        districtResponse.put("price",districtPrice);
        districtResponse.put("date",districtMonth);
        districtResponse.put("rindRatio",districtRindratio);

        jsonArray.add(JSON.toJSON(newcodeResponse));
        jsonArray.add(JSON.toJSON(districtResponse));

        return jsonArray;
    }

    @Override
    public CommunityReviewDo getReviewById(Integer plotId, String city) {
        CommunityReviewDo communityReviewDo = new CommunityReviewDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(new ParentIdQueryBuilder("review",plotId.toString()));

        SearchResponse searchResponse = plotEsDao.getReviewById(boolQueryBuilder,city);

        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            String sourceAsString = hits[0].getSourceAsString();
            communityReviewDo = JSON.parseObject(sourceAsString, CommunityReviewDo.class);
        }
        return communityReviewDo;
    }


}
