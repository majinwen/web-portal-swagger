package com.toutiao.app.dao.Appnewhouse.impl;
import com.toutiao.app.dao.Appnewhouse.AppNewHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class AppNewHouseEsDaoImpl implements AppNewHouseEsDao {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newhouseType;//索引类型
    @Value("${tt.newlayout.type}")
    private String layoutType;//子类索引类型
    @Value("${distance}")
    private Double distance;

    @Override
    public SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        //查询详情
        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(boolQueryBuilder)
                .execute().actionGet();
        return  searchresponse;
    }


    @Override
    public SearchResponse getNewHouseLayout(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        //查询户型信息
        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(layoutType)
                .setQuery(boolQueryBuilder)
                .execute().actionGet();
        return searchresponse;


    }

}
