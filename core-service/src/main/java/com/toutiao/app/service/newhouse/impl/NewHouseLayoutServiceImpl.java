package com.toutiao.app.service.newhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.newhouse.NewHouseLayoutEsDao;
import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDomain;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutPriceDo;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class NewHouseLayoutServiceImpl implements NewHouseLayoutService{

    private static final Logger logger = LoggerFactory.getLogger(NewHouseLayoutServiceImpl.class);

    //索引类型
    @Value("${tt.newhouse.type}")
    private String newHouseType;
    @Autowired
    private NewHouseLayoutEsDao newHouseLayoutEsDao;

    /**
     * 根据新房id获取该id下所有的户型以及数量
     * @param newHouseId
     * @return
     */
    @Override
    public NewHouseLayoutCountDomain getNewHouseLayoutByNewHouseId(Integer newHouseId) {

        List<NewHouseLayoutCountDo> newHouseLayoutCountDoList = new ArrayList<>();
        NewHouseLayoutCountDomain newHouseLayoutCountDomain = new NewHouseLayoutCountDomain();
        BoolQueryBuilder sizeBuilder = QueryBuilders.boolQuery();
        sizeBuilder.must(JoinQueryBuilders.hasParentQuery(newHouseType,QueryBuilders.termQuery("building_name_id",newHouseId) ,false));
        
        SearchResponse searchresponse = newHouseLayoutEsDao.getLayoutCountByNewHouseId(sizeBuilder);

        Map aggMap =searchresponse.getAggregations().asMap();
        LongTerms gradeTerms = (LongTerms) aggMap.get("roomCount");

        Iterator roomBucketIt = gradeTerms.getBuckets().iterator();
        while(roomBucketIt.hasNext()) {
            NewHouseLayoutCountDo newHouseLayoutCountDo = new NewHouseLayoutCountDo();
            Terms.Bucket roomBucket = (Terms.Bucket) roomBucketIt.next();

            newHouseLayoutCountDo.setRoom(roomBucket.getKey());
            newHouseLayoutCountDo.setCount(roomBucket.getDocCount());
            newHouseLayoutCountDoList.add(newHouseLayoutCountDo);
        }

        newHouseLayoutCountDomain.setRooms(newHouseLayoutCountDoList);
        Long totalCount = 0L;
        if(null != newHouseLayoutCountDoList && newHouseLayoutCountDoList.size() > 0){
            for(int i=0; i < newHouseLayoutCountDoList.size(); i++){
                totalCount = totalCount+ Integer.valueOf(String.valueOf(newHouseLayoutCountDoList.get(i).getCount()));
            }
            newHouseLayoutCountDomain.setTotalCount(totalCount);
        }

        return newHouseLayoutCountDomain;
    }

    /**
     * 根据新房id获取该户型下的户型列表
     * @param newHouseId
     * @param roomCount
     * @return
     */
    @Override
    public List<NewHouseLayoutDo> getNewHouseLayoutList(Integer newHouseId, Integer roomCount) {

        BoolQueryBuilder detailsBuilder = boolQuery();
        List<NewHouseLayoutDo> newHouseLayoutDoList = new ArrayList<>();
        detailsBuilder.must(JoinQueryBuilders.hasParentQuery(newHouseType,QueryBuilders.termQuery("building_name_id",newHouseId) ,false));
        if(roomCount > 0){
            detailsBuilder.must(QueryBuilders.termQuery("room",roomCount));
        }

        SearchResponse searchresponse = newHouseLayoutEsDao.getLayoutListByNewHouseIdAndRoomCount(detailsBuilder);

        SearchHits layoutHits = searchresponse.getHits();
        SearchHit[] searchHists = layoutHits.getHits();

        String details = "";
        for (SearchHit hit : searchHists) {
            details = hit.getSourceAsString();
            NewHouseLayoutDo newHouseLayoutDo = JSON.parseObject(details,NewHouseLayoutDo.class);
            newHouseLayoutDoList.add(newHouseLayoutDo);
        }
        return newHouseLayoutDoList;
    }

    /**
     * 根据新房id获取户型价格范围
     * @param newHouseId
     * @return
     */
    @Override
    public NewHouseLayoutPriceDo getNewHouseLayoutPriceByNewHouseId(Integer newHouseId) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        NewHouseLayoutPriceDo newHouseLayoutPriceDo = new NewHouseLayoutPriceDo();
//        boolQueryBuilder.must(JoinQueryBuilders.hasParentQuery(newHouseType,QueryBuilders.termQuery("building_name_id",newHouseId) ,false));
        boolQueryBuilder.must(QueryBuilders.termQuery("building_id",newHouseId));

        SearchResponse searchResponse=newHouseLayoutEsDao.getLayoutPriceByNewHouseId(boolQueryBuilder);

        InternalMin lowestPrice = searchResponse.getAggregations().get("minPrice");
        newHouseLayoutPriceDo.setHouseMinPrice(lowestPrice.getValue());
        InternalMax highestPrice = searchResponse.getAggregations().get("maxPrice");
        newHouseLayoutPriceDo.setHouseMaxPrice(highestPrice.getValue());
        return newHouseLayoutPriceDo;
    }
}
