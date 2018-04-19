package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.NearbyPlotsEsDao;
import com.toutiao.app.domain.plot.NearbyPlotsListDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
import com.toutiao.app.service.plot.NearbyPlotsRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * 附近小区接口服务实现类
 *
 */
@Service
public class NearbyPlotsRestServiceImpl implements NearbyPlotsRestService {

    private static final Logger logger = LoggerFactory.getLogger(NearbyPlotsRestServiceImpl.class);

    @Value("${plot.child.type}")
    private String childType;
    @Autowired
    private NearbyPlotsEsDao nearbyPlotsEsDao;

    /**
     * 根据用户坐标获取用户附近小区列表
     * @param nearbyPlotsListDo
     * @return
     */
    @Override
    public PlotDetailsFewDomain queryNearbyPlotsListByUserCoordinate(NearbyPlotsListDo nearbyPlotsListDo) {
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        PlotDetailsFewDomain plotDetailsFewDomain = new PlotDetailsFewDomain();
        //小区筛选条件
        BoolQueryBuilder boolQueryBuilder = getNearbyPlotsFilterCondition(nearbyPlotsListDo);
        //附近5km
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("location")
                .point(nearbyPlotsListDo.getLat(), nearbyPlotsListDo.getLon()).distance(nearbyPlotsListDo.getDistance(), DistanceUnit.KILOMETERS);
//        srb.setPostFilter(location1).setFrom((pageNum-1) * pageSize).setSize(villageRequest.getSize());
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearbyPlotsListDo.getLat(), nearbyPlotsListDo.getLon());
        sort.unit(DistanceUnit.KILOMETERS);
        sort.order(SortOrder.ASC);
        sort.point(nearbyPlotsListDo.getLat(), nearbyPlotsListDo.getLon());

        int pageNum = 1;
        if(nearbyPlotsListDo.getPageNum()!=null && nearbyPlotsListDo.getPageNum()>1){
            pageNum = nearbyPlotsListDo.getPageNum();
        }
        SearchResponse searchResponse = nearbyPlotsEsDao.queryNearbyPlotsListByUserCoordinate(geoDistanceQueryBuilder,sort,boolQueryBuilder,
                nearbyPlotsListDo.getKeyword(), pageNum,nearbyPlotsListDo.getSize());

        long nearbyPlotsTotal = searchResponse.getHits().totalHits;

        if(nearbyPlotsTotal==0L){
            throw new BaseException(PlotsInterfaceErrorCodeEnum.NEARBY_PLOTS_NOT_FOUND,"附近小区楼盘列表为空");
        }
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
            plotDetailsFewDoList.add(plotDetailsFewDo);
        }
        plotDetailsFewDomain.setNearbyPlots(plotDetailsFewDoList);
        plotDetailsFewDomain.setTotals(nearbyPlotsTotal);
        return plotDetailsFewDomain;
    }


    /**
     * 根据入参过滤附近小区筛选条件
     * @param nearbyPlotsListDo
     * @return
     */
    public BoolQueryBuilder getNearbyPlotsFilterCondition(NearbyPlotsListDo nearbyPlotsListDo){

        BoolQueryBuilder boolQueryBuilder = boolQuery();

        if (StringTool.isNotEmpty(nearbyPlotsListDo.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(nearbyPlotsListDo.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsListDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsListDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsListDo.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsListDo.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(nearbyPlotsListDo.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsListDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsListDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsListDo.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsListDo.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsListDo.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsListDo.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsListDo.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsListDo.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //区域id
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getDistrictId())){
            boolQueryBuilder.must(termQuery("areaId",nearbyPlotsListDo.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getAreaId())){
            boolQueryBuilder.must(termQuery("tradingAreaId",nearbyPlotsListDo.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getSubwayLineId())){
            boolQueryBuilder.must(termQuery("subwayLineId",nearbyPlotsListDo.getSubwayLineId()));
        }
        //地铁站id
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getSubwayStationId())){
            boolQueryBuilder.must(termQuery("metroStationId",nearbyPlotsListDo.getSubwayStationId()));
        }
        //均价
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getBeginPrice())&&StringTool.isNotEmpty(nearbyPlotsListDo.getEndPrice())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(nearbyPlotsListDo.getBeginPrice()).lte(nearbyPlotsListDo.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getAge())){
            String[] age = nearbyPlotsListDo.getAge().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(nearbyPlotsListDo.getLabelId())){
            boolQueryBuilder.must(termsQuery("labelId",nearbyPlotsListDo.getLabelId().split(",")));
        }
        //房源面积大小
        if ((StringTool.isNotEmpty(nearbyPlotsListDo.getHouseAreaSize()))){
            BoolQueryBuilder QueryBuilder = QueryBuilders.boolQuery();
            String[] houseArea = nearbyPlotsListDo.getHouseAreaSize().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            for (int i = 0; i < houseArea.length; i = i + 2) {
                if (i + 1 > houseArea.length) {
                    break;
                }
                BoolQueryBuilder areaSize = QueryBuilder.should(JoinQueryBuilders
                        .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseArea[i]).lte(houseArea[i + 1]), ScoreMode.None));
                boolQueryBuilder.must(areaSize);
            }

        }

        //是否上架
        boolQueryBuilder.must(termQuery("is_approve", 1));

        //是否删除
        boolQueryBuilder.must(termQuery("is_del", 0));
        return boolQueryBuilder;

    }
}
