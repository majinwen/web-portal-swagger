package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.service.plot.PlotsRestService;

import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;

import com.toutiao.web.service.map.MapService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.*;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlotsRestServiceImpl implements PlotsRestService {
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


    /**
     * 小区详情信息
     * @param plotId
     * @return
     */
    @Override
    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId) {
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = plotEsDao.queryPlotDetail(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            String details = "";
            PlotDetailsDo plotDetailsDo = new PlotDetailsDo();
            for (SearchHit searchHit : hits) {
                details = searchHit.getSourceAsString();
            }
            if (StringUtils.isNotEmpty(details))
            {
                plotDetailsDo = JSON.parseObject(details,PlotDetailsDo.class);
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
            }

            return plotDetailsDo;
        } catch (Exception e) {
            e.printStackTrace();
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
    public PlotDetailsFewDomain queryPlotListByPlotIdList(List list, Integer pageNum , Integer size) {
        PlotDetailsFewDomain plotDetailsFewDomain = new PlotDetailsFewDomain();
        List<PlotDetailsFewDo> list1 = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("id",list));
        SearchResponse searchResponse = plotEsDao.queryPlotListByPlotIdList(boolQueryBuilder, (pageNum-1)*size, size);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                list1.add(plotDetailsFewDo);
            }
            plotDetailsFewDomain.setNearbyPlots(list1);
            plotDetailsFewDomain.setTotals(searchResponse.getHits().getTotalHits());
        }
        return plotDetailsFewDomain;
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
     * @param plotListQuery
     * @return
     */
    @Override
    public PlotListDo queryPlotListByRequirement(PlotListQuery plotListQuery) {
        String key = "";
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //关键字
        if (StringTool.isNotEmpty(plotListQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("tradingArea",plotListQuery.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("tradingArea",plotListQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("rc", plotListQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("area", plotListQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea",plotListQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //区域id
        if (StringTool.isNotEmpty(plotListQuery.getDistrictId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId",plotListQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(plotListQuery.getAreaId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("tradingAreaId",plotListQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(plotListQuery.getSubwayLineId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId",plotListQuery.getSubwayLineId()));
            key = String.valueOf(plotListQuery.getSubwayLineId());
        }
        //地铁站id
        if (StringTool.isNotEmpty(plotListQuery.getSubwayStationId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("metroStationId",plotListQuery.getSubwayStationId()));
            key = key+"$"+plotListQuery.getSubwayStationId();
        }
        //均价
        if (StringTool.isNotEmpty(plotListQuery.getBeginPrice())&&StringTool.isNotEmpty(plotListQuery.getEndPrice())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListQuery.getBeginPrice()).lte(plotListQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(plotListQuery.getHouseYearId())){
            String[] age = plotListQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(plotListQuery.getLabelId())){
            Integer[] labelId = plotListQuery.getLabelId();
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
        if(plotListQuery.getBeginArea()!=0 && plotListQuery.getEndArea()!=0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(plotListQuery.getBeginArea()).lte(plotListQuery.getEndArea()), ScoreMode.None));

        }else if(plotListQuery.getBeginArea()!=0 && plotListQuery.getEndArea()==0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(plotListQuery.getBeginArea()), ScoreMode.None));
        }else if(plotListQuery.getBeginArea()==0 && plotListQuery.getEndArea()!=0){

            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .lte(plotListQuery.getEndArea()), ScoreMode.None));
        }

        Integer from = 0;
        //分页起始位置
        if (StringTool.isNotEmpty(plotListQuery.getPageNum())&&plotListQuery.getPageNum()>1&&StringTool.isNotEmpty(plotListQuery.getPageSize())&&plotListQuery.getPageSize()>0){
            from = (plotListQuery.getPageNum()-1)*plotListQuery.getPageSize();
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
        SearchResponse searchResponse = plotEsDao.queryPlotListByRequirement(from, boolQueryBuilder, levelSort, plotScoreSort,plotListQuery.getPageSize());
        if (searchResponse!=null){
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                plotDetailsFewDo.setKey(key);
                plotDetailsFewDoList.add(plotDetailsFewDo);
            }
        }
        plotListDo.setPlotList(plotDetailsFewDoList);
        plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return plotListDo;
    }

    /**
     * 小区列表含坐标
     * @param plotListQuery
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListQuery plotListQuery) {
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (plotListQuery.getLat()!=0&&plotListQuery.getLon()!=0){
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListQuery.getLat(), plotListQuery.getLon()).distance(1600, DistanceUnit.METERS);
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", plotListQuery.getLat(), plotListQuery.getLon());
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

                plotListQuery.setPageSize(10-hits.length);
                PlotListDo plotListDo = plotsRestService.queryPlotListByRequirement(plotListQuery);
                plotDetailsFewDoList.addAll(plotListDo.getPlotList());
            }else if (hits.length==0){
                plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListQuery).getPlotList();
            }
        }else {
            plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListQuery).getPlotList();
        }
        return plotDetailsFewDoList;
    }

}
