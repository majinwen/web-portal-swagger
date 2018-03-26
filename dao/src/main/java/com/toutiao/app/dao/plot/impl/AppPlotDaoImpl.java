package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.AppPlotDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppPlotDaoImpl implements AppPlotDao {
    @Value("${plot.index}")
    private String index ;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;
    @Value("${distance}")
    private Double distance;
    @Autowired
    private ESClientTools esClientTools;


    @Override
    public Map queryPlotDetail(Integer plotId) {
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length==1){
                Map<String, Object> source = hits[0].getSource();
                return source;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List queryNearPlotByLocationAndDistance(double lat, double lon, Integer plotId) {
        try {
            List nearPlotList = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //从该坐标查询距离为distance内的小区
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
            srb.setPostFilter(location).setSize(5);
            //按照距离排序由近到远并获取小区之间的距离
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);
            srb.addSort(sort);
            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    nearPlotList.add(sourceAsString);
                }
                return nearPlotList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
