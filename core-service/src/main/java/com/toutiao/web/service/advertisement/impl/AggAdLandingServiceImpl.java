package com.toutiao.web.service.advertisement.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.ClaimSellHouseDo;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.domain.sellhouse.SellHousesSearchDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.officeweb.CpcSellHouse;
import com.toutiao.web.dao.mapper.officeweb.CpcSellHouseMapper;
import com.toutiao.web.domain.advertisement.*;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class AggAdLandingServiceImpl implements AggAdLandingService{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.claim.esfhouse.index}")
    private String recommendEsfIndex;//推荐二手房房源索引
    @Value("${tt.claim.esfhouse.type}")
    private String recommendEsfType;//推荐二手房房源索引类型
    @Value("${tt.esf.agent.index}")
    private String agentIndex;//认领二手房房源索引
    @Value("${tt.esf.agent.type}")
    private String agentType;//认领二手房房源索引类型
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${tt.claim.renthouse.index}")
    private String recommendRentIndex;
    @Value("${tt.claim.renthouse.type}")
    private String recommendRentType;
    @Value("${distance}")
    private Double distance;
    @Autowired
    private RedisSessionUtils redisSessionUtils;
    @Autowired
    private CpcSellHouseMapper cpcSellHouseMapper;
    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentService agentService;


    private static final String RECOMMEND_DEFAULT = "ad_recommend_sellHouse_queryBit";
    private static final String RECOMMEND_SUBWAY = "ad_recommend_sellHouse_queryBit_subway";
    private static final String RECOMMEND_TOTAL_PRICE = "ad_recommend_sellHouse_queryBit_totalprice";
    private static final String RECOMMEND_LAYOUT = "ad_recommend_sellHouse_queryBit_layout";
    private static final String RECOMMEND_MANSION = "ad_recommend_sellHouse_queryBit_mansion";
    private static final String RECOMMEND_DISTRICT = "ad_recommend_sellHouse_queryBit_district";
    private static final String RECOMMEND_AREA = "ad_recommend_sellHouse_queryBit_area";


    private static final String RENT_RECOMMEND_DEFAULT = "ad_recommend_rentHouse_queryBit";
    private static final String RENT_RECOMMEND_PRICE = "ad_recommend_rentHouse_queryBit_price";
    private static final String RENT_RECOMMEND_SUBWAY = "ad_recommend_rentHouse_queryBit_subway";
    private static final String RENT_RECOMMEND_MONTHPAY = "ad_recommend_rentHouse_queryBit_monthPay";
    private static final String RENT_RECOMMEND_ENTIRERENT = "ad_recommend_rentHouse_queryBit_entireRent";
    private static final String RENT_RECOMMEND_DEFAULT_V2 = "ad_recommend_rentHouse_queryBit_v2";
    private static final String RENT_RECOMMEND_SOLEPRICE_V2 = "ad_recommend_rentHouse_queryBit_price_v2";
    private static final String RENT_RECOMMEND_DOUBLEPRICE_V2 = "ad_recommend_rentHouse_queryBit_price_v2";
    private static final String RENT_RECOMMEND_SUBWAY_V2 = "ad_recommend_rentHouse_queryBit_subway_v2";
    private static final String RENT_RECOMMEND_MONTHPAY_V2 = "ad_recommend_rentHouse_queryBit_monthPay_v2";
    private static final String RENT_RECOMMEND_RENT_V2 = "ad_recommend_rentHouse_queryBit_rent_v2";
    private static final String RENT_RECOMMEND_QUALITYRENT_V2 = "ad_recommend_rentHouse_queryBit_qualityRent_v2";

    private static final Integer CPC_STATUS_TOP10_1 = 1;
    private static final Integer CPC_STATUS_TOP10_0 = 0;



    @Override
    public Map<String, Object> getAdLanding(AggAdLandingDo aggAdLandingDo) {

        TransportClient client = esClientTools.init();
//        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        QueryBuilder queryBuilder = null;

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
           // booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", aggAdLandingDo.getNs()));
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
        }

        // 房源标签（满5，满2）
        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
            String[] layoutId = aggAdLandingDo.getTag().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", layoutId));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //小户型（90㎡内）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLs())) {
            String area = aggAdLandingDo.getLs().replaceAll("\\[","").replaceAll("]","")
                    .replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //豪宅 （200㎡以上）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLh())) {
            String area = aggAdLandingDo.getLs().replaceAll("\\[","").replaceAll("]","")
                    .replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));

        int pageNum = 1;

        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }

        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                .setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic"},
                        null)
                .setFrom((pageNum-1)*aggAdLandingDo.getPs())
                .setSize(aggAdLandingDo.getPs())
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        List<Map<String,Object>> resultlist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,Object> list = hit.getSourceAsMap();
            list.put("pageNum",aggAdLandingDo.getPn());
            resultlist.add(list);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",resultlist);
        result.put("total", hits.getTotalHits());

        return result;
    }


    private Long getNextIndex(String key,Long startBit,Long currentBit){
        if(startBit==0) {
            String redisValue = redisSessionUtils.getValue(key);
            if (StringUtils.isNotBlank(redisValue)) {
                return Long.parseLong(redisValue);
            }
        }
        return currentBit;
    }

    /**
     * 二手房推荐房源
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public SellHouseDomain getRecommendAdLanding(AggAdLandingDo aggAdLandingDo) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        //判断是否首次进入
        Long startBit = aggAdLandingDo.getStartBit();

        Long queryBit = aggAdLandingDo.getQueryBit();

        Integer pageNum = aggAdLandingDo.getPn();
        Integer pageSize = aggAdLandingDo.getPs();


        //有了用户的首次进入标志位和用户遍历数据的起始位置，那么就可以开始查询了。。。
        //查询二手房推优表中的数据
        //首先，填充筛选条件
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//构建筛选器
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel","0"));
        String redisCatogory = RECOMMEND_DEFAULT;

        // 筛选条件1-默认全部类型的推荐二手房房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getTj())){
            redisCatogory = RECOMMEND_DEFAULT;
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("adSort").gt(queryBit));
        }

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源
            redisCatogory = RECOMMEND_SUBWAY;

        }

        //区域
        if(StringTool.isNotEmpty(aggAdLandingDo.getDistrict())){
            redisCatogory = RECOMMEND_DISTRICT+"_"+aggAdLandingDo.getDistrict();
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", aggAdLandingDo.getDistrict()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                redisCatogory = redisCatogory+"_"+aggAdLandingDo.getRoom();
                if(Integer.valueOf(aggAdLandingDo.getRoom())>4){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(5));
                }else{
                    booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
                }
            }
        }

        //商圈
        if(StringTool.isNotEmpty(aggAdLandingDo.getAreaId())){
            redisCatogory = RECOMMEND_AREA+"_"+aggAdLandingDo.getAreaId();
            String[] areaIDs = aggAdLandingDo.getAreaId().split(",");

            String[] ids = new String[areaIDs.length];
            for(int i=0; i<areaIDs.length; i++){
                ids[i] =areaIDs[i];
            }
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId",ids));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                redisCatogory = redisCatogory+"_"+aggAdLandingDo.getRoom();
                if(Integer.valueOf(aggAdLandingDo.getRoom())>3){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(4));
                }else{
                    booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
                }
            }
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            redisCatogory = RECOMMEND_TOTAL_PRICE;
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源


        }

        //小户型（1居，2居）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLs())) {
            redisCatogory = RECOMMEND_LAYOUT;
            String[] layoutId = aggAdLandingDo.getLs().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源

        }

        //豪宅
        if (StringUtil.isNotNullString(aggAdLandingDo.getLh())) {
            redisCatogory = RECOMMEND_MANSION;

            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(aggAdLandingDo.getLh()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源

        }

        //面积
        if (StringUtil.isNotNullString(aggAdLandingDo.getHouseArea())) {
            if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==1){//等于
                booleanQueryBuilder.must(QueryBuilders.termsQuery("buildArea",aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==2){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==3){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lt(aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==4){
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String area = aggAdLandingDo.getHouseArea().replaceAll("\\[","").replaceAll("]","")
                        .replaceAll("-",",");
                String[] layoutId = area.split(",");
                for (int i = 0; i < layoutId.length; i = i + 2) {
                    if (i + 1 > layoutId.length) {
                        break;
                    }
                    boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                    booleanQueryBuilder.must(boolQueryBuilder);
                }
            }
        }

        //减价房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsCutPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", aggAdLandingDo.getIsCutPrice()));
        }

        //新上房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsNew())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", aggAdLandingDo.getIsNew()));
        }
        //洼地房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsLowPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", aggAdLandingDo.getIsLowPrice()));
        }

        queryBit = getNextIndex(redisCatogory,startBit,queryBit);
        RangeQueryBuilder range = QueryBuilders.rangeQuery("adSort").gt(queryBit);
        if(queryBit<startBit){
            range.lt(startBit);
        }
        booleanQueryBuilder.must(range);
        // ES查询了一下
        SearchResponse searchResponse = getRecommendSellHouseAdLanding(booleanQueryBuilder, pageSize);
        //判断ES给我的返回结果
        //1.查询返回的结果数量，默认10条一档级
        long query_size = searchResponse.getHits().getHits().length;

        sellHouseDomain =  getRecommendHits(aggAdLandingDo,searchResponse);



        String newRedisValue = "";
        if(startBit==0){
            newRedisValue=sellHouseDomain.getSourceEnd().toString();
            sellHouseDomain.setStartBit(sellHouseDomain.getSourceStart());
        }
        else {
            sellHouseDomain.setStartBit(startBit);
        }





        if(query_size < 10){
            //到一圈了
            if(queryBit<startBit || (startBit==0 && queryBit==0) ){
                sellHouseDomain.setSign(1);
            }
            //到队尾了
            else {
                newRedisValue = "0";
            }
            sellHouseDomain.setQueryBit(0L);
//            sellHouseDomain.setSign(1);
            //如果满足0-10之间，还有数据? 那么开始遍历数据了
        }
        else {
            sellHouseDomain.setQueryBit(sellHouseDomain.getSourceEnd());
            newRedisValue = sellHouseDomain.getSourceEnd().toString();
        }

        if(StringUtils.isNotBlank(newRedisValue)) {
            redisSessionUtils.set(redisCatogory, newRedisValue);
        }
        return sellHouseDomain;
    }


    public SellHouseDomain getRecommendHits(AggAdLandingDo aggAdLandingDo, SearchResponse searchResponse){
        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        SearchHit[] searchHists = searchResponse.getHits().getHits();
        List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDos = new ArrayList<>();
        String detail="";
        Long source_start=0L;
        Long source_end = 0L;
        for (int i=0;i<searchHists.length;i++) {
            SearchHit hit = searchHists[i];
            if(i==0){
                source_start =Long.parseLong(hit.getSourceAsMap().get("adSort").toString());
            }
            if((i+1)==searchHists.length){
                source_end =Long.parseLong(hit.getSourceAsMap().get("adSort").toString());
            }
            detail = hit.getSourceAsString();
            SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,SellHouseAggAdLandingDo.class);
            sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }

        sellHouseDomain.setSourceStart(source_start);
        sellHouseDomain.setSourceEnd(source_end);

        sellHouseDomain.setSellHouseAggAdLandingDoList(sellHouseAggAdLandingDos);
        return sellHouseDomain;
    }


    public RentHouseDomain getRentRecommendHits(SearchResponse searchResponse){
        RentHouseDomain rentHouseDomain = new RentHouseDomain();
        SearchHit[] searchHists = searchResponse.getHits().getHits();
        List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDos = new ArrayList<>();
        String detail="";
        Long source_start=0L;
        Long source_end = 0L;
        for (int i=0;i<searchHists.length;i++) {
            SearchHit hit = searchHists[i];
            if(i==0){
                source_start =Long.parseLong(hit.getSourceAsMap().get("adSort").toString());
            }
            if((i+1)==searchHists.length){
                source_end =Long.parseLong(hit.getSourceAsMap().get("adSort").toString());
            }
            detail = hit.getSourceAsString();
            RentHouseAggAdLandingDo rentHouseAggAdLandingDo = JSON.parseObject(detail,RentHouseAggAdLandingDo.class);
            rentHouseAggAdLandingDos.add(rentHouseAggAdLandingDo);
        }

        rentHouseDomain.setSourceStart(source_start);
        rentHouseDomain.setSourceEnd(source_end);

        rentHouseDomain.setRentHouseAggAdLandingDoList(rentHouseAggAdLandingDos);
        return rentHouseDomain;
    }

    /**
     * 二手房认领房源
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public SellHouseDomain getClaimAdLanding(AggAdLandingDo aggAdLandingDo) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel","0"));
        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        // 筛选条件1-默认全部类型的认领二手房房源
        booleanQueryBuilder.must(QueryBuilders.termQuery("isRecommend",0));

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //小户型（1居，2居）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLs())) {
            String[] layoutId = aggAdLandingDo.getLs().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
        }

        //豪宅
        if (StringUtil.isNotNullString(aggAdLandingDo.getLh())) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(aggAdLandingDo.getLh()));
        }

        //区县
        if (StringTool.isNotEmpty(aggAdLandingDo.getDistrict())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", aggAdLandingDo.getDistrict()));
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                if(Integer.valueOf(aggAdLandingDo.getRoom())>4){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(5));
                }else{
                    booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
                }

            }
        }

        //商圈
        if(StringTool.isNotEmpty(aggAdLandingDo.getAreaId())){
            String[] areaIDs = aggAdLandingDo.getAreaId().split(",");
            String[] ids = new String[areaIDs.length];
            for(int i=0; i<areaIDs.length; i++){
                ids[i] =areaIDs[i];
            }
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId",ids));
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                if(Integer.valueOf(aggAdLandingDo.getRoom())>3){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(4));
                }else{
                    booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
                }
            }
        }

        //面积
        if (StringUtil.isNotNullString(aggAdLandingDo.getHouseArea())) {
            if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==1){//等于
                booleanQueryBuilder.must(QueryBuilders.termsQuery("buildArea",aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==2){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==3){
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lt(aggAdLandingDo.getHouseArea()));
            }else if(StringTool.isEmpty(aggAdLandingDo.getHaSign()) && aggAdLandingDo.getHaSign()==4){
                String area = aggAdLandingDo.getHouseArea().replaceAll("\\[","").replaceAll("]","")
                        .replaceAll("-",",");
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String[] layoutId = area.split(",");
                for (int i = 0; i < layoutId.length; i = i + 2) {
                    if (i + 1 > layoutId.length) {
                        break;
                    }
                    boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                    booleanQueryBuilder.must(boolQueryBuilder);
                }
            }
        }

        //减价房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsCutPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", aggAdLandingDo.getIsCutPrice()));
        }

        //新上房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsNew())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", aggAdLandingDo.getIsNew()));
        }
        //洼地房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsLowPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", aggAdLandingDo.getIsLowPrice()));
        }

        SearchResponse searchResponse = getClaimSellHouseDetailsAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            sellHouseDomain.setSign(2);
        }else{
            sellHouseDomain.setSign(1);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();
        List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,SellHouseAggAdLandingDo.class);
            sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }


        sellHouseDomain.setSellHouseAggAdLandingDoList(sellHouseAggAdLandingDos);
        sellHouseDomain.setPageNum(pageNum);
        return sellHouseDomain;
    }

    /**
     * 二手房经纪公司导入
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public SellHouseDomain getApiImportAdLanding(AggAdLandingDo aggAdLandingDo) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();

        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel","0"));
        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //小户型（1居，2居）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLs())) {
            String[] layoutId = aggAdLandingDo.getLs().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
        }

        //豪宅
        if (StringUtil.isNotNullString(aggAdLandingDo.getLh())) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(aggAdLandingDo.getLh()));
        }

        //区县
        if (StringTool.isNotEmpty(aggAdLandingDo.getDistrict())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", aggAdLandingDo.getDistrict()));
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                if(Integer.valueOf(aggAdLandingDo.getRoom())>4){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(5));
                }
                booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
            }
        }

        //商圈
        if(StringTool.isNotEmpty(aggAdLandingDo.getAreaId())){
            String[] areaIDs = aggAdLandingDo.getAreaId().split(",");
            String[] ids = new String[areaIDs.length];
            for(int i=0; i<areaIDs.length; i++){
                ids[i] =areaIDs[i];
            }
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId",ids));
            if(StringUtil.isNotNullString(aggAdLandingDo.getRoom())){
                if(Integer.valueOf(aggAdLandingDo.getRoom())>3){
                    booleanQueryBuilder.must(QueryBuilders.rangeQuery("room").gte(4));
                }else{
                    booleanQueryBuilder.must(QueryBuilders.termQuery("room", aggAdLandingDo.getRoom()));
                }
            }
        }

        //减价房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsCutPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", aggAdLandingDo.getIsCutPrice()));
        }

        //新上房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsNew())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", aggAdLandingDo.getIsNew()));
        }
        //洼地房
        if(StringTool.isNotEmpty(aggAdLandingDo.getIsLowPrice())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", aggAdLandingDo.getIsLowPrice()));
        }

        SearchResponse searchResponse = getUnClaimSellHouseDetailsAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            sellHouseDomain.setSign(0);
        }else{
            sellHouseDomain.setSign(2);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();

        List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,SellHouseAggAdLandingDo.class);
            sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }

        sellHouseDomain.setSellHouseAggAdLandingDoList(sellHouseAggAdLandingDos);
        sellHouseDomain.setPageNum(pageNum);
        return sellHouseDomain;
    }

    /**
     * 出租推荐房源
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public RentHouseDomain getRentRecommendAdLanding(AggAdLandingDo aggAdLandingDo) {

        RentHouseDomain rentHouseDomain = new RentHouseDomain();
        //判断是否首次进入
        Long startBit = aggAdLandingDo.getStartBit();

        Long queryBit = aggAdLandingDo.getQueryBit();

        Integer pageSize = aggAdLandingDo.getPs();


        //有了用户的首次进入标志位和用户遍历数据的起始位置，那么就可以开始查询了。。。
        //查询二手房推优表中的数据
        //首先，填充筛选条件
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//构建筛选器
        booleanQueryBuilder.must(QueryBuilders.termQuery("rent_sign",0));
        String redisCatogory = RENT_RECOMMEND_DEFAULT;

        // 筛选条件1-默认全部类型的推荐出租房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getTj())){
            redisCatogory = RENT_RECOMMEND_DEFAULT;
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("adSort").gt(queryBit));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));

        }

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
            redisCatogory = RENT_RECOMMEND_SUBWAY;

        }


        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            redisCatogory = RENT_RECOMMEND_PRICE;
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        }

        //标签，月付
        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
            redisCatogory = RENT_RECOMMEND_MONTHPAY;
            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", aggAdLandingDo.getTag()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        }

        //整租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            redisCatogory = RENT_RECOMMEND_ENTIRERENT;
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        }


        queryBit = getNextIndex(redisCatogory,startBit,queryBit);
        RangeQueryBuilder range = QueryBuilders.rangeQuery("adSort").gt(queryBit);
        if(queryBit<startBit){
            range.lt(startBit);
        }
        booleanQueryBuilder.must(range);
        // ES查询了一下
        SearchResponse searchResponse = getRecommendRentHouseAdLanding(booleanQueryBuilder, pageSize);
        //判断ES给我的返回结果
        //1.查询返回的结果数量，默认10条一档级
        long query_size = searchResponse.getHits().getHits().length;

        rentHouseDomain =  getRentRecommendHits(searchResponse);



        String newRedisValue = "";
        if(startBit==0){
            newRedisValue=rentHouseDomain.getSourceEnd().toString();
            rentHouseDomain.setStartBit(rentHouseDomain.getSourceStart());
        }
        else {
            rentHouseDomain.setStartBit(startBit);
        }

        if(query_size < 10){
            //到一圈了
            if(queryBit<startBit || (startBit==0 && queryBit==0) ){
                rentHouseDomain.setSign(1);
            }
            //到队尾了
            else {
                newRedisValue = "0";
            }
            rentHouseDomain.setQueryBit(0L);
        }
        else {
            rentHouseDomain.setQueryBit(rentHouseDomain.getSourceEnd());
            newRedisValue = rentHouseDomain.getSourceEnd().toString();
        }

        if(StringUtils.isNotBlank(newRedisValue)) {
            redisSessionUtils.set(redisCatogory, newRedisValue);
        }
        return rentHouseDomain;
    }

    /**
     * 出租录入房源
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public RentHouseDomain getRentInputAdLanding(AggAdLandingDo aggAdLandingDo) {


        RentHouseDomain sellHouseDomain = new RentHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();

        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        // 筛选条件1-默认全部类型的录入出租房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getTj())){

        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_recommend",0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //标签，月付
        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", aggAdLandingDo.getTag()));
        }

        //整租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
        }


        SearchResponse searchResponse = getInputRentHouseAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            sellHouseDomain.setSign(2);
        }else{
            sellHouseDomain.setSign(1);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();
        List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            RentHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,RentHouseAggAdLandingDo.class);
            rentHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }


        sellHouseDomain.setRentHouseAggAdLandingDoList(rentHouseAggAdLandingDos);
        sellHouseDomain.setPageNum(pageNum);
        return sellHouseDomain;
    }

    @Override
    public RentHouseDomain getRentImportAdLanding(AggAdLandingDo aggAdLandingDo) {

        RentHouseDomain rentHouseDomain = new RentHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();


        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }

        booleanQueryBuilder.must(QueryBuilders.termQuery("is_recommend",0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",3));
        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //标签，月付
        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", aggAdLandingDo.getTag()));
        }

        //整租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
        }

        SearchResponse searchResponse = getImportRentHouseAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            rentHouseDomain.setSign(0);
        }else{
            rentHouseDomain.setSign(2);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();

        List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            RentHouseAggAdLandingDo rentHouseAggAdLandingDo = JSON.parseObject(detail,RentHouseAggAdLandingDo.class);
            rentHouseAggAdLandingDos.add(rentHouseAggAdLandingDo);
        }

        rentHouseDomain.setRentHouseAggAdLandingDoList(rentHouseAggAdLandingDos);
        rentHouseDomain.setPageNum(pageNum);
        return rentHouseDomain;
    }

    /**
     * 出租推荐房源v2
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public RentHouseDomain getRentRecommendAdLandingV2(AggAdLandingDo aggAdLandingDo) {
        RentHouseDomain rentHouseDomain = new RentHouseDomain();
        //判断是否首次进入
        Long startBit = aggAdLandingDo.getStartBit();

        Long queryBit = aggAdLandingDo.getQueryBit();

        Integer pageSize = aggAdLandingDo.getPs();


        //有了用户的首次进入标志位和用户遍历数据的起始位置，那么就可以开始查询了。。。
        //查询二手房推优表中的数据
        //首先，填充筛选条件
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//构建筛选器
//        booleanQueryBuilder.must(QueryBuilders.termQuery("rent_sign",0));
        String redisCatogory = RENT_RECOMMEND_DEFAULT_V2;

        // 筛选条件1-默认全部类型的推荐出租房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getTj())){
            redisCatogory = RENT_RECOMMEND_DEFAULT_V2;
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("adSort").gt(queryBit));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        }

        //近地铁
//        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
//            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
//            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
//            redisCatogory = RENT_RECOMMEND_SUBWAY_V2;
//
//        }

        //房源租金(solo)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isEmpty(aggAdLandingDo.getEp())) {
            redisCatogory = RENT_RECOMMEND_SOLEPRICE_V2;
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp())));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));

        }

        //房源租金(double)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            redisCatogory = RENT_RECOMMEND_DOUBLEPRICE_V2;
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));//isRecommend大于0，都是推荐房源
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));

        }

        //整租/合租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            redisCatogory = RENT_RECOMMEND_RENT_V2;
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("is_recommend").gt(0));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
            //如果是合租则过滤公寓
            if (aggAdLandingDo.getRentType()==2){
                booleanQueryBuilder.must(QueryBuilders.termQuery("rent_sign", 0));
            }
        }

        //品质公寓(公寓中合租的房源)
        if (StringTool.isNotEmpty(aggAdLandingDo.getQualityRent())&&"1".equals(aggAdLandingDo.getQualityRent())){
            redisCatogory = RENT_RECOMMEND_QUALITYRENT_V2;
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", 2));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("rent_sign").gt(0));
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        }

        queryBit = getNextIndex(redisCatogory,startBit,queryBit);
        RangeQueryBuilder range = QueryBuilders.rangeQuery("adSort").gt(queryBit);
        if(queryBit<startBit){
            range.lt(startBit);
        }
        booleanQueryBuilder.must(range);
        // ES查询了一下
        SearchResponse searchResponse = getRecommendRentHouseAdLanding(booleanQueryBuilder, pageSize);
        //判断ES给我的返回结果
        //1.查询返回的结果数量，默认10条一档级
        long query_size = searchResponse.getHits().getHits().length;

        rentHouseDomain =  getRentRecommendHits(searchResponse);



        String newRedisValue = "";
        if(startBit==0){
            newRedisValue=rentHouseDomain.getSourceEnd().toString();
            rentHouseDomain.setStartBit(rentHouseDomain.getSourceStart());
        }
        else {
            rentHouseDomain.setStartBit(startBit);
        }

        if(query_size < 10){
            //到一圈了
            if(queryBit<startBit || (startBit==0 && queryBit==0) ){
                rentHouseDomain.setSign(1);
            }
            //到队尾了
            else {
                newRedisValue = "0";
            }
            rentHouseDomain.setQueryBit(0L);
        }
        else {
            rentHouseDomain.setQueryBit(rentHouseDomain.getSourceEnd());
            newRedisValue = rentHouseDomain.getSourceEnd().toString();
        }

        if(StringUtils.isNotBlank(newRedisValue)) {
            redisSessionUtils.set(redisCatogory, newRedisValue);
        }
        return rentHouseDomain;
    }

    /**
     * 出租录入房源v2
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public RentHouseDomain getRentInputAdLandingV2(AggAdLandingDo aggAdLandingDo) {
        RentHouseDomain sellHouseDomain = new RentHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();

        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        // 筛选条件1-默认全部类型的录入出租房源
        if(StringTool.isNotEmpty(aggAdLandingDo.getTj())){

        }
        //非推荐
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_recommend",0));
        //录入
        booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));

        //近地铁
//        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
//        }

        //租金范围(solo)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp())));
        }

        //租金范围(double)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));
        }

        //标签，月付
//        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
//            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", aggAdLandingDo.getTag()));
//        }

        //整租/合租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
            //如果是合租则过滤公寓
            if (aggAdLandingDo.getRentType()==2){
                booleanQueryBuilder.must(QueryBuilders.termQuery("rent_sign", 0));
            }
        }

        //品质公寓(公寓中合租的房源)
        if (StringTool.isNotEmpty(aggAdLandingDo.getQualityRent())&&"1".equals(aggAdLandingDo.getQualityRent())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", 2));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("rent_sign").gt(0));
        }


        SearchResponse searchResponse = getInputRentHouseAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            sellHouseDomain.setSign(2);
        }else{
            sellHouseDomain.setSign(1);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();
        List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            RentHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,RentHouseAggAdLandingDo.class);
            rentHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }


        sellHouseDomain.setRentHouseAggAdLandingDoList(rentHouseAggAdLandingDos);
        sellHouseDomain.setPageNum(pageNum);
        return sellHouseDomain;
    }

    /**
     * 出租导入房源v2
     * @param aggAdLandingDo
     * @return
     */
    @Override
    public RentHouseDomain getRentImportAdLandingV2(AggAdLandingDo aggAdLandingDo) {
        RentHouseDomain rentHouseDomain = new RentHouseDomain();
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();


        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        //非推荐
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_recommend",0));
        //导入房源
        booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",3));
        //近地铁
//        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", aggAdLandingDo.getNs()));
//        }

        //租金范围(solo)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp())));

        }

        //租金范围(double)
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("rent_house_price")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //标签，月付
//        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
//            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", aggAdLandingDo.getTag()));
//        }

        //整租/合租
        if(StringTool.isNotEmpty(aggAdLandingDo.getRentType())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", aggAdLandingDo.getRentType()));
            //如果是合租则过滤公寓
            if (aggAdLandingDo.getRentType()==2){
                booleanQueryBuilder.must(QueryBuilders.termQuery("rent_sign", 0));
            }
        }

        //品质公寓(公寓中合租的房源)
        if (StringTool.isNotEmpty(aggAdLandingDo.getQualityRent())&&"1".equals(aggAdLandingDo.getQualityRent())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("rent_type", 2));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("rent_sign").gt(0));
        }

        SearchResponse searchResponse = getImportRentHouseAdLanding(booleanQueryBuilder, pageNum, pageSize);
        //计算补充数据的起始位置
        long query_size = searchResponse.getHits().getHits().length;
        if(query_size==0){
            rentHouseDomain.setSign(0);
        }else{
            rentHouseDomain.setSign(2);
        }
        SearchHit[] searchHists = searchResponse.getHits().getHits();

        List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            RentHouseAggAdLandingDo rentHouseAggAdLandingDo = JSON.parseObject(detail,RentHouseAggAdLandingDo.class);
            rentHouseAggAdLandingDos.add(rentHouseAggAdLandingDo);
        }

        rentHouseDomain.setRentHouseAggAdLandingDoList(rentHouseAggAdLandingDos);
        rentHouseDomain.setPageNum(pageNum);
        return rentHouseDomain;
    }

    /**
     * 二手房点击量top10
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseTop10() {
        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic",
                "adSort","isClaim","claimHouseTitle","claimHousePhotoTitle","claimTags","claimTagsName","claimHouseId"};
        TransportClient client = esClientTools.init();
        Date date = DateUtil.lastDate(new Date());
        String cpcDate = DateUtil.format(date);

        List<CpcSellHouse> cpcSellHouseList = cpcSellHouseMapper.selectByLastDate(cpcDate);
        QueryBuilder queryBuilder;
        SearchResponse searchResponse;
        String[] houseId = new String[10];
        if(null !=cpcSellHouseList && cpcSellHouseList.size() > 0){
            for(int i = 0; i< cpcSellHouseList.size(); i++){
                houseId[i] = cpcSellHouseList.get(i).getHouseId();
            }
        }else{
            Date beforeYesterday = DateUtil.beforeYesterday(new Date());
            String cpcbeforeYesterday = DateUtil.format(beforeYesterday);
            List<CpcSellHouse> cpcBeforeYesterdaySellHouseList = cpcSellHouseMapper.selectByBeforeYesterday(cpcbeforeYesterday);
            if(null !=cpcBeforeYesterdaySellHouseList && cpcBeforeYesterdaySellHouseList.size() > 0){
                for(int i = 0; i< cpcBeforeYesterdaySellHouseList.size(); i++){
                    houseId[i] = cpcBeforeYesterdaySellHouseList.get(i).getHouseId();
                }
            }
        }
        queryBuilder = QueryBuilders.idsQuery(projhouseType).addIds(houseId);
        searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(queryBuilder).setFetchSource(returnField,null).execute().actionGet();

        SearchHit[] searchHists = searchResponse.getHits().getHits();

        List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDos = new ArrayList<>();
        String detail = "";
        for(SearchHit hit : searchHists){
            detail = hit.getSourceAsString();
            SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,SellHouseAggAdLandingDo.class);
            sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }

        if(sellHouseAggAdLandingDos.size() < 10){
            String[] houseId_repair = new String[10-sellHouseAggAdLandingDos.size()];
            List<CpcSellHouse> cpcSellHousesRepairs = cpcSellHouseMapper.selectByLastDateRepair(cpcDate);
            if(null != cpcSellHousesRepairs && cpcSellHousesRepairs.size() > 0){
                for(int i = 0; i< 10-sellHouseAggAdLandingDos.size(); i++){
                    houseId_repair[i] = cpcSellHousesRepairs.get(i).getHouseId();
                }
            }else{
                Date beforeYesterday = DateUtil.beforeYesterday(new Date());
                String cpcbeforeYesterday = DateUtil.format(beforeYesterday);
                List<CpcSellHouse> cpcBeforeYesterdaySellHouseRepairs = cpcSellHouseMapper.selectByBeforeYesterdayRepair(cpcbeforeYesterday);
                if(null !=cpcBeforeYesterdaySellHouseRepairs && cpcBeforeYesterdaySellHouseRepairs.size() > 0){
                    for(int i = 0; i< 10-sellHouseAggAdLandingDos.size(); i++){
                        houseId_repair[i] = cpcBeforeYesterdaySellHouseRepairs.get(i).getHouseId();
                    }
                }
            }


            queryBuilder = QueryBuilders.idsQuery(projhouseType).addIds(houseId_repair);
            searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(queryBuilder).setFetchSource(returnField,null).execute().actionGet();

            SearchHit[] searchHits = searchResponse.getHits().getHits();
            String details = "";

            for(SearchHit hit : searchHits){
                details = hit.getSourceAsString();
                SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(details,SellHouseAggAdLandingDo.class);
                sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
                if(sellHouseAggAdLandingDos.size() == 10){
                    break;
                }
            }

        }

        sellHouseDomain.setSellHouseAggAdLandingDoList(sellHouseAggAdLandingDos);

        return sellHouseDomain;
    }

    @Override
    public SellHouseSearchDomain getSellHouseByPrice(AggAdLandingDo aggAdLandingDo) {

        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        SellHousesSearchDo  sellHousesSearchDo= new SellHousesSearchDo();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        Integer pageNum = 1;
        Integer pageSize = aggAdLandingDo.getPs();
        booleanQueryBuilder.mustNot(QueryBuilders.termsQuery("is_parent_claim", "1"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isRecommend",0));
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("price_increase_decline",aggAdLandingDo.getPriceID()));
        if(StringTool.isNotEmpty(aggAdLandingDo.getDays())){
            Date date = new Date();
            String nowDate = DateUtil.format(date)+" 00:00:00";
            String pastDate= DateUtil.getPastDate(aggAdLandingDo.getDays())+" 00:00:00";
            booleanQueryBuilder.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("claim_time").gt(pastDate).lte(nowDate)));
        }

        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);

        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(queryBuilder).boost(10).maxBoost(100)
                .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);

        List<SellHousesSearchDo> sellHousesSearchDos =new ArrayList<>();
        //ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query, null, null, aggAdLandingDo.getPn(),
                aggAdLandingDo.getPs(), null);

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        Date date = new Date();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details=searchHit.getSourceAsString();
            sellHousesSearchDo=JSON.parseObject(details,SellHousesSearchDo.class);
            ClaimSellHouseDo claimSellHouseDo=JSON.parseObject(details,ClaimSellHouseDo.class);
            if (null!=claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim()==1)
            {   //将认领信息替换
                sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
            }
            AgentBaseDo agentBaseDo = new AgentBaseDo();
            if(claimSellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())){
                agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString(),null);
                if(StringTool.isNotEmpty(searchHit.getSourceAsMap().get("price_increase_decline"))){
                    if(Integer.valueOf(searchHit.getSourceAsMap().get("price_increase_decline").toString())>0){
                        int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSourceAsMap().get("claim_time").toString()));
                        if(claimDays>=0 && claimDays<30){
                            sellHousesSearchDo.setHousePhotoTitleTags(Integer.valueOf(sellHousesSearchDo.getPriceIncreaseDecline()));
                        }
                    }else {
                        int importFlag = -1;
                        if(StringTool.isNotEmpty(searchHit.getSourceAsMap().get("import_time"))){
                            int importDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSourceAsMap().get("import_time").toString()));
                            if(importDays>=0 && importDays<7){
                                importFlag = 3;
                                sellHousesSearchDo.setHousePhotoTitleTags(importFlag);
                            }else{
                                sellHousesSearchDo.setHousePhotoTitleTags(importFlag);
                            }
                        }
                    }
                }



            }else{
                agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
                agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
                agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
            }
            sellHousesSearchDo.setAgentBaseDo(agentBaseDo);
            sellHousesSearchDos.add(sellHousesSearchDo);

        }
        sellHouseSearchDomain.setData(sellHousesSearchDos);
        sellHouseSearchDomain.setTotalNum((int)searchResponse.getHits().getTotalHits());


        return sellHouseSearchDomain;
    }


    public SearchResponse getRecommendSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize){
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic",
                "adSort","claimHouseId","claimHouseTitle","claimHousePhotoTitle","claimTagsName"};

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).addSort("adSort",SortOrder.ASC).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    /**
     * 出租推荐房源
     * @param booleanQueryBuilder
     * @param pageSize
     * @return
     */
    public SearchResponse getRecommendRentHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize){
        String[] returnField = new String[]{"area_name", "house_area", "room","forward", "room", "district_name", "area_name",
                "rent_type_name","rent_house_tags_name","rent_house_price","nearest_subway","adSort","house_id","rent_sign","zufang_name","house_title_img"};

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse = client.prepareSearch(recommendRentIndex).setTypes(recommendRentType)
                .setQuery(booleanQueryBuilder).addSort("adSort",SortOrder.ASC).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    /**
     * 出租录入房源
     * @param booleanQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    public SearchResponse getInputRentHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"area_name", "house_area", "room","forward", "room", "district_name", "area_name",
                "rent_type_name","rent_house_tags_name","rent_house_price","nearest_subway","adSort","house_id","rent_sign","zufang_name","house_title_img"};
        TransportClient client = esClientTools.init();

        //随机取返回的数据
        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        SearchResponse searchResponse = client.prepareSearch(recommendRentIndex).setTypes(recommendRentType)
                .setQuery(booleanQueryBuilder).addSort(scrip).setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    /**
     * 出租导入房源
     * @param booleanQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    public SearchResponse getImportRentHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        String[] returnField = new String[]{"area_name", "house_area", "room","forward", "room", "district_name", "area_name",
                "rent_type_name","rent_house_tags_name","rent_house_price","nearest_subway","adSort","house_id","rent_sign","zufang_name","house_title_img"};
        //随机取返回的数据
        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        SearchResponse searchResponse = client.prepareSearch(recommendRentIndex).setTypes(recommendRentType)
                .setQuery(booleanQueryBuilder).addSort(scrip).setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    public SearchResponse getClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic",
                "adSort","claimHouseId","claimHouseTitle","claimHousePhotoTitle","claimTagsName"};

        TransportClient client = esClientTools.init();

        //随机取返回的数据
        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).addSort(scrip).setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    public SearchResponse getUnClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic",
                "adSort","isClaim","claimHouseTitle","claimHousePhotoTitle","claimTags","claimTagsName","claimHouseId"};

        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).addSort(scrip).setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }


    @Override
    public Map<String, Object> advertisementCpc_1() {


        Map<String ,Object> advertisement =new HashedMap();
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder booleanQueryBuilder1 = QueryBuilders.boolQuery();
        BoolQueryBuilder booleanQueryBuilder2 = QueryBuilders.boolQuery();

        //价格300到500万
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder.must(termsQuery("isDel", "0"));
        //价格300到500万
        booleanQueryBuilder1.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder1.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder1.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder1.must(termsQuery("isDel", "0"));
        //价格300到500万
        booleanQueryBuilder2.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder2.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder2.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder2.must(termsQuery("isDel", "0"));
        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).setQuery(booleanQueryBuilder.must(termQuery("of_company", "我爱我家")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company","traffic"},
                        null)
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder1).setQuery(booleanQueryBuilder1.must(termQuery("of_company", "中原地产")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company","traffic"},
                        null)
                .execute().actionGet();

        SearchHits hit1 = searchresponse.getHits();
        SearchHit[] searchHist1 = hit1.getHits();

        for (SearchHit hit : searchHist1) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder2).setQuery(booleanQueryBuilder2.must(termQuery("of_company", "麦田房产")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company","traffic"},
                        null)
                .execute().actionGet();

        SearchHits hit2 = searchresponse.getHits();
        SearchHit[] searchHist2 = hit2.getHits();

        for (SearchHit hit : searchHist2) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }


        Collections.shuffle(buildinglist);

        advertisement.put("data",buildinglist);

        return advertisement;



    }
}
