package com.toutiao.app.service.sellhouse.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.sellhouse.SellHouseService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;


    @Override
    public SellHouseDetailsDo getSellHouseByHouseId(Integer houseId) {

        //声明符合查询方法
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        //long totalHouse = hits.getTotalHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        JSONObject json = JSON.parseObject(details);
        System.out.println(json);

        SellHouseDetailsDo sellHouseDetailsDo = JSON.parseObject(details,SellHouseDetailsDo.class);
        return sellHouseDetailsDo;
    }
}
