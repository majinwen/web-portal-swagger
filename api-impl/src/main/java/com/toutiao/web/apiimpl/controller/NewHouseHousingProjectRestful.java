package com.toutiao.web.apiimpl.controller;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.ProjInfo;
import com.toutiao.web.domain.query.HousingProjectQuery;
import com.toutiao.web.service.HousingProjectService;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.List;

/**
 * 新房楼盘管理
 * @author WuShoulei on 2017/11/15
 */
@RestController
@RequestMapping("/newHouse")
public class NewHouseHousingProjectRestful {

    @Autowired
    private HousingProjectService housingProjectService;

    /**
     * 楼盘列表-新房
     * @param query
     * @return
     */
    @RequestMapping(value= "/listHousingProject")
    @ResponseBody
    public NashResult listNewHouseHousingProject(HousingProjectQuery query) {

        query.setProjFlag(0);//新房楼盘标志位
        List<ProjInfo> projInfoList = housingProjectService.listHousingProject(query);

        SearchResponse searchresponse = new SearchResponse();
        JSONObject hites = new JSONObject();
        try {
            // 设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
            // 创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));

//            for (int i = 0; i < projInfoList.size(); i++) {
//                 ProjInfo projInfo = projInfoList.get(i);
//                 String sdd = JSON.toJSONString(projInfo);
//                 IndexResponse response = client.prepareIndex("proj", "proj_house",projInfo.getNewcode().toString()).setSource(sdd).get();
//                 System.out.println("成功创建了一个索引，索引名为："+response.getIndex()+",类别为："+response.getType()+",文档ID为："+response.getId());
//            }

            //1000109	 	搜索引擎-按照id查询
            GetResponse response = client.prepareGet("proj", "proj_house", "11111354").get();
            System.out.println(response.getSourceAsString());
            //列表查询分页
//            searchresponse = client.prepareSearch("proj").setTypes("proj_house").setFrom(2).setSize(3).setExplain(true)
//                    .execute().actionGet();
//            SearchHits hits = searchresponse.getHits();


//            SearchResponse searchResponse = client
//                    .prepareSearch("proj").setTypes("proj_house").setSize(10)
//                    .setScroll(new TimeValue(20000)).execute()
//                    .actionGet();//注意:首次搜索并不包含数据
//            long totalCount = searchResponse.getHits().getTotalHits();
//            int page=(int)totalCount/(5*10);//计算总页数,每次搜索数量为分片数*设置的size大小
//            System.out.println(totalCount);
//            for (int i = 0; i <= page; i++) {
//                //再次发送请求,并使用上次搜索结果的ScrollId
//                searchResponse =client
//                        .prepareSearchScroll(searchResponse.getScrollId())
//                        .setScroll(new TimeValue(20000)).execute()
//                        .actionGet();
//                parseSearchResponse(searchResponse);
//            }

            //多条件查询，分页
//            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//
//            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", "1641"));
//            booleanQueryBuilder.must(QueryBuilders.termQuery("address", "124"));
//
////            booleanQueryBuilder.
//
//            searchresponse = client.prepareSearch("proj")
//                    .setTypes("proj_house")
////                    .setQuery(QueryBuilders.termsQuery("address", "西二环菜户营桥东南侧","海港区","运河"))
////                    .setQuery(QueryBuilders.boolQuery()
////                            .should(QueryBuilders.matchQuery("buildcategory", "1").boost(2))
////                            .should(QueryBuilders.matchQuery("areaId", "1641")))
//
//                    .setQuery(booleanQueryBuilder)
//                    .setFrom(0)
//                    .setSize(10)
//                    .setExplain(true)
//                    .execute().actionGet();
//            SearchHits hits = searchresponse.getHits();
//            long totalCount1 = hits.getTotalHits();
//            SearchHit[] searchHists = hits.getHits();
//            for (SearchHit hit : searchHists) {
//                String name = (String) hit.getSource().get("address");
//
//                Integer id = (Integer) hit.getSource().get("newcode");
////                String name = (String) JSON.toJSON(hit.getSource());
//                System.out.println(name+": "+id);
//            }



            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", "1641"));
            booleanQueryBuilder.must(QueryBuilders.termQuery("address", "124"));

//            QueryBuilder queryBuilders =  new QueryBuilder()；



            QueryBuilders.boolQuery().must(QueryBuilders.termQuery("areaId", "111"));

            QueryBuilders.boolQuery().must(QueryBuilders.termQuery("address", "50"));

            QueryBuilders.termsQuery("employees", new int[]{1,3});
            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
            searchresponse = client.prepareSearch("pro")
                    .setTypes("pro_arry")
//                    .setQuery(QueryBuilders.termsQuery("address", "西二环菜户营桥东南侧","海港区","运河"))
//                    .setQuery(QueryBuilders.boolQuery()
//                            .should(QueryBuilders.matchQuery("buildcategory", "1").boost(2))
//                            .should(QueryBuilders.matchQuery("areaId", "1641")))

                    .setQuery(queryBuilder)
                    .setFrom(0)
                    .setSize(10)
                    .setExplain(true)
                    .execute().actionGet();
            SearchHits hits = searchresponse.getHits();
            long totalCount1 = hits.getTotalHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit hit : searchHists) {
                String name = (String) hit.getSource().get("address");

                Integer id = (Integer) hit.getSource().get("newcode");
//                String name = (String) JSON.toJSON(hit.getSource());
                System.out.println(name+": "+id);
            }





            // 关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return NashResult.build(projInfoList);
    }
    private static int i=0;
    public static void parseSearchResponse(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        System.out.println("-----------begin------------");
        for (SearchHit searchHit : hits.getHits()) {
            try {
                i++;
                String id = searchHit.getId();
                System.out.println("第" + i + "条数据:" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------end------------");
    }

}
