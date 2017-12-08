package com.toutiao.web.service.repository.admin.impl;

import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysVillageServiceImpl implements SysVillageService {

    @Override
    public List GetNearByhHouseAndDistance(String index, String type, double lat, double lon, String distance) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        //从该坐标查询距离为distance
        GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(lat,lon).distance(Double.parseDouble(distance), DistanceUnit.METERS);
        srb.setPostFilter(location1);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location",lat,lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat,lon);
        srb.addSort(sort);

        SearchResponse searchResponse = srb.execute().actionGet();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String[] house = new String[(int) hits.getTotalHits()];
        System.out.println("附近的小区(" + hits.getTotalHits() + "个)：");
        List houseList = new ArrayList();
        for (SearchHit hit : searchHists) {
            String rc = (String) hit.getSource().get("rc");//小区名称
            String abbreviatedage = (String) hit.getSource().get("abbreviatedage");//建成年代
            String label = (String) hit.getSource().get("label");//标签
            String avgprice = (String) hit.getSource().get("avgprice");//


            houseList.add(rc);
            List<Double> location = (List<Double>)hit.getSource().get("location");
            // 获取距离值，并保留两位小数点
            BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
            Map<String, Object> hitMap = hit.getSource();
            // 在创建MAPPING的时候，属性名的不可为geoDistance。
            hitMap.put("geoDistance", geoDis.setScale(1, BigDecimal.ROUND_HALF_DOWN));
            String distance1 = hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString();//距离
//            System.out.println(rc + "距离你的位置为：" + hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString());

        }
        return houseList;
    }

    @Override
    public VillageEntity findVillageById(String index, String type,Integer id) throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("id", id);
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(queryBuilder)
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();
        //获取查询总数
        long total = hits.getTotalHits();
        System.out.println(total);
        //获取响应字符串
        System.out.println(response.toString());
        return null;
    }
}
