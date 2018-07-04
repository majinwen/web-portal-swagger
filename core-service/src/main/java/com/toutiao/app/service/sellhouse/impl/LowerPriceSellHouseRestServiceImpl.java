package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.LowerPriceSellHouseEsDao;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDo;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.sellhouse.LowerPriceSellHouseRestService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 捡漏房服务层
 *
 * @author zym
 */
@Service
public class LowerPriceSellHouseRestServiceImpl implements LowerPriceSellHouseRestService {
    @Autowired
    private LowerPriceSellHouseEsDao lowerPriceSellHouseEsDao;

    @Autowired
    private SubscribeService subscribeService;

    /**
     * 获取捡漏房List
     */
    @Override
    public LowerPriceShellHouseDomain getLowerPriceHouse(LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery) {
        LowerPriceShellHouseDomain lowerPriceShellHouseDomain = new LowerPriceShellHouseDomain();

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        Integer areaId = lowerPriceShellHouseDoQuery.getAreaId();
        //捡漏房
        booleanQueryBuilder.must(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("isLowPrice", 1)));

        //商圈
        if (areaId != null) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("areaId", areaId)));
        }

        //新导入房源
        Integer isNew = lowerPriceShellHouseDoQuery.getIsNew();
        if (isNew != null) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("isNew", isNew)));
        }

        //价格区间
        Integer lowestTotalPrice = lowerPriceShellHouseDoQuery.getLowestTotalPrice();
        Integer highestTotalPrice = lowerPriceShellHouseDoQuery.getHighestTotalPrice();
        if (lowestTotalPrice != null) {
            if (highestTotalPrice != null) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery().must(
                        QueryBuilders.rangeQuery("houseTotalPrices")
                                .gte(lowestTotalPrice).lte(highestTotalPrice)));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery().must(
                        QueryBuilders.rangeQuery("houseTotalPrices")
                                .gte(lowestTotalPrice)));
            }
        } else if (highestTotalPrice != null) {
            booleanQueryBuilder.must(QueryBuilders.boolQuery().must(
                    QueryBuilders.rangeQuery("houseTotalPrices")
                            .lte(highestTotalPrice)));
        }

        Integer sort = lowerPriceShellHouseDoQuery.getSort();
        Integer pageNum = lowerPriceShellHouseDoQuery.getPageNum();
        Integer pageSize = lowerPriceShellHouseDoQuery.getPageSize();
        SearchResponse lowerPriceSellHouse = lowerPriceSellHouseEsDao.getLowerPriceSellHouse(booleanQueryBuilder, sort, pageNum, pageSize);

        SearchHits hits = lowerPriceSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<LowerPriceShellHouseDo> lowerPriceShellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                LowerPriceShellHouseDo lowerPriceShellHouseDo = JSON.parseObject(details, LowerPriceShellHouseDo.class);
                lowerPriceShellHouseDo.setSortField(searchHit.getSortValues()[0].toString());
                lowerPriceShellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                lowerPriceShellHouseDos.add(lowerPriceShellHouseDo);
            }
        }

        //查询订阅Id
        if (!UserBasic.isLogin()) {
            lowerPriceShellHouseDomain.setSubscribeId(-1);
        } else {
            UserBasic userBasic = UserBasic.getCurrent();
            UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
            userSubscribeDetailDo.setTopicType(2);
            if (areaId != null) {
                userSubscribeDetailDo.setDistrictId(areaId);
            }
            if (lowestTotalPrice != null) {
                userSubscribeDetailDo.setBeginPrice(lowestTotalPrice);
            }
            if (highestTotalPrice != null) {
                userSubscribeDetailDo.setEndPrice(highestTotalPrice);
            }

            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer
                    .valueOf(userBasic.getUserId()));
            if (userSubscribe != null) {
                lowerPriceShellHouseDomain.setSubscribeId(userSubscribe.getId());
            } else {
                lowerPriceShellHouseDomain.setSubscribeId(-1);
            }
        }
        lowerPriceShellHouseDomain.setData(lowerPriceShellHouseDos);
        lowerPriceShellHouseDomain.setTotalCount(hits.totalHits);
        return lowerPriceShellHouseDomain;
    }
}
