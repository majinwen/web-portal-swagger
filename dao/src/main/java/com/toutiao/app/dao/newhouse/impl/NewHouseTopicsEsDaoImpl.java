package com.toutiao.app.dao.newhouse.impl;

import com.toutiao.app.dao.newhouse.NewHouseTopicsEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NewHouseTopicsEsDaoImpl implements NewHouseTopicsEsDao {

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.newhouse.index}")
    private String newHouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newHouseType;//索引类型



    @Override
    public SearchResponse getNewHouseList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchResponse searchresponse =  client.prepareSearch(newHouseIndex).setTypes(newHouseType)
                .setQuery(boolQueryBuilder).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC)
                .setFetchSource(new String[]{"building_name_id","building_name","average_price", "district_id","district_name","area_id",
                                "area_name","building_title_img", "location","house_min_area","house_max_area","nearbysubway","total_price",
                                "deliver_time","building_tags_id","building_tags","ringRoad","building_feature"},
                        null)
                .setFrom((pageNum-1)*pageSize)
                .setSize(pageSize)
                .execute().actionGet();

        return searchresponse;
    }
}
