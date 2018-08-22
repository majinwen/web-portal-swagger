package com.toutiao.app.service.Intelligence.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserLoginDomain;
import com.toutiao.app.service.Intelligence.HomePageReportService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.*;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFindhouseMapper;
import com.toutiao.web.dao.mapper.officeweb.PriceTrendMapper;
import com.toutiao.web.dao.mapper.officeweb.TotalRoomRatioMapper;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import com.toutiao.web.service.map.MapService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.util.PGobject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomePageReportServiceImpl implements HomePageReportService {
    private static final Integer USERTYPE_1A = 1;
    private static final Integer USERTYPE_1B = 2;
    private static final Integer USERTYPE_1C = 3;
    private static final Integer USERTYPE_2A = 4;
    private static final Integer USERTYPE_2B = 5;
    private static final Integer USERTYPE_2C = 6;
    private static final Integer USERTYPE_3A = 7;
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    @Autowired
    private TotalRoomRatioMapper totalRoomRatioMapper;
    @Autowired
    private IntelligenceFindhouseMapper intelligenceFindhouseMapper;
    @Autowired
    private PriceTrendMapper priceTrendMapper;
    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;
    @Autowired
    private MapService mapService;


    @Override
    public Integer saveHomePageReport(HttpServletRequest request, UserFavoriteConditionDoQuery userFavoriteConditionDoQuery) {
        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
        String userPhone = null;
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginDomain userLoginDomain = JSONObject.parseObject(user,UserLoginDomain.class);

        if(null != userLoginDomain){
            UserBasicDo userBasic  =userBasicInfoService.queryUserBasic(userLoginDomain.getUserId());
            userPhone = userBasic.getPhone();
        }

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
        intelligenceQuery.setCreateTime(date);

        if(StringTool.isNotEmpty(userFavoriteConditionDoQuery.getBeginPrice())){
            intelligenceQuery.setMinTotalPrice(userFavoriteConditionDoQuery.getBeginPrice());
        }
        if (StringTool.isNotEmpty(userFavoriteConditionDoQuery.getEndPrice())){
            intelligenceQuery.setMaxTotalPrice(userFavoriteConditionDoQuery.getEndPrice());
        }
        if (null!=userFavoriteConditionDoQuery.getLayoutId()&&userFavoriteConditionDoQuery.getLayoutId().length>0){
            intelligenceQuery.setLayoutArray(StringUtils.join(userFavoriteConditionDoQuery.getLayoutId(),","));
        }
        if(null!=userFavoriteConditionDoQuery.getDistrictId()&&userFavoriteConditionDoQuery.getDistrictId().length>0){
            intelligenceQuery.setDistrictArray(StringUtils.join(userFavoriteConditionDoQuery.getDistrictId(),","));
        }
        IntelligenceQuery personas = getPersonas(intelligenceQuery);

        IntelligenceFhRes intelligenceFhRes = intelligenceFindHouseServiceByType1(personas,userPhone);

        if (StringTool.isNotBlank(intelligenceFhRes)&&StringTool.isNotEmpty(intelligenceFhRes.getId())&&intelligenceFhRes.getId()>0) {
            return intelligenceFhRes.getId();
        }
        return 0;
    }

    @Override
    public IntelligenceQuery getPersonas(IntelligenceQuery intelligenceQuery) {
        //用户选择3居及以上，认为用户优先需要3km内有教育配套和医疗配套，即为用户打了教育配套和医疗配套标签，此处不参与1中描述的结果集统计
//        intelligenceQuery.setLayOut(StringUtils.join(intelligenceQuery.getlayoutArray(),","));
//        IntelligenceFh intelligenceFh = new IntelligenceFh();
        String[] layoutArray = null;
        if (null!=intelligenceQuery.getLayoutArray()&&intelligenceQuery.getLayoutArray().length()>0){
            layoutArray = intelligenceQuery.getLayoutArray().split(",");
        }

        if (ArrayUtils.isNotEmpty(layoutArray) && ArrayUtils.contains(layoutArray,"3")) {
            //教育配套
            intelligenceQuery.setSchoolFlag(1);
            //医院配套
            intelligenceQuery.setHospitalFlag(1);
        }
        //获取相对应的比率
        if (ArrayUtils.isNotEmpty(layoutArray)&& ArrayUtils.contains(layoutArray,"5")){
            List<TotalRoomRatio> roomRatio = totalRoomRatioMapper.selectByTotalAndCategory3(intelligenceQuery);
            if (StringTool.isNotBlank(roomRatio) && roomRatio.size() > 0) {
                if (roomRatio.size() > 1) {
                    Double plotRatio = 0.0;
                    for (TotalRoomRatio ratio : roomRatio) {
                        plotRatio += ratio.getRatio();
                    }
                    //用户比率
//                    intelligenceFh.setRatio(plotRatio);
                }
                //用户比率
//                intelligenceFh.setRatio(roomRatio.get(0).getRatio());
                //用户画像类型
                intelligenceQuery.setUserPortrayalType(roomRatio.get(0).getUserPortrayalType());

            } else {
                if (ArrayUtils.isNotEmpty(layoutArray)&& ArrayUtils.contains(layoutArray,"5") && StringTool.isNotBlank(intelligenceQuery.getMaxTotalPrice()) && intelligenceQuery.getMaxTotalPrice() * 10000 > 4E6) {
                    //用户画像类型
                    intelligenceQuery.setUserPortrayalType(4);
//                    intelligenceFh.setRatio(0.5);
                }
                if (ArrayUtils.isNotEmpty(layoutArray)&& ArrayUtils.contains(layoutArray,"5")  && StringTool.isNotBlank(intelligenceQuery.getMaxTotalPrice()) && intelligenceQuery.getMaxTotalPrice() * 10000 <= 4E6) {
                    intelligenceQuery.setUserPortrayalType(1);
//                    intelligenceFh.setRatio(0.5);
                }
            }
        }else {
            //比率
            List<TotalRoomRatio> roomRatio = totalRoomRatioMapper.selectByTotalAndCategory2(intelligenceQuery);
            if (StringTool.isNotBlank(roomRatio) && roomRatio.size() > 0) {
                //用户画像类型
                intelligenceQuery.setUserPortrayalType(roomRatio.get(0).getUserPortrayalType());
                //用户比率
//                intelligenceFh.setRatio(roomRatio.get(0).getRatio());
            } else {
                if (StringTool.isNotBlank(intelligenceQuery.getMaxTotalPrice()) && intelligenceQuery.getMaxTotalPrice() * 10000 > 4E6) {
                    //用户画像类型
                    intelligenceQuery.setUserPortrayalType(4);
//                    intelligenceFh.setRatio(0.5);
                }
                if (StringTool.isNotBlank(intelligenceQuery.getMaxTotalPrice()) && intelligenceQuery.getMaxTotalPrice()* 10000 <= 4E6) {
                    intelligenceQuery.setUserPortrayalType(1);
//                    intelligenceFh.setRatio(0.5);
                }
            }
        }
        return intelligenceQuery;
    }

    @Override
    public IntelligenceFhRes intelligenceFindHouseServiceByType1(IntelligenceQuery intelligenceQuery, String userPhone) {
        IntelligenceFhRes intelligenceFhRes = new IntelligenceFhRes();

        //初始化数据
//        IntelligenceQuery intelligenceQuery = init(intelligenceQuery1);

        //搜索量前200
        List<IntelligenceFindhouse> starPropertyList = intelligenceFindhouseMapper.queryByStarPropertyV1(intelligenceQuery);

        //判断类型
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1AV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1A(list);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1BV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1B(list, starPropertyList);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_1C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType1CV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend1C(list, starPropertyList);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2AV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2A(list, starPropertyList);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2B) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2BV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2B(list, starPropertyList);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_2C) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType2CV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend2C(list, starPropertyList);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        if (intelligenceQuery.getUserPortrayalType() == USERTYPE_3A) {
            List<IntelligenceFindhouse> list = intelligenceFindhouseMapper.queryByUserType3AV1(intelligenceQuery);
            List<IntelligenceFindhouse> finalList = recommend3A(list);
            intelligenceFhRes = save(intelligenceQuery, finalList, userPhone);
        }
        return intelligenceFhRes;
    }


    /**
     * 功能描述：保存结果
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2018/1/3 15:46
     */
    public IntelligenceFhRes save(IntelligenceQuery intelligenceQuery, List<IntelligenceFindhouse> finalList, String userPhone) {
        IntelligenceFhRes intelligenceFhRes = new IntelligenceFhRes();
        String str = JSONObject.toJSONString(intelligenceQuery);
        IntelligenceFhResJson intelligenceFhResJson = JSON.parseObject(str, IntelligenceFhResJson.class);
        BeanUtils.copyProperties(intelligenceFhResJson, intelligenceFhRes);
//        List<IntelligenceFindhouse> test = intelligenceFindhouseMapper.test(11114240);
//        finalList = test;
//        intelligenceFhRes.setLayoutArray(StringUtils.join(intelligenceQuery.getLayoutArray()));
//        intelligenceFhRes.setDistrictArray(StringUtils.join(intelligenceQuery.getDistrictArray()));
        intelligenceFhRes.setLayoutArray(intelligenceQuery.getLayoutArray());
        intelligenceFhRes.setDistrictArray(intelligenceQuery.getDistrictArray());

//        intelligenceFhRes.setlayoutArray(intelligenceQuery.getlayoutArray());
//        intelligenceFhRes.setDistrictArray(intelligenceQuery.getDistrictArray());
        int index = 1;
        if (null != finalList && finalList.size() > 0) {
            for (IntelligenceFindhouse intelligence : finalList) {
//                if (null != intelligence.getPropertyfee()) {
//                    BigDecimal bigDecimal = new BigDecimal(intelligence.getPropertyfee().doubleValue() * 12);
//                    intelligence.setPropertyfee(bigDecimal);
//                }
                if (null != intelligence.getCarRentPrice()) {
                    BigDecimal bigDecimal = new BigDecimal(intelligence.getCarRentPrice().doubleValue() * 12);
                    intelligence.setCarRentPrice(bigDecimal);
                }
                if (null != intelligence.getCarSellPrice()) {
                    BigDecimal bigDecimal = new BigDecimal(intelligence.getCarSellPrice().doubleValue() * 12);
                    intelligence.setCarSellPrice(bigDecimal);
                }
                //默认顺序
                intelligence.setSortInex(index++);
                if (null != intelligence.getNearestSubwayDesc()&&(intelligence.getNearestSubwayDesc().split("\\$").length==3)) {
                    Integer distance = Integer.valueOf(intelligence.getNearestSubwayDesc().split("\\$")[2]);
                    intelligence.setMetroWithPlotDistance(distance);
                }
            }
            //查询地图信息
            for (IntelligenceFindhouse intelligenceFindhouse : finalList) {
                MapInfo mapInfo = mapService.getMapInfo(intelligenceFindhouse.getNewcode());
                if (null != mapInfo && null != mapInfo.getTypeCount() && null != mapInfo.getDataInfo()) {
                    JSONObject datainfo = JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
                    JSONObject typeCount = JSON.parseObject(((PGobject) mapInfo.getTypeCount()).getValue());
                    intelligenceFindhouse.setTypeCount(typeCount);
                    intelligenceFindhouse.setDataInfo(datainfo);
                }
            }
            if (null != intelligenceFhRes.getDistrictId()) {
                String[] districtId = intelligenceFhRes.getDistrictId().split(",");
                String district = "";
                for (int i = 0; i < districtId.length; i++) {
                    if (null != DistrictMap.getDistrict(districtId[i])) {
                        if (i > 0) {
                            district += "," + DistrictMap.getDistrict(districtId[i]);
                        } else {
                            district += DistrictMap.getDistrict(districtId[i]);
                        }
                    }
                }
                intelligenceFhRes.setDistrictId(district);
            }
            String jsonString = JSONArray.toJSONString(finalList);
            intelligenceFhRes.setFhResult(jsonString);
            if(userPhone!=null){
                intelligenceFhRes.setPhone(userPhone);
            }
            if(StringTool.isNotEmpty(intelligenceQuery.getMaxTotalPrice())&&StringTool.isNotEmpty(intelligenceQuery.getMinTotalPrice())){
                intelligenceFhRes.setTotalPrice((int)((intelligenceQuery.getMinTotalPrice()+intelligenceQuery.getMaxTotalPrice())/2));
            }else if(StringTool.isEmpty(intelligenceQuery.getMaxTotalPrice())&&StringTool.isNotEmpty(intelligenceQuery.getMinTotalPrice())){
                intelligenceFhRes.setTotalPrice(Integer.valueOf(String.valueOf(intelligenceQuery.getMinTotalPrice()).split(".")[0]));
            }

            intelligenceFhResMapper.saveData(intelligenceFhRes);

        }
        return intelligenceFhRes;
    }


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
        plotTotalFirst = (Double.valueOf(plotTotal) - (Double.valueOf(plotTotal) * 0.1));
        plotTotalEnd = (Double.valueOf(plotTotal) + (Double.valueOf(plotTotal) * 0.1));
        if (null==intelligenceFh.getMaxTotalPrice()&&null==intelligenceFh.getMinTotalPrice()){
            intelligenceFh.setMaxTotalPrice(plotTotalEnd);
            intelligenceFh.setMinTotalPrice(plotTotalFirst);
        }
        intelligenceFh.setTotalPrice(plotTotal);
        intelligenceFh.setPreconcTotal(plotTotal);
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
     * 功能描述：从200个中选取一个
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2018/1/4 23:03
     */
    public void removeRepetitionTwo(List<IntelligenceFindhouse> finalList, List<IntelligenceFindhouse> list, int number) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Boolean flag = false;
            while (!flag) {
                boolean AA = true;
                int index = random.nextInt(list.size());
                for (IntelligenceFindhouse intelligenceFindhouse : finalList) {
                    if (intelligenceFindhouse.getNewcode().equals(list.get(index).getNewcode())) {
                        AA = false;
                    }
                }
                if (AA) {
                    flag = true;
                    finalList.add(list.get(index));
                }
                list.remove(index);
            }
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

        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetitionTwo(finalList, starPropertyList, 1);
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
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetitionTwo(finalList, starPropertyList, 1);
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
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetitionTwo(finalList, starPropertyList, 1);
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
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetitionTwo(finalList, starPropertyList, 1);
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
        //搜索量前200，随机1个
        if (null != starPropertyList && starPropertyList.size() >= 1) {
            removeRepetitionTwo(finalList, starPropertyList, 1);
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






























