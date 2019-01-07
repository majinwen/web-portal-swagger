package com.toutiao.app.service.mapSearch.impl;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.naming.pcosnaming.PersistentBindingIterator;
import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.constant.house.HouseLableEnum;
import com.toutiao.web.common.constant.map.MapGroupConstant;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.mapSearch.MapGroupUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.lucene.index.Term;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName EsfMapSearchRestServiceImpl
 * @Author jiangweilong
 * @Date 2018/11/23 3:33 PM
 * @Description:
 **/

@Service
public class EsfMapSearchRestServiceImpl implements EsfMapSearchRestService {

    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;
    @Autowired
    private CommunityRestService communityRestService;
    @Autowired
    private SellHouseService sellHouseService;
    @Autowired
    private AgentService agentService;

    @Autowired
    private EsfMapSearchEsDao esfMapSearchEsDao;
    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private PlotEsDao plotEsDao;


    /**
     * 地图找房-二手房查询
     * @param esfMapSearchDoQuery
     * @return
     */
    @Override
    public EsfMapSearchDomain esfMapSearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {


        EsfMapSearchDomain esfMapSearchDomain = new EsfMapSearchDomain();

        //判断当前地图查询级别
        Integer groupTypeId = MapGroupUtil.returnMapGrouId(esfMapSearchDoQuery.getGroupType());
        //groupTypeId 1：区县，2：商圈，3：社区
        if (groupTypeId.equals(MapGroupConstant.DISTRICT_CODE)) {

            esfMapSearchDomain = esfMapSearchByDistrict(esfMapSearchDoQuery, city);
            return esfMapSearchDomain;
        }

        if (groupTypeId.equals(MapGroupConstant.BIZCIRCLE_CODE)) {

            esfMapSearchDomain = esfMapSearchByBizcircle(esfMapSearchDoQuery, city);
            return esfMapSearchDomain;
        }

        if (groupTypeId.equals(MapGroupConstant.COMMUNITY_CODE)) {

            esfMapSearchDomain = esfMapSearchByCommunity(esfMapSearchDoQuery, city);
            return esfMapSearchDomain;

        }
        //地图附近
        if (null!=esfMapSearchDoQuery.getLat() && null!=esfMapSearchDoQuery.getLon() && null!=esfMapSearchDoQuery.getDistance()) {
            esfMapSearchDomain = esfMapSearchByNear(esfMapSearchDoQuery, city);
            return esfMapSearchDomain;
        }



        return esfMapSearchDomain;
    }

