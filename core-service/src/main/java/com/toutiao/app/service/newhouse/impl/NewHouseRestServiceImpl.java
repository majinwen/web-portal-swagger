package com.toutiao.app.service.newhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.service.map.MapService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class NewHouseRestServiceImpl implements NewHouseRestService {

    @Autowired
    private NewHouseEsDao newHouseEsDao;

    @Autowired
    private NewHouseLayoutService newHouseLayoutService;

    @Autowired
    private MapService mapService;

    @Autowired
    private NewHouseRestService newHouseService;

    @Autowired
    private FavoriteRestService favoriteRestService;


    private Logger logger = LoggerFactory.getLogger(NewHouseRestServiceImpl.class);


    private static final Integer IS_DEL = 0;//新房未删除
    private static final Integer IS_APPROVE = 1;//新房未下架

    /**
     * 新房异常2
     */
    @Value("${tt.newhouse.type}")
    private String newhouseType;

    /**
     * 根据newcode获取新房详细信息
     * @param newCode
     * @return
     */
    @Override
    public NewHouseDetailDo getNewHouseBuildByNewCode(Integer newCode, String city) {
        NewHouseDetailDo newHouseDetailDo = new NewHouseDetailDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("building_name_id",newCode));
        SearchResponse buildResponse =newHouseEsDao.getNewHouseBulid(boolQueryBuilder, city);
        SearchHit[] searchHists = buildResponse.getHits().getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        if (StringUtils.isNotEmpty(details))
        {
            UserBasic userBasic = UserBasic.getCurrent();
            if(StringTool.isNotEmpty(userBasic)){
                NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
                newHouseIsFavoriteDoQuery.setUserId(Integer.valueOf(userBasic.getUserId()));
                newHouseIsFavoriteDoQuery.setBuildingId(newHouseDetailDo.getBuildingNameId());
                Boolean isFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
                newHouseDetailDo.setIsFavorite(isFavorite);
            }
            newHouseDetailDo = JSON.parseObject(details,NewHouseDetailDo.class);

            String [] img = newHouseDetailDo.getBuildingImgs().get(0).split(",");
            newHouseDetailDo.setBuildingImg(img);
        }
        if ("0".equals(newHouseDetailDo.getHeatingType())){
            newHouseDetailDo.setHeatingType("未知");
        }
        if ("1".equals(newHouseDetailDo.getHeatingType())){
            newHouseDetailDo.setHeatingType("集中供暖");
        }
        if ("2".equals(newHouseDetailDo.getHeatingType())){
            newHouseDetailDo.setHeatingType("自供暖");
        }

        return  newHouseDetailDo;

    }


    @Override
    public NewHouseListDomain getNewHouseList(NewHouseDoQuery newHouseDoQuery, String city) {
        NewHouseListDomain newHouseListVo=new NewHouseListDomain();
        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        FieldSortBuilder levelSort=null;
        FieldSortBuilder buildingSort=null;


//        BoolQueryBuilder bqbPlotName = QueryBuilders.boolQuery();
//        if (StringTool.isNotBlank(newHouseDoQuery.getKeyword())) {
//            SearchResponse searchResponse = null;
//            bqbPlotName.must(QueryBuilders.termQuery("building_name_accurate",newHouseDoQuery.getKeyword()));
//            searchResponse = newHouseEsDao.getPlotByKeyWord(bqbPlotName, city);
//            long total = searchResponse.getHits().getTotalHits();
//            out: if(total > 0l){
//                break out;
//            }else{
//                BoolQueryBuilder bqb = QueryBuilders.boolQuery();
//                bqb.must(QueryBuilders.multiMatchQuery(newHouseDoQuery.getKeyword(),"search_nickname").operator(Operator.AND).minimumShouldMatch("100%"));
//                searchResponse = newHouseEsDao.getPlotByNickNameKeyWord(bqb, city);
//                if(searchResponse.getHits().getTotalHits()>0l){
//                    SearchHits hits = searchResponse.getHits();
//
//                    SearchHit[] searchHists = hits.getHits();
//                    outFor:for (SearchHit hit : searchHists) {
//                        hit.getSource().get("search_name");
//                        newHouseDoQuery.setKeyword(hit.getSource().get("search_name").toString());
//                        break outFor ;
//                    }
//                }
//            }
//        }



        if(StringUtil.isNotNullString(newHouseDoQuery.getKeyword())){
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseDoQuery.getKeyword()))){
                queryBuilder = QueryBuilders.disMaxQuery()
//                        .add(QueryBuilders.matchQuery("building_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart"))
//                        .add(QueryBuilders.matchQuery("area_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("district_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseDoQuery.getKeyword()).analyzer("ik_max_word").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_nickname",newHouseDoQuery.getKeyword()).fuzziness("AUTO").analyzer("ik_smart").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_name_accurate", newHouseDoQuery.getKeyword()).boost(2).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("area_name", newHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("district_name", newHouseDoQuery.getKeyword()).operator(Operator.AND)).tieBreaker(0.3f);
            }

            booleanQueryBuilder.must(queryBuilder);
        }
        else
        {
            levelSort=SortBuilders.fieldSort("build_level").order(SortOrder.ASC);
            buildingSort=SortBuilders.fieldSort("building_sort").order(SortOrder.DESC);
        }

        //城市
