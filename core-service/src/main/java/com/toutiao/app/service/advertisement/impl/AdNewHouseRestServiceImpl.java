package com.toutiao.app.service.advertisement.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.advertisement.AdNewHouseEsDao;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.service.advertisement.AdNewHouseRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   15:23
 * Theme:
 */

@Service
public class AdNewHouseRestServiceImpl implements AdNewHouseRestService {

    @Autowired
    private AdNewHouseEsDao adNewHouseEsDao;

    @Autowired
    private NewHouseLayoutService newHouseLayoutService;

    @Autowired
    private NewHouseEsDao newHouseEsDao;

    @Autowired
    private NewHouseRestService newHouseService;


    @Override
    public NewHouseListDomain getAdNewHouseHomePage(Integer[] newHouseIds, String city) {

        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        Map<String,NewHouseListDo> newHouseMap = new HashMap<>();
        NewHouseListDomain newHouseListVo=new NewHouseListDomain();
        int newHouseCount = 0;
        if(null!=newHouseIds && newHouseIds.length >0){
            newHouseCount = newHouseIds.length;
            BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
            booleanQueryBuilder.must(termQuery("is_approve",1 ));
            booleanQueryBuilder.must(termQuery("is_del", 0));
            booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1,2}));
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
            booleanQueryBuilder.must(termsQuery("_id",newHouseIds));
            SearchResponse adRecommendNewHouse = adNewHouseEsDao.getAdNewHouse(booleanQueryBuilder, city);

            SearchHits hits = adRecommendNewHouse.getHits();
            SearchHit[] searchHists = hits.getHits();
            if(searchHists.length > 0){
                for (SearchHit searchHit : searchHists) {
                    String details=searchHit.getSourceAsString();
                    NewHouseListDo newHouseList= JSON.parseObject(details,NewHouseListDo.class);
                    newHouseMap.put(searchHit.getId(),newHouseList);
                }
                for(Integer newHouseId : newHouseIds){
                    if(newHouseMap.containsKey(newHouseId.toString())){
                        NewHouseListDo newHouseList = newHouseMap.get(newHouseId.toString());
                        newHouseListDoList.add(newHouseList);
                    }
                }
            }
        }
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        newHouseDoQuery.setPageSize(5-newHouseCount);
        NewHouseListDomain newHouseListDomain = newHouseService.getNewHouseList(newHouseDoQuery, city);
        newHouseListDoList.addAll(newHouseListDomain.getData());
        newHouseListVo.setData(newHouseListDoList);
        newHouseListVo.setTotalCount(newHouseListDoList.size());
        return newHouseListVo;
    }

    @Override
    public NewHouseListDomain getAdNewHouseList(Integer[] newHouseIds, String city) {

        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        Map<String,NewHouseListDo> newHouseMap = new HashMap<>();
        NewHouseListDomain newHouseListVo=new NewHouseListDomain();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        booleanQueryBuilder.must(termQuery("is_approve",1 ));
        booleanQueryBuilder.must(termQuery("is_del", 0));
        booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1,2}));
        booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        booleanQueryBuilder.must(termsQuery("_id",newHouseIds));
        SearchResponse adRecommendNewHouse = adNewHouseEsDao.getAdNewHouse(booleanQueryBuilder, city);

        SearchHits hits = adRecommendNewHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                NewHouseListDo newHouseListDos=JSON.parseObject(details,NewHouseListDo.class);

                //获取新房下户型的数量
                NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseListDos.getBuildingNameId(),city);
                if(null!=newHouseLayoutCountDomain.getRooms() && newHouseLayoutCountDomain.getRooms().size() > 0){
                    List<String> roomsType = new ArrayList<>();
                    for(int i=0; i< newHouseLayoutCountDomain.getRooms().size(); i++){
                        roomsType.add(newHouseLayoutCountDomain.getRooms().get(i).getRoom().toString());
                    }
                    String rooms = String.join(",", roomsType);
                    newHouseListDos.setRoomType(rooms);
                }else{
                    newHouseListDos.setRoomType("");
                }
                //新房动态
                BoolQueryBuilder queryBuilderDynamic = boolQuery();//声明符合查询方法
                queryBuilderDynamic.must(QueryBuilders.termQuery("newcode",newHouseListDos.getBuildingNameId()));
                SearchResponse  dynamicResponse =newHouseEsDao.getDynamicByNewCode(queryBuilderDynamic,1,10, city);
                long dynamicTotal = dynamicResponse.getHits().totalHits;//动态总数
                newHouseListDos.setDynamicTotal(dynamicTotal);

                newHouseMap.put(searchHit.getId(),newHouseListDos);
            }

            for(Integer newHouseId : newHouseIds){
                if(newHouseMap.containsKey(newHouseId.toString())){
                    NewHouseListDo newHouseList = newHouseMap.get(newHouseId.toString());
                    newHouseListDoList.add(newHouseList);
                }
            }
            newHouseListVo.setData(newHouseListDoList);
            newHouseListVo.setTotalCount(hits.getTotalHits());
        }

        return newHouseListVo;
    }
}
