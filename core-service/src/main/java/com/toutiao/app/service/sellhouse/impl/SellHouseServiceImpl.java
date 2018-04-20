package com.toutiao.app.service.sellhouse.impl;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.ProjHouseInfoResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;

    @Override
    public SellHouseDetailsDo getSellHouseByHouseId(Integer houseId) {

        //二手房房源详情
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        SellHouseDetailsDo sellHouseDetailsDo = JSON.parseObject(details,SellHouseDetailsDo.class);
        return sellHouseDetailsDo;
    }

    /**
     * 二手房附近列表
     * @param newcode
     * @param lat
     * @param lon
     * @param distance
     * @return
     */
    public List<NearBySellHousesDo> getSellHouseByHouseIdAndLocation(NearBySellHousesDo nearBySellHousesDo) {

        SearchResponse searchresponse = null;
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
        String key = null;
        //关键字搜索
        List<String> searchDistrictsList = new ArrayList<>();
        List<String> searchAreasList = new ArrayList<>();
        List<String> searchTermList = new ArrayList<>();
        if (StringTool.isNotBlank(nearBySellHousesDo.getKeyword()))
        {
            //区域关键词
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHousesDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart")));
                AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE,projhouseIndex,nearBySellHousesDo.getKeyword());
            }

        }






    }

    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            long time = new Date().getTime();
            long index = (time / 600000) % hits.length;
            String details = hits[(int) index].getSourceAsString();
            AgentsBySellHouseDo agentsBySellHouseDo = JSON.parseObject(details,AgentsBySellHouseDo.class);
            return agentsBySellHouseDo;
        }else{
            return null;
        }
    }

    @Override
    public ChooseSellHouseDomain getSellHouseByChoose(ChooseSellHouseDo chooseSellHouseDo) {

        List<SellHouseDo> sellHouseDos = new ArrayList<>();

        ChooseSellHouseDomain chooseSellHouseDomain = new ChooseSellHouseDomain();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

        if (StringTool.isNotBlank(chooseSellHouseDo.getKeyWord())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(chooseSellHouseDo.getKeyWord()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyWord()))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyWord()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyWord()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyWord()).analyzer("ik_smart")));

            } else if (StringUtil.isNotNullString(AreaMap.getAreas(chooseSellHouseDo.getKeyWord()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyWord()))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyWord()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyWord()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyWord()).analyzer("ik_smart").boost(2)));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyWord()).boost(2))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyWord()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyWord()))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyWord())));
            }
        }

        //商圈名称
        if (StringTool.isNotEmpty(chooseSellHouseDo.getHouseBusinessName())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", chooseSellHouseDo.getHouseBusinessName()));
        }
        //商圈id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", chooseSellHouseDo.getAreaId()));

        }
        //小区id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getNewCode())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", chooseSellHouseDo.getNewCode()));

        }
        //房源id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getHouseId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", chooseSellHouseDo.getHouseId()));

        }
        //区域id
        if (StringTool.isNotEmpty((chooseSellHouseDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", chooseSellHouseDo.getDistrictId()));

        }
        //区域的名称
        if (StringTool.isNotEmpty((chooseSellHouseDo.getArea()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("area", chooseSellHouseDo.getArea()));

        }
        String key = null;
        //地铁线id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", chooseSellHouseDo.getSubwayLineId()));
            key = chooseSellHouseDo.getSubwayLineId();
        }
        //地铁站id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", chooseSellHouseDo.getSubwayStationId()));
            key = chooseSellHouseDo.getSubwayLineId() + "$" + chooseSellHouseDo.getSubwayStationId();
        }

        if (StringTool.isNotEmpty(chooseSellHouseDo.getBeginPrice()) && StringTool.isNotEmpty(chooseSellHouseDo.getEndPrice())) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(chooseSellHouseDo.getBeginPrice()).lte(chooseSellHouseDo.getEndPrice())));

        }
        //面积
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseAreaSize())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String area = chooseSellHouseDo.getHouseAreaSize().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");

            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //楼龄
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseYearId())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String houseyear = chooseSellHouseDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");

            String[] layoutId = houseyear.split(",");
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
        if (StringUtil.isNotNullString(chooseSellHouseDo.getLayoutId())) {
            String[] layoutId = chooseSellHouseDo.getLayoutId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
        }
        //物业类型
        if (StringUtil.isNotNullString(chooseSellHouseDo.getPropertyTypeId())) {
            String[] layoutId = chooseSellHouseDo.getPropertyTypeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseType", layoutId));
        }
        //建筑类型
        if (StringUtil.isNotNullString(chooseSellHouseDo.getBuildingTypeId())) {
            String[] layoutId = chooseSellHouseDo.getBuildingTypeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("buildCategory", layoutId));
        }

        //朝向
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseOrientationId())) {
            String[] layoutId = chooseSellHouseDo.getHouseOrientationId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("forward", layoutId));
        }

        //电梯
        if (StringTool.isNotEmpty(chooseSellHouseDo.getElevatorFlag())) {
            String[] layoutId = chooseSellHouseDo.getElevatorFlag().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("elevator", layoutId));
        }
        //标签(满二，满三，满五)
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseLabelId())) {
            String[] layoutId = chooseSellHouseDo.getHouseLabelId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", layoutId));
        }

        //楼层
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseFloorId())) {
            String[] layoutId = chooseSellHouseDo.getHouseFloorId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("floor", layoutId));

        }
        //权属
        if (StringUtil.isNotNullString(chooseSellHouseDo.getOwnership())) {
            String[] layoutId = chooseSellHouseDo.getOwnership().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("propertyRight", layoutId));

        }
        //按距离排序
        GeoDistanceQueryBuilder location = null;
        GeoDistanceSortBuilder sort = null;
        if (StringUtils.isNotBlank(chooseSellHouseDo.getNear())){
            if(chooseSellHouseDo.getLat()!=0 && chooseSellHouseDo.getLon()!=0){
                 location = QueryBuilders.geoDistanceQuery("housePlotLocation").
                        point(chooseSellHouseDo.getLat(), chooseSellHouseDo.getLon()).distance(chooseSellHouseDo.getNear(), DistanceUnit.KILOMETERS);
                sort = SortBuilders.geoDistanceSort("housePlotLocation", chooseSellHouseDo.getLat(), chooseSellHouseDo.getLon());
                sort.unit(DistanceUnit.KILOMETERS);
                sort.order(SortOrder.ASC);
            }
        }
        //去未删除的房源信息
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        int pageNum = 1;

        if(chooseSellHouseDo.getPageNum()!=null && chooseSellHouseDo.getPageNum()>1){
            pageNum = chooseSellHouseDo.getPageNum();
        }
        SearchResponse searchresponse  = sellHouseEsDao.getSellHouseByChoose(booleanQueryBuilder, location, sort, chooseSellHouseDo.getKeyWord(),
                chooseSellHouseDo.getSort(),chooseSellHouseDo.getPageSize(),pageNum);

        SearchHits hits = searchresponse.getHits();
        long totalHits = hits.totalHits;
        SearchHit[] searchHists = hits.getHits();
        String details;
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
            SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);
            sellHouseDo.setSubwayKeys(key);
            sellHouseDos.add(sellHouseDo);
        }
        chooseSellHouseDomain.setSellHouseList(sellHouseDos);
        chooseSellHouseDomain.setTotal(totalHits);

        return chooseSellHouseDomain;
    }

}
