//package com.toutiao.app.service.plot.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.toutiao.app.domain.MapInfo;
//import com.toutiao.app.domain.plot.PlotDetailsDo;
//import com.toutiao.app.domain.plot.PlotDetailsFewDo;
//import com.toutiao.app.domain.plot.PlotListDo;
//import com.toutiao.app.service.plot.PlotsRestService;
//
//import com.toutiao.web.common.util.StringTool;
//import com.toutiao.web.common.util.StringUtil;
//import com.toutiao.web.dao.mapper.officeweb.MapInfoMapper;
//import com.toutiao.web.dao.sources.beijing.AreaMap;
//import com.toutiao.web.dao.sources.beijing.DistrictMap;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.lucene.search.join.ScoreMode;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.common.unit.DistanceUnit;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.join.query.JoinQueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.sort.*;
//import org.postgresql.util.PGobject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Service
//public class PlotsRestServiceImpl implements PlotsRestService {
//    @Value("${distance}")
//    private Double distance;
//    @Value("${plot.child.type}")p
//    private String childType;
//    @Autowired
//    private AppPlotDao appPlotDao;
//    @Autowired
//    private MapInfoMapper mapInfoMapper;
//    @Autowired
//    private PlotsRestService lotService;
//
//
//    /**
//     * 小区详情信息
//     * @param plotId
//     * @return
//     */
//    @Override
//    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId) {
//        try {
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
//            SearchResponse searchResponse = appPlotDao.queryPlotDetail(boolQueryBuilder);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            Map<String, Object> source = hits[0].getSource();
//            PlotDetailsDo plotDetailsDo = PlotDetailsDo.class.newInstance();
//            BeanUtils.populate(plotDetailsDo, source);
//            if ("商电".equals(plotDetailsDo.getElectricSupply())){
//                plotDetailsDo.setElectricFee("1.33");
//            }else {
//                plotDetailsDo.setElectricFee("0.48");
//            }
//            if ("商水".equals(plotDetailsDo.getWaterSupply())){
//                plotDetailsDo.setWaterFee("6");
//            }else {
//                plotDetailsDo.setWaterFee("5");
//            }
//            if ("0".equals(plotDetailsDo.getHeatingMode())){
//                plotDetailsDo.setHeatingMode("未知");
//            }
//            if ("1".equals(plotDetailsDo.getHeatingMode())){
//                plotDetailsDo.setHeatingMode("集中供暖");
//            }
//            if ("2".equals(plotDetailsDo.getHeatingMode())){
//                plotDetailsDo.setHeatingMode("自供暖");
//            }
//            return plotDetailsDo;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取小区周边配套
//     * @param plotId
//     * @return
//     */
//    @Override
//    public JSONObject queryPlotDataInfo(Integer plotId) {
//        try {
//            MapInfo mapInfo = new MapInfo();
//            com.toutiao.web.dao.entity.officeweb.MapInfo result = mapInfoMapper.selectByNewCode(plotId);
//            BeanUtils.copyProperties(mapInfo,result);
//            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
//            //获取地铁和环线位置
//            PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotId);
////            String distance=plotDetailsDo.
//
//            return datainfo;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 附近小区
//     * @param lat
//     * @param lon
//     * @param plotId
//     * @return
//     */
//    @Override
//    public List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId){
//        try {
//            List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            //从该坐标查询距离为distance内的小区
//            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
//            //按照距离排序由近到远并获取小区之间的距离
//            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
//            sort.unit(DistanceUnit.METERS);
//            sort.order(SortOrder.ASC);
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
//            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", plotId));
//            SearchResponse searchResponse = plotEsDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length>0){
//                for (SearchHit hit:hits){
//                    Map<String, Object> source = hit.getSource();
//                    PlotDetailsFewDo plotDetailsFewDo = PlotDetailsFewDo.class.newInstance();
//                    BeanUtils.populate(plotDetailsFewDo, source);
//                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                    plotDetailsFewDoList.add(plotDetailsFewDo);
//                }
//                return plotDetailsFewDoList;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 小区列表条件筛选
//     * @param plotListDo
//     * @return
//     */
//    @Override
//    public List<PlotDetailsFewDo> queryPlotListByRequirement(PlotListDo plotListDo) {
//        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//        //关键字
//        if (StringTool.isNotEmpty(plotListDo.getKeyword())){
//            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//            if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDo.getKeyword()))){
//                queryBuilder
//                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()))
//                        .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_smart"))
//                        .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()).analyzer("ik_smart").boost(2))
//                        .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()).analyzer("ik_smart"));
//            }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListDo.getKeyword()))){
//                queryBuilder
//                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()))
//                        .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_smart"))
//                        .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()).analyzer("ik_smart"))
//                        .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()).analyzer("ik_max_word").boost(2));
//            }else {
//                queryBuilder
//                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()).boost(2))
//                        .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_max_word"))
//                        .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()))
//                        .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()));
//            }
//            boolQueryBuilder.must(queryBuilder);
//        }
//        //区域id
//        if (StringTool.isNotEmpty(plotListDo.getDistrictId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("areaId",plotListDo.getDistrictId().split(",")));
//        }
//        //商圈id
//        if (StringTool.isNotEmpty(plotListDo.getAreaId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("tradingAreaId",plotListDo.getAreaId().split(",")));
//        }
//        //地铁线id
//        if (StringTool.isNotEmpty(plotListDo.getSubwayLineId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId",plotListDo.getSubwayLineId().split(",")));
//        }
//        //地铁站id
//        if (StringTool.isNotEmpty(plotListDo.getSubwayStationId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("metroStationId",plotListDo.getSubwayStationId().split(",")));
//        }
//        //均价
//        if (StringTool.isNotEmpty(plotListDo.getBeginPrice())&&StringTool.isNotEmpty(plotListDo.getEndPrice())){
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDo.getBeginPrice()).lte(plotListDo.getEndPrice()));
//        }
//        //楼龄
//        if (StringTool.isNotEmpty(plotListDo.getAge())){
//            String[] age = plotListDo.getAge().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
//                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
//                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
//        }
//        //标签
//        if (StringTool.isNotEmpty(plotListDo.getLabelId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("labelId",plotListDo.getLabelId().split(",")));
//        }
//        //物业类型
//        if (StringTool.isNotEmpty(plotListDo.getPropertyTypeId())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("propertyType",plotListDo.getPropertyTypeId()));
//        }
//        //房源面积大小
//        if ((StringTool.isNotEmpty(plotListDo.getHouseAreaSize()))){
//            BoolQueryBuilder QueryBuilder = QueryBuilders.boolQuery();
//            String[] houseSize = plotListDo.getHouseAreaSize().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
//            for (int i = 0; i < houseSize.length; i = i + 2) {
//                if (i + 1 > houseSize.length) {
//                    break;
//                }
//                BoolQueryBuilder areaSize = QueryBuilder.should(JoinQueryBuilders
//                        .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseSize[i]).lte(houseSize[i + 1]), ScoreMode.None));
//                boolQueryBuilder.must(areaSize);
//            }
//
//        }
//        //电梯
//        if (StringTool.isNotEmpty(plotListDo.getElevatorFlag())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("elevator",plotListDo.getElevatorFlag().split(",")));
//        }
//        //建筑类型
//        if (StringTool.isNotEmpty(plotListDo.getBuildingType())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("architectureTypeId",plotListDo.getBuildingType().split(",")));
//        }
//        //楼盘特色
//        if (StringTool.isNotEmpty(plotListDo.getBuildingFeature())){
//            boolQueryBuilder.must(QueryBuilders.termsQuery("villageCharacteristics",plotListDo.getBuildingFeature().split(",")));
//        }
//        Integer from = 0;
//        //分页起始位置
//        if (StringTool.isNotEmpty(plotListDo.getPageNum())&&plotListDo.getPageNum()>1&&StringTool.isNotEmpty(plotListDo.getSize())&&plotListDo.getSize()>0){
//            from = (plotListDo.getPageNum()-1)*plotListDo.getSize();
//        }
//        if (StringTool.isEmpty(plotListDo.getSize())||plotListDo.getSize()<1){
//            plotListDo.setSize(10);
//        }
//
//        //是否上架
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//
//        //是否删除
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
//
//        //级别排序
//        FieldSortBuilder levelSort = SortBuilders.fieldSort("level").order(SortOrder.ASC);
//
//        //小区分数排序
//        FieldSortBuilder plotScoreSort = SortBuilders.fieldSort("plotScore").order(SortOrder.DESC);
//
//        SearchResponse searchResponse = plotEsDao.queryPlotListByRequirement(from, boolQueryBuilder, levelSort, plotScoreSort,plotListDo.getSize());
//        if (searchResponse!=null){
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            for (SearchHit hit:hits){
//                String sourceAsString = hit.getSourceAsString();
//                PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
//                plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                plotDetailsFewDoList.add(plotDetailsFewDo);
//            }
//        }
//        return plotDetailsFewDoList;
//    }
//
//    /**
//     * 小区列表含坐标
//     * @param plotListDo
//     * @return
//     */
//    @Override
//    public List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDo plotListDo) {
//        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if (plotListDo.getLat()!=0&&plotListDo.getLon()!=0){
//            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDo.getLat(), plotListDo.getLon()).distance(1600, DistanceUnit.METERS);
//            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", plotListDo.getLat(), plotListDo.getLon());
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
//                plotListDo.setSize(10-hits.length);
//                List<PlotDetailsFewDo> plotDetailsFewDos = plotsRestService.queryPlotListByRequirement(plotListDo);
//                plotDetailsFewDoList.addAll(plotDetailsFewDos);
//            }else if (hits.length==0){
//                plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDo);
//            }
//        }else {
//            plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDo);
//        }
//        return plotDetailsFewDoList;
//    }
//
//}
