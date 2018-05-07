package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.NearbyPlotsEsDao;
import com.toutiao.app.domain.plot.NearbyPlotsDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.plot.NearbyPlotsRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.rent.RentRestService;
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
    @Autowired
    private PlotsEsfRestService plotsEsfRestService;
    @Autowired
    private RentRestService rentRestService;

    /**
     * 根据用户坐标获取用户附近小区列表
     * @param nearbyPlotsDoQuery
     * @return
     */
    @Override
    public PlotDetailsFewDomain queryNearbyPlotsListByUserCoordinate(NearbyPlotsDoQuery nearbyPlotsDoQuery) {
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        PlotDetailsFewDomain plotDetailsFewDomain = new PlotDetailsFewDomain();
        //小区筛选条件
        BoolQueryBuilder boolQueryBuilder = getNearbyPlotsFilterCondition(nearbyPlotsDoQuery);
        //附近5km
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("location")
                .point(nearbyPlotsDoQuery.getLat(), nearbyPlotsDoQuery.getLon()).distance(nearbyPlotsDoQuery.getDistance(), DistanceUnit.KILOMETERS);
//        srb.setPostFilter(location1).setFrom((pageNum-1) * pageSize).setSize(villageRequest.getSize());
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", nearbyPlotsDoQuery.getLat(), nearbyPlotsDoQuery.getLon());
        sort.unit(DistanceUnit.KILOMETERS);
        sort.order(SortOrder.ASC);
        sort.point(nearbyPlotsDoQuery.getLat(), nearbyPlotsDoQuery.getLon());

        int pageNum = 1;
        if(nearbyPlotsDoQuery.getPageNum()!=null && nearbyPlotsDoQuery.getPageNum()>1){
            pageNum = nearbyPlotsDoQuery.getPageNum();
        }
        SearchResponse searchResponse = nearbyPlotsEsDao.queryNearbyPlotsListByUserCoordinate(geoDistanceQueryBuilder,sort,boolQueryBuilder,
                nearbyPlotsDoQuery.getKeyword(), pageNum,nearbyPlotsDoQuery.getPageSize());

        long nearbyPlotsTotal = searchResponse.getHits().totalHits;

        if(nearbyPlotsTotal==0L){
            throw new BaseException(PlotsInterfaceErrorCodeEnum.NEARBY_PLOTS_NOT_FOUND,"附近小区楼盘列表为空");
        }
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
            //二手房总数
            try {
                PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotDetailsFewDo.getId());
                plotDetailsFewDo.setSellHouseTotalNum(Math.toIntExact(plotsEsfRoomCountDomain.getTotalCount()));
            }catch (BaseException e){
                logger.error("获取小区下二手房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
                if (e.getCode()==50301){
                    plotDetailsFewDo.setSellHouseTotalNum(0);
                }
            }
            //租房总数
            try {
                RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotDetailsFewDo.getId());
                plotDetailsFewDo.setRentTotalNum(rentNumListDo.getTotalNum());
            }catch (BaseException e){
                logger.error("获取小区下租房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
                if (e.getCode()==50401){
                    plotDetailsFewDo.setRentTotalNum(0);
                }
            }

            plotDetailsFewDoList.add(plotDetailsFewDo);
        }
        plotDetailsFewDomain.setNearbyPlots(plotDetailsFewDoList);
        plotDetailsFewDomain.setTotals(nearbyPlotsTotal);
        return plotDetailsFewDomain;
    }


    /**
     * 根据入参过滤附近小区筛选条件
     * @param nearbyPlotsDoQuery
     * @return
     */
    public BoolQueryBuilder getNearbyPlotsFilterCondition(NearbyPlotsDoQuery nearbyPlotsDoQuery){

        BoolQueryBuilder boolQueryBuilder = boolQuery();

        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(nearbyPlotsDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsDoQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsDoQuery.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(nearbyPlotsDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsDoQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc", nearbyPlotsDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("rc_accurate", nearbyPlotsDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area", nearbyPlotsDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea",nearbyPlotsDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //区域id
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getDistrictId())){
            boolQueryBuilder.must(termQuery("areaId",nearbyPlotsDoQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getAreaId())){
            boolQueryBuilder.must(termQuery("tradingAreaId",nearbyPlotsDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getSubwayLineId())){
            boolQueryBuilder.must(termQuery("subwayLineId",nearbyPlotsDoQuery.getSubwayLineId()));
        }
        //地铁站id
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getSubwayStationId())){
            boolQueryBuilder.must(termQuery("metroStationId",nearbyPlotsDoQuery.getSubwayStationId()));
        }
        //均价
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getBeginPrice())&&StringTool.isNotEmpty(nearbyPlotsDoQuery.getEndPrice())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(nearbyPlotsDoQuery.getBeginPrice()).lte(nearbyPlotsDoQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getHouseYearId())){
            String[] age = nearbyPlotsDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(nearbyPlotsDoQuery.getLabelId())){
            Integer[] labelId = nearbyPlotsDoQuery.getLabelId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("labelId",labelId));
        }
        //房源面积大小
        if(nearbyPlotsDoQuery.getBeginArea()!=null && nearbyPlotsDoQuery.getEndArea()!=null){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(nearbyPlotsDoQuery.getBeginArea()).lte(nearbyPlotsDoQuery.getEndArea()), ScoreMode.None));

        }else if(nearbyPlotsDoQuery.getBeginArea()==null && nearbyPlotsDoQuery.getEndArea()!=null){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .lte(nearbyPlotsDoQuery.getEndArea()), ScoreMode.None));
        }else if(nearbyPlotsDoQuery.getBeginArea()!=null && nearbyPlotsDoQuery.getEndArea()==null){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea")
                    .gte(nearbyPlotsDoQuery.getBeginArea()), ScoreMode.None));
        }

        //是否上架
        boolQueryBuilder.must(termQuery("is_approve", 1));

        //是否删除
        boolQueryBuilder.must(termQuery("is_del", 0));
        return boolQueryBuilder;

    }
}