//        if(newHouseDoQuery.getCityId()!=null && newHouseDoQuery.getCityId()!=0){
//            booleanQueryBuilder.must(termQuery("city_id",newHouseDoQuery.getCityId()));
//        }
        //区域
        if(newHouseDoQuery.getDistrictId()!=null && newHouseDoQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseDoQuery.getDistrictId()));
        }
        //商圈
//        if(newHouseDoQuery.getAreaId()!=null && newHouseDoQuery.getAreaId()!=0){
//            booleanQueryBuilder.must(termQuery("area_id", newHouseDoQuery.getAreaId()));
//        }
        //地铁线id
        String keys = "";
        if(newHouseDoQuery.getSubwayLineId() !=null && newHouseDoQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseDoQuery.getSubwayLineId()}));
            keys = newHouseDoQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if(newHouseDoQuery.getSubwayStationId()!=null && newHouseDoQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{newHouseDoQuery.getSubwayStationId()}));
            keys = keys+"$"+newHouseDoQuery.getSubwayStationId().toString();
        }
        //总价
        if(newHouseDoQuery.getBeginPrice()!=0  && newHouseDoQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice()).lte(newHouseDoQuery.getEndPrice())));
        }else if (newHouseDoQuery.getBeginPrice()==0 && newHouseDoQuery.getEndPrice()!=0)
        {        newHouseDoQuery.setBeginPrice(0.0);
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice()).lte(newHouseDoQuery.getEndPrice())));
        }else if (newHouseDoQuery.getEndPrice()==0 &&  newHouseDoQuery.getBeginPrice()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice())));
        }

        //标签
        if(null!=newHouseDoQuery.getLabelId() && newHouseDoQuery.getLabelId().length!=0){


            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            Integer[] longs = newHouseDoQuery.getLabelId();

          /*  boolean has_subway = Arrays.asList(longs).contains(1);*/

            for(int i=0;i<longs.length;i++){
                if(longs[i].equals(1)){
                    boolQueryBuilder.must(QueryBuilders.termQuery("has_subway", longs[i]));
                }else {
                    boolQueryBuilder.must(QueryBuilders.termQuery("building_tags_id", longs[i]));
                }
            }
            booleanQueryBuilder.must(boolQueryBuilder);
          /*  if(has_subway){
                Integer[] tagOther = new Integer[longs.length-1];
                int idx = 0;
                for(int i=0;i<longs.length;i++){
                    if(longs[i].equals(1)){
                        boolQueryBuilder.should(QueryBuilders.termQuery("has_subway", longs[i]));
                    }else {
                        tagOther[idx++] = longs[i];
                    }
                }
                if(tagOther.length!=0){
                    boolQueryBuilder.should(QueryBuilders.termsQuery("building_tags_id", tagOther));
                }
                booleanQueryBuilder.must(boolQueryBuilder);
            }else{
                booleanQueryBuilder.must(QueryBuilders.termsQuery("building_tags_id", longs));
            }*/

        }

        //户型
        if(newHouseDoQuery.getLayoutId()!=null && newHouseDoQuery.getLayoutId().length!=0 ){

            Integer[] longs =  newHouseDoQuery.getLayoutId();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.getNewHouseChildType(city), QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }

        //面积
        if(newHouseDoQuery.getBeginArea()!=0 &&  newHouseDoQuery.getEndArea()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseDoQuery.getBeginArea())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseDoQuery.getEndArea())));
        }else if(newHouseDoQuery.getBeginArea()==0 && newHouseDoQuery.getEndArea()!=0)
        {

            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseDoQuery.getEndArea())));

        }else if (newHouseDoQuery.getEndArea()==0 && newHouseDoQuery.getBeginArea()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseDoQuery.getBeginArea())));
        }

        //销售状态
        if(StringTool.isNotEmpty(newHouseDoQuery.getSaleStatusId()) && newHouseDoQuery.getSaleStatusId().length>0){
            Integer[] longs =  newHouseDoQuery.getSaleStatusId();
            booleanQueryBuilder.must(termsQuery("sale_status_id", longs));
        }else{
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        }
        //5环内
        if(StringTool.isNotEmpty(newHouseDoQuery.getRingRoad())){
            booleanQueryBuilder.must(rangeQuery("ringRoad").lte(newHouseDoQuery.getRingRoad()));
        }

        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve",IS_APPROVE ));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));
        booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1,2}));

            SearchResponse searchResponse=newHouseEsDao.getNewHouseList(booleanQueryBuilder,newHouseDoQuery.getPageNum(),newHouseDoQuery.getPageSize(),levelSort,buildingSort,city);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            if(searchHists.length > 0){
                for (SearchHit searchHit : searchHists) {
                    String details = "";
                    details=searchHit.getSourceAsString();
                    NewHouseListDo newHouseListDos=JSON.parseObject(details,NewHouseListDo.class);
                    if (""!=keys&&null!=newHouseListDos.getNearbysubway()){
                        newHouseListDos.setSubwayDistanceInfo((String) newHouseListDos.getNearbysubway().get(keys));
                    }
//                newHouseListDos.setNearbysubway(null);
                    try{
                        //获取新房下户型的数量
                        NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseListDos.getBuildingNameId(),city);
//                        if (null!=newHouseLayoutCountDomain.getTotalCount())
//                        {
//                            newHouseListDos.setRoomTotalCount(newHouseLayoutCountDomain.getTotalCount());
//                        }
//                        else
//                        {
//                            newHouseListDos.setRoomTotalCount(0);
//                        }
                        if(null!=newHouseLayoutCountDomain.getRooms() && newHouseLayoutCountDomain.getRooms().size() > 0){
                            List<String> roomsType = new ArrayList<>();
                            for(int i=0; i< newHouseLayoutCountDomain.getRooms().size(); i++){
                                roomsType.add(newHouseLayoutCountDomain.getRooms().get(i).getRoom().toString());
                            }
                            String rooms = String.join(",", roomsType);
                            newHouseListDos.setRoomType(rooms);
                        }else{
                            newHouseListDos.setRoomType("");
                        }

                    }catch (Exception e){
                        logger.error("获取新房户型信息异常信息={}",e.getStackTrace());
                    }
                    //新房动态
                    BoolQueryBuilder queryBuilderDynamic = boolQuery();//声明符合查询方法
                    queryBuilderDynamic.must(QueryBuilders.termQuery("newcode",newHouseListDos.getBuildingNameId()));
                    SearchResponse  dynamicResponse =newHouseEsDao.getDynamicByNewCode(queryBuilderDynamic,1,10, city);
                    long dynamicTotal = dynamicResponse.getHits().totalHits;//动态总数
                    newHouseListDos.setDynamicTotal(dynamicTotal);

                    //获取新房户型价格范围
                    NewHouseLayoutPriceDo newHouseLayoutPriceDo = newHouseLayoutService.getNewHouseLayoutPriceByNewHouseId(newHouseListDos.getBuildingNameId());
                    newHouseListDos.setHouseMinPrice(newHouseLayoutPriceDo.getHouseMinPrice());
                    newHouseListDos.setHouseMaxPrice(newHouseLayoutPriceDo.getHouseMaxPrice());
                    //新房动态
                    NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
                    newHouseDynamicDoQuery.setNewCode(newHouseListDos.getBuildingNameId());
                    newHouseDynamicDoQuery.setPageSize(1);
                    List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery,city);
                    newHouseListDos.setNewHouseDynamic(newHouseDynamicDoList);
