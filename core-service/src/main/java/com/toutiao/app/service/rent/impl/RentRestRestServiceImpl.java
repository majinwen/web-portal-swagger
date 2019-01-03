package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.favorite.FavoriteHouseDomain;
import com.toutiao.app.domain.favorite.FavoriteHouseListDoQuery;
import com.toutiao.app.domain.favorite.FavoriteHouseVo;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.plot.PlotsHousesDomain;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.favorite.RentFavoriteRestService;
import com.toutiao.app.service.plot.PlotsHomesRestService;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class RentRestRestServiceImpl implements RentRestService {
    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 0;//出租:1
    private static final Integer FOCUS_APARTMENT = 2;//公寓:2
    private static final Integer DISPERSED_APARTMENTS = 1;//公寓:2
    private static final String LAYOUT = "4";
    //租房标识
    private final Integer FAVORITE_RENT = 1;

    @Autowired
    private RentEsDao rentEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;
    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private FavoriteRestService favoriteRestService;
    @Autowired
    private PlotEsDao plotEsDao;
    @Autowired
    private PlotsHomesRestService plotsHomesRestService;
    @Autowired
    private RentFavoriteRestService rentFavoriteRestService;

    @Autowired
    private RentRestRestServiceImpl rentRestRestService;
    /**
     * 租房详情信息
     *
     * @param rentId
     * @return
     */
    @Override
    public RentDetailsDo queryRentDetailByHouseId(String rentId, String city) {
        Date date = new Date();
        RentDetailsDo rentDetailsDo = new RentDetailsDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("house_id", rentId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", IS_DEL));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", RELEASE_STATUS));
        SearchResponse searchResponse = rentEsDao.queryRentByRentId(boolQueryBuilder, city);
        SearchHit[] hits = searchResponse.getHits().getHits();
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();

            for (SearchHit searchHit : hits) {
                String sourceAsString = searchHit.getSourceAsString();
                rentDetailsDo = JSON.parseObject(sourceAsString, RentDetailsDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsDo.setIsDefaultImage(1);
                }


                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) searchHit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsDo.setHousePhoto(imgs.toArray(new String[0]));

                if (rentDetailsDo.getRentHouseType() == 1 && StringTool.isNotEmpty(rentDetailsDo.getUserId())) {
                    //经纪人信息
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsDo.getUserId().toString(), city);
                    if (StringTool.isNotEmpty(agentBaseDo)) {
                        rentDetailsDo.setPhone(agentBaseDo.getDisplayPhone());
                        rentDetailsDo.setAgentHeadPhoto(agentBaseDo.getHeadPhoto());
                        rentDetailsDo.setBrokerageAgency(agentBaseDo.getAgentCompany());
                        rentDetailsDo.setEstateAgent(agentBaseDo.getAgentName());
                        if (StringTool.isNotEmpty(agentBaseDo.getAgentBusinessCard())) {
                            rentDetailsDo.setAgentBusinessCard(agentBaseDo.getAgentBusinessCard().toString());
                        }
                    }
                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("estate_agent") == null ? "" : searchHit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("brokerage_agency") == null ? "" : searchHit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("agent_headphoto") == null ? "" : searchHit.getSourceAsMap().get("agent_headphoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("phone") == null ? "" : searchHit.getSourceAsMap().get("phone").toString());
                    agentBaseDo.setUserId(searchHit.getSourceAsMap().get("userId") == null ? "" : searchHit.getSourceAsMap().get("userId").toString());
                }
                rentDetailsDo.setAgentBaseDo(agentBaseDo);

                //公司图标
                String AgentCompany = rentDetailsDo.getBrokerageAgency();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

            }
            try {

                UserBasic userBasic = UserBasic.getCurrent();
                if (StringTool.isNotEmpty(userBasic)) {
                    IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
                    isFavoriteDo.setUserId(Integer.valueOf(userBasic.getUserId()));
                    isFavoriteDo.setHouseId(rentDetailsDo.getHouseId());
                    boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_RENT, isFavoriteDo);
                    rentDetailsDo.setIsFavorite(isFavorite);
                }
            } catch (BaseException e) {
                rentDetailsDo.setIsFavorite(Boolean.FALSE);
            }
        }

        Integer plotId = rentDetailsDo.getZufangId();

        if (null != plotId) {

            String details = "";
            BoolQueryBuilder plotBoolQueryBuilder = QueryBuilders.boolQuery();
            plotBoolQueryBuilder.must(QueryBuilders.termQuery("id", plotId));
            SearchResponse plotSearchResponse = plotEsDao.queryPlotDetail(plotBoolQueryBuilder, city);
            SearchHit[] plotHits = plotSearchResponse.getHits().getHits();

            for (SearchHit searchHit : plotHits) {
                details = searchHit.getSourceAsString();
            }

            if (StringUtils.isNotEmpty(details)) {
                PlotDetailsDo plotDetailsDo = JSON.parseObject(details, PlotDetailsDo.class);
                PlotsHousesDomain plotsHousesDomain = plotsHomesRestService.queryPlotsHomesByPlotId(plotId, city);

                plotsHousesDomain.setAvgPrice(plotDetailsDo.getAvgPrice());
                plotDetailsDo.setPlotsHousesDomain(plotsHousesDomain);
                rentDetailsDo.setPlotDetailsDo(plotDetailsDo);
            }
        }
        return rentDetailsDo;
    }

    /**
     * 根据小区id查询该小区下的出租房源
     *
     * @param plotId
     * @return
     */
    @Override
    public RentDetailsListDo queryRentListByPlotId(Integer plotId, Integer rentType, Integer pageNum, Integer pageSize,String city) {
        Date date = new Date();
        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id", plotId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));
        if (StringTool.isNotEmpty(rentType)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("rent_type", rentType));
        }
        Integer from = (pageNum - 1) * 10;
        SearchResponse searchResponse = rentEsDao.queryRentListByPlotId(boolQueryBuilder, from,pageSize, city);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsFewDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsFewDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsFewDo.setIsDefaultImage(1);
                }
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                    agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                }
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                list.add(rentDetailsFewDo);
            }
            rentDetailsListDo.setRentDetailsList(list);
            rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
