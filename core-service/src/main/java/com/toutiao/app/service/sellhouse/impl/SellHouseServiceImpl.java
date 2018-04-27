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
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;

    @Override
    public SellAndClaimHouseDetailsDo getSellHouseByHouseId(String houseId) {

        //二手房房源详情
        SellAndClaimHouseDetailsDo sellAndClaimHouseDetailsDo = new SellAndClaimHouseDetailsDo();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if ("FS".equals(houseId.substring(0,2))){
            booleanQueryBuilder.must(QueryBuilders.termQuery("claimHouseId", houseId));
        }else {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        }
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        if (searchHists.length>0){
            for (SearchHit searchHit : searchHists) {
                details = searchHit.getSourceAsString();
            }
            sellAndClaimHouseDetailsDo = JSON.parseObject(details,SellAndClaimHouseDetailsDo.class);
        }
        return sellAndClaimHouseDetailsDo;
    }

    /**
     * 附近房源列表列表
     * @return
     */
    @Override
    public NearBySellHouseDomain getSellHouseByHouseIdAndLocation(NearBySellHousesDo nearBySellHousesDo) {
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
        List<NearBySellHousesDo> nearBySellHouses =new ArrayList<>();
        NearBySellHouseDomain nearBySellHouseDomain=new NearBySellHouseDomain();
        ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        NearBySellHousesDo search=new NearBySellHousesDo();
        BeanUtils.copyProperties(nearBySellHousesDo,search);
        long count=0;
        //增加搜索框
        addSearch(booleanQueryBuilder,search);
        //从该坐标查询距离为5000内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation").point(nearBySellHousesDo.getLat(), nearBySellHousesDo.getLon()).distance(nearBySellHousesDo.getDistance(), DistanceUnit.KILOMETERS);
        //按照距离排序由近到远并获取小区之间的距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("housePlotLocation", nearBySellHousesDo.getLat(), nearBySellHousesDo.getLon());
        sort.unit(DistanceUnit.KILOMETERS);
        sort.order(SortOrder.ASC);
        Integer size = 10;
        Integer pageNum=nearBySellHousesDo.getPageNum();
        Integer from = (pageNum-1)*size;
        //先查找总量
        SearchResponse  searchResponsecount= sellHouseEsDao.getSellHouseByHouseIdAndLocation(booleanQueryBuilder, location, sort, from,size);
        count= searchResponsecount.getHits().getTotalHits();
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",1));
        addSearch(booleanQueryBuilder,search);
        SearchResponse  searchResponse= sellHouseEsDao.getSellHouseByHouseIdAndLocation(booleanQueryBuilder, location, sort, from,size);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length>0) {
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
                }
                nearBySellHouses.add(nearBySellHousesDo);
            }
        }
        if(searchHists.length>0&&searchHists.length<10)
        {
            BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();//声明符合查询方法
            booleanQuery.must(QueryBuilders.termsQuery("isDel", "0"));
            booleanQuery.must(QueryBuilders.termQuery("is_claim",0));
            addSearch(booleanQuery,search);
            long From = ((pageNum - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse  response= sellHouseEsDao.getSellHouseByHouseIdAndLocation(booleanQuery, location, sort, (int) From,size-searchHists.length);
            SearchHit[] hits1 = response.getHits().getHits();
            for (SearchHit hit : hits1) {
                String details = "";
                details=hit.getSourceAsString();
                nearBySellHousesDo=JSON.parseObject(details,NearBySellHousesDo.class);
                nearBySellHouses.add(nearBySellHousesDo);
            }
        }
        if (searchHists.length==0)
        {
            BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();//声明符合查询方法
            booleanQuery.must(QueryBuilders.termsQuery("isDel", "0"));
            booleanQuery.must(QueryBuilders.termQuery("is_claim",0));
            addSearch(booleanQuery,search);
            long From = ((pageNum - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse  response= sellHouseEsDao.getSellHouseByHouseIdAndLocation(booleanQuery, location, sort, (int) From,size);
            SearchHit[] hits1 = response.getHits().getHits();
            for (SearchHit hit : hits1) {
                String details = "";
                details=hit.getSourceAsString();
                nearBySellHousesDo=JSON.parseObject(details,NearBySellHousesDo.class);
                nearBySellHouses.add(nearBySellHousesDo);
            }
        }
        nearBySellHouseDomain.setNearBySellHousesDos(nearBySellHouses);
        nearBySellHouseDomain.setTotalCount(count);
        return nearBySellHouseDomain;
    }

    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    @Override
    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            long time = System.currentTimeMillis();
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


    /**
     * 增加搜索框搜索
     */
    private void addSearch(BoolQueryBuilder booleanQueryBuilder ,  NearBySellHousesDo nearBySellHousesDo)
    {
        if (StringTool.isNotBlank(nearBySellHousesDo.getKeyword())) {

            if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHousesDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName",nearBySellHousesDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart")));

            } else if (StringUtil.isNotNullString(AreaMap.getAreas(nearBySellHousesDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart").boost(2)));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword())));
            }
        }
        //商圈id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", nearBySellHousesDo.getAreaId()));
        }
        //区域id
        if (StringTool.isNotEmpty((nearBySellHousesDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", nearBySellHousesDo.getDistrictId()));

        }
        //地铁线id
        String key = null;
        if (StringTool.isNotEmpty(nearBySellHousesDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", new int[] {nearBySellHousesDo.getSubwayLineId()}));
            key = nearBySellHousesDo.getSubwayLineId().toString();
        }

        //地铁站id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", new int[] {nearBySellHousesDo.getSubwayStationId()}));
        }

        //面积
        if(nearBySellHousesDo.getBeginArea()!=null && nearBySellHousesDo.getEndArea()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHousesDo.getBeginArea())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("buildArea").lte(nearBySellHousesDo.getEndArea())));
        }

        //总价
        if(nearBySellHousesDo.getBeginPrice()!=null &&nearBySellHousesDo.getEndPrice()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(nearBySellHousesDo.getBeginPrice()).lte(nearBySellHousesDo.getEndPrice())));
        }

        //户型(室)
        if (StringTool.isNotEmpty(nearBySellHousesDo.getLayoutId())) {
            Integer[] layoutId =nearBySellHousesDo.getLayoutId();
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
        }

        //朝向
        if (StringTool.isNotEmpty(nearBySellHousesDo.getForwardId())) {
            Integer [] forward =nearBySellHousesDo.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.termsQuery("forward", forward));
        }

        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(nearBySellHousesDo.getLabelId())) {
            Integer[] houseLabelId = nearBySellHousesDo.getLabelId();
            booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", houseLabelId));
        }

        //楼龄
        if (StringUtil.isNotNullString(nearBySellHousesDo.getHouseYearId())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String houseyear = nearBySellHousesDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");

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

    }

}
