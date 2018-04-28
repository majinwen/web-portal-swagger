package com.toutiao.app.service.sellhouse.impl;
import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;
    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;

    @Override
    public SellAndClaimHouseDetailsDo getSellHouseByHouseId(String houseId) {

        //二手房房源详情
        SellAndClaimHouseDetailsDo sellAndClaimHouseDetailsDo = new SellAndClaimHouseDetailsDo();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if ("FS".equals(houseId.substring(0,2))){
            booleanQueryBuilder.must(QueryBuilders.termQuery("claimHouseId", houseId));
        }else {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        }
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        if (searchHists.length>0){
            for (SearchHit searchHit : searchHists) {
                details = searchHit.getSourceAsString();
            }
            sellAndClaimHouseDetailsDo = JSON.parseObject(details,SellAndClaimHouseDetailsDo.class);
        }
        return sellAndClaimHouseDetailsDo;
    }



    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    @Override
    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            long time = System.currentTimeMillis();
            long index = (time / 600000) % hits.length;
            String details = hits[(int) index].getSourceAsString();
            AgentsBySellHouseDo agentsBySellHouseDo = JSON.parseObject(details,AgentsBySellHouseDo.class);
            return agentsBySellHouseDo;
        }else{
            return null;
        }
    }

    /**
     * 二手房，房源列表
     * @param sellHouseQueryDo
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseQueryDo);

        Date date = new Date();
        String pastDateOfMonth = DateUtil.getPastDate(30)+" 00:00:00";
        String pastDateOfWeek = DateUtil.getPastDate(7)+" 00:00:00";
        String nowDate = DateUtil.format(date)+" 00:00:00";
        //获取认领房源中30天内有价格变动的房源
        BoolQueryBuilder queryBuilderOfMonth = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",1));
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        queryBuilderOfMonth.should(QueryBuilders.rangeQuery("create_time").gt(pastDateOfMonth).lte(nowDate));
        queryBuilderOfMonth.should(QueryBuilders.rangeQuery("price_increase_decline").gt(0));
        boolQueryBuilder.must(queryBuilderOfMonth);
        //获取7天内导入的，并被认领的
        BoolQueryBuilder queryBuilderOfWeek = QueryBuilders.boolQuery();
        queryBuilderOfWeek.should(QueryBuilders.rangeQuery("import_time").gt(pastDateOfWeek).lte(nowDate));
        boolQueryBuilder.must(queryBuilderOfWeek);
        FunctionScoreQueryBuilder query = getQuery(sellHouseQueryDo,boolQueryBuilder);
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query,1,10);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        for (SearchHit searchHit : searchHists) {
            String details = searchHit.getSourceAsString();
            SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);
            sellHouseDos.add(sellHouseDo);
        }
        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal(searchHists.length);
        return sellHouseDomain;
    }


    /**
     * 查询二手房推荐房源
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getRecommendSellHouse(SellHouseDoQuery sellHouseDoQuery) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",1));
        boolQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));
        FunctionScoreQueryBuilder query = getQuery(sellHouseDoQuery,boolQueryBuilder);
        SearchResponse searchResponse = sellHouseEsDao.getRecommendSellHouse(query,sellHouseDoQuery.getUid(),sellHouseDoQuery.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        for (SearchHit searchHit : searchHists) {
            String details = searchHit.getSourceAsString();
            SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);
            sellHouseDos.add(sellHouseDo);
        }
        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal(searchHists.length);

        return sellHouseDomain;
    }



    public FunctionScoreQueryBuilder getQuery(SellHouseDoQuery sellHouseDoQuery,BoolQueryBuilder boolQueryBuilder){
        FunctionScoreQueryBuilder query = null;
        List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword());
        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())){
            if(searchKeyword!=null && searchKeyword.size() > 0){
                int searchTermSize = searchKeyword.size();
                FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchTermSize];
                if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                    for(int i=0 ;i<searchKeyword.size();i++){
                        QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize-i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }else if(StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))){
                    for(int i=0 ;i<searchKeyword.size();i++){
                        QueryBuilder filter = QueryBuilders.termsQuery("area",searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize-i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }else {
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }
                query =QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }else {
            query =QueryBuilders.functionScoreQuery(boolQueryBuilder);
        }
        return query;

    }

}
