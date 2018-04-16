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
    public SearchResponse getNewHouseList(NewHouseListDo newHouseListDo) {
        TransportClient client = esClientTools.init();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        SearchResponse searchresponse = new SearchResponse();
        if(StringUtil.isNotNullString(newHouseListDo.getKeyword())){
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseListDo.getKeyword()))){
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseListDo.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("area_name", newHouseListDo.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("district_name", newHouseListDo.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name_accurate", newHouseListDo.getKeyword()).boost(2))
                        .add(QueryBuilders.matchQuery("building_name", newHouseListDo.getKeyword()).analyzer("ik_max_word"))
                        .add(QueryBuilders.matchQuery("area_name", newHouseListDo.getKeyword()))
                        .add(QueryBuilders.matchQuery("district_name", newHouseListDo.getKeyword())).tieBreaker(0.3f);
            }

            booleanQueryBuilder.must(queryBuilder);
            //    booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("building_name_accurate", newHouseQuery.getKeyword()))).boost(2);
        }

        //城市
        if(newHouseListDo.getCityId()!=null && newHouseListDo.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id", newHouseListDo.getDistrict_id()));
        }
        //区域
        if(newHouseListDo.getDistrict_id()!=null && newHouseListDo.getDistrict_id() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseListDo.getDistrict_id()));
        }
        //商圈
        if(newHouseListDo.getAreaId()!=null && newHouseListDo.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", newHouseListDo.getAreaId()));
        }
        //地铁线id
        String keys = "";
        if(newHouseListDo.getSubwayLineId() !=null && newHouseListDo.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseListDo.getSubwayLineId()}));
            keys = newHouseListDo.getSubwayLineId().toString();
        }
        //地铁站id
        if(newHouseListDo.getSubwayStationId()!=null && newHouseListDo.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{newHouseListDo.getSubwayStationId()}));
            keys = keys+"$"+newHouseListDo.getSubwayStationId().toString();
        }
        //总价
        if(newHouseListDo.getMin_price()!=null && newHouseListDo.getMax_price()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseListDo.getMin_price()).lte(newHouseListDo.getMax_price())));
        }

        //户型
        if(null!=newHouseListDo.getLabelId() && newHouseListDo.getLabelId().length!=0){

              Integer[] longs = newHouseListDo.getLabelId();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }


        //面积
        if(newHouseListDo.getHouse_min_area()!=null && newHouseListDo.getHosue_max_area()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseListDo.getHouse_min_area())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseListDo.getHosue_max_area())));
        }


        //销售状态
        if(StringUtil.isNotNullString(newHouseListDo.getSale_status_name())){
            booleanQueryBuilder.must(termQuery("sale_status_id", newHouseListDo.getSale_status_name()));
        }else{
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        }
        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));


        int pageNum = 1;

        if(newHouseListDo.getPageNum()!=null && newHouseListDo.getPageNum()>1){
            pageNum = newHouseListDo.getPageNum();
        }
        searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(booleanQueryBuilder).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
                        new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                "location","house_min_area","house_max_area","nearbysubway","total_price","roundstation"},
                        null)
                .setFrom((pageNum-1)*newHouseListDo.getPageSize())
                .setSize(newHouseListDo.getPageSize())
                .execute().actionGet();


       return searchresponse;
    }

}
