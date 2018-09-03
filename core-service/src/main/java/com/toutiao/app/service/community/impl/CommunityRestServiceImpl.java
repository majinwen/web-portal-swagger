package com.toutiao.app.service.community.impl;

import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.web.common.constant.city.CityConstant;
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
     石景山18 朝阳13 海淀33   西城14 通州18 昌平7 丰台2 大兴20 房山14 门头沟3 顺义34

     首置
     朝阳10 海淀8  通州10 昌平14 丰台13 大兴12 房山13 门头沟9 石景山9 顺义10


     改善
     朝阳15 海淀12 东城12  西城12 通州13 昌平14 丰台11 大兴11 房山10 石景山10 顺义11

     * @return
     */
    @Override
    public Map<Integer, Map<String, Integer>> getCountByBuildTags(String city) {


        Map<Integer, Map<String, Integer>> resMap = new HashMap<>();
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
        if(CityConstant.ABBREVIATION_BEIJING.equals(city) || "".equals(city)){
            luxuryHouseCountMap.put("105035",17);
            luxuryHouseCountMap.put("105034",12);
            luxuryHouseCountMap.put("105036",8);
            luxuryHouseCountMap.put("105037",5);
            luxuryHouseCountMap.put("105044",1);
            luxuryHouseCountMap.put("105046",1);
            luxuryHouseCountMap.put("105040",5);


            villaCountMap.put("105041",18);
            villaCountMap.put("105035",13);
            villaCountMap.put("105034",33);
            villaCountMap.put("105037",14);
            villaCountMap.put("105044",18);
            villaCountMap.put("105046",7);
            villaCountMap.put("105040",2);
            villaCountMap.put("105043",20);
            villaCountMap.put("105042",14);
            villaCountMap.put("105051",3);
            villaCountMap.put("105045",34);


            firstHomeCountMap.put("105035",10);
            firstHomeCountMap.put("105034",8);
            firstHomeCountMap.put("105044",10);
            firstHomeCountMap.put("105046",14);
            firstHomeCountMap.put("105040",13);
            firstHomeCountMap.put("105043",12);
            firstHomeCountMap.put("105042",13);
            firstHomeCountMap.put("105051",9);
            firstHomeCountMap.put("105041",9);
            firstHomeCountMap.put("105045",10);


            changeHomeCountMap.put("105035",15);
            changeHomeCountMap.put("105034",12);
            changeHomeCountMap.put("105036",12);
            changeHomeCountMap.put("105037",12);
            changeHomeCountMap.put("105044",13);
            changeHomeCountMap.put("105046",14);
            changeHomeCountMap.put("105040",11);
            changeHomeCountMap.put("105043",11);
            changeHomeCountMap.put("105042",10);
            changeHomeCountMap.put("105041",10);
            changeHomeCountMap.put("105045",11);


            parkCountMap.put("陶然亭公园",18);
            parkCountMap.put("北海公园",5);
            parkCountMap.put("朝阳公园",17);
            parkCountMap.put("颐和园",11);
            parkCountMap.put("南海子公园",16);
            parkCountMap.put("奥林匹克森林公园",23);
            parkCountMap.put("园博园",5);
            parkCountMap.put("紫竹院公园",11);
        }else if(CityConstant.ABBREVIATION_SHANGHAI.equals(city)){
            luxuryHouseCountMap.put("105059",3);
            luxuryHouseCountMap.put("105056",5);
            luxuryHouseCountMap.put("105061",4);
            luxuryHouseCountMap.put("105064",14);
            luxuryHouseCountMap.put("105057",4);
            luxuryHouseCountMap.put("105054",6);
            luxuryHouseCountMap.put("105060",4);
            luxuryHouseCountMap.put("105055",14);
            luxuryHouseCountMap.put("105054",6);
            luxuryHouseCountMap.put("105052",12);


            villaCountMap.put("105061",8);
            villaCountMap.put("105064",11);
            villaCountMap.put("105067",9);
            villaCountMap.put("105066",22);


            firstHomeCountMap.put("105062",13);
            firstHomeCountMap.put("105069",6);
            firstHomeCountMap.put("105059",5);
            firstHomeCountMap.put("105063",8);
            firstHomeCountMap.put("105056",7);
            firstHomeCountMap.put("105061",12);
            firstHomeCountMap.put("105064",28);
            firstHomeCountMap.put("105057",3);
            firstHomeCountMap.put("105067",4);
            firstHomeCountMap.put("105066",4);
            firstHomeCountMap.put("105054",4);
            firstHomeCountMap.put("105060",11);
            firstHomeCountMap.put("105055",5);


            changeHomeCountMap.put("105062",9);
            changeHomeCountMap.put("105059",2);
            changeHomeCountMap.put("105063",3);
            changeHomeCountMap.put("105056",8);
            changeHomeCountMap.put("105061",16);
            changeHomeCountMap.put("105064",16);
            changeHomeCountMap.put("105057",4);
            changeHomeCountMap.put("105066",2);
            changeHomeCountMap.put("105054",5);
            changeHomeCountMap.put("105060",8);
            changeHomeCountMap.put("105055",4);
            changeHomeCountMap.put("105052",6);


//            parkCountMap.put("陶然亭公园",18);
//            parkCountMap.put("北海公园",5);
//            parkCountMap.put("朝阳公园",17);
//            parkCountMap.put("颐和园",11);
//            parkCountMap.put("南海子公园",16);
//            parkCountMap.put("奥林匹克森林公园",23);
//            parkCountMap.put("园博园",5);
//            parkCountMap.put("紫竹院公园",11);
        }






        resMap.put(2,firstHomeCountMap);
        resMap.put(3,changeHomeCountMap);
        resMap.put(4,luxuryHouseCountMap);
        resMap.put(5,villaCountMap);
        resMap.put(6,parkCountMap);

        return resMap;
    }
}
