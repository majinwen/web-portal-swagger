package com.toutiao.app.service.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.app.domain.sellhouse.ChooseSellHouseDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FilterSellHouseChooseServiceImpl implements FilterSellHouseChooseService {

    @Autowired
    private SellHouseKeywordEsDao sellHouseKeywordEsDao;

    /**
     * 过滤二手房查询条件
     * @param nearBySellHousesDo
     * @return
     */
    @Override
    public BoolQueryBuilder filterChoose(NearBySellHousesDo nearBySellHousesDo) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(nearBySellHousesDo.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(nearBySellHousesDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHousesDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword()).analyzer("ik_smart")));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", nearBySellHousesDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", nearBySellHousesDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("plotName", nearBySellHousesDo.getKeyword())));
            }
        }
        //商圈名称
        if (StringTool.isNotEmpty(nearBySellHousesDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", nearBySellHousesDo.getAreaId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", nearBySellHousesDo.getAreaId()));

        }

        //房源id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getHouseId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", nearBySellHousesDo.getHouseId()));

        }
        //区域id
        if (StringTool.isNotEmpty((nearBySellHousesDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", nearBySellHousesDo.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", nearBySellHousesDo.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(nearBySellHousesDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayStationId", nearBySellHousesDo.getSubwayStationId()));
        }

        if (StringTool.isNotEmpty(nearBySellHousesDo.getBeginPrice()) && StringTool.isNotEmpty(nearBySellHousesDo.getEndPrice())) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHousesDo.getBeginPrice()).lte(nearBySellHousesDo.getEndPrice())));

        }
        //面积
        if (StringTool.isNotEmpty(nearBySellHousesDo.getBeginArea()) && StringTool.isNotEmpty(nearBySellHousesDo.getEndArea())) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHousesDo.getBeginArea()).lte(nearBySellHousesDo.getEndArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }
        //楼龄
        if (StringUtil.isNotNullString(nearBySellHousesDo.getHouseYearId())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String houseyear = nearBySellHousesDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");

            String[] layoutId = houseyear.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("year")
                        //计算房源建成年代
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i+1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i])))));
                booleanQueryBuilder.must(boolQueryBuilder);

            }
        }
        //户型(室)
        if (StringTool.isNotEmpty(nearBySellHousesDo.getLayout())) {
            Integer[] longs = nearBySellHousesDo.getLayout();
//            booleanQueryBuilder.must();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room",longs)));
        }


        //朝向
        if (StringTool.isNotEmpty(nearBySellHousesDo.getForward())) {
            Integer[] longs =nearBySellHousesDo.getForward();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward",longs)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(nearBySellHousesDo.getHouseLabelId())) {
            Integer[] longs = nearBySellHousesDo.getHouseLabelId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("tags",longs)));
        }





        return booleanQueryBuilder;
    }

    /**
     * 二手房关键字
     * @param keywords
     * @return
     */
    @Override
    public List<String> filterKeyWords(String keywords) {

        List<String> keyword = sellHouseKeywordEsDao.filterKeyWords(keywords);

        return keyword;
    }

    @Override
    public BoolQueryBuilder filterSellHouseChoose(ChooseSellHouseDo chooseSellHouseDo) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(chooseSellHouseDo.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(chooseSellHouseDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyword()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyword()).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(chooseSellHouseDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyword()).analyzer("ik_smart")));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", chooseSellHouseDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", chooseSellHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", chooseSellHouseDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("plotName", chooseSellHouseDo.getKeyword())));
            }
        }
        //商圈名称
        if (StringTool.isNotEmpty(chooseSellHouseDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", chooseSellHouseDo.getAreaId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", chooseSellHouseDo.getAreaId()));

        }
        //区域id
        if (StringTool.isNotEmpty((chooseSellHouseDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", chooseSellHouseDo.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", chooseSellHouseDo.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(chooseSellHouseDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayStationId", chooseSellHouseDo.getSubwayStationId()));
        }

        if (StringTool.isNotEmpty(chooseSellHouseDo.getBeginPrice()) && StringTool.isNotEmpty(chooseSellHouseDo.getEndPrice())) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(chooseSellHouseDo.getBeginPrice()).lte(chooseSellHouseDo.getEndPrice())));

        }
        //面积
        if (StringTool.isNotEmpty(chooseSellHouseDo.getBeginArea()) && StringTool.isNotEmpty(chooseSellHouseDo.getEndArea())) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(chooseSellHouseDo.getBeginArea()).lte(chooseSellHouseDo.getEndArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }
        //楼龄
        if (StringUtil.isNotNullString(chooseSellHouseDo.getHouseYearId())) {
            String houseYear = chooseSellHouseDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
            String[] layoutId = houseYear.split(",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("year")
                        //计算房源建成年代
                        .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i+1]))))
                        .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(layoutId[i])))));
                booleanQueryBuilder.must(boolQueryBuilder);

            }
        }
        //户型(室)
        if (StringTool.isNotEmpty(chooseSellHouseDo.getLayout())) {
            Integer[] longs = chooseSellHouseDo.getLayout();
//            booleanQueryBuilder.must();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room",longs)));
        }


        //朝向
        if (StringTool.isNotEmpty(chooseSellHouseDo.getForward())) {
            Integer[] longs =chooseSellHouseDo.getForward();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward",longs)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(chooseSellHouseDo.getHouseLabelId())) {
            Integer[] longs = chooseSellHouseDo.getHouseLabelId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("tags",longs)));
        }

        return booleanQueryBuilder;
    }
}
