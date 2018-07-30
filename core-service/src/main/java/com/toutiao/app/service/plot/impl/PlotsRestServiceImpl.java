package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.domain.favorite.PlotIsFavoriteDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.service.map.MapService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlotsRestServiceImpl implements PlotsRestService {
    private static final Logger logger = LoggerFactory.getLogger(PlotsRestServiceImpl.class);

    @Value("${distance}")
    private Double distance;
    @Value("${plot.child.type}")
    private String childType;
    @Autowired
    private PlotEsDao plotEsDao;
    @Autowired
    private PlotsRestService plotsRestService;
    @Autowired
    private MapService mapService;
    @Autowired
    private PlotsEsfRestService plotsEsfRestService;
    @Autowired
    private RentRestService rentRestService;
    @Autowired
    private FavoriteRestService favoriteRestService;


    /**
     * 小区详情信息
     * @param plotId
     * @return
     */
    @Override
    public PlotDetailsDo queryPlotDetailByPlotId(Integer plotId, String city) {
        String details = "";
        try {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("id",plotId));
            SearchResponse searchResponse = plotEsDao.queryPlotDetail(boolQueryBuilder,city);
            SearchHit[] hits = searchResponse.getHits().getHits();
            PlotDetailsDo plotDetailsDo = new PlotDetailsDo();
            for (SearchHit searchHit : hits) {
                details = searchHit.getSourceAsString();
            }
            if (StringUtils.isNotEmpty(details))
            {
                UserBasic userBasic = UserBasic.getCurrent();

                plotDetailsDo = JSON.parseObject(details,PlotDetailsDo.class);
//                String[] photos = plotDetailsDo.getPhoto().get(0).split(",");
                plotDetailsDo.setPhotos(plotDetailsDo.getPhoto().toArray(new String[0]));
                if(StringTool.isNotEmpty(userBasic)){
                    PlotIsFavoriteDoQuery plotIsFavoriteDoQuery = new PlotIsFavoriteDoQuery();
                    plotIsFavoriteDoQuery.setUserId(Integer.valueOf(userBasic.getUserId()));
                    plotIsFavoriteDoQuery.setBuildingId(plotDetailsDo.getId());
                    Boolean isFavorite = favoriteRestService.getPlotIsFavorite(plotIsFavoriteDoQuery);
                    plotDetailsDo.setIsFavorite(isFavorite);
                }

                if ("商电".equals(plotDetailsDo.getElectricSupply())){
                    plotDetailsDo.setElectricFee(1.33);
                }else {
                    plotDetailsDo.setElectricFee(0.48);
                }
                if ("商水".equals(plotDetailsDo.getWaterSupply())){
                    plotDetailsDo.setWaterFee(6.00);
                }else {
                    plotDetailsDo.setWaterFee(5.00);
                }
                if ("0".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("未知");
                }
                if ("1".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("集中供暖");
                }
                if ("2".equals(plotDetailsDo.getHeatingMode())){
                    plotDetailsDo.setHeatingMode("自供暖");
                }
                if (null!=plotDetailsDo.getElevator() &&"1".equals(plotDetailsDo.getElevator()))
                {
                    plotDetailsDo.setHasElevator("有");
                }
                if(null!=plotDetailsDo.getElevator() && "2".equals(plotDetailsDo.getElevator()))
                {
                    plotDetailsDo.setHasElevator("无");
                }
                return plotDetailsDo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (details.isEmpty())
        {
            throw  new  BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_DETAILS_NOT_FOUND);
        }
        return null;
    }

    /**
     * 获取小区周边配套
     * @param plotId
     * @return
     */
    @Override
    public PlotTrafficDo queryPlotDataInfo(Integer plotId){
            MapInfo mapInfo = new MapInfo();
            PlotTrafficDo plotTrafficDo=new PlotTrafficDo();
            mapInfo =  mapService.getMapInfo(plotId);
            if (mapInfo==null)
            {
                throw  new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_TRAFFIC_NOT_FOUND);
            }
            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            //获取小区交通
            JSONObject businfo= (JSONObject) datainfo.get("gongjiao");
            if (businfo.size()>0)
            {
                plotTrafficDo.setBusStation(businfo.get("name").toString());
                plotTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
            }
            //获取地铁和环线位置
            PlotDetailsDo plotDetailsDo = plotsRestService.queryPlotDetailByPlotId(plotId, CityUtils.getCity());
            if (!"".equals(plotDetailsDo.getTrafficInformation()))
            {  String []  trafficInfo=plotDetailsDo.getTrafficInformation().split("\\$");
                plotTrafficDo.setSubwayStation(trafficInfo[1]);
                plotTrafficDo.setSubwayLine(trafficInfo[0]);
                plotTrafficDo.setSubwayDistance(Double.valueOf(trafficInfo[2]));
            }
            if (null!=plotDetailsDo.getRingRoadName() && !"".equals(plotDetailsDo.getRingRoadName()))
            {
                plotTrafficDo.setRingRoadName(plotDetailsDo.getRingRoadName());
            }
            if (null!=plotDetailsDo.getRingRoadDistance())
            {
                plotTrafficDo.setRingRoadDistance(Double.valueOf(plotDetailsDo.getRingRoadDistance().toString()));
            }
            return plotTrafficDo;
    }

    /**
     * 小区收藏列表
     * @param list
     * @return
     */
    @Override
    public PlotFavoriteListDo queryPlotListByPlotIdList(List list, Integer pageNum , Integer size) {
        PlotFavoriteListDo plotFavoriteListDo = new PlotFavoriteListDo();
        List<UserFavoritePlotDo> list1 = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("id",list));
        SearchResponse searchResponse = plotEsDao.queryPlotListByPlotIdList(boolQueryBuilder, (pageNum-1)*size, size);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            for (SearchHit hit:hits){
                String sourceAsString = hit.getSourceAsString();
                UserFavoritePlotDo userFavoritePlotDo = JSON.parseObject(sourceAsString, UserFavoritePlotDo.class);
                list1.add(userFavoritePlotDo);
            }
            plotFavoriteListDo.setData(list1);
            plotFavoriteListDo.setTotalNum(searchResponse.getHits().getTotalHits());
        }
        return plotFavoriteListDo;
    }


    /**
     * 附近小区
     * @param lat
     * @param lon
     * @param plotId
     * @return
     */
    @Override
    public List<PlotDetailsFewDo> queryAroundPlotByLocation(Double lat, Double lon, Integer plotId){
        try {
            List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //从该坐标查询距离为distance内的小区
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
            //按照距离排序由近到远并获取小区之间的距离
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);
            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", plotId));
            SearchResponse searchResponse = plotEsDao.queryNearPlotByLocationAndDistance(boolQueryBuilder, location, sort);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map<String, Object> source = hit.getSource();
                    PlotDetailsFewDo plotDetailsFewDo = PlotDetailsFewDo.class.newInstance();
                    BeanUtils.populate(plotDetailsFewDo, source);
                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
                    plotDetailsFewDoList.add(plotDetailsFewDo);
                }
                return plotDetailsFewDoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 小区列表条件筛选
     * @param plotListDoQuery
     * @return
     */
    @Override
    public PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery, HttpServletRequest request, HttpServletResponse response, String city) {
        String key = "";
        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //关键字
        if (StringTool.isNotEmpty(plotListDoQuery.getKeyword())){
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_smart"));
            }else if(StringUtil.isNotNullString(AreaMap.getAreas(plotListDoQuery.getKeyword()))){
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()).analyzer("ik_max_word").boost(2));
            }else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("rc_accurate", plotListDoQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("rc", plotListDoQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("area", plotListDoQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("tradingArea", plotListDoQuery.getKeyword()));
            }
            boolQueryBuilder.must(queryBuilder);
        }
        //区域id
        if (StringTool.isNotEmpty(plotListDoQuery.getDistrictId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId", plotListDoQuery.getDistrictId()));
        }
        //商圈id
        if (StringTool.isNotEmpty(plotListDoQuery.getAreaId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("tradingAreaId", plotListDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayLineId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("subwayLineId", plotListDoQuery.getSubwayLineId()));
            key = String.valueOf(plotListDoQuery.getSubwayLineId());
        }
        //地铁站id
        if (StringTool.isNotEmpty(plotListDoQuery.getSubwayStationId())){
            boolQueryBuilder.must(QueryBuilders.termQuery("metroStationId", plotListDoQuery.getSubwayStationId()));
            key = key+"$"+ plotListDoQuery.getSubwayStationId();
        }
        //均价
        if (plotListDoQuery.getBeginPrice()!=0&&plotListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()).lte(plotListDoQuery.getEndPrice()));
        }else if(plotListDoQuery.getBeginPrice()!=0&&plotListDoQuery.getEndPrice()==0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(plotListDoQuery.getBeginPrice()));
        }else if(plotListDoQuery.getBeginPrice()==0&&plotListDoQuery.getEndPrice()!=0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").lte(plotListDoQuery.getEndPrice()));
        }
        //楼龄
        if (StringTool.isNotEmpty(plotListDoQuery.getHouseYearId())){
            String[] age = plotListDoQuery.getHouseYearId().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age")
                    .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[1]))))
                    .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(age[0])))));
        }
        //标签
        if (StringTool.isNotEmpty(plotListDoQuery.getLabelId())){
            Integer[] labelId = plotListDoQuery.getLabelId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("labelId",labelId));
            for (Integer i:labelId){
                if (i==1){
                    boolQueryBuilder.must(QueryBuilders.termQuery("has_subway",1));
                }
            }
        }


