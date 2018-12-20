package com.toutiao.app.dao.newhouse.impl;

import com.toutiao.app.dao.newhouse.NewHouseTopicsEsDao;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewHouseTopicsEsDaoImpl implements NewHouseTopicsEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.newhouse.index}")
    private String newHouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newHouseType;//索引类型



    @Override
    public SearchResponse getNewHouseList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize) {

        SearchRequest searchRequest = new SearchRequest(newHouseIndex).types(newHouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                .fetchSource(
                        new String[]{"building_name_id","building_name","average_price", "district_id","district_name","area_id",
                                "area_name","building_title_img", "location","house_min_area","house_max_area","nearbysubway","total_price",
                                "deliver_time","building_tags_id","building_tags","ringRoad","building_feature"},
                        null).from((pageNum-1)*pageSize).size(pageSize);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }
}
