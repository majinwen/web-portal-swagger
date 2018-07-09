package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.CutPriceSellHouseEsDao;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDo;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.sellhouse.CutPriceSellHouseRestService;
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

@Service
public class CutPriceSellHouseRestServiceImpl implements CutPriceSellHouseRestService {
	@Autowired
	private CutPriceSellHouseEsDao cutPriceSellHouseEsDao;

	@Autowired
	private SubscribeService subscribeService;

	/**
	 * 获取降价房Domain
	 */
	@Override
	public CutPriceShellHouseDomain getCutPriceHouse(CutPriceShellHouseDoQuery cutPriceShellHouseDoQuery) {
		CutPriceShellHouseDomain cutPriceShellHouseDomain = new CutPriceShellHouseDomain();
		BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
		Integer areaId = cutPriceShellHouseDoQuery.getAreaId();
		//降价房
		booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));

		//区域
		if (areaId != null) {
			booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", areaId));
		}

		//新导入房源
		Integer isNew = cutPriceShellHouseDoQuery.getIsNew();
		if (isNew != null) {
			booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", isNew));
		}

		//价格区间
		Integer lowestTotalPrice = cutPriceShellHouseDoQuery.getLowestTotalPrice();
		Integer highestTotalPrice = cutPriceShellHouseDoQuery.getHighestTotalPrice();
		if (lowestTotalPrice != null) {
			if (highestTotalPrice != null) {
				booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(lowestTotalPrice)
						.lte(highestTotalPrice));
			} else {
				booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(lowestTotalPrice));
			}
		} else if (highestTotalPrice != null) {
			booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(highestTotalPrice));
		}
		Integer sort = cutPriceShellHouseDoQuery.getSort();
		Integer pageNum = cutPriceShellHouseDoQuery.getPageNum();
		Integer pageSize = cutPriceShellHouseDoQuery.getPageSize();
		SearchResponse cutPriceSellHouse = cutPriceSellHouseEsDao.getCutPriceSellHouse(booleanQueryBuilder, sort, pageNum, pageSize);

		SearchHits hits = cutPriceSellHouse.getHits();
		SearchHit[] searchHists = hits.getHits();
		List<CutPriceShellHouseDo> cutPriceShellHouseDos = new ArrayList<>();
		if (searchHists.length > 0) {
			for (SearchHit searchHit : searchHists) {
				String details = searchHit.getSourceAsString();
				CutPriceShellHouseDo cutPriceShellHouseDo = JSON.parseObject(details, CutPriceShellHouseDo.class);
				cutPriceShellHouseDo.setSortField(searchHit.getSortValues()[0].toString());
				cutPriceShellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
				cutPriceShellHouseDos.add(cutPriceShellHouseDo);
			}
		}

		//查询订阅Id
		if (!UserBasic.isLogin()) {
			cutPriceShellHouseDomain.setSubscribeId(-1);
		} else {
			UserBasic userBasic = UserBasic.getCurrent();
			UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
			userSubscribeDetailDo.setTopicType(1);
			userSubscribeDetailDo.setDistrictId(areaId);
			userSubscribeDetailDo.setBeginPrice(lowestTotalPrice);
			userSubscribeDetailDo.setEndPrice(highestTotalPrice);

			UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer
					.valueOf(userBasic.getUserId()));
			if (userSubscribe != null) {
				cutPriceShellHouseDomain.setSubscribeId(userSubscribe.getId());
			} else {
				cutPriceShellHouseDomain.setSubscribeId(-1);
			}
		}
		cutPriceShellHouseDomain.setData(cutPriceShellHouseDos);
		cutPriceShellHouseDomain.setTotalCount(hits.totalHits);
		return cutPriceShellHouseDomain;
	}
}
