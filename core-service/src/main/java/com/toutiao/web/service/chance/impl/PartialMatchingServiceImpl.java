package com.toutiao.web.service.chance.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.chance.PartialMatchingService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartialMatchingServiceImpl implements PartialMatchingService {
    @Value("${tt.search.engines}")
    private String search_engines_index ;
    @Value("${tt.search.scope}")
    private String search_scope_index;
    @Value("${tt.search.engines.type}")
    private String search_engines_type;
    @Value("${tt.search.scope.type}")
    private String search_scope_type;
    @Autowired
    private ESClientTools esClientTools;
    private static final Integer IS_DEL = 0;//未删除
    private static final Integer IS_APPROVE = 1;//未下架
    private static final String MINIMUM_SHOULD_MATCH = "80%";//匹配度
    private static final String NEW_HOUSE_TYPE = "0";
    private static final String PLOT_TYPE = "1";
    private static final String ESF_TYPE = "2";



    @Override
    public Map search(String keyword,String property) {
        Map map = new HashMap();
        List list = new ArrayList();
        TransportClient client = esClientTools.init();

        SearchRequestBuilder srbScope = client.prepareSearch(search_scope_index).setTypes(search_scope_type);
        BoolQueryBuilder boolQueryBuilderScope = QueryBuilders.boolQuery();
        boolQueryBuilderScope.must(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH));

        if (property!=null){
            String searchType = getSearchType(property);
            boolQueryBuilderScope.must(QueryBuilders.multiMatchQuery(searchType,"search_type_sings"));
        }
        srbScope.addSort("search_sort",SortOrder.ASC);
        SearchResponse searchResponseScope = srbScope.setQuery(boolQueryBuilderScope).execute().actionGet();
        if (searchResponseScope!=null){
            SearchHit[] hits = searchResponseScope.getHits().getHits();
            for (SearchHit hit :hits) {
                Map<String, Object> source = hit.getSource();
                list.add(source);
            }
        }



        if(list.size()<10){
            SearchRequestBuilder srbEngines = client.prepareSearch(search_engines_index).setTypes(search_engines_type);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH));
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_APPROVE,"is_approve"));
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_DEL,"is_del"));

            if (property!=null){
                String searchType = getSearchType(property);
                boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchType,"search_type_sings"));
            }

            srbEngines.addAggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                    .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                    .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)));

            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.preTags("<em style = 'color:red'>").postTags("</em>").field("search_name");
            srbEngines.highlighter(highlightBuilder);
            SearchResponse searchResponse = srbEngines.setQuery(boolQueryBuilder).execute().actionGet();
            if(searchResponse !=null){
                SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit :hits) {
                    Map<String, Object> source = hit.getSource();
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    HighlightField village = highlightFields.get("search_name");
                    Text[] fragments = village.fragments();
                    String name = String.valueOf(fragments[0]);
                    source.put("em_search_name",name);
                    list.add(source);
                }
                map.put("total",searchResponse.getHits().getTotalHits());
                if(list.size()<10){
                    map.put("list",list);
                }else{
                    map.put("list",list.subList(0,10));
                }

                map.put("plotNum",((InternalFilter)searchResponse.getAggregations().get("plot")).getDocCount());
                map.put("esfNum",((InternalFilter)searchResponse.getAggregations().get("esf")).getDocCount());
                map.put("newHouseNum",((InternalFilter)searchResponse.getAggregations().get("newHouse")).getDocCount());
            }
        }else {
            if(list.size()<10){
                map.put("list",list);
            }else{
                map.put("list",list.subList(0,10));
            }
        }
        return map;
    }

    public String getSearchType(String property){
        String searchType = null;
        if (property.equals("新房")){
            searchType = "0";
        }
        if (property.equals("小区")){
            searchType = "1";
        }
        if (property.equals("二手房")){
            searchType = "2";
        }
        return searchType;
    }
}
