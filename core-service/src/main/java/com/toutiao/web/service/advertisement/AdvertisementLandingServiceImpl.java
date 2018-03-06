package com.toutiao.web.service.advertisement;

import com.toutiao.web.common.util.ESClientTools;
import org.apache.commons.collections.map.HashedMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class AdvertisementLandingServiceImpl implements  AdvertisementLandingService{


    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;



    @Override
    public Map<String, Object> advertisement() {
        Map<String ,Object> advertisement =new HashedMap();
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

        //价格70到200万
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(70).lte(200));

        //面积50平米以上
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(50));

        //标签有近地铁
        booleanQueryBuilder.must(termsQuery("tags", "1"));

        //过滤掉北京周边
        booleanQueryBuilder.mustNot(termsQuery("areaId", "106013"));

        booleanQueryBuilder.must(termsQuery("isDel", "0"));

        Script script = new Script("Math.random()");

        booleanQueryBuilder.mustNot(termsQuery("housePhotoTitle", ""));

        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder)
                .addSort(scrip).setFetchSource(
                        new String[]{"houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen","traffic","tagsName","tags","houseTotalPrices","housePhotoTitle"},
                        null).setSize(7)
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }
        advertisement.put("data",buildinglist);
        advertisement.put("total", hits.getTotalHits());

        return advertisement;

    }
}
