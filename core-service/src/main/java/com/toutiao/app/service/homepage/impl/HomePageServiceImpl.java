package com.toutiao.app.service.homepage.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.favorite.UserFavoriteCondition;
import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteConditionMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomePageServiceImpl implements HomePageRestService {
    @Autowired
    private HomePageEsDao homePageEsDao;

    @Autowired
    private NewHouseRestService newHouseRestService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;

    @Autowired
    private CommunityRestService communityRestService;

    @Autowired
    private UserFavoriteConditionMapper userFavoriteConditionMapper;

    @Autowired
    private HomePageRestService homePageRestService;

    @Autowired
    private SellHouseService sellHouseService;
    /**
     * @return 获取二手房5条
     */
    @Override
    public List<HomePageEsfDo> getHomePageEsf() {
        Random random = new Random();
        List<HomePageEsfDo> homePageEsfDos = new ArrayList<>();
        List<HomePageEsfDo> result = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("housePhotoTitle", ""));
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termsQuery("is_claim", "1"));
        SearchResponse searchResponse = homePageEsDao.getHomePageEsf(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String details = "";
            details = hit.getSourceAsString();
            HomePageEsfDo homePageEsf = JSON.parseObject(details, HomePageEsfDo.class);
            homePageEsfDos.add(homePageEsf);
        }

        if (!homePageEsfDos.isEmpty() && homePageEsfDos.size() > 5) {
            while (result.size() < 5) {
                result = hashPush(result, homePageEsfDos.get(random.nextInt(10)));
            }
        }
        return result;

    }

    /**
     * 获取新房5条
     *
     * @return
     */

    @Override
    public NewHouseListDomain getHomePageNewHouse(String city) {
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        newHouseDoQuery.setPageSize(5);
//        newHouseDoQuery.setSaleStatusId(new Integer[]{1});
        NewHouseListDomain newHouseListDomain = newHouseRestService.getNewHouseList(newHouseDoQuery,city);

        return newHouseListDomain;

    }

