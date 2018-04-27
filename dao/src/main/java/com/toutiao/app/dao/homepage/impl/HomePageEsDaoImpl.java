package com.toutiao.app.dao.homepage.impl;
import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HomePageEsDaoImpl implements HomePageEsDao {

    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类

    @Autowired
    private ESClientTools esClientTools;

    @Override
    public SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder) {
        SearchResponse searchResponse=null;
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
             srb.addSort("sortingScore", SortOrder.DESC);
             searchResponse = srb.setQuery(boolQueryBuilder).setSize(20).execute().actionGet();
            return searchResponse;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return searchResponse;

    }
}