//                //获取新房的收藏数量
//                int newHouseFavoriteCount=favoriteRestService.newHouseFavoriteByNewCode(newHouseListDos.getBuildingNameId());
//                newHouseListDos.setNewHouseFavorite(newHouseFavoriteCount);
                    newHouseListDoList.add(newHouseListDos);
                }



//                booleanQueryBuilder.must(QueryBuilders.termQuery("newcode",newHouseDynamicDoQuery.getNewCode()));
//                try {
//                    SearchResponse  dynamicResponse =newHouseEsDao.getDynamicByNewCode(booleanQueryBuilder,newHouseDynamicDoQuery.getPageNum(),newHouseDynamicDoQuery.getPageSize(), city);
//                    SearchHits hits = dynamicResponse.getHits();
//                    SearchHit[] searchHists = hits.getHits();
//                    for (SearchHit searchHit : searchHists) {
//                        String details = "";
//                        details=searchHit.getSourceAsString();
//                        NewHouseDynamicDo newHouseDynamic=JSON.parseObject(details,NewHouseDynamicDo.class);
//                        newHouseDynamicDoList.add(newHouseDynamic);
//                    }
//                }catch (Exception e)
//                {
//                    logger.error("获取新房动态信息异常信息"+newHouseDynamicDoQuery.getNewCode().toString()+"={}",e.getStackTrace());
//                }





                newHouseListVo.setData(newHouseListDoList);
                newHouseListVo.setTotalCount(hits.getTotalHits());
            }else{
                throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_NOT_FOUND,"新房楼盘列表为空");
            }



        return newHouseListVo ;
    }

    @Override
    public List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDoQuery newHouseDynamicDoQuery, String city) {
        List<NewHouseDynamicDo> newHouseDynamicDoList=new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode",newHouseDynamicDoQuery.getNewCode()));
        try {
            SearchResponse  dynamicResponse =newHouseEsDao.getDynamicByNewCode(booleanQueryBuilder,newHouseDynamicDoQuery.getPageNum(),newHouseDynamicDoQuery.getPageSize(), city);
            SearchHits hits = dynamicResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                NewHouseDynamicDo newHouseDynamic=JSON.parseObject(details,NewHouseDynamicDo.class);
                newHouseDynamicDoList.add(newHouseDynamic);
            }
        }catch (Exception e)
        {
            logger.error("获取新房动态信息异常信息"+newHouseDynamicDoQuery.getNewCode().toString()+"={}",e.getStackTrace());
        }

        return  newHouseDynamicDoList;
    }

    @Override
    public NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode, String city) {
        MapInfo mapInfo = new MapInfo();
        NewHouseTrafficDo newHouseTrafficDo=new NewHouseTrafficDo();
        mapInfo =  mapService.getMapInfo(newCode);
        if (mapInfo==null)
        {
            throw  new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_TRAFFIC_NOT_FOUND);
        }
        //获取新房交通配套
        JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
        JSONObject businfo= (JSONObject) datainfo.get("gongjiao");
        if (businfo.size()>0)
        {
            newHouseTrafficDo.setBusStation(businfo.get("name").toString());
            newHouseTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
        }

        //获取地铁和环线位置
        NewHouseDetailDo newHouseDetailDo=newHouseService.getNewHouseBuildByNewCode(newCode, city);
        if (null!=newHouseDetailDo.getRoundstation() &&!"".equals(newHouseDetailDo.getRoundstation()))
        {  String []  trafficInfo=newHouseDetailDo.getRoundstation().split("\\$");
            newHouseTrafficDo.setSubwayStation(trafficInfo[1]);
            newHouseTrafficDo.setSubwayLine(trafficInfo[0]);
            newHouseTrafficDo.setSubwayDistance(Double.valueOf(trafficInfo[2]));
        }
        if(null!=newHouseDetailDo.getRingRoadName() && !"".equals(newHouseDetailDo.getRingRoadName()))
        {
            newHouseTrafficDo.setRingRoadName(newHouseDetailDo.getRingRoadName());
        }
        if(null!=newHouseDetailDo.getRingRoadDistance())
        {
            newHouseTrafficDo.setRingRoadDistance(Double.valueOf(newHouseDetailDo.getRingRoadDistance().toString()));
        }

        return  newHouseTrafficDo;
    }

    @Override
    public NewHouseDetailDo getOneNewHouseByRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();

        //组装条件
        //区域
        if (null!=userFavoriteConditionDoQuery.getDistrictId()&&userFavoriteConditionDoQuery.getDistrictId().length>0){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("district_id",userFavoriteConditionDoQuery.getDistrictId()));
        }
        //户型
        if (null!=userFavoriteConditionDoQuery.getLayoutId()&&userFavoriteConditionDoQuery.getLayoutId().length>0){
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.getNewHouseChildType(city),QueryBuilders.termsQuery("room",userFavoriteConditionDoQuery.getLayoutId()),ScoreMode.None));
        }
        //价格
        if (null!=userFavoriteConditionDoQuery.getBeginPrice()&&null!=userFavoriteConditionDoQuery.getEndPrice()){
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.getNewHouseChildType(city),QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice()*0.9).lte(userFavoriteConditionDoQuery.getEndPrice()*1.1),ScoreMode.None));
        }else if (null!=userFavoriteConditionDoQuery.getBeginPrice()&&null==userFavoriteConditionDoQuery.getEndPrice()){
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.getNewHouseChildType(city),QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice()*0.9),ScoreMode.None));
        }

        //查询
        SearchResponse oneNewHouseByRecommendCondition = newHouseEsDao.getOneNewHouseByRecommendCondition(booleanQueryBuilder, city);
        SearchHit[] hits = oneNewHouseByRecommendCondition.getHits().getHits();
        NewHouseDetailDo newHouseDetailDo = new NewHouseDetailDo();
        if (hits.length>0){
            String sourceAsString = hits[0].getSourceAsString();
            newHouseDetailDo = JSON.parseObject(sourceAsString,NewHouseDetailDo.class);
        }else {
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_NOT_FOUND,"新房楼盘列表为空");
        }
        return newHouseDetailDo;
    }


}