//    /**
//     * 获取首页主题房
//     *
//     * @param homeThemeHouseDoQuery
//     * @return
//     */
//    @Override
//    public HomeThemeHouseListDo getHomeThemeHouse(HomeThemeHouseDoQuery homeThemeHouseDoQuery) {
//        //构建筛选器
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//
//        //价格
//        if (homeThemeHouseDoQuery.getBeginPrice() == 0 && homeThemeHouseDoQuery.getEndPrice() != 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(homeThemeHouseDoQuery.getEndPrice()));
//        } else if (homeThemeHouseDoQuery.getBeginPrice() != 0 && homeThemeHouseDoQuery.getEndPrice() == 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(homeThemeHouseDoQuery.getBeginPrice()));
//        } else if (homeThemeHouseDoQuery.getBeginPrice() != 0 && homeThemeHouseDoQuery.getEndPrice() != 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(homeThemeHouseDoQuery.getBeginPrice())
//                    .lte(homeThemeHouseDoQuery.getEndPrice()));
//        }
//
//        //面积
//        if (homeThemeHouseDoQuery.getBeginArea() == 0 && homeThemeHouseDoQuery.getEndArea() != 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lte(homeThemeHouseDoQuery.getEndArea()));
//        } else if (homeThemeHouseDoQuery.getBeginArea() != 0 && homeThemeHouseDoQuery.getEndArea() == 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gt(homeThemeHouseDoQuery.getBeginArea()));
//        } else if (homeThemeHouseDoQuery.getBeginArea() != 0 && homeThemeHouseDoQuery.getEndArea() != 0) {
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gt(homeThemeHouseDoQuery.getBeginArea())
//                    .lte(homeThemeHouseDoQuery.getEndArea()));
//        }
//
//        //几居
//        if (null != homeThemeHouseDoQuery.getLayoutId() && homeThemeHouseDoQuery.getLayoutId().length > 0) {
//            boolQueryBuilder.must(QueryBuilders.termsQuery("room",homeThemeHouseDoQuery.getLayoutId()));
//        }
//
//        //是否有地铁
//        if (null != homeThemeHouseDoQuery.getHasSubway() && homeThemeHouseDoQuery.getHasSubway() > 0) {
//            boolQueryBuilder.must(QueryBuilders.termQuery("has_subway", homeThemeHouseDoQuery.getHasSubway()));
//        }
//
//        //isRecommend大于0，推荐房源
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));
//        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
//
//        SearchResponse homeThemeHouse = homePageEsDao.getHomeThemeHouse(boolQueryBuilder, (homeThemeHouseDoQuery.getPageNum() - 1) * homeThemeHouseDoQuery.getPageSize(), homeThemeHouseDoQuery.getPageSize());
//        SearchHit[] hits = homeThemeHouse.getHits().getHits();
//        HomeThemeHouseListDo homeThemeHouseListDo = new HomeThemeHouseListDo();
//
//        if (hits.length > 0) {
//            List<HomeThemeHouseDo> list = new ArrayList<>();
//            for (SearchHit hit : hits) {
//                String sourceAsString = hit.getSourceAsString();
//                HomeThemeHouseDo homeThemeHouseDo = JSON.parseObject(sourceAsString, HomeThemeHouseDo.class);
//                list.add(homeThemeHouseDo);
//            }
//            homeThemeHouseListDo.setData(list);
//            homeThemeHouseListDo.setTotalNum((int) homeThemeHouse.getHits().getTotalHits());
//        }
//        return homeThemeHouseListDo;
//    }

    /**
     * 首页根据坐标获取周边小区
     * @param nearHouseDoQuery
     * @return
     */
    @Override
    public HomePageNearPlotListDo getHomePageNearPlot(NearHouseDoQuery nearHouseDoQuery, String city) {

        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        HomePageNearPlotListDo homePageNearPlotListDo = new HomePageNearPlotListDo();
        Boolean flag = false;
        //组装条件
        boolQueryBuilder.must(QueryBuilders.rangeQuery("house_count").gt(0));
        boolQueryBuilder.must(QueryBuilders.termQuery("cityId", nearHouseDoQuery.getCityId()));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        SearchResponse homePageNearPlot = null;
        //是否存在坐标
        if (StringTool.isNotEmpty(nearHouseDoQuery.getLat())&&StringTool.isNotEmpty(nearHouseDoQuery.getLon())&&nearHouseDoQuery.getLat()>0&&nearHouseDoQuery.getLon()>0){
            flag = true;

            //过滤附近5km
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                    .point(nearHouseDoQuery.getLat(), nearHouseDoQuery.getLon())
                    .distance(nearHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);

            //按距离排序并计算距离
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearHouseDoQuery.getLat(), nearHouseDoQuery.getLon());
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);

            boolQueryBuilder.must(location);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(0));
            homePageNearPlot = homePageEsDao.getHomePageNearPlot(boolQueryBuilder, nearHouseDoQuery.getSize(), sort, city);
        }else {
            boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId",new int[]{1}));
            homePageNearPlot = homePageEsDao.getHomePageNearPlotNoLocation(boolQueryBuilder, nearHouseDoQuery.getSize(), city);
        }

        if (null!=homePageNearPlot){
            SearchHit[] hits = homePageNearPlot.getHits().getHits();
            if (hits!=null&&hits.length>0){
                List<HomePageNearPlotDo> list = new ArrayList<>();
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    HomePageNearPlotDo homePageNearPlotDo = JSON.parseObject(sourceAsString, HomePageNearPlotDo.class);

                    PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(homePageNearPlotDo.getId(), CityUtils.getCity());
                    if(null != plotsEsfRoomCountDomain.getTotalCount()){
                        homePageNearPlotDo.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
                    }else{
                        homePageNearPlotDo.setHouseCount(0);
                    }
                    if(flag){
                        homePageNearPlotDo.setDistance((double) Math.round((Double) hit.getSortValues()[0]));
                    }
                    list.add(homePageNearPlotDo);
                }
                homePageNearPlotListDo.setData(list);
                homePageNearPlotListDo.setTotalNum((int) homePageNearPlot.getHits().getTotalHits());
            }
        }

        return homePageNearPlotListDo;
    }

    /**
     * 根据坐标获取周边二手房
     * @param nearHouseDoQuery
     * @return
     */
    @Override
    public HomePageNearEsfListDo getHomePageNearEsf(NearHouseDoQuery nearHouseDoQuery, String city) {

        Date date = new Date();
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        HomePageNearEsfListDo homePageNearEsfListDo = new HomePageNearEsfListDo();
        Boolean flag =false;

        //组装条件
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", 0));

        SearchResponse homePageNearEsf = null;
        //是否存在坐标
        if (StringTool.isNotEmpty(nearHouseDoQuery.getLat())&&StringTool.isNotEmpty(nearHouseDoQuery.getLon())&&nearHouseDoQuery.getLat()>0&&nearHouseDoQuery.getLon()>0){
            flag = true;
            //过滤附近5km
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                    .point(nearHouseDoQuery.getLat(), nearHouseDoQuery.getLon())
                    .distance(nearHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);

            //按距离排序并计算距离
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("housePlotLocation", nearHouseDoQuery.getLat(), nearHouseDoQuery.getLon());
            sort.unit(DistanceUnit.METERS);
//            sort.order(SortOrder.ASC);
            boolQueryBuilder.must(location);

            homePageNearEsf = homePageEsDao.getHomePageNearEsf(boolQueryBuilder, nearHouseDoQuery.getSize(), sort, city);
        }else {
            homePageNearEsf = homePageEsDao.getHomePageNearEsfNoLocation(boolQueryBuilder, nearHouseDoQuery.getSize(), city);
        }

        if (null!=homePageNearEsf){
            SearchHit[] hits = homePageNearEsf.getHits().getHits();
            if (hits!=null&&hits.length>0){
                List<HomePageNearEsfDo> list = new ArrayList<>();
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    HomePageNearEsfDo homePageNearEsfDo = JSON.parseObject(sourceAsString, HomePageNearEsfDo.class);

                    //判断3天内导入，且无图片，默认上显示默认图
                    String importTime = homePageNearEsfDo.getImportTime();
                    int isDefault = sellHouseService.isDefaultImage(importTime ,date, homePageNearEsfDo.getHousePhotoTitle());
                    if(isDefault==1){
                        homePageNearEsfDo.setIsDefaultImage(1);
                    }

                    if (flag){
                        homePageNearEsfDo.setDistance((double) Math.round((Double) hit.getSortValues()[1]));
                    }

                    AgentBaseDo agent = new AgentBaseDo();
                    //替换认领信息
                    if(StringTool.isNotEmpty(sourceAsMap.get("userId"))&&((int)sourceAsMap.get("is_claim"))==1){
                        homePageNearEsfDo.setHouseId((String) sourceAsMap.get("claimHouseId"));
                        homePageNearEsfDo.setHouseTitle((String) sourceAsMap.get("claimHouseTitle"));
                        homePageNearEsfDo.setTagsName((List) sourceAsMap.get("claimTagsName"));
                        homePageNearEsfDo.setHousePhotoTitle((String) sourceAsMap.get("claimHousePhotoTitle"));

                        agent = agentService.queryAgentInfoByUserId((String) sourceAsMap.get("userId"), city);

                    }else {
                        agent.setAgentCompany(hit.getSourceAsMap().get("ofCompany").toString());
                        agent.setAgentName(hit.getSourceAsMap().get("houseProxyName").toString());
                        agent.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agent.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone").toString());
                    }
                    homePageNearEsfDo.setAgentBaseDo(agent);
                    homePageNearEsfDo.setUnitPrice((double) Math.round((homePageNearEsfDo.getHouseTotalPrices()/homePageNearEsfDo.getBuildArea())*10000));
                    list.add(homePageNearEsfDo);
                }
                homePageNearEsfListDo.setData(list);
                homePageNearEsfListDo.setTotalNum((int) homePageNearEsf.getHits().getTotalHits());
            }
        }
        return homePageNearEsfListDo;
    }

    /**
     * 专题着陆页-附近小区
     * @param nearHouseSpecialPageDoQuery
     * @return
     */
    @Override
    public HomePageNearPlotDo getPlotSpecialPage(NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery, String city) {
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        HomePageNearPlotDo homePageNearPlotDo = new HomePageNearPlotDo();

        //组装条件
        boolQueryBuilder.must(QueryBuilders.termQuery("id",nearHouseSpecialPageDoQuery.getPlotId()));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));

        //按距离排序并计算距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearHouseSpecialPageDoQuery.getLat(), nearHouseSpecialPageDoQuery.getLon());
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);

        SearchResponse plotSpecialPage = homePageEsDao.getPlotSpecialPage(boolQueryBuilder, sort, city);

        SearchHit[] hits = plotSpecialPage.getHits().getHits();

        if (hits.length>0){
            String sourceAsString = hits[0].getSourceAsString();
            homePageNearPlotDo = JSON.parseObject(sourceAsString, HomePageNearPlotDo.class);
            homePageNearPlotDo.setDistance((double) Math.round((Double) hits[0].getSortValues()[0]));

            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(homePageNearPlotDo.getId(),CityUtils.getCity());
            if(null != plotsEsfRoomCountDomain.getTotalCount()){
                homePageNearPlotDo.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
            }else{
                homePageNearPlotDo.setHouseCount(0);
            }
        }
        return homePageNearPlotDo;
    }

    /**
     * 专题着陆页-附近二手房
     * @param nearHouseSpecialPageDoQuery
     * @return
     */
    @Override
    public HomePageNearEsfListDo getEsfSpecialPage(NearHouseSpecialPageDoQuery nearHouseSpecialPageDoQuery, String city) {

        Date date = new Date();
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        HomePageNearEsfListDo homePageNearEsfListDo = new HomePageNearEsfListDo();

        Integer from = (nearHouseSpecialPageDoQuery.getPageNum()-1)*nearHouseSpecialPageDoQuery.getSize();

        //组装条件
        boolQueryBuilder.must(QueryBuilders.termQuery("newcode", nearHouseSpecialPageDoQuery.getPlotId()));
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));

        //按距离排序并计算距离
