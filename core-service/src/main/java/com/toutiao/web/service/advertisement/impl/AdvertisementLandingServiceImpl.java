package com.toutiao.web.service.advertisement.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.service.advertisement.AdvertisementLandingService;
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
import java.util.Collections;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class AdvertisementLandingServiceImpl implements AdvertisementLandingService {


    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;



    @Override
    public Map<String, Object> advertisementCpc_3() {
        Map<String ,Object> advertisement =new HashedMap();
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

        //价格70到200万
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(70).lte(200));

        //面积50平米以上
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(50));

//        //标签有近地铁
//        booleanQueryBuilder.must(termsQuery("tags", "1"));

        //过滤掉北京周边
        booleanQueryBuilder.mustNot(termsQuery("areaId", "106013"));

        booleanQueryBuilder.must(termsQuery("isDel", "0"));

        Script script = new Script("Math.random()");

        booleanQueryBuilder.mustNot(termsQuery("housePhotoTitle", ""));

        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder)
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen","traffic","tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId"},
                        null).setSize(7)
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }
        Collections.shuffle(buildinglist);
        advertisement.put("data",buildinglist);
        advertisement.put("total", hits.getTotalHits());

        return advertisement;

    }

    @Override
    public Map<String, Object> advertisementCpc_1() {


        Map<String ,Object> advertisement =new HashedMap();
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder booleanQueryBuilder1 = QueryBuilders.boolQuery();
        BoolQueryBuilder booleanQueryBuilder2 = QueryBuilders.boolQuery();

        //价格300到500万
        booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder.must(termsQuery("isDel", "0"));
        //价格300到500万
        booleanQueryBuilder1.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder1.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder1.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder1.must(termsQuery("isDel", "0"));
        //价格300到500万
        booleanQueryBuilder2.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(300).lte(500));
        //标签有近地铁
//        booleanQueryBuilder2.must(termsQuery("tags", "1"));
        //忽略标题图为空
        booleanQueryBuilder2.mustNot(termsQuery("housePhotoTitle", ""));
        booleanQueryBuilder2.must(termsQuery("isDel", "0"));
        Script script = new Script("Math.random()");
        ScriptSortBuilder scrip = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).setQuery(booleanQueryBuilder.must(termQuery("of_company", "我爱我家")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company"},
                        null)
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder1).setQuery(booleanQueryBuilder1.must(termQuery("of_company", "中原地产")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company"},
                        null)
                .execute().actionGet();

        SearchHits hit1 = searchresponse.getHits();
        SearchHit[] searchHist1 = hit1.getHits();

        for (SearchHit hit : searchHist1) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }

        searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder2).setQuery(booleanQueryBuilder2.must(termQuery("of_company", "麦田房产")))
                .addSort(scrip).setFetchSource(
                        new String[]{"houseId","houseTitle","buildArea","forwardName","room","hall","plotName","toilet","kitchen",
                                "tagsName","tags","houseTotalPrices","housePhotoTitle","area","areaId","houseBusinessName","houseBusinessNameId","of_company"},
                        null)
                .execute().actionGet();

        SearchHits hit2 = searchresponse.getHits();
        SearchHit[] searchHist2 = hit2.getHits();

        for (SearchHit hit : searchHist2) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }


        Collections.shuffle(buildinglist);

        advertisement.put("data",buildinglist);

        return advertisement;
    }
}
