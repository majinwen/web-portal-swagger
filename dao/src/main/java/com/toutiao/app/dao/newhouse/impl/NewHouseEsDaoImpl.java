package com.toutiao.app.dao.newhouse.impl;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class NewHouseEsDaoImpl implements NewHouseEsDao {
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

    //房源动态索引
    @Value("${tt.dynamic.index}")
    private String houseDynamicIndex;
    @Value("${tt.dynamic.type}")
    private  String dynamicType;


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
    public SearchResponse getNewHouseList(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize) {
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(boolQueryBuilder).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
                        new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                "location","house_min_area","house_max_area","nearbysubway","total_price","roundstation","deliver_time","park_radio"},
                        null)
                .setFrom((pageNum-1)*pageSize)
                .setSize(pageSize)
                .execute().actionGet();


       return searchresponse;
    }

    @Override
    public SearchResponse getDynamicByNewCode(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        searchresponse= client.prepareSearch(houseDynamicIndex).setTypes(dynamicType)
                .setQuery(boolQueryBuilder).addSort("create_time",SortOrder.DESC).setFetchSource(
                        new String[]{"title","time","link_url","detail","newcode","create_time","type","is_del"},null
                )
                .setFrom((pageNum-1)*pageSize)
                .setSize(pageSize)
                .execute().actionGet();
        return  searchresponse;

    }

}
