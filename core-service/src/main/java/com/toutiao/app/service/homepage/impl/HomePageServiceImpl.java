package com.toutiao.app.service.homepage.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.app.domain.homepage.*;
import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HomePageServiceImpl implements HomePageRestService {
    @Autowired
    private HomePageEsDao homePageEsDao;

    @Autowired
    private NewHouseRestService newHouseRestService;

    /**
     * @return 获取二手房5条
     */
    @Override
    public List<HomePageEsfDo> getHomePageEsf() {
        Random random = new Random();
        List<HomePageEsfDo> homePageEsfDos = new ArrayList<>();
        List<HomePageEsfDo> result = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("housePhotoTitle", ""));
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termsQuery("is_claim", "1"));
        SearchResponse searchResponse = homePageEsDao.getHomePageEsf(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String details = "";
            details = hit.getSourceAsString();
            HomePageEsfDo homePageEsf = JSON.parseObject(details, HomePageEsfDo.class);
            homePageEsfDos.add(homePageEsf);
        }

        if (!homePageEsfDos.isEmpty() && homePageEsfDos.size() > 5) {
            while (result.size() < 5) {
                result = hashPush(result, homePageEsfDos.get(random.nextInt(10)));
            }
        }
        return result;

    }

    /**
     * 获取新房5条
     *
     * @return
     */

    @Override
    public NewHouseListDomain getHomePageNewHouse() {
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        newHouseDoQuery.setPageSize(5);
        NewHouseListDomain newHouseListDomain = newHouseRestService.getNewHouseList(newHouseDoQuery);

        return newHouseListDomain;

    }

    /**
     * 获取首页主题房
     *
     * @param homeThemeHouseDoQuery
     * @return
     */
    @Override
    public HomeThemeHouseListDo getHomeThemeHouse(HomeThemeHouseDoQuery homeThemeHouseDoQuery) {
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();


        //价格
        if (homeThemeHouseDoQuery.getBeginPrice() == 0 && homeThemeHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(homeThemeHouseDoQuery.getEndPrice()));
        } else if (homeThemeHouseDoQuery.getBeginPrice() != 0 && homeThemeHouseDoQuery.getEndPrice() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(homeThemeHouseDoQuery.getBeginPrice()));
        } else if (homeThemeHouseDoQuery.getBeginPrice() != 0 && homeThemeHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gt(homeThemeHouseDoQuery.getBeginPrice())
                    .lte(homeThemeHouseDoQuery.getEndPrice()));
        }

        //面积
        if (homeThemeHouseDoQuery.getBeginArea() == 0 && homeThemeHouseDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lte(homeThemeHouseDoQuery.getEndArea()));
        } else if (homeThemeHouseDoQuery.getBeginArea() != 0 && homeThemeHouseDoQuery.getEndArea() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gt(homeThemeHouseDoQuery.getBeginArea()));
        } else if (homeThemeHouseDoQuery.getBeginArea() != 0 && homeThemeHouseDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gt(homeThemeHouseDoQuery.getBeginArea())
                    .lte(homeThemeHouseDoQuery.getEndArea()));
        }

        //几居
        if (null != homeThemeHouseDoQuery.getLayoutId() && homeThemeHouseDoQuery.getLayoutId().length > 0) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("room",homeThemeHouseDoQuery.getLayoutId()));
        }

        //是否有地铁
        if (null != homeThemeHouseDoQuery.getHasSubway() && homeThemeHouseDoQuery.getHasSubway() > 0) {
            boolQueryBuilder.must(QueryBuilders.termQuery("has_subway", homeThemeHouseDoQuery.getHasSubway()));
        }

        //isRecommend大于0，推荐房源
        boolQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));
        boolQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));

        SearchResponse homeThemeHouse = homePageEsDao.getHomeThemeHouse(boolQueryBuilder, (homeThemeHouseDoQuery.getPageNum() - 1) * homeThemeHouseDoQuery.getPageSize(), homeThemeHouseDoQuery.getPageSize());
        SearchHit[] hits = homeThemeHouse.getHits().getHits();
        HomeThemeHouseListDo homeThemeHouseListDo = new HomeThemeHouseListDo();

        if (hits.length > 0) {
            List<HomeThemeHouseDo> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                HomeThemeHouseDo homeThemeHouseDo = JSON.parseObject(sourceAsString, HomeThemeHouseDo.class);
                list.add(homeThemeHouseDo);
            }
            homeThemeHouseListDo.setData(list);
            homeThemeHouseListDo.setTotalNum((int) homeThemeHouse.getHits().getTotalHits());
        }
        return homeThemeHouseListDo;
    }

    private List hashPush(List<HomePageEsfDo> result, HomePageEsfDo homePageEsfDos) {
        Boolean flag = false;
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                if (null != result.get(i).getClaimHouseId() && null != homePageEsfDos.getClaimHouseId() && result.get(i).getClaimHouseId().equals(homePageEsfDos.getClaimHouseId())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            result.add(homePageEsfDos);
        }
        return result;
    }

    /**
     * 首页获取降价房8条
     */
    @Override
    public List<HomePageCutPriceDo> getHomePageCutPrice() {
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		//筛选低价房 price_increase_decline=2
		boolQueryBuilder.must(QueryBuilders.termQuery("price_increase_decline", 2));
        SearchResponse cutPriceHouses = homePageEsDao.getHomePageCutPrice(boolQueryBuilder);
        SearchHit[] hits = cutPriceHouses.getHits().getHits();
        List<HomePageCutPriceDo> homePageCutPriceDos = new ArrayList<>();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                HomePageCutPriceDo homePageCutPriceDo = JSON.parseObject(sourceAsString, HomePageCutPriceDo.class);
                homePageCutPriceDos.add(homePageCutPriceDo);
            }
        }
        return homePageCutPriceDos;
    }

    /**
     * 首页获取价格洼地房8条
     */
    @Override
    public List<HomePageLowerPriceDo> getHomePageLowerPrice() {
        //构建筛选器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //价格洼地 isLowPrice=1
        boolQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));
        SearchResponse lowerPriceHouses = homePageEsDao.getHomePageLowerPrice(boolQueryBuilder);
        SearchHit[] hits = lowerPriceHouses.getHits().getHits();
        List<HomePageLowerPriceDo> homePageCutPriceDos = new ArrayList<>();
        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                HomePageLowerPriceDo homePageLowerPriceDo = JSON.parseObject(sourceAsString, HomePageLowerPriceDo.class);
                homePageCutPriceDos.add(homePageLowerPriceDo);
            }
        }
        return homePageCutPriceDos;
    }
}
