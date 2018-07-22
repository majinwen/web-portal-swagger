package com.toutiao.app.service.newhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.newhouse.NewHouseTopicsEsDao;
import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.newhouse.NewHouseTopicsRestService;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;


@Service
public class NewHouseTopicsRestServiceImpl implements NewHouseTopicsRestService{

    @Autowired
    private NewHouseTopicsEsDao newHouseTopicsEsDao;


    /**
     * 获取五环内最美新房
     * @param newHouseDoQuery
     * @return
     */
    @Override
    public NewHouseListDomain getNewHouseTopic(NewHouseDoQuery newHouseDoQuery) {

        NewHouseListDomain newHouseListVo=new NewHouseListDomain();
        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        //区域
        if(newHouseDoQuery.getDistrictId()!=null && newHouseDoQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseDoQuery.getDistrictId()));
        }
        //销售状态（1在售和5待售）
        if(StringTool.isNotEmpty(newHouseDoQuery.getSaleStatusId()) && newHouseDoQuery.getSaleStatusId().length>0){
            Integer[] longs =  newHouseDoQuery.getSaleStatusId();
            booleanQueryBuilder.must(termsQuery("sale_status_id", longs));
        }
        //五环内
        if(StringTool.isNotEmpty(newHouseDoQuery.getRingRoad())){
            booleanQueryBuilder.must(rangeQuery("ringRoad").lte(newHouseDoQuery.getRingRoad()));
        }
        //标签
        if(StringTool.isNotEmpty(newHouseDoQuery.getBuildingFeature())){
            String[] feature = newHouseDoQuery.getBuildingFeature().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("building_tags_id", feature));
        }
        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve", 1));
        booleanQueryBuilder.must(termQuery("is_del", 0));

        SearchResponse newHouseList = newHouseTopicsEsDao.getNewHouseList(booleanQueryBuilder, newHouseDoQuery.getPageNum(), newHouseDoQuery.getPageSize());
        SearchHits hits = newHouseList.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                NewHouseListDo newHouseListDos= JSON.parseObject(details,NewHouseListDo.class);
                newHouseListDoList.add(newHouseListDos);
            }

            newHouseListVo.setData(newHouseListDoList);
            newHouseListVo.setTotalCount(hits.getTotalHits());
        }
        return newHouseListVo;
    }
}
