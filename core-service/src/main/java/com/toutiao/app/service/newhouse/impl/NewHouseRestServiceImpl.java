package com.toutiao.app.service.newhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.domain.sellhouse.HouseLable;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.constant.house.HouseLableEnum;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteNewHouseMapper;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.service.map.MapService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private UserFavoriteNewHouseMapper userFavoriteNewHouseMapper;

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
     *
     * @param newCode
     * @return
     */
    @Override
    public NewHouseDetailDo getNewHouseBuildByNewCode(Integer newCode, String city) {
        NewHouseDetailDo newHouseDetailDo = new NewHouseDetailDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("building_name_id", newCode));
        SearchResponse buildResponse = newHouseEsDao.getNewHouseBulid(boolQueryBuilder, city);
        SearchHit[] searchHists = buildResponse.getHits().getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        if (StringUtils.isNotEmpty(details)) {
            newHouseDetailDo = JSON.parseObject(details, NewHouseDetailDo.class);
            try {

                UserBasic userBasic = UserBasic.getCurrent();
                if (StringTool.isNotEmpty(userBasic)) {
                    NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery = new NewHouseIsFavoriteDoQuery();
                    newHouseIsFavoriteDoQuery.setUserId(Integer.valueOf(userBasic.getUserId()));
                    newHouseIsFavoriteDoQuery.setBuildingId(newHouseDetailDo.getBuildingNameId());
                    Boolean isFavorite = favoriteRestService.getNewHouseIsFavorite(newHouseIsFavoriteDoQuery);
                    newHouseDetailDo.setIsFavorite(isFavorite);
                }
            } catch (BaseException e) {
                logger.info("用户未登录");
                newHouseDetailDo.setIsFavorite(Boolean.FALSE);
            }

            String[] img = newHouseDetailDo.getBuildingImgs().get(0).split(",");
            newHouseDetailDo.setBuildingImg(img);
        }
        if ("0".equals(newHouseDetailDo.getHeatingType())) {
            newHouseDetailDo.setHeatingType("未知");
        }
        if ("1".equals(newHouseDetailDo.getHeatingType())) {
            newHouseDetailDo.setHeatingType("集中供暖");
        }
        if ("2".equals(newHouseDetailDo.getHeatingType())) {
            newHouseDetailDo.setHeatingType("自供暖");
        }

        NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newCode, city);
        newHouseDetailDo.setRoomType(getNewHouseRoomType(newHouseLayoutCountDomain));

        return newHouseDetailDo;

    }


    @Override
    public NewHouseListDomain getNewHouseList(NewHouseDoQuery newHouseDoQuery, String city) {
        NewHouseListDomain newHouseListVo = new NewHouseListDomain();
        List<NewHouseListDo> newHouseListDoList = new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        FieldSortBuilder levelSort = null;
        FieldSortBuilder buildingSort = null;

        if (StringUtil.isNotNullString(newHouseDoQuery.getKeyword())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseDoQuery.getKeyword()))) {
                queryBuilder = QueryBuilders.disMaxQuery()
//                        .add(QueryBuilders.matchQuery("building_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart"))
//                        .add(QueryBuilders.matchQuery("area_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("district_name", newHouseDoQuery.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseDoQuery.getKeyword()).analyzer("ik_max_word").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_nickname", newHouseDoQuery.getKeyword()).fuzziness("AUTO").analyzer("ik_smart").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_name_accurate", newHouseDoQuery.getKeyword()).boost(2).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("area_name", newHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("district_name", newHouseDoQuery.getKeyword()).operator(Operator.AND)).tieBreaker(0.3f);
            }

            booleanQueryBuilder.must(queryBuilder);
        } else {
            levelSort = SortBuilders.fieldSort("build_level").order(SortOrder.ASC);
            buildingSort = SortBuilders.fieldSort("building_sort").order(SortOrder.DESC);
        }

        //城市
//        if(newHouseDoQuery.getCityId()!=null && newHouseDoQuery.getCityId()!=0){
//            booleanQueryBuilder.must(termQuery("city_id",newHouseDoQuery.getCityId()));
//        }
        //区域
        if (newHouseDoQuery.getDistrictId() != null && newHouseDoQuery.getDistrictId() != 0) {
            booleanQueryBuilder.must(termQuery("district_id", newHouseDoQuery.getDistrictId()));
        }
        //商圈
