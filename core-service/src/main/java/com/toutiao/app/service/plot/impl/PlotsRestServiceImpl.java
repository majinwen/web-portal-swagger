package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.plot.PlotsRestService;

import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;

import com.toutiao.web.service.map.MapService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.*;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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


    /**
     * 小区详情信息
     * @param plotId
     * @return
     */
    @Override
    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId) {
        String details = "";
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = plotEsDao.queryPlotDetail(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            PlotDetailsDo plotDetailsDo = new PlotDetailsDo();
            for (SearchHit searchHit : hits) {
                details = searchHit.getSourceAsString();
            }
            if (StringUtils.isNotEmpty(details))
            {
                UserBasic userBasic = new UserBasic();

                plotDetailsDo = JSON.parseObject(details,PlotDetailsDo.class);

                if(StringTool.isNotEmpty(userBasic)){
                    PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                    plotIsFavoriteDoQuery.setUserId(Integer.valueOf(userBasic.getUserId()));
                    plotIsFavoriteDoQuery.setPlotId(plotDetailsDo.getId());
                    Boolean isFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
                    plotDetailsDo.setIsFavorite(isFavorite);
                }

                if ("商电".equals(plotDetailsDo.getElectricSupply())){
                    plotDetailsDo.setElectricFee("1.33");
                }else {
                    plotDetailsDo.setElectricFee("0.48");
                }
                if ("商水".equals(plotDetailsDo.getWaterSupply())){
                    plotDetailsDo.setWaterFee("6");
                }else {
                    plotDetailsDo.setWaterFee("5");
                }
                if ("0".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("未知");
                }
                if ("1".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("集中供暖");
                }
                if ("2".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("自供暖");
                }
                if (null!=plotDetailsDo.getElevator() &&"1".equals(plotDetailsDo.getElevator()))
                {
                    plotDetailsDo.setHasElevator("有");
                }
                if(null!=plotDetailsDo.getElevator() && "2".equals(plotDetailsDo.getElevator()))
                {
                    plotDetailsDo.setHasElevator("无");
                }
                return plotDetailsDo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (details.isEmpty())
        {
            throw  new  BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_DETAILS_NOT_FOUND);
        }
        return null;
    }

    /**
     * 获取小区周边配套
     * @param plotId
     * @return
     */
    @Override
    public PlotTrafficDo queryPlotDataInfo(Integer plotId){
            MapInfo mapInfo = new MapInfo();
            PlotTrafficDo plotTrafficDo=new PlotTrafficDo();
            mapInfo =  mapService.getMapInfo(plotId);
            if (mapInfo==null)
            {
                throw  new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_TRAFFIC_NOT_FOUND);
            }
            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            //获取小区交通
            JSONObject businfo= (JSONObject) datainfo.get("gongjiao");
            plotTrafficDo.setBusStation(businfo.get("name").toString());
            plotTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
            //获取地铁和环线位置
            PlotDetailsDo plotDetailsDo = plotsRestService.queryPlotDetailByPlotId(plotId);
            if (!"".equals(plotDetailsDo.getTrafficInformation()))
            {  String []  trafficInfo=plotDetailsDo.getTrafficInformation().split("\\$");
                plotTrafficDo.setSubwayStation(trafficInfo[1]);
                plotTrafficDo.setSubwayLine(trafficInfo[0]);
                plotTrafficDo.setSubwayDistance(Double.valueOf(trafficInfo[2]));
            }
            if (null!=plotDetailsDo.getRingRoadName() && !"".equals(plotDetailsDo.getRingRoadName()))
            {
                plotTrafficDo.setRingRoadName(plotDetailsDo.getRingRoadName());
            }
            if (null!=plotDetailsDo.getRingRoadDistance())
            {
                plotTrafficDo.setRingRoadDistance(Double.valueOf(plotDetailsDo.getRingRoadDistance().toString()));
            }
            return plotTrafficDo;
    }

    /**
     * 小区收藏列表
     * @param list
     * @return
     */
    @Override
    public PlotFavoriteListDo queryPlotListByPlotIdList(List list, Integer pageNum , Integer size) {
        PlotFavoriteListDo plotFavoriteListDo = new PlotFavoriteListDo();
        List<UserFavoritePlotDo> list1 = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("id",list));
        SearchResponse searchResponse = plotEsDao.queryPlotListByPlotIdList(boolQueryBuilder, (pageNum-1)*size, size);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                UserFavoritePlotDo userFavoritePlotDo = JSON.parseObject(sourceAsString, UserFavoritePlotDo.class);
                list1.add(userFavoritePlotDo);
            }
            plotFavoriteListDo.setData(list1);
            plotFavoriteListDo.setTotalNum(searchResponse.getHits().getTotalHits());
        }
        return plotFavoriteListDo;
    }

    /**
     * 附近小区
     * @param lat
     * @param lon
     * @param plotId
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId){
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
            SearchResponse searchResponse = plotEsDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map<String, Object> source = hit.getSource();
                    PlotDetailsFewDo plotDetailsFewDo = PlotDetailsFewDo.class.newInstance();
                    BeanUtils.populate(plotDetailsFewDo, source);
                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                    plotDetailsFewDoList.add(plotDetailsFewDo);
                }
                return plotDetailsFewDoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 小区列表条件筛选
     * @param plotListDoQuery
     * @return
     */
    @Override
    public PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery) {
        String key = "";
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //关键字
        if (StringTool.isNotEmpty(plotListDoQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //区域id
        if (StringTool.isNotEmpty(plotListDoQuery.getDistrictId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId", plotListDoQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(plotListDoQuery.getAreaId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("tradingAreaId", plotListDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayLineId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", plotListDoQuery.getSubwayLineId()));
            key = String.valueOf(plotListDoQuery.getSubwayLineId());
        }
        //地铁站id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayStationId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("metroStationId", plotListDoQuery.getSubwayStationId()));
            key = key+"$"+ plotListDoQuery.getSubwayStationId();
        }
        //均价
        if (StringTool.isNotEmpty(plotListDoQuery.getBeginPrice())&&StringTool.isNotEmpty(plotListDoQuery.getEndPrice())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()).lte(plotListDoQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(plotListDoQuery.getHouseYearId())){
            String[] age = plotListDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(plotListDoQuery.getLabelId())){
            Integer[] labelId = plotListDoQuery.getLabelId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("labelId",labelId));
        }


