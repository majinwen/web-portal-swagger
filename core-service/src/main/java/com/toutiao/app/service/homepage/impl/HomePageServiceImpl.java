package com.toutiao.app.service.homepage.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.app.domain.homepage.HomePageEsfDo;
import com.toutiao.app.domain.homepage.HomePageNewHouseDo;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.web.domain.query.ProjHouseInfoResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HomePageServiceImpl implements HomePageRestService {
    @Autowired
    private HomePageEsDao homePageEsDao;


    /**
     *
     * @return
     * 获取二手房5条
     */
    @Override
    public List<HomePageEsfDo> getHomePageEsf() {
        Random random = new Random();
        List<HomePageEsfDo> homePageEsfDos=new ArrayList<>();
        List<HomePageEsfDo> result=new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("housePhotoTitle", ""));
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termsQuery("is_claim","1"));
        SearchResponse searchResponse= homePageEsDao.getHomePageEsf(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit : hits)
        {
            String details = "";
            details=hit.getSourceAsString();
            HomePageEsfDo homePageEsf= JSON.parseObject(details,HomePageEsfDo.class);
            homePageEsfDos.add(homePageEsf);
        }

        if (!homePageEsfDos.isEmpty() && homePageEsfDos.size()>5)
        {
            while (result.size()<5)
            {
                result=hashPush(result,homePageEsfDos.get(random.nextInt(20)));
            }
        }
        return  result;

    }

    /**
     * 获取新房5条
     * @return
     */

    @Override
    public List<HomePageNewHouseDo> getHomePageNewHouse() {

    }


    private List hashPush(List<HomePageEsfDo> result ,  HomePageEsfDo homePageEsfDos ){
        Boolean flag = false;
        if(result.size()>0){
            for (int i = 0; i <result.size() ; i++) {
                if (result.get(i).getClaimHouseId().equals(homePageEsfDos.getClaimHouseId())){
                    flag = true;
                }
            }
        }
        if(!flag){
            result.add(homePageEsfDos);
        }
        return result;
    }
}
