package com.toutiao.web.common.util.elastic;



import com.toutiao.web.common.constant.elastic.ESIndexConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-26
 * Time:   11:32
 * Theme:  es索引工具类
 */
@Component
public class ESIndexUtils {

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

    public static Map<String ,String> esMap = new HashMap<>();


    public static Map<String,Map<String, String>> esIndexMaps = new HashMap<>();




    public Map<String ,String> getESByCity(HttpServletRequest request, HttpServletResponse response,String cityCode){

//        String cityCode = CookieUtils.getCookie(request, response,CookieUtils.COOKIE_NAME_CITY);

//        Map<String ,String> esMap = new HashMap<>();
        Map<String,Map<String, String>> esMaps = new HashMap<>();
//        if(!CITY_BJ.equals(cityCode) && !"".equals(cityCode)){
//            plotType_t1 = "plot";
//            esMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1+"_"+cityCode);
//            esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2+"_"+cityCode);
//            esMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType+"_"+cityCode);
//            esMap.put(ESIndexConstant.PLOT_INDEX,plotIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1+"_"+cityCode);
//            esMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2+"_"+cityCode);
//            esMap.put(ESIndexConstant.ESF_INDEX,esfIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.ESF_TYPE,esfType+"_"+cityCode);
//            esMap.put(ESIndexConstant.RENT_INDEX,rentIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.RENT_TYPE,rentType+"_"+cityCode);
//            esMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType+"_"+cityCode);
//            esMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType+"_"+cityCode);
//            esMap.put(ESIndexConstant.AGENT_INDEX,agentIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.AGENT_TYPE,agentType+"_"+cityCode);
//            esMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType+"_"+cityCode);
//            esMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType+"_"+cityCode);
//            esMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex+"_"+cityCode);
//            esMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType+"_"+cityCode);
//        }else{
//            plotType_t1 = "polt";
//            esMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex);
//            esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1);
//            esMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2);
//            esMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex);
//            esMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType);
//            esMap.put(ESIndexConstant.PLOT_INDEX,plotIndex);
//            esMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1);
//            esMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2);
//            esMap.put(ESIndexConstant.ESF_INDEX,esfIndex);
//            esMap.put(ESIndexConstant.ESF_TYPE,esfType);
//            esMap.put(ESIndexConstant.RENT_INDEX,rentIndex);
//            esMap.put(ESIndexConstant.RENT_TYPE,rentType);
//            esMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex);
//            esMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType);
//            esMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex);
//            esMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType);
//            esMap.put(ESIndexConstant.AGENT_INDEX,agentIndex);
//            esMap.put(ESIndexConstant.AGENT_TYPE,agentType);
//            esMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex);
//            esMap.put(ESIndexConstant.ENGINES_TYPE,enginesType);
//            esMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex);
//            esMap.put(ESIndexConstant.SCOPE_TYPE,scopeType);
//            esMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex);
//            esMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType);
//        }


        System.out.println(esMap.hashCode());
        return esMap;
    }





    public Map<String ,String> getESByCity1(HttpServletRequest request, HttpServletResponse response,String cityCode){

//        String cityCode = CookieUtils.getCookie(request, response,CookieUtils.COOKIE_NAME_CITY);

        Map<String ,String> esIndexMap = new HashMap<>();

//        Map<String,Map<String, String>> esIndexMaps = new HashMap<>();
//        if(!CITY_BJ.equals(cityCode) && !"".equals(cityCode)){
//            plotType_t1 = "plot";
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.PLOT_INDEX,plotIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.ESF_INDEX,esfIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.ESF_TYPE,esfType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.RENT_INDEX,rentIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.RENT_TYPE,rentType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.AGENT_INDEX,agentIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.AGENT_TYPE,agentType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.ENGINES_TYPE,enginesType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.SCOPE_TYPE,scopeType+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex+"_"+cityCode);
//            esIndexMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType+"_"+cityCode);
//
//            esIndexMaps.put(cityCode,esIndexMap);
//        }else{
//            plotType_t1 = "polt";
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_INDEX,newHouseIndex);
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T1,newHouseType_t1);
//            esIndexMap.put(ESIndexConstant.NEW_HOUSE_TYPE_T2,newHouseType_t2);
//            esIndexMap.put(ESIndexConstant.DYNAMIC_INDEX,dynamicIndex);
//            esIndexMap.put(ESIndexConstant.DYNAMIC_TYPE,dynamicType);
//            esIndexMap.put(ESIndexConstant.PLOT_INDEX,plotIndex);
//            esIndexMap.put(ESIndexConstant.PLOT_TYPE_T1,plotType_t1);
//            esIndexMap.put(ESIndexConstant.PLOT_TYPE_T2,plotType_t2);
//            esIndexMap.put(ESIndexConstant.ESF_INDEX,esfIndex);
//            esIndexMap.put(ESIndexConstant.ESF_TYPE,esfType);
//            esIndexMap.put(ESIndexConstant.RENT_INDEX,rentIndex);
//            esIndexMap.put(ESIndexConstant.RENT_TYPE,rentType);
//            esIndexMap.put(ESIndexConstant.CLAIM_ESF_INDEX,claimEsfIndex);
//            esIndexMap.put(ESIndexConstant.CLAIM_ESF_TYPE,claimEsfType);
//            esIndexMap.put(ESIndexConstant.CLAIM_RENT_INDEX,claimRentIndex);
//            esIndexMap.put(ESIndexConstant.CLAIM_RENT_TYPE,claimRentType);
//            esIndexMap.put(ESIndexConstant.AGENT_INDEX,agentIndex);
//            esIndexMap.put(ESIndexConstant.AGENT_TYPE,agentType);
//            esIndexMap.put(ESIndexConstant.ENGINES_INDEX,enginesIndex);
//            esIndexMap.put(ESIndexConstant.ENGINES_TYPE,enginesType);
//            esIndexMap.put(ESIndexConstant.SCOPE_INDEX,scopeIndex);
//            esIndexMap.put(ESIndexConstant.SCOPE_TYPE,scopeType);
//            esIndexMap.put(ESIndexConstant.AREA_ROOM_INDEX,areaRoomIndex);
//            esIndexMap.put(ESIndexConstant.AREA_ROOM_TYPE,enginesType);
//            esIndexMaps.put(CITY_BJ,esIndexMap);
//        }


        System.out.println(esMap.hashCode());
        return esMap;
    }


}