    /**
     * 按照区县搜索二手房
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapSearchDomain esfMapSearchByDistrict(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {

        EsfMapSearchDomain esfMapSearchDistrictDomain = new EsfMapSearchDomain();
        NumberFormat nf = NumberFormat.getNumberInstance();
        List<EsfMapSearchDo> data = new ArrayList<>();
        nf.setMaximumFractionDigits(1);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        nf.setRoundingMode(RoundingMode.UP);
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);

        long esfCount = searchSellHouse.getHits().totalHits;
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(), esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(), esfMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("district_location").setCornersOGC(bottomLeft, topRight);
        builder.must(geoBoundingBoxQueryBuilder);
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByDistrict(builder, city);
        //long searchCount = searchResponse.getHits().totalHits;
        Terms terms = searchResponse.getAggregations().get("houseCount");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchDistrictDo = new EsfMapSearchDo();
            esfMapSearchDistrictDo.setId(Integer.valueOf(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString()));//区县id
           // esfMapSearchDistrictDo.setCount((int)((ParsedStringTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms districtName = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtName");
            if (districtName.getBuckets().size() > 0) {
                esfMapSearchDistrictDo.setName(districtName.getBuckets().get(0).getKeyAsString());//区县名称
            }

            Terms districtAvgPrice = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtAvgPrice");
            if (districtAvgPrice.getBuckets().size() > 0) {
                esfMapSearchDistrictDo.setPrice(districtAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
            }

            Terms districtLatitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtLatitude");
            if (districtLatitude.getBuckets().size() > 0) {
                esfMapSearchDistrictDo.setLatitude(districtLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
            }

            Terms districtLongitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtLongitude");
            if (districtLongitude.getBuckets().size() > 0) {
                esfMapSearchDistrictDo.setLongitude(districtLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
            }

            String desc = 0 + "万("+esfMapSearchDistrictDo.getCount()+"套)";
            if (null != esfMapSearchDistrictDo.getPrice()){
                desc = nf.format(esfMapSearchDistrictDo.getPrice()/10000)+"万/m²";
            }
            esfMapSearchDistrictDo.setDesc(desc);
            data.add(esfMapSearchDistrictDo);
        }
        esfMapSearchDistrictDomain.setData(data);
        SearchResponse responseHouseCount = esfMapSearchEsDao.esfMapSearchByCommunity(boolQueryBuilder, city);
        long searchHouseCount = responseHouseCount.getHits().totalHits;
        esfMapSearchDistrictDomain.setHit("可视范围内"+searchHouseCount+"套房源，共"+esfCount+"套房源");
        return esfMapSearchDistrictDomain;
    }

    /**
     * 按照商圈搜索二手房
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapSearchDomain esfMapSearchByBizcircle(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfMapSearchDomain esfMapSearchBizcircleDomain = new EsfMapSearchDomain();
        NumberFormat nf = NumberFormat.getNumberInstance();
        List<EsfMapSearchDo> data = new ArrayList<>();
        nf.setMaximumFractionDigits(1);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        nf.setRoundingMode(RoundingMode.UP);

        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
        long esfCount = searchSellHouse.getHits().totalHits;



        BoolQueryBuilder aggBuilder = QueryBuilders.boolQuery();
        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(),esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(),esfMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("bizcircle_location").setCornersOGC(bottomLeft, topRight);
        aggBuilder.must(geoBoundingBoxQueryBuilder);
        if(StringTool.isNotEmpty((sellHouseDoQuery.getDistrictId())) && sellHouseDoQuery.getDistrictId() != 0){
            aggBuilder.must(QueryBuilders.termQuery("district_id", sellHouseDoQuery.getDistrictId()));
        }
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByBizcircle(aggBuilder, city);
        //long searchCount = searchResponse.getHits().totalHits;
        Terms houseCount = searchResponse.getAggregations().get("houseCount");
        List buckets= houseCount.getBuckets();
        for (Object bucket : buckets){
            EsfMapSearchDo esfMapSearchBizcircleDo = new EsfMapSearchDo();
            esfMapSearchBizcircleDo.setId(Integer.valueOf(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString()));//商圈id
            //esfMapSearchBizcircleDo.setCount((int) ((ParsedStringTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms bizcircleName = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleName");
            if (bizcircleName.getBuckets().size() > 0) {
                esfMapSearchBizcircleDo.setName(bizcircleName.getBuckets().get(0).getKeyAsString());//商圈名称
            }

            Terms bizcircleAvgprice = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleAvgprice");
            if (bizcircleAvgprice.getBuckets().size() > 0) {
                esfMapSearchBizcircleDo.setPrice(bizcircleAvgprice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
            }

            Terms bizcircleLatitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleLatitude");
            if (bizcircleLatitude.getBuckets().size() > 0) {
                esfMapSearchBizcircleDo.setLatitude(bizcircleLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
            }

            Terms bizcircleLongitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleLongitude");
            if (bizcircleLongitude.getBuckets().size() > 0) {
                esfMapSearchBizcircleDo.setLongitude(bizcircleLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
            }

            String desc = 0 + "万("+esfMapSearchBizcircleDo.getCount()+"套)";
            if (null != esfMapSearchBizcircleDo.getPrice()){
                desc = nf.format(esfMapSearchBizcircleDo.getPrice()/10000)+"万/m²";
            }
            esfMapSearchBizcircleDo.setDesc(desc);
            data.add(esfMapSearchBizcircleDo);
        }
        esfMapSearchBizcircleDomain.setData(data);
        SearchResponse responseHouseCount = esfMapSearchEsDao.esfMapSearchByCommunity(boolQueryBuilder, city);
        long searchHouseCount = responseHouseCount.getHits().totalHits;
        esfMapSearchBizcircleDomain.setHit("可视范围内"+searchHouseCount+"套房源，共"+esfCount+"套房源");
        return esfMapSearchBizcircleDomain;
    }

    /**
     * 按照社区搜索二手房
     * @param esfMapSearchDoQuery
     * @return
     */
    public EsfMapSearchDomain esfMapSearchByCommunity(EsfMapSearchDoQuery esfMapSearchDoQuery, String city){

        EsfMapSearchDomain esfMapSearchCommunityDomain = new EsfMapSearchDomain();
        NumberFormat nf = NumberFormat.getNumberInstance();
        List<EsfMapSearchDo> data = new ArrayList<>();
        nf.setMaximumFractionDigits(1);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        nf.setRoundingMode(RoundingMode.UP);

        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder,city);
        long esfCount = searchSellHouse.getHits().totalHits;
        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(),esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(),esfMapSearchDoQuery.getMinLongitude());

        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("housePlotLocation").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);

        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByCommunity(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms houseCount = searchResponse.getAggregations().get("houseCount");
        List buckets= houseCount.getBuckets();
        for (Object bucket : buckets){
            EsfMapSearchDo esfMapSearchCommunityDo = new EsfMapSearchDo();
            esfMapSearchCommunityDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//社区id
            esfMapSearchCommunityDo.setCount((int) ((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms communityName = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityName");
            if (communityName.getBuckets().size() > 0) {
                esfMapSearchCommunityDo.setName(communityName.getBuckets().get(0).getKeyAsString());//社区名称
            }
            Terms communityAvgPrice = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityAvgPrice");
            if (communityAvgPrice.getBuckets().size() > 0) {
                esfMapSearchCommunityDo.setPrice(communityAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
            }
            Terms plotLongitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLongitude");
            if (plotLongitude.getBuckets().size() > 0) {
                esfMapSearchCommunityDo.setLongitude(plotLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
            }
            Terms plotLatitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLatitude");
            if (plotLatitude.getBuckets().size() > 0) {
                esfMapSearchCommunityDo.setLatitude(plotLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
            }

            String desc = 0 + "万("+esfMapSearchCommunityDo.getCount()+"套)";
            if (null != esfMapSearchCommunityDo.getPrice()){
                desc = nf.format(esfMapSearchCommunityDo.getPrice()/10000)+"万/m²("+esfMapSearchCommunityDo.getCount()+"套)";
            }
            esfMapSearchCommunityDo.setDesc(desc);
            data.add(esfMapSearchCommunityDo);
        }
        esfMapSearchCommunityDomain.setData(data);
        esfMapSearchCommunityDomain.setHit("可视范围内"+searchCount+"套房源，共"+esfCount+"套房源");
        return esfMapSearchCommunityDomain;
    }

    /**
     * 小区房源列表
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfHouseListDomain esfMapSearchHouseList(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfHouseListDomain esfHouseListDomain = new EsfHouseListDomain();
        EsfMapCommunityDo esfMapCommunityDo = new EsfMapCommunityDo();
        EsfMapHouseDo esfMapHouseDo = new EsfMapHouseDo();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);

        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //小区id
       // BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termsQuery("newcode", esfMapSearchDoQuery.getNewCode()));

        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));

        GeoDistanceSortBuilder geoDistanceSort = null;
        if((StringTool.isNotEmpty(esfMapSearchDoQuery.getDistance()) && esfMapSearchDoQuery.getDistance()!=0) &&
                StringTool.isNotEmpty(esfMapSearchDoQuery.getLat()) &&
                StringTool.isNotEmpty(esfMapSearchDoQuery.getLon())){
            geoDistanceSort = SortBuilders.geoDistanceSort("housePlotLocation", esfMapSearchDoQuery.getLat(), esfMapSearchDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
        }
//
//        //地铁线id
//        if (StringTool.isNotEmpty(esfMapSearchDoQuery.getSubwayLineId()) && esfMapSearchDoQuery.getSubwayLineId() != 0) {
//            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", esfMapSearchDoQuery.getSubwayLineId()));
//
//        }
//        //地铁站id
//        if (StringTool.isNotEmpty(esfMapSearchDoQuery.getSubwayStationId()) && esfMapSearchDoQuery.getSubwayStationId().length != 0) {
//            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", esfMapSearchDoQuery.getSubwayStationId()));
//        }



        List<EsfMapHouseDo> esfMapHouseDos = new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchHouseList(booleanQueryBuilder,geoDistanceSort,
                esfMapSearchDoQuery.getPageNum(),esfMapSearchDoQuery.getPageSize(),city, esfMapSearchDoQuery.getSort());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            SearchHit communityHit = searchHists[0];

            String districtName = communityHit.getSourceAsMap().get("area").toString();
            String bizcircleName = communityHit.getSourceAsMap().get("houseBusinessName").toString();

            esfMapCommunityDo.setCount((int)hits.totalHits);
            String description = districtName + " " +bizcircleName;
            if (StringTool.isNotEmpty(esfMapSearchDoQuery.getSubwayLineId()) && esfMapSearchDoQuery.getSubwayLineId() != 0) {
                SearchResponse line = esfMapSearchEsDao.queryLinePoint(esfMapSearchDoQuery.getSubwayLineId(), city);
                Map<String, Object> subwayMap = line.getHits().getHits()[0].getSourceAsMap();
                description = "近" + subwayMap.get("line_name");
            }
           esfMapCommunityDo.setDescription(description);

            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                esfMapHouseDo=JSON.parseObject(details,EsfMapHouseDo.class);

                String nearbyDistance = StringTool.nullToString(esfMapHouseDo.getArea()) + " " + StringTool.nullToString(esfMapHouseDo.getHouseBusinessName());
                String traffic = esfMapHouseDo.getTraffic();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }

                if(StringTool.isNotEmpty(esfMapSearchDoQuery.getDistance()) && esfMapSearchDoQuery.getDistance()!=0){
                    BigDecimal geoDis = new BigDecimal((Double) searchHit.getSortValues()[0]);
                    String distance = geoDis.setScale(1, BigDecimal.ROUND_CEILING)+DistanceUnit.KILOMETERS.toString();
                    nearbyDistance = "距您" + distance;
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = esfMapHouseDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, esfMapHouseDo.getHousePhotoTitle());
                if(isDefault==1){
                    esfMapHouseDo.setIsDefaultImage(1);
                }
                claimSellHouseDo=JSON.parseObject(details,ClaimSellHouseDo.class);
                if (null!=claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim()==1)
                {   //将认领信息替换
                    esfMapHouseDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    esfMapHouseDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    esfMapHouseDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    esfMapHouseDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = esfMapHouseDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http://")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                esfMapHouseDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(claimSellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(esfMapHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(esfMapHouseDo.getUserId().toString(),city);
                }else if(claimSellHouseDo.getIsClaim()==0){
                    if(StringUtil.isNotNullString(esfMapHouseDo.getProjExpertUserId())){
                        agentBaseDo = agentService.queryAgentInfoByUserId(esfMapHouseDo.getProjExpertUserId(),city);
                    }else{
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
                    }

                }

                esfMapHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                esfMapHouseDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    esfMapHouseDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isLowPrice = esfMapHouseDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                int isCutPrice = esfMapHouseDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = esfMapHouseDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                esfMapHouseDo.setHouseLableList(houseLableList);
                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if(esfMapHouseDo.getIsLowest() == 1){
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低","");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = esfMapHouseDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = esfMapHouseDo.getTotalAbsoluteWithCommunity();
                if(esfMapHouseDo.getIsLowPrice() == 1){
                    if(totalAbsoluteWithBizcircle< 0 && totalAbsoluteWithCommunity < 0){
                        if(Math.abs(totalAbsoluteWithBizcircle) >Math.abs(totalAbsoluteWithCommunity)){
                            lowPriceStr ="总价低于商圈同户型"+Math.abs(totalAbsoluteWithBizcircle)+"万";
                        }else{
                            lowPriceStr = "总价低于小区同户型"+Math.abs(totalAbsoluteWithCommunity)+"万";
                        }
                    }else if (totalAbsoluteWithBizcircle< 0 ){
                        lowPriceStr ="总价低于商圈同户型"+Math.abs(totalAbsoluteWithBizcircle)+"万";
                    }else if (totalAbsoluteWithCommunity< 0 ){
                        lowPriceStr = "总价低于小区同户型"+Math.abs(totalAbsoluteWithCommunity)+"万";
                    }
                }
                if(!StringUtil.isNullString(lowPriceStr)){
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = esfMapHouseDo.getPriceFloat();
                if (esfMapHouseDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = esfMapHouseDo.getAvgDealCycle();
                if (esfMapHouseDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List tagNameList = esfMapHouseDo.getRecommendBuildTagsName();
                String areaName = esfMapHouseDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = esfMapHouseDo.getTypeCounts();

                if (esfMapHouseDo.getIsMustRob() == 1 && esfMapHouseDo.getIsMainLayout() == 1) {
                    if (esfMapHouseDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (tagNameList.size() > 0 && typeCountsMap != null) {
                        if(tagNameList.contains("豪宅")){
                            communityLableStr = areaName + typeCountsMap.get(4).get(esfMapHouseDo.getAreaId()) + "大豪宅社区主力户型";
                        }else if(tagNameList.contains("别墅")){
                            communityLableStr = areaName + typeCountsMap.get(5).get(esfMapHouseDo.getAreaId()) + "大别墅社区主力户型";
                        }else if(tagNameList.contains("首次置业")){
                            communityLableStr = areaName + typeCountsMap.get(2).get(esfMapHouseDo.getAreaId()) + "大首置社区主力户型";
                        }else if(tagNameList.contains("换房升级")){
                            communityLableStr = areaName + typeCountsMap.get(3).get(esfMapHouseDo.getAreaId()) + "大换房社区主力户型";
                        }else if(tagNameList.contains("近公园")){
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if(!StringUtil.isNullString(communityLableStr)){
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = esfMapHouseDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(esfMapHouseDo.getHouseBusinessName()+esfMapHouseDo.getRoom()+"居室低总价榜NO."+rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = esfMapHouseDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }


//                double priceFloat = esfMapHouseDo.getPriceFloat();
//                if(esfMapHouseDo.getIsCutPrice() == 1 && priceFloat>0){
//                    HouseSubject sellHouseSubject = new HouseSubject();
//                    sellHouseSubject.setText("降"+priceFloat+"万");
//                    sellHouseSubject.setUrl("http://www.baidu.com");
//                    houseSubjectList.add(sellHouseSubject);
//                }
                esfMapHouseDo.setHouseSubjectList(houseSubjectList);

                esfMapHouseDo.setNearbyDistance(esfMapHouseDo.getArea() + " " + esfMapHouseDo.getHouseBusinessName());
                esfMapHouseDos.add(esfMapHouseDo);
                //增加地铁与房子之间的距离
                String keys="";
                if(null!=esfMapSearchDoQuery.getSubwayLineId())
                {
                    keys+=esfMapSearchDoQuery.getSubwayLineId().toString();
                }
                if (null!=esfMapSearchDoQuery.getSubwayStationId() && esfMapSearchDoQuery.getSubwayStationId().length!=0){
                    Map<Integer,String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    String stationKey = keys+"$"+esfMapSearchDoQuery.getSubwayStationId()[0];
                    String stationValue = esfMapHouseDo.getSubwayDistince().get(stationKey).toString();
                    String[] stationValueSplit = stationValue.split("\\$");
                    Integer distance = Integer.valueOf(stationValueSplit[2]);
                    sortDistance.add(distance);
                    map.put(distance,stationKey);

                    Integer minDistance = Collections.min(sortDistance);
                    esfMapHouseDo.setSubwayDistanceInfo(esfMapHouseDo.getSubwayDistince().get(map.get(minDistance)).toString());
                    trafficArr = esfMapHouseDo.getSubwayDistanceInfo().split("\\$");
                    if (trafficArr.length == 3) {
                        nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "m";
                    }
                }
                esfMapHouseDo.setNearbyDistance(nearbyDistance);
            }
            esfHouseListDomain.setEsfMapHouseDos(esfMapHouseDos);
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.must(QueryBuilders.termsQuery("id",esfMapSearchDoQuery.getNewCode()));
            SearchResponse response = plotEsDao.queryPlotDetail(builder, city);
            if(null!=response) {
                SearchHit[] searchHits = response.getHits().getHits();
                for (SearchHit hit:searchHits){
                    String details = hit.getSourceAsString();
                    PlotDetailsDo plotDetailsDo = JSON.parseObject(details, PlotDetailsDo.class);

                    esfMapCommunityDo.setPloatName(plotDetailsDo.getRc());
                    esfMapCommunityDo.setPlotId(plotDetailsDo.getId().toString());
                    if(StringTool.isDoubleNotEmpty(plotDetailsDo.getAvgPrice())){
                        esfMapCommunityDo.setCommunityAvgPrice(plotDetailsDo.getAvgPrice());
                    }

                    if (StringTool.isNotEmpty(plotDetailsDo.getPhoto()) && plotDetailsDo.getPhoto().length > 0) {
                        String titlePhoto = plotDetailsDo.getPhoto()[0];
                        if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                            titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                        }
                        esfMapCommunityDo.setBuildingImages(titlePhoto);
                    }
                    esfMapCommunityDo.setCityId(CityUtils.returnCityId(city));
                    esfMapCommunityDo.setBuildYears(plotDetailsDo.getAbbreviatedAge());
                    esfMapCommunityDo.setBuildingStructure(plotDetailsDo.getBuildingStructure());
                    esfMapCommunityDo.setDistrictName(plotDetailsDo.getArea());
                    esfMapCommunityDo.setAreaName(plotDetailsDo.getTradingArea());
                    esfMapCommunityDo.setTagsName(plotDetailsDo.getLabel());

                }
                esfHouseListDomain.setEsfMapCommunityDo(esfMapCommunityDo);
            }


        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }


        return esfHouseListDomain;
    }

    /**
     * 地图附近找房
     * @param esfMapSearchDoQuery
     * @return
     */
    public EsfMapSearchDomain esfMapSearchByNear(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfMapSearchDomain esfMapSearchNearDomain = new EsfMapSearchDomain();
        List<EsfMapSearchDo> data = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setRoundingMode(RoundingMode.UP);

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
//        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
//        long esfCount = searchSellHouse.getHits().totalHits;

        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByNear(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms terms = searchResponse.getAggregations().get("houseCount");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchNearDo = new EsfMapSearchDo();
            esfMapSearchNearDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//社区id
            esfMapSearchNearDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms communityName = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityName");
            if (communityName.getBuckets().size() > 0) {
                esfMapSearchNearDo.setName(communityName.getBuckets().get(0).getKeyAsString());//社区名称
            }

            Terms communityAvgPrice = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityAvgPrice");
            if (communityAvgPrice.getBuckets().size() > 0) {
                esfMapSearchNearDo.setPrice(communityAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
            }

            Terms plotLatitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLatitude");
            if (plotLatitude.getBuckets().size() > 0) {
                esfMapSearchNearDo.setLatitude(plotLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
            }

            Terms plotLongitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLongitude");
            if (plotLongitude.getBuckets().size() > 0) {
                esfMapSearchNearDo.setLongitude(plotLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
            }

            String desc = 0 + "万("+esfMapSearchNearDo.getCount()+"套)";
            if (null != esfMapSearchNearDo.getPrice()){
                desc = nf.format(esfMapSearchNearDo.getPrice()/10000)+"万("+esfMapSearchNearDo.getCount()+"套)";
            }
            esfMapSearchNearDo.setDesc(desc);
            data.add(esfMapSearchNearDo);
        }
        esfMapSearchNearDomain.setData(data);
        esfMapSearchNearDomain.setHit("共"+searchCount+"套房源");
        return esfMapSearchNearDomain;
    }

    /**
     * 地铁找房
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapStationDomain esfMapSubwaySearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {

        EsfMapStationDomain esfMapStationDomain = new EsfMapStationDomain();
        //地铁线
//        if (null!=esfMapSearchDoQuery.getSubwayLineId() && null==esfMapSearchDoQuery.getSubwayStationId()) {
//
//            esfMapStationDomain = esfMapSearchByLine(esfMapSearchDoQuery, city);
//            return esfMapStationDomain;
//        }
        if(StringTool.isNotEmpty(esfMapSearchDoQuery.getSubwayLineId()) && StringTool.isEmpty(esfMapSearchDoQuery.getSubwayStationId())){
            esfMapStationDomain = esfMapSearchByLine(esfMapSearchDoQuery, city);
            return esfMapStationDomain;
        }

        //地铁站
        if (StringTool.isNotEmpty(esfMapSearchDoQuery.getSubwayStationId())) {

            esfMapStationDomain = esfMapSearchByStation(esfMapSearchDoQuery, city);
        }
        return esfMapStationDomain;
    }

    /**
     * 地铁找房（地铁线）
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapStationDomain esfMapSearchByLine(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfMapStationDomain esfMapSearchSubwayDomain = new EsfMapStationDomain();
        List<EsfMapSearchDo> data = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setRoundingMode(RoundingMode.UP);

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
//        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
//        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
//        long esfCount = searchSellHouse.getHits().totalHits;

        if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayLineId()) && sellHouseDoQuery.getSubwayLineId() != 0) {
            boolQueryBuilder.must(QueryBuilders.termQuery("line_id", sellHouseDoQuery.getSubwayLineId()));

        }
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchBySubway(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        BoolQueryBuilder builder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        SearchResponse response = sellHouseEsDao.querySellHouse(builder, city);
        Terms terms = searchResponse.getAggregations().get("sorting");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchSubwayDo = new EsfMapSearchDo();
            //esfMapSearchSubwayDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//地铁站id
            //esfMapSearchSubwayDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms stationId = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("stationId");
            if (stationId.getBuckets().size() > 0) {
                esfMapSearchSubwayDo.setId(stationId.getBuckets().get(0).getKeyAsNumber().intValue());//地铁站名称
            }

            Terms stationName = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("stationName");
            if (stationName.getBuckets().size() > 0) {
                esfMapSearchSubwayDo.setName(stationName.getBuckets().get(0).getKeyAsString());//地铁站名称
            }

            Terms price = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("price");
            if (price.getBuckets().size() > 0) {
                esfMapSearchSubwayDo.setPrice(price.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
            }

            Terms latitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("latitude");
            if (latitude.getBuckets().size() > 0) {
                esfMapSearchSubwayDo.setLatitude(latitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
            }

            Terms longitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("longitude");
            if (longitude.getBuckets().size() > 0) {
                esfMapSearchSubwayDo.setLongitude(longitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
            }

            String desc = nf.format(esfMapSearchSubwayDo.getPrice()/10000)+"万";//描述

            esfMapSearchSubwayDo.setDesc(desc);
            data.add(esfMapSearchSubwayDo);
        }
        esfMapSearchSubwayDomain.setStationData(data);
        esfMapSearchSubwayDomain.setHit("共" + response.getHits().totalHits + "套房源");

        return esfMapSearchSubwayDomain;
    }

    /**
     * 地铁找房（地铁站）
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapStationDomain esfMapSearchByStation(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfMapStationDomain esfMapSearchStationDomain = new EsfMapStationDomain();
        List<EsfMapStationDo> esfMapStationDos = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setRoundingMode(RoundingMode.UP);

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
//        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
//        long esfCount = searchSellHouse.getHits().totalHits;
        EsfMapStationDomain subwayLine = esfMapSearchByLine(esfMapSearchDoQuery, city);
        esfMapSearchStationDomain.setStationData(subwayLine.getStationData());
        long searchCount = 0;
        for (int i=0; i<esfMapSearchDoQuery.getSubwayStationId().length; i++) {
            SearchResponse stationRes = esfMapSearchEsDao.queryStationPoint(esfMapSearchDoQuery.getSubwayStationId()[i], city);
            SearchHits searchHits = stationRes.getHits();
            SearchHit[] hits = searchHits.getHits();
            Map<String, Object> stationMap = new HashMap<>();
            if (hits.length > 0) {
                SearchHit stationHit = hits[0];
                stationMap = stationHit.getSourceAsMap();
            }
            EsfMapStationDo esfMapStationDo = new EsfMapStationDo();
            esfMapStationDo.setStationId(esfMapSearchDoQuery.getSubwayStationId()[i]);
            List<EsfMapSearchDo> esfMapSearchDos = new ArrayList<>();
            if (stationMap.size() != 0){
                esfMapStationDo.setStationName(stationMap.get("station_name").toString());
                GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("housePlotLocation")
                        .point(Double.valueOf(stationMap.get("latitude").toString()), Double.valueOf(stationMap.get("longitude").toString()))
                        .distance(3, DistanceUnit.KILOMETERS);
                boolQueryBuilder.must(geoDistanceQueryBuilder);
                SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByNear(boolQueryBuilder, city);
                Terms terms = searchResponse.getAggregations().get("houseCount");
                List buckets = terms.getBuckets();
                for (Object bucket : buckets) {
                    EsfMapSearchDo esfMapSearchStationDo = new EsfMapSearchDo();
                    esfMapSearchStationDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//小区id
                    esfMapSearchStationDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

                    Terms communityName = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityName");
                    if (communityName.getBuckets().size() > 0) {
                        esfMapSearchStationDo.setName(communityName.getBuckets().get(0).getKeyAsString());//小区名称
                    }

                    Terms communityAvgPrice = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityAvgPrice");
                    if (communityAvgPrice.getBuckets().size() > 0) {
                        esfMapSearchStationDo.setPrice(communityAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价
                    }

                    Terms plotLatitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLatitude");
                    if (plotLatitude.getBuckets().size() > 0) {
                        esfMapSearchStationDo.setLatitude(plotLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度
                    }

                    Terms plotLongitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLongitude");
                    if (plotLongitude.getBuckets().size() > 0) {
                        esfMapSearchStationDo.setLongitude(plotLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度
                    }

                    String desc = 0 + "万("+esfMapSearchStationDo.getCount()+"套)";
                    if (null != esfMapSearchStationDo.getPrice()){
                        desc = nf.format(esfMapSearchStationDo.getPrice()/10000)+"万("+esfMapSearchStationDo.getCount()+"套)";
                    }
                    esfMapSearchStationDo.setDesc(desc);
                    esfMapSearchDos.add(esfMapSearchStationDo);
                }
            }
            esfMapStationDo.setData(esfMapSearchDos);
            esfMapStationDos.add(esfMapStationDo);
            searchCount += esfMapSearchDos.size();
        }
        esfMapSearchStationDomain.setCommunityData(esfMapStationDos);
        esfMapSearchStationDomain.setHit("共" + searchCount + "套房源");
        return esfMapSearchStationDomain;
    }

    /**
     * 画圈找房房源列表
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfCircleListDomain esfMapDrawCircleList(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfCircleListDomain esfCircleListDomain = new EsfCircleListDomain();
        List<EsfCircleListDo> esfCircleListDos = new ArrayList<>();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);

        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //小区id
        Integer[] newcode = esfMapSearchDoQuery.getNewCode();
        booleanQueryBuilder.must(QueryBuilders.termsQuery("newcode", newcode));

        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));

        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

        FunctionScoreQueryBuilder queryKmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);

        query = QueryBuilders.functionScoreQuery(queryKmBuilder).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchHouseDrawCircleList(query,sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize(), city, sellHouseDoQuery.getSort());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        Date date = new Date();
        if(searchHits.length > 0){
            for (int i=0; i<searchHits.length; i++) {
                EsfCircleListDo esfCircleListDo = new EsfCircleListDo();
                EsfMapHouseDo esfMapHouseDo = new EsfMapHouseDo();
                String details=searchHits[i].getSourceAsString();
                esfMapHouseDo=JSON.parseObject(details,EsfMapHouseDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = esfMapHouseDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, esfMapHouseDo.getHousePhotoTitle());
                if(isDefault==1){
                    esfMapHouseDo.setIsDefaultImage(1);
                }
                claimSellHouseDo=JSON.parseObject(details,ClaimSellHouseDo.class);
                if (null!=claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim()==1)
                {   //将认领信息替换
                    esfMapHouseDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    esfMapHouseDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    esfMapHouseDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    esfMapHouseDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = esfMapHouseDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http://")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                esfMapHouseDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(claimSellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(esfMapHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(esfMapHouseDo.getUserId().toString(),city);
                }else if(claimSellHouseDo.getIsClaim()==0){
                    if(StringUtil.isNotNullString(esfMapHouseDo.getProjExpertUserId())){
                        agentBaseDo = agentService.queryAgentInfoByUserId(esfMapHouseDo.getProjExpertUserId(),city);
                    }else{
                        agentBaseDo.setAgentName(searchHits[i].getSourceAsMap().get("houseProxyName")==null?"":searchHits[i].getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHits[i].getSourceAsMap().get("houseProxyPhoto")==null?"":searchHits[i].getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHits[i].getSourceAsMap().get("houseProxyPhone")==null?"":searchHits[i].getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHits[i].getSourceAsMap().get("ofCompany")==null?"":searchHits[i].getSourceAsMap().get("ofCompany").toString());
                    }
                }

                esfMapHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                esfMapHouseDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    esfMapHouseDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isLowPrice = esfMapHouseDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                int isCutPrice = esfMapHouseDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = esfMapHouseDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                esfMapHouseDo.setHouseLableList(houseLableList);
                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if(esfMapHouseDo.getIsLowest() == 1){
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低","");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = esfMapHouseDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = esfMapHouseDo.getTotalAbsoluteWithCommunity();
                if(esfMapHouseDo.getIsLowPrice() == 1){
                    if(totalAbsoluteWithBizcircle< 0 && totalAbsoluteWithCommunity < 0){
                        if(Math.abs(totalAbsoluteWithBizcircle) >Math.abs(totalAbsoluteWithCommunity)){
                            lowPriceStr ="总价低于商圈同户型"+Math.abs(totalAbsoluteWithBizcircle)+"万";
                        }else{
                            lowPriceStr = "总价低于小区同户型"+Math.abs(totalAbsoluteWithCommunity)+"万";
                        }
                    }else if (totalAbsoluteWithBizcircle< 0 ){
                        lowPriceStr ="总价低于商圈同户型"+Math.abs(totalAbsoluteWithBizcircle)+"万";
                    }else if (totalAbsoluteWithCommunity< 0 ){
                        lowPriceStr = "总价低于小区同户型"+Math.abs(totalAbsoluteWithCommunity)+"万";
                    }
                }
                if(!StringUtil.isNullString(lowPriceStr)){
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = esfMapHouseDo.getPriceFloat();
                if (esfMapHouseDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = esfMapHouseDo.getAvgDealCycle();
                if (esfMapHouseDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List tagNameList = esfMapHouseDo.getRecommendBuildTagsName();
                String areaName = esfMapHouseDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = esfMapHouseDo.getTypeCounts();

                if (esfMapHouseDo.getIsMustRob() == 1 && esfMapHouseDo.getIsMainLayout() == 1) {
                    if (esfMapHouseDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (tagNameList.size() > 0 && typeCountsMap != null) {
                        if(tagNameList.contains("豪宅")){
                            communityLableStr = areaName + typeCountsMap.get(4).get(esfMapHouseDo.getAreaId()) + "大豪宅社区主力户型";
                        }else if(tagNameList.contains("别墅")){
                            communityLableStr = areaName + typeCountsMap.get(5).get(esfMapHouseDo.getAreaId()) + "大别墅社区主力户型";
                        }else if(tagNameList.contains("首次置业")){
                            communityLableStr = areaName + typeCountsMap.get(2).get(esfMapHouseDo.getAreaId()) + "大首置社区主力户型";
                        }else if(tagNameList.contains("换房升级")){
                            communityLableStr = areaName + typeCountsMap.get(3).get(esfMapHouseDo.getAreaId()) + "大换房社区主力户型";
                        }else if(tagNameList.contains("近公园")){
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if(!StringUtil.isNullString(communityLableStr)){
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = esfMapHouseDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(esfMapHouseDo.getHouseBusinessName()+esfMapHouseDo.getRoom()+"居室低总价榜NO."+rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = esfMapHouseDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }

                esfMapHouseDo.setHouseSubjectList(houseSubjectList);

                esfMapHouseDo.setNearbyDistance(esfMapHouseDo.getArea() + " " + esfMapHouseDo.getHouseBusinessName());

//                //增加地铁与房子之间的距离
//                String keys="";
//                if(null!=sellHouseDoQuery.getSubwayLineId())
//                {
//                    keys+=sellHouseDoQuery.getSubwayLineId().toString();
//                }
//                if (null!=sellHouseDoQuery.getSubwayStationId()){
//                    Map<Integer,String> map = new HashMap<>();
//                    List<Integer> sortDistance = new ArrayList<>();
//                    for(int j=0; j<sellHouseDoQuery.getSubwayStationId().length; j++){
//                        String stationKey = keys+"$"+sellHouseDoQuery.getSubwayStationId()[j];
//                        if(StringTool.isNotEmpty(esfMapHouseDo.getSubwayDistince().get(stationKey))){
//                            String stationValue = esfMapHouseDo.getSubwayDistince().get(stationKey).toString();
//                            String[] stationValueSplit = stationValue.split("\\$");
//                            Integer distance = Integer.valueOf(stationValueSplit[2]);
//                            sortDistance.add(distance);
//                            map.put(distance,stationKey);
//                        }
//                    }
//                    Integer minDistance = Collections.min(sortDistance);
//                    esfMapHouseDo.setSubwayDistanceInfo(esfMapHouseDo.getSubwayDistince().get(map.get(minDistance)).toString());
//                }

                esfCircleListDo.setEsfMapHouseDo(esfMapHouseDo);
                esfCircleListDos.add(esfCircleListDo);
            }
            esfCircleListDomain.setEsfCircleListDos(esfCircleListDos);
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }
        return esfCircleListDomain;
    }
}
