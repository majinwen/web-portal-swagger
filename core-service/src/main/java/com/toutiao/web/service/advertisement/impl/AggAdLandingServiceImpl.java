package com.toutiao.web.service.advertisement.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.RedisSessionUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.advertisement.AggAdLandingDao;
import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.domain.advertisement.SellHouseAggAdLandingDo;
import com.toutiao.web.domain.advertisement.SellHouseDomain;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
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

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

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
    @Value("${distance}")
    private Double distance;
    @Autowired
    private RedisSessionUtils redisSessionUtils;
    @Autowired
    private AggAdLandingDao aggAdLandingDao;

    private static final String RECOMMEND_DEFAULT = "ad_recommend_sellHouse_queryBit";
    private static final String RECOMMEND_SUBWAY = "ad_recommend_sellHouse_queryBit_subway";
    private static final String RECOMMEND_TOTAL_PRICE = "ad_recommend_sellHouse_queryBit_totalprice";
    private static final String RECOMMEND_LAYOUT = "ad_recommend_sellHouse_queryBit_layout";
    private static final String RECOMMEND_MANSION = "ad_recommend_sellHouse_queryBit_mansion";
    private static final String RECOMMEND_DISTRICT = "ad_recommend_sellHouse_queryBit_district";



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
            redisCatogory = RECOMMEND_DISTRICT;
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", aggAdLandingDo.getDistrict()));
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));//isRecommend大于0，都是推荐房源

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

        if(query_size==0L){
            sellHouseDomain.setSign(1);
        }else{
            sellHouseDomain =  getRecommendHits(aggAdLandingDo,searchResponse);
        }


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
                source_start =Long.parseLong(hit.getSource().get("adSort").toString());
            }
            if((i+1)==searchHists.length){
                source_end =Long.parseLong(hit.getSource().get("adSort").toString());
            }
            detail = hit.getSourceAsString();
            SellHouseAggAdLandingDo sellHouseAggAdLandingDo = JSON.parseObject(detail,SellHouseAggAdLandingDo.class);
            sellHouseAggAdLandingDos.add(sellHouseAggAdLandingDo);
        }

        //把前台的传过来的查询条件返回回去
        //1.获取List对象中adSort中的最大一条的记录
        SellHouseAggAdLandingDo sellHouseAggAdLandingDo = sellHouseAggAdLandingDos.get(sellHouseAggAdLandingDos.size()-1);

        sellHouseDomain.setSourceStart(source_start);
        sellHouseDomain.setSourceEnd(source_end);
        //3.封装数据返回
        sellHouseDomain.setQueryBit(sellHouseAggAdLandingDo.getAdSort());
//        sellHouseDomain.setStartBit(startBit);
        sellHouseDomain.setSellHouseAggAdLandingDoList(sellHouseAggAdLandingDos);
        return sellHouseDomain;
    }

    /**
     * 认领房源
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
     * 经纪公司导入
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


        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("isClaim",0));

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




    public SearchResponse getRecommendSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize){
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).addSort("adSort",SortOrder.ASC).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    public SearchResponse getClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    public SearchResponse getUnClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).setFrom((pageNum-1)*pageSize).setSize(pageSize)
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