//        if(newHouseDoQuery.getAreaId()!=null && newHouseDoQuery.getAreaId()!=0){
//            booleanQueryBuilder.must(termQuery("area_id", newHouseDoQuery.getAreaId()));
//        }
        //地铁线id
        String keys = "";
        if (newHouseDoQuery.getSubwayLineId() != null && newHouseDoQuery.getSubwayLineId() != 0) {
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseDoQuery.getSubwayLineId()}));
            keys = newHouseDoQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if (newHouseDoQuery.getSubwayStationId() != null && newHouseDoQuery.getSubwayStationId().length > 0) {
            booleanQueryBuilder.must(termsQuery("subway_station_id", newHouseDoQuery.getSubwayStationId()));
        }
        //均价
        if (newHouseDoQuery.getBeginPrice() != 0 && newHouseDoQuery.getEndPrice() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice()).lte(newHouseDoQuery.getEndPrice())));
        } else if (newHouseDoQuery.getBeginPrice() == 0 && newHouseDoQuery.getEndPrice() != 0) {
            newHouseDoQuery.setBeginPrice(0);
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice()).lte(newHouseDoQuery.getEndPrice())));
        } else if (newHouseDoQuery.getEndPrice() == 0 && newHouseDoQuery.getBeginPrice() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseDoQuery.getBeginPrice())));
        }
        //总价
        if (newHouseDoQuery.getBeginTotalPrice() != 0 && newHouseDoQuery.getEndTotalPrice() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseDoQuery.getBeginTotalPrice()).lte(newHouseDoQuery.getEndTotalPrice())));
        } else if (newHouseDoQuery.getBeginTotalPrice() == 0 && newHouseDoQuery.getEndTotalPrice() != 0) {
            newHouseDoQuery.setBeginTotalPrice(0);
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseDoQuery.getBeginTotalPrice()).lte(newHouseDoQuery.getEndTotalPrice())));
        } else if (newHouseDoQuery.getEndTotalPrice() == 0 && newHouseDoQuery.getBeginTotalPrice() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseDoQuery.getBeginTotalPrice())));
        }

        if ("3".equals(newHouseDoQuery.getSort()) || "4".equals(newHouseDoQuery.getSort())) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(0)));
        }

        //标签
        if (null != newHouseDoQuery.getLabelId() && newHouseDoQuery.getLabelId().length != 0) {


            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            Integer[] longs = newHouseDoQuery.getLabelId();

          /*  boolean has_subway = Arrays.asList(longs).contains(1);*/

            for (int i = 0; i < longs.length; i++) {
                if (longs[i].equals(1)) {
                    boolQueryBuilder.must(QueryBuilders.termQuery("has_subway", longs[i]));
                } else {
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
        if (newHouseDoQuery.getLayoutId() != null && newHouseDoQuery.getLayoutId().length != 0) {

            Integer[] longs = newHouseDoQuery.getLayoutId();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.termsQuery("room", longs), ScoreMode.None));

        }

        //面积
        if (newHouseDoQuery.getBeginArea() != 0 && newHouseDoQuery.getEndArea() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseDoQuery.getBeginArea())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseDoQuery.getEndArea())));
        } else if (newHouseDoQuery.getBeginArea() == 0 && newHouseDoQuery.getEndArea() != 0) {

            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseDoQuery.getEndArea())));

        } else if (newHouseDoQuery.getEndArea() == 0 && newHouseDoQuery.getBeginArea() != 0) {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseDoQuery.getBeginArea())));
        }

        //销售状态
        if (StringTool.isNotEmpty(newHouseDoQuery.getSaleStatusId()) && newHouseDoQuery.getSaleStatusId().length > 0) {
            Integer[] longs = newHouseDoQuery.getSaleStatusId();
            booleanQueryBuilder.must(termsQuery("sale_status_id", longs));
        } else {
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0, 1, 5, 6}));
        }
        //5环内
        if (StringTool.isNotEmpty(newHouseDoQuery.getRingRoad())) {
            booleanQueryBuilder.must(rangeQuery("ringRoad").lte(newHouseDoQuery.getRingRoad()));
        }

        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));
        booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1, 2}));

        SearchResponse searchResponse = newHouseEsDao.getNewHouseList(booleanQueryBuilder, newHouseDoQuery.getPageNum(), newHouseDoQuery.getPageSize(), levelSort, buildingSort, city, newHouseDoQuery.getSort());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details = searchHit.getSourceAsString();
                NewHouseListDo newHouseListDos = JSON.parseObject(details, NewHouseListDo.class);
                if (null != newHouseDoQuery.getSubwayStationId() && newHouseDoQuery.getSubwayStationId().length > 0) {
                    Map<Integer, String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for (int i = 0; i < newHouseDoQuery.getSubwayStationId().length; i++) {
                        String stationKey = keys + "$" + newHouseDoQuery.getSubwayStationId()[i];
                        if (StringTool.isNotEmpty(newHouseListDos.getNearbysubway().get(stationKey))) {
                            String stationValue = newHouseListDos.getNearbysubway().get(stationKey).toString();
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance, stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    newHouseListDos.setSubwayDistanceInfo(newHouseListDos.getNearbysubway().get(map.get(minDistance)).toString());
                }
                try {
                    //获取新房下户型的数量
                    NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseListDos.getBuildingNameId(), city);
                    newHouseListDos.setRoomType(getNewHouseRoomType(newHouseLayoutCountDomain));


                } catch (Exception e) {
                    logger.error("获取新房户型信息异常信息={}", e.getStackTrace());
                }

                //新房图片处理
                if (!Objects.equals(newHouseListDos.getBuildingTitleImg(), "") && !newHouseListDos.getBuildingTitleImg().startsWith("http")) {
                    newHouseListDos.setBuildingTitleImg("http://s1.qn.toutiaofangchan.com/" + newHouseListDos.getBuildingTitleImg() + "-dongfangdi1200x900");
                }

                //新房标签
                List<HouseLable> houseLableList = new ArrayList<>();
                String saleStatusName = newHouseListDos.getSaleStatusName();

                if (!StringUtil.isNullString(saleStatusName) && HouseLableEnum.containKey(saleStatusName)) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.getEnumByKey(saleStatusName));
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }
                int isActive = newHouseListDos.getIsActive();
                if (isActive == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.ISACTIVE);
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }
                String propertyType = newHouseListDos.getPropertyType();
                if (!StringUtil.isNullString(propertyType) && HouseLableEnum.containKey(propertyType)) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.getEnumByKey(propertyType));
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }

                newHouseListDos.setHouseLabelList(houseLableList);

                //新房动态
                BoolQueryBuilder queryBuilderDynamic = boolQuery();//声明符合查询方法
                queryBuilderDynamic.must(QueryBuilders.termQuery("newcode", newHouseListDos.getBuildingNameId()));
                SearchResponse dynamicResponse = newHouseEsDao.getDynamicByNewCode(queryBuilderDynamic, 1, 10, city);
                long dynamicTotal = dynamicResponse.getHits().totalHits;//动态总数
                newHouseListDos.setDynamicTotal(dynamicTotal);

                //获取新房户型价格范围
                NewHouseLayoutPriceDo newHouseLayoutPriceDo = newHouseLayoutService.getNewHouseLayoutPriceByNewHouseId(newHouseListDos.getBuildingNameId(), city);
                newHouseListDos.setHouseMinPrice(newHouseLayoutPriceDo.getHouseMinPrice());
                newHouseListDos.setHouseMaxPrice(newHouseLayoutPriceDo.getHouseMaxPrice());
                //新房动态
                NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
                newHouseDynamicDoQuery.setNewCode(newHouseListDos.getBuildingNameId());
                newHouseDynamicDoQuery.setPageSize(1);
                List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery, city);
                newHouseListDos.setNewHouseDynamic(newHouseDynamicDoList);
                newHouseListDos.setBuildingFeature("");
