package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.AppPlotDao;
import com.toutiao.app.domain.MapInfo;
import com.toutiao.app.domain.Plot.PlotDetailsDo;
import com.toutiao.app.domain.Plot.PlotDetailsFewDo;
import com.toutiao.app.domain.Plot.PlotListDo;
import com.toutiao.app.service.plot.PlotsRestService;

import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.mapper.officeweb.MapInfoMapper;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlotsRestServiceImpl implements PlotsRestService {
    @Value("${distance}")
    private Double distance;
    @Value("${plot.child.type}")
    private String childType;
    @Autowired
    private AppPlotDao appPlotDao;
    @Autowired
    private MapInfoMapper mapInfoMapper;
    @Autowired
    private PlotsRestService appPlotService;



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
            SearchResponse searchResponse = appPlotDao.queryPlotDetail(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            Map<String, Object> source = hits[0].getSource();
            PlotDetailsDo plotDetailsDo = PlotDetailsDo.class.newInstance();
            BeanUtils.populate(plotDetailsDo, source);
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
    public JSONObject queryPlotDataInfo(Integer plotId) {
        try {
            MapInfo mapInfo = new MapInfo();
            com.toutiao.web.dao.entity.officeweb.MapInfo result = mapInfoMapper.selectByNewCode(plotId);
            BeanUtils.copyProperties(mapInfo,result);
            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            //获取地铁和环线位置
            PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotId);
            plotDetailsDo.getTrafficInformation().split("$");

            return datainfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 附近小区
     * @param lat
     * @param lon
     * @param plotId
     * @return
     */
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
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", plotId));
            SearchResponse searchResponse = appPlotDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort);
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
     * 小区列表
     * @param plotListDo
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryPlotListByRequirement(PlotListDo plotListDo) {
        try {
            String key = "";
            FieldSortBuilder avgPriceSort = null;
            BoolQueryBuilder queryBuilder = null;
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();

            //关键字
            if (StringTool.isNotEmpty(plotListDo.getKeyword())){
                if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDo.getKeyword()))){
                    queryBuilder = QueryBuilders.boolQuery()
                            .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()))
                            .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_smart"))
                            .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()).analyzer("ik_smart").boost(2))
                            .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()).analyzer("ik_smart"));
                }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListDo.getKeyword()))){
                    queryBuilder = QueryBuilders.boolQuery()
                            .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()))
                            .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_smart"))
                            .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()).analyzer("ik_smart"))
                            .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()).analyzer("ik_max_word").boost(2));
                }else {
                    queryBuilder = QueryBuilders.boolQuery()
                            .should(QueryBuilders.matchQuery("rc_accurate", plotListDo.getKeyword()).boost(2))
                            .should(QueryBuilders.matchQuery("rc", plotListDo.getKeyword()).analyzer("ik_max_word"))
                            .should(QueryBuilders.matchQuery("area", plotListDo.getKeyword()))
                            .should(QueryBuilders.matchQuery("tradingArea",plotListDo.getKeyword()));
                }
                boolQueryBuilder.must(queryBuilder);
            }
            //区域id
            if (StringTool.isNotEmpty(plotListDo.getDistrictId())){
                boolQueryBuilder.must(QueryBuilders.termQuery("areaId",plotListDo.getDistrictId()));
            }
            //商圈id
            if (StringTool.isNotEmpty(plotListDo.getAreaId())){
                boolQueryBuilder.must(QueryBuilders.termQuery("tradingAreaId",plotListDo.getAreaId()));
            }
            //地铁线id
            if (StringTool.isNotEmpty(plotListDo.getSubwayLineId())){
                boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId",plotListDo.getSubwayLineId()));
                key = plotListDo.getSubwayLineId();
            }
            //地铁站id
            if (StringTool.isNotEmpty(plotListDo.getSubwayStationId())){
                boolQueryBuilder.must(QueryBuilders.termQuery("metroStationId",plotListDo.getSubwayStationId()));
                key = plotListDo.getSubwayLineId()+"$"+plotListDo.getSubwayStationId();
            }
            //均价
            if (StringTool.isNotEmpty(plotListDo.getBeginPrice())&&StringTool.isNotEmpty(plotListDo.getEndPrice())){
                boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDo.getBeginPrice()).lte(plotListDo.getEndPrice()));
            }
            //楼龄
            if (StringTool.isNotEmpty(plotListDo.getAge())){
                String[] age = plotListDo.getAge().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
                boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
            }
            //物业类型
            if (StringTool.isNotEmpty(plotListDo.getPropertyTypeId())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("propertyType",plotListDo.getPropertyTypeId()));
            }
            //房源面积大小
            if ((StringTool.isNotEmpty(plotListDo.getHouseAreaSize()))){
                BoolQueryBuilder QueryBuilder = QueryBuilders.boolQuery();
                String[] houseSize = plotListDo.getHouseAreaSize().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
                for (int i = 0; i < houseSize.length; i = i + 2) {
                    if (i + 1 > houseSize.length) {
                        break;
                    }
                    BoolQueryBuilder areaSize = QueryBuilder.should(JoinQueryBuilders
                            .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseSize[i]).lte(houseSize[i + 1]), ScoreMode.None));
                    boolQueryBuilder.must(areaSize);
                }

            }
            //电梯
            if (StringTool.isNotEmpty(plotListDo.getElevatorFlag())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("elevator",plotListDo.getElevatorFlag().split(",")));
            }
            //建筑类型
            if (StringTool.isNotEmpty(plotListDo.getBuildingType())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("architectureTypeId",plotListDo.getBuildingType().split(",")));
            }
            //楼盘特色
            if (StringTool.isNotEmpty(plotListDo.getBuildingFeature())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("villageCharacteristics",plotListDo.getBuildingFeature().split(",")));
            }
            //均价排序(价格由高到低)
            if (StringTool.isNotEmpty(plotListDo.getSortId())&&"1".equals(plotListDo.getSortId())){
                avgPriceSort = SortBuilders.fieldSort("avgPrice").order(SortOrder.DESC);
            }
            //均价排序(价格由低到高)
            if (StringTool.isNotEmpty(plotListDo.getSortId())&&"2".equals(plotListDo.getSortId())){
                avgPriceSort = SortBuilders.fieldSort("avgPrice").order(SortOrder.ASC);
            }
            //分页
            if (!StringTool.isNotEmpty(plotListDo.getFrom())){
                Integer from = (plotListDo.getPageNum()-1)*plotListDo.getSize();
                plotListDo.setFrom(from);
            }
            //默认排序
            //系统评分
            FieldSortBuilder scoreSort = SortBuilders.fieldSort("_score").order(SortOrder.DESC);
            //级别
            FieldSortBuilder levelSort = SortBuilders.fieldSort("level").order(SortOrder.ASC);
            //小区分数
            FieldSortBuilder plotScoreSort = SortBuilders.fieldSort("plotScore").order(SortOrder.DESC);
            SearchResponse searchResponse = appPlotDao.queryPlotListByRequirement(plotListDo.getKeyword(), plotListDo.getFrom(), boolQueryBuilder, avgPriceSort, scoreSort, levelSort, plotScoreSort);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
                plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                plotDetailsFewDoList.add(plotDetailsFewDo);
            }
            return plotDetailsFewDoList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
