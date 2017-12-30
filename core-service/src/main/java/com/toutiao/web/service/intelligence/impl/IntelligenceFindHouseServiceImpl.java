package com.toutiao.web.service.intelligence.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.*;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.dao.entity.robot.SubwayDistance;
import com.toutiao.web.dao.mapper.officeweb.*;
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
import java.text.DecimalFormat;
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
    @Autowired
    private PriceTrendMapper priceTrendMapper;

    @Override
    public IntelligenceFh queryUserCheckPrice(IntelligenceQuery intelligenceQuery) {

        IntelligenceFh intelligenceFh = null;
        try {
            intelligenceFh = new IntelligenceFh();
            //复制信息
            BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
            //初始化
            Double plotTotalFirst = null;
            Double plotTotalEnd = null;
            String plotTotal = null;
            //判断用户是否首付还是总价
            //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
            if (StringTool.isNotBlank(intelligenceFh.getDownPayMent()) && StringTool.
                    isNotBlank(intelligenceFh.getMonthPayMent())) {
                plotTotal = intelligenceFh.getDownPayMent() + Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30;
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //获取用户类型
            String userType = intelligenceFh.getUserType();
            //判断总价是否高于400万
            if (StringTool.isNotBlank(plotTotal) && Integer.valueOf(plotTotal) * 10000 >= 4E6) {
                //获取用户的类型
                if (StringTool.isNotBlank(userType) && userType.equalsIgnoreCase("1")) {
                    //需要将用户选择的类型改成自住 改善
                    userType = "2";
                    intelligenceFh.setUserType(userType);
                }
            }
            //根据总价，筛选小区（小区总价范围），得到结果集数量，即为您筛选出X个小区
            int plotCount = intelligenceFindhouseMapper.queryPlotCount(plotTotalFirst, plotTotalEnd);
            //获取相对应的比率
            Double totalPriceRate = totalListedRatioMapper.selectByTotalPrice(plotTotalFirst / 10000, plotTotalEnd / 10000);
            String totalRate = null;
            if (StringTool.isNotBlank(totalPriceRate)) {
                //用户所对应的小区比率
                totalRate = new StringBuffer().append(Double.valueOf(new DecimalFormat("##.0000").format(totalPriceRate)) * 100).append("%").toString();
            }
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
            //初始化
            Double plotTotalFirst = null;
            Double plotTotalEnd = null;
            String plotTotal = null;
            //判断用户是否首付还是总价
            //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
            if (StringTool.isNotBlank(intelligenceFh.getDownPayMent()) && StringTool.
                    isNotBlank(intelligenceFh.getMonthPayMent())) {
                plotTotal = intelligenceFh.getDownPayMent() + Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30;
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //通过总价和户型查询小区数量
            Integer count = intelligenceFindhouseMapper.queryPlotCountByCategoryAndPrice(plotTotalFirst, plotTotalEnd, intelligenceFh.getLayOut());

            //用户选择3居及以上，认为用户优先需要3km内有教育配套和医疗配套，即为用户打了教育配套和医疗配套标签，此处不参与1中描述的结果集统计
            if (StringTool.isNotBlank(intelligenceFh.getLayOut()) && intelligenceFh.getLayOut() >= 3) {
                //教育配套
                intelligenceFh.setSchoolFlag(1);
                //医院配套
                intelligenceFh.setHospitalFlag(1);
            }
            //获取相对应的比率
            //比率
            List<TotalRoomRatio> roomRatio = totalRoomRatioMapper.selectByTotalAndCategory(plotTotalFirst / 10000, plotTotalEnd / 10000, intelligenceFh.getLayOut());
            if (StringTool.isNotBlank(roomRatio) && roomRatio.size() > 0) {
                //用户画像类型
                intelligenceFh.setUserPortrayalType(roomRatio.get(0).getUserPortrayalType());
                //用户比率
                intelligenceFh.setRatio(roomRatio.get(0).getRatio() + "%");
            }
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

            //初始化
            Double plotTotalFirst = null;
            Double plotTotalEnd = null;
            String plotTotal = null;
            //判断用户是否首付还是总价
            //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
            if (StringTool.isNotBlank(intelligenceFh.getDownPayMent()) && StringTool.
                    isNotBlank(intelligenceFh.getMonthPayMent())) {
                plotTotal = intelligenceFh.getDownPayMent() + Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30;
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();
                //上下浮动10%
                plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
                plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
            }
            //保存上下浮动数
            intelligenceFh.setPlotTotalFirst(plotTotalFirst);
            intelligenceFh.setPlotTotalEnd(plotTotalEnd);
            //区域的id
            String[] split = intelligenceFh.getDistrictId().split(",");
            for (int i = 0; i < split.length; i++) {
                //通过总价和户型查询小区数量
                int count1 = intelligenceFindhouseMapper.queryPlotCountByCategoryAndPriceAndDistict(plotTotalFirst, plotTotalEnd, intelligenceFh.getLayOut(), Integer.valueOf(split[i]));
                count += count1;
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
    public List<IntelligenceFindhouse> intelligenceFindHouseServiceByType(IntelligenceQuery intelligenceQuery) {
        //判断类型
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1A(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1B(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1B(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1C(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1C(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2A(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2B(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2B(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2C(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2C(list);
            return finalList;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_3A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType3A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend3A(list);
            return finalList;
        }
        return null;
    }

    /**
     * 功能描述：均价由低到高排序
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/29 13:26
     */
    public List<IntelligenceFindhouse> sortByPrice(List<IntelligenceFindhouse> list) {
        Collections.sort(list, new Comparator<IntelligenceFindhouse>() {
            @Override
            public int compare(IntelligenceFindhouse o1, IntelligenceFindhouse o2) {
                Double o1Price = 0.0;
                Double o2Price = 0.0;
                if (null != o1.getPrice() && o1.getPrice().doubleValue() > 0) {
                    o1Price = o1.getPrice().doubleValue();
                }
                if (null != o1.getEsfPrice() && o1.getEsfPrice().doubleValue() > 0) {
                    o1Price = o1.getEsfPrice().doubleValue();
                }
                if (null != o2.getPrice() && o2.getPrice().doubleValue() > 0) {
                    o2Price = o2.getPrice().doubleValue();
                }
                if (null != o2.getEsfPrice() && o2.getEsfPrice().doubleValue() > 0) {
                    o2Price = o2.getEsfPrice().doubleValue();
                }
                if (o1Price > o2Price) {
                    return 1;
                }
                if (o1Price == o2Price) {
                    return 0;
                }
                return -1;
            }
        });
        return list;
    }

    /**
     * 功能描述：筛选条件1A
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/28 16:55
     */
    public List<IntelligenceFindhouse> recommend1A(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前5%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double number = list.size() * 0.05;
            if ((int) Math.ceil(number) >= 2) {
                int index = random.nextInt((int) Math.ceil(number));
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(number));
                finalList.add(list.get(index2));
                list.remove(index2);
            } else {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }

        //1km内有地铁，随机3个
        List listFour = new ArrayList();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).getHasSubway() && list.get(i).getHasSubway() == 1) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (null != listFour && listFour.size() >= 3) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            listFour.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index2));
            listFour.remove(index2);
            int index3 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index3));
            listFour.remove(index3);
        } else if (null != list && list.size() >= 3) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
            list.remove(index2);
            int index3 = random.nextInt(list.size());
            list.add(list.get(index3));
        }
        return finalList;
    }


    /**
     * 功能描述：筛选条件1B
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/28 16:55
     */
    public List<IntelligenceFindhouse> recommend1B(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前5%-20%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double min = list.size() * 0.05;
            double max = list.size() * 0.2;
            if ((int) (Math.ceil(max) - Math.ceil(min)) >= 2) {
                int index = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index2));
                list.remove(index2);
            } else {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }

        //搜索量前200，随机1个
        if (null != list && list.size() >= 1) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }

        //1km内有地铁，随机2个
        List listFour = new ArrayList();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).getHasSubway() && list.get(i).getHasSubway() == 1) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (null != listFour && listFour.size() >= 2) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            listFour.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index2));
            listFour.remove(index2);
        } else if (null != list && list.size() >= 2) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
            list.remove(index2);
        }
        return finalList;
    }


    /**
     * 功能描述：筛选条件1C
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/28 16:55
     */
    public List<IntelligenceFindhouse> recommend1C(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前20%-50%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double min = list.size() * 0.2;
            double max = list.size() * 0.5;
            if ((int) (Math.ceil(max) - Math.ceil(min)) >= 2) {
                int index = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index2));
                list.remove(index2);
            } else {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }

        //搜索量前200，随机1个
        if (null != list && list.size() >= 1) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }

        //换手率最高的前20%，随机2个
        //按照换手率由高到低排序
        Collections.sort(list, new Comparator<IntelligenceFindhouse>() {
            @Override
            public int compare(IntelligenceFindhouse o1, IntelligenceFindhouse o2) {
                if (o2.getTurnoverRate().doubleValue() > o1.getTurnoverRate().doubleValue()) {
                    return 1;
                }
                if (o2.getTurnoverRate().doubleValue() == o1.getTurnoverRate().doubleValue()) {
                    return 0;
                }
                return -1;
            }
        });
        double number = list.size() * 0.2;
        if ((int) Math.ceil(number) >= 2) {
            int index = random.nextInt((int) Math.ceil(number));
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt((int) Math.ceil(number));
            finalList.add(list.get(index2));
            list.remove(index2);
        } else if (null != list && list.size() >= 2) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
            list.remove(index2);
        }
        return finalList;
    }


    /**
     * 功能描述：筛选条件2A
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/28 16:55
     */
    public List<IntelligenceFindhouse> recommend2A(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前5%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double number = list.size() * 0.05;
            if ((int) Math.ceil(number) >= 2) {
                int index = random.nextInt((int) Math.ceil(number));
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(number));
                finalList.add(list.get(index2));
                list.remove(index2);
            } else if (null != list && list.size() >= 2) {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }
        //搜索量前200，随机1个
        if (null != list && list.size() >= 1) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }
        //环线在四环内，随机2个
        List listFour = new ArrayList();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).getRingRoad() && list.get(i).getRingRoad() <= 4) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (null != listFour && listFour.size() >= 2) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            finalList.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
        } else if (null != list && list.size() >= 2) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
        }
        return finalList;
    }

    /**
     * 功能描述：筛选条件2B
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/28 16:55
     */
    public List<IntelligenceFindhouse> recommend2B(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前5-20%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double min = list.size() * 0.05;
            double max = list.size() * 0.2;
            if ((int) (Math.ceil(max) - Math.ceil(min)) >= 2) {
                int index = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index2));
                list.remove(index2);
            } else {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }
        //搜索量前200，随机1个
        if (null != list && list.size() >= 1) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }
        //环线在四环内，随机2个
        List listFour = new ArrayList();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).getRingRoad() && list.get(i).getRingRoad() <= 4) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (null != listFour && listFour.size() >= 2) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            finalList.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
        } else if (null != list && list.size() >= 2) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
        }
        return finalList;
    }

    /**
     * 功能描述：筛选条件2C
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/29 12:50
     */
    public List<IntelligenceFindhouse> recommend2C(List<IntelligenceFindhouse> listHouse) {
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;
        //小区均价最低的前20-50%， 随机2个
        if (null != listHouse && listHouse.size() >= 2) {
            //按价格排序
            list = sortByPrice(listHouse);

            double min = list.size() * 0.2;
            double max = list.size() * 0.5;
            if ((int) (Math.ceil(max) - Math.ceil(min)) >= 2) {
                int index = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt((int) Math.ceil(max - min)) + (int) Math.ceil(min);
                finalList.add(list.get(index2));
                list.remove(index2);
            } else {
                int index = random.nextInt(list.size());
                finalList.add(list.get(index));
                list.remove(index);
                int index2 = random.nextInt(list.size());
                finalList.add(list.get(index2));
                list.remove(index2);
            }
        }
        //搜索量前200，随机1个
        if (null != list && list.size() >= 1) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }
        //环线在三环内，随机2个
        List listFour = new ArrayList();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (null != list.get(i).getRingRoad() && list.get(i).getRingRoad() <= 3) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (null != listFour && listFour.size() >= 2) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            finalList.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index2));
        } else if (null != list && list.size() >= 2) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
        }
        return finalList;
    }

    /**
     * 功能描述：筛选条件3A
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/29 12:50
     */
    public List<IntelligenceFindhouse> recommend3A(List<IntelligenceFindhouse> listHouse) {
        PriceTrend priceTrend = new PriceTrend();
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;

        for (int i = 0; i < listHouse.size(); i++) {
            if (null == listHouse.get(i).getTurnoverRate() || listHouse.get(i).getTurnoverRate().intValue() <= 0) {
                BigDecimal bigDecimal = new BigDecimal(0);
                listHouse.get(i).setTurnoverRate(bigDecimal);
            }
        }

        //按照换手率由高到低排序
        Collections.sort(listHouse, new Comparator<IntelligenceFindhouse>() {
            @Override
            public int compare(IntelligenceFindhouse o1, IntelligenceFindhouse o2) {
                if (o2.getTurnoverRate().doubleValue() > o1.getTurnoverRate().doubleValue()) {
                    return 1;
                }

                if (o2.getTurnoverRate().doubleValue() == o1.getTurnoverRate().doubleValue()) {
                    return 0;
                }
                return 0;
            }
        });

        if (null != listHouse && listHouse.size() >= 2) {
            int index = random.nextInt(listHouse.size());
            finalList.add(listHouse.get(index));
            listHouse.remove(index);
            int index2 = random.nextInt(listHouse.size());
            finalList.add(listHouse.get(index2));
            listHouse.remove(index2);
        }


        if (null != listHouse && listHouse.size() >= 3) {
            //按价格排序
            list = sortByPrice(listHouse);
        }
        //楼盘均价价格低于区域均价的90%，随机3个
        List listFour = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            //计算平均区域价格
            int priceNumber = 0;
            int totelPrice = 0;
            priceTrend.setBuildingId(list.get(i).getNewcode());
            List<PriceTrend> priceTrends = priceTrendMapper.searchPriceTrendList(priceTrend);
            for (int j = 0; j < priceTrends.size(); j++) {
                if (priceTrends.get(j).getPrice().intValue() != 0) {
                    totelPrice += priceTrends.get(j).getPrice().intValue();
                    priceNumber++;
                }
            }
            if (totelPrice != 0 && list.get(i).getPrice().doubleValue() < ((totelPrice / priceNumber) * 0.9)) {
                listFour.add(list.get(i));
            }
        }
        if (listFour.size() >= 3) {
            int index = random.nextInt(listFour.size());
            finalList.add(listFour.get(index));
            listFour.remove(index);
            int index2 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index2));
            listFour.remove(index2);
            int index3 = random.nextInt(listFour.size());
            finalList.add(listFour.get(index3));
            listFour.remove(index3);
        } else {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
            int index2 = random.nextInt(list.size());
            finalList.add(list.get(index2));
            list.remove(index2);
            int index3 = random.nextInt(list.size());
            finalList.add(list.get(index3));
            list.remove(index3);
        }
        return finalList;
    }

}