//        if ((StringTool.isNotEmpty(nearbyPlotsDoQuery.getHouseAreaSize()))){
//            String[] houseArea = nearbyPlotsDoQuery.getHouseAreaSize().replaceAll("\\[", "").replaceAll("]", "").replaceAll("-", ",").split(",");
//            BoolQueryBuilder QueryBuilder = QueryBuilders.boolQuery();
//            for (int i = 0; i < houseArea.length; i = i + 2) {
//                if (i + 1 > houseArea.length) {
//                    break;
//                }
//                BoolQueryBuilder areaSize = QueryBuilder.should(JoinQueryBuilders
//                        .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseArea[i]).lte(houseArea[i + 1]), ScoreMode.None));
//                boolQueryBuilder.must(areaSize);



//            }
//
//        }

        //房源面积大小
        if(plotListDoQuery.getBeginArea()!=0 && plotListDoQuery.getEndArea()!=0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()).lte(plotListDoQuery.getEndArea()), ScoreMode.None));

        }else if(plotListDoQuery.getBeginArea()!=0 && plotListDoQuery.getEndArea()==0){
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("sellHouseArea")
                    .gte(plotListDoQuery.getBeginArea()), ScoreMode.None));
        }else if(plotListDoQuery.getBeginArea()==0 && plotListDoQuery.getEndArea()!=0){

            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(childType, QueryBuilders.rangeQuery("sellHouseArea")
                    .lte(plotListDoQuery.getEndArea()), ScoreMode.None));
        }

        GeoDistanceSortBuilder sort = null;
        //坐标
        if (StringTool.isNotEmpty(plotListDoQuery.getLat())&&plotListDoQuery.getLat()>0&&plotListDoQuery.getLon()>0&&StringTool.isNotEmpty(plotListDoQuery.getLon())){
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(plotListDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
            //排序
            sort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
//            sort.order(SortOrder.ASC);
            sort.point(plotListDoQuery.getLat(), plotListDoQuery.getLon());

        }



        /**
         * top 50小区
         */
        if(StringTool.isNotEmpty(plotListDoQuery.getIsTop())){
            int [] isTop={plotListDoQuery.getIsTop()};
            boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId", isTop));
        }

        Integer from = 0;
        //分页起始位置
        if (StringTool.isNotEmpty(plotListDoQuery.getPageNum())&& plotListDoQuery.getPageNum()>1&&StringTool.isNotEmpty(plotListDoQuery.getPageSize())&& plotListDoQuery.getPageSize()>0){
            from = (plotListDoQuery.getPageNum()-1)* plotListDoQuery.getPageSize();
        }



        //是否上架
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));

        //是否删除
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));

        //级别排序
        FieldSortBuilder levelSort = SortBuilders.fieldSort("level").order(SortOrder.ASC);

        //小区分数排序
        FieldSortBuilder plotScoreSort = SortBuilders.fieldSort("plotScore").order(SortOrder.DESC);

        //坐标
