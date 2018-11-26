package com.toutiao.app.service.mapSearch.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.map.MapGroupConstant;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.mapSearch.MapGroupUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
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

        //地铁找房
        if (null != esfMapSearchDoQuery.getSubwayLineId()) {

            esfMapSearchDomain = esfMapSearchBySubway(esfMapSearchDoQuery, city);
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
//        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);

        long esfCount = searchSellHouse.getHits().totalHits;
        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(), esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(), esfMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("district_location").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByDistrict(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms terms = searchResponse.getAggregations().get("houseCount");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchDistrictDo = new EsfMapSearchDo();
            esfMapSearchDistrictDo.setId(Integer.valueOf(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString()));//区县id
            esfMapSearchDistrictDo.setCount((int)((ParsedStringTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms districtName = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtName");
            esfMapSearchDistrictDo.setName(districtName.getBuckets().get(0).getKeyAsString());//区县名称

            Terms districtAvgPrice = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtAvgPrice");
            esfMapSearchDistrictDo.setPrice(districtAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价

            Terms districtLatitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtLatitude");
            esfMapSearchDistrictDo.setLatitude(districtLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度

            Terms districtLongitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtLongitude");
            esfMapSearchDistrictDo.setLongitude(districtLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度

            String desc = nf.format(esfMapSearchDistrictDo.getPrice()/10000)+"万";
            esfMapSearchDistrictDo.setDesc(desc);
            data.add(esfMapSearchDistrictDo);

        }
        esfMapSearchDistrictDomain.setData(data);
        esfMapSearchDistrictDomain.setHit("可视范围内"+searchCount+"套房源，共"+esfCount+"房源");
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

        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
//        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
        long esfCount = searchSellHouse.getHits().totalHits;

        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(),esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(),esfMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("bizcircle_location").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);

        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByBizcircle(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms houseCount = searchResponse.getAggregations().get("houseCount");
        List buckets= houseCount.getBuckets();
        for (Object bucket : buckets){
            EsfMapSearchDo esfMapSearchBizcircleDo = new EsfMapSearchDo();
            esfMapSearchBizcircleDo.setId(Integer.valueOf(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString()));//商圈id
            esfMapSearchBizcircleDo.setCount((int) ((ParsedStringTerms.ParsedBucket) bucket).getDocCount());//房源数量


            Terms bizcircleName = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleName");
            esfMapSearchBizcircleDo.setName(bizcircleName.getBuckets().get(0).getKeyAsString());//商圈名称

            Terms bizcircleAvgprice = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleAvgprice");
            esfMapSearchBizcircleDo.setPrice(bizcircleAvgprice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价

            Terms bizcircleLatitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleLatitude");
            esfMapSearchBizcircleDo.setLatitude(bizcircleLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度

            Terms bizcircleLongitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("bizcircleLongitude");
            esfMapSearchBizcircleDo.setLongitude(bizcircleLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度

            String desc = nf.format(esfMapSearchBizcircleDo.getPrice()/10000)+"万";
            esfMapSearchBizcircleDo.setDesc(desc);
            data.add(esfMapSearchBizcircleDo);
        }
        esfMapSearchBizcircleDomain.setData(data);
        esfMapSearchBizcircleDomain.setHit("可视范围内"+searchCount+"套房源，共"+esfCount+"房源");
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
            esfMapSearchCommunityDo.setName(communityName.getBuckets().get(0).getKeyAsString());//社区名称

            Terms communityAvgPrice = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityAvgPrice");
            esfMapSearchCommunityDo.setPrice(communityAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价

            Terms plotLatitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLatitude");
            esfMapSearchCommunityDo.setLatitude(plotLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度

            Terms plotLongitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLongitude");
            esfMapSearchCommunityDo.setLongitude(plotLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度

            String desc = nf.format(esfMapSearchCommunityDo.getPrice()/10000)+"万("+esfMapSearchCommunityDo.getCount()+"套)";
            esfMapSearchCommunityDo.setDesc(desc);
            data.add(esfMapSearchCommunityDo);
        }
        esfMapSearchCommunityDomain.setData(data);
        esfMapSearchCommunityDomain.setHit("可视范围内"+searchCount+"套房源，共"+esfCount+"房源");
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
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", esfMapSearchDoQuery.getNewcode()));

        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));

        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

        //设置高斯函数
        GaussDecayFunctionBuilder functionBuilder = null;
        FunctionScoreQueryBuilder queryKmBuilder = null;
        GeoDistanceSortBuilder sort = null;
        if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
            double[] location =new double[]{sellHouseDoQuery.getLon(),sellHouseDoQuery.getLat()};
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("housePlotLocation",location,sellHouseDoQuery.getDistance()+"km",sellHouseDoQuery.getDistance()+"km");
            //获取5km内所有的二手房

            sort = SortBuilders.geoDistanceSort("housePlotLocation", sellHouseDoQuery.getLat(), sellHouseDoQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
            sort.geoDistance(GeoDistance.ARC);

        }
        queryKmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);
        if (StringUtil.isNotNullString(sellHouseDoQuery.getKeyword())) {
            List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()+1];
            }else{
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for(int i=0 ;i<searchKeyword.size();i++){
                    QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",searchKeyword.get(i));
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize-i);
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else{
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }


            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);

            }else{
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }else{
            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                query = QueryBuilders.functionScoreQuery(queryKmBuilder,functionBuilder).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }else{
                query = QueryBuilders.functionScoreQuery(queryKmBuilder).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }
        List<EsfMapHouseDo> esfMapHouseDos = new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchHouseList(query, sellHouseDoQuery.getDistance(),
                sellHouseDoQuery.getKeyword(),sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize(), city, sort);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            SearchHit communityHit = searchHists[0];
            String community = communityHit.getSourceAsString();
            esfMapCommunityDo = JSON.parseObject(community, EsfMapCommunityDo.class);
            String plotName = communityHit.getSourceAsMap().get("plotName").toString();
            esfMapCommunityDo.setPloatName(plotName);
            esfMapCommunityDo.setCount((int)hits.totalHits);
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                esfMapHouseDo=JSON.parseObject(details,EsfMapHouseDo.class);
                if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                    BigDecimal geoDis = new BigDecimal((Double) searchHit.getSortValues()[0]);
                    String distance = geoDis.setScale(1, BigDecimal.ROUND_CEILING)+DistanceUnit.KILOMETERS.toString();
                    esfMapHouseDo.setNearbyDistance(distance);
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
                if (!Objects.equals(titlePhoto, "") && !titlePhoto.startsWith("http://")) {
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

                //设置房源公司图标
                esfMapHouseDo.setCompanyIcon("http://wap-qn.bidewu.com/wap/5i5j.png");
                //设置房源标签
                List<HouseLable> houseLableList= new ArrayList<>();
                HouseLable houseLable = new HouseLable();
                houseLable.setText("捡漏");
                houseLable.setIcon("http://wap-qn.bidewu.com/wap/jl.png");
                houseLableList.add(houseLable);
                HouseLable houseLable1 = new HouseLable();
                houseLable1.setText("降价");
                houseLable1.setIcon("http://wap-qn.bidewu.com/wap/jj.png");
                houseLableList.add(houseLable1);
                HouseLable houseLable2 = new HouseLable();
                houseLable2.setText("抢手");
                houseLable2.setIcon("http://wap-qn.bidewu.com/wap/qs.png");
                houseLableList.add(houseLable2);
                esfMapHouseDo.setHouseLableList(houseLableList);
                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();
                HouseSubject sellHouseSubject = new HouseSubject();
                sellHouseSubject.setText("长宁大豪宅社区主力户型");
                sellHouseSubject.setUrl("http://www.baidu.com");
                houseSubjectList.add(sellHouseSubject);
                HouseSubject houseSubject1 = new HouseSubject();
                houseSubject1.setText("总价低于商圈同户型29.53万");
                houseSubject1.setUrl("http://www.baidu.com");
                houseSubjectList.add(houseSubject1);
                HouseSubject sellHouseSubject2 = new HouseSubject();
                sellHouseSubject2.setText("降5万");
                sellHouseSubject2.setUrl("http://www.baidu.com");
                houseSubjectList.add(sellHouseSubject2);
                esfMapHouseDo.setHouseSubjectList(houseSubjectList);

                esfMapHouseDos.add(esfMapHouseDo);
                //增加地铁与房子之间的距离
                String keys="";
                if(null!=sellHouseDoQuery.getSubwayLineId())
                {
                    keys+=sellHouseDoQuery.getSubwayLineId().toString();
                }
                if (null!=sellHouseDoQuery.getSubwayStationId()){
                    Map<Integer,String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for(int i=0; i<sellHouseDoQuery.getSubwayStationId().length; i++){
                        String stationKey = keys+"$"+sellHouseDoQuery.getSubwayStationId()[i];
                        if(StringTool.isNotEmpty(esfMapHouseDo.getSubwayDistince().get(stationKey))){
                            String stationValue = esfMapHouseDo.getSubwayDistince().get(stationKey).toString();
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance,stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    esfMapHouseDo.setSubwayDistanceInfo(esfMapHouseDo.getSubwayDistince().get(map.get(minDistance)).toString());
                }


            }
            esfHouseListDomain.setEsfMapHouseDos(esfMapHouseDos);
            esfHouseListDomain.setEsfMapCommunityDo(esfMapCommunityDo);
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
        boolQueryBuilder = getBoolQueryBuilder(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
        long esfCount = searchSellHouse.getHits().totalHits;

        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                .point(sellHouseDoQuery.getLat(), sellHouseDoQuery.getLon())
                .distance(sellHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);
        boolQueryBuilder.must(location);
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchByNear(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms terms = searchResponse.getAggregations().get("houseCount");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchNearDo = new EsfMapSearchDo();
            esfMapSearchNearDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//社区id
            esfMapSearchNearDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

            Terms communityName = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityName");
            esfMapSearchNearDo.setName(communityName.getBuckets().get(0).getKeyAsString());//社区名称

            Terms communityAvgPrice = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("communityAvgPrice");
            esfMapSearchNearDo.setPrice(communityAvgPrice.getBuckets().get(0).getKeyAsNumber().doubleValue());//均价

            Terms plotLatitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLatitude");
            esfMapSearchNearDo.setLatitude(plotLatitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//纬度

            Terms plotLongitude = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("plotLongitude");
            esfMapSearchNearDo.setLongitude(plotLongitude.getBuckets().get(0).getKeyAsNumber().doubleValue());//经度

            String desc = nf.format(esfMapSearchNearDo.getPrice()/10000) + "万(" + esfMapSearchNearDo.getCount() + "套)";//描述
            esfMapSearchNearDo.setDesc(desc);
            data.add(esfMapSearchNearDo);
        }
        esfMapSearchNearDomain.setData(data);
        esfMapSearchNearDomain.setHit("可视范围内"+searchCount+"套房源，共"+esfCount+"房源");
        return esfMapSearchNearDomain;
    }

    /**
     * 地铁找房
     * @param esfMapSearchDoQuery
     * @param city
     * @return
     */
    public EsfMapSearchDomain esfMapSearchBySubway(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {
        EsfMapSearchDomain esfMapSearchSubwayDomain = new EsfMapSearchDomain();
        List<EsfMapSearchDo> data = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        nf.setRoundingMode(RoundingMode.UP);

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getBoolQueryBuilder(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchResponse searchSellHouse = sellHouseEsDao.querySellHouse(boolQueryBuilder, city);
        long esfCount = searchSellHouse.getHits().totalHits;

        boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", esfMapSearchDoQuery.getSubwayLineId()));
        if (null != esfMapSearchDoQuery.getSubwayStationId()) {

        }
        SearchResponse searchResponse = esfMapSearchEsDao.esfMapSearchBySubway(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        Terms terms = searchResponse.getAggregations().get("houseCount");
        List buckets = terms.getBuckets();
        for (Object bucket : buckets) {
            EsfMapSearchDo esfMapSearchSubwayDo = new EsfMapSearchDo();
            esfMapSearchSubwayDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());//地铁站id
            esfMapSearchSubwayDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());//房源数量

            esfMapSearchSubwayDo.setName(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsString());//地铁站名称


        }

        return esfMapSearchSubwayDomain;
    }
    /**
     * 构建BoolQueryBuilder(地图附近找房使用)
     * @param sellHouseDoQuery
     * @return
     */
    public BoolQueryBuilder getBoolQueryBuilder(SellHouseDoQuery sellHouseDoQuery) {
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2)));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("plotNickname",sellHouseDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("plotName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart")));
            }
        }
        //楼盘id
        if(StringTool.isNotEmpty(sellHouseDoQuery.getBuildingId())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", sellHouseDoQuery.getBuildingId()));
        }

        //商圈id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", sellHouseDoQuery.getAreaId()));

        }
        //区域id
        if (StringTool.isNotEmpty((sellHouseDoQuery.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", sellHouseDoQuery.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", sellHouseDoQuery.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", sellHouseDoQuery.getSubwayStationId()));
        }

        if (sellHouseDoQuery.getBeginPrice()!=0 && sellHouseDoQuery.getEndPrice()!=0) {
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(sellHouseDoQuery.getBeginPrice()).lte(sellHouseDoQuery.getEndPrice()));
        }else if(sellHouseDoQuery.getBeginPrice()!=0 && sellHouseDoQuery.getEndPrice()==0){
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(sellHouseDoQuery.getBeginPrice()));
        }else if(sellHouseDoQuery.getBeginPrice()==0 && sellHouseDoQuery.getEndPrice()!=0){
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .lte(sellHouseDoQuery.getEndPrice()));

        }
        //面积

        if (sellHouseDoQuery.getBeginArea()!=0 && sellHouseDoQuery.getEndArea()!=0) {

            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()).lte(sellHouseDoQuery.getEndArea()));
        }else if(sellHouseDoQuery.getBeginArea()!=0 && sellHouseDoQuery.getEndArea()==0){
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()));
        }else if(sellHouseDoQuery.getBeginArea()==0 && sellHouseDoQuery.getEndArea()!=0){
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lte(sellHouseDoQuery.getEndArea()));
        }

        //楼龄
        if (StringUtil.isNotNullString(sellHouseDoQuery.getHouseYearId())) {
            String houseYear = sellHouseDoQuery.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
            String[] layoutId = houseYear.split(",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("year")
                        //计算房源建成年代
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i+1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i])))));
                booleanQueryBuilder.must(boolQueryBuilder);

            }
        }
        //户型(室)
        if (StringTool.isNotEmpty(sellHouseDoQuery.getLayoutId())) {
            Integer[] layoutId = sellHouseDoQuery.getLayoutId();
//            booleanQueryBuilder.must();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room",layoutId)));
        }


        //朝向
        if (StringTool.isNotEmpty(sellHouseDoQuery.getForwardId())) {
            Integer[] forwardId =sellHouseDoQuery.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward",forwardId)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(sellHouseDoQuery.getLabelId())) {
            Integer[] longs = sellHouseDoQuery.getLabelId();
            BoolQueryBuilder bool= QueryBuilders.boolQuery();
            boolean has_subway = Arrays.asList(longs).contains(1);
            if(has_subway){
                Integer[] tagOther = new Integer[longs.length-1];
                int idx = 0;
                for(int i=0;i<longs.length;i++){
                    if(longs[i].equals(1)){
                        bool.should(QueryBuilders.termQuery("has_subway", longs[i]));
                    } else {
                        tagOther[idx++] = longs[i];
                    }
                }
                if(tagOther.length!=0){
                    bool.should(QueryBuilders.termsQuery("tags", tagOther));
                }
                booleanQueryBuilder.must(bool);
            }else{
                booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", longs));
            }
        }
        //降价房
        if(StringTool.isNotEmpty(sellHouseDoQuery.getIsCutPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", sellHouseDoQuery.getIsCutPrice()));
        }
        //洼地房
        if(StringTool.isNotEmpty(sellHouseDoQuery.getIsLowPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", sellHouseDoQuery.getIsLowPrice()));
        }
        //逢出毕抢
        if(StringTool.isNotEmpty(sellHouseDoQuery.getIsMustRob())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob", sellHouseDoQuery.getIsMustRob()));
        }

        return booleanQueryBuilder;
    }
}
