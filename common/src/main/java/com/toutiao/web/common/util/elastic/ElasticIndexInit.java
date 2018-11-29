package com.toutiao.web.common.util.elastic;

import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.constant.elastic.ESIndexConstant;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticIndexInit.class);

    public final static String CITY_BJ = "bj";
    //新房
    @Value("${bdw.newhouse.index}")
    private String newHouseIndex;
    @Value("${bdw.newhouse.parent.type}")
    private String newHouseType_t1;
//    @Value("${bdw.newhouse.child.type}")
//    private String newHouseType_t2;

    //新楼盘动态
    @Value("${bdw.dynamic.index}")
    private String dynamicIndex;
    @Value("${bdw.dynamic.type}")
    private String dynamicType;

    //小区
    @Value("${bdw.villages.index}")
    private String plotIndex;
    @Value("${bdw.villages.parent.type}")
    private String plotType_t1;
//    @Value("${bdw.villages.child.type}")
//    private String plotType_t2;

    //二手房
    @Value("${bdw.esf.index}")
    private String esfIndex;
    @Value("${bdw.esf.type}")
    private String esfType;

    //租房
    @Value("${bdw.zufang.rent.index}")
    private String rentIndex;
    @Value("${bdw.zufang.rent.type}")
    private String rentType;


    //二手房认领(cpc广告投放)
    @Value("${bdw.claim.esfhouse.index}")
    private String claimEsfIndex;
    @Value("${bdw.claim.esfhouse.type}")
    private String claimEsfType;

    //租房认领(cpc广告投放)
    @Value("${bdw.claim.renthouse.index}")
    private String claimRentIndex;
    @Value("${bdw.claim.renthouse.type}")
    private String claimRentType;

    //经纪人信息
    @Value("${bdw.agent.index}")
    private String agentIndex;
    @Value("${bdw.agent.type}")
    private String agentType;

    //关键字推荐
    @Value("${bdw.search.engines}")
    private String enginesIndex;
    @Value("${bdw.search.engines.type}")
    private String enginesType;

    //商圈推荐
    @Value("${bdw.search.scope}")
    private String scopeIndex;
    @Value("${bdw.search.scope.type}")
    private String scopeType;

    //商圈户型均价
    @Value("${bdw.areaRoom.index}")
    private String areaRoomIndex;
    @Value("${bdw.areaRoom.type}")
    private String areaRoomType;

    //区域商圈均价
    @Value("${bdw.dbavgprice.index}")
    private String dbAvgPriceIndex;
    @Value("${bdw.dbavgprice.type}")
    private String dbAvgPriceType;

    //地铁
    @Value("${bdw.subwayhouse.index}")
    private String subwayHousePriceIndex;
    @Value("${bdw.subwayhouse.type}")
    private String subwayHousePriceType;


    //城市
    @Value("${city.bidewu.substation}")
    private String cityIds;
    /**
     * 二手房全量索引
     */
    private String esfFullAmountIndex;
    /**
     * 二手房全量类型
     */
    private String esfFullAmountType;

    public static Map<String,Map<String, String>> esIndexMaps = new HashMap<>();

    @Override
    public void run(String... strings) throws Exception {

//        System.out.println("初始化ElasticSearch城市分站索引！＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋");
        LOGGER.info("初始化ElasticSearch城市分站索引！＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋");

        String[] city = cityIds.split("\\.");
        int cityCount = city.length;
        for(int i=0; i < cityCount; i++){
            Map<String ,String> esMap = new HashMap<>();
            if(!"".equals(city[i]) && city.length>0){
                esMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex+"_"+city[i]);

//                esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2+"_"+city[i]);
                esMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.PLOT_INDEX,plotIndex+"_"+city[i]);

//                esMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2+"_"+city[i]);
                esMap.put(ESIndexConstant.ESF_INDEX,esfIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.RENT_INDEX,rentIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.AGENT_INDEX,agentIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex+"_"+city[i]);

                esMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex+"_"+city[i]);


                if(CITY_BJ.equals(city[i])){
                    esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1);
                    esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType);
                    esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1);
                    esMap.put(ESIndexConstant.ESF_TYPE,esfType);
                    esMap.put(ESIndexConstant.RENT_TYPE,rentType);
                    esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType);
                    esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType);
                    esMap.put(ESIndexConstant.AGENT_TYPE,agentType);
                    esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType);
                    esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType);
                    esMap.put(ESIndexConstant.AREA_ROOM_TYPE,areaRoomType);

                }else{
                    esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1+"_"+city[i]);
                    esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType+"_"+city[i]);
                    esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1+"_"+city[i]);
                    esMap.put(ESIndexConstant.ESF_TYPE,esfType+"_"+city[i]);
                    esMap.put(ESIndexConstant.RENT_TYPE,rentType+"_"+city[i]);
                    esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType+"_"+city[i]);
                    esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType+"_"+city[i]);
                    esMap.put(ESIndexConstant.AGENT_TYPE,agentType+"_"+city[i]);
                    esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType+"_"+city[i]);
                    esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType+"_"+city[i]);
                    esMap.put(ESIndexConstant.AREA_ROOM_TYPE,areaRoomType+"_"+city[i]);

                }

                esIndexMaps.put(city[i],esMap);
            }
        }
        //添加公共elastic索引
        Map<String ,String> map = new HashMap<>();
        map.put(ESIndexConstant.ESF_FULL_AMOUNT_INDEX,esfFullAmountIndex);
        map.put(ESIndexConstant.ESF_FULL_AMOUNT_TYPE,esfFullAmountType);
        map.put(ESIndexConstant.SUBWAY_HOUSE_PRICE_INDEX,subwayHousePriceIndex);
        map.put(ESIndexConstant.SUBWAY_HOUSE_PRICE_TYPE,subwayHousePriceType);
        map.put(ESIndexConstant.DISTRICT_BIZCIRCLE_AVERAGE_PRICE_INDEX,dbAvgPriceIndex);
        map.put(ESIndexConstant.DISTRICT_BIZCIRCLE_AVERAGE_PRICE_TYPE,dbAvgPriceType);

        esIndexMaps.put(CityConstant.ABBREVIATION_QUANGUO,map);
    }
}