//        } else {
//            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_RENT_NOT_FOUND, "小区没有出租房源信息");
        } else {
            rentDetailsListDo.setRentDetailsList(list);
            rentDetailsListDo.setTotalCount(0);
        }
        return rentDetailsListDo;
    }

    /**
     * 根据出租房源的id查询该小区下的出租房源的个数
     *
     * @param plotId
     * @return
     */
    @Override
    public RentNumListDo queryRentNumByPlotId(Integer plotId, String city) {
        RentNumListDo rentNumListDo = new RentNumListDo();
        List<RentNumDo> list = new ArrayList<>();
        RentNumDo rentNumDo = new RentNumDo();
        RentNumDo rentNumDo2 = new RentNumDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id", plotId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));
        SearchResponse searchResponse = rentEsDao.queryRentNumByPlotId(boolQueryBuilder, city);
        long zhengzu = ((ParsedFilter) searchResponse.getAggregations().get("ZHENGZU")).getDocCount();
        long hezu = ((ParsedFilter) searchResponse.getAggregations().get("HEZU")).getDocCount();
        rentNumDo.setNum((int) zhengzu);
        rentNumDo.setRentSign(1);
        rentNumDo.setRentSignName("整租");
        list.add(rentNumDo);
        rentNumDo2.setNum((int) hezu);
        rentNumDo2.setRentSign(2);
        rentNumDo2.setRentSignName("合租");
        list.add(rentNumDo2);
        rentNumListDo.setData(list);
        rentNumListDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//        if (searchResponse.getHits().getTotalHits() == 0) {
//            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_RENT_NOT_FOUND, "小区没有出租房源信息");
//        }
        return rentNumListDo;
    }

//    /**
//     * 根据出租房源的id查询相关的经纪人
//     * @param rentId
//     * @return
//     */
//    @Override
//    public RentAgentDo queryRentAgentByRentId(String rentId) {
//        try {
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",rentId));
//            SearchResponse searchResponse = agentHouseEsDao.getAgentRentByRentId(boolQueryBuilder);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length>0){
//                long time = System.currentTimeMillis();
//                long index = (time / 600000) % hits.length;
//                String sourceAsString = hits[(int) index].getSourceAsString();
//                RentAgentDo rentAgentDo = JSON.parseObject(sourceAsString, RentAgentDo.class);
//                return rentAgentDo;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    /**
     * 附近5km内出租房源(规则:app的是吧，那就优先三公里的录入房源由近到远)
     *
     * @param nearHouseDo
     * @return
     */
    @Override
    public List<RentDetailsFewDo> queryNearHouseByLocation(NearHouseDo nearHouseDo) {
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //从该坐标查询距离为5000内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(nearHouseDo.getLat(), nearHouseDo.getLon()).distance(nearHouseDo.getDistance(), DistanceUnit.KILOMETERS);
        //按照距离排序由近到远并获取小区之间的距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearHouseDo.getLat(), nearHouseDo.getLon());
        sort.unit(DistanceUnit.KILOMETERS);
        sort.order(SortOrder.ASC);
        Integer size = 10;
        Integer from = (nearHouseDo.getPageNum() - 1) * size;
        SearchResponse searchResponse = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(boolQueryBuilder, nearHouseDo), location, sort, from, size);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                rentDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                fullHouseBarrage(rentDetailsFewDo);
                //设置公司图标
                String AgentCompany = rentDetailsFewDo.getBrokerageAgency();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                list.add(rentDetailsFewDo);
            }
        }
        if (hits.length > 0 && hits.length < 10) {
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            nearHouseDo.setRentHouseType(3);
            long From = ((nearHouseDo.getPageNum() - ((searchResponse.getHits().getTotalHits() / 10) + 1)) * size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(booleanQueryBuilder, nearHouseDo), location, sort, (int) From, size - hits.length);
            SearchHit[] hits1 = response.getHits().getHits();
            if (hits1.length > 0) {
                for (SearchHit hit : hits1) {
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    rentDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits() + (int) response.getHits().getTotalHits());
                    fullHouseBarrage(rentDetailsFewDo);
                    //设置公司图标
                    String AgentCompany = rentDetailsFewDo.getBrokerageAgency();
                    if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                        rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                    }
                    list.add(rentDetailsFewDo);
                }
            }
        } else if (hits.length == 0) {
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            nearHouseDo.setRentHouseType(3);
            long From = ((nearHouseDo.getPageNum() - ((searchResponse.getHits().getTotalHits() / 10) + 1)) * size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(booleanQueryBuilder, nearHouseDo), location, sort, (int) From, size);
            SearchHit[] hits1 = response.getHits().getHits();
            if (hits1.length > 0) {
                for (SearchHit hit : hits1) {
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    rentDetailsFewDo.setTotalNum((int) response.getHits().getTotalHits());
                    fullHouseBarrage(rentDetailsFewDo);
                    //设置公司图标
                    String AgentCompany = rentDetailsFewDo.getBrokerageAgency();
                    if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                        rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                    }
                    list.add(rentDetailsFewDo);
                }
            }
        }
        return list;
    }

    /**
     * 推荐租房列表，7天内上新
     *
     * @param rentHouseDoQuery
     * @return
     */
    @Override
    public RentDetailsListDo getRentList(RentHouseDoQuery rentHouseDoQuery, String city) {
        Date date = new Date();
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getRecommendRentBoolQueryBuilder(boolQueryBuilder, rentHouseDoQuery);
//        Date date = new Date();
//        String nowDate = DateUtil.format(date);
//        nowDate = nowDate+" 00:00:00";
//
//        String pastDate = DateUtil.getPastDate(7);
//        pastDate = pastDate+" 00:00:00";
//
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("update_time").gt(pastDate).lte(nowDate));
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gte(0));
//        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType","1"));


        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源
//        boolQueryBuilder.must(QueryBuilders.termQuery("rent_type","1"));
        if (CityConstant.ABBREVIATION_BEIJING.equals(city)) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gt(4000).lte(6000));
        } else if (CityConstant.ABBREVIATION_SHANGHAI.equals(city)) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gt(2000).lte(4000));
        } else if (CityConstant.ABBREVIATION_TIANJIN.equals(city)) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gt(1300).lte(1500));
        }

        Integer size = 10;
        Integer from = (rentHouseDoQuery.getPageNum() - 1) * size;

        SearchResponse searchResponse = rentEsDao.queryRentList(boolQueryBuilder, from, size, city);
        SearchHit[] hits = searchResponse.getHits().getHits();

        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit searchHit : hits) {
                String sourceAsString = searchHit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsFewDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsFewDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsFewDo.setIsDefaultImage(1);
                }

                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) searchHit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsFewDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("estate_agent") == null ? "" : searchHit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("brokerage_agency") == null ? "" : searchHit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("phone") == null ? "" : searchHit.getSourceAsMap().get("phone").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("agent_headphoto") == null ? "" : searchHit.getSourceAsMap().get("agent_headphoto").toString());

                }
                //设置公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                fullHouseBarrage(rentDetailsFewDo);
                list.add(rentDetailsFewDo);
            }
            rentDetailsListDo.setRentDetailsList(list);
            rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
