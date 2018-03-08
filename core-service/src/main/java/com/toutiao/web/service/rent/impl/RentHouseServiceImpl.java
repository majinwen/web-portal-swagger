package com.toutiao.web.service.rent.impl;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层
 *
 */

@Service
public class RentHouseServiceImpl implements RentHouseService{
//    @Value("${rent.index}")
    private String index ;
//    @Value("${rent.type}")
    private String type ;

    @Autowired
    private ESClientTools esClientTools;


    @Override
    public NashResult queryNearHouseByDistance(RentHouseQuery rentHouseQuery) {
        List list = new ArrayList();
        Map result = new HashMap();
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //从该坐标查询距离为distance的点
            GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(rentHouseQuery.getLat(), rentHouseQuery.getLon()).distance(rentHouseQuery.getNearbyKm(), DistanceUnit.KILOMETERS);
            srb.setPostFilter(location1).setSize(6);
            // 按距离排序
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", rentHouseQuery.getLat(), rentHouseQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
            sort.order(SortOrder.ASC);
            sort.point(rentHouseQuery.getLat(), rentHouseQuery.getLon());
            srb.addSort(sort);

            //小区/公寓
            //是否删除
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
            //发布状态
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
            //价格上下浮动20%
            if (rentHouseQuery.getBeginPrice()>0&&rentHouseQuery.getEndPrice()>0){
                boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentHouseQuery.getBeginPrice()).lte(rentHouseQuery.getEndPrice()));
            }
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] searchHists = searchResponse.getHits().getHits();
            for (SearchHit hit:searchHists){
                Map source = hit.getSource();
                list.add(source);
                //获取距离值，并保留两位小数点
                BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
                Map<String, Object> hitMap = hit.getSource();

                // 在创建MAPPING的时候，属性名的不可为geoDistance。
                hitMap.put("geoDistance", geoDis.setScale(1, BigDecimal.ROUND_HALF_DOWN));
                String distance1 = hit.getSource().get("geoDistance") + DistanceUnit.KILOMETERS.toString();//距离
                //System.out.println("距离你的位置为：" + hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString());
            }
            result.put("rent",list);
            result.put("total",searchResponse.getHits().getTotalHits());
            return NashResult.build(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return NashResult.Fail("");
    }

    @Override
    public NashResult queryHouseById(RentHouseQuery rentHouseQuery) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //出租房源ID
        if(StringUtils.isNotBlank(rentHouseQuery.getHouseId())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("house_id",rentHouseQuery.getHouseId()));
        }
        //公寓上级ID
        if (StringUtils.isNotBlank(rentHouseQuery.getApartmentParentId())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("apartment_parent_id",rentHouseQuery.getApartmentParentId()));
        }
        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
        //发布状态
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
        SearchResponse response = srb.setQuery(boolQueryBuilder).execute().actionGet();
        SearchHit[] searchHists = response.getHits().getHits();
        if(searchHists.length==1){
            Map source = searchHists[0].getSource();
            return NashResult.build(source);
        }
        return NashResult.Fail("");
    }

}
