package com.toutiao.web.service.intelligence.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.dao.entity.robot.SubwayDistance;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
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

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    /**
     * 功能描述：通过总价获取相依小区的数量
     * ·* @param [intelligenceQuery, model]
     *
     * @return java.util.List
     * @author zhw
     * @date 2017/12/18 20:12
     */
    @Override
    public List queryPlotCount(IntelligenceQuery intelligenceQuery) {
        //初始化
        Double plotTotal = null;
        try {
            if (StringTool.isNotEmpty(intelligenceQuery.getBeginDownPayment()) && StringTool.isNotEmpty(intelligenceQuery.getBeginMonthPayment())) {
               //首付(起始)
                Double beginDownPayment = intelligenceQuery.getBeginDownPayment();
                //月供(起始)
                Double beginMonthPayment = intelligenceQuery.getBeginMonthPayment();

                //通过页面传递的参数获取用户期望的总价范围
                //计算总价范围 总价=首付+月供*12*30
                plotTotal = beginDownPayment + beginMonthPayment * 12 * 30;
            }else {
                //获取页面填入的总价数据
                plotTotal = intelligenceQuery.getPreconcTotal();
            }
            intelligenceQuery.setPlotTotal(plotTotal);

            String userType = intelligenceQuery.getUserType();
            //总价高于400万 将用户的类型升级到自住改善
            if (plotTotal >= 4E6 && userType.equals("1")) {
                userType = "3";
                //将用户选择的类型放入对象中
                intelligenceQuery.setUserType(userType);

            }
            if ("3".equals(intelligenceQuery.getUserType())) {
                //将第七种画像附给当前用户
                intelligenceQuery.setUserPortrayalType(7);
            }
            //获取该总价范围内的小区数量
            SearchHits hits = esInfo(intelligenceQuery);
            //获取相应小区的个数
            long totalHits = hits.getTotalHits();
            //设置小区数量
            intelligenceQuery.setPlotCount(totalHits);
            //判断
            //获取相对应的比率
            List<TotalListedRatio> totalPriceRate = totalListedRatioMapper.selectByTotalPrice(plotTotal);
            //用户所对应的小区比率
            String totalRate = new StringBuffer().append(totalPriceRate.get(0).getRatio()).append("%").toString();
            intelligenceQuery.setRatio(totalRate);
            List list = new ArrayList();
            list.add(intelligenceQuery);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：通过总价与户型选出小区的数量与比率
     *
     * @param [intelligenceQuery]
     * @return java.util.List
     * @author zhw
     * @date 2017/12/18 23:36
     */
    @Override
    public List queryByCategory(IntelligenceQuery intelligenceQuery) {

        try {
            Integer category_id = intelligenceQuery.getCategoryId();

            if (StringTool.isNotBlank(category_id)) {
                //判断户型类型是否为三居以上
                //如果三居以上则需要在页面显示教育配套，医疗配套标签


                //缺少相关代码


                //通过户型赛选小区，并且算出小区数量
                SearchHits hits = esInfo(intelligenceQuery);
                //获取相应小区的个数
                long totalHits = hits.getTotalHits();
                //保存根据用户条件赛选的小区数量
                intelligenceQuery.setPlotCount(totalHits);
                //通过上一步获取的总价，与当前户型去获取比率
                //比率
                List<Double> totalList = totalRoomRatioMapper.selectByTotal(intelligenceQuery.getPlotTotal());
                //保存总价
                //intelligenceQuery.setPlotTotal(totalList.get(0));

                TotalRoomRatio roomRatio = totalRoomRatioMapper.selectByTotalAndCategory(totalList.get(0), category_id);
                //用户画像类型
                intelligenceQuery.setUserPortrayalType(roomRatio.getUserPortrayalType());
                //用户比率
                intelligenceQuery.setRatio(new StringBuffer().append(roomRatio.getRatio().intValue() * 100).append("%").toString());
                List list = new ArrayList();
                list.add(intelligenceQuery);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QueryFindByRobot> filterByTotalAndCategory(IntelligenceQuery intelligenceQuery) {

        try {
            Integer category_id = intelligenceQuery.getCategoryId();
            //聚合
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            if (StringTool.isNotEmpty(category_id)) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", category_id));
            }
            //按照区域去重 获取去重后的数量
            AggregationBuilder aggregation =
                    AggregationBuilders
                            .terms("agg").field("districtName");
            SearchResponse sResponse = srb
                    .setQuery(booleanQueryBuilder)
                    .addAggregation(aggregation)
                    .execute().actionGet();
            List houseList = new ArrayList();
            for (SearchHit searchHit : sResponse.getHits().getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            return houseList;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Long> queryPlotCountBySchoolType(IntelligenceQuery intelligenceQuery) {

        try {
            //获取相应小区的个数
            long totalHits = esInfo(intelligenceQuery).getTotalHits();
            List<Long> list = new ArrayList();
            list.add(totalHits);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List queryPlotInfoByUserType(IntelligenceQuery intelligenceQuery) {

        try {
            //类型1 A
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("1")) {
                //无赛选条件   结果处理：小区均价最低2个，离地铁最近3个
                List queryPlotInfoByCondition = queryPlotInfoByCondition(intelligenceQuery);
                return queryPlotInfoByCondition;
            }
            //类型1 B
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("2")) {
                //过滤掉1.5km内无地铁
                //过滤掉6环外
                List queryPlotInfoByCondition2 = queryPlotInfoByCondition2(intelligenceQuery);
                return queryPlotInfoByCondition2;

            }
            //类型1 C
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("3")) {
                //过滤掉1.5km内无地铁
                //过滤掉6环外
                //过滤掉5环外
                List queryPlotInfoByCondition2 = queryPlotInfoByCondition2(intelligenceQuery);
                return queryPlotInfoByCondition2;
            }
            //类型2 A
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("4")) {
                //过滤掉1.5km内无地铁
                //过滤掉5环外
                //过滤掉2km内无购物配套
                List queryPlotInfoByCondition3 = queryPlotInfoByCondition3(intelligenceQuery);
                return queryPlotInfoByCondition3;
            }
            //类型2 B
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("5")) {
                //过滤掉5环外
                //过滤掉车位配比小于1：1
                //过滤掉2km内无购物配套
                List queryPlotInfoByCondition4 = queryPlotInfoByCondition4(intelligenceQuery);
                return queryPlotInfoByCondition4;
            }
            //类型2 C
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("6")) {
                //过滤掉5环外
                //过滤掉车位配比小于1：1
                //过滤掉2km内无购物配套
                //过滤掉物业费小于2元
                List queryPlotInfoByCondition5 = queryPlotInfoByCondition5(intelligenceQuery);
                return queryPlotInfoByCondition5;

            }
            //类型3
            if (StringTool.isNotBlank(intelligenceQuery.getUserPortrayalType()) && intelligenceQuery.getUserPortrayalType().equals("7")) {

                List queryPlotInfoByCondition6 = queryPlotInfoByCondition6(intelligenceQuery);
                return queryPlotInfoByCondition6;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * 功能描述：根据用户的手机号码获取用户报表相关数据信息
     * @author zhw
     * @date 2017/12/26 15:45
     * @param [usePhone]
     * @return com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes
     */
    @Override
    public IntelligenceFhRes queryUserReport(String usePhone) {

        IntelligenceFhRes intelligenceFhRes= intelligenceFhResMapper.selectByUserPhone(usePhone);

        return intelligenceFhRes;
    }

    /**
     * 功能描述：根据条件查询es服务器中存储的数据
     *
     * @param [intelligenceQuery]
     * @return org.elasticsearch.search.SearchHits
     * @author zhw
     * @date 2017/12/19 17:48
     */
    protected SearchHits esInfo(IntelligenceQuery intelligenceQuery) throws Exception {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

        Double plotTotal = intelligenceQuery.getPlotTotal();

        //判断起始总价是否为空，null
        if (StringTool.isNotBlank(plotTotal)) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
        }
        //总价结束
        if (StringTool.isNotBlank(plotTotal)) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
        }
        //户型id
        if (StringTool.isNotEmpty(intelligenceQuery.getCategoryId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", intelligenceQuery.getCategoryId()));
        }
        //学校类型
        if (StringTool.isNotEmpty(intelligenceQuery.getSchoolTypeName())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("schoolTypeArray", intelligenceQuery.getSchoolTypeName()));
        }
        //区域id
        if (StringTool.isNotEmpty(intelligenceQuery.getDistrict_Id())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", intelligenceQuery.getDistrict_Id()));
        }

        //查询数据
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().get();
        SearchHits hits = searchResponse.getHits();

        return hits;
    }


    protected List queryPlotInfoByCondition(IntelligenceQuery intelligenceQuery) {
        try {
            List save = new ArrayList();
            //结果处理：小区均价最低2个，离地铁最近3个
            SearchHits hits = esInfo(intelligenceQuery);

            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            //小区均价最低2个
            Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
                @Override
                public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                    //按照小区均价排序
                    if (o1.getEsf_total_price() > o2.getEsf_total_price()) {
                        return 1;
                    }
                    if (o1.getEsf_total_price() == o2.getEsf_total_price()) {
                        return 0;
                    }
                    return -1;
                }
            });

            save.add(houseList.get(0));
            save.add(houseList.get(1));
            //移除选择的这两个数据
            houseList.remove(0);
            houseList.remove(1);
            List<SubwayDistance> subwayDistances = new ArrayList<>();
            //离地铁最近3个
            for (QueryFindByRobot queryFindByRobot : houseList) {
                subwayDistances.add(JSONObject.parseObject(queryFindByRobot.getSubwayDistance(), SubwayDistance.class));
            }
            Collections.sort(subwayDistances, new Comparator<SubwayDistance>() {
                @Override
                public int compare(SubwayDistance o1, SubwayDistance o2) {

                    //按照地铁距离排序
                    if (o1.getDistance() > o2.getDistance()) {
                        return 1;
                    }
                    if (o1.getDistance() == o2.getDistance()) {
                        return 0;
                    }
                    return -1;
                }
            });
            //离地铁最近3个
            save.add(subwayDistances.get(0));
            save.add(subwayDistances.get(1));
            save.add(subwayDistances.get(2));
            return save;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    protected List queryPlotInfoByCondition2(IntelligenceQuery intelligenceQuery) {

        //过滤掉1.5km内无地铁 排序查询
        //过滤掉6环外 排序
        try {
            List save = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            //总价结束
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            //户型id
            if (StringTool.isNotEmpty(intelligenceQuery.getCategoryId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", intelligenceQuery.getCategoryId()));
            }
            //学校类型
            if (StringTool.isNotEmpty(intelligenceQuery.getSchoolTypeName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("schoolTypeArray", intelligenceQuery.getSchoolTypeName()));
            }
            //区域id
            if (StringTool.isNotEmpty(intelligenceQuery.getDistrict_Id())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", intelligenceQuery.getDistrict_Id()));
            }

            //查询数据
            SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
                    //过滤掉1.5km内无地铁 排序查询 有1 无0
                    .addSort("has_subway", SortOrder.ASC)
                    //过滤掉6环外 排序
                    .addSort("ring_road", SortOrder.ASC).
                            execute().get();
            SearchHits hits = searchResponse.getHits();
            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            //小区均价最低2个，换手率最高3个
            //小区均价最低2个
            Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
                @Override
                public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                    //按照小区均价排序
                    if (o1.getEsf_total_price() > o2.getEsf_total_price()) {
                        return 1;
                    }
                    if (o1.getEsf_total_price() == o2.getEsf_total_price()) {
                        return 0;
                    }
                    return -1;
                }
            });
            save.add(houseList.get(0));
            save.add(houseList.get(1));
            //移除选择的这两个数据
            houseList.remove(0);
            houseList.remove(1);
            //换手率最高3个
            Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
                @Override
                public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                    //按照换手率均价排序 升序
                    if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 1) {
                        return 1;
                    }
                    if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                        return 0;
                    }
                    return -1;
                }
            });
            //换手率最高3个
            save.add(houseList.get(houseList.size() - 1));
            save.add(houseList.get(houseList.size() - 2));
            save.add(houseList.get(houseList.size() - 3));
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //过滤掉1.5km内无地铁
    //过滤掉5环外
    //过滤掉2km内无购物配套
    protected List queryPlotInfoByCondition3(IntelligenceQuery intelligenceQuery) {

        //过滤掉1.5km内无地铁 排序查询
        //过滤掉6环外 排序
        try {
            List save = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            //总价结束
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            //户型id
            if (StringTool.isNotEmpty(intelligenceQuery.getCategoryId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", intelligenceQuery.getCategoryId()));
            }
            //学校类型
            if (StringTool.isNotEmpty(intelligenceQuery.getSchoolTypeName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("schoolTypeArray", intelligenceQuery.getSchoolTypeName()));
            }
            //区域id
            if (StringTool.isNotEmpty(intelligenceQuery.getDistrict_Id())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", intelligenceQuery.getDistrict_Id()));
            }
            //查询数据
            SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
                    //过滤掉1.5km内无地铁 排序查询 有1 无2
                    .addSort("has_subway", SortOrder.ASC)
                    //过滤掉6环外 排序
                    .addSort("ring_road", SortOrder.ASC)
                    //过滤掉2km内无购物配套 有1 无2
                    .addSort("has_market", SortOrder.ASC)
                    .execute().get();
            SearchHits hits = searchResponse.getHits();
            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            sortByCondition(save, houseList);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //过滤掉5环外
    //过滤掉车位配比小于1：1
    //过滤掉2km内无购物配套
    protected List queryPlotInfoByCondition4(IntelligenceQuery intelligenceQuery) {

        //过滤掉1.5km内无地铁 排序查询
        //过滤掉6环外 排序
        try {
            List save = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            //总价结束
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            //户型id
            if (StringTool.isNotEmpty(intelligenceQuery.getCategoryId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", intelligenceQuery.getCategoryId()));
            }
            //学校类型
            if (StringTool.isNotEmpty(intelligenceQuery.getSchoolTypeName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("schoolTypeArray", intelligenceQuery.getSchoolTypeName()));
            }
            //区域id
            if (StringTool.isNotEmpty(intelligenceQuery.getDistrict_Id())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", intelligenceQuery.getDistrict_Id()));
            }
            //过滤掉5环外
            //过滤掉车位配比小于1：1
            //过滤掉2km内无购物配套
            //查询数据
            SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
                    //过滤掉1.5km内无地铁 排序查询 有1 无2
                    .addSort("ring_road", SortOrder.ASC)
                    //过滤掉车位配比小于1：1 < 1:2  倒叙排列 大的在前面
                    .addSort("park_radio", SortOrder.DESC)
                    //过滤掉2km内无购物配套 有1 无2
                    .addSort("has_market", SortOrder.ASC)
                    .execute().get();
            SearchHits hits = searchResponse.getHits();
            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            sortByCondition(save, houseList);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //过滤掉5环外
    //过滤掉车位配比小于1：1
    //过滤掉2km内无购物配套
    //过滤掉物业费小于2元
    protected List queryPlotInfoByCondition5(IntelligenceQuery intelligenceQuery) {

        //过滤掉1.5km内无地铁 排序查询
        //过滤掉6环外 排序
        try {
            List save = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            //总价结束
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            //户型id
            if (StringTool.isNotEmpty(intelligenceQuery.getCategoryId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("plotCategoryId", intelligenceQuery.getCategoryId()));
            }
            //学校类型
            if (StringTool.isNotEmpty(intelligenceQuery.getSchoolTypeName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("schoolTypeArray", intelligenceQuery.getSchoolTypeName()));
            }
            //区域id
            if (StringTool.isNotEmpty(intelligenceQuery.getDistrict_Id())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", intelligenceQuery.getDistrict_Id()));
            }
            //过滤掉5环外
            //过滤掉车位配比小于1：1
            //过滤掉2km内无购物配套
            //查询数据
            SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
                    //过滤掉1.5km内无地铁 排序查询 有1 无2
                    .addSort("ring_road", SortOrder.ASC)
                    //过滤掉车位配比小于1：1 < 1:2  倒叙排列 大的在前面
                    .addSort("park_radio", SortOrder.DESC)
                    //过滤掉2km内无购物配套 有1 无2
                    .addSort("has_market", SortOrder.ASC)
                    ////过滤掉物业费小于2元
                    .addSort("propertyfee", SortOrder.DESC)
                    .execute().get();
            SearchHits hits = searchResponse.getHits();
            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            sortByCondition(save, houseList);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 换手率最高2个
     * 租金月供比最高1个住宅，1个商铺（无商铺则推住宅),铭牌2个
     *
     * @param intelligenceQuery
     * @return
     */
    protected List queryPlotInfoByCondition6(IntelligenceQuery intelligenceQuery) {

        //过滤掉1.5km内无地铁 排序查询
        //过滤掉6环外 排序
        try {
            List save = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(robotIndex).setTypes(robotType);
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

            Double plotTotal = intelligenceQuery.getPlotTotal();

            //判断起始总价是否为空，null
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesBegin").lte(plotTotal));
            }
            //总价结束
            if (StringTool.isNotBlank(plotTotal)) {
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("plotTotalPricesEnd").gte(plotTotal));
            }
            //换手率最高2个
            //租金月供比最高1个住宅，1个商铺（无商铺则推住宅),铭牌2个
            SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
                    .execute().get();
            SearchHits hits = searchResponse.getHits();
            List<QueryFindByRobot> houseList = new ArrayList();
            for (SearchHit searchHit : hits.getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<QueryFindByRobot> entityClass = QueryFindByRobot.class;
                QueryFindByRobot instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            sortByCondition1(save, houseList);
            return save;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sortByCondition(List save, List<QueryFindByRobot> houseList) {
        //小区均价最低2个，环线最小的2个(或核心商圈2个)，换手率最高1个
        //小区均价最低2个
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照小区均价排序
                if (o1.getEsf_total_price() > o2.getEsf_total_price()) {
                    return 1;
                }
                if (o1.getEsf_total_price() == o2.getEsf_total_price()) {
                    return 0;
                }
                return -1;
            }
        });
        save.add(houseList.get(0));
        save.add(houseList.get(1));
        //移除选择的这两个数据
        houseList.remove(0);
        houseList.remove(1);
        //换手率最高1个
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照换手率均价排序 升序
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 1) {
                    return 1;
                }
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                    return 0;
                }
                return -1;
            }
        });
        //换手率最高1个
        save.add(houseList.get(houseList.size() - 1));
        houseList.remove(houseList.size() - 1);

        //环线最小的2个(或核心商圈2个)
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照环线最小排序 升序
                if (o1.getRoundstation().compareTo(o2.getRoundstation()) == 1) {
                    return 1;
                }
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                    return 0;
                }
                return -1;
            }
        });
        //环线最小的2个
        save.add(houseList.get(0));
        save.add(houseList.get(1));
    }

    private void sortByCondition1(List save, List<QueryFindByRobot> houseList) {
        //换手率最高2个
        //租金月供比最高1个住宅，1个商铺（无商铺则推住宅),铭牌2个
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照换手率均价排序 升序
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 1) {
                    return 1;
                }
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                    return 0;
                }
                return -1;
            }
        });
        //换手率最高1个
        save.add(houseList.get(houseList.size() - 1));
        save.add(houseList.get(houseList.size() - 2));

        houseList.remove(houseList.size() - 1);
        houseList.remove(houseList.size() - 2);

        //有问题
        //租金月供比最高1个,铭牌2个
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照环线最小排序 升序
                if (o1.getRoundstation().compareTo(o2.getRoundstation()) == 1) {
                    return 1;
                }
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                    return 0;
                }
                return -1;
            }
        });
        //租金月供比最高1个
        save.add(houseList.get(0));
        houseList.remove(0);
        //铭牌2个
        Collections.sort(houseList, new Comparator<QueryFindByRobot>() {
            @Override
            public int compare(QueryFindByRobot o1, QueryFindByRobot o2) {
                //按照环线最小排序 升序
                if (o1.getRoundstation().compareTo(o2.getRoundstation()) == 1) {
                    return 1;
                }
                if (o1.getTurnover_rate().compareTo(o2.getTurnover_rate()) == 0) {
                    return 0;
                }
                return -1;
            }
        });
        //有问题
        save.add(houseList.get(houseList.size() - 1));
        save.add(houseList.get(houseList.size() - 2));
    }


}
