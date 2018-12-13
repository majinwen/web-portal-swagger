package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SellHouseEsDaoImpl implements SellHouseEsDao {

    @Value("${tt.esfFullAmount.index}")
    private String esfFullAmountIndex;
    @Value("${tt.esfFullAmountType.type}")
    private String esfFullAmountType;
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }


    /**
     * 根据小区id获取小区的房源数量
     *
     * @param plotsId
     * @return
     */
    @Override
    public SearchResponse getSellHouseCountByPlotsId(Integer plotsId, String city) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", plotsId));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", 0));
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).aggregation(AggregationBuilders.terms("roomCount").field("layout").order(BucketOrder.count(true)));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    /**
     * 根据小区id获取小区的房源数量v2(非聚合)
     *
     * @param plotsId
     * @return
     */
    @Override
    public SearchResponse getEsfCountByPlotsId(Integer plotsId, String city) {
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", plotsId));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", 0));
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }


    @Override
    public SearchResponse getEsfByPlotsIdAndRoom(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }


    @Override
    public SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer
            pageNum, Integer pageSize, String city, GeoDistanceSortBuilder geoDistanceSort, String sort) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ((null != keyword && !"".equals(keyword)) || (null != distance && distance > 0)) {
            //   searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("updateTimeSort", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseTotalPrices", SortOrder.ASC).sort(geoDistanceSort);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseTotalPrices", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("5".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseUnitCost", SortOrder.ASC).sort(geoDistanceSort);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("buildArea", SortOrder.DESC).sort(geoDistanceSort);
            } else {
                if (geoDistanceSort != null) {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
                } else {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
                }
            }
        } else {
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("updateTimeSort", SortOrder.DESC);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseTotalPrices", SortOrder.ASC);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseTotalPrices", SortOrder.DESC);
            } else if ("5".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("houseUnitCost", SortOrder.ASC);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("buildArea", SortOrder.DESC);
            } else {
                searchSourceBuilder.query(query).sort("extraTagsCount", SortOrder.DESC).sort("updateTimeSort", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            }
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer
            pageNum, Integer pageSize, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ((null != keyword && !"".equals(keyword)) || null != distance) {
            searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
        } else {
            searchSourceBuilder.query(query).sort("extraTagsCount", SortOrder.DESC).sort("updateTimeSort", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public SearchResponse getSellHouseByCondition(FunctionScoreQueryBuilder query, Integer pageNum, Integer pageSize, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public SearchResponse getRecommendSellHouse(FunctionScoreQueryBuilder query, String uid, Integer pageSize, String city) {


        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (!uid.equals("0")) {
            searchSourceBuilder.searchAfter(new String[]{uid});
        }
        searchSourceBuilder.query(query).sort("_uid", SortOrder.DESC).size(1)
                .fetchSource(new String[]{"areaId", "houseId", "housePhotoTitle", "houseTitle", "tagsName", "claimHouseId", "claimHouseTitle", "claimHousePhotoTitle", "price_increase_decline", "houseTotalPrices",
                        "houseUnitCost", "buildArea", "claimTagsName", "room", "hall", "forwardName", "area", "houseBusinessName",
                        "plotName", "year", "parkRadio", "subwayDistince", "housePlotLocation", "newcode", "housePhoto", "is_claim", "userId",
                        "houseProxyName", "ofCompany", "houseProxyPhone", "houseProxyPhoto", "claim_time", "price_increase_decline", "import_time", "price_increase_decline_amount",
                        "isMainLayout", "isDealLayout", "avgDealCycle", "isLowPrice", "isCutPrice", "isMustRob", "isLowest", "isNew", "isCommunityTopHouse", "avgAbsoluteWithCommunity",
                        "avgAbsoluteWithBizcircle", "avgAbsoluteWithDistrict", "avgRelativeWithCommunity", "avgRelativeWithBizcircle", "avgRelativeWithDistrict", "totalAbsoluteWithCommunity",
                        "totalAbsoluteWithBizcircle", "totalAbsoluteWithDistrict", "totalRelativeWithCommunity", "totalRelativeWithBizcircle", "totalRelativeWithDistrict", "traffic", "priceFloat",
                        "recommendBuildTagsId", "recommendBuildTagsName", "nearPark", "rankLowInBizcircleLayout", "rankInLowCommunityLayout"}, null);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

//    @Override
//    public SearchResponse getHouseByIds(IdsQueryBuilder idsQueryBuilder) {
//        TransportClient client = esClientTools.init();
//        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
//                .setQuery(idsQueryBuilder).setSize(1000)
//                .execute().actionGet();
//        return searchresponse;
//    }

    @Override
    public SearchResponse getComparedHouseByIds(IdsQueryBuilder idsQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(idsQueryBuilder).size(1000);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;


    }

//    @Override
//    public SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }
//
//    @Override
//    public SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(searchEnginesIndex).setTypes(searchEnginesType);
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }

    @Override
    public SearchResponse querySellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder) {

        SearchRequest searchRequest = new SearchRequest(esfFullAmountIndex).types(esfFullAmountType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).fetchSource(
                new String[]{"houseId", "housePhotoTitle", "area", "areaId", "houseBusinessNameId",
                        "houseBusinessName", "plotName", "newcode", "buildArea", "room", "forwardName",
                        "houseTotalPrices", "isCutPrice", "priceFloat", "isLowPrice", "isMustRob", "hall"},
                null);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }

    @Override
    public SearchResponse querySellHouseByHouseIdNew(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).fetchSource(
                new String[]{"houseId", "housePhotoTitle", "area", "areaId", "houseBusinessNameId",
                        "houseBusinessName", "plotName", "newcode", "buildArea", "room", "forwardName",
                        "houseTotalPrices", "isCutPrice", "priceFloat", "isLowPrice", "isMustRob", "hall"},
                null
        );
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchresponse;

    }

    @Override
    public SearchResponse querySellHouse(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0).aggregation(AggregationBuilders.terms("").field(""));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }

    @Override
    public SearchResponse getSimilarSellHouseList(BoolQueryBuilder query, String city, GeoDistanceSortBuilder geoDistanceSort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).size(3).sort("updateTimeSort", SortOrder.DESC).sort(geoDistanceSort);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }

    @Override
    public SearchResponse getBeSureToSnatchList(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize, FieldSortBuilder sortFile, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).sort(sortFile).from((pageNum - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchresponse;
    }


    @Override
    public SearchResponse getEsfCustomConditionDetails(BoolQueryBuilder query, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).size(0)
                .aggregation(AggregationBuilders.terms("areaName").field("houseBusinessNameId").size(20).order(BucketOrder.count(false))
                        .subAggregation(AggregationBuilders.min("houseMinArea").field("buildArea"))
                        .subAggregation(AggregationBuilders.max("houseMaxArea").field("buildArea"))
                        .subAggregation(AggregationBuilders.terms("buildCount").field("newcode").size(1000)));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchresponse;
    }

    @Override
    public SearchResponse getAvgPriceByBizcircle(BoolQueryBuilder query, String city) {
        Integer cityId = CityUtils.returnCityId(city);
        query.must(QueryBuilders.termQuery("city_id", cityId));
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(CityConstant.ABBREVIATION_QUANGUO))
                .types(ElasticCityUtils.getDbAvgPriceType(CityConstant.ABBREVIATION_QUANGUO));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query);
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