//                //获取新房的收藏数量
//                int newHouseFavoriteCount=favoriteRestService.newHouseFavoriteByNewCode(newHouseListDos.getBuildingNameId());
//                newHouseListDos.setNewHouseFavorite(newHouseFavoriteCount);

                //descHigh : 位于 districtName ringRoad环 buildingAddress
                StringBuilder descHigh = new StringBuilder();
                descHigh.append("位于");
                if (StringTool.isNotEmpty(newHouseListDos.getDistrictName())) {
                    descHigh.append(newHouseListDos.getDistrictName());
                }
                if (StringTool.isNotEmpty(newHouseListDos.getRingRoad())) {
                    descHigh.append(newHouseListDos.getRingRoad());
                    descHigh.append("环");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getBuildingAddress())) {
                    descHigh.append(newHouseListDos.getBuildingAddress());
                }
                newHouseListDos.setDescHigh(descHigh.toString().equals("位于") ? "" : descHigh.toString());

                //descMid : 位于 developers建propertyType,openedTimeDesc,deliverTimeDesc
                StringBuilder descMidSb = new StringBuilder();
                if (StringTool.isNotEmpty(newHouseListDos.getDevelopers())) {
                    descMidSb.append(newHouseListDos.getDevelopers());
                    descMidSb.append("建");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getPropertyType())) {
                    descMidSb.append(newHouseListDos.getPropertyType());
                    descMidSb.append(",");
                } else if (StringTool.isNotEmpty(descMidSb.toString())) {
                    descMidSb.append(",");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getOpenedTimeDesc())) {
                    descMidSb.append(newHouseListDos.getOpenedTimeDesc());
                    descMidSb.append(",");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getDeliverTimeDesc())) {
                    descMidSb.append(newHouseListDos.getDeliverTimeDesc());
                    descMidSb.append(",");
                }
                String descMidStr = "";
                if (StringTool.isNotEmpty(descMidSb.toString())) {
                    descMidStr = descMidSb.substring(0, descMidSb.length() - 1);
                }

                newHouseListDos.setDescMid(descMidStr);


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
        } else {
            newHouseListVo.setData(newHouseListDoList);
            newHouseListVo.setTotalCount(hits.getTotalHits());
//            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_NOT_FOUND, "新房楼盘列表为空");
        }


        return newHouseListVo;
    }

    private String getNewHouseRoomType(NewHouseLayoutCountDomain newHouseLayoutCountDomain) {
        if (null != newHouseLayoutCountDomain.getRooms() && newHouseLayoutCountDomain.getRooms().size() > 0) {
            List<String> roomsType = new ArrayList<>();
            for (int i = 0; i < newHouseLayoutCountDomain.getRooms().size(); i++) {
                roomsType.add(newHouseLayoutCountDomain.getRooms().get(i).getRoom().toString());
            }
            String rooms = String.join(",", roomsType);
            return rooms;
        } else {
            return "";
        }
    }

    @Override
    public List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDoQuery newHouseDynamicDoQuery, String city) {
        List<NewHouseDynamicDo> newHouseDynamicDoList = new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", newHouseDynamicDoQuery.getNewCode()));
        try {
            SearchResponse dynamicResponse = newHouseEsDao.getDynamicByNewCode(booleanQueryBuilder, newHouseDynamicDoQuery.getPageNum(), newHouseDynamicDoQuery.getPageSize(), city);
            SearchHits hits = dynamicResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details = searchHit.getSourceAsString();
                NewHouseDynamicDo newHouseDynamic = JSON.parseObject(details, NewHouseDynamicDo.class);
                newHouseDynamicDoList.add(newHouseDynamic);
            }
        } catch (Exception e) {
            logger.error("获取新房动态信息异常信息" + newHouseDynamicDoQuery.getNewCode().toString() + "={}", e.getStackTrace());
        }

        return newHouseDynamicDoList;
    }

    @Override
    public NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode, String city) {
        MapInfo mapInfo = new MapInfo();
        NewHouseTrafficDo newHouseTrafficDo = new NewHouseTrafficDo();
        mapInfo = mapService.getMapInfo(newCode);
        if (mapInfo == null) {
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_TRAFFIC_NOT_FOUND);
        }
        //获取新房交通配套
        JSONObject datainfo = JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
        JSONObject businfo = (JSONObject) datainfo.get("gongjiao");
        if (businfo.size() > 0) {
            newHouseTrafficDo.setBusStation(businfo.get("name").toString());
            newHouseTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
        }

        //获取地铁和环线位置
        NewHouseDetailDo newHouseDetailDo = newHouseService.getNewHouseBuildByNewCode(newCode, city);
        if (null != newHouseDetailDo.getRoundstation() && !"".equals(newHouseDetailDo.getRoundstation())) {
            String[] trafficInfo = newHouseDetailDo.getRoundstation().split("\\$");
            newHouseTrafficDo.setSubwayStation(trafficInfo[1]);
            newHouseTrafficDo.setSubwayLine(trafficInfo[0]);
            newHouseTrafficDo.setSubwayDistance(Double.valueOf(trafficInfo[2]));
        }
        if (null != newHouseDetailDo.getRingRoadName() && !"".equals(newHouseDetailDo.getRingRoadName())) {
            newHouseTrafficDo.setRingRoadName(newHouseDetailDo.getRingRoadName());
        }
        if (null != newHouseDetailDo.getRingRoadDistance()) {
            newHouseTrafficDo.setRingRoadDistance(Double.valueOf(newHouseDetailDo.getRingRoadDistance().toString()));
        }

        return newHouseTrafficDo;
    }

    @Override
    public NewHouseDetailDo getOneNewHouseByRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        //构建筛选器
        BoolQueryBuilder booleanQueryBuilder = boolQuery();

        //组装条件
        //区域
        if (null != userFavoriteConditionDoQuery.getDistrictId() && userFavoriteConditionDoQuery.getDistrictId().length > 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("district_id", userFavoriteConditionDoQuery.getDistrictId()));
        }
        //户型
        if (null != userFavoriteConditionDoQuery.getLayoutId() && userFavoriteConditionDoQuery.getLayoutId().length > 0) {
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()), ScoreMode.None));
        }
        //价格
        if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice()) {
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice() * 0.9).lte(userFavoriteConditionDoQuery.getEndPrice() * 1.1), ScoreMode.None));
        } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null == userFavoriteConditionDoQuery.getEndPrice()) {
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.rangeQuery("total_price").gt(userFavoriteConditionDoQuery.getBeginPrice() * 0.9), ScoreMode.None));
        }

        //查询
        SearchResponse oneNewHouseByRecommendCondition = newHouseEsDao.getOneNewHouseByRecommendCondition(booleanQueryBuilder, city);
        SearchHit[] hits = oneNewHouseByRecommendCondition.getHits().getHits();
        NewHouseDetailDo newHouseDetailDo = new NewHouseDetailDo();
        if (hits.length > 0) {
            String sourceAsString = hits[0].getSourceAsString();
            newHouseDetailDo = JSON.parseObject(sourceAsString, NewHouseDetailDo.class);
        } else {
            throw new BaseException(NewHouseInterfaceErrorCodeEnum.NEWHOUSE_NOT_FOUND, "新房楼盘列表为空");
        }
        return newHouseDetailDo;
    }

    /**
     * 查询新房猜你喜欢
     *
     * @param newHouseDoQuery
     * @param userId
     * @param city
     * @return
     */
    @Override
    public NewHouseListDomain queryGuessLikeNewHouseList(NewHouseDoQuery newHouseDoQuery, Integer userId, String city) {


        NewHouseListDomain newHouseListVo = new NewHouseListDomain();
        List<NewHouseListDo> newHouseListDoList = new ArrayList<>();
        List<SearchHit> searchHitList = new ArrayList<>();
        int totalCount = 0;
        // 无用户行为取价格不为空，且电话不为空，且户型不为空
        if ((null == userId && (newHouseDoQuery.getDistrictId() == 0 && newHouseDoQuery.getAvgPrice() == 0 && newHouseDoQuery.getTotalPrice() == 0)) ||
                (null != userId && (newHouseDoQuery.getDistrictId() == 0 && newHouseDoQuery.getAvgPrice() == 0 && newHouseDoQuery.getTotalPrice() == 0)
                        && (userFavoriteNewHouseMapper.selectFavoriteNewHouseByUserIdAndCityId(Integer.valueOf(userId), CityUtils.returnCityId(city)) == 0))) {
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{1, 5}));
            booleanQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
            booleanQueryBuilder.must(termQuery("is_del", IS_DEL));
            booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1, 2}));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(1.0)).should(QueryBuilders.rangeQuery("average_price").gte(1.0)));
            booleanQueryBuilder.must(existsQuery("saletelphone"));
            booleanQueryBuilder.mustNot(termQuery("saletelphone", ""));
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.rangeQuery("room").gte(1), ScoreMode.None));

            SearchResponse searchResponse = newHouseEsDao.getGuessLikeNewHouseList(booleanQueryBuilder, city, newHouseDoQuery.getPageNum(), newHouseDoQuery.getPageSize());

            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            totalCount += (int) searchResponse.getHits().getTotalHits();
            if (searchHists.length > 0) {
                searchHitList.addAll(Arrays.asList(searchHists));
            }

        } else {

            if (null != userId) {
                NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery = new NewHouseFavoriteListDoQuery();
                newHouseFavoriteListDoQuery.setUserId(userId);
                newHouseFavoriteListDoQuery.setCityId(CityUtils.returnCityId(city));
                List<NewHouseFavoriteDo> houseFavoriteDoList = userFavoriteNewHouseMapper.selectNewHouseFavoriteByUserIdAndCityId(newHouseFavoriteListDoQuery);

                if (null != houseFavoriteDoList && houseFavoriteDoList.size() > 0) {
                    NewHouseFavoriteDo newHouseFavoriteDo = houseFavoriteDoList.get(0);
                    newHouseDoQuery.setDistrictName(newHouseFavoriteDo.getDistrictName());
                    newHouseDoQuery.setAvgPrice(newHouseFavoriteDo.getAveragePrice().doubleValue());
                    newHouseDoQuery.setTotalPrice(newHouseFavoriteDo.getTotalPrice().doubleValue());
                }
            }


            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{1, 5}));
            booleanQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
            booleanQueryBuilder.must(termQuery("is_del", IS_DEL));
            booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1, 2}));
            if (null != newHouseDoQuery.getDistrictName() && !"".equals(newHouseDoQuery.getDistrictName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_name", newHouseDoQuery.getDistrictName()));
            } else if (null != newHouseDoQuery.getDistrictId() && newHouseDoQuery.getDistrictId() > 0) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("district_id", newHouseDoQuery.getDistrictId()));
            }

            if (newHouseDoQuery.getAvgPrice() > 0) {
                double beginPrice = newHouseDoQuery.getAvgPrice() - (newHouseDoQuery.getAvgPrice() * 0.3);
                double endPrice = newHouseDoQuery.getAvgPrice() + (newHouseDoQuery.getAvgPrice() * 0.3);
                booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(beginPrice).lte(endPrice)));
            } else if (newHouseDoQuery.getTotalPrice() > 0) {
                double beginTotalPrice = newHouseDoQuery.getTotalPrice() - (newHouseDoQuery.getTotalPrice() * 0.3);
                double endTotalPrice = newHouseDoQuery.getTotalPrice() + (newHouseDoQuery.getTotalPrice() * 0.3);
                booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(beginTotalPrice).lte(endTotalPrice)));
            }

            SearchResponse searchResponseT1 = newHouseEsDao.getGuessLikeNewHouseList(booleanQueryBuilder, city, newHouseDoQuery.getPageNum(), newHouseDoQuery.getPageSize());

            SearchHits hitsT1 = searchResponseT1.getHits();
            SearchHit[] searchHistsT1 = hitsT1.getHits();
            int totalHitsT1 = (int) searchResponseT1.getHits().getTotalHits();
            totalCount += totalHitsT1;
            if (searchHistsT1.length > 0) {
                searchHitList.addAll(Arrays.asList(searchHistsT1));
            }

            //当T1条件分页结果不足每页大小,查询T2条件并补充
            if (searchHitList.size() < newHouseDoQuery.getPageSize()) {
                BoolQueryBuilder boolQueryBuilderT2 = QueryBuilders.boolQuery();
                //获取pageNum和pageSize
                int pageNum_T1 = totalHitsT1 / newHouseDoQuery.getPageSize();
                int pageNum_T2 = newHouseDoQuery.getPageNum() - pageNum_T1;
                int pageSize_T2 = newHouseDoQuery.getPageSize();
                if (pageNum_T2 == 1) {
                    pageSize_T2 = newHouseDoQuery.getPageSize() - totalHitsT1 % newHouseDoQuery.getPageSize();
                }
                boolQueryBuilderT2.must(termsQuery("sale_status_id", new int[]{1, 5}));
                boolQueryBuilderT2.must(termQuery("is_approve", IS_APPROVE));
                boolQueryBuilderT2.must(termQuery("is_del", IS_DEL));
                boolQueryBuilderT2.must(termsQuery("property_type_id", new int[]{1, 2}));
                booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(1.0)).should(QueryBuilders.rangeQuery("average_price").gte(1.0)));
                boolQueryBuilderT2.must(existsQuery("saletelphone"));
                boolQueryBuilderT2.mustNot(termQuery("saletelphone", ""));
                boolQueryBuilderT2.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.rangeQuery("room").gte(1), ScoreMode.None));

                SearchResponse searchResponseT2 = newHouseEsDao.getGuessLikeNewHouseList(boolQueryBuilderT2, city, pageNum_T2, pageSize_T2);


                SearchHit[] hitsT2 = searchResponseT2.getHits().getHits();
                int totalHits_T2 = (int) searchResponseT2.getHits().getTotalHits();
                totalCount += totalHits_T2;
                if (hitsT2.length > 0) {
                    searchHitList.addAll(Arrays.asList(hitsT2));
                }

            }
        }

        if (searchHitList.size() > 0) {
            for (SearchHit searchHit : searchHitList) {
                String details = "";
                details = searchHit.getSourceAsString();
                NewHouseListDo newHouseListDos = JSON.parseObject(details, NewHouseListDo.class);

                try {
                    //获取新房下户型的数量
                    NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseListDos.getBuildingNameId(), city);
                    List<NewHouseLayoutCountDo> houseLayoutCountDoList = newHouseLayoutCountDomain.getRooms();
                    if (null != houseLayoutCountDoList && houseLayoutCountDoList.size() > 0) {
                        List<String> roomsType = new ArrayList<>();
                        for (NewHouseLayoutCountDo layoutCountDo : houseLayoutCountDoList) {
                            roomsType.add(layoutCountDo.getRoom().toString());
                        }
                        Collections.sort(roomsType);
                        newHouseListDos.setRoomType(String.join("," , roomsType));
                    } else {
                        newHouseListDos.setRoomType("");
                    }

                } catch (Exception e) {
                    logger.error("获取新房户型信息异常信息={}", e.getStackTrace());
                }

                //新房图片处理
                if (!Objects.equals(newHouseListDos.getBuildingTitleImg(), "") && !newHouseListDos.getBuildingTitleImg().startsWith("http")) {
                    newHouseListDos.setBuildingTitleImg("http://s1.qn.toutiaofangchan.com/" + newHouseListDos.getBuildingTitleImg() + "-dongfangdi1200x900");
                }

                //新房标签
                List<HouseLable> houseLableList = new ArrayList<>();
                String saleStatusName = newHouseListDos.getSaleStatusName();

                if (!StringUtil.isNullString(saleStatusName) && HouseLableEnum.containKey(saleStatusName)) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.getEnumByKey(saleStatusName));
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }
                int isActive = newHouseListDos.getIsActive();
                if (isActive == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.ISACTIVE);
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }
                String propertyType = newHouseListDos.getPropertyType();
                if (!StringUtil.isNullString(propertyType) && HouseLableEnum.containKey(propertyType)) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.getEnumByKey(propertyType));
                    houseLable.setShakeIcon(houseLable.getIcon().replace(".png", "_shake.png"));
                    houseLableList.add(houseLable);
                }

                newHouseListDos.setHouseLabelList(houseLableList);

                //新房动态
                BoolQueryBuilder queryBuilderDynamic = boolQuery();//声明符合查询方法
                queryBuilderDynamic.must(QueryBuilders.termQuery("newcode", newHouseListDos.getBuildingNameId()));
                SearchResponse dynamicResponse = newHouseEsDao.getDynamicByNewCode(queryBuilderDynamic, 1, 10, city);
                long dynamicTotal = dynamicResponse.getHits().totalHits;//动态总数
                newHouseListDos.setDynamicTotal(dynamicTotal);

                //获取新房户型价格范围
                NewHouseLayoutPriceDo newHouseLayoutPriceDo = newHouseLayoutService.getNewHouseLayoutPriceByNewHouseId(newHouseListDos.getBuildingNameId(), city);
                newHouseListDos.setHouseMinPrice(newHouseLayoutPriceDo.getHouseMinPrice());
                newHouseListDos.setHouseMaxPrice(newHouseLayoutPriceDo.getHouseMaxPrice());
                //新房动态
                NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
                newHouseDynamicDoQuery.setNewCode(newHouseListDos.getBuildingNameId());
                newHouseDynamicDoQuery.setPageSize(1);
                List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery, city);
                newHouseListDos.setNewHouseDynamic(newHouseDynamicDoList);
                newHouseListDos.setBuildingFeature("");