//        } else {
//            throw new BaseException(RentInterfaceErrorCodeEnum.RENT_NOT_FOUND, "租房推荐列表为空");
        }

        return rentDetailsListDo;
    }

    /**
     * 租房推优房源
     *
     * @param rentHouseDoQuery
     * @return
     */
    @Override
    public RentDetailsFewDo queryRecommendRent(RentHouseDoQuery rentHouseDoQuery, String city) {

        String uid = rentHouseDoQuery.getUid();
        RentDetailsFewDo rentDetailsFewDo = new RentDetailsFewDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getRecommendRentBoolQueryBuilder(boolQueryBuilder, rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));

        SearchResponse searchResponse = rentEsDao.queryRecommendRentList(boolQueryBuilder, uid, city);

        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit searchHit : hits) {
                String sourceAsString = searchHit.getSourceAsString();
                rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) searchHit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsFewDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("estate_agent") == null ? "" : searchHit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("brokerage_agency") == null ? "" : searchHit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("phone") == null ? "" : searchHit.getSourceAsMap().get("phone").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("agent_headphoto") == null ? "" : searchHit.getSourceAsMap().get("agent_headphoto").toString());

                }
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                rentDetailsFewDo.setUid(searchHit.getSortValues()[0].toString());
            }

//        } else {
//            throw new BaseException(RentInterfaceErrorCodeEnum.RECOMMEND_RENT_NOT_FOUND, "未查询到租房推优房源");
        }

        return rentDetailsFewDo;
    }

    @Override
    public RentDetailsListDo getRentHouseSearchList(RentHouseDoQuery rentHouseDoQuery, String city) {


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Date date = new Date();

        //添加筛选条件
        BoolQueryBuilder booleanQueryBuilder = getRecommendRentBoolQueryBuilder(boolQueryBuilder, rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源
        FunctionScoreQueryBuilder query = null;
        //设置基础分(录入优先展示)(录入:1,导入1/3)
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("rentHouseTypeId")
                .modifier(FieldValueFactorFunction.Modifier.RECIPROCAL).missing(10);

        //坐标
//        Map<String,Double> map = new HashMap<>();
//        map.put("lat",rentHouseDoQuery.getLat());
//        map.put("lon",rentHouseDoQuery.getLon());
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));

        GaussDecayFunctionBuilder functionBuilder = null;
        GeoDistanceSortBuilder geoDistanceSort = null;
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
            double[] location = new double[]{rentHouseDoQuery.getLon(), rentHouseDoQuery.getLat()};

            //设置高斯函数(要保证5km内录入的排在导入的前面,录入房源的最低分需要大于导入的最高分)
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location", location, "4km", "1km", 0.4);
            //根据坐标计算距离

            geoDistanceSort = SortBuilders.geoDistanceSort("location", rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
        }

        //获取5km内的所有出租房源(函数得分进行加法运算,查询得分和函数得分进行乘法运算)
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);


        if (StringUtil.isNotNullString(rentHouseDoQuery.getKeyword())) {
            List<String> searchKeyword = nearRentHouseRestService.getAnalyzeByKeyWords(rentHouseDoQuery.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
            } else {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("district_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else {
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("zufang_name_search", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }

            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }


        } else {
            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, functionBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }
        }

        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        List<RentDetailsFewDo> rentDetailsFewDos = new ArrayList<>();
        SearchResponse searchResponse = rentEsDao.queryRentSearchList(query, rentHouseDoQuery.getDistance(), rentHouseDoQuery.getKeyword(), rentHouseDoQuery.getPageNum(),
                rentHouseDoQuery.getPageSize(), city, geoDistanceSort, rentHouseDoQuery.getSort());
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                String nearbyDistance = StringTool.nullToString(rentDetailsFewDo.getDistrictName()) + " " + StringTool.nullToString(rentDetailsFewDo.getAreaName());
                String traffic = rentDetailsFewDo.getNearestSubway();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }

                if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                    BigDecimal geoDis;
                    if (rentHouseDoQuery.getSort().equals("0")) {
                        geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
                    } else {
                        geoDis = new BigDecimal((Double) hit.getSortValues()[1]);
                    }
                    String distances= geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                    nearbyDistance = "距您" + distances;
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsFewDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsFewDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsFewDo.setIsDefaultImage(1);
                }


                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) hit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsFewDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                    agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                }

                //增加房子与地铁的距离
                String keys = "";
                if (null != rentHouseDoQuery.getSubwayLineId() && rentHouseDoQuery.getSubwayLineId() > 0) {
                    keys += rentHouseDoQuery.getSubwayLineId().toString();
                    //增加地铁线选择，地铁站选择不限
                    if(StringTool.isNotEmpty(rentDetailsFewDo.getNearbySubway().get(keys))){
                        trafficArr = rentDetailsFewDo.getNearbySubway().get(keys).split("\\$");
                        if (trafficArr.length == 3) {
//                            nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                            nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                        }
                    }
                }
//                if (null != rentHouseDoQuery.getSubwayStationId()) {
//                    keys += "$" + rentHouseDoQuery.getSubwayStationId();
//                }
//                if (!"".equals(keys) && null != rentDetailsFewDo.getNearbySubway()) {
//                    rentDetails
// ewDo.setSubwayDistanceInfo(rentDetailsFewDo.getNearbySubway().get(keys).toString());
//                }
                if (null != rentHouseDoQuery.getSubwayStationId() && rentHouseDoQuery.getSubwayStationId().length > 0) {
                    Map<Integer,String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for (int i=0; i<rentHouseDoQuery.getSubwayStationId().length; i++) {
                        String stationKey = keys+"$"+rentHouseDoQuery.getSubwayStationId()[i];
                        if (StringTool.isNotEmpty(rentDetailsFewDo.getNearbySubway().get(stationKey))) {
                            String stationValue = rentDetailsFewDo.getNearbySubway().get(stationKey);
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance,stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    rentDetailsFewDo.setSubwayDistanceInfo(rentDetailsFewDo.getNearbySubway().get(map.get(minDistance)));
                    trafficArr = rentDetailsFewDo.getSubwayDistanceInfo().split("\\$");
                    if (trafficArr.length == 3) {
//                        nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                        nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";

                    }
                }

                if(StringTool.isNotEmpty(nearbyDistance)){
                    rentDetailsFewDo.setNearbyDistance(nearbyDistance);
                }

                //设置标题图
                String titlePhoto = rentDetailsFewDo.getHouseTitleImg();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dfdo400x300";
                }
                rentDetailsFewDo.setHouseTitleImg(titlePhoto);

                //设置公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                fullHouseBarrage(rentDetailsFewDo);
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                rentDetailsFewDos.add(rentDetailsFewDo);

            }
        }
        rentDetailsListDo.setRentDetailsList(rentDetailsFewDos);
        rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return rentDetailsListDo;
    }

    @Override
    public RentDetailsListDo rentGuessYouLike(RentGuessYourLikeQuery rentGuessYourLikeQuery, String city,Integer userId) {
        boolean isQueryEs = false;
        boolean isUserQuery = false;
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();

        if (userId != null) {
            RentFavoriteListDoQuery rentFavoriteListDoQuery = new RentFavoriteListDoQuery();
            rentFavoriteListDoQuery.setUserId(userId);
            rentFavoriteListDoQuery.setSize(10);
            rentFavoriteListDoQuery.setPageNum(1);

            RentFavoriteDomain rentFavoriteDomain = rentFavoriteRestService.guessULikeRentByUserId(rentFavoriteListDoQuery);
            if (rentFavoriteDomain.getData().size() > 0){
                isQueryEs = true;
                isUserQuery = true;
                RentFavoriteDo rentFavoriteDo = rentFavoriteDomain.getData().get(rentFavoriteDomain.getData().size()-1);
                RentDetailsDo  rentDetailsDo = rentRestRestService.queryRentDetailByHouseId(rentFavoriteDo.getHouseId(),city);

                if (rentDetailsDo.getAreaId()!=null){
                    boolQueryBuilder.must(termQuery("area_id", rentDetailsDo.getAreaId()));
                }

                if (rentDetailsDo.getHall()!=null){
                    boolQueryBuilder.must(termQuery("hall",rentDetailsDo.getHall()));
                }

                if (rentDetailsDo.getRoom()!=null){
                    boolQueryBuilder.must(termQuery("room", rentDetailsDo.getRoom()));
                }
                if (rentDetailsDo.getRentType()!=null){
                    boolQueryBuilder.must(termQuery("rent_type", rentDetailsDo.getRentType()));
                }

                if (rentDetailsDo.getRentHousePrice()!=null){
                    boolQueryBuilder.filter(rangeQuery("rent_house_price").gte(rentDetailsDo.getRentHousePrice()+rentDetailsDo.getRentHousePrice()*0.3).lte(rentDetailsDo.getRentHousePrice()-rentDetailsDo.getRentHousePrice()*0.3));
                }
            }
        }

        if (!isUserQuery){
                if (rentGuessYourLikeQuery.getAreaId()!=null){
                    boolQueryBuilder.must(termQuery("area_id", rentGuessYourLikeQuery.getAreaId()));
                    isQueryEs = true;
                }

                if (rentGuessYourLikeQuery.getHall()!=null){
                    boolQueryBuilder.must(termQuery("hall",rentGuessYourLikeQuery.getHall()));
                    isQueryEs = true;
                }

                if (rentGuessYourLikeQuery.getRoom()!=null){
                    boolQueryBuilder.must(termQuery("room", rentGuessYourLikeQuery.getRoom()));
                    isQueryEs = true;
                }

                if (rentGuessYourLikeQuery.getRentType()!=null){
                    boolQueryBuilder.must(termQuery("rent_type", rentGuessYourLikeQuery.getRentType()));
                    isQueryEs = true;
                }

                if (rentGuessYourLikeQuery.getTotalPrice()!=null){
                    Double rent_house_price = rentGuessYourLikeQuery.getTotalPrice();
                    boolQueryBuilder.filter(rangeQuery("rent_house_price").gte(rent_house_price-rent_house_price*0.3).lte(rent_house_price+rent_house_price*0.3));
                    isQueryEs = true;
                }


        }
        SearchHit[]  hits = new SearchHit[0];
        List<RentDetailsFewDo> rentDetailsFewDos =  new ArrayList<>();
        if (isQueryEs){

            SearchResponse searchResponse = rentEsDao.guessYoourLikeRent(boolQueryBuilder, city, rentGuessYourLikeQuery.getPageNum(),rentGuessYourLikeQuery.getPageSize());
             hits = searchResponse.getHits().getHits();
        }


               for (SearchHit hit : hits) {
                   String sourceAsString = hit.getSourceAsString();
                   RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                   String nearbyDistance = StringTool.nullToString(rentDetailsFewDo.getDistrictName()) + " " + StringTool.nullToString(rentDetailsFewDo.getAreaName());
                   rentDetailsFewDo.setNearbyDistance(nearbyDistance);
                   rentRestRestService.fullHouseBarrage(rentDetailsFewDo);
                   AgentBaseDo agentBaseDo = new AgentBaseDo();
                   if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                       agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                   } else {
                       agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                       agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                       agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                       agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                   }
                   rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                   //设置公司图标
                   String AgentCompany = agentBaseDo.getAgentCompany();
                   if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                       rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                   }
                   rentDetailsFewDos.add(rentDetailsFewDo);
               }


            int pageSizeResidue = 10 - hits.length;
            BoolQueryBuilder boolQueryBuilder7Day = QueryBuilders.boolQuery();
                Date date = new Date();
                String today =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                Calendar cal=Calendar.getInstance();
                cal.add(Calendar.DATE,-7);
                Date sevenDaysAgo=cal.getTime();
                String sevenDaysAgoChar =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(sevenDaysAgo);
                boolQueryBuilder7Day.must(rangeQuery("update_time").lte(today).gte(sevenDaysAgoChar));
            SearchResponse searchResponse7Day = rentEsDao.guessYoourLikeRent(boolQueryBuilder7Day, city, rentGuessYourLikeQuery.getPageNum(),pageSizeResidue);
            SearchHit[]  hits7Day = searchResponse7Day.getHits().getHits();
            if (hits7Day.length > 0){
                for (SearchHit hit : hits7Day) {
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    String nearbyDistance = StringTool.nullToString(rentDetailsFewDo.getDistrictName()) + " " + StringTool.nullToString(rentDetailsFewDo.getAreaName());
                    rentDetailsFewDo.setNearbyDistance(nearbyDistance);
                    rentRestRestService.fullHouseBarrage(rentDetailsFewDo);
                    AgentBaseDo agentBaseDo = new AgentBaseDo();
                    if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                    } else {
                        agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                        agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                        agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                        agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                    }
                    rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                    //设置公司图标
                    String AgentCompany = agentBaseDo.getAgentCompany();
                    if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                        rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                    }
                    rentDetailsFewDos.add(rentDetailsFewDo);
                }
            }

            if (rentDetailsFewDos.size()<10){
                boolQueryBuilder7Day = QueryBuilders.boolQuery();
                int residueNum = 10 - rentDetailsFewDos.size();
                searchResponse7Day = rentEsDao.guessYoourLikeRent(boolQueryBuilder7Day, city, rentGuessYourLikeQuery.getPageNum(),residueNum);
                hits7Day = searchResponse7Day.getHits().getHits();
                if (hits7Day.length > 0){
                    for (SearchHit hit : hits7Day) {
                        String sourceAsString = hit.getSourceAsString();
                        RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                        String nearbyDistance = StringTool.nullToString(rentDetailsFewDo.getDistrictName()) + " " + StringTool.nullToString(rentDetailsFewDo.getAreaName());
                        rentDetailsFewDo.setNearbyDistance(nearbyDistance);
                        rentRestRestService.fullHouseBarrage(rentDetailsFewDo);
                        AgentBaseDo agentBaseDo = new AgentBaseDo();
                        if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                            agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                        } else {
                            agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                            agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                            agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                            agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                        }
                        rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                        //设置公司图标
                        String AgentCompany = agentBaseDo.getAgentCompany();
                        if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                            rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                        }
                        rentDetailsFewDos.add(rentDetailsFewDo);
                    }
                }
            }

        rentDetailsListDo.setRentDetailsList(rentDetailsFewDos);
        rentDetailsListDo.setTotalCount(rentDetailsFewDos.size());
        return rentDetailsListDo;
    }

    @Override
    public RentDetailsListDo getSimilarRentHouseSearchList(RentHouseDoQuery rentHouseDoQuery, String city) {
       // BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Date date = new Date();

        //添加筛选条件
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

        //商圈id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getAreaId()) && rentHouseDoQuery.getAreaId().length != 0) {
            booleanQueryBuilder.must(termsQuery("area_id", rentHouseDoQuery.getAreaId()));
        }
        //户型(室)
        if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && !StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {

            if (rentHouseDoQuery.getElo().equals("0")) {
                booleanQueryBuilder.must(rangeQuery("erent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getElo().split(",");
                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    booleanQueryBuilder.must(termsQuery("erent_layout", roomresult));
                } else {
                    booleanQueryBuilder.must(termsQuery("erent_layout", room));
                }
            }

        } else if (!StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            if (rentHouseDoQuery.getJlo().equals("0")) {
                booleanQueryBuilder.must(rangeQuery("jrent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getJlo().split(",");

                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    booleanQueryBuilder.must(termsQuery("jrent_layout", roomresult));
                } else {
                    booleanQueryBuilder.must(termsQuery("jrent_layout", room));
                }
            }

        }


        if (StringTool.isNotEmpty(rentHouseDoQuery.getHouseId())) {
            booleanQueryBuilder.mustNot(QueryBuilders.termQuery("house_id", rentHouseDoQuery.getHouseId()));
        }


        if (StringTool.isDoubleNotEmpty(rentHouseDoQuery.getTotalPrice())){
            double beginPrice = rentHouseDoQuery.getTotalPrice()-(rentHouseDoQuery.getTotalPrice()*0.1);
            double endPrice = rentHouseDoQuery.getTotalPrice()+(rentHouseDoQuery.getTotalPrice()*0.1);
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(beginPrice).lte(endPrice));
        }

        GeoDistanceSortBuilder geoDistanceSort = null;
        if (StringTool.isDoubleNotEmpty(rentHouseDoQuery.getLat()) && StringTool.isDoubleNotEmpty(rentHouseDoQuery.getLon()) ) {
            geoDistanceSort = SortBuilders.geoDistanceSort("location", rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
        }

        booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源


        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        List<RentDetailsFewDo> rentDetailsFewDos = new ArrayList<>();
        SearchResponse searchResponse = rentEsDao.querySimilarRentSearchList(booleanQueryBuilder, city, geoDistanceSort);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                String nearbyDistance = "";

                if(rentHouseDoQuery.getBuildingId().equals(rentDetailsFewDo.getZufangId())){
                    nearbyDistance = "小区同户型";
                }else{
                    BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[1]);
                    String distances= geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                    nearbyDistance = "距本房源" + distances;
                }


                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsFewDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsFewDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsFewDo.setIsDefaultImage(1);
                }


                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) hit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsFewDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                    agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                }



                if(StringTool.isNotEmpty(nearbyDistance)){
                    rentDetailsFewDo.setNearbyDistance(nearbyDistance);
                }

                //设置标题图
                String titlePhoto = rentDetailsFewDo.getHouseTitleImg();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                rentDetailsFewDo.setHouseTitleImg(titlePhoto);

                //设置公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                fullHouseBarrage(rentDetailsFewDo);
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                rentDetailsFewDos.add(rentDetailsFewDo);

            }
        }
        rentDetailsListDo.setRentDetailsList(rentDetailsFewDos);
        rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return rentDetailsListDo;
    }

    @Override
    public RentDetailsListDo getCommuteRentHouseSearchList(RentHouseDoQuery rentHouseDoQuery, String city) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Date date = new Date();

        //添加筛选条件
        BoolQueryBuilder booleanQueryBuilder = getCommuteRecommendRentBoolQueryBuilder(boolQueryBuilder, rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源
        FunctionScoreQueryBuilder query = null;


        //根据坐标计算距离
        GeoDistanceSortBuilder sort = null;
        if(StringTool.isNotEmpty(rentHouseDoQuery.getLat())&&StringTool.isNotEmpty(rentHouseDoQuery.getLon())){
            sort = SortBuilders.geoDistanceSort("location", rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
            sort.geoDistance(GeoDistance.ARC);
        }

        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        List<RentDetailsFewDo> rentDetailsFewDos = new ArrayList<>();
        SearchResponse searchResponse = rentEsDao.queryCommuteRentSearchList(booleanQueryBuilder,rentHouseDoQuery.getPageNum(), rentHouseDoQuery.getPageSize(), city, sort,rentHouseDoQuery.getSort());
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                Double location = 0.0;
                if(null!=hit.getSortValues()&&hit.getSortValues().length>0){
                    location = (Double) hit.getSortValues()[1];
                }
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                if(StringTool.isNotEmpty(location)&&location>0){
                    BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[1]);
                    String distance = geoDis.setScale(2, BigDecimal.ROUND_CEILING)+DistanceUnit.KILOMETERS.toString();
                    rentDetailsFewDo.setNearbyDistance(distance);
                    String time = "";
                    String trafficType = rentHouseDoQuery.getTrafficType();
                    if(StringTool.isNotEmpty(rentHouseDoQuery.getTrafficType())&&"0".equals(trafficType)){
                        time = String.valueOf(Math.ceil(location * 1000 / 1.2 /60)).replace(".0","");
                    }
                    if(StringTool.isNotEmpty(rentHouseDoQuery.getTrafficType())&&"1".equals(trafficType)){
                        time = String.valueOf(Math.ceil(location * 1000 / 3 /60)).replace(".0","");
                    }
                    if(StringTool.isNotEmpty(rentHouseDoQuery.getTrafficType())&&"2".equals(trafficType)){
                        time = String.valueOf(Math.ceil(location * 1000 / 300)).replace(".0","");
                    }
                    if(StringTool.isNotEmpty(rentHouseDoQuery.getTrafficType())&&"3".equals(trafficType)){
                        time = String.valueOf(Math.ceil(location * 1000 /600)).replace(".0","");
                    }
                    rentDetailsFewDo.setTime(time);
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = rentDetailsFewDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, rentDetailsFewDo.getHouseTitleImg());
                if (isDefault == 1) {
                    rentDetailsFewDo.setIsDefaultImage(1);
                }


                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) hit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                rentDetailsFewDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(rentDetailsFewDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(hit.getSourceAsMap().get("estate_agent") == null ? "" : hit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("brokerage_agency") == null ? "" : hit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto") == null ? "" : hit.getSourceAsMap().get("agent_headphoto").toString());
                    agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("phone") == null ? "" : hit.getSourceAsMap().get("phone").toString());
                }
                //增加房子与地铁的距离
                String keys = "";
                if (null != rentHouseDoQuery.getSubwayLineId()) {
                    keys += rentHouseDoQuery.getSubwayLineId().toString();
                }

                if (null != rentHouseDoQuery.getSubwayStationId() && rentHouseDoQuery.getSubwayStationId().length !=0) {
                    Map<Integer,String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for (int i=0; i<rentHouseDoQuery.getSubwayStationId().length; i++) {
                        String stationKey = keys+"$"+rentHouseDoQuery.getSubwayStationId()[i];
                        if (StringTool.isNotEmpty(rentDetailsFewDo.getNearbySubway().get(stationKey))) {
                            String stationValue = rentDetailsFewDo.getNearbySubway().get(stationKey);
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance,stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    rentDetailsFewDo.setSubwayDistanceInfo(rentDetailsFewDo.getNearbySubway().get(map.get(minDistance)));
                }
                //设置标题图
                //设置标题图
                String titlePhoto = rentDetailsFewDo.getHouseTitleImg();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                rentDetailsFewDo.setHouseTitleImg(titlePhoto);
                //设置公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    rentDetailsFewDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                rentDetailsFewDos.add(rentDetailsFewDo);

            }
        }
        rentDetailsListDo.setRentDetailsList(rentDetailsFewDos);
        rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return rentDetailsListDo;
    }

    @Override
    public int isDefaultImage(String importTime, Date today, String image) {
        if (StringTool.isEmpty(image)) {
            if (StringTool.isNotEmpty(importTime)) {
                int betweenDays = DateUtil.daysBetween(today, DateUtil.parseV1(importTime));
                if (betweenDays <= 3) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
        return 0;
    }


    public BoolQueryBuilder getRecommendRentBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder, RentHouseDoQuery rentHouseDoQuery) {

        //关键字
        if (StringTool.isNotEmpty(rentHouseDoQuery.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("zufang_nickname", rentHouseDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //附近
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance())&&rentHouseDoQuery.getDistance()!=0) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                    .point(rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon())
                    .distance(rentHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
        }

        //城市
        if (StringTool.isNotEmpty(rentHouseDoQuery.getCityId()) && rentHouseDoQuery.getCityId()!=0) {
            boolQueryBuilder.must(termQuery("city_id", rentHouseDoQuery.getCityId()));
        }
        //区域
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistrictId()) && rentHouseDoQuery.getDistrictId()!=0) {
            boolQueryBuilder.must(termQuery("district_id", rentHouseDoQuery.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(rentHouseDoQuery.getAreaId()) && rentHouseDoQuery.getAreaId().length !=0) {
            boolQueryBuilder.must(termsQuery("area_id", rentHouseDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayLineId()) && rentHouseDoQuery.getSubwayLineId() != 0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseDoQuery.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayStationId()) && rentHouseDoQuery.getSubwayStationId().length!=0) {
            boolQueryBuilder.must(termsQuery("subway_station_id", rentHouseDoQuery.getSubwayStationId()));
        }
        //租金
        if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentHouseDoQuery.getBeginPrice()).lte(rentHouseDoQuery.getEndPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentHouseDoQuery.getBeginPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() == 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").lte(rentHouseDoQuery.getEndPrice()));
        }

        //来源
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSource())) {
            String[] source = rentHouseDoQuery.getSource().split(",");
            boolQueryBuilder.must(termsQuery("data_source_sign", source));
        }
        //朝向
        if (StringTool.isNotEmpty(rentHouseDoQuery.getForwardId())) {
            Integer[] forword = rentHouseDoQuery.getForwardId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }
        //面积
