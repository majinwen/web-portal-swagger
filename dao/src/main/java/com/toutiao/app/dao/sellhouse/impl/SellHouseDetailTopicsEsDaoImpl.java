//package com.toutiao.app.dao.sellhouse.impl;
//
//import com.toutiao.app.dao.sellhouse.SellHouseDetailTopicsEsDao;
//import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
//import com.toutiao.web.common.util.ESClientTools;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class SellHouseDetailTopicsEsDaoImpl implements SellHouseDetailTopicsEsDao {
//
////    @Autowired
////    private ESClientTools esClientTools;
//    @Value("${tt.projhouse.index}")
//    private String proJHouseIndex;//索引名称
//    @Value("${tt.projhouse.type}")
//    private String proJHouseType;//索引类
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    /**
//     * 小区附近
//     * @param boolQueryBuilder
//     * @param searchAfterIds
//     * @param pageSize
//     * @return
//     */
//    @Override
//    public SearchResponse getNearbyTopicsSellHouse(BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds, Integer pageSize) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(proJHouseIndex).setTypes(proJHouseType);
//
//
//        if(searchAfterIds.length!=0){
//            srb.searchAfter(searchAfterIds);
//        }
//
//        SearchResponse searchresponse = srb.setQuery(boolQueryBuilder).addSort("updateTimeSort",SortOrder.DESC)
//                .addSort("_uid",SortOrder.DESC).setSize(pageSize).setFetchSource(
//                new String[] {"houseId","housePhotoTitle","houseTitle","tagsName","claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
//                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
//                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId",
//                        "houseProxyName","ofCompany","houseProxyPhone","houseProxyPhoto","claim_time","price_increase_decline","import_time","price_increase_decline_amount",
//                        "isMainLayout","isDealLayout","avgDealCycle","isLowPrice","isCutPrice","isMustRob","isLowest","isNew","isCommunityTopHouse","avgAbsoluteWithCommunity",
//                        "avgAbsoluteWithBizcircle","avgAbsoluteWithDistrict","avgRelativeWithCommunity","avgRelativeWithBizcircle","avgRelativeWithDistrict","totalAbsoluteWithCommunity",
//                        "totalAbsoluteWithBizcircle","totalAbsoluteWithDistrict","totalRelativeWithCommunity","totalRelativeWithBizcircle","totalRelativeWithDistrict","traffic"} ,null)
//                .execute().actionGet();
//
//        SearchRequest searchRequest = new SearchRequest(proJHouseIndex);
//
//
//
//        return searchresponse;
//    }
//
//    /**
//     * 降价房
//     * @param sellHouseDoQuery
//     * @param boolQueryBuilder
//     * @param searchAfterIds
//     * @return
//     */
//    @Override
//    public SearchResponse getCutPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(proJHouseIndex).setTypes(proJHouseType);
//        if("0".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("updateTimeSort",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("1".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("2".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("3".equals(sellHouseDoQuery.getSort())){
//            boolQueryBuilder.must(QueryBuilders.termQuery("price_increase_decline", "2"));
//            srb.addSort("price_increase_decline_amount",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("4".equals(sellHouseDoQuery.getSort())){
//            boolQueryBuilder.must(QueryBuilders.termQuery("price_increase_decline", "2"));
//            srb.addSort("price_increase_decline_amount",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }
//
//        SearchResponse searchresponse = srb.setQuery(boolQueryBuilder).searchAfter(searchAfterIds).setSize(sellHouseDoQuery.getPageSize()).setFetchSource(
//                        new String[] {"houseId","housePhotoTitle","houseTitle","tagsName","claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
//                                "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
//                                "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId",
//                                "houseProxyName","ofCompany","houseProxyPhone","houseProxyPhoto","claim_time","price_increase_decline","import_time","price_increase_decline_amount",
//                                "isMainLayout","isDealLayout","avgDealCycle","isLowPrice","isCutPrice","isMustRob","isLowest","isNew","isCommunityTopHouse","avgAbsoluteWithCommunity",
//                                "avgAbsoluteWithBizcircle","avgAbsoluteWithDistrict","avgRelativeWithCommunity","avgRelativeWithBizcircle","avgRelativeWithDistrict","totalAbsoluteWithCommunity",
//                                "totalAbsoluteWithBizcircle","totalAbsoluteWithDistrict","totalRelativeWithCommunity","totalRelativeWithBizcircle","totalRelativeWithDistrict","traffic"} ,null)
//                .execute().actionGet();
//        return searchresponse;
//    }
//
//
//    /**
//     * 洼地房
//     * @param sellHouseDoQuery
//     * @param boolQueryBuilder
//     * @param searchAfterIds
//     * @return
//     */
//    @Override
//    public SearchResponse getLowPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(proJHouseIndex).setTypes(proJHouseType);
//        if("0".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("updateTimeSort",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("1".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("2".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("5".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("6".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }
//
//        SearchResponse searchresponse = srb.setQuery(boolQueryBuilder).setSize(sellHouseDoQuery.getPageSize()).setFetchSource(
//                new String[] {"houseId","housePhotoTitle","houseTitle","tagsName","claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
//                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
//                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId",
//                        "houseProxyName","ofCompany","houseProxyPhone","houseProxyPhoto","claim_time","price_increase_decline","import_time","price_increase_decline_amount",
//                        "isMainLayout","isDealLayout","avgDealCycle","isLowPrice","isCutPrice","isMustRob","isLowest","isNew","isCommunityTopHouse","avgAbsoluteWithCommunity",
//                        "avgAbsoluteWithBizcircle","avgAbsoluteWithDistrict","avgRelativeWithCommunity","avgRelativeWithBizcircle","avgRelativeWithDistrict","totalAbsoluteWithCommunity",
//                        "totalAbsoluteWithBizcircle","totalAbsoluteWithDistrict","totalRelativeWithCommunity","totalRelativeWithBizcircle","totalRelativeWithDistrict","traffic"} ,null)
//                .execute().actionGet();
//        return searchresponse;
//    }
//
//    /**
//     * 逢出毕抢
//     * @param sellHouseDoQuery
//     * @param boolQueryBuilder
//     * @param searchAfterIds
//     * @return
//     */
//    @Override
//    public SearchResponse getMustRobTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(proJHouseIndex).setTypes(proJHouseType);
//        if("0".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("updateTimeSort",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("2".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("1".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("5".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("6".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }
//
//        SearchResponse searchresponse = srb.setQuery(boolQueryBuilder).setSize(sellHouseDoQuery.getPageSize()).setFetchSource(
//                new String[] {"houseId","housePhotoTitle","houseTitle","tagsName","claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
//                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
//                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId",
//                        "houseProxyName","ofCompany","houseProxyPhone","houseProxyPhoto","claim_time","price_increase_decline","import_time","price_increase_decline_amount",
//                        "isMainLayout","isDealLayout","avgDealCycle","isLowPrice","isCutPrice","isMustRob","isLowest","isNew","isCommunityTopHouse","avgAbsoluteWithCommunity",
//                        "avgAbsoluteWithBizcircle","avgAbsoluteWithDistrict","avgRelativeWithCommunity","avgRelativeWithBizcircle","avgRelativeWithDistrict","totalAbsoluteWithCommunity",
//                        "totalAbsoluteWithBizcircle","totalAbsoluteWithDistrict","totalRelativeWithCommunity","totalRelativeWithBizcircle","totalRelativeWithDistrict","traffic"} ,null)
//                .execute().actionGet();
//        return searchresponse;
//    }
//
//    /**
//     * 商圈户型
//     * @param sellHouseDoQuery
//     * @param boolQueryBuilder
//     * @param searchAfterIds
//     * @return
//     */
//    @Override
//    public SearchResponse getAreaRoomTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(proJHouseIndex).setTypes(proJHouseType);
//        if("0".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("updateTimeSort",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("2".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("1".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("houseTotalPrices",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }else if("6".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.DESC).addSort("_uid",SortOrder.DESC);
//        }else if("5".equals(sellHouseDoQuery.getSort())){
//            srb.addSort("buildArea",SortOrder.ASC).addSort("_uid",SortOrder.DESC);
//        }
//
//        SearchResponse searchresponse = srb.setQuery(boolQueryBuilder).setSize(sellHouseDoQuery.getPageSize()).setFetchSource(
//                new String[] {"houseId","housePhotoTitle","houseTitle","tagsName","claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
//                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
//                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId",
//                        "houseProxyName","ofCompany","houseProxyPhone","houseProxyPhoto","claim_time","price_increase_decline","import_time","price_increase_decline_amount",
//                        "isMainLayout","isDealLayout","avgDealCycle","isLowPrice","isCutPrice","isMustRob","isLowest","isNew","isCommunityTopHouse","avgAbsoluteWithCommunity",
//                        "avgAbsoluteWithBizcircle","avgAbsoluteWithDistrict","avgRelativeWithCommunity","avgRelativeWithBizcircle","avgRelativeWithDistrict","totalAbsoluteWithCommunity",
//                        "totalAbsoluteWithBizcircle","totalAbsoluteWithDistrict","totalRelativeWithCommunity","totalRelativeWithBizcircle","traffic","totalRelativeWithDistrict"} ,null)
//                .execute().actionGet();
//        return searchresponse;
//    }
//
//
//}
