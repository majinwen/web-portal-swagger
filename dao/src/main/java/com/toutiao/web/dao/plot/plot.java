package com.toutiao.web.dao.plot;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.mapper.officeweb.PlotRatioMapper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class plot {
    @Value("${plot.index}")
    private String index ;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;
    @Value("${distance}")
    private Double distance;
    @Autowired
    private ESClientTools esClientTools;
    @Autowired
    private PlotRatioMapper plotRatioMapper;

    /**
     * queryById
     */
    public Map plot(Integer id){
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",id));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length==1){
                Map<String, Object> source = hits[0].getSource();
                return source;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