//                //获取新房的收藏数量
//                int newHouseFavoriteCount=favoriteRestService.newHouseFavoriteByNewCode(newHouseListDos.getBuildingNameId());
//                newHouseListDos.setNewHouseFavorite(newHouseFavoriteCount);

                //descHigh : 位于 districtName ringRoad环 buildingAddress
                StringBuilder descHigh = new StringBuilder();
                descHigh.append("位于");
                if (StringTool.isNotEmpty(newHouseListDos.getDistrictName())) {
                    descHigh.append(newHouseListDos.getDistrictName());
                }
                if (StringTool.isNotEmpty(newHouseListDos.getRingRoad())) {
                    descHigh.append(newHouseListDos.getRingRoad());
                    descHigh.append("环");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getBuildingAddress())) {
                    descHigh.append(newHouseListDos.getBuildingAddress());
                }
                newHouseListDos.setDescHigh(descHigh.toString().equals("位于") ? "" : descHigh.toString());

                //descMid : 位于 developers建propertyType,openedTimeDesc,deliverTimeDesc
                StringBuilder descMidSb = new StringBuilder();
                if (StringTool.isNotEmpty(newHouseListDos.getDevelopers())) {
                    descMidSb.append(newHouseListDos.getDevelopers());
                    descMidSb.append("建");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getPropertyType())) {
                    descMidSb.append(newHouseListDos.getPropertyType());
                    descMidSb.append(",");
                } else if (StringTool.isNotEmpty(descMidSb.toString())) {
                    descMidSb.append(",");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getOpenedTimeDesc())) {
                    descMidSb.append(newHouseListDos.getOpenedTimeDesc());
                    descMidSb.append(",");
                }
                if (StringTool.isNotEmpty(newHouseListDos.getDeliverTimeDesc())) {
                    descMidSb.append(newHouseListDos.getDeliverTimeDesc());
                    descMidSb.append(",");
                }
                String descMidStr = "";
                if (StringTool.isNotEmpty(descMidSb.toString())) {
                    descMidStr = descMidSb.substring(0, descMidSb.length() - 1);
                }
                newHouseListDos.setDescMid(descMidStr);
                newHouseListDoList.add(newHouseListDos);
            }

            newHouseListVo.setData(newHouseListDoList);
            newHouseListVo.setTotalCount(totalCount);
        } else {
            newHouseListVo.setData(newHouseListDoList);
            newHouseListVo.setTotalCount(totalCount);
        }
        return newHouseListVo;
    }


    @Override
    public NewHouseCustomConditionDomain getNewHouseCustomList(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        //NewHouseCustomConditionDo newHouseCustomConditionDo = new NewHouseCustomConditionDo();
        List<NewHouseCustomConditionDo> newHouseCustomConditionDos = new ArrayList<>();
        NewHouseCustomConditionDomain newHouseCustomConditionDomain = new NewHouseCustomConditionDomain();
        BoolQueryBuilder builder =QueryBuilders.boolQuery();
        if(StringTool.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId()) && userFavoriteConditionDoQuery.getDistrictId().length!=0){
            builder.must(QueryBuilders.termsQuery("district_id",userFavoriteConditionDoQuery.getDistrictId()));
        }
        builder.must(termQuery("is_approve", IS_APPROVE));
        builder.must(termQuery("is_del", IS_DEL));
        builder.must(termsQuery("property_type_id", new int[]{1, 2}));

        builder.must(existsQuery("saletelphone"));
        builder.must(QueryBuilders.boolQuery().should(rangeQuery("total_price").gt(0)).should(rangeQuery("average_price").gt(0)));