//        Map<String,Double> map = new HashMap<>();
//        map.put("lat",plotListDoQuery.getLat());
//        map.put("lon",plotListDoQuery.getLon());
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));

        //设置高斯函数按照距离进行打分
//        GaussDecayFunctionBuilder functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location",json,"2.5km","0.5km" ,0.5);

        //多个内部分数用乘法,新老分数之间用乘法
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, functionBuilder).scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);

        PlotListDo plotListDo = new PlotListDo();
        SearchResponse searchResponse = null;
        if ((StringTool.isNotEmpty(plotListDoQuery.getLat())&&plotListDoQuery.getLat()>0&&plotListDoQuery.getLon()>0&&StringTool.isNotEmpty(plotListDoQuery.getLon()))){
            searchResponse = plotEsDao.queryPlotListByRequirementAndKeywordV1(from, boolQueryBuilder, plotListDoQuery.getPageSize(),sort,levelSort,plotScoreSort,city);
        }else {
            searchResponse = plotEsDao.queryCommonPlotList(from, boolQueryBuilder, plotListDoQuery.getPageSize(),plotListDoQuery.getKeyword(),request,response ,city);
            if (searchResponse!=null){
                SearchHit[] hits = searchResponse.getHits().getHits();

                if(hits.length > 0){
                    for (SearchHit hit:hits){
                        commonMethod(hit,key,plotDetailsFewDoList,city);
                    }
                }else{
                    throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_NOT_FOUND,"小区楼盘列表为空");
                }

            }
            plotListDo.setPlotList(plotDetailsFewDoList);
            plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
            return plotListDo;
        }

        long oneKM_size = searchResponse.getHits().getTotalHits();
        if(searchResponse != null){
            int reslocationinfo = searchResponse.getHits().getHits().length;
            if(reslocationinfo == 10){
                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHists = hits.getHits();
                for (SearchHit hit : searchHists) {
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }else if(reslocationinfo < 10 && reslocationinfo>0){
                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHists = hits.getHits();
                for (SearchHit hit : searchHists) {
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
                SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(0, booleanQueryBuilder,  plotListDoQuery.getPageSize() - reslocationinfo,plotListDoQuery.getKeyword(),request,response, city);
                SearchHit[] hits1 = searchResponse1.getHits().getHits();
                for (SearchHit hit:hits1){
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }else if(reslocationinfo == 0){
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                booleanQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
                Integer newFrom = (plotListDoQuery.getPageNum()-1)*plotListDoQuery.getPageSize();
                if(oneKM_size>0){
                 newFrom = (int) ((plotListDoQuery.getPageNum()-1)*plotListDoQuery.getPageSize() - (oneKM_size/plotListDoQuery.getPageSize()+1)*plotListDoQuery.getPageSize());
                }
                SearchResponse searchResponse1 = plotEsDao.queryCommonPlotList(newFrom, booleanQueryBuilder, plotListDoQuery.getPageSize(),plotListDoQuery.getKeyword(),request,response, city);
                SearchHit[] hits1 = searchResponse1.getHits().getHits();
                for (SearchHit hit:hits1){
                    commonMethod(hit,key,plotDetailsFewDoList,city);
                }
            }
        }

        plotListDo.setPlotList(plotDetailsFewDoList);
        plotListDo.setTotalCount((int) searchResponse.getHits().getTotalHits());
        return plotListDo;
    }

    /**
     * 遍历结果
     * @param hit
     * @param key
     * @return
     */
    public void commonMethod(SearchHit hit,String key,List<PlotDetailsFewDo> plotDetailsFewDoList, String city){
        String sourceAsString = hit.getSourceAsString();
        PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
        if (null!=plotDetailsFewDo.getMetroWithPlotsDistance()&&""!=key){
            plotDetailsFewDo.setSubwayDistanceInfo((String) plotDetailsFewDo.getMetroWithPlotsDistance().get(key));
        }
        plotDetailsFewDo.setMetroWithPlotsDistance(null);
        //二手房总数
        try {
            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotDetailsFewDo.getId(),city);
            plotDetailsFewDo.setSellHouseTotalNum(Math.toIntExact(plotsEsfRoomCountDomain.getTotalCount()));
        }catch (BaseException e){
            // logger.error("获取小区下二手房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
            if (e.getCode()==50301){
                plotDetailsFewDo.setSellHouseTotalNum(0);
            }
        }
        //租房总数
        try {
            RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotDetailsFewDo.getId(),city);
            plotDetailsFewDo.setRentTotalNum(rentNumListDo.getTotalNum());
        }catch (BaseException e){
            // logger.error("获取小区下租房数量异常 "+plotDetailsFewDo.getId()+"={}",e.getCode());
            if (e.getCode()==50401){
                plotDetailsFewDo.setRentTotalNum(0);
            }
        }
        plotDetailsFewDoList.add(plotDetailsFewDo);
    }

