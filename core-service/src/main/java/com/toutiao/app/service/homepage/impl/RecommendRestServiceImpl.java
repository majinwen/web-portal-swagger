package com.toutiao.app.service.homepage.impl;

import com.toutiao.app.dao.homepage.RecommendEsDao;
import com.toutiao.app.domain.homepage.RecommendTopicDo;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;
import com.toutiao.app.service.homepage.RecommendRestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:40
 * Theme:
 */

@Service
public class RecommendRestServiceImpl implements RecommendRestService {

    @Autowired
    private RecommendEsDao recommendEsDao;

    public static final double LOWEST_PRICE = 1000d;
    public static final double PRICE = 1000d;
    private static final int[] SHOUZHI_VS_GAISHAN = {2,3};
    private static final int[] HAOZHAI_VS_BIESHU = {4,5};

    /**
     * 首页推荐专题
     * @return
     */
    @Override
    public RecommendTopicDomain getRecommendTopic(RecommendTopicDoQuery recommendTopicDoQuery) {

        RecommendTopicDomain recommendTopicDomain = new RecommendTopicDomain();

        TermsQueryBuilder termsQueryBuilderByAreaId = QueryBuilders.termsQuery("areaId", recommendTopicDoQuery.getDistrictId());
        BoolQueryBuilder bqb_plotTags = QueryBuilders.boolQuery();

        String flag = "";
        if(recommendTopicDoQuery.getEndPrice() <= PRICE){//价格小于1000万，推荐首置，改善
            bqb_plotTags.must(QueryBuilders.termsQuery("recommendBuildTagsId", SHOUZHI_VS_GAISHAN));
            flag = "lower1000";
        }else if(recommendTopicDoQuery.getEndPrice() > PRICE){//价格大于1000万，推荐豪宅，别墅
            bqb_plotTags.must(QueryBuilders.termsQuery("recommendBuildTagsId", HAOZHAI_VS_BIESHU));
            flag = "higher1000";
        }

        RangeQueryBuilder rangeQueryBuilder = null;
        if (recommendTopicDoQuery.getBeginPrice()!=0 && recommendTopicDoQuery.getEndPrice()!=0) {
            rangeQueryBuilder = QueryBuilders.rangeQuery("houseTotalPrices").gte(recommendTopicDoQuery.getBeginPrice()).lte(recommendTopicDoQuery.getEndPrice());
        }else if(recommendTopicDoQuery.getBeginPrice()!=0 && recommendTopicDoQuery.getEndPrice()==0){
            rangeQueryBuilder = QueryBuilders.rangeQuery("houseTotalPrices").gte(recommendTopicDoQuery.getBeginPrice());
        }else if(recommendTopicDoQuery.getBeginPrice()==0 && recommendTopicDoQuery.getEndPrice()!=0){
            rangeQueryBuilder = QueryBuilders.rangeQuery("houseTotalPrices").lte(recommendTopicDoQuery.getEndPrice());
        }
        bqb_plotTags.must(rangeQueryBuilder);
        bqb_plotTags.must(termsQueryBuilderByAreaId);


        TermQueryBuilder termQuery_isDel= QueryBuilders.termQuery("isDel",0);
        TermQueryBuilder termQuery_isClaim= QueryBuilders.termQuery("is_claim",1);
        BoolQueryBuilder bqb_isCutPrice = QueryBuilders.boolQuery();
        //价格洼地
        bqb_isCutPrice.must(QueryBuilders.termQuery("isCutPrice",1));
        bqb_isCutPrice.must(rangeQueryBuilder);
        bqb_isCutPrice.must(termsQueryBuilderByAreaId);
        bqb_isCutPrice.must(termQuery_isClaim);
        bqb_isCutPrice.must(termQuery_isDel);
        BoolQueryBuilder bqb_isLowPrice = QueryBuilders.boolQuery();
        //捡漏房
        bqb_isLowPrice.must(QueryBuilders.termQuery("isLowPrice",1));
        bqb_isLowPrice.must(rangeQueryBuilder);
        bqb_isLowPrice.must(termsQueryBuilderByAreaId);
        bqb_isLowPrice.must(termQuery_isClaim);
        bqb_isLowPrice.must(termQuery_isDel);
        BoolQueryBuilder bqb_isMustRob = QueryBuilders.boolQuery();
        //逢出毕抢
        bqb_isMustRob.must(QueryBuilders.termQuery("isMustRob",1));
        bqb_isMustRob.must(rangeQueryBuilder);
        bqb_isMustRob.must(termsQueryBuilderByAreaId);
        bqb_isMustRob.must(termQuery_isClaim);
        bqb_isMustRob.must(termQuery_isDel);

        List<Map<String,Map<String,RecommendTopicDo>>> list_all = new ArrayList<>();

        SearchResponse sp_isCutPrice=recommendEsDao.getRecommendByRecommendHouseTags(bqb_isCutPrice);
        List<Map<String,Map<String,RecommendTopicDo>>> list_isCutPrice= cleanEsdata(sp_isCutPrice,"isCutPrice");

        SearchResponse sp_isMustRob=recommendEsDao.getRecommendByRecommendHouseTags(bqb_isMustRob);
        List<Map<String,Map<String,RecommendTopicDo>>> list_isMustRob= cleanEsdata(sp_isMustRob,"isMustRob");

        SearchResponse sp_isLowPrice=recommendEsDao.getRecommendByRecommendHouseTags(bqb_isLowPrice);
        List<Map<String,Map<String,RecommendTopicDo>>> list_isLowPrice= cleanEsdata(sp_isLowPrice,"isLowPrice");

        SearchResponse recommendByRecommendBuildTags = recommendEsDao.getRecommendByRecommendBuildTags(bqb_plotTags);
        List<Map<String,Map<String,RecommendTopicDo>>> map = cleanEsdata(recommendByRecommendBuildTags,flag);

        list_all.addAll(list_isCutPrice);
        list_all.addAll(list_isMustRob);
        list_all.addAll(list_isLowPrice);
        list_all.addAll(map);
        Map mapResult = mapCombine(list_all);
        recommendTopicDomain.setData(mapResult);
        return recommendTopicDomain;
    }


