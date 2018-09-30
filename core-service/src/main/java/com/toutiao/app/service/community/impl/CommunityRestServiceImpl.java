package com.toutiao.app.service.community.impl;

import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.BuildTags;
import com.toutiao.web.dao.mapper.officeweb.BuildTagsMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-21
 * Time:   11:52
 * Theme:
 */

@Service
public class CommunityRestServiceImpl implements CommunityRestService{
    @Autowired
    private BuildTagsMapper buildTagsMapper;

    /**
     * 豪宅
     朝阳17  海淀12  东城8  西城5 通州1 昌平1 丰台5

     别墅
     石景山18 朝阳13 海淀33   西城14 通州18 昌平7 丰台2 大兴20 房山14 门头沟3 顺义34

     首置
     朝阳10 海淀8  通州10 昌平14 丰台13 大兴12 房山13 门头沟9 石景山9 顺义10


     改善
     朝阳15 海淀12 东城12  西城12 通州13 昌平14 丰台11 大兴11 房山10 石景山10 顺义11

     * @return
     */
    @Override
    public Map<Integer, Map<String, Integer>> getCountByBuildTags(Integer cityId) {


        Map<Integer, Map<String, Integer>> resMap = new HashMap<>();

        List<BuildTags> buildTagsList =buildTagsMapper.buildTagsList(cityId);

        //豪宅
        Map<String, Integer> luxuryHouseCountMap = new HashMap<>();

        //别墅
        Map<String, Integer> villaCountMap = new HashMap<>();

        //首次置业
        Map<String, Integer> firstHomeCountMap = new HashMap<>();

        //改善生活 朝阳15 海淀12 东城12  西城12 通州13 昌平14 丰台11 大兴11 房山10 石景山10 顺义11
        Map<String, Integer> changeHomeCountMap = new HashMap<>();

        //近公园
        Map<String, Integer> parkCountMap = new HashMap<>();

        for (BuildTags buildTags:buildTagsList) {
                if (buildTags.getType() == 4){
                  luxuryHouseCountMap.put(buildTags.getDistrictId().toString(),buildTags.getAmount());
                }else if (buildTags.getType() == 5){
                    villaCountMap.put(buildTags.getDistrictId().toString(),buildTags.getAmount());
                } else if (buildTags.getType() == 2){
                    firstHomeCountMap.put(buildTags.getDistrictId().toString(),buildTags.getAmount());
                }else if (buildTags.getType() == 3){
                    changeHomeCountMap.put(buildTags.getDistrictId().toString(),buildTags.getAmount());
                }else if (buildTags.getType() == 6){
                    parkCountMap.put(buildTags.getParkName(),buildTags.getAmount());
                }
        }

        resMap.put(2,firstHomeCountMap);
        resMap.put(3,changeHomeCountMap);
        resMap.put(4,luxuryHouseCountMap);
        resMap.put(5,villaCountMap);
        resMap.put(6,parkCountMap);

        return resMap;
    }
}
