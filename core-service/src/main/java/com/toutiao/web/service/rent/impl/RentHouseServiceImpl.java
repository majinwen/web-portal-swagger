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
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层
 *
 */

@Service
public class RentHouseServiceImpl implements RentHouseService{
    @Value("${tt.rent.index}")
    private String index ;
    @Value("${tt.rent.type}")
    private String type ;

    @Autowired
    private ESClientTools esClientTools;


    @Override
    public List queryNearHouseByDistance(RentHouseQuery rentHouseQuery) {
        List list = new ArrayList();
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
            srb.addSort(sort);
            //小区/公寓
            if (rentHouseQuery.getRentSign()==1){
                boolQueryBuilder.must(QueryBuilders.termQuery("rent_sign",1));
            }
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
                if (!"0.0".equals(hit.getSortValues()[0].toString())){
                    list.add(source);
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    @Override
    public List queryHouseByparentId(RentHouseQuery rentHouseQuery) {
        List list = new ArrayList();
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //上级公寓ID
        if (StringUtils.isNotBlank(rentHouseQuery.getApartmentParentId())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("apartment_parent_id",rentHouseQuery.getApartmentParentId()));
        }
        //按价格由低到高排序
        srb.addSort("rent_house_price",SortOrder.ASC);
        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
        //发布状态
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
        //小区/公寓
        boolQueryBuilder.must(QueryBuilders.termQuery("rent_sign",0));
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
        SearchHit[] hits = searchResponse.getHits().getHits();
        if(hits.length>0){
            for (SearchHit hit:hits){
                Map source = hit.getSource();
                list.add(source);
            }
        }
        return list;
    }

    @Override
    public String queryHouseNumByparentId(Integer parentId) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //上级公寓ID
        if (parentId>0){
            boolQueryBuilder.must(QueryBuilders.termQuery("apartment_parent_id",parentId));
        }
        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
        //发布状态
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
        String totalHits = String.valueOf(searchResponse.getHits().getTotalHits());
        return totalHits;
    }


}
