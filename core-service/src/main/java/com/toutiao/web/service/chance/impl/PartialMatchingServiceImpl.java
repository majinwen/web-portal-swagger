package com.toutiao.web.service.chance.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.chance.PartialMatchingService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
    private static final String RENT_TYPE = "3";
    private static final String APARTMENT_TYPE = "4";



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
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (searchType == RENT_TYPE){
                boolQueryBuilderScope.must(queryBuilder.should(QueryBuilders.multiMatchQuery(RENT_TYPE,"search_type_sings")));
                boolQueryBuilderScope.must(queryBuilder.should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE,"search_type_sings")));
            }else {
                boolQueryBuilderScope.must(QueryBuilders.multiMatchQuery(searchType,"search_type_sings"));
            }
        }
        srbScope.addSort("search_sort",SortOrder.ASC);


        srbScope.addAggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                .addAggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                .addAggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));

        SearchResponse searchResponseScope = srbScope.setQuery(boolQueryBuilderScope).execute().actionGet();
        if (searchResponseScope!=null){
            SearchHit[] hits = searchResponseScope.getHits().getHits();
            for (SearchHit hit :hits) {
                Map<String, Object> source = hit.getSourceAsMap();
                if ((Integer) source.get("location_num")>0){
                    list.add(source);
                }
            }
        }

        map.put("plotNum",((InternalFilter)searchResponseScope.getAggregations().get("plot")).getDocCount());
        map.put("esfNum",((InternalFilter)searchResponseScope.getAggregations().get("esf")).getDocCount());
        map.put("newHouseNum",((InternalFilter)searchResponseScope.getAggregations().get("newHouse")).getDocCount());
        map.put("rentNum",((InternalFilter)searchResponseScope.getAggregations().get("rent")).getDocCount());
        map.put("apartmentNum",((InternalFilter)searchResponseScope.getAggregations().get("apartment")).getDocCount());

        SearchRequestBuilder srbEngines = client.prepareSearch(search_engines_index).setTypes(search_engines_type);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH));
        boolQueryBuilder.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH)).should(QueryBuilders.multiMatchQuery(keyword,"search_nickname").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_APPROVE,"is_approve"));
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_DEL,"is_del"));

        if (property!=null){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            String searchType = getSearchType(property);
            if (searchType == RENT_TYPE){
                boolQueryBuilder.must(queryBuilder.should(QueryBuilders.multiMatchQuery(RENT_TYPE,"search_type_sings")));
                boolQueryBuilder.must(queryBuilder.should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE,"search_type_sings")));
            }else {
                boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchType,"search_type_sings"));
            }
        }

        srbEngines.addAggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                .addAggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                .addAggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));
//        TermsAggregationBuilder aggs = AggregationBuilders.terms("test").field("search_type_sings");
//        srbEngines.addAggregation(aggs);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em style = 'color:red'>").postTags("</em>").field("search_name");
        srbEngines.highlighter(highlightBuilder);
        SearchResponse searchResponse = srbEngines.setQuery(boolQueryBuilder).execute().actionGet();
//        Aggregation player_count = searchResponse.getAggregations().get("player_count");
//        Terms ddd = searchResponse.getAggregations().get("test");

        if(searchResponse !=null){
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit :hits) {
                Map<String, Object> source = hit.getSourceAsMap();
//                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//                HighlightField village = highlightFields.get("search_name");
//                Text[] fragments = village.fragments();
//                String name = String.valueOf(fragments[0]);
//                source.put("em_search_name",name);
                list.add(source);
            }
            map.put("total",searchResponse.getHits().getTotalHits());
            if(list.size()<10){
                map.put("list",list);
            }else{
                map.put("list",list.subList(0,10));
            }
            if(Integer.valueOf(map.get("plotNum").toString())==0){
                map.put("plotNum",((InternalFilter)searchResponse.getAggregations().get("plot")).getDocCount());
            }
            if(Integer.valueOf(map.get("esfNum").toString())==0){
                map.put("esfNum",((InternalFilter)searchResponse.getAggregations().get("esf")).getDocCount());
            }
            if(Integer.valueOf(map.get("newHouseNum").toString())==0){
                map.put("newHouseNum",((InternalFilter)searchResponse.getAggregations().get("newHouse")).getDocCount());
            }
            if(Integer.valueOf(map.get("rentNum").toString())==0){
                map.put("rentNum",((InternalFilter)searchResponse.getAggregations().get("rent")).getDocCount());
            }
            if(Integer.valueOf(map.get("apartmentNum").toString())==0){
                map.put("apartmentNum",((InternalFilter)searchResponse.getAggregations().get("apartment")).getDocCount());
            }
//            map.put("plotNum",((InternalFilter)searchResponse.getAggregations().get("plot")).getDocCount());
//            map.put("esfNum",((InternalFilter)searchResponse.getAggregations().get("esf")).getDocCount());

//            map.put("newHouseNum",((InternalFilter)searchResponse.getAggregations().get("newHouse")).getDocCount());
        }
        return map;
    }

    public String getSearchType(String property){
        String searchType = null;
        if (property.equals("新房")){
            searchType = NEW_HOUSE_TYPE;
        }
        if (property.equals("小区")){
            searchType = PLOT_TYPE;
        }
        if (property.equals("二手房")){
            searchType = ESF_TYPE;
        }
        if (property.equals("租房")){
            searchType = RENT_TYPE;
        }
        return searchType;
    }
}
