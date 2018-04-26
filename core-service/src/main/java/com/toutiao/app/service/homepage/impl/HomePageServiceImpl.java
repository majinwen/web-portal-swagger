package com.toutiao.app.service.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.app.domain.homepage.HomePageEsfDo;
import com.toutiao.app.service.homepage.HomePageRestService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageRestService {
    @Autowired
    private HomePageEsDao homePageEsDao;


    @Override
    public List<HomePageEsfDo> getHomePageEsf() {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("housePhotoTitle", ""));
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        homePageEsDao.getHomePageEsf(boolQueryBuilder);


    }
}
