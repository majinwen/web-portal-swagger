package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NearbySellHouseEsDaoImpl implements NearbySellHouseEsDao{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类

    @Override
    public SearchResponse getNearbySellHouseByFilter(FunctionScoreQueryBuilder query, Integer pageNum,Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);

        SearchResponse searchresponse = srb.setQuery(query).setFrom((pageNum - 1) * pageSize).setSize(pageSize).setFetchSource(
                new String[] { "houseId","houseTitle","housePhoto","houseTotalPrices","houseUnitCost","area","houseBusinessName",
                        "housePlotLocation","tagsName","plotName_accurate","traffic","forwardName","room","hall","buildArea","toilet",
                        "year","forwardName","is_claim","year","claimHouseTitle","claimHousePhotoTitle","claimTags","claimTagsName","claimHouseId",
                        "parkRadio","houseUnitCost","newcode","subwayLineId","subwayDistince","subwayStationId","housePhotoTitle","userId","price_increase_decline",
                "claim_time","import_time"} ,null
        ).execute().actionGet();


        return searchresponse;
    }
}
