package com.toutiao.app.dao.Appnewhouse.impl;
import com.toutiao.app.dao.Appnewhouse.AppNewHouseEsDao;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.ProjHouseInfoResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

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



    private static final Integer IS_DEL = 0;//新房未删除
    private static final Integer IS_APPROVE = 1;//新房未下架

    @Override
    public SearchResponse getNewHouseBulidByNewCode(Integer newcode) {
        TransportClient client = esClientTools.init();
        //查询新房信息
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("building_name_id", newcode));
        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();
       return  searchresponse;
    }


    @Override
    public SearchResponse getNewHouseLayoutByNewCode(Integer newcode) {
        TransportClient client = esClientTools.init();
      //查询户型信息
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        booleanQueryBuilder.must(JoinQueryBuilders.hasParentQuery(newhouseType,QueryBuilders.termQuery("building_name_id",newcode) ,false));
        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(layoutType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();

        return searchresponse;


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

}
