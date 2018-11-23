package com.toutiao.app.service.mapSearch.impl;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDistrictDomain;
import com.toutiao.app.domain.mapSearch.EsfMapSearchDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.web.common.util.mapSearch.MapGroupUtil;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName EsfMapSearchRestServiceImpl
 * @Author jiangweilong
 * @Date 2018/11/23 3:33 PM
 * @Description:
 **/

@Service
public class EsfMapSearchRestServiceImpl implements EsfMapSearchRestService {

    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;


    /**
     * 地图找房-二手房查询
     * @param esfMapSearchDoQuery
     * @return
     */
    @Override
    public Object esfMapSearch(EsfMapSearchDoQuery esfMapSearchDoQuery, String city) {

        //判断当前地图查询级别


        Integer groupTypeId = MapGroupUtil.returnMapGrouId(esfMapSearchDoQuery.getGroupType());
        //groupTypeId 1：区县，2：商圈，3：社区






        return null;
    }


    /**
     * 按照区域搜索二手房
     * @param esfMapSearchDoQuery
     * @return
     */
    public EsfMapSearchDistrictDomain esfMapSearchByDistrict(EsfMapSearchDoQuery esfMapSearchDoQuery, String city){

        EsfMapSearchDistrictDomain esfMapSearchDistrictDomain = new EsfMapSearchDistrictDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoQuery, sellHouseDoQuery);

        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        GeoPoint topRight = new GeoPoint(esfMapSearchDoQuery.getMaxLatitude(),esfMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(esfMapSearchDoQuery.getMinLatitude(),esfMapSearchDoQuery.getMinLongitude());

        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("housePlotLocation").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);






        return esfMapSearchDistrictDomain;
    }
}
