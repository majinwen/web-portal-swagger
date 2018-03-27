package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.AppPlotDao;
import com.toutiao.app.dao.rent.AppRentDao;
import com.toutiao.app.domain.MapInfo;
import com.toutiao.app.domain.Plot.PlotDetailsDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.plot.AppPlotService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.restmodel.NashResult;

import com.toutiao.web.dao.mapper.officeweb.MapInfoMapper;
import com.toutiao.web.domain.query.PlotRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppPlotServiceImpl implements AppPlotService {
    @Value("${distance}")
    private Double distance;

    @Autowired
    private AppPlotDao appPlotDao;
//    @Autowired
//    private AppRentDao appRentDao;
    @Autowired
    private MapInfoMapper mapInfoMapper;
    @Autowired
    private SellHouseService sellHouseService;

    @Override
    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId) {
        try {
            //小区详情
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = appPlotDao.queryPlotDetail(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            Map<String, Object> source = hits[0].getSource();
            PlotDetailsDo plotDetailsDo = PlotDetailsDo.class.newInstance();
            BeanUtils.populate(plotDetailsDo, source);

//            String details = "";
//            details = hits[0].getSourceAsString();
//            PlotDetailsDo plotDetailsDo = JSON.parseObject(details,PlotDetailsDo.class);

            //地图信息
            MapInfo mapInfo = queryPlotMapInfo(plotId);
            if (mapInfo!=null){
                plotDetailsDo.setMapInfo(mapInfo);
            }

            //小区内二手房
            List<SellHouseDetailsDo> esfByPlotId = getEsfByPlotId(plotId);
            if (esfByPlotId!=null){
                plotDetailsDo.setSellHouseDetailsDo(esfByPlotId);
            }

            //小区附近小区
            String[] location = plotDetailsDo.getLocation().split(",");
            List<PlotDetailsDo> nearPlotByLocationAndDistance = getNearPlotByLocationAndDistance(Double.valueOf(location[0]), Double.valueOf(location[1]), plotDetailsDo.getId());
            if (nearPlotByLocationAndDistance!=null&&nearPlotByLocationAndDistance.size()>0){
                plotDetailsDo.setPlotDetailsDoList(nearPlotByLocationAndDistance);
            }
    //            Map rentList = appRentDao.queryHouseByPlotId(plotRequest.getPlotId());
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
    public MapInfo queryPlotMapInfo(Integer plotId) {
        MapInfo mapInfo = new MapInfo();
        com.toutiao.web.dao.entity.officeweb.MapInfo result = mapInfoMapper.selectByNewCode(plotId);
        try {
            BeanUtils.copyProperties(mapInfo,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapInfo;
    }

    /**
     * 查询小区内的二手房
     * @param plotId
     * @return
     */
    public List<SellHouseDetailsDo> getEsfByPlotId(Integer plotId){
        List<SellHouseDetailsDo> sellHouseDetailsDos = sellHouseService.queryEsfByPlotId(plotId);
        return sellHouseDetailsDos;
    }

    /**
     * 附近小区
     * @param lat
     * @param lon
     * @param plotId
     * @return
     */
    public List<PlotDetailsDo> getNearPlotByLocationAndDistance(Double lat, Double lon, Integer plotId) throws Exception {
        List<PlotDetailsDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //从该坐标查询距离为distance内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
        //按照距离排序由近到远并获取小区之间的距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("id",plotId));
        SearchResponse searchResponse = appPlotDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
//                String sourceAsString = hit.getSourceAsString();
                Map<String, Object> source = hit.getSource();
                PlotDetailsDo plotDetailsDo = PlotDetailsDo.class.newInstance();
                BeanUtils.populate(plotDetailsDo, source);
//                PlotDetailsDo plotDetailsDo = JSON.parseObject(sourceAsString,PlotDetailsDo.class);
                list.add(plotDetailsDo);
            }
        }
        return list;
    }


}
