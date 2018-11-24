package com.toutiao.app.service.suggest.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.suggest.SuggestEsDao;
import com.toutiao.app.domain.suggest.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SuggestServiceImpl implements SuggestService {

    @Value("${tt.search.engines}")
    private String search_engines_index;
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
     *
     * @param keyword
     * @param property
     * @return
     */
    @Override
    public SuggestDo suggest(String keyword, String property, String city) {
        SuggestDo suggestDo = new SuggestDo();
        List<SearchScopeDo> scopeDoList = new ArrayList<>();
        List<SearchEnginesDo> enginesDoList = new ArrayList<>();


        BoolQueryBuilder boolQueryBuilderAD = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQueryBuilderBuild = QueryBuilders.boolQuery();
        BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH));
        boolQueryBuilderAD.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword, "search_name").minimumShouldMatch("100%")));

        if (property != null) {
            String searchType = getSearchType(property);
            if (searchType == RENT_TYPE) {
                BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
                boolQueryBuilderAD.must(queryBuilder.should(QueryBuilders.multiMatchQuery(RENT_TYPE, "search_type_sings")));
                boolQueryBuilderAD.must(queryBuilder.should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE, "search_type_sings")));

                boolQueryBuilderBuild.must(queryBuilder.should(QueryBuilders.multiMatchQuery(RENT_TYPE, "search_type_sings")));
                boolQueryBuilderBuild.must(queryBuilder.should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE, "search_type_sings")));
            } else {
                boolQueryBuilderAD.must(QueryBuilders.multiMatchQuery(searchType, "search_type_sings"));

                boolQueryBuilderBuild.must(QueryBuilders.multiMatchQuery(searchType, "search_type_sings"));
            }
        }

        SearchResponse areaAndDistrictSuggest = suggestEsDao.getAreaAndDistrictSuggest(boolQueryBuilderAD, city);

        SearchHit[] hits = areaAndDistrictSuggest.getHits().getHits();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                SearchScopeDo searchScopeDo = JSON.parseObject(sourceAsString, SearchScopeDo.class);
                scopeDoList.add(searchScopeDo);
            }
        }
        suggestDo.setPlotNum((int) ((InternalFilter) areaAndDistrictSuggest.getAggregations().get("plot")).getDocCount());
        suggestDo.setEsfNum((int) ((InternalFilter) areaAndDistrictSuggest.getAggregations().get("esf")).getDocCount());
        suggestDo.setNewHouseNum((int) ((InternalFilter) areaAndDistrictSuggest.getAggregations().get("newHouse")).getDocCount());
        suggestDo.setRentNum((int) ((InternalFilter) areaAndDistrictSuggest.getAggregations().get("rent")).getDocCount());
        suggestDo.setApartmentNum((int) ((InternalFilter) areaAndDistrictSuggest.getAggregations().get("apartment")).getDocCount());


        boolQueryBuilderBuild.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword, "search_nickname").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));
        boolQueryBuilderBuild.must(QueryBuilders.multiMatchQuery(IS_APPROVE, "is_approve"));
        boolQueryBuilderBuild.must(QueryBuilders.multiMatchQuery(IS_DEL, "is_del"));
        boolQueryBuilderBuild.must(boolQueryBuilder1.should(QueryBuilders.multiMatchQuery(keyword, "search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));
        SearchResponse keywordSuggest = suggestEsDao.getKeywordSuggest(boolQueryBuilderBuild, city);
        SearchHit[] keywordHits = keywordSuggest.getHits().getHits();
        if (keywordHits.length > 0) {
            for (SearchHit hit : keywordHits) {
                String sourceAsString = hit.getSourceAsString();
                SearchEnginesDo searchEnginesDo = JSON.parseObject(sourceAsString, SearchEnginesDo.class);
                enginesDoList.add(searchEnginesDo);
            }
        }

        suggestDo.setSearchScopeList(scopeDoList);

//        if (scopeDoList.size()<10 && scopeDoList.size()>0){
//            suggestDo.setSearchEnginesList(enginesDoList.subList(0,10-scopeDoList.size()));
//        }else if(scopeDoList.size() == 0){
//            suggestDo.setSearchEnginesList(enginesDoList);
//        }
        if (scopeDoList.size() < 10 && scopeDoList.size() > 0) {
            if ((enginesDoList.size() + scopeDoList.size()) < 10) {
                suggestDo.setSearchEnginesList(enginesDoList);
            } else {
                suggestDo.setSearchEnginesList(enginesDoList.subList(0, 10 - scopeDoList.size()));
            }
        } else if (scopeDoList.size() >= 10) {
            suggestDo.setSearchEnginesList(enginesDoList.subList(0, 10 - scopeDoList.size()));
        } else {
            suggestDo.setSearchEnginesList(enginesDoList);
        }


        suggestDo.setPlotNum(suggestDo.getPlotNum() + (int) ((InternalFilter) keywordSuggest.getAggregations().get("plot")).getDocCount());
        suggestDo.setEsfNum(suggestDo.getEsfNum() + (int) ((InternalFilter) keywordSuggest.getAggregations().get("esf")).getDocCount());
        suggestDo.setNewHouseNum(suggestDo.getNewHouseNum() + (int) ((InternalFilter) keywordSuggest.getAggregations().get("newHouse")).getDocCount());
        suggestDo.setRentNum(suggestDo.getRentNum() + (int) ((InternalFilter) keywordSuggest.getAggregations().get("rent")).getDocCount());
        suggestDo.setApartmentNum(suggestDo.getApartmentNum() + (int) ((InternalFilter) keywordSuggest.getAggregations().get("apartment")).getDocCount());

        return suggestDo;
    }

    /**
     * 搜索联想词提示v2
     *
     * @param keyword
     * @param property
     * @return
     */
    @Override
    public SuggestResultDo suggest_v2(String keyword, String property, String city) {
        SuggestResultDo suggestResultDo = new SuggestResultDo();
        List<SuggestListDo> resultList = new ArrayList<>();
        List<SearchScopeDo> scopeDoList = new ArrayList<>();
        SuggestListDo suggestListDo = new SuggestListDo();
        SearchScopeDo searchScopeDo = new SearchScopeDo();
        List<SearchEnginesDo> enginesDoList = new ArrayList<>();

        BoolQueryBuilder enginesQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder scopeQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder enginesQueryBuildershould = QueryBuilders.boolQuery();
        scopeQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword, "search_sort").minimumShouldMatch("100%"));

        enginesQueryBuilder.must(enginesQueryBuildershould
                .should(QueryBuilders.multiMatchQuery(keyword, "search_nickname").minimumShouldMatch(MINIMUM_SHOULD_MATCH))
                .should(QueryBuilders.multiMatchQuery(keyword, "search_name").minimumShouldMatch(MINIMUM_SHOULD_MATCH)));
        enginesQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_APPROVE, "is_approve"));
        enginesQueryBuilder.must(QueryBuilders.multiMatchQuery(IS_DEL, "is_del"));

        //聚合获取数量
