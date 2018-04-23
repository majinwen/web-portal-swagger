package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SellHouseEsDaoImpl implements SellHouseEsDao{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;



    @Override
    public SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();

        return searchresponse;
    }

    @Override
    public SearchResponse getSellHouseByHouseIdAndLocation(GeoDistanceSortBuilder sort, NearBySellHousesDo nearBySellHousesDo,BoolQueryBuilder booleanQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        SearchResponse searchResponse =new SearchResponse();

        searchResponse = srb.setQuery(booleanQueryBuilder).addSort(sort).setFetchSource(

                    new String[] {"houseTitle","housePhoto","houseTotalPrices","houseUnitCost","area","houseBusinessName","houseId","housePlotLocation","tagsName","plotName_accurate","traffic","forwardName","room","hall","buildArea","toilet","year","forwardName","is_claim","year"} ,null
            ).setFrom((nearBySellHousesDo.getPageNum() - 1) * nearBySellHousesDo.getPageSize()).setSize(nearBySellHousesDo.getPageSize()).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getSellHouseByChoose(BoolQueryBuilder booleanQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, String keyWord, Integer order,int pageSize,int pageNum) {

        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = null;
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        if(location!=null && sort!=null){
            srb.setPostFilter(location).addSort(sort);
        }
        if (order != null && order == 1) {
            searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.DESC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else if (order != null && order == 2) {
            searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.ASC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else {
            //如果含有关键字查询，优先显示关键字
            if (StringTool.isNotBlank(keyWord)){
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("_score",SortOrder.DESC).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            } else {
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            }
        }
        return searchresponse;
    }


    //分词匹配
    private  List<String> fenCiMatching(String keyword,BoolQueryBuilder booleanQueryBuilder, TransportClient client) {

        List<String> searchDistrictsList = new ArrayList<>();
        List<String> searchAreasList = new ArrayList<>();
        List<String> searchTermList = new ArrayList<>();

        if (StringTool.isNotBlank(keyword)) {
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(keyword))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", keyword))
                        .should(QueryBuilders.matchQuery("area", keyword).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", keyword).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", keyword).analyzer("ik_smart")));
                AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE, projhouseIndex, keyword);
                ikRequest.setTokenizer("ik_smart");
                List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
                ikTokenList.forEach(ikToken -> {
                    searchDistrictsList.add(ikToken.getTerm());
                });
            }

            else if (StringUtil.isNotNullString(AreaMap.getAreas(keyword))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", keyword))
                        .should(QueryBuilders.matchQuery("area", keyword).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("houseBusinessName", keyword).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("plotName", keyword).analyzer("ik_smart").boost(2)));
                AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE, projhouseIndex, keyword);
                ikRequest.setTokenizer("ik_max_word");
                List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
                ikTokenList.forEach(ikToken -> {
                    searchAreasList.add(ikToken.getTerm());
                });
            }
            else
            {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate",keyword ).boost(2))
                        .should(QueryBuilders.matchQuery("area", keyword))
                        .should(QueryBuilders.matchQuery("houseBusinessName", keyword))
                        .should(QueryBuilders.matchQuery("plotName", keyword)));
                AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE,projhouseIndex,keyword);
                ikRequest.setTokenizer("ik_max_word");
                List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
                if(keyword.length()>1){
                    ikTokenList.forEach(ikToken -> {
                        if(ikToken.getTerm().length()>1){
                            searchTermList.add(ikToken.getTerm());
                        }
                    });
                } else {
                    ikTokenList.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
                }

            }

        if (!searchDistrictsList.isEmpty())
        {
            return searchDistrictsList;
        }
        if (!searchAreasList.isEmpty())
        {
            return searchAreasList;
        }
        if (!searchTermList.isEmpty())
        {
            return  searchTermList;
        }

        }
        return null;
    }

}
