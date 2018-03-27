package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.rent.AppRentDao;
import com.toutiao.app.domain.Rent.RentAgentDo;
import com.toutiao.app.domain.Rent.RentDetailsDo;
import com.toutiao.app.domain.Rent.RentDetailsDoList;
import com.toutiao.app.service.rent.AppRentService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppRentServiceImpl implements AppRentService {
    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 0;//出租:1
    private static final Integer FOCUS_APARTMENT = 2;//公寓:2
    private static final Integer DISPERSED_APARTMENTS = 1;//公寓:2
    private static final String LAYOUT = "3";

    @Autowired
    private AppRentDao appRentDao;
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
            SearchResponse searchResponse = appRentDao.queryRentByRentId(boolQueryBuilder);
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
    public RentDetailsDoList queryRentListByPlotId(Integer plotId) {
        try {
//            RentDetailsDo rentDetailsDo = new RentDetailsDo();
            List<RentDetailsDo> list = new ArrayList<>();
            RentDetailsDoList rentDetailsDoList = new RentDetailsDoList();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
            SearchResponse searchResponse = appRentDao.queryRentListByPlotId(boolQueryBuilder);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map<String, Object> source = hit.getSource();
//                    BeanUtils.copyProperties(source,rentDetailsDo);
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsDo rentDetailsDo = JSON.parseObject(sourceAsString, RentDetailsDo.class);
                    list.add(rentDetailsDo);
                }
                rentDetailsDoList.setTotalNum((int) searchResponse.getHits().getTotalHits());
                rentDetailsDoList.setRentDetailsDoList(list);
                return rentDetailsDoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
                long time = new Date().getTime();
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


}
