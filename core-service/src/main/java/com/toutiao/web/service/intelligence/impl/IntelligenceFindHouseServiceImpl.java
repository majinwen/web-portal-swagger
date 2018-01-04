package com.toutiao.web.service.intelligence.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.*;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.dao.entity.robot.SubwayDistance;
import com.toutiao.web.dao.mapper.officeweb.*;
import com.toutiao.web.domain.intelligenceFh.DistictInfo;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
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
import java.text.SimpleDateFormat;
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
    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;

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
                plotTotal = String.valueOf(Integer.valueOf(intelligenceFh.getDownPayMent()) + (Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30 / 10000));
            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();

            }
            //上下浮动10%
            plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
            plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
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
            String totalRate = "";
            if (StringTool.isNotBlank(totalPriceRate)) {
                //用户所对应的小区比率
                intelligenceFh.setRatio(Double.valueOf(String.valueOf(totalPriceRate * 100)));
            } else {
               /* intelligenceFh.setRatio(Double.valueOf(new StringBuffer().append(Double.valueOf(new DecimalFormat("##.000").format(0)) * 100).toString()));
                totalRate = String.valueOf(0);*/
                intelligenceFh.setRatio(Double.valueOf(String.valueOf(0)));
            }
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
                plotTotal = String.valueOf(Integer.valueOf(intelligenceFh.getDownPayMent()) + (Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30 / 10000));

            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();

            }
            //上下浮动10%
            plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
            plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;

            //通过总价和户型查询小区数量
            Integer count = intelligenceFindhouseMapper.queryPlotCountByCategoryAndPrice(plotTotalFirst, plotTotalEnd, intelligenceFh.getLayOut());
            //获取该小区所在区域的信息
            List<DistictInfo> distictInfo = intelligenceFindhouseMapper.queryPlotCountByCategoryAndPrice1(plotTotalFirst, plotTotalEnd, intelligenceFh.getLayOut());
            intelligenceFh.setDistictInfo(distictInfo);
            //用户选择3居及以上，认为用户优先需要3km内有教育配套和医疗配套，即为用户打了教育配套和医疗配套标签，此处不参与1中描述的结果集统计
            if (StringTool.isNotBlank(intelligenceFh.getLayOut()) && intelligenceFh.getLayOut() >= 3) {
                //教育配套
                intelligenceFh.setSchoolFlag(1);
                //医院配套
                intelligenceFh.setHospitalFlag(1);
            }
            //获取相对应的比率
            Integer layOut = intelligenceFh.getLayOut();
            if (StringTool.isNotBlank(layOut) && layOut == 6) {
                List<TotalRoomRatio> roomRatio = totalRoomRatioMapper.selectByTotalAndCategory1(plotTotalFirst / 10000, plotTotalEnd / 10000, intelligenceFh.getLayOut());
                if (StringTool.isNotBlank(roomRatio) && roomRatio.size() > 0) {
                    if (roomRatio.size() > 1) {
                        Double plotRatio = 0.0;
                        for (TotalRoomRatio ratio : roomRatio) {
                            plotRatio += ratio.getRatio();
                        }
                        //用户比率
                        intelligenceFh.setRatio(plotRatio);
                    }
                    //用户比率
                    intelligenceFh.setRatio(roomRatio.get(0).getRatio());
                    //用户画像类型
                    intelligenceFh.setUserPortrayalType(roomRatio.get(0).getUserPortrayalType());

                } else {
                    if (StringTool.isNotBlank(layOut) && layOut == 6 && StringTool.isNotBlank(plotTotal) && Integer.valueOf(plotTotal) * 10000 > 4E6) {
                        //用户画像类型
                        intelligenceFh.setUserPortrayalType(4);
                        intelligenceFh.setRatio(0.5);
                    }
                    if (StringTool.isNotBlank(layOut) && layOut == 6 && StringTool.isNotBlank(plotTotal) && Integer.valueOf(plotTotal) * 10000 <= 4E6) {
                        intelligenceFh.setUserPortrayalType(1);
                        intelligenceFh.setRatio(0.5);
                    }
                }
            } else {
                //比率
                List<TotalRoomRatio> roomRatio = totalRoomRatioMapper.selectByTotalAndCategory(plotTotalFirst / 10000, plotTotalEnd / 10000, intelligenceFh.getLayOut());
                if (StringTool.isNotBlank(roomRatio) && roomRatio.size() > 0) {
                    //用户画像类型
                    intelligenceFh.setUserPortrayalType(roomRatio.get(0).getUserPortrayalType());
                    //用户比率
                    intelligenceFh.setRatio(roomRatio.get(0).getRatio());
                } else {
                    if (StringTool.isNotBlank(plotTotal) && Integer.valueOf(plotTotal) * 10000 > 4E6) {
                        //用户画像类型
                        intelligenceFh.setUserPortrayalType(4);
                        intelligenceFh.setRatio(0.5);
                    }
                    if (StringTool.isNotBlank(plotTotal) && Integer.valueOf(plotTotal) * 10000 <= 4E6) {
                        intelligenceFh.setUserPortrayalType(1);
                        intelligenceFh.setRatio(0.5);
                    }
                }
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
                plotTotal = String.valueOf(Integer.valueOf(intelligenceFh.getDownPayMent()) + (Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30 / 10000));

            }
            //选择总价
            if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
                plotTotal = intelligenceFh.getPreconcTotal();

            }
            //上下浮动10%
            plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
            plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;

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
    public Integer intelligenceFindHouseServiceByType(IntelligenceQuery IntelligenceQuery) {

        //初始化数据
        IntelligenceQuery intelligenceQuery = init(IntelligenceQuery);

        //搜索量前200
        List<IntelligenceFindhouse> starPropertyList = intelligenceFindhouseMapper.queryByStarProperty(intelligenceQuery);

        //判断类型
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1A(list);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1B(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1B(list, starPropertyList);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1C(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1C(list, starPropertyList);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2A(list, starPropertyList);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2B(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2B(list, starPropertyList);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2C(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2C(list, starPropertyList);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_3A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType3A(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend3A(list);
            Integer AIID = save(intelligenceQuery, finalList);
            return AIID;
        }
        return null;
    }

    /**
     * 功能描述：保存结果
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2018/1/3 15:46
     */
    public Integer save(IntelligenceQuery intelligenceQuery, List<IntelligenceFindhouse> finalList) {
        IntelligenceFhRes intelligenceFhRes = new IntelligenceFhRes();
        String str = JSONObject.toJSONString(intelligenceQuery);
        IntelligenceFhResJson intelligenceFhResJson = JSON.parseObject(str, IntelligenceFhResJson.class);
        BeanUtils.copyProperties(intelligenceFhResJson, intelligenceFhRes);
//        if (null!=finalList&&finalList.size()!=0){
//            for (IntelligenceFindhouse intelligence : finalList) {
//                String jsonStr = JSONObject.toJSONString(intelligence);
//                JSONObject fh_result = JSONObject.parseObject(jsonStr);
//                intelligenceFhRes.setFhResult(fh_result);
//                intelligenceFhResMapper.saveData(intelligenceFhRes);
//            }
//        }
        if (null != finalList && finalList.size() != 0) {
            String jsonString = JSONArray.toJSONString(finalList);
            intelligenceFhRes.setFhResult(jsonString);
            intelligenceFhResMapper.saveData(intelligenceFhRes);
            return intelligenceFhRes.getId();
        }
        return null;
    }

    /*public static void main(String[] args) {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time);
        System.out.println(nowTimeStamp);
        String formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(nowTimeStamp);
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        System.out.println(date);
    }*/

    /**
     * 功能描述：初始化数据
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/31 23:00
     */
    public IntelligenceQuery init(IntelligenceQuery intelligenceFh) {
        Double plotTotalFirst = null;
        Double plotTotalEnd = null;
        String plotTotal = null;
        if (StringTool.isNotBlank(intelligenceFh.getDownPayMent()) && StringTool.
                isNotBlank(intelligenceFh.getMonthPayMent())) {
            plotTotal = String.valueOf(Integer.valueOf(intelligenceFh.getDownPayMent()) + (Integer.valueOf(intelligenceFh.getMonthPayMent()) * 12 * 30 / 10000));

        }
        //选择总价
        if (StringTool.isNotBlank(intelligenceFh.getPreconcTotal())) {
            plotTotal = intelligenceFh.getPreconcTotal();

        }
        //上下浮动10%
        plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1)) * 10000;
        plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1)) * 10000;
        intelligenceFh.setMaxTotalPrice(plotTotalEnd);
        intelligenceFh.setMinTotalPrice(plotTotalFirst);

        return intelligenceFh;
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
     * 功能描述：去重
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2017/12/29 18:27
     */
    public void removeRepetition(List finalList, List list, int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int index = random.nextInt(list.size());
            finalList.add(list.get(index));
            list.remove(index);
        }
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
                removeRepetition(finalList, list, 2);
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
            removeRepetition(finalList, listFour, 3);
        } else if (null != list && list.size() >= 3) {
            removeRepetition(finalList, list, 3);
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
    public List<IntelligenceFindhouse> recommend1B(List<IntelligenceFindhouse> listHouse, List<IntelligenceFindhouse> starPropertyList) {
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
                removeRepetition(finalList, list, 2);
            }
        }

        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetition(finalList, starPropertyList, 1);
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
            removeRepetition(finalList, listFour, 2);
        } else if (null != list && list.size() >= 2) {
            removeRepetition(finalList, list, 2);
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
    public List<IntelligenceFindhouse> recommend1C(List<IntelligenceFindhouse> listHouse, List<IntelligenceFindhouse> starPropertyList) {
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
                removeRepetition(finalList, list, 2);
            }
        }

        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetition(finalList, starPropertyList, 1);
        }

        //换手率最高的前20%，随机2个
        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i).getTurnoverRate() || list.get(i).getTurnoverRate().intValue() <= 0) {
                BigDecimal bigDecimal = new BigDecimal(0);
                list.get(i).setTurnoverRate(bigDecimal);
            }
        }
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
            removeRepetition(finalList, list, 2);
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
    public List<IntelligenceFindhouse> recommend2A(List<IntelligenceFindhouse> listHouse, List<IntelligenceFindhouse> starPropertyList) {
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
                removeRepetition(finalList, list, 2);
            }
        }
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetition(finalList, starPropertyList, 1);
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
            removeRepetition(finalList, listFour, 2);
        } else if (null != list && list.size() >= 2) {
            removeRepetition(finalList, list, 2);
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
    public List<IntelligenceFindhouse> recommend2B(List<IntelligenceFindhouse> listHouse, List<IntelligenceFindhouse> starPropertyList) {
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
                removeRepetition(finalList, list, 2);
            }
        }
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetition(finalList, starPropertyList, 1);
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
            removeRepetition(finalList, listFour, 2);
        } else if (null != list && list.size() >= 2) {
            removeRepetition(finalList, list, 2);
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
    public List<IntelligenceFindhouse> recommend2C(List<IntelligenceFindhouse> listHouse, List<IntelligenceFindhouse> starPropertyList) {
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
                removeRepetition(finalList, list, 2);
            }
        }
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetition(finalList, starPropertyList, 1);
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
            removeRepetition(finalList, listFour, 2);
        } else if (null != list && list.size() >= 2) {
            removeRepetition(finalList, list, 2);
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
        List finalList = new ArrayList();
        Random random = new Random();
        List<IntelligenceFindhouse> list = null;

        for (int i = 0; i < listHouse.size(); i++) {
            if (null == listHouse.get(i).getTurnoverRate()) {
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
                return -1;
            }
        });

        if (null != listHouse && listHouse.size() >= 2) {
            removeRepetition(finalList, listHouse, 2);
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
            List<PriceTrend> priceTrends = priceTrendMapper.newhouseTrendList(list.get(i).getNewcode(), null, null);
            for (int j = 0; j < priceTrends.size(); j++) {
                if (priceTrends.get(j).getPrice().intValue() != 0) {
                    totelPrice += priceTrends.get(j).getPrice().intValue();
                    priceNumber++;
                }
            }
            if (totelPrice != 0) {
                double areaAvgprive = (totelPrice / priceNumber) * 0.9;
                Double price = 0.0;
                if (null != list.get(i).getPrice()) {
                    price = list.get(i).getPrice().doubleValue();
                }
                if (null != list.get(i).getEsfPrice()) {
                    price = list.get(i).getEsfPrice().doubleValue();
                }
                if ((null != list.get(i).getPrice() || null != list.get(i).getEsfPrice()) && (price < areaAvgprive)) {
                    listFour.add(list.get(i));
                }
            }
        }
        if (listFour.size() >= 3) {
            removeRepetition(finalList, listFour, 3);
        } else {
            removeRepetition(finalList, list, 3);
        }
        return finalList;
    }

}
