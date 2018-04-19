package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.NearbyPlotsEsDao;
import com.toutiao.web.common.util.ESClientTools;
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
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NearbyPlotsEsDaoImpl implements NearbyPlotsEsDao{

    @Value("${plot.index}")
    private String index ;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;
    @Autowired
    private ESClientTools esClientTools;



    /**
     * 根据用户坐标获取用户附近小区列表
     * @param geoDistanceQueryBuilder 坐标
     * @param sort 距离计算
     * @param boolQueryBuilder 筛选条件
     * @param pageNum 起始
     * @param pageSize 每页查询数量
     * @return
     */
    @Override
    public SearchResponse queryNearbyPlotsListByUserCoordinate(GeoDistanceQueryBuilder geoDistanceQueryBuilder, GeoDistanceSortBuilder sort,
                                                               BoolQueryBuilder boolQueryBuilder, String keyword, Integer pageNum, Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse;

        if(StringUtil.isNotNullString(keyword)){
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            FunctionScoreQueryBuilder query = getSearchKeywords(keyword,boolQueryBuilder);
            searchResponse = srb.setQuery(query).setPostFilter(geoDistanceQueryBuilder).setFrom((pageNum - 1) * pageSize).setSize(pageSize).execute().actionGet();
        }else{
            searchResponse = client.prepareSearch(index).setTypes(parentType).addSort("level", SortOrder.ASC)
                    .addSort("plotScore", SortOrder.DESC).setPostFilter(geoDistanceQueryBuilder)
                    .setQuery(boolQueryBuilder).addSort(sort).setFrom((pageNum-1)*pageSize).setSize(pageSize).execute().actionGet();
        }

        return searchResponse;
    }


    /**
     * 获取关键字分词
     * @param keyword
     * @return
     */
    private FunctionScoreQueryBuilder getSearchKeywords(String keyword, BoolQueryBuilder boolQueryBuilder){

        FunctionScoreQueryBuilder query = null;

        List<String> analyzeKeywordDistrictsList = new ArrayList<>();
        List<String> analyzeKeywordAreasList = new ArrayList<>();
        List<String> analyzeKeywordTermList = new ArrayList<>();

        if(StringUtil.isNotNullString(DistrictMap.getDistricts(keyword))){
            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(esClientTools.init(), AnalyzeAction.INSTANCE,index,keyword);
            ikRequest.setTokenizer("ik_smart");
            List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
            ikTokenList.forEach(ikToken -> { analyzeKeywordDistrictsList.add(ikToken.getTerm()); });
        }else if(StringUtil.isNotNullString(AreaMap.getAreas(keyword))){
            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(esClientTools.init(), AnalyzeAction.INSTANCE,index,keyword);
            ikRequest.setTokenizer("ik_smart");
            List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
            ikTokenList.forEach(ikToken -> { analyzeKeywordAreasList.add(ikToken.getTerm()); });
        }else {
            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(esClientTools.init(), AnalyzeAction.INSTANCE,index,keyword);
            ikRequest.setTokenizer("ik_max_word");
            List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
            if(keyword.length()>1){
                ikTokenList.forEach(ikToken -> {
                    if(ikToken.getTerm().length()>1){
                        analyzeKeywordTermList.add(ikToken.getTerm());
                    }
                });
            } else {
                ikTokenList.forEach(ikToken -> { analyzeKeywordTermList.add(ikToken.getTerm()); });
            }
        }
        //按照小区区域关键字搜索
        if(analyzeKeywordDistrictsList!=null && analyzeKeywordDistrictsList.size() > 0){
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[analyzeKeywordDistrictsList.size()];
            for(int i=0 ;i<analyzeKeywordDistrictsList.size();i++){
                int searchDistrictsSize = analyzeKeywordDistrictsList.size();
                QueryBuilder filter = QueryBuilders.termsQuery("area",analyzeKeywordDistrictsList.get(i));
                filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize-i));
            }
            query =QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                    .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);

            //按照小区名称关键字搜索
        }else if(analyzeKeywordTermList!=null && analyzeKeywordTermList.size() > 0){
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[analyzeKeywordTermList.size()];
            for(int i=0 ;i<analyzeKeywordTermList.size();i++){
                int searchTermSize = analyzeKeywordTermList.size();
                QueryBuilder filter = QueryBuilders.termsQuery("plotName",analyzeKeywordTermList.get(i));
                filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, ScoreFunctionBuilders.weightFactorFunction(searchTermSize-i));
            }
            query =QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                    .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);

            //按照小区商圈关键字搜索
        }else if(analyzeKeywordAreasList!=null && analyzeKeywordAreasList.size() > 0){

            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[analyzeKeywordAreasList.size()];
            for(int i=0 ;i<analyzeKeywordAreasList.size();i++){
                int searchAreasSize = analyzeKeywordAreasList.size();
                QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",analyzeKeywordAreasList.get(i));
                filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, ScoreFunctionBuilders.weightFactorFunction(searchAreasSize-i));
            }
            query =QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                    .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        }

        return query;
    }
}