//        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("housePlotLocation", nearHouseSpecialPageDoQuery.getLat(), nearHouseSpecialPageDoQuery.getLon());
//        sort.unit(DistanceUnit.METERS);
//        sort.order(SortOrder.ASC);

        SearchResponse esfSpecialPage = homePageEsDao.getEsfSpecialPage(boolQueryBuilder,from,nearHouseSpecialPageDoQuery.getSize(),city);

        SearchHit[] hits = esfSpecialPage.getHits().getHits();
        if (hits.length>0){
            List<HomePageNearEsfDo> list = new ArrayList<>();
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                HomePageNearEsfDo homePageNearEsfDo = JSON.parseObject(sourceAsString, HomePageNearEsfDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = homePageNearEsfDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, homePageNearEsfDo.getHousePhotoTitle());
                if(isDefault==1){
                    homePageNearEsfDo.setIsDefaultImage(1);
                }

//                homePageNearEsfDo.setUpTimestamp(hit.getSortValues()[0].toString());
//                homePageNearEsfDo.setUid(hit.getSortValues()[1].toString().split("#")[1]);
//                homePageNearEsfDo.setDistance((double) Math.round((Double) hit.getSortValues()[2]));

                AgentBaseDo agent = new AgentBaseDo();
                //替换认领信息
                if(StringTool.isNotEmpty(sourceAsMap.get("userId"))&&((int)sourceAsMap.get("is_claim"))==1){
                    homePageNearEsfDo.setHouseId((String) sourceAsMap.get("claimHouseId"));
                    homePageNearEsfDo.setHouseTitle((String) sourceAsMap.get("claimHouseTitle"));
                    homePageNearEsfDo.setTagsName((List) sourceAsMap.get("claimTagsName"));
                    homePageNearEsfDo.setHousePhotoTitle((String) sourceAsMap.get("claimHousePhotoTitle"));

                    agent = agentService.queryAgentInfoByUserId((String) sourceAsMap.get("userId"), city);

                }else {
                    agent.setAgentCompany(hit.getSourceAsMap().get("ofCompany").toString());
                    agent.setAgentName(hit.getSourceAsMap().get("houseProxyName").toString());
                    agent.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agent.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone").toString());
                }
                homePageNearEsfDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                homePageNearEsfDo.setAgentBaseDo(agent);
                homePageNearEsfDo.setUnitPrice((double) Math.round((homePageNearEsfDo.getHouseTotalPrices()/homePageNearEsfDo.getBuildArea())*10000));
                list.add(homePageNearEsfDo);
            }
            homePageNearEsfListDo.setData(list);
            homePageNearEsfListDo.setTotalNum((int) esfSpecialPage.getHits().getTotalHits());
        }
        return homePageNearEsfListDo;
    }


    private List hashPush(List<HomePageEsfDo> result, HomePageEsfDo homePageEsfDos) {
        Boolean flag = false;
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                if (null != result.get(i).getClaimHouseId() && null != homePageEsfDos.getClaimHouseId() && result.get(i).getClaimHouseId().equals(homePageEsfDos.getClaimHouseId())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            result.add(homePageEsfDos);
        }
        return result;
    }

    /**
     * 首页获取不买亏二手房8条
     */
    @Override
    public List<HomePageMustBuyDo> getHomePageMustBuy(Integer topicType) {
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        if (topicType == 1) {
            //筛选低价房 isCutPrice=1
            boolQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));
        } else if (topicType == 2) {
            //价格洼地 isLowPrice=1
            boolQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));
        }
        SearchResponse cutPriceHouses = homePageEsDao.getHomePageMustBuy(boolQueryBuilder);
        SearchHit[] hits = cutPriceHouses.getHits().getHits();
        List<HomePageMustBuyDo> homePageMustBuyDos = new ArrayList<>();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                HomePageMustBuyDo homePageMustBuyDo = JSON.parseObject(sourceAsString, HomePageMustBuyDo.class);
                //homePageMustBuyDo.setTypeCounts(communityRestService.getCountByBuildTags());
                homePageMustBuyDos.add(homePageMustBuyDo);
            }
        }
        return homePageMustBuyDos;
    }

    @Override
    public Map<String,HomePageTop50Do> getHomePageTop50() {

//        List<HomePageTop50Do> homePageTop50Dos=new ArrayList<>();
//        List<Map<String,HomePageTop50Do>> homePageTop50Dos=new ArrayList<>();
        Map<String,HomePageTop50Do> map = new HashMap<>();
        int [] isTop={1};
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId",isTop));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve",1));
        SearchResponse top50 = homePageEsDao.getHomePageTop50(boolQueryBuilder, CityUtils.getCity());
        Terms count = top50.getAggregations().get("count");
        List list= count.getBuckets();
        for (Object l:list)
        {    HomePageTop50Do homePageTop50Do=new HomePageTop50Do();
            homePageTop50Do.setDistrictId(((StringTerms.Bucket) l).getKeyAsNumber().intValue());
            homePageTop50Do.setCount( Math.toIntExact((((StringTerms.Bucket) l)).getDocCount()));
            TopHits topHits =((StringTerms.Bucket) l).getAggregations().get("group_hits");
            for (SearchHit hit : topHits.getHits().getHits())
            {
                homePageTop50Do.setDistrictName((String) hit.getSourceAsMap().get("area"));
            }
            map.put(((StringTerms.Bucket) l).getKeyAsString(),homePageTop50Do);

        }
