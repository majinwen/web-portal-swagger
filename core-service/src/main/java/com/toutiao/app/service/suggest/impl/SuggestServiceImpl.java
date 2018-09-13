package com.toutiao.app.service.suggest.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.suggest.SuggestEsDao;
import com.toutiao.app.domain.suggest.SearchEnginesDo;
import com.toutiao.app.domain.suggest.SearchScopeDo;
import com.toutiao.app.domain.suggest.SuggestDo;
import com.toutiao.app.service.suggest.SuggestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestServiceImpl implements SuggestService {

    @Value("${tt.search.engines}")
    private String search_engines_index ;
    @Value("${tt.search.scope}")
    private String search_scope_index;
    @Value("${tt.search.engines.type}")
    private String search_engines_type;
    @Value("${tt.search.scope.type}")
    private String search_scope_type;

    private static final Integer IS_DEL = 0;//未删除
    private static final Integer IS_APPROVE = 1;//未下架
    private static final String MINIMUM_SHOULD_MATCH = "80%";//匹配度
    private static final String NEW_HOUSE_TYPE = "0";
    private static final String PLOT_TYPE = "1";
    private static final String ESF_TYPE = "2";
    private static final String RENT_TYPE = "3";
    private static final String APARTMENT_TYPE = "4";

    @Autowired
    private SuggestEsDao suggestEsDao;



    /**
     * 搜索联想词提示
     * @param keyword
     * @param property
     * @return
     */
    @Override
    public SuggestDo suggest(String keyword, String property, String city) {
        SuggestDo suggestDo = new SuggestDo();
        List<SearchScopeDo> scopeDoList = new ArrayList<>();
        List<SearchEnginesDo> enginesDoList = new ArrayList<>();


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH));
        boolQueryBuilder.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));

        if (property!=null){
            String searchType = getSearchType(property);
            if (searchType == RENT_TYPE){
                BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
                boolQueryBuilder.must(queryBuilder.should(QueryBuilders.multiMatchQuery(RENT_TYPE,"search_type_sings")));
                boolQueryBuilder.must(queryBuilder.should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE,"search_type_sings")));
            }else {
                boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchType,"search_type_sings"));
            }
        }

        SearchResponse areaAndDistrictSuggest = suggestEsDao.getAreaAndDistrictSuggest(boolQueryBuilder, city);

        SearchHit[] hits = areaAndDistrictSuggest.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit :hits) {
                String sourceAsString = hit.getSourceAsString();
                SearchScopeDo searchScopeDo = JSON.parseObject(sourceAsString, SearchScopeDo.class);
                scopeDoList.add(searchScopeDo);
            }
        }
        suggestDo.setPlotNum((int) ((InternalFilter)areaAndDistrictSuggest.getAggregations().get("plot")).getDocCount());
        suggestDo.setEsfNum((int) ((InternalFilter)areaAndDistrictSuggest.getAggregations().get("esf")).getDocCount());
        suggestDo.setNewHouseNum((int) ((InternalFilter)areaAndDistrictSuggest.getAggregations().get("newHouse")).getDocCount());
        suggestDo.setRentNum((int) ((InternalFilter)areaAndDistrictSuggest.getAggregations().get("rent")).getDocCount());
        suggestDo.setApartmentNum((int) ((InternalFilter)areaAndDistrictSuggest.getAggregations().get("apartment")).getDocCount());


        boolQueryBuilder.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword,"search_nickname").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_APPROVE,"is_approve"));
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_DEL,"is_del"));
        SearchResponse keywordSuggest = suggestEsDao.getKeywordSuggest(boolQueryBuilder, city);
        SearchHit[] keywordHits = keywordSuggest.getHits().getHits();
        if (keywordHits.length>0){
            for (SearchHit hit :keywordHits) {
                String sourceAsString = hit.getSourceAsString();
                SearchEnginesDo searchEnginesDo = JSON.parseObject(sourceAsString, SearchEnginesDo.class);
                enginesDoList.add(searchEnginesDo);
            }
        }

        suggestDo.setSearchScopeList(scopeDoList);

        if (scopeDoList.size()<10 && scopeDoList.size()>0){
            suggestDo.setSearchEnginesList(enginesDoList.subList(0,10-scopeDoList.size()));
        }else if(scopeDoList.size() == 0){
            suggestDo.setSearchEnginesList(enginesDoList);
        }

        suggestDo.setPlotNum(suggestDo.getPlotNum()+(int) ((InternalFilter)keywordSuggest.getAggregations().get("plot")).getDocCount());
        suggestDo.setEsfNum(suggestDo.getEsfNum()+(int) ((InternalFilter)keywordSuggest.getAggregations().get("esf")).getDocCount());
        suggestDo.setNewHouseNum(suggestDo.getNewHouseNum()+(int) ((InternalFilter)keywordSuggest.getAggregations().get("newHouse")).getDocCount());
        suggestDo.setRentNum(suggestDo.getRentNum()+(int) ((InternalFilter)keywordSuggest.getAggregations().get("rent")).getDocCount());
        suggestDo.setApartmentNum(suggestDo.getApartmentNum()+(int) ((InternalFilter)keywordSuggest.getAggregations().get("apartment")).getDocCount());

        return suggestDo;
    }


    /**
     * 获取房源类型标志
     * @param property
     * @return
     */
    public String getSearchType(String property){
        String searchType = null;
        if (property.equals("newhouse")){
            searchType = NEW_HOUSE_TYPE;
        }
        if (property.equals("plot")){
            searchType = PLOT_TYPE;
        }
        if (property.equals("sellhouse")){
            searchType = ESF_TYPE;
        }
        if (property.equals("rent")){
            searchType = RENT_TYPE;
        }
        return searchType;
    }
}
