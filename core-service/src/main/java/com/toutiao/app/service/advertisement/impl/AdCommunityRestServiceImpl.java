package com.toutiao.app.service.advertisement.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.advertisement.AdCommunityEsDao;
import com.toutiao.app.domain.plot.PlotTop50Do;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.service.advertisement.AdCommunityRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   18:17
 * Theme:
 */

@Service
public class AdCommunityRestServiceImpl implements AdCommunityRestService {

    @Autowired
    private AdCommunityEsDao adCommunityEsDao;

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;


    @Override
    public List<PlotTop50Do> getExcellentCommunityByIds(Integer[] communityIds, String city) {

        int [] isTop={1};
        List<PlotTop50Do> plotTop50Dos=new ArrayList<>();
        Map<String,PlotTop50Do> communityMap = new HashMap<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId",isTop));
        boolQueryBuilder.must(termsQuery("_id",communityIds));

        SearchResponse searchResponse = adCommunityEsDao.getAdCommunity(boolQueryBuilder, city);
        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit:hits){
            String sourceAsString = hit.getSourceAsString();
            PlotTop50Do plotTop50Do = JSON.parseObject(sourceAsString, PlotTop50Do.class);
            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(plotTop50Do.getId(), CityUtils.getCity());
            if(plotsEsfRoomCountDomain.getTotalCount()!= null){
                plotTop50Do.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
            }else{
                plotTop50Do.setHouseCount(0);
            }

            communityMap.put(hit.getId(),plotTop50Do);

        }

        for(Integer communityId : communityIds){
            if(communityMap.containsKey(communityId.toString())){
                PlotTop50Do plotTop50Do = communityMap.get(communityId.toString());
                plotTop50Dos.add(plotTop50Do);
            }
        }
        return plotTop50Dos;
    }
}
