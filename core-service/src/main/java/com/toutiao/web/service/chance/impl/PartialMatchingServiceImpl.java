package com.toutiao.web.service.chance.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.chance.PartialMatchingService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
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

    public Map search(String keyword,String houseProperty) {
        Map map = new HashMap();
        List list = new ArrayList();
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch("search_index").setTypes("search_type");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder queryBuilder = null;
        queryBuilder = boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"village"));
        if (houseProperty!=null){
            queryBuilder = boolQueryBuilder.must(QueryBuilders.multiMatchQuery(houseProperty,"house_property"));
        }
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em style = 'color:red'>").postTags("</em>").field("village");
        srb.highlighter(highlightBuilder);
        SearchResponse searchResponse = srb.setQuery(queryBuilder).execute().actionGet();
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
        }
        return map;
    }
}
