package com.toutiao.app.service.plot.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.plot.PlotsHousesDomain;
import com.toutiao.app.service.plot.PlotsHomesRestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-31
 * Time:   19:02
 * Theme:
 */
@Service
public class PlotsHomesRestServiceImpl implements PlotsHomesRestService {


    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private RentEsDao rentEsDao;


    @Override
    public PlotsHousesDomain queryPlotsHomesByPlotId(Integer plotId) {

        PlotsHousesDomain plotsHousesDomain = new PlotsHousesDomain();
        SearchResponse esfResponse = sellHouseEsDao.getEsfCountByPlotsId(plotId);
        SearchHit[] esfHits = esfResponse.getHits().getHits();
        long esf_top_size = 0;
        long rent_top_size = 0;
        if(esfHits!=null){
            esf_top_size = esfResponse.getHits().totalHits;
        }
        plotsHousesDomain.setSellHouseCount(esf_top_size);
        //租房
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del","0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
        SearchResponse rentResponse = rentEsDao.queryRentCountByPlotId(boolQueryBuilder);
        SearchHit[] rentHits = rentResponse.getHits().getHits();
        if(rentHits!=null){
            rent_top_size = rentResponse.getHits().totalHits;
        }
        plotsHousesDomain.setRentCount(rent_top_size);

        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.must(QueryBuilders.termQuery("is_del",0));
        queryBuilder.must(QueryBuilders.termQuery("release_status",1));
        queryBuilder.must(QueryBuilders.termQuery("zufang_id", plotId));

        SearchResponse rentPriceResponse = rentEsDao.getRentPriceByPlotId(queryBuilder);
        Map aggMap =rentPriceResponse.getAggregations().asMap();
        InternalMin minHouse = (InternalMin) aggMap.get("minRentPrice");

        plotsHousesDomain.setStartPrice(minHouse.getValue());

        return plotsHousesDomain;
    }
}
