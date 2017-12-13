package com.toutiao.web.service.repository.admin.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.repository.admin.ProjHouseInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjHouseInfoServiceImpl implements ProjHouseInfoService {

    @Autowired
    private ESClientTools esClientTools;
    private String projhouseIndex="projhouseinfo";//索引名称
    private String projhouseType="projhouse";//索引类


    /**
     * 按照标签范围查找
     *
     * @param index
     * @param type
     * @param term  字段
     * @param first 开始
     * @param last  结束
     * @return
     */
    @Override
    public List<ProjHouseInfo> queryProjHouseInfoByRang(String index, String type, String term, int first, int last) {
        TransportClient client = null;
        SearchResponse res = null;
        List<ProjHouseInfo> list = new ArrayList<ProjHouseInfo>();
        try {
            esClientTools.init();
            client = esClientTools.getClient();
//            QueryBuilder qb = QueryBuilders
//                    .rangeQuery(term)
//                    .gt(first)
//                    .lt(last);
//            BoolQueryBuilder bqb = QueryBuilders.boolQuery();
//            bqb.must(QueryBuilders.rangeQuery(term).gt(first).lt(last));
            //RangeQueryBuilder  ddd = QueryBuilders.rangeQuery(term).gt(first).lt(last);
            QueryBuilder qb = QueryBuilders
                    .rangeQuery(term)
                    .from(first)
                    .to(last)
                    .includeLower(true)
                    .includeUpper(true);
            res = client.prepareSearch(index)
                    .setTypes(type)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(qb)
                    .setFrom(0)
                    .setSize(10)
                    .execute().actionGet();
            for (SearchHit hit : res.getHits().getHits()) {

                Map map = hit.getSource();
                Class<ProjHouseInfo> entityClass = ProjHouseInfo.class;
                ProjHouseInfo instance = entityClass.newInstance();
                BeanUtils.populate(instance, map);
                list.add(instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return list;
    }

    /**
     * 随机获取数据并且根据房源级别排序
     *
     * @return
     */
    @Override
    public Map<String, Object> queryProjHouseInfo(ProjHouseInfoQuery projHouseInfoRequest) {
        List<ProjHouseInfo> list = null;

        esClientTools.init();
        TransportClient client = esClientTools.getClient();

        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        //srb.setSearchType(SearchType.DFS_QUERY_AND_FETCH);
        list = new ArrayList<ProjHouseInfo>();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
        //参数都为null,则查询所有数据
        if (StringTool.isEmpty(projHouseInfoRequest)) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.matchAllQuery());
            srb.setQuery(queryBuilder);
        }
        // 二手房ID
        if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseId())) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", projHouseInfoRequest.getHouseId()));
            srb.setQuery(queryBuilder);
        }
        //商圈名称
        if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseBusinessName())) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termQuery("businessAreaName", projHouseInfoRequest.getHouseBusinessName()));
            srb.setQuery(queryBuilder);
        }
        //商圈id
        if (projHouseInfoRequest.getHouseBusinessId() != null && projHouseInfoRequest.getHouseBusinessId() != 0) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termQuery("businessAreaId", projHouseInfoRequest.getHouseBusinessId()));
            srb.setQuery(queryBuilder);
        }
        //区域
        if (projHouseInfoRequest.getAreaId() != null && projHouseInfoRequest.getAreaId() != 0) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", projHouseInfoRequest.getAreaId()));
            srb.setQuery(queryBuilder);
        }
        //地铁线id
        if (projHouseInfoRequest.getSubwayLineId() != null && projHouseInfoRequest.getSubwayLineId().length != 0) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("metroSubwayLineId", projHouseInfoRequest.getSubwayLineId()));
            srb.setQuery(queryBuilder);
        }
        //地铁站id
        if (projHouseInfoRequest.getSubwayStationId() != null && projHouseInfoRequest.getSubwayStationId().length != 0) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("metroStationId", projHouseInfoRequest.getSubwayStationId()));
            srb.setQuery(queryBuilder);
        }
        //范围====================
        //总价  单选
        if (projHouseInfoRequest.getBeginPrice() != null && projHouseInfoRequest.getEndPrice() != 0) {
            BoolQueryBuilder queryBuilder = booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(projHouseInfoRequest.getBeginPrice()).lte(projHouseInfoRequest.getEndPrice())));
            srb.setQuery(queryBuilder);
        }
        //范围=========================
        //多选=========================
        //户型0-200,0-300
        //面积 没有确定?????
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseAreaId())) {
            String[] layoutId = projHouseInfoRequest.getHouseAreaId().split(",");
            BoolQueryBuilder queryBuilder =null;
            for (int i=0;i<layoutId.length;i++){
                String[] areRang = layoutId[i].split("-");
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseArea").gte(areRang[0]).lte(areRang[1])));
                srb.setQuery(queryBuilder);
            }
        }
        //楼龄
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseAreaId())) {
            BoolQueryBuilder queryBuilder =null;
            String[] layoutId = projHouseInfoRequest.getHouseAreaId().split(",");
            if (projHouseInfoRequest.getHouseAreaId()=="5") {
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseYear").gte(projHouseInfoRequest.getHouseAreaId())));
            }
            if (projHouseInfoRequest.getHouseAreaId()=="10") {
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseYear").gte(projHouseInfoRequest.getHouseAreaId())));
            }
            if (projHouseInfoRequest.getHouseAreaId()=="15") {
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseYear").gte(projHouseInfoRequest.getHouseAreaId())));
            }
            if (projHouseInfoRequest.getHouseAreaId()=="20") {
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseYear").gte(projHouseInfoRequest.getHouseAreaId())));
            }
            if (projHouseInfoRequest.getHouseAreaId()=="21") {
                queryBuilder = booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseYear").lte(projHouseInfoRequest.getHouseAreaId())));
            }
            srb.setQuery(queryBuilder);
        }
        //户型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseTypeId())) {
            String[] layoutId = projHouseInfoRequest.getHouseTypeId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseTypeId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //物业类型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseManagementTypeId())) {
            String[] layoutId = projHouseInfoRequest.getHouseManagementTypeId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseManagementTypeId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //电梯
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseLiftId())) {
            String[] layoutId = projHouseInfoRequest.getHouseLiftId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseLiftId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //建筑类型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getBuildingTypeId())) {
            String[] layoutId = projHouseInfoRequest.getBuildingTypeId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("buildingTypeId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //朝向houseOrientationId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseOrientationId())) {
            String[] layoutId = projHouseInfoRequest.getHouseOrientationId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseOrientationId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //标签(满二，满三，满五)houseLabelId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseLabelId())) {
            String[] layoutId = projHouseInfoRequest.getHouseLabelId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseLabelId", layoutId));
            srb.setQuery(queryBuilder);

        }
        //楼层houseFloorId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseFloorId())) {
            String[] layoutId = projHouseInfoRequest.getHouseFloorId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseFloorId", layoutId));
            srb.setQuery(queryBuilder);
        }
        //用途housePurposeId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHousePurposeId())) {
            String[] layoutId = projHouseInfoRequest.getHousePurposeId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("housePurposeId", layoutId));
            srb.setQuery(queryBuilder);
        }

        //供暖houseHeatingId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseHeatingId())) {
            String[] layoutId = projHouseInfoRequest.getHouseHeatingId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("housePurposeId", layoutId));
            srb.setQuery(queryBuilder);
        }

        //权属houseOwnershipId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseOwnershipId())) {
            String[] layoutId = projHouseInfoRequest.getHouseOwnershipId().split(",");
            BoolQueryBuilder queryBuilder = booleanQueryBuilder.must(QueryBuilders.termsQuery("houseOwnershipId", layoutId));
            srb.setQuery(queryBuilder);
        }
        /**
         * 排序  0--默认（按房源级别（广告优先））--1总价升排序--2总价降排序
         */
        int pageNum = 1;
        int pageSize = 10;
        if (projHouseInfoRequest.getPageNum() != null && projHouseInfoRequest.getPageNum() > 1) {
            pageNum = projHouseInfoRequest.getPageNum();
        }
        System.out.println(booleanQueryBuilder);

        if (projHouseInfoRequest.getSort() != null && projHouseInfoRequest.getSort() == 1) {
            searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.ASC)
                    /**
                     * 设置需要返回的参数传递到页面
                     * setFetchSource(
                     new String[]{"building_name_id", "building_name", "average_price", "building_tags", "activity_desc", "city_id",
                     "district_id", "district_name", "area_id", "area_name", "building_imgs"},
                     null)
                     */
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else if (projHouseInfoRequest.getSort() != null && projHouseInfoRequest.getSort() == 2) {
            searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.DESC)/*.setFetchSource(
                            new String[]{"building_name_id", "building_name", "average_price", "building_tags", "activity_desc", "city_id",
                                    "district_id", "district_name", "area_id", "area_name", "building_imgs"},
                            null)*/
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else {
            searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(booleanQueryBuilder).addSort("houseRank", SortOrder.DESC)/*.setFetchSource(
                            new String[]{"building_name_id", "building_name", "average_price", "building_tags", "activity_desc", "city_id",
                                    "district_id", "district_name", "area_id", "area_name", "building_imgs"},
                            null)*/
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }
        SearchHits hits = searchresponse.getHits();
        ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();

        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String, Object> buildings = hit.getSource();
            buildinglist.add(buildings);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", buildinglist);
        result.put("total", hits.getTotalHits());

        return result;
    }


}
