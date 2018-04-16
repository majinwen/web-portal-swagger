package com.toutiao.app.service.newhouse.impl;
import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.Appnewhouse.AppNewHouseEsDao;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.newhouse.AppNewHouseService;
import com.toutiao.web.common.exceptions.BaseException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppNewHouseServiceImpl implements AppNewHouseService {

    @Autowired
    private AppNewHouseEsDao newHouseEsDao;

    /**
     * 根据newcode获取新房详细信息
     * @param newcode
     * @return
     */
    @Override
    public NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("building_name_id",newcode));
        SearchResponse bulidResponse =newHouseEsDao.getNewHouseBulid(boolQueryBuilder);
        SearchHit[] searchHists = bulidResponse.getHits().getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        if (details.isEmpty())
        {
            throw new BaseException("201","未找到新房信息");
        }

        NewHouseDetailDo newHouseDetailDo = JSON.parseObject(details,NewHouseDetailDo.class);
        return  newHouseDetailDo;

    }


    /**
     *
     * @param newcode
     * 根据newcode查询户型信息
     * @return
     */
    @Override
    public List<NewHouseLayoutDo> getNewHouseLayoutByNewcode(Integer newcode) {

        List<NewHouseLayoutDo> newHouseLayoutDos=new ArrayList<>();
        SearchResponse layoutResponse =newHouseEsDao.getNewHouseLayoutByNewCode(newcode);
        SearchHits hits = layoutResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details=searchHit.getSourceAsString();
            NewHouseLayoutDo newHouseLayoutDo=JSON.parseObject(details,NewHouseLayoutDo.class);
            newHouseLayoutDos.add(newHouseLayoutDo);
        }
        if(newHouseLayoutDos.isEmpty())
        {
            throw  new BaseException("201","未找到新房户型信息");
        }
        return newHouseLayoutDos;


    }




}
