package com.toutiao.app.service.community.impl;

import com.toutiao.app.service.community.CommunityRestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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


    /**
     * 豪宅
     朝阳17  海淀12  东城8  西城5 通州1 昌平1 丰台5

     别墅
     朝阳38 海淀27 东城4  西城2 通州18 昌平47 丰台7 大兴37 房山25 门头沟3 顺义36 怀柔13 平谷2 密云16  延庆7

     首置
     朝阳 海淀 东城  西城 通州 昌平 丰台 大兴 房山 门头沟 石景山 顺义
     每个区域都是10

     改善
     朝阳 海淀 东城  西城 通州 昌平 丰台 大兴 房山 石景山 顺义
     每个区域都是10
     * @return
     */
    @Override
    public Map<Integer, Map<String, Integer>> getCountByBuildTags() {


        Map<Integer, Map<String, Integer>> resMap = new HashMap<>();



        //豪宅
        Map<String, Integer> luxuryHouseCountMap = new HashMap<>();
        luxuryHouseCountMap.put("105035",17);
        luxuryHouseCountMap.put("105034",12);
        luxuryHouseCountMap.put("105036",8);
        luxuryHouseCountMap.put("105037",5);
        luxuryHouseCountMap.put("105044",1);
        luxuryHouseCountMap.put("105046",1);
        luxuryHouseCountMap.put("105040",5);

        //别墅
        Map<String, Integer> villaCountMap = new HashMap<>();
        villaCountMap.put("105035",38);
        villaCountMap.put("105034",27);
        villaCountMap.put("105036",4);
        villaCountMap.put("105037",2);
        villaCountMap.put("105044",18);
        villaCountMap.put("105046",47);
        villaCountMap.put("105040",7);
        villaCountMap.put("105043",37);
        villaCountMap.put("105042",25);
        villaCountMap.put("105051",3);
        villaCountMap.put("105045",36);
        villaCountMap.put("105048",13);
        villaCountMap.put("105050",2);
        villaCountMap.put("105047",16);
        villaCountMap.put("105049",7);

        //首次置业
        Map<String, Integer> firstHomeCountMap = new HashMap<>();
        firstHomeCountMap.put("105035",10);
        firstHomeCountMap.put("105034",10);
        firstHomeCountMap.put("105036",10);
        firstHomeCountMap.put("105037",10);
        firstHomeCountMap.put("105044",10);
        firstHomeCountMap.put("105046",10);
        firstHomeCountMap.put("105040",10);
        firstHomeCountMap.put("105043",10);
        firstHomeCountMap.put("105042",10);
        firstHomeCountMap.put("105051",10);
        firstHomeCountMap.put("105041",10);
        firstHomeCountMap.put("105045",10);

        //改善生活
        Map<String, Integer> changeHomeCountMap = new HashMap<>();
        changeHomeCountMap.put("105035",10);
        changeHomeCountMap.put("105034",10);
        changeHomeCountMap.put("105036",10);
        changeHomeCountMap.put("105037",10);
        changeHomeCountMap.put("105044",10);
        changeHomeCountMap.put("105046",10);
        changeHomeCountMap.put("105040",10);
        changeHomeCountMap.put("105043",10);
        changeHomeCountMap.put("105042",10);
        changeHomeCountMap.put("105041",10);
        changeHomeCountMap.put("105045",10);

        //近公园

        Map<String, Integer> parkCountMap = new HashMap<>();
        parkCountMap.put("陶然亭公园",6);
        parkCountMap.put("北海公园",4);
        parkCountMap.put("朝阳公园",8);
        parkCountMap.put("颐和园",1);
        parkCountMap.put("南海子公园",7);
        parkCountMap.put("奥林匹克森林公园",12);
        parkCountMap.put("园博园",2);
        parkCountMap.put("紫竹院公园",4);



        resMap.put(2,firstHomeCountMap);
        resMap.put(3,changeHomeCountMap);
        resMap.put(4,luxuryHouseCountMap);
        resMap.put(5,villaCountMap);
        resMap.put(6,parkCountMap);

        return resMap;
    }
}