//        if (StringTool.isNotEmpty(rentHouseDoQuery。)){
//            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            String area = rentHouseDoQuery.getRentHouseArea().replaceAll("\\[","")
//                    .replaceAll("]","").replaceAll("-",",");
//            String[] layoutId = area.split(",");
//            for (int i = 0; i < layoutId.length; i = i + 2) {
//                if (i + 1 > layoutId.length) {
//                    break;
//                }
//                booleanQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
//                boolQueryBuilder.must(booleanQueryBuilder);
//            }
//        }
        if (rentHouseDoQuery.getBeginArea() != 0 && rentHouseDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()).lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getBeginArea() && 0 != rentHouseDoQuery.getEndArea()) {

            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getEndArea() && 0 != rentHouseDoQuery.getBeginArea()) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()));

        }
        //整租/合租
        if (StringTool.isNotEmpty(rentHouseDoQuery.getRentType())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", rentHouseDoQuery.getRentType()));
        }
//        //几居
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getLayoutId())){
//            Integer[] split = rentHouseDoQuery.getLayoutId();
//            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
//        }


        //户型
        if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && !StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {

            if (rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("erent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getElo().split(",");
                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("erent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("erent_layout", room));
                }
            }

        } else if (!StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            if (rentHouseDoQuery.getJlo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("jrent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getJlo().split(",");

                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("jrent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("jrent_layout", room));
                }
            }

        } else if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            if (rentHouseDoQuery.getJlo().equals("0") && rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (rentHouseDoQuery.getElo().equals("0") && !rentHouseDoQuery.getJlo().equals("0")) {
                String[] jroom = rentHouseDoQuery.getJlo().split(",");
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));

                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (!rentHouseDoQuery.getElo().equals("0") && rentHouseDoQuery.getJlo().equals("0")) {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));

                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                String[] jroom = rentHouseDoQuery.getJlo().split(",");

                //String[] roommore = new String[]{"4","5","6","7","8","9","10","11","12","13","14"};
                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            }

        }

        //标签
        if (StringTool.isNotEmpty(rentHouseDoQuery.getLabelId())) {
            Integer[] split = rentHouseDoQuery.getLabelId();
            BoolQueryBuilder booleanQueryBuilder = boolQuery();
            boolean has_subway = Arrays.asList(split).contains(0);

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(0)) {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", 1));
                } else {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("rent_house_tags_id", split[i]));
                }
            }
            boolQueryBuilder.must(booleanQueryBuilder);

        /*    if(has_subway){
                Integer[] tagOther = new Integer[split.length-1];
                int idx = 0;
                for(int i=0;i<split.length;i++){
                    if(split[i].equals(0)){
                        booleanQueryBuilder.should(QueryBuilders.termQuery("has_subway", 1));
                    }else {
                        tagOther[idx++] = split[i];
                    }
                }
                if(tagOther.length!=0){
                    booleanQueryBuilder.should(QueryBuilders.termsQuery("rent_house_tags_id", tagOther));
                }
                boolQueryBuilder.must(booleanQueryBuilder);
            }else{
                boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", split));
            }*/
        }

        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        return boolQueryBuilder;
    }

    public BoolQueryBuilder getCommuteRecommendRentBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder, RentHouseDoQuery rentHouseDoQuery) {

        //关键字
        if (StringTool.isNotEmpty(rentHouseDoQuery.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("zufang_nickname", rentHouseDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //附近
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance())&&rentHouseDoQuery.getDistance()>0) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                    .point(rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon())
                    .distance(rentHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
        }

        //通勤找房
        if (StringTool.isNotEmpty(rentHouseDoQuery.getLat())&&StringTool.isNotEmpty(rentHouseDoQuery.getTrafficType())&&StringTool.isNotEmpty(rentHouseDoQuery.getTime())){
            String time = rentHouseDoQuery.getTime();
            String trafficType = rentHouseDoQuery.getTrafficType();
            Double distance = 0.0;
            if(StringTool.isNotEmpty(trafficType)&&"0".equals(trafficType)){
                distance = Integer.valueOf(time)* 60 * 1.2 /1000;
            }
            if(StringTool.isNotEmpty(trafficType)&&"1".equals(trafficType)){
                distance = Integer.valueOf(time)* 60 * 3.0/1000;
            }
            if(StringTool.isNotEmpty(trafficType)&&"2".equals(trafficType)){
                distance = Integer.valueOf(time) * 300.0/1000;
            }
            if(StringTool.isNotEmpty(trafficType)&&"3".equals(trafficType)){
                distance = Integer.valueOf(time) * 600.0/1000;
            }

            if (distance>0){
                GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                        .point(rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon())
                        .distance(distance, DistanceUnit.KILOMETERS);
                boolQueryBuilder.must(location);
            }
        }

        //城市
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getCityId()) && ) {
//            boolQueryBuilder.must(termQuery("city_id", rentHouseDoQuery.getCityId()));
//        }
        //区域
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistrictId()) && rentHouseDoQuery.getDistrictId()!=0) {
            boolQueryBuilder.must(termQuery("district_id", rentHouseDoQuery.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(rentHouseDoQuery.getAreaId()) && rentHouseDoQuery.getAreaId().length!=0) {
            boolQueryBuilder.must(termsQuery("area_id", rentHouseDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayLineId()) && rentHouseDoQuery.getSubwayLineId()!=0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseDoQuery.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayStationId()) && rentHouseDoQuery.getSubwayStationId().length!=0) {
            boolQueryBuilder.must(termsQuery("subway_station_id", rentHouseDoQuery.getSubwayStationId()));
        }
        //租金
        if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentHouseDoQuery.getBeginPrice()).lte(rentHouseDoQuery.getEndPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentHouseDoQuery.getBeginPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() == 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").lte(rentHouseDoQuery.getEndPrice()));
        }

        //来源
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSource())) {
            String[] source = rentHouseDoQuery.getSource().split(",");
            boolQueryBuilder.must(termsQuery("data_source_sign", source));
        }
        //朝向
        if (StringTool.isNotEmpty(rentHouseDoQuery.getForwardId()) && rentHouseDoQuery.getForwardId().length!=0) {
            Integer[] forword = rentHouseDoQuery.getForwardId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }
        //面积
