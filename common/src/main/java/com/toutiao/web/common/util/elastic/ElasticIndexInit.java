package com.toutiao.web.common.util.elastic;

import com.toutiao.web.common.constant.elastic.ESIndexConstant;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-27
 * Time:   12:34
 * Theme: 初始化elastic索引
 */

@Component
@Data
public class ElasticIndexInit implements CommandLineRunner {


    public final static String CITY_BJ = "bj";

    //新房
    @Value("${tt.newhouse.index}")
    private String newHouseIndex;
    @Value("${tt.newhouse.type}")
    private String newHouseType_t1;
    @Value("${tt.newlayout.type}")
    private String newHouseType_t2;

    //新楼盘动态
    @Value("${tt.dynamic.index}")
    private String dynamicIndex;
    @Value("${tt.dynamic.type}")
    private String dynamicType;

    //小区
    @Value("${plot.index}")
    private String plotIndex;
    @Value("${plot.parent.type}")
    private String plotType_t1;
    @Value("${plot.child.type}")
    private String plotType_t2;

    //二手房
    @Value("${tt.projhouse.index}")
    private String esfIndex;
    @Value("${tt.projhouse.type}")
    private String esfType;

    //租房
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;


    //二手房认领(cpc广告投放)
    @Value("${tt.claim.esfhouse.index}")
    private String claimEsfIndex;
    @Value("${tt.claim.esfhouse.type}")
    private String claimEsfType;

    //租房认领(cpc广告投放)
    @Value("${tt.claim.renthouse.index}")
    private String claimRentIndex;
    @Value("${tt.claim.renthouse.type}")
    private String claimRentType;

    //经纪人信息
    @Value("${tt.agent.index}")
    private String agentIndex;
    @Value("${tt.agent.type}")
    private String agentType;

    //关键字推荐
    @Value("${tt.search.engines}")
    private String enginesIndex;
    @Value("${tt.search.engines.type}")
    private String enginesType;

    //商圈推荐
    @Value("${tt.search.scope}")
    private String scopeIndex;
    @Value("${tt.search.scope.type}")
    private String scopeType;

    //商圈户型均价
    @Value("${tt.areaRoom.index}")
    private String areaRoomIndex;
    @Value("${tt.areaRoom.type}")
    private String areaRoomType;
    //城市
    @Value("${city.bidewu.substation}")
    private String cityIds;

    public static Map<String,Map<String, String>> esIndexMaps = new HashMap<>();

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("初始化ElasticSearch城市分站索引！＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋");


        String[] city = cityIds.split("\\.");
        int cityCount = city.length;
        for(int i=0; i < cityCount; i++){
            Map<String ,String> esMap = new HashMap<>();
            if(!CITY_BJ.equals(city[i]) && !"".equals(city[i])){
                plotType_t1 = "plot";
                esMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1+"_"+city[i]);
                esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2+"_"+city[i]);
                esMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType+"_"+city[i]);
                esMap.put(ESIndexConstant.PLOT_INDEX,plotIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1+"_"+city[i]);
                esMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2+"_"+city[i]);
                esMap.put(ESIndexConstant.ESF_INDEX,esfIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.ESF_TYPE,esfType+"_"+city[i]);
                esMap.put(ESIndexConstant.RENT_INDEX,rentIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.RENT_TYPE,rentType+"_"+city[i]);
                esMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType+"_"+city[i]);
                esMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType+"_"+city[i]);
                esMap.put(ESIndexConstant.AGENT_INDEX,agentIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.AGENT_TYPE,agentType+"_"+city[i]);
                esMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType+"_"+city[i]);
                esMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType+"_"+city[i]);
                esMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex+"_"+city[i]);
                esMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType+"_"+city[i]);

                esIndexMaps.put(city[i],esMap);
            }else{
                plotType_t1 = "polt";
                esMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex);
                esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1);
                esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2);
                esMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex);
                esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType);
                esMap.put(ESIndexConstant.PLOT_INDEX,plotIndex);
                esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1);
                esMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2);
                esMap.put(ESIndexConstant.ESF_INDEX,esfIndex);
                esMap.put(ESIndexConstant.ESF_TYPE,esfType);
                esMap.put(ESIndexConstant.RENT_INDEX,rentIndex);
                esMap.put(ESIndexConstant.RENT_TYPE,rentType);
                esMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex);
                esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType);
                esMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex);
                esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType);
                esMap.put(ESIndexConstant.AGENT_INDEX,agentIndex);
                esMap.put(ESIndexConstant.AGENT_TYPE,agentType);
                esMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex);
                esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType);
                esMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex);
                esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType);
                esMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex);
                esMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType);

                esIndexMaps.put(CITY_BJ,esMap);
            }
        }
    }
}
