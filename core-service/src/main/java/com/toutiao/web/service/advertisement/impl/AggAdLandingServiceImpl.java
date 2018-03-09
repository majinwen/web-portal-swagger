package com.toutiao.web.service.advertisement.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class AggAdLandingServiceImpl implements AggAdLandingService{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;

    @Override
    public Map<String, Object> getAdLanding(AggAdLandingDo aggAdLandingDo) {

        TransportClient client = esClientTools.init();
//        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        QueryBuilder queryBuilder = null;

        //近地铁
        if(StringUtil.isNotNullString(aggAdLandingDo.getNs())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", aggAdLandingDo.getNs()));
        }

        // 房源标签（满5，满2）
        if(StringUtil.isNotNullString(aggAdLandingDo.getTag())){
            String[] layoutId = aggAdLandingDo.getTag().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", layoutId));
        }

        //房源总价范围
        if (StringTool.isNotEmpty(aggAdLandingDo.getBp()) && StringTool.isNotEmpty(aggAdLandingDo.getEp())) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(aggAdLandingDo.getBp()).lte(aggAdLandingDo.getEp())));

        }

        //小户型（90㎡内）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLs())) {
            String area = aggAdLandingDo.getLs().replaceAll("\\[","").replaceAll("]","")
                    .replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //豪宅 （200㎡以上）
        if (StringUtil.isNotNullString(aggAdLandingDo.getLh())) {
            String area = aggAdLandingDo.getLs().replaceAll("\\[","").replaceAll("]","")
                    .replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));

        int pageNum = 1;

        if(aggAdLandingDo.getPn()!=null && aggAdLandingDo.getPn()>1){
            pageNum = aggAdLandingDo.getPn();
        }

        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                .setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName"},
                        null)
                .setFrom((pageNum-1)*aggAdLandingDo.getPs())
                .setSize(aggAdLandingDo.getPs())
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        List<Map<String,Object>> resultlist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,Object> list = hit.getSourceAsMap();
            list.put("pageNum",aggAdLandingDo.getPn());
            resultlist.add(list);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",resultlist);
        result.put("total", hits.getTotalHits());

        return result;
    }
}