//        if (StringTool.isNotEmpty(rentHouseDoQuery。)){
//            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            String area = rentHouseDoQuery.getRentHouseArea().replaceAll("\\[","")
//                    .replaceAll("]","").replaceAll("-",",");
//            String[] layoutId = area.split(",");
//            for (int i = 0; i < layoutId.length; i = i + 2) {
//                if (i + 1 > layoutId.length) {
//                    break;
//                }
//                booleanQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
//                boolQueryBuilder.must(booleanQueryBuilder);
//            }
//        }
        if (rentHouseDoQuery.getBeginArea() != 0 && rentHouseDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()).lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getBeginArea() && 0 != rentHouseDoQuery.getEndArea()) {

            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getEndArea() && 0 != rentHouseDoQuery.getBeginArea()) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()));

        }
//        //整租/合租
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getRentType())){
//            String[] split = rentHouseDoQuery.getRentType().split(",");
//            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
//        }
//        //几居
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getLayoutId())){
//            Integer[] split = rentHouseDoQuery.getLayoutId();
//            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
//        }


        //户型
        if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && !StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {

            if (rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("erent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getElo().split(",");
                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("erent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("erent_layout", room));
                }
            }

        } else if (!StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            if (rentHouseDoQuery.getJlo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("jrent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getJlo().split(",");

                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("jrent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("jrent_layout", room));
                }
            }

        } else if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            if (rentHouseDoQuery.getJlo().equals("0") && rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (rentHouseDoQuery.getElo().equals("0") && !rentHouseDoQuery.getJlo().equals("0")) {
                String[] jroom = rentHouseDoQuery.getJlo().split(",");
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));

                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (!rentHouseDoQuery.getElo().equals("0") && rentHouseDoQuery.getJlo().equals("0")) {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));

                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                String[] jroom = rentHouseDoQuery.getJlo().split(",");

                //String[] roommore = new String[]{"4","5","6","7","8","9","10","11","12","13","14"};
                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            }

        }

        //标签
        if (StringTool.isNotEmpty(rentHouseDoQuery.getLabelId()) && rentHouseDoQuery.getLabelId().length!=0) {
            Integer[] split = rentHouseDoQuery.getLabelId();
            BoolQueryBuilder booleanQueryBuilder = boolQuery();
            boolean has_subway = Arrays.asList(split).contains(0);

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(0)) {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", 1));
                } else {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("rent_house_tags_id", split[i]));
                }
            }
            boolQueryBuilder.must(booleanQueryBuilder);

        /*    if(has_subway){
                Integer[] tagOther = new Integer[split.length-1];
                int idx = 0;
                for(int i=0;i<split.length;i++){
                    if(split[i].equals(0)){
                        booleanQueryBuilder.should(QueryBuilders.termQuery("has_subway", 1));
                    }else {
                        tagOther[idx++] = split[i];
                    }
                }
                if(tagOther.length!=0){
                    booleanQueryBuilder.should(QueryBuilders.termsQuery("rent_house_tags_id", tagOther));
                }
                boolQueryBuilder.must(booleanQueryBuilder);
            }else{
                boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", split));
            }*/
        }

        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        return boolQueryBuilder;
    }

    /**
     * 获取boolQueryBuilder
     *
     * @param boolQueryBuilder
     * @param nearHouseDo
     * @return
     */
    public BoolQueryBuilder getBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder, NearHouseDo nearHouseDo) {

        //关键字
        if (StringTool.isNotEmpty(nearHouseDo.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearHouseDo.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart"));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(nearHouseDo.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //城市
        if (StringTool.isNotEmpty(nearHouseDo.getCityId()) && nearHouseDo.getCityId()>0) {
            boolQueryBuilder.must(termQuery("city_id", nearHouseDo.getCityId()));
        }
        //区域
        if (StringTool.isNotEmpty(nearHouseDo.getDistrictId()) && nearHouseDo.getDistrictId() > 0) {
            boolQueryBuilder.must(termQuery("district_id", nearHouseDo.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(nearHouseDo.getAreaId()) && nearHouseDo.getAreaId()>0) {
            boolQueryBuilder.must(termQuery("area_id", nearHouseDo.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(nearHouseDo.getSubwayLineId())&& nearHouseDo.getSubwayLineId()>0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{nearHouseDo.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(nearHouseDo.getSubwayStationId())&& nearHouseDo.getSubwayStationId()>0) {
            boolQueryBuilder.must(termsQuery("subway_station_id", new int[]{nearHouseDo.getSubwayStationId()}));
        }
        //租金
        if (StringTool.isNotEmpty(nearHouseDo.getBeginPrice()) && StringTool.isNotEmpty(nearHouseDo.getEndPrice()) && nearHouseDo.getBeginPrice()>0 && nearHouseDo.getEndPrice()>0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(nearHouseDo.getBeginPrice()).lte(nearHouseDo.getEndPrice()));
        } else if (StringTool.isNotEmpty(nearHouseDo.getBeginPrice()) && StringTool.isNotEmpty(nearHouseDo.getEndPrice()) && nearHouseDo.getBeginPrice()>0 && nearHouseDo.getEndPrice()==0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(nearHouseDo.getBeginPrice()));
        }
        else if (StringTool.isNotEmpty(nearHouseDo.getBeginPrice()) && StringTool.isNotEmpty(nearHouseDo.getEndPrice()) && nearHouseDo.getBeginPrice() == 0 && nearHouseDo.getEndPrice() > 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .lte(nearHouseDo.getEndPrice()));
        }
        //面积
        if (StringTool.isNotEmpty(nearHouseDo.getRentHouseArea())) {
            String area = nearHouseDo.getRentHouseArea().replaceAll("\\[", "")
                    .replaceAll("]", "").replaceAll("-", ",");
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                booleanQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
                boolQueryBuilder.must(booleanQueryBuilder);
            }
        }
        //来源
        if (StringTool.isNotEmpty(nearHouseDo.getSource())) {
            String[] source = nearHouseDo.getSource().split(",");
            boolQueryBuilder.must(termsQuery("data_source_sign", source));
        }
        //朝向
        if (StringTool.isNotEmpty(nearHouseDo.getForward())) {
            String[] forword = nearHouseDo.getForward().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }
        //整租/合租
        if (StringTool.isNotEmpty(nearHouseDo.getRentType())) {
            String[] split = nearHouseDo.getRentType().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
        }
        //几居
        if (StringTool.isNotEmpty(nearHouseDo.getRoom())) {
            String[] split = nearHouseDo.getRoom().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
        }
        //标签
        if (StringTool.isNotEmpty(nearHouseDo.getTags())) {
            String[] split = nearHouseDo.getTags().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", split));
        }
//        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",nearHouseDo.getRentHouseType()));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        return boolQueryBuilder;
    }

    /**
     * 增加弹幕信息
     * @param rentDetailsFewDo
     */
    private void fullHouseBarrage(RentDetailsFewDo rentDetailsFewDo) {
        //二手房弹幕第一行
        List<String> houseBarrageFirstList = new ArrayList<>();
        if(StringTool.isNotEmpty(rentDetailsFewDo.getHouseTitle())){
            houseBarrageFirstList.add(rentDetailsFewDo.getHouseTitle());
        }
        if(StringTool.isNotEmpty(rentDetailsFewDo.getRentTypeName())){
            houseBarrageFirstList.add(rentDetailsFewDo.getRentTypeName());
        }
        rentDetailsFewDo.setHouseBarrageFirstList(houseBarrageFirstList);

        //二手房弹幕第二行
        List<String> houseBarrageSecondList = new ArrayList<>();
        if(StringTool.isNotEmpty(rentDetailsFewDo.getNearestSubway())){
            String[] trafficArr = rentDetailsFewDo.getNearestSubway().split("\\$");
            if (trafficArr.length == 3) {
                String  nearbyDistance = "距" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                houseBarrageSecondList.add(nearbyDistance);
            }
        }
        if(rentDetailsFewDo.getForward().contains("东") || rentDetailsFewDo.getForward().contains("南")){
            houseBarrageSecondList.add("采光很好");
        }
        for (String tag : rentDetailsFewDo.getRentHouseTagsName()){
            houseBarrageSecondList.add(tag);
        }
        rentDetailsFewDo.setHouseBarrageSecondList(houseBarrageSecondList);

    }
}
