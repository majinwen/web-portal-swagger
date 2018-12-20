package com.toutiao.app.dao.homepage;

import org.elasticsearch.action.search.SearchResponse;

/**
 * Created by CuiShihao on 2018/10/19
 */
public interface HomePageCountEsDao {

    //获取新房相应的数量
    SearchResponse getNewCount(String city);

    SearchResponse getEsfCount(String city);
}
