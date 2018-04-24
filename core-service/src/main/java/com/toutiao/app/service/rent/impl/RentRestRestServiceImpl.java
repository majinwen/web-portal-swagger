package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.rent.RentAgentDo;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class RentRestRestServiceImpl implements RentRestService {
    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 0;//出租:1
    private static final Integer FOCUS_APARTMENT = 2;//公寓:2
    private static final Integer DISPERSED_APARTMENTS = 1;//公寓:2
    private static final String LAYOUT = "3";

    @Autowired
    private RentEsDao rentEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;

    /**
     * 租房详情信息
     * @param rentId
     * @return
     */
    @Override
    public RentDetailsDo queryRentDetailByHouseId(String rentId) {
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("house_id",rentId));
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del",IS_DEL));
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status",RELEASE_STATUS));
            SearchResponse searchResponse = rentEsDao.queryRentByRentId(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            String details = "";
            RentDetailsDo rentDetailsDo = new RentDetailsDo();
            for (SearchHit searchHit : hits) {
                details = searchHit.getSourceAsString();
            }
            if (org.apache.commons.lang.StringUtils.isNotEmpty(details)) {
                rentDetailsDo = JSON.parseObject(details, RentDetailsDo.class);
            }
            return rentDetailsDo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据小区id查询该小区下的出租房源
     * @param plotId
     * @return
     */
    @Override
    public List<RentDetailsFewDo> queryRentListByPlotId(Integer plotId,Integer rentType,Integer pageNum) {
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
        if (StringTool.isNotEmpty(rentType)){
            boolQueryBuilder.must(QueryBuilders.termQuery("rent_type",rentType));
        }
        Integer from = (pageNum-1)*10;
        SearchResponse searchResponse = rentEsDao.queryRentListByPlotId(boolQueryBuilder,from);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                rentDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                list.add(rentDetailsFewDo);
            }
        }
        return list;
    }

    /**
     * 根据出租房源的id查询该小区下的出租房源的个数
     * @param plotId
     * @return
     */
    @Override
    public List<RentNumDo> queryRentNumByPlotId(Integer plotId) {
        List<RentNumDo> list = new ArrayList<>();
        RentNumDo rentNumDo = new RentNumDo();
        RentNumDo rentNumDo2 = new RentNumDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
        SearchResponse searchResponse = rentEsDao.queryRentNumByPlotId(boolQueryBuilder);
        long zhengzu = ((InternalFilter) searchResponse.getAggregations().get("ZHENGZU")).getDocCount();
        long hezu = ((InternalFilter) searchResponse.getAggregations().get("HEZU")).getDocCount();
        rentNumDo.setTotalNum((int) zhengzu);
        rentNumDo.setRent_sign(1);
        rentNumDo.setRent_sign_name("整租");
        list.add(rentNumDo);
        rentNumDo2.setTotalNum((int) hezu);
        rentNumDo2.setRent_sign(2);
        rentNumDo2.setRent_sign_name("合租");
        list.add(rentNumDo2);
        return list;
    }

    /**
     * 根据出租房源的id查询相关的经纪人
     * @param rentId
     * @return
     */
    @Override
    public RentAgentDo queryRentAgentByRentId(String rentId) {
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",rentId));
            SearchResponse searchResponse = agentHouseEsDao.getAgentRentByRentId(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                long time = System.currentTimeMillis();
                long index = (time / 600000) % hits.length;
                String sourceAsString = hits[(int) index].getSourceAsString();
                RentAgentDo rentAgentDo = JSON.parseObject(sourceAsString, RentAgentDo.class);
                return rentAgentDo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 附近5km内出租房源(规则:app的是吧，那就优先三公里的录入房源由近到远)
     * @param nearHouseDo
     * @return
     */
    @Override
    public List<RentDetailsFewDo> queryNearHouseByLocation(NearHouseDo nearHouseDo) {
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //从该坐标查询距离为5000内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(nearHouseDo.getLat(), nearHouseDo.getLon()).distance(nearHouseDo.getDistance(), DistanceUnit.METERS);
        //按照距离排序由近到远并获取小区之间的距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearHouseDo.getLat(), nearHouseDo.getLon());
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        Integer size = 10;
        Integer from = (nearHouseDo.getPageNum()-1)*size;
        SearchResponse searchResponse = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(boolQueryBuilder,nearHouseDo), location, sort, from, size);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                rentDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                list.add(rentDetailsFewDo);
            }
        }
        if (hits.length>0&&hits.length<10){
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
            nearHouseDo.setRentHouseType(3);
            long From = ((nearHouseDo.getPageNum() - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(booleanQueryBuilder,nearHouseDo), location, sort, (int) From,size-hits.length);
            SearchHit[] hits1 = response.getHits().getHits();
            if (hits1.length>0){
                for (SearchHit hit:hits1){
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    rentDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits()+(int)response.getHits().getTotalHits());
                    list.add(rentDetailsFewDo);
                }
            }
        }else if (hits.length==0){
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
            nearHouseDo.setRentHouseType(3);
            long From = ((nearHouseDo.getPageNum() - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(getBoolQueryBuilder(booleanQueryBuilder,nearHouseDo), location, sort, (int) From,size);
            SearchHit[] hits1 = response.getHits().getHits();
            if (hits1.length>0){
                for (SearchHit hit:hits1){
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    rentDetailsFewDo.setTotalNum((int)response.getHits().getTotalHits());
                    list.add(rentDetailsFewDo);
                }
            }
        }
        return list;
    }

    /**
     * 获取boolQueryBuilder
     * @param boolQueryBuilder
     * @param nearHouseDo
     * @return
     */
    public BoolQueryBuilder getBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,NearHouseDo nearHouseDo){

        //关键字
        if (StringTool.isNotEmpty(nearHouseDo.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(nearHouseDo.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(nearHouseDo.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()).analyzer("ik_smart").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", nearHouseDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area_name_search", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("district_name_search", nearHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("zufang_name_search", nearHouseDo.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //城市
        if (StringTool.isNotEmpty(nearHouseDo.getCityId())){
            boolQueryBuilder.must(termQuery("city_id", nearHouseDo.getCityId()));
        }
        //区域
        if (StringTool.isNotEmpty(nearHouseDo.getDistrictId())){
            boolQueryBuilder.must(termQuery("district_id",nearHouseDo.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(nearHouseDo.getAreaId())){
            boolQueryBuilder.must(termQuery("area_id", nearHouseDo.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(nearHouseDo.getSubwayLineId())){
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{nearHouseDo.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(nearHouseDo.getSubwayStationId())){
            boolQueryBuilder.must(termsQuery("subway_station_id", new int[]{nearHouseDo.getSubwayStationId()}));
        }
        //租金
        if (StringTool.isNotEmpty(nearHouseDo.getBeginPrice())&&StringTool.isNotEmpty(nearHouseDo.getEndPrice())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(nearHouseDo.getBeginPrice()).lte(nearHouseDo.getEndPrice()));
        }
        //面积
        if (StringTool.isNotEmpty(nearHouseDo.getRentHouseArea())){
            String area = nearHouseDo.getRentHouseArea().replaceAll("\\[","")
                    .replaceAll("]","").replaceAll("-",",");
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
        if (StringTool.isNotEmpty(nearHouseDo.getSource())){
            String[] source = nearHouseDo.getSource().split(",");
            boolQueryBuilder.must(termsQuery("data_source_sign",source));
        }
        //朝向
        if (StringTool.isNotEmpty(nearHouseDo.getForward())){
            String[] forword = nearHouseDo.getForward().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }
        //整租/合租
        if (StringTool.isNotEmpty(nearHouseDo.getRentType())){
            String[] split = nearHouseDo.getRentType().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
        }
        //几居
        if (StringTool.isNotEmpty(nearHouseDo.getRoom())){
            String[] split = nearHouseDo.getRoom().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
        }
        //标签
        if (StringTool.isNotEmpty(nearHouseDo.getTags())){
            String[] split = nearHouseDo.getTags().split(",");
            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", split));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",nearHouseDo.getRentHouseType()));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));
        return boolQueryBuilder;
    }
}