//        homePageTop50Dos.add(map);
        return  map;
    }

    /**
     * 首页缝出必抢（特色楼盘的主力户型）
     * @param homeSureToSnatchDoQuery
     * @return
     */
    @Override
    public List<HomeSureToSnatchDo> getHomeBeSureToSnatch(HomeSureToSnatchDoQuery homeSureToSnatchDoQuery) {

        List<HomeSureToSnatchDo> homeSureToSnatchDos=new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("isCommunityTopHouse",1));
//        boolQueryBuilder.must(QueryBuilders.termQuery("isMainLayout",1));
        boolQueryBuilder.must(QueryBuilders.termQuery("status",0));
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel",0));
        boolQueryBuilder.must(QueryBuilders.termQuery("isMustRob",1));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        SearchResponse beSureToSnatch=homePageEsDao.getHomeBeSureToSnatch(boolQueryBuilder,homeSureToSnatchDoQuery.getPageNum(),homeSureToSnatchDoQuery.getPageSize());
        SearchHit[] hits = beSureToSnatch.getHits().getHits();
        for (SearchHit hit : hits) {
            String details = "";
            details = hit.getSourceAsString();
            HomeSureToSnatchDo  homeSureToSnatchDo = JSON.parseObject(details, HomeSureToSnatchDo.class);
//            homeSureToSnatchDo.setTypeCounts(communityRestService.getCountByBuildTags());
            if(homeSureToSnatchDo.getIsClaim().equals(1))
            {
                homeSureToSnatchDo.setHousePhotoTitle(homeSureToSnatchDo.getClaimHousePhotoTitle());
                homeSureToSnatchDo.setHouseId(homeSureToSnatchDo.getClaimHouseId());
            }
            homeSureToSnatchDos.add(homeSureToSnatchDo);
        }
        return homeSureToSnatchDos;


    }

    @Override
    public Integer saveRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {

        UserFavoriteConditionDo recommendCondition = homePageRestService.getRecommendCondition(userFavoriteConditionDoQuery.getUserId(), city);
        Integer result = 0;
        if (StringTool.isNotEmpty(recommendCondition.getUserId())){
            result = homePageRestService.updateRecommendCondition(userFavoriteConditionDoQuery, city);
            return result;
        }
        UserFavoriteCondition userFavoriteCondition = new UserFavoriteCondition();
        userFavoriteCondition.setCondition(JSON.toJSONString(userFavoriteConditionDoQuery));
        userFavoriteCondition.setIsDel(0);
        userFavoriteCondition.setCreateTime(new Date());
        userFavoriteCondition.setUpdateTime(new Date());
        userFavoriteCondition.setUserId(userFavoriteConditionDoQuery.getUserId());
        userFavoriteCondition.setCityId(CityUtils.returnCityId(city));
        result = userFavoriteConditionMapper.insertSelective(userFavoriteCondition);
        return result;
    }

    @Override
    public UserFavoriteConditionDo getRecommendCondition(Integer userId, String city) {
        UserFavoriteConditionDo userFavoriteConditionDo = new UserFavoriteConditionDo();
        UserFavoriteCondition recommendCondition = userFavoriteConditionMapper.getRecommendCondition(userId, CityUtils.returnCityId(city));
        if (StringTool.isNotEmpty(recommendCondition)&&StringTool.isNotEmpty(recommendCondition.getCondition())){
            JSONObject jsonObject = JSON.parseObject(((PGobject) recommendCondition.getCondition()).getValue());
            userFavoriteConditionDo = JSON.parseObject(JSON.toJSONString(jsonObject), UserFavoriteConditionDo.class);
            userFavoriteConditionDo.setCity(CityUtils.retuenCityCode(recommendCondition.getCityId()));
        }
        return userFavoriteConditionDo;
    }

    @Override
    public Integer updateRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        UserFavoriteCondition userFavoriteCondition = new UserFavoriteCondition();
        userFavoriteCondition.setCondition(JSON.toJSONString(userFavoriteConditionDoQuery));
        userFavoriteCondition.setUpdateTime(new Date());
        userFavoriteCondition.setUserId(userFavoriteConditionDoQuery.getUserId());
        userFavoriteCondition.setCityId(CityUtils.returnCityId(city));
        int result = userFavoriteConditionMapper.updateRecommendCondition(userFavoriteCondition);
        return result;
    }

    @Override
    public Integer deleteRecommendCondition(Integer userId, Integer cityId) {

        int result = userFavoriteConditionMapper.deleteRecommendCondition(userId ,cityId);
        return result;
    }
}