//        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[4];
//        QueryBuilder total_price_filter = QueryBuilders.rangeQuery("total_price").gt(0);
//        filterFunctionBuilders[0] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(total_price_filter, ScoreFunctionBuilders.weightFactorFunction(3));
//        QueryBuilder average_price_filter = QueryBuilders.rangeQuery("average_price").gt(0);
//        filterFunctionBuilders[1] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(average_price_filter, ScoreFunctionBuilders.weightFactorFunction(2));
//        QueryBuilder saleTelPhone = QueryBuilders.existsQuery("saletelphone");
//        filterFunctionBuilders[2] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(saleTelPhone, ScoreFunctionBuilders.weightFactorFunction(3));
//        QueryBuilder phone = QueryBuilders.termQuery("saletelphone","");
//        filterFunctionBuilders[3] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(phone, ScoreFunctionBuilders.weightFactorFunction(0));
//
//        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(builder,filterFunctionBuilders);




        SearchResponse searchResponse = newHouseEsDao.getNewHouseCustomList(builder, userFavoriteConditionDoQuery.getPageNum(), userFavoriteConditionDoQuery.getPageSize(), city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length>0){
            for(SearchHit searchHit : searchHists){
                String  details = searchHit.getSourceAsString();
                NewHouseCustomConditionDo newHouseCustomConditionDo = JSON.parseObject(details, NewHouseCustomConditionDo.class);
                if(StringTool.isDoubleNotEmpty(newHouseCustomConditionDo.getTotalPrice())){
                    newHouseCustomConditionDo.setPriceDesc(newHouseCustomConditionDo.getTotalPrice()+"万/套");
                }else if (StringTool.isDoubleNotEmpty(newHouseCustomConditionDo.getAveragePrice())){
                    newHouseCustomConditionDo.setPriceDesc(newHouseCustomConditionDo.getAveragePrice()+"元/m²");
                }else {
                    newHouseCustomConditionDo.setPriceDesc("售价待定");
                }
                //新房图片处理
                if (!Objects.equals(newHouseCustomConditionDo.getBuildingTitleImg(), "") && !newHouseCustomConditionDo.getBuildingTitleImg().startsWith("http")) {
                    newHouseCustomConditionDo.setBuildingTitleImg("http://s1.qn.toutiaofangchan.com/" + newHouseCustomConditionDo.getBuildingTitleImg() + "-dongfangdi1200x900");
                }
                newHouseCustomConditionDos.add(newHouseCustomConditionDo);
            }
        }
        newHouseCustomConditionDomain.setData(newHouseCustomConditionDos);

        return newHouseCustomConditionDomain;
    }


}
