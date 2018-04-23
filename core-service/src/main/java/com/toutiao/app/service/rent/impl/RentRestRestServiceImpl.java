package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.domain.rent.RentAgentDo;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.util.StringTool;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            Map<String, Object> source = hits[0].getSource();
            RentDetailsDo rentDetailsDo = RentDetailsDo.class.newInstance();
            BeanUtils.populate(rentDetailsDo, source);
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
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public List<RentDetailsFewDo> queryNearHouseByLocation(Double lat, Double lon, Integer pageNum) {
        List<RentDetailsFewDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",1));
        //从该坐标查询距离为5000内的小区
        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(5000, DistanceUnit.METERS);
        //按照距离排序由近到远并获取小区之间的距离
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        Integer size = 10;
        Integer from = (pageNum-1)*size;
        SearchResponse searchResponse = rentEsDao.queryNearHouseByLocation(boolQueryBuilder, location, sort, from, size);
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
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",3));
            long From = ((pageNum - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(booleanQueryBuilder, location, sort, (int) From,size-hits.length);
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
            booleanQueryBuilder.must(QueryBuilders.termQuery("rentHouseType",3));
            long From = ((pageNum - ((searchResponse.getHits().getTotalHits()/10)+1))*size);
            SearchResponse response = rentEsDao.queryNearHouseByLocation(booleanQueryBuilder, location, sort, (int) From,size);
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
}
