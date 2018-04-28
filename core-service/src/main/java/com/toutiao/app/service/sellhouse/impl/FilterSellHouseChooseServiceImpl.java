package com.toutiao.app.service.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.domain.sellhouse.SellHouseQueryDo;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
     * @param
     *
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
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", nearBySellHouseQueryDo.getAreaId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", nearBySellHouseQueryDo.getAreaId()));

        }
        //区域id
        if (StringTool.isNotEmpty((nearBySellHouseQueryDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", nearBySellHouseQueryDo.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", nearBySellHouseQueryDo.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayStationId", nearBySellHouseQueryDo.getSubwayStationId()));
        }

        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getBeginPrice()) && StringTool.isNotEmpty(nearBySellHouseQueryDo.getEndPrice())) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()).lte(nearBySellHouseQueryDo.getEndPrice())));

        }
        //总价
        if(StringTool.isNotEmpty(nearBySellHouseQueryDo.getBeginPrice()) && StringTool.isNotEmpty(nearBySellHouseQueryDo.getEndPrice()))
        {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()).lte(nearBySellHouseQueryDo.getEndPrice()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }else if (nearBySellHouseQueryDo.getBeginPrice()==null && null!=nearBySellHouseQueryDo.getEndPrice())
        {
            nearBySellHouseQueryDo.setBeginPrice(0.0);
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()).lte(nearBySellHouseQueryDo.getEndPrice()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }else if(nearBySellHouseQueryDo.getEndPrice()==null && null!=nearBySellHouseQueryDo.getBeginPrice())
        {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("houseTotalPrices").gte(nearBySellHouseQueryDo.getBeginPrice()));
        }

        //面积
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getBeginArea()) && StringTool.isNotEmpty(nearBySellHouseQueryDo.getEndArea())) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHouseQueryDo.getBeginArea()).lte(nearBySellHouseQueryDo.getEndArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }else if(null==nearBySellHouseQueryDo.getBeginArea() && null!= nearBySellHouseQueryDo.getEndArea())
        {
            nearBySellHouseQueryDo.setBeginArea(0.0);
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHouseQueryDo.getBeginArea()).lte(nearBySellHouseQueryDo.getEndArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }
        else if(null==nearBySellHouseQueryDo.getEndArea() && null!= nearBySellHouseQueryDo.getBeginArea())
        {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(nearBySellHouseQueryDo.getBeginArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }
        //楼龄
        if (StringUtil.isNotNullString(nearBySellHouseQueryDo.getHouseYearId())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String houseyear = nearBySellHouseQueryDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");

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
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getLayoutId())) {
            Integer[] longs = nearBySellHouseQueryDo.getLayoutId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room",longs)));
        }


        //朝向
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getForwardId())) {
            Integer[] longs =nearBySellHouseQueryDo.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward",longs)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getLabelId())) {
            Integer[] longs = nearBySellHouseQueryDo.getLabelId();
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
    public BoolQueryBuilder filterSellHouseChoose(SellHouseQueryDo sellHouseQueryDo) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if (StringTool.isNotBlank(sellHouseQueryDo.getKeyword())) {
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseQueryDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseQueryDo.getKeyword()).analyzer("ik_max_word").boost(2))
                        .should(QueryBuilders.matchQuery("area", sellHouseQueryDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", sellHouseQueryDo.getKeyword()).analyzer("ik_smart").boost(2)));
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseQueryDo.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("area", sellHouseQueryDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseQueryDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("plotName", sellHouseQueryDo.getKeyword()).analyzer("ik_smart")));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("plotName_accurate", sellHouseQueryDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", sellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("houseBusinessName", sellHouseQueryDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("plotName", sellHouseQueryDo.getKeyword())));
            }
        }
        //商圈名称
        if (StringTool.isNotEmpty(sellHouseQueryDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", sellHouseQueryDo.getAreaId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(sellHouseQueryDo.getAreaId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", sellHouseQueryDo.getAreaId()));

        }
        //区域id
        if (StringTool.isNotEmpty((sellHouseQueryDo.getDistrictId()))) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", sellHouseQueryDo.getDistrictId()));

        }

        //地铁线id
        if (StringTool.isNotEmpty(sellHouseQueryDo.getSubwayLineId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", sellHouseQueryDo.getSubwayLineId()));

        }
        //地铁站id
        if (StringTool.isNotEmpty(sellHouseQueryDo.getSubwayStationId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("subwayStationId", sellHouseQueryDo.getSubwayStationId()));
        }

        if (StringTool.isNotEmpty(sellHouseQueryDo.getBeginPrice()) && StringTool.isNotEmpty(sellHouseQueryDo.getEndPrice())) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices")
                            .gte(sellHouseQueryDo.getBeginPrice()).lte(sellHouseQueryDo.getEndPrice())));
        }
        //面积
        if (StringTool.isNotEmpty(sellHouseQueryDo.getBeginArea()) && StringTool.isNotEmpty(sellHouseQueryDo.getEndArea())) {
            booleanQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gte(sellHouseQueryDo.getBeginArea()).lte(sellHouseQueryDo.getEndArea()));
            booleanQueryBuilder.must(booleanQueryBuilder);
        }
        //楼龄
        if (StringUtil.isNotNullString(sellHouseQueryDo.getHouseYearId())) {
            String houseYear = sellHouseQueryDo.getHouseYearId().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
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
        if (StringTool.isNotEmpty(sellHouseQueryDo.getLayoutId())) {
            Integer[] layoutId = sellHouseQueryDo.getLayoutId();
//            booleanQueryBuilder.must();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room",layoutId)));
        }


        //朝向
        if (StringTool.isNotEmpty(sellHouseQueryDo.getForwardId())) {
            Integer[] forwardId =sellHouseQueryDo.getForwardId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("forward",forwardId)));
        }


        //标签(满二，满三，满五)
        if (StringTool.isNotEmpty(sellHouseQueryDo.getLabelId())) {
            Integer[] labelId = sellHouseQueryDo.getLabelId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("tags",labelId)));
        }

        return booleanQueryBuilder;
    }
}
