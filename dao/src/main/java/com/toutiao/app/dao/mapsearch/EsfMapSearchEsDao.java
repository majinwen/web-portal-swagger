package com.toutiao.app.dao.mapsearch;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @ClassName EsfMapSearchEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/23 7:41 PM
 * @Description:
 **/

public interface EsfMapSearchEsDao {

    SearchResponse esfMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city);

}
