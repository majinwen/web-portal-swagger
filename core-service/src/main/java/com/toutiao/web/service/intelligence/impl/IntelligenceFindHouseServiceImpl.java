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
import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
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

    private static final Integer USERTYPE_1A = 1;
    private static final Integer USERTYPE_1B = 2;
    private static final Integer USERTYPE_1C = 3;
    private static final Integer USERTYPE_2A = 4;
    private static final Integer USERTYPE_2B = 5;
    private static final Integer USERTYPE_2C = 6;
    private static final Integer USERTYPE_3A = 7;
    @Autowired
    private TotalListedRatioMapper totalListedRatioMapper;
    @Autowired
    private TotalRoomRatioMapper totalRoomRatioMapper;
    @Autowired
    private IntelligenceFindhouseMapper intelligenceFindhouseMapper;

    @Override
    public IntelligenceFh queryUserCheckPrice(IntelligenceQuery intelligenceQuery) {

        IntelligenceFh intelligenceFh = null;
        try {
            intelligenceFh = new IntelligenceFh();
            //复制信息
            BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
            //初始化
            Double plotTotal = null;
            //判断用户是否首付还是总价
            //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
            if (StringTool.isNotBlank(intelligenceFh.getDownPayMent()) && StringTool.
                    isNotBlank(intelligenceFh.getMonthPayMent())) {
                plotTotal = intelligenceFh.getDownPayMent() + intelligenceFh.getMonthPayMent() * 12 * 30;
                //保存总价
                intelligenceFh.setTotalPrice(plotTotal);
            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();
                intelligenceFh.setTotalPrice(plotTotal);
            }
            //获取用户类型
            String userType = intelligenceFh.getUserType();
            //判断总价是否高于400万
            if (StringTool.isNotBlank(plotTotal) && plotTotal >= 4E6) {
                //获取用户的类型
                if (StringTool.isNotBlank(userType) && userType.equalsIgnoreCase("1")) {
                    //需要将用户选择的类型改成自住 改善
                    userType = "2";
                    intelligenceFh.setUserType(userType);
                }
            }
            //根据总价，筛选小区（小区总价范围=当前均价*小区全部面积，不只是待售），得到结果集数量，即为您筛选出X个小区
            int plotCount = intelligenceFindhouseMapper.queryPlotCount(plotTotal);
            //获取相对应的比率
            List<TotalListedRatio> totalPriceRate = totalListedRatioMapper.selectByTotalPrice(plotTotal);
            //用户所对应的小区比率
            String totalRate = new StringBuffer().append(totalPriceRate.get(0).getRatio().intValue() * 100).append("%").toString();
            intelligenceFh.setRatio(totalRate);
            intelligenceFh.setPlotCount(plotCount);
            return intelligenceFh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public IntelligenceFh queryUserCheckPriceAndCategory(IntelligenceQuery intelligenceQuery) {
        IntelligenceFh intelligenceFh = null;

        try {
            intelligenceFh = new IntelligenceFh();
            //复制对象信息
            BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
            //通过总价和户型查询小区数量
            Integer count = intelligenceFindhouseMapper.queryPlotCountByCategoryAndPrice(intelligenceFh.getTotalPrice(), intelligenceFh.getLayOut());
            //用户选择3居及以上，认为用户优先需要3km内有教育配套和医疗配套，即为用户打了教育配套和医疗配套标签，此处不参与1中描述的结果集统计
            if (StringTool.isNotBlank(intelligenceFh.getLayOut()) && intelligenceFh.getLayOut() >= 3) {
                //教育配套
                intelligenceFh.setSchoolFlag(true);
                //医院配套
                intelligenceFh.setHospitalFlag(true);
            }
            //获取相对应的比率
            //比率
            TotalRoomRatio roomRatio = totalRoomRatioMapper.selectByTotalAndCategory(intelligenceFh.getTotalPrice(), intelligenceFh.getLayOut());
            //用户画像类型
            intelligenceFh.setUserPortrayalType(roomRatio.getUserPortrayalType());
            //用户比率
            intelligenceFh.setRatio(new StringBuffer().append(roomRatio.getRatio().intValue() * 100).append("%").toString());
            //小区数量
            intelligenceFh.setPlotCount(count);
            return intelligenceFh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：根据区域赛选小区数量
     *
     * @param
     * @return com.toutiao.web.domain.query.IntelligenceQuery
     * @author zhw
     * @date 2017/12/26 21:48
     */
    @Override
    public IntelligenceFh queryPlotCountByDistrict(IntelligenceQuery intelligenceQuery) {
        IntelligenceFh intelligenceFh = null;
        try {
            //初始化
            int count = 0;
            intelligenceFh = new IntelligenceFh();
            //复制信息
            BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
            //区域的id
            String[] split = intelligenceFh.getDistrictId().split(",");
            for (int i = 0; i < split.length; i++) {
                //通过总价和户型查询小区数量
                count += intelligenceFindhouseMapper.queryPlotCountByCategoryAndPriceAndDistict(
                        intelligenceFh.getTotalPrice(), intelligenceFh.getLayOut(), split[i]);

            }
            //保存查询的小区数量
            intelligenceFh.setPlotCount(count);
            return intelligenceFh;
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IntelligenceFindhouse> intelligenceFindHouseServiceTypeTwo(IntelligenceQuery intelligenceQuery) {
        //判断类型
        if(intelligenceQuery.getUserPortrayalType()==USERTYPE_1A){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1A(intelligenceQuery);

        }
        if(intelligenceQuery.getUserPortrayalType()==USERTYPE_1B){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1B(intelligenceQuery);

        }
        if(intelligenceQuery.getUserPortrayalType()==USERTYPE_1C){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1C(intelligenceQuery);

        }
        //类型2A
        if (intelligenceQuery.getUserPortrayalType()==4){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoA(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }
        //类型2B
        if (intelligenceQuery.getUserPortrayalType()==5){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoB(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }
        //类型2C
        if (intelligenceQuery.getUserPortrayalType()==6){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.selectByTypeTwoC(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommendHouse(list);
            return finalList;
        }
        if(intelligenceQuery.getUserPortrayalType()==USERTYPE_3A){
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType3A(intelligenceQuery);

        }

        return null;
    }

    /**
     *
     * 功能描述：筛选方法
     * @author zengqingzhou
     * @date 2017/12/27 14:58
     * @param
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

                    double esfPriceJ = 0.0;
                    double esfPriceJ_1= 0.0;
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

                    short ringRoad = 10;
                    short ringRoad_1 = 10;
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

                    Integer starProperty = 0;
                    Integer starProperty_1 = 0;
                    if (null!=list.get(j).getStarProperty()&&list.get(j).getStarProperty()>0){
                        starProperty= list.get(j).getStarProperty();
                    }

                    if (null!=list.get(j+1).getStarProperty()&&list.get(j+1).getStarProperty()>0){
                        starProperty_1= list.get(j+1).getStarProperty();
                    }

                    if (starProperty<starProperty_1){
                        tempStarProperty = list.get(j).getStarProperty();
                        list.get(j).setStarProperty(list.get(j+1).getStarProperty());
                        list.get(j+1).setStarProperty(tempStarProperty);
                    }
                }
            }
            finalList.add(list.get(1));
            finalList.add(list.get(2));
        }
    return finalList;
    }

}
