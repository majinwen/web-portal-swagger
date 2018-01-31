package com.toutiao.web.service.chance.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.chance.PartialMatchingService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartialMatchingServiceImpl implements PartialMatchingService {
    @Autowired
    private ESClientTools esClientTools;


    @Override

    public Map search(String keyword,String houseProperty) {
        Map map = new HashMap();
        List list = new ArrayList();
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch("search_index").setTypes("search_type");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"village").minimumShouldMatch("80%"));
        if (houseProperty!=null){
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(houseProperty,"house_property"));
        }
        srb.addAggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("house_property", "小区")))
           .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("house_property", "二手房")))
           .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("house_property", "新房")));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em style = 'color:red'>").postTags("</em>").field("village");
        srb.highlighter(highlightBuilder);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
        if(searchResponse !=null){
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit :hits) {
                Map<String, Object> source = hit.getSource();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField village = highlightFields.get("village");
                Text[] fragments = village.fragments();
                String name = String.valueOf(fragments[0]);
                source.put("villageName",name);
                list.add(source);
            }
            map.put("total",searchResponse.getHits().getTotalHits());
            map.put("list",list);
            map.put("plotNum",((InternalFilter)searchResponse.getAggregations().get("plot")).getDocCount());
            map.put("esfNum",((InternalFilter)searchResponse.getAggregations().get("esf")).getDocCount());
            map.put("newHouseNum",((InternalFilter)searchResponse.getAggregations().get("newHouse")).getDocCount());
        }
        return map;
    }
}
