package com.toutiao.web.service.intelligence.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.dao.entity.robot.SubwayDistance;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFindhouseMapper;
import com.toutiao.web.dao.mapper.officeweb.TotalListedRatioMapper;
import com.toutiao.web.dao.mapper.officeweb.TotalRoomRatioMapper;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class IntelligenceFindHouseServiceImpl implements IntelligenceFindHouseService {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.robot.index}")
    private String robotIndex;//索引名称
    @Value("${tt.robot.index}")
    private String robotType;//索引类
    @Autowired
    private TotalListedRatioMapper totalListedRatioMapper;
    @Autowired
    private TotalRoomRatioMapper totalRoomRatioMapper;
    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;
    @Autowired
    private IntelligenceFindhouseMapper intelligenceFindhouseMapper;

    /**
     * 功能描述：根据用户的手机号码获取用户报表相关数据信息
     *
     * @param
     * @return com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes
     * @author zhw
     * @date 2017/12/26 15:45
     */
    @Override
    public IntelligenceFhRes queryUserReport(String usePhone) {

        IntelligenceFhRes intelligenceFhRes = intelligenceFhResMapper.selectByUserPhone(usePhone);

        return intelligenceFhRes;
    }

    @Override
    public Map<String, String> queryUserCheckPrice(IntelligenceQuery intelligenceQuery) {
        //初始化
        Double plotTotal = null;
        //判断用户是否首付还是总价
        //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
        if (StringTool.isNotBlank(intelligenceQuery.getBeginDownPayment()) && StringTool.isNotBlank(intelligenceQuery.getBeginMonthPayment())) {
            plotTotal = intelligenceQuery.getBeginDownPayment() + intelligenceQuery.getBeginMonthPayment() * 12 * 30;
        }
        //选择总价
        if (StringTool.isNotBlank(intelligenceQuery.getPreconcTotal())) {
            plotTotal = intelligenceQuery.getBeginDownPayment() + intelligenceQuery.getBeginMonthPayment() * 12 * 30;
        }
        String userType = intelligenceQuery.getUserType();
        //判断总价是否高于400万
        if (StringTool.isNotBlank(plotTotal) && plotTotal >= 4E6) {
            //获取用户的类型
            if (StringTool.isNotBlank(userType) && userType.equalsIgnoreCase("1")) {
                //需要将用户选择的类型改成自住 改善
                userType="3";
                intelligenceQuery.setUserType(userType);
            }

        }


        return null;
    }

    @Override
    public List<IntelligenceFindhouse> intelligenceFindHouseServiceTypeTwo(IntelligenceQuery intelligenceQuery) {
        //判断类型
        //类型2A
        if ("4".equals(intelligenceQuery.getUserType())){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoA(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }
        //类型2B
        if ("5".equals(intelligenceQuery.getUserType())){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoB(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }
        //类型2C
        if ("6".equals(intelligenceQuery.getUserType())){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoC(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }

        return null;
    }

    /**
     *
     * 功能描述：筛选方法
     * @author zengqingzhou
     * @date 2017/12/27 14:58
     * @param [list]
     * @return java.util.List<com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse>
     */
    public List<IntelligenceFindhouse> recommendHouse(List<IntelligenceFindhouse> list){
        List finalList = new ArrayList();
        BigDecimal tempPrice = null;
        Short tempRingRoad = null;
        Integer tempStarProperty = null;
        //均价最低
        if (list.size() > 1){
            for (int i = 0;i<list.size()-1;i++){
                for (int j = 0; j <list.size()-1-i;j++){

                    double esfPriceJ= Double.parseDouble(null);
                    double esfPriceJ_1= Double.parseDouble(null);
                    if (null!=list.get(j).getEsfPrice()&&list.get(j).getEsfPrice().doubleValue()>0){
                        esfPriceJ= list.get(j).getEsfPrice().doubleValue();
                    }
                    if (null!=list.get(j).getPrice()&&list.get(j).getPrice().doubleValue()>0){
                        esfPriceJ= list.get(j).getPrice().doubleValue();
                    }
                    if (null!=list.get(j+1).getEsfPrice()&&list.get(j+1).getEsfPrice().doubleValue()>0){
                        esfPriceJ_1= list.get(j+1).getEsfPrice().doubleValue();
                    }
                    if (null!=list.get(j+1).getPrice()&&list.get(j+1).getPrice().doubleValue()>0){
                        esfPriceJ_1= list.get(j+1).getPrice().doubleValue();
                    }

                    if (esfPriceJ>esfPriceJ_1){
                        tempPrice = list.get(j).getEsfPrice();
                        list.get(j).setEsfPrice(list.get(j+1).getEsfPrice());
                        list.get(j+1).setEsfPrice(tempPrice);
                    }
                }
            }

            finalList.add(list.get(1));
            finalList.add(list.get(2));
            list.remove(0);
            list.remove(0);
        }

        //环线最小
        if (list.size()>0){
            for (int i = 0;i<list.size()-1;i++){
                for (int j = 0; j <list.size()-1-i;j++){

                    short ringRoad = Short.parseShort(null);
                    short ringRoad_1 = Short.parseShort(null);
                    if (null!=list.get(j).getRingRoad()&&list.get(j).getRingRoad()>0){
                        ringRoad= list.get(j).getRingRoad();
                    }

                    if (null!=list.get(j+1).getRingRoad()&&list.get(j+1).getRingRoad()>0){
                        ringRoad_1= list.get(j+1).getRingRoad();
                    }

                    if (ringRoad>ringRoad_1){
                        tempRingRoad = list.get(j).getRingRoad();
                        list.get(j).setRingRoad(list.get(j+1).getRingRoad());
                        list.get(j+1).setRingRoad(tempRingRoad);
                    }
                }
            }
            finalList.add(list.get(1));
            list.remove(0);
        }

        //明星楼盘
        if (list.size()>1){
            for (int i = 0;i<list.size()-1;i++){
                for (int j = 0; j <list.size()-1-i;j++){

                    Integer starProperty = null;
                    Integer starProperty_1 = null;
                    if (null!=list.get(j).getStarProperty()&&list.get(j).getStarProperty()>0){
                        starProperty= list.get(j).getStarProperty();
                    }

                    if (null!=list.get(j+1).getStarProperty()&&list.get(j+1).getStarProperty()>0){
                        starProperty_1= list.get(j+1).getStarProperty();
                    }

                    if (starProperty>starProperty_1){
                        tempStarProperty = list.get(j).getStarProperty();
                        list.get(j).setStarProperty(list.get(j+1).getStarProperty());
                        list.get(j+1).setStarProperty(tempStarProperty);
                    }
                }
            }
            finalList.add(list.get(1));
        }
    return finalList;
    }

}
