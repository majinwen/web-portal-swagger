package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.app.domain.sellhouse.ClaimSellHouseDo;
import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
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
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NearSellHouseRestServiceImpl implements NearSellHouseRestService{

    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;

    @Autowired
    private NearbySellHouseEsDao nearbySellHouseEsDao;

    @Override
    public NearBySellHouseDomain getSellHouseByHouseIdAndLocation(NearBySellHouseQueryDo nearBySellHouseQueryDo) {

        NearBySellHouseDomain nearBySellHouseDomain=new NearBySellHouseDomain();
        NearBySellHousesDo  nearBySellHousesDo=new NearBySellHousesDo();
        //过滤附近5km
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                .point(nearBySellHouseQueryDo.getLat(), nearBySellHouseQueryDo.getLon())
                .distance(nearBySellHouseQueryDo.getDistance(), DistanceUnit.KILOMETERS);

        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterChoose(nearBySellHouseQueryDo);


        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.mustNot(QueryBuilders.termsQuery("is_parent_claim", "1"));
        booleanQueryBuilder.must(location);
        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

        Map<String,Double> map = new HashMap<>();
        map.put("lat",nearBySellHouseQueryDo.getLat());
        map.put("lon",nearBySellHouseQueryDo.getLon());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        //设置高斯函数
        GaussDecayFunctionBuilder functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("housePlotLocation",json,"1km","1km" );
        //获取5km内所有的二手房
        FunctionScoreQueryBuilder query5kmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);
        if (StringUtil.isNotNullString(nearBySellHouseQueryDo.getKeyword())) {
            List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(nearBySellHouseQueryDo.getKeyword());
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()+1];
            if (StringUtil.isNotNullString(AreaMap.getAreas(nearBySellHouseQueryDo.getKeyword()))) {
                for(int i=0 ;i<searchKeyword.size();i++){
                    int searchAreasSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize-i);
                    QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHouseQueryDo.getKeyword()))) {
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchDistrictsSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else{
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchTermSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }
            filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
             query = QueryBuilders.functionScoreQuery(query5kmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                    .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        }else{
             query = QueryBuilders.functionScoreQuery(query5kmBuilder,functionBuilder).boost(10).maxBoost(100)
                    .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        }
        List<NearBySellHousesDo> nearBySellHouses =new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        SearchResponse searchResponse = nearbySellHouseEsDao.getNearbySellHouseByFilter(query,nearBySellHouseQueryDo.getPageNum(),nearBySellHouseQueryDo.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details=searchHit.getSourceAsString();
            nearBySellHousesDo=JSON.parseObject(details,NearBySellHousesDo.class);
            claimSellHouseDo=JSON.parseObject(details,ClaimSellHouseDo.class);
            if (null!=claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim()==1)
            {   //将认领信息替换
                nearBySellHousesDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                nearBySellHousesDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                nearBySellHousesDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                nearBySellHousesDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
            }
            nearBySellHouses.add(nearBySellHousesDo);
            //增加地铁站与房源的距离
            String keys="";
            if(null!=nearBySellHouseQueryDo.getSubwayLineId())
            {
                 keys+=nearBySellHouseQueryDo.getSubwayLineId().toString();
            }
            if (null!=nearBySellHouseQueryDo.getSubwayStationId())
            {
                keys+= "$"+nearBySellHouseQueryDo.getSubwayStationId();
            }
           if (!"".equals(keys) && null!=nearBySellHousesDo.getSubwayDistince())
           {
               nearBySellHousesDo.setSubwayDistanceInfo(nearBySellHousesDo.getSubwayDistince().get(keys).toString());
           }
        }
        nearBySellHouseDomain.setNearBySellHousesDos(nearBySellHouses);
        nearBySellHouseDomain.setTotalCount(searchResponse.getHits().getTotalHits());

        return nearBySellHouseDomain;
    }

}
