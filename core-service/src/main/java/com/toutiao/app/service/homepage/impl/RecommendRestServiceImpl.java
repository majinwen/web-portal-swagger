package com.toutiao.app.service.homepage.impl;

import com.toutiao.app.dao.homepage.RecommendEsDao;
import com.toutiao.app.domain.homepage.RecommendTopicDo;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;
import com.toutiao.app.service.homepage.RecommendRestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

        Map<String,Map<String,RecommendTopicDo>> mapMap = new HashMap<>();

        TermsQueryBuilder termsQueryBuilderByAreaId = QueryBuilders.termsQuery("areaId", recommendTopicDoQuery.getDistrictId());
        BoolQueryBuilder bqb_plotTags = QueryBuilders.boolQuery();

        String flag = "";
        if(recommendTopicDoQuery.getEndPrice() <= PRICE){//价格小于1000万，推荐首置，改善
            bqb_plotTags.must(QueryBuilders.termsQuery("recommendBuildTagsId", SHOUZHI_VS_GAISHAN));
            flag = "2";
        }else if(recommendTopicDoQuery.getEndPrice() > PRICE){//价格大于1000万，推荐豪宅，别墅
            bqb_plotTags.must(QueryBuilders.termsQuery("recommendBuildTagsId", HAOZHAI_VS_BIESHU));
            flag = "3";
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


        BoolQueryBuilder bqb_isCutPrice = QueryBuilders.boolQuery();
        //价格洼地
        bqb_isCutPrice.must(QueryBuilders.termQuery("isCutPrice",1));
        bqb_isCutPrice.must(rangeQueryBuilder);
        bqb_isCutPrice.must(termsQueryBuilderByAreaId);

        BoolQueryBuilder bqb_isLowPrice = QueryBuilders.boolQuery();
        //捡漏房
        bqb_isLowPrice.must(QueryBuilders.termQuery("isLowPrice",1));
        bqb_isLowPrice.must(rangeQueryBuilder);
        bqb_isLowPrice.must(termsQueryBuilderByAreaId);

        BoolQueryBuilder bqb_isMustRob = QueryBuilders.boolQuery();
        //逢出毕抢
        bqb_isMustRob.must(QueryBuilders.termQuery("isMustRob",1));
        bqb_isMustRob.must(rangeQueryBuilder);
        bqb_isMustRob.must(termsQueryBuilderByAreaId);


        SearchResponse sr_isCutPrice = recommendEsDao.getRecommendByRecommendHouseTags(bqb_isCutPrice);

        Terms terms_isCutPrice = sr_isCutPrice.getAggregations().get("areaId");
        if(null != terms_isCutPrice){
            if(terms_isCutPrice.getBuckets().size()>0){
                int size = terms_isCutPrice.getBuckets().size();
                for(int i=0; i<size; i++){
                    RecommendTopicDo recommendTopicDo = new RecommendTopicDo();
                    Map<String,RecommendTopicDo> recommendTopicDoMap = new HashMap<>();
                    Terms.Bucket bucket = terms_isCutPrice.getBuckets().get(i);
                    InternalCardinality internalCardinality = bucket.getAggregations().get("count");
                    recommendTopicDo.setCount((int)internalCardinality.getValue());
                    InternalMin lowestPrice = bucket.getAggregations().get("minPrice");
                    InternalMax highestPrice = bucket.getAggregations().get("maxPrice");
                    recommendTopicDo.setLowestPrice(lowestPrice.getValue());
                    recommendTopicDo.setHighestPrice(highestPrice.getValue());
                    recommendTopicDoMap.put(flag,recommendTopicDo);
                    if(mapMap.containsKey(bucket.getKeyAsString())){

                    }
//                    mapMap.put(bucket.getKeyAsString(),recommendTopicDoMap);
                }
//                recommendTopicDomain.setData(mapMap);
            }
        }





        SearchResponse recommendByRecommendBuildTags = recommendEsDao.getRecommendByRecommendBuildTags(bqb_plotTags);
        Terms areaIdBucket = recommendByRecommendBuildTags.getAggregations().get("areaId");
        if(null != areaIdBucket){
            if(areaIdBucket.getBuckets().size()>0){
                int size = areaIdBucket.getBuckets().size();
                for(int i=0; i<size; i++){
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
//                    mapMap.put(bucket.getKeyAsString(),recommendTopicDoMap);
                }
               // recommendTopicDomain.setData(mapMap);
            }
        }
        return recommendTopicDomain;
    }
}
