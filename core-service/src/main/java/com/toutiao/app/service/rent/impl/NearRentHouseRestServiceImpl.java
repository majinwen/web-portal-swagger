package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.rent.NearHouseListDoQuery;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class NearRentHouseRestServiceImpl implements NearRentHouseRestService {

    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;
    @Autowired
    private SellHouseKeywordEsDao sellHouseKeywordEsDao;
    @Autowired
    private RentEsDao rentEsDao;
    @Autowired
    private AgentService agentService;

    @Override
    public RentDetailsListDo queryNearHouseByLocation(NearHouseListDoQuery nearHouseListDoQuery, String city) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //从该坐标过滤距离为5000内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(nearHouseListDoQuery.getLat(), nearHouseListDoQuery.getLon()).distance(nearHouseListDoQuery.getDistance(), DistanceUnit.KILOMETERS).boost(0);

        //添加筛选条件
        BoolQueryBuilder booleanQueryBuilder = getBoolQueryBuilder(boolQueryBuilder, nearHouseListDoQuery);
        booleanQueryBuilder.must(location);

        //设置基础分(录入优先展示)(录入:1,导入1/3)
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("rentHouseTypeId")
                .modifier(FieldValueFactorFunction.Modifier.RECIPROCAL).missing(10);

        //坐标
        Map<String,Double> map = new HashMap<>();
        map.put("lat",nearHouseListDoQuery.getLat());
        map.put("lon",nearHouseListDoQuery.getLon());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));

        //设置高斯函数(要保证5km内录入的排在导入的前面,录入房源的最低分需要大于导入的最高分)
        GaussDecayFunctionBuilder functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location",json,"4km","1km" ,0.4);

        //获取5km内的所有出租房源(函数得分进行加法运算,查询得分和函数得分进行加法运算)
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor).scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);

        FunctionScoreQueryBuilder query = null;

        if (StringUtil.isNotNullString(nearHouseListDoQuery.getKeyword())) {
            List<String> searchKeyword = nearRentHouseRestService.getAnalyzeByKeyWords(nearHouseListDoQuery.getKeyword());
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()+1];
            if (StringUtil.isNotNullString(AreaMap.getAreas(nearHouseListDoQuery.getKeyword()))) {
                for(int i=0 ;i<searchKeyword.size();i++){
                    int searchAreasSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize-i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area_name",searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearHouseListDoQuery.getKeyword()))) {
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchDistrictsSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("district_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else{
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchTermSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("zufang_name_search", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }

            filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);

            query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
        }else{
            query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder,functionBuilder).scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
        }

        RentDetailsListDo rentDetailsListDo = new RentDetailsListDo();
        List<RentDetailsFewDo> rentDetailsFewDos = new ArrayList<>();
        SearchResponse searchResponse = rentEsDao.queryNearRentHouse(query, (nearHouseListDoQuery.getPageNum() - 1) * 10, city);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                //增加地铁与房子之间的距离
                String keys="";
                if(null!=nearHouseListDoQuery.getSubwayLineId())
                {
                    keys+=nearHouseListDoQuery.getSubwayLineId().toString();
                }
                if (null!=nearHouseListDoQuery.getSubwayStationId())
                {
                    keys+= "$"+nearHouseListDoQuery.getSubwayStationId();
                }
                if (!"".equals(keys) && null!=rentDetailsFewDo.getNearbySubway())
                {
                    rentDetailsFewDo.setSubwayDistanceInfo(rentDetailsFewDo.getNearbySubway().get(keys).toString());
                }

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(StringTool.isNotEmpty(rentDetailsFewDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(rentDetailsFewDo.getUserId().toString(), city);

                }else{
                    agentBaseDo.setAgentCompany(hit.getSource().get("brokerage_agency").toString());
                    agentBaseDo.setAgentName(hit.getSource().get("estate_agent").toString());
                    agentBaseDo.setDisplayPhone(hit.getSource().get("phone").toString());
                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("agent_headphoto")==null?"":hit.getSourceAsMap().get("agent_headphoto").toString());

                }
                rentDetailsFewDo.setAgentBaseDo(agentBaseDo);
                rentDetailsFewDos.add(rentDetailsFewDo);
            }
        }
        rentDetailsListDo.setRentDetailsList(rentDetailsFewDos);
        rentDetailsListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return rentDetailsListDo;
    }

    /**
     * 获取关键字分词
     * @param keywords
     * @return
     */
    @Override
    public List<String> getAnalyzeByKeyWords(String keywords) {
        List<String> strings = sellHouseKeywordEsDao.filterKeyWords(keywords);
        return strings;
    }


    /**
     * 获取boolQueryBuilder
     * @param boolQueryBuilder
     * @param nearHouseListDoQuery
     * @return
     */
    public BoolQueryBuilder getBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,NearHouseListDoQuery nearHouseListDoQuery){

        //关键字
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(nearHouseListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(nearHouseListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseListDoQuery.getKeyword()).analyzer("ik_smart").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseListDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseListDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //城市
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getCityId())){
            boolQueryBuilder.must(termQuery("city_id", nearHouseListDoQuery.getCityId()).boost(0));
        }
        //区域
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getDistrictId())){
            boolQueryBuilder.must(termQuery("district_id",nearHouseListDoQuery.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getAreaId())){
            boolQueryBuilder.must(termQuery("area_id", nearHouseListDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getSubwayLineId())){
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{nearHouseListDoQuery.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getSubwayStationId())){
            boolQueryBuilder.must(termsQuery("subway_station_id", new int[]{nearHouseListDoQuery.getSubwayStationId()}));
        }
        //租金
        if (nearHouseListDoQuery.getBeginPrice()!=0 &&nearHouseListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(nearHouseListDoQuery.getBeginPrice()).lte(nearHouseListDoQuery.getEndPrice()));
        }else if(nearHouseListDoQuery.getBeginPrice()!=0 && nearHouseListDoQuery.getEndPrice()==0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(nearHouseListDoQuery.getBeginPrice()));
        }else if(nearHouseListDoQuery.getBeginPrice()!=0&& nearHouseListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").lte(nearHouseListDoQuery.getEndPrice()));
        }
        //面积
