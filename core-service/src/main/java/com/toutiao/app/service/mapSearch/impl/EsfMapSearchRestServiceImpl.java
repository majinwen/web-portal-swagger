package com.toutiao.app.service.mapSearch.impl;

import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.web.common.constant.map.MapGroupConstant;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.mapSearch.MapGroupUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        //地图附近
        if (null!=esfMapSearchDoQuery.getLat() && null!=esfMapSearchDoQuery.getLon() && null!=esfMapSearchDoQuery.getDistance()) {
            esfMapSearchDomain = esfMapSearchByNear(esfMapSearchDoQuery, city);
            return esfMapSearchDomain;
        }
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

        if(null!=sellHouseDoQuery.getBeginPrice() && null!=sellHouseDoQuery.getEndPrice()){
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
        }
        //面积
        if(null != sellHouseDoQuery.getBeginArea() && null != sellHouseDoQuery.getEndArea()){
            if (sellHouseDoQuery.getBeginArea()!=0 && sellHouseDoQuery.getEndArea()!=0) {

                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()).lte(sellHouseDoQuery.getEndArea()));
            }else if(sellHouseDoQuery.getBeginArea()!=0 && sellHouseDoQuery.getEndArea()==0){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()));
            }else if(sellHouseDoQuery.getBeginArea()==0 && sellHouseDoQuery.getEndArea()!=0){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lte(sellHouseDoQuery.getEndArea()));
            }
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