//    /**
//     * 小区列表含坐标
//     * @param plotListDoQuery
//     * @return
//     */
//    @Override
//    public List<PlotDetailsFewDo> queryPlotListByRequirementWithLocation(PlotListDoQuery plotListDoQuery) {
//        List<PlotDetailsFewDo> plotDetailsFewDoList = new ArrayList<>();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if (plotListDoQuery.getLat()!=0&& plotListDoQuery.getLon()!=0){
//            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location").point(plotListDoQuery.getLat(), plotListDoQuery.getLon()).distance(1600, DistanceUnit.METERS);
//            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", plotListDoQuery.getLat(), plotListDoQuery.getLon());
//            sort.unit(DistanceUnit.METERS);
//            sort.order(SortOrder.ASC);
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
//            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
//            SearchResponse searchResponse = plotEsDao.queryNearPlotListByLocationAndDistance(boolQueryBuilder, location, sort);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            if (hits.length==10){
//                for (SearchHit hit:hits){
//                    String sourceAsString = hit.getSourceAsString();
//                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
//                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                    plotDetailsFewDoList.add(plotDetailsFewDo);
//                }
//            }else if (hits.length<10&&hits.length>0){
//                for (SearchHit hit:hits){
//                    String sourceAsString = hit.getSourceAsString();
//                    PlotDetailsFewDo plotDetailsFewDo = JSON.parseObject(sourceAsString, PlotDetailsFewDo.class);
//                    plotDetailsFewDo.setTotalNum((int) searchResponse.getHits().getTotalHits());
//                    plotDetailsFewDoList.add(plotDetailsFewDo);
//                }
//
//                plotListDoQuery.setPageSize(10-hits.length);
//                PlotListDo plotListDo = plotsRestService.queryPlotListByRequirement(plotListDoQuery);
//                plotDetailsFewDoList.addAll(plotListDo.getPlotList());
//            }else if (hits.length==0){
//                plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
//            }
//        }else {
//            plotDetailsFewDoList = plotsRestService.queryPlotListByRequirement(plotListDoQuery).getPlotList();
//        }
//        return plotDetailsFewDoList;
//    }


    /**
     *
     * @param plotTop50ListDoQuery
     * @return
     */

    @Override
    public List<PlotTop50Do> getPlotTop50List(PlotTop50ListDoQuery plotTop50ListDoQuery,String userAgent,  String city) {
        List<PlotTop50Do> plotTop50Dos=new ArrayList<>();
        int [] isTop={1};
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", 1));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termsQuery("recommendBuildTagsId",isTop));
        if (null!=plotTop50ListDoQuery.getDistrictId())
        {
            boolQueryBuilder.must(QueryBuilders.termQuery("areaId",plotTop50ListDoQuery.getDistrictId()));
        }

        SearchResponse searchResponse= plotEsDao.getPlotTop50List(boolQueryBuilder,plotTop50ListDoQuery.getPageNum(),plotTop50ListDoQuery.getPageSize());
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit:hits){
            String sourceAsString = hit.getSourceAsString();
            PlotTop50Do plotTop50Do = JSON.parseObject(sourceAsString, PlotTop50Do.class);

            PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(plotTop50Do.getId(),CityUtils.getCity());
            if(plotsEsfRoomCountDomain.getTotalCount()!= null){
                plotTop50Do.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
            }else{
                plotTop50Do.setHouseCount(0);
            }

            plotTop50Dos.add(plotTop50Do);
        }
        return plotTop50Dos;
    }


}
