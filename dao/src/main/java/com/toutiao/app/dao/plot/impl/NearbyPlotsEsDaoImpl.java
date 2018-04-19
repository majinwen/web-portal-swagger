package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.NearbyPlotsEsDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NearbyPlotsEsDaoImpl implements NearbyPlotsEsDao{

    @Value("${plot.index}")
    private String index ;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;



    /**
     * 根据用户坐标获取用户附近小区列表
     * @param geoDistanceQueryBuilder 坐标
     * @param sort 距离计算
     * @param boolQueryBuilder 筛选条件
     * @param pageNum 起始
     * @param size 每页查询数量
     * @return
     */
    @Override
    public SearchResponse queryNearbyPlotsListByUserCoordinate(GeoDistanceQueryBuilder geoDistanceQueryBuilder, GeoDistanceSortBuilder sort,
                                                               BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer size) {


//        SearchResponse searchResponse =





        return null;
    }
}
