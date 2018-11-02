package com.toutiao.app.dao.advertisement.impl;

import com.toutiao.app.dao.advertisement.AdCommunityEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   18:21
 * Theme:
 */

@Service
public class AdCommunityEsDaoImpl implements AdCommunityEsDao {

    @Autowired
    private ESClientTools esClientTools;

    @Override
    public SearchResponse getAdCommunity(BoolQueryBuilder booleanQueryBuilder, String city) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
        SearchResponse searchresponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();

        return searchresponse;
    }
}
