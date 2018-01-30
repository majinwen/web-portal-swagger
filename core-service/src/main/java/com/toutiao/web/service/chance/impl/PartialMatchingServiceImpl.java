package com.toutiao.web.service.chance.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.chance.PartialMatchingService;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartialMatchingServiceImpl implements PartialMatchingService {
    @Value("${plot.index}")
    private String index;
    @Value("${plot.parent.type}")
    private String parentType;
    @Autowired
    private ESClientTools esClientTools;


    @Override
    public Map Search(String keyword,String houseProperty) {
        Map map = new HashMap();
        List list = new ArrayList();
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch("search_index").setTypes("search_type");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder queryBuilder = null;
        queryBuilder = boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"projname"));
        if (houseProperty!=null){
            queryBuilder = boolQueryBuilder.must(QueryBuilders.multiMatchQuery(houseProperty,"house_property"));
        }
        SearchResponse searchResponse = srb.setQuery(queryBuilder).execute().actionGet();
        if(searchResponse !=null){
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit :hits) {
                Map<String, Object> source = hit.getSource();
                list.add(source);
            }
            map.put("total",searchResponse.getHits().getTotalHits());
            map.put("list",list);
        }
        return map;
    }
}