//        SearchResponse EnginesAggregations = suggestEsDao.getKeywordSuggest(enginesQueryBuilder, city);

        //是否为首页搜索
        if (null == property) {
            BoolQueryBuilder newhouse = new BoolQueryBuilder().must(enginesQueryBuilder);
            BoolQueryBuilder plot = new BoolQueryBuilder().must(enginesQueryBuilder);
            BoolQueryBuilder sellhouse = new BoolQueryBuilder().must(enginesQueryBuilder);
            BoolQueryBuilder rent = new BoolQueryBuilder().must(enginesQueryBuilder);

            newhouse.must(QueryBuilders.multiMatchQuery(NEW_HOUSE_TYPE, "search_type_sings"));
            List<SearchEnginesDo> newHouseList = getEnginesResult(newhouse, city);

            plot.must(QueryBuilders.multiMatchQuery(PLOT_TYPE, "search_type_sings"));
            List<SearchEnginesDo> plotList = getEnginesResult(plot, city);

            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            sellhouse.must(queryBuilder
                    .should(QueryBuilders.multiMatchQuery(RENT_TYPE, "search_type_sings"))
                    .should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE, "search_type_sings")));
            List<SearchEnginesDo> rentList = getEnginesResult(sellhouse, city);

            rent.must(QueryBuilders.multiMatchQuery(ESF_TYPE, "search_type_sings"));
            List<SearchEnginesDo> esfList = getEnginesResult(rent, city);

            scopeDoList = getScopeResult(scopeQueryBuilder, city);

            resultList = getEnginesList(newHouseList, plotList, rentList, esfList, scopeDoList);

        } else {
            String searchType = getSearchType(property);
            if (searchType == RENT_TYPE) {
                BoolQueryBuilder scopeQueryBuilderShould = QueryBuilders.boolQuery();
                BoolQueryBuilder enginesQueryBuilderShould = QueryBuilders.boolQuery();
                scopeQueryBuilder.must(scopeQueryBuilderShould
                        .should(QueryBuilders.multiMatchQuery(RENT_TYPE, "search_type_sings"))
                        .should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE, "search_type_sings"))
                );

                enginesQueryBuilder.must(enginesQueryBuilderShould
                        .should(QueryBuilders.multiMatchQuery(RENT_TYPE, "search_type_sings"))
                        .should(QueryBuilders.multiMatchQuery(APARTMENT_TYPE, "search_type_sings")));
            } else {
                scopeQueryBuilder.must(QueryBuilders.multiMatchQuery(searchType, "search_type_sings"));
                enginesQueryBuilder.must(QueryBuilders.multiMatchQuery(searchType, "search_type_sings"));
            }

            scopeDoList = getScopeResult(scopeQueryBuilder, city);
            enginesDoList = getEnginesResult(enginesQueryBuilder, city);
            if (null != scopeDoList && scopeDoList.size() > 0) {
                suggestListDo.setSearchScope(scopeDoList.get(0));
            }
            suggestListDo.setHouseType(Integer.valueOf(searchType));
            if (null != enginesDoList && enginesDoList.size() > 0) {
                suggestListDo.setSearchEnginesList(enginesDoList);
            }

            resultList.add(suggestListDo);
        }

        suggestResultDo.setSuggestResultList(resultList);
        return suggestResultDo;
    }

    public List<SearchEnginesDo> getEnginesResult(BoolQueryBuilder enginesQueryBuilder, String city) {
        List<SearchEnginesDo> enginesDoList = new ArrayList<>();
        SearchResponse keywordSuggest = suggestEsDao.getKeywordSuggest(enginesQueryBuilder, city);
        SearchHit[] keywordHits = keywordSuggest.getHits().getHits();
        if (keywordHits.length > 0) {
            for (SearchHit hit : keywordHits) {
                String sourceAsString = hit.getSourceAsString();
                SearchEnginesDo searchEnginesDo = JSON.parseObject(sourceAsString, SearchEnginesDo.class);
                enginesDoList.add(searchEnginesDo);
            }
        }
        return enginesDoList;
    }


    public List<SearchScopeDo> getScopeResult(BoolQueryBuilder scopeQueryBuilder, String city) {
        List<SearchScopeDo> list = new ArrayList<>();
        SearchResponse areaAndDistrictSuggest = suggestEsDao.getAreaAndDistrictSuggest(scopeQueryBuilder, city);
        SearchHit[] hits = areaAndDistrictSuggest.getHits().getHits();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                SearchScopeDo searchScopeDo = JSON.parseObject(sourceAsString, SearchScopeDo.class);
                list.add(searchScopeDo);
            }
        }
        return list;
    }

    /**
     * 补数据
     *
     * @param newHouseList
     * @param plotList
     * @param rentList
     * @param esfList
     * @return
     */
    public List<SuggestListDo> getEnginesList(List<SearchEnginesDo> newHouseList, List<SearchEnginesDo> plotList, List<SearchEnginesDo> rentList, List<SearchEnginesDo> esfList, List<SearchScopeDo> scopeDoList) {
        List<SuggestListDo> resultList = new ArrayList<>();
        SuggestListDo newhouse = new SuggestListDo();
        SuggestListDo plot = new SuggestListDo();
        SuggestListDo sellhouse = new SuggestListDo();
        SuggestListDo rent = new SuggestListDo();

        Map<Integer, SearchScopeDo> map = new HashMap();
        for (SearchScopeDo scope : scopeDoList) {
            if (scope.getSearchTypeSings() == 0) {
                map.put(0, scope);
            }
            if (scope.getSearchTypeSings() == 1) {
                map.put(1, scope);
            }
            if (scope.getSearchTypeSings() == 2) {
                map.put(2, scope);
            }
            if (scope.getSearchTypeSings() == 3) {
                map.put(3, scope);
            }
            if (scope.getSearchTypeSings() == 4) {
                map.put(4, scope);
            }
        }

        if (null != newHouseList && newHouseList.size() > 3) {
            newHouseList = newHouseList.subList(0, 3);
        }
        List<SearchEnginesDo> subPlotList = plotList;
        if (null != plotList && plotList.size() > 3) {
            subPlotList = plotList.subList(0, 3);
        }
        List<SearchEnginesDo> subRentList = rentList;
        if (null != rentList && rentList.size() > 3) {
            subRentList = rentList.subList(0, 3);
        }

        Integer esfSize = newHouseList.size() + subPlotList.size() + subRentList.size();
        if (null != esfList && esfList.size() >= 12 - esfSize) {
            esfList = esfList.subList(0, 12 - esfSize);
        }

        Integer totalSize = esfSize + esfList.size();

        if (totalSize == 12) {
            newhouse.setSearchEnginesList(newHouseList);
            newhouse.setHouseType(0);
            if (null != map.get(0)) {
                newhouse.setSearchScope(map.get(0));
            }
            resultList.add(newhouse);

            sellhouse.setSearchEnginesList(esfList);
            sellhouse.setHouseType(2);
            if (null != map.get(2)) {
                sellhouse.setSearchScope(map.get(2));
            }
            resultList.add(sellhouse);

            if (null != map.get(3) && null != map.get(4)) {
                map.get(3).setLocationNum(map.get(3).getLocationNum() + map.get(4).getLocationNum());
                rent.setSearchScope(map.get(3));
            }
            rent.setSearchEnginesList(subRentList);
            rent.setHouseType(3);
            resultList.add(rent);

            plot.setSearchEnginesList(subPlotList);
            plot.setHouseType(1);
            if (null != map.get(1)) {
                plot.setSearchScope(map.get(1));
            }
            resultList.add(plot);

            return resultList;

        }

        if (totalSize < 12 && rentList.size() > 3) {
            rentList = rentList.subList(0, 15 - totalSize);
            totalSize = totalSize + rentList.size() - 3;
            if (totalSize == 12) {
                newhouse.setSearchEnginesList(newHouseList);
                newhouse.setHouseType(0);
                if (null != map.get(0)) {
                    newhouse.setSearchScope(map.get(0));
                }
                resultList.add(newhouse);

                sellhouse.setSearchEnginesList(esfList);
                sellhouse.setHouseType(2);
                if (null != map.get(2)) {
                    sellhouse.setSearchScope(map.get(2));
                }
                resultList.add(sellhouse);

                if (null != map.get(3) && null != map.get(4)) {
                    map.get(3).setLocationNum(map.get(3).getLocationNum() + map.get(4).getLocationNum());
                    rent.setSearchScope(map.get(3));
                }
                rent.setSearchEnginesList(rentList);
                rent.setHouseType(3);

                resultList.add(rent);

                plot.setSearchEnginesList(subPlotList);
                plot.setHouseType(1);
                if (null != map.get(1)) {
                    plot.setSearchScope(map.get(1));
                }
                resultList.add(plot);

                return resultList;
            }
        }

        if (totalSize < 12 && plotList.size() > 3) {
            plotList = plotList.subList(0, 15 - totalSize);

            newhouse.setSearchEnginesList(newHouseList);
            newhouse.setHouseType(0);
            if (null != map.get(0)) {
                newhouse.setSearchScope(map.get(0));
            }
            resultList.add(newhouse);

            sellhouse.setSearchEnginesList(esfList);
            sellhouse.setHouseType(2);
            if (null != map.get(2)) {
                sellhouse.setSearchScope(map.get(2));
            }
            resultList.add(sellhouse);

            if (null != map.get(3) && null != map.get(4)) {
                map.get(3).setLocationNum(map.get(3).getLocationNum() + map.get(4).getLocationNum());
                rent.setSearchScope(map.get(3));
            }
            rent.setSearchEnginesList(rentList);
            rent.setHouseType(3);
            resultList.add(rent);

            plot.setSearchEnginesList(plotList);
            plot.setHouseType(1);
            if (null != map.get(1)) {
                plot.setSearchScope(map.get(1));
            }
            resultList.add(plot);

            return resultList;
        }
        return resultList;
    }

    /**
     * 获取房源类型标志
     *
     * @param property
     * @return
     */
    public String getSearchType(String property) {
        String searchType = null;
        if (property.equals("newhouse")) {
            searchType = NEW_HOUSE_TYPE;
        }
        if (property.equals("plot")) {
            searchType = PLOT_TYPE;
        }
        if (property.equals("sellhouse")) {
            searchType = ESF_TYPE;
        }
        if (property.equals("rent")) {
            searchType = RENT_TYPE;
        }
        return searchType;
    }
}
