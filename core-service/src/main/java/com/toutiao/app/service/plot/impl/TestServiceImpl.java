package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.TestDao;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.plot.PlotListDo;
import com.toutiao.app.domain.plot.PlotListDoQuery;
import com.toutiao.app.service.plot.TestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private TestDao testDao;

    @Override
    public void search() throws Exception{
        SearchRequest searchRequest = new SearchRequest("alias").types("cloth");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("size","41"));
        boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery("child",QueryBuilders.termQuery("size","41"), ScoreMode.None));
//        boolQueryBuilder.must(JoinQueryBuilders.hasParentQuery("parent",QueryBuilders.termQuery("size","41"), false));
//        searchSourceBuilder.query(boolQueryBuilder).size(100).from(0).sort("field",SortOrder.DESC).sort("field");
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = testDao.search(searchRequest);
        SearchHit[] hits = response.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
    }


    /**
     * 小区列表条件筛选
     * @param plotListDoQuery
     * @return
     */
    @Override
    public PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery, String city) throws Exception {
        String key = "";
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest("villages").types("community");
        //关键字
        if (StringTool.isNotEmpty(plotListDoQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart").boost(2));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("rc_nickname",plotListDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //区域id
        if (StringTool.isNotEmpty(plotListDoQuery.getDistrictId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId", plotListDoQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(plotListDoQuery.getAreaId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("tradingAreaId", plotListDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayLineId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", plotListDoQuery.getSubwayLineId()));
            key = String.valueOf(plotListDoQuery.getSubwayLineId());
        }
        //地铁站id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayStationId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("metroStationId", plotListDoQuery.getSubwayStationId()));
            key = key+"$"+ plotListDoQuery.getSubwayStationId();
        }
        //均价
        if (plotListDoQuery.getBeginPrice()!=0&&plotListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()).lte(plotListDoQuery.getEndPrice()));
        }else if(plotListDoQuery.getBeginPrice()!=0&&plotListDoQuery.getEndPrice()==0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()));
        }else if(plotListDoQuery.getBeginPrice()==0&&plotListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").lte(plotListDoQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(plotListDoQuery.getHouseYearId())){
            String[] age = plotListDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(plotListDoQuery.getLabelId())){
            Integer[] labelId = plotListDoQuery.getLabelId();
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            for(int i=0;i<labelId.length;i++){
                if(labelId[i].equals(0)){
                    booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway",1));
                }else {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("recommendBuildTagsId", labelId[i]));
                }
            }
            boolQueryBuilder.must(booleanQueryBuilder);
        }

        //房源面积大小
        if(plotListDoQuery.getBeginArea()!=0 && plotListDoQuery.getEndArea()!=0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery("sellhouse", QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()).lte(plotListDoQuery.getEndArea()), ScoreMode.None));

        }else if(plotListDoQuery.getBeginArea()!=0 && plotListDoQuery.getEndArea()==0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery("sellhouse", QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()), ScoreMode.None));
        }else if(plotListDoQuery.getBeginArea()==0 && plotListDoQuery.getEndArea()!=0){

            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery("sellhouse", QueryBuilders.rangeQuery("sellHouseArea")
                    .lte(plotListDoQuery.getEndArea()), ScoreMode.None));
        }

        GeoDistanceSortBuilder sort = null;
        //坐标
        if (StringTool.isNotEmpty(plotListDoQuery.getLat())&&plotListDoQuery.getLat()>0&&plotListDoQuery.getLon()>0&&StringTool.isNotEmpty(plotListDoQuery.getLon())){
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(plotListDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
            //排序
            sort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
//            sort.point(plotListDoQuery.getLat(), plotListDoQuery.getLon());

        }


        Integer from = 0;
        //分页起始位置
        if (StringTool.isNotEmpty(plotListDoQuery.getPageNum())&& plotListDoQuery.getPageNum()>1&&StringTool.isNotEmpty(plotListDoQuery.getPageSize())&& plotListDoQuery.getPageSize()>0){
            from = (plotListDoQuery.getPageNum()-1)* plotListDoQuery.getPageSize();
        }
        //是否上架
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));

        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));

        //级别排序
        FieldSortBuilder levelSort = SortBuilders.fieldSort("level").order(SortOrder.ASC);

        //小区分数排序
        FieldSortBuilder plotScoreSort = SortBuilders.fieldSort("plotScore").order(SortOrder.DESC);

        PlotListDo plotListDo = new PlotListDo();
        SearchResponse searchResponse = null;
        if ((StringTool.isNotEmpty(plotListDoQuery.getLat())&&plotListDoQuery.getLat()>0&&plotListDoQuery.getLon()>0&&StringTool.isNotEmpty(plotListDoQuery.getLon()))){
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(plotListDoQuery.getPageSize()).sort(sort).sort(levelSort).sort(plotScoreSort);
            searchRequest.source(searchSourceBuilder);
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }else {
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(plotListDoQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse!=null){
                SearchHit[] hits = searchResponse.getHits().getHits();

                if(hits.length > 0){
                    for (SearchHit hit:hits){
                        commonMethod(hit,key,plotDetailsFewDoList,city);
                    }
                }else{
                    throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_NOT_FOUND,"小区楼盘列表为空");
                }

            }
            plotListDo.setPlotList(plotDetailsFewDoList);
            plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
            return plotListDo;
        }

        long oneKM_size = searchResponse.getHits().getTotalHits();
        if(searchResponse != null){
            int reslocationinfo = searchResponse.getHits().getHits().length;
            if(reslocationinfo == 10){
                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHists = hits.getHits();
                for (SearchHit hit : searchHists) {
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }else if(reslocationinfo < 10 && reslocationinfo>0){
                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHists = hits.getHits();
                for (SearchHit hit : searchHists) {
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
                SearchSourceBuilder searchSourceBuilder1 = new SearchSourceBuilder();
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));

                SearchResponse searchResponse1 = null;
                if (StringTool.isNotEmpty(plotListDoQuery.getKeyword())){
//                  SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(0, booleanQueryBuilder,  plotListDoQuery.getPageSize() - reslocationinfo,plotListDoQuery.getKeyword(), city);
                    searchSourceBuilder1.query(booleanQueryBuilder).from(from).size(plotListDoQuery.getPageSize()).sort("_score",SortOrder.DESC).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
                    searchRequest.source(searchSourceBuilder);
                    searchResponse1 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                }else {
//                    searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).addSort("level", SortOrder.ASC).addSort("plotScore", SortOrder.DESC).execute().actionGet();
                    searchSourceBuilder1.query(booleanQueryBuilder).from(from).size(plotListDoQuery.getPageSize()).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
                    searchRequest.source(searchSourceBuilder);
                    searchResponse1 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                }


                SearchHit[] hits1 = searchResponse1.getHits().getHits();
                for (SearchHit hit:hits1){
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }else if(reslocationinfo == 0){
                SearchSourceBuilder searchSourceBuilder2 = new SearchSourceBuilder();
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
                Integer newFrom = (plotListDoQuery.getPageNum()-1)*plotListDoQuery.getPageSize();
                if(oneKM_size>0){
                    newFrom = (int) ((plotListDoQuery.getPageNum()-1)*plotListDoQuery.getPageSize() - (oneKM_size/plotListDoQuery.getPageSize()+1)*plotListDoQuery.getPageSize());
                }
//                SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(newFrom, booleanQueryBuilder, plotListDoQuery.getPageSize(),plotListDoQuery.getKeyword(), city);
                searchSourceBuilder2.query(booleanQueryBuilder).from(newFrom).size(plotListDoQuery.getPageSize());
                searchRequest.source(searchSourceBuilder);
                SearchResponse searchResponse2 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                SearchHit[] hits1 = searchResponse2.getHits().getHits();
                for (SearchHit hit:hits1){
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }
        }

        plotListDo.setPlotList(plotDetailsFewDoList);
        plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return plotListDo;
    }

    /**
     * 遍历结果
     * @param hit
     * @param key
     * @return
     */
    public void commonMethod(SearchHit hit,String key,List<PlotDetailsFewDo> plotDetailsFewDoList, String city){
        String sourceAsString = hit.getSourceAsString();
        PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
        plotDetailsFewDo.setAvgPrice((double) Math.round(plotDetailsFewDo.getAvgPrice()));
        if (null!=plotDetailsFewDo.getMetroWithPlotsDistance()&&""!=key){
            plotDetailsFewDo.setSubwayDistanceInfo((String) plotDetailsFewDo.getMetroWithPlotsDistance().get(key));
        }
        plotDetailsFewDo.setMetroWithPlotsDistance(null);
        //二手房总数
//        try {
//            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotDetailsFewDo.getId(),city);
//            plotDetailsFewDo.setSellHouseTotalNum(Math.toIntExact(plotsEsfRoomCountDomain.getTotalCount()));
//        }catch (BaseException e){
//            // logger.error("获取小区下二手房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
//            if (e.getCode()==50301){
//                plotDetailsFewDo.setSellHouseTotalNum(0);
//            }
//        }
//        //租房总数
//        try {
//            RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotDetailsFewDo.getId(),city);
//            plotDetailsFewDo.setRentTotalNum(rentNumListDo.getTotalNum());
//        }catch (BaseException e){
//            // logger.error("获取小区下租房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
//            if (e.getCode()==50401){
//                plotDetailsFewDo.setRentTotalNum(0);
//            }
//        }
        plotDetailsFewDoList.add(plotDetailsFewDo);
    }
}