//        if ((StringTool.isNotEmpty(nearbyPlotsDoQuery.getHouseAreaSize()))){
//            String[] houseArea = nearbyPlotsDoQuery.getHouseAreaSize().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
//            BoolQueryBuilder QueryBuilder = QueryBuilders.boolQuery();
//            for (int i = 0; i < houseArea.length; i = i + 2) {
//                if (i + 1 > houseArea.length) {
//                    break;
//                }
//                BoolQueryBuilder areaSize = QueryBuilder.should(JoinQueryBuilders
//                        .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseArea[i]).lte(houseArea[i + 1]), ScoreMode.None));
//                boolQueryBuilder.must(areaSize);



//            }
//
//        }

        //房源面积大小
        if(plotListDoQuery.getBeginArea()!=null && plotListDoQuery.getEndArea()!=null){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(plotListDoQuery.getBeginArea()).lte(plotListDoQuery.getEndArea()), ScoreMode.None));

        }else if(plotListDoQuery.getBeginArea()!=null && plotListDoQuery.getEndArea()==null){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(plotListDoQuery.getBeginArea()), ScoreMode.None));
        }else if(plotListDoQuery.getBeginArea()==null && plotListDoQuery.getEndArea()!=null){

            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .lte(plotListDoQuery.getEndArea()), ScoreMode.None));
        }

        Integer from = 0;
        //分页起始位置
        if (StringTool.isNotEmpty(plotListDoQuery.getPageNum())&& plotListDoQuery.getPageNum()>1&&StringTool.isNotEmpty(plotListDoQuery.getPageSize())&& plotListDoQuery.getPageSize()>0){
            from = (plotListDoQuery.getPageNum()-1)* plotListDoQuery.getPageSize();
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
        SearchResponse searchResponse = plotEsDao.queryPlotListByRequirement(from, boolQueryBuilder, levelSort, plotScoreSort, plotListDoQuery.getPageSize());
        if (searchResponse!=null){
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                plotDetailsFewDo.setKey(key);

                //二手房总数
                try {
                    PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotDetailsFewDo.getId());
                    plotDetailsFewDo.setSellHouseTotalNum(Math.toIntExact(plotsEsfRoomCountDomain.getTotalCount()));
                }catch (BaseException e){
                   // logger.error("获取小区下二手房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
                    if (e.getCode()==50301){
                        plotDetailsFewDo.setSellHouseTotalNum(0);
                    }
                }
                //租房总数
                try {
                    RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotDetailsFewDo.getId());
                    plotDetailsFewDo.setRentTotalNum(rentNumListDo.getTotalNum());
                }catch (BaseException e){
                   // logger.error("获取小区下租房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
                    if (e.getCode()==50401){
                        plotDetailsFewDo.setRentTotalNum(0);
                    }
                }
                plotDetailsFewDoList.add(plotDetailsFewDo);
            }
        }
        plotListDo.setPlotList(plotDetailsFewDoList);
        plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return plotListDo;
    }

    /**
     * 小区列表含坐标
     * @param plotListDoQuery
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDoQuery plotListDoQuery) {
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (plotListDoQuery.getLat()!=0&& plotListDoQuery.getLon()!=0){
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(1600, DistanceUnit.METERS);
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);
            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
            SearchResponse searchResponse = plotEsDao.queryNearPlotListByLocationAndDistance(boolQueryBuilder, location, sort);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length==10){
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                    plotDetailsFewDoList.add(plotDetailsFewDo);
                }
            }else if (hits.length<10&&hits.length>0){
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                    plotDetailsFewDoList.add(plotDetailsFewDo);
                }

                plotListDoQuery.setPageSize(10-hits.length);
                PlotListDo plotListDo = plotsRestService.queryPlotListByRequirement(plotListDoQuery);
                plotDetailsFewDoList.addAll(plotListDo.getPlotList());
            }else if (hits.length==0){
                plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
            }
        }else {
            plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
        }
        return plotDetailsFewDoList;
    }

}