//        if (StringTool.isNotEmpty(nearHouseListDoQuery.getRentHouseArea())){
//            String area = nearHouseListDoQuery.getRentHouseArea().replaceAll("\\[","")
//                    .replaceAll("]","").replaceAll("-",",");
//            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            String[] layoutId = area.split(",");
//            for (int i = 0; i < layoutId.length; i = i + 2) {
//                if (i + 1 > layoutId.length) {
//                    break;
//                }
//                booleanQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
//                boolQueryBuilder.must(booleanQueryBuilder);
//            }
//        }

        //房源面积大小
        if(nearHouseListDoQuery.getBeginArea()!=0 && nearHouseListDoQuery.getEndArea()!=0){
            boolQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(nearHouseListDoQuery.getBeginArea()).lte(nearHouseListDoQuery.getBeginArea()));
        }else if(nearHouseListDoQuery.getBeginArea()!=0 && nearHouseListDoQuery.getEndArea()==0){
            boolQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(nearHouseListDoQuery.getBeginArea()));
        }else if(nearHouseListDoQuery.getBeginArea()==0 && nearHouseListDoQuery.getEndArea()!=0){
            boolQueryBuilder.should(QueryBuilders.rangeQuery("house_area").lte(nearHouseListDoQuery.getBeginArea()));
        }

        //来源
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getSource())){
            boolQueryBuilder.must(termsQuery("data_source_sign",nearHouseListDoQuery.getSource()));
        }
        //朝向
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getForwardId())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", nearHouseListDoQuery.getForwardId()));
        }
        //整租/合租
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getRentType())){
            String[] split = nearHouseListDoQuery.getRentType().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
        }
        //几居
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getLayoutId())){
            boolQueryBuilder.must(QueryBuilders.termsQuery("room", nearHouseListDoQuery.getLayoutId()));
        }
        //标签
        if (StringTool.isNotEmpty(nearHouseListDoQuery.getLabelId())){
            Integer[] labelId = nearHouseListDoQuery.getLabelId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", labelId));
            for (Integer i:labelId){
                if (i==1){
                    boolQueryBuilder.must(QueryBuilders.termQuery("has_subway", 1));
                }
            }
        }

        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0).boost(0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1).boost(0));
        return boolQueryBuilder;
    }

}
