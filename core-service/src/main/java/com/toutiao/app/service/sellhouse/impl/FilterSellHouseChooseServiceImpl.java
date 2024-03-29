package com.toutiao.app.service.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.domain.sellhouse.RecommendEsf5DoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class FilterSellHouseChooseServiceImpl implements FilterSellHouseChooseService {

    @Autowired
    private SellHouseKeywordEsDao sellHouseKeywordEsDao;
//    @Autowired
//    private SellHouseEsDao sellHouseEsDao;

    /**
     * 过滤二手房查询条件
     *
     * @param
     * @return
     */
    @Override
    public BoolQueryBuilder filterChoose(NearBySellHouseQueryDo nearBySellHouseQueryDo) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(nearBySellHouseQueryDo.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(nearBySellHouseQueryDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("area", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHouseQueryDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHouseQueryDo.getKeyword()).analyzer("ik_smart")));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHouseQueryDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", nearBySellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHouseQueryDo.getKeyword())));
            }
        }
        //商圈名称
//        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getAreaId())) {
//            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", nearBySellHouseQueryDo.getAreaId()));
//        }
        //商圈id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", nearBySellHouseQueryDo.getAreaId()));

        }

        //区域id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getDistrictIds())) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", nearBySellHouseQueryDo.getDistrictIds()));
        }

        //地铁线id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayLineId()) && nearBySellHouseQueryDo.getSubwayLineId() > 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", nearBySellHouseQueryDo.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayStationId", nearBySellHouseQueryDo.getSubwayStationId()));
        }

        //总价
        if (nearBySellHouseQueryDo.getBeginPrice() != 0 && nearBySellHouseQueryDo.getEndPrice() != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()).lte(nearBySellHouseQueryDo.getEndPrice()));

        } else if (nearBySellHouseQueryDo.getBeginPrice() == 0 && nearBySellHouseQueryDo.getEndPrice() != 0) {

            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(nearBySellHouseQueryDo.getEndPrice()));

        } else if (nearBySellHouseQueryDo.getEndPrice() == 0 && nearBySellHouseQueryDo.getBeginPrice() != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()));
        }

        //面积
        if (0 != nearBySellHouseQueryDo.getBeginArea() && 0 != nearBySellHouseQueryDo.getEndArea()) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHouseQueryDo.getBeginArea()).lte(nearBySellHouseQueryDo.getEndArea()));

        } else if (nearBySellHouseQueryDo.getBeginArea() == 0 && nearBySellHouseQueryDo.getEndArea() != 0) {

            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").lte(nearBySellHouseQueryDo.getEndArea()));

        } else if (nearBySellHouseQueryDo.getEndArea() == 0 && nearBySellHouseQueryDo.getBeginArea() != 0) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHouseQueryDo.getBeginArea()));

        }
        //楼龄
        if (StringUtil.isNotNullString(nearBySellHouseQueryDo.getHouseYearId())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String houseyear = nearBySellHouseQueryDo.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",");

            String[] layoutId = houseyear.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("year")
                        //计算房源建成年代
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(layoutId[i + 1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(layoutId[i])))));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //户型(室)
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getLayoutId())) {
            Integer[] longs = nearBySellHouseQueryDo.getLayoutId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room", longs)));
        }


        //朝向
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getForwardId())) {
            Integer[] longs = nearBySellHouseQueryDo.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward", longs)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getLabelId())) {
            Integer[] longs = nearBySellHouseQueryDo.getLabelId();
            BoolQueryBuilder bool = QueryBuilders.boolQuery();
            boolean has_subway = Arrays.asList(longs).contains(1);
            if (has_subway) {
                Integer[] tagOther = new Integer[longs.length - 1];
                int idx = 0;
                for (int i = 0; i < longs.length; i++) {
                    if (longs[i].equals(1)) {
                        bool.should(QueryBuilders.termQuery("has_subway", longs[i]));
                    } else {
                        tagOther[idx++] = longs[i];
                    }
                }
                if (tagOther.length != 0) {
                    bool.should(QueryBuilders.termsQuery("tags", tagOther));
                }
                booleanQueryBuilder.must(bool);
            } else {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", longs));
            }
        }


        return booleanQueryBuilder;
    }

    /**
     * 二手房关键字
     *
     * @param keywords
     * @return
     */
    @Override
    public List<String> filterKeyWords(String keywords, String city) {

        List<String> keyword = sellHouseKeywordEsDao.filterKeyWords(keywords, city);

        return keyword;
    }

    @Override
    public BoolQueryBuilder filterSellHouseChoose(SellHouseDoQuery sellHouseDoQuery) {

//        BoolQueryBuilder bqbPlotName = QueryBuilders.boolQuery();
//        SearchResponse searchResponse = null;
//        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())) {
//            bqbPlotName.must(QueryBuilders.boolQuery()
//                    .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
//                    .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
//                    .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
//                    .should(QueryBuilders.matchQuery("plotName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart")));
//            searchResponse = sellHouseEsDao.getPlotByKeyWord(bqbPlotName);
//            long total = searchResponse.getHits().getTotalHits();
//            out: if(total != 0l){
//                break out;
//            }else{
//                BoolQueryBuilder bqb = QueryBuilders.boolQuery();
//                bqb.must(QueryBuilders.multiMatchQuery(sellHouseDoQuery.getKeyword(),"search_nickname").operator(Operator.AND).minimumShouldMatch("100%"));
//                searchResponse = sellHouseEsDao.getPlotByNickNameKeyWord(bqb);
//                if(searchResponse.getHits().getTotalHits()>0l){
//                    SearchHits hits = searchResponse.getHits();
//
//                    SearchHit[] searchHists = hits.getHits();
//                    outFor:for (SearchHit hit : searchHists) {
//                        hit.getSource().get("search_name");
//                        sellHouseDoQuery.setKeyword(hit.getSource().get("search_name").toString());
//                        break outFor ;
//                    }
//                }
//            }
//        }
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
//                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2)));
//                        .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"))
//                        .should(QueryBuilders.matchQuery("plotName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2)));
//                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"))
//                        .should(QueryBuilders.matchQuery("plotName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart")));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("area", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("plotNickname", sellHouseDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("plotName", sellHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart")));
            }
        }
        //楼盘id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getBuildingId()) && sellHouseDoQuery.getBuildingId() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", sellHouseDoQuery.getBuildingId()));
        }

        //附近
        if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() != 0) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                    .point(sellHouseDoQuery.getLat(), sellHouseDoQuery.getLon())
                    .distance(sellHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            booleanQueryBuilder.must(location);
        }


        //商圈id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getAreaId()) && sellHouseDoQuery.getAreaId().length != 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", sellHouseDoQuery.getAreaId()));

        }
        //区域id
        if (StringTool.isNotEmpty((sellHouseDoQuery.getDistrictId())) && sellHouseDoQuery.getDistrictId() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", sellHouseDoQuery.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayLineId()) && sellHouseDoQuery.getSubwayLineId() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", sellHouseDoQuery.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayStationId()) && sellHouseDoQuery.getSubwayStationId().length != 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", sellHouseDoQuery.getSubwayStationId()));
        }


        if (sellHouseDoQuery.getBeginPrice() != 0 && sellHouseDoQuery.getEndPrice() != 0) {
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(sellHouseDoQuery.getBeginPrice()).lte(sellHouseDoQuery.getEndPrice()));
        } else if (sellHouseDoQuery.getBeginPrice() != 0 && sellHouseDoQuery.getEndPrice() == 0) {
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(sellHouseDoQuery.getBeginPrice()));
        } else if (sellHouseDoQuery.getBeginPrice() == 0 && sellHouseDoQuery.getEndPrice() != 0) {
            booleanQueryBuilder
                    .must(QueryBuilders.rangeQuery("houseTotalPrices")
                            .lte(sellHouseDoQuery.getEndPrice()));

        }

        //面积

        if (sellHouseDoQuery.getBeginArea() != 0 && sellHouseDoQuery.getEndArea() != 0) {

            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()).lte(sellHouseDoQuery.getEndArea()));
        } else if (sellHouseDoQuery.getBeginArea() != 0 && sellHouseDoQuery.getEndArea() == 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").gte(sellHouseDoQuery.getBeginArea()));
        } else if (sellHouseDoQuery.getBeginArea() == 0 && sellHouseDoQuery.getEndArea() != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("buildArea").lte(sellHouseDoQuery.getEndArea()));
        }


        //楼龄
        if (StringUtil.isNotNullString(sellHouseDoQuery.getHouseYearId())) {
            String houseYear = sellHouseDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",");
            String[] layoutId = houseYear.split(",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("year")
                        //计算房源建成年代
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(layoutId[i + 1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(layoutId[i])))));
                booleanQueryBuilder.must(boolQueryBuilder);

            }
        }
        //户型(室)
        if (StringTool.isNotEmpty(sellHouseDoQuery.getLayoutId()) && sellHouseDoQuery.getLayoutId().length!=0) {
            Integer[] layoutId = sellHouseDoQuery.getLayoutId();
//            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));

            Integer[] roommore = new Integer[]{6, 7, 8, 9, 10, 11 ,12, 13, 14};
            if(Arrays.asList(layoutId).contains(5)){
                Integer[] roomresult = (Integer[]) ArrayUtils.addAll(layoutId, roommore);
                booleanQueryBuilder.must(QueryBuilders.termsQuery("room", roomresult));
            }else{
                booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
            }
        }


        //朝向
        if (StringTool.isNotEmpty(sellHouseDoQuery.getForwardId()) && sellHouseDoQuery.getForwardId().length!=0) {
            Integer[] forwardId = sellHouseDoQuery.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.termsQuery("forward", forwardId));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(sellHouseDoQuery.getLabelId())) {
            Integer[] longs = sellHouseDoQuery.getLabelId();
            BoolQueryBuilder bool = QueryBuilders.boolQuery();
            boolean has_subway = Arrays.asList(longs).contains(1);
            if (has_subway) {
                Integer[] tagOther = new Integer[longs.length - 1];
                int idx = 0;
                for (int i = 0; i < longs.length; i++) {
                    if (longs[i].equals(1)) {
                        bool.should(QueryBuilders.termQuery("has_subway", longs[i]));
                    } else {
                        tagOther[idx++] = longs[i];
                    }
                }
                if (tagOther.length != 0) {
                    bool.should(QueryBuilders.termsQuery("tags", tagOther));
                }
                booleanQueryBuilder.must(bool);
            } else {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", longs));
            }
        }
        //降价房
        if (StringTool.isNotEmpty(sellHouseDoQuery.getIsCutPrice()) && sellHouseDoQuery.getIsCutPrice() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", sellHouseDoQuery.getIsCutPrice()));
        }
        //洼地房
        if (StringTool.isNotEmpty(sellHouseDoQuery.getIsLowPrice()) && sellHouseDoQuery.getIsLowPrice() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", sellHouseDoQuery.getIsLowPrice()));
        }
        //逢出毕抢
        if (StringTool.isNotEmpty(sellHouseDoQuery.getIsMustRob()) && sellHouseDoQuery.getIsMustRob() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob", sellHouseDoQuery.getIsMustRob()));
        }

        return booleanQueryBuilder;
    }

    /**
     * 获取推荐房源5条查询条件
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    @Override
    public BoolQueryBuilder getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery) {
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        //户型(室)
        Integer[] layoutId = recommendEsf5DoQuery.getLayoutId();
        if (StringTool.isNotEmpty(layoutId)) {
            List<Integer> layoutIds = Arrays.asList(layoutId);
            if (Collections.max(layoutIds) > 4) {
                BoolQueryBuilder bqb = QueryBuilders.boolQuery();
                if (layoutId.length > 1) {
                    int arr = layoutId[layoutId.length - 1];
                    int[] comp = new int[]{layoutId.length - 1};
                    for (int i = 0; i < layoutId.length; i++) {
                        if (layoutId[i] < arr) {
                            arr = layoutId[i];
                            comp[i] = arr;
                        }
                    }
                    bqb.should(QueryBuilders.termsQuery("layout", comp));
                }
                bqb.should(QueryBuilders.rangeQuery("layout").gte(5));
                booleanQueryBuilder.must(bqb);
            } else {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("layout", layoutId));
            }


        }

        //区域id
        Integer[] districtIds = recommendEsf5DoQuery.getDistrictIds();
        if (StringTool.isNotEmpty((districtIds))) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", districtIds));
        }

        //总价(上下浮动10%)
        double beginPrice = recommendEsf5DoQuery.getBeginPrice();
//        if (beginPrice != 0) {
//            beginPrice *= 0.9;
//        }
        double endPrice = recommendEsf5DoQuery.getEndPrice();
//        if (endPrice != 0) {
//            endPrice *= 1.1;
//        }
        if (beginPrice != 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
        } else if (beginPrice == 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(endPrice));
        } else if (endPrice == 0 && beginPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel", 0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", 0));
        return booleanQueryBuilder;
    }
}