    public List<Map<String,Map<String,RecommendTopicDo>>> cleanEsdata(SearchResponse searchResponse, String flag){
        List<Map<String,Map<String,RecommendTopicDo>>> mapList = new ArrayList<>();
        Terms areaIdBucket = searchResponse.getAggregations().get("areaId");
        if(null != areaIdBucket){
            if(areaIdBucket.getBuckets().size()>0){
                int size = areaIdBucket.getBuckets().size();
                for(int i=0; i<size; i++){
                    Map<String,Map<String,RecommendTopicDo>> mapMap = new HashMap<>();
                    RecommendTopicDo recommendTopicDo = new RecommendTopicDo();
                    Map<String,RecommendTopicDo> recommendTopicDoMap = new HashMap<>();
                    Terms.Bucket bucket = areaIdBucket.getBuckets().get(i);
                    InternalCardinality internalCardinality = bucket.getAggregations().get("count");
                    recommendTopicDo.setCount((int)internalCardinality.getValue());
                    InternalMin lowestPrice = bucket.getAggregations().get("minPrice");
                    InternalMax highestPrice = bucket.getAggregations().get("maxPrice");
                    recommendTopicDo.setLowestPrice(lowestPrice.getValue());
                    recommendTopicDo.setHighestPrice(highestPrice.getValue());
                    recommendTopicDoMap.put(flag,recommendTopicDo);
                    mapMap.put(bucket.getKeyAsString(),recommendTopicDoMap);

                    mapList.add(mapMap);
                }
            }
        }

        return mapList;
    }




    public static Map mapCombine(List<Map<String,Map<String,RecommendTopicDo>>> list) {
        Map<Object, List> map = new HashMap<>();
        for (Map m : list) {
            Iterator<Object> it = m.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (!map.containsKey(key)) {
                    List newList = new ArrayList<>();
                    newList.add(m.get(key));
                    map.put(key, newList);
                } else {
                    map.get(key).add(m.get(key));
                }
            }
        }
        return map;
    }
}
