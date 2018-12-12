package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.rent.UserFavoriteRentEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.rent.RentHouseDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentDetailDo;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.app.service.rent.UserFavoriteRentService;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Service
public class UserFavoriteRentServiceImpl implements UserFavoriteRentService {

    @Autowired
    private UserFavoriteRentEsDao userFavoriteRentEsDao;

    @Autowired
    private AgentService agentService;

    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;

    private static final String LAYOUT = "4";

    @Override
    public UserFavoriteRentListDomain queryRentListByUserFavorite(RentHouseDoQuery rentHouseDoQuery, String city) {
        UserFavoriteRentListDomain userFavoriteRentListDomain = new UserFavoriteRentListDomain();
        List<UserFavoriteRentDetailDo> userFavoriteRentDetailDos = new ArrayList<>();
        Date date = new Date();
        //添加筛选条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getUserFavoriteRentBoolQueryBuilder(rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源
        FunctionScoreQueryBuilder query = null;
        //设置基础分(录入优先展示)(录入:1,导入1/3)
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("rentHouseTypeId")
                .modifier(FieldValueFactorFunction.Modifier.RECIPROCAL).missing(10);
        GaussDecayFunctionBuilder functionBuilder = null;
        GeoDistanceSortBuilder geoDistanceSort = null;
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
            double[] location = new double[]{rentHouseDoQuery.getLon(), rentHouseDoQuery.getLat()};

            //设置高斯函数(要保证5km内录入的排在导入的前面,录入房源的最低分需要大于导入的最高分)
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location", location, "4km", "1km", 0.4);
            //根据坐标计算距离

            geoDistanceSort = SortBuilders.geoDistanceSort("location", rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
        }

        //获取5km内的所有出租房源(函数得分进行加法运算,查询得分和函数得分进行乘法运算)
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, fieldValueFactor).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);

        if (StringUtil.isNotNullString(rentHouseDoQuery.getKeyword())) {
            List<String> searchKeyword = nearRentHouseRestService.getAnalyzeByKeyWords(rentHouseDoQuery.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
            } else {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("district_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else {
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("zufang_name_search", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }

            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }


        } else {
            if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, functionBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }
        }
        SearchResponse searchResponse = userFavoriteRentEsDao.queryRentListByUserFavorite(query, rentHouseDoQuery.getDistance(), rentHouseDoQuery.getKeyword(), rentHouseDoQuery.getPageNum(),
                rentHouseDoQuery.getPageSize(), city, geoDistanceSort, rentHouseDoQuery.getSort());

        SearchHit[] searchHits = searchResponse.getHits().getHits();

        if (searchHits.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                String sourceAsString = searchHit.getSourceAsString();
                UserFavoriteRentDetailDo userFavoriteRentDetailDo = JSON.parseObject(sourceAsString, UserFavoriteRentDetailDo.class);

                String nearbyDistance = StringTool.nullToString(userFavoriteRentDetailDo.getDistrictName()) + " " + StringTool.nullToString(userFavoriteRentDetailDo.getAreaName());
                String traffic = userFavoriteRentDetailDo.getNearestSubway();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }

                if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance()) && rentHouseDoQuery.getDistance() > 0) {
                    BigDecimal geoDis = new BigDecimal((Double) searchHit.getSortValues()[0]);
                    String distances= geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                    nearbyDistance = "距您" + distances;
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = userFavoriteRentDetailDo.getCreateTime();
                int isDefault = isDefaultImage(importTime, date, userFavoriteRentDetailDo.getHouseTitleImg());
                if (isDefault == 1) {
                    userFavoriteRentDetailDo.setIsDefaultImage(1);
                }

                List<Map<String, String>> rentHouseImg = (List<Map<String, String>>) searchHit.getSourceAsMap().get("rent_house_img");
                for (int i = 0; i < rentHouseImg.size(); i++) {
                    imgs.add(rentHouseImg.get(i).get("image_path"));

                }
                userFavoriteRentDetailDo.setHousePhoto(imgs.toArray(new String[0]));
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (StringTool.isNotEmpty(userFavoriteRentDetailDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(userFavoriteRentDetailDo.getUserId().toString(), city);

                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("estate_agent") == null ? "" : searchHit.getSourceAsMap().get("estate_agent").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("brokerage_agency") == null ? "" : searchHit.getSourceAsMap().get("brokerage_agency").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("phone") == null ? "" : searchHit.getSourceAsMap().get("phone").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("agent_headphoto") == null ? "" : searchHit.getSourceAsMap().get("agent_headphoto").toString());

                }

                //增加房子与地铁的距离
                String keys = "";
                if (null != rentHouseDoQuery.getSubwayLineId() && rentHouseDoQuery.getSubwayLineId() > 0) {
                    keys += rentHouseDoQuery.getSubwayLineId().toString();
                }
                if (null != rentHouseDoQuery.getSubwayStationId() && rentHouseDoQuery.getSubwayStationId().length > 0) {
                    Map<Integer,String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for (int i=0; i<rentHouseDoQuery.getSubwayStationId().length; i++) {
                        String stationKey = keys+"$"+rentHouseDoQuery.getSubwayStationId()[i];
                        if (StringTool.isNotEmpty(userFavoriteRentDetailDo.getNearbySubway().get(stationKey))) {
                            String stationValue = userFavoriteRentDetailDo.getNearbySubway().get(stationKey);
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance,stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    userFavoriteRentDetailDo.setSubwayDistanceInfo(userFavoriteRentDetailDo.getNearbySubway().get(map.get(minDistance)));
                    trafficArr = userFavoriteRentDetailDo.getSubwayDistanceInfo().split("\\$");
                    if (trafficArr.length == 3) {
                        nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";

                    }
                }

                if(StringTool.isNotEmpty(nearbyDistance)){
                    userFavoriteRentDetailDo.setNearbyDistance(nearbyDistance);
                }

                //设置标题图
                String titlePhoto = userFavoriteRentDetailDo.getHouseTitleImg();
                if (!Objects.equals(titlePhoto, "") && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                userFavoriteRentDetailDo.setHouseTitleImg(titlePhoto);

                //设置公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if(!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)){
                    userFavoriteRentDetailDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
                userFavoriteRentDetailDo.setAgentBaseDo(agentBaseDo);
                fullHouseBarrage(userFavoriteRentDetailDo);
                userFavoriteRentDetailDos.add(userFavoriteRentDetailDo);
            }
            userFavoriteRentListDomain.setFavoriteRentDetails(userFavoriteRentDetailDos);
            userFavoriteRentListDomain.setTotalCount((int) searchResponse.getHits().getTotalHits());
        }

        return userFavoriteRentListDomain;
    }

    /**
     * 根据定制条件构建BoolQueryBuilder
     * @param rentHouseDoQuery
     * @return
     */
    public BoolQueryBuilder getUserFavoriteRentBoolQueryBuilder(RentHouseDoQuery rentHouseDoQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //关键字
        if (StringTool.isNotEmpty(rentHouseDoQuery.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
//                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", rentHouseDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("zufang_nickname", rentHouseDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //附近
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistance())&&rentHouseDoQuery.getDistance()!=0) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                    .point(rentHouseDoQuery.getLat(), rentHouseDoQuery.getLon())
                    .distance(rentHouseDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
        }

        //城市
        if (StringTool.isNotEmpty(rentHouseDoQuery.getCityId()) && rentHouseDoQuery.getCityId()!=0) {
            boolQueryBuilder.must(termQuery("city_id", rentHouseDoQuery.getCityId()));
        }
        //区域
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistrictId()) && rentHouseDoQuery.getDistrictId()!=0) {
            boolQueryBuilder.must(termQuery("district_id", rentHouseDoQuery.getDistrictId()));
        }
        //商圈
        if (StringTool.isNotEmpty(rentHouseDoQuery.getAreaId()) && rentHouseDoQuery.getAreaId().length !=0) {
            boolQueryBuilder.must(termsQuery("area_id", rentHouseDoQuery.getAreaId()));
        }
        //地铁线id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayLineId()) && rentHouseDoQuery.getSubwayLineId() != 0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseDoQuery.getSubwayLineId()}));
        }
        //地铁站id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayStationId()) && rentHouseDoQuery.getSubwayStationId().length!=0) {
            boolQueryBuilder.must(termsQuery("subway_station_id", rentHouseDoQuery.getSubwayStationId()));
        }
        //租金
        if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentHouseDoQuery.getBeginPrice()).lte(rentHouseDoQuery.getEndPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() != 0 && rentHouseDoQuery.getEndPrice() == 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentHouseDoQuery.getBeginPrice()));
        } else if (rentHouseDoQuery.getBeginPrice() == 0 && rentHouseDoQuery.getEndPrice() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").lte(rentHouseDoQuery.getEndPrice()));
        }

        //来源
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSource())) {
            String[] source = rentHouseDoQuery.getSource().split(",");
            boolQueryBuilder.must(termsQuery("data_source_sign", source));
        }
        //朝向
        if (StringTool.isNotEmpty(rentHouseDoQuery.getForwardId())) {
            Integer[] forword = rentHouseDoQuery.getForwardId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }
        //面积
//        if (StringTool.isNotEmpty(rentHouseDoQuery。)){
//            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//            String area = rentHouseDoQuery.getRentHouseArea().replaceAll("\\[","")
//                    .replaceAll("]","").replaceAll("-",",");
//            String[] layoutId = area.split(",");
//            for (int i = 0; i < layoutId.length; i = i + 2) {
//                if (i + 1 > layoutId.length) {
//                    break;
//                }
//                booleanQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
//                boolQueryBuilder.must(booleanQueryBuilder);
//            }
//        }
        if (rentHouseDoQuery.getBeginArea() != 0 && rentHouseDoQuery.getEndArea() != 0) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()).lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getBeginArea() && 0 != rentHouseDoQuery.getEndArea()) {

            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").lte(rentHouseDoQuery.getEndArea()));

        } else if (0 == rentHouseDoQuery.getEndArea() && 0 != rentHouseDoQuery.getBeginArea()) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentHouseDoQuery.getBeginArea()));

        }
//        //整租/合租
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getRentType())){
//            String[] split = rentHouseDoQuery.getRentType().split(",");
//            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
//        }
//        //几居
//        if (StringTool.isNotEmpty(rentHouseDoQuery.getLayoutId())){
//            Integer[] split = rentHouseDoQuery.getLayoutId();
//            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
//        }


        //户型
        if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && !StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {

            if (rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("erent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getElo().split(",");
                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("erent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("erent_layout", room));
                }
            }

        } else if (!StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            if (rentHouseDoQuery.getJlo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("jrent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentHouseDoQuery.getJlo().split(",");

                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("jrent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("jrent_layout", room));
                }
            }

        } else if (StringTool.isNotBlank(rentHouseDoQuery.getElo()) && StringTool.isNotBlank(rentHouseDoQuery.getJlo())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            if (rentHouseDoQuery.getJlo().equals("0") && rentHouseDoQuery.getElo().equals("0")) {
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (rentHouseDoQuery.getElo().equals("0") && !rentHouseDoQuery.getJlo().equals("0")) {
                String[] jroom = rentHouseDoQuery.getJlo().split(",");
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));

                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (!rentHouseDoQuery.getElo().equals("0") && rentHouseDoQuery.getJlo().equals("0")) {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));

                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else {
                String[] eroom = rentHouseDoQuery.getElo().split(",");
                String[] jroom = rentHouseDoQuery.getJlo().split(",");

                //String[] roommore = new String[]{"4","5","6","7","8","9","10","11","12","13","14"};
                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            }

        }

        //标签
        if (StringTool.isNotEmpty(rentHouseDoQuery.getLabelId())) {
            Integer[] split = rentHouseDoQuery.getLabelId();
            BoolQueryBuilder booleanQueryBuilder = boolQuery();
            boolean has_subway = Arrays.asList(split).contains(0);

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(0)) {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("has_subway", 1));
                } else {
                    booleanQueryBuilder.must(QueryBuilders.termQuery("rent_house_tags_id", split[i]));
                }
            }
            boolQueryBuilder.must(booleanQueryBuilder);

        /*    if(has_subway){
                Integer[] tagOther = new Integer[split.length-1];
                int idx = 0;
                for(int i=0;i<split.length;i++){
                    if(split[i].equals(0)){
                        booleanQueryBuilder.should(QueryBuilders.termQuery("has_subway", 1));
                    }else {
                        tagOther[idx++] = split[i];
                    }
                }
                if(tagOther.length!=0){
                    booleanQueryBuilder.should(QueryBuilders.termsQuery("rent_house_tags_id", tagOther));
                }
                boolQueryBuilder.must(booleanQueryBuilder);
            }else{
                boolQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", split));
            }*/
        }
        boolQueryBuilder.must(termQuery("is_del", 0));
        boolQueryBuilder.must(termQuery("release_status", 1));
        return boolQueryBuilder;
    }

    /**
     * 租房判断是否上传默认图
     * @param importTime
     * @param today
     * @param image
     * @return
     */
    public int isDefaultImage(String importTime, Date today, String image) {
        if (StringTool.isEmpty(image)) {
            if (StringTool.isNotEmpty(importTime)) {
                int betweenDays = DateUtil.daysBetween(today, DateUtil.parseV1(importTime));
                if (betweenDays <= 3) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
        return 0;
    }

    /**
     * 增加弹幕信息
     * @param userFavoriteRentDetailDo
     */
    private void fullHouseBarrage(UserFavoriteRentDetailDo userFavoriteRentDetailDo) {
        //二手房弹幕第一行
        List<String> houseBarrageFirstList = new ArrayList<>();
        if(StringTool.isNotEmpty(userFavoriteRentDetailDo.getHouseTitle())){
            houseBarrageFirstList.add(userFavoriteRentDetailDo.getHouseTitle());
        }
        if(StringTool.isNotEmpty(userFavoriteRentDetailDo.getRentTypeName())){
            houseBarrageFirstList.add(userFavoriteRentDetailDo.getRentTypeName());
        }
        userFavoriteRentDetailDo.setHouseBarrageFirstList(houseBarrageFirstList);

        //二手房弹幕第二行
        List<String> houseBarrageSecondList = new ArrayList<>();
        if(StringTool.isNotEmpty(userFavoriteRentDetailDo.getNearestSubway())){
            String[] trafficArr = userFavoriteRentDetailDo.getNearestSubway().split("\\$");
            if (trafficArr.length == 3) {
                String  nearbyDistance = "距" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                houseBarrageSecondList.add(nearbyDistance);
            }
        }
        if(userFavoriteRentDetailDo.getForward().contains("东") || userFavoriteRentDetailDo.getForward().contains("南")){
            houseBarrageSecondList.add("采光很好");
        }
        for (String tag : userFavoriteRentDetailDo.getRentHouseTagsName()){
            houseBarrageSecondList.add(tag);
        }
        userFavoriteRentDetailDo.setHouseBarrageSecondList(houseBarrageSecondList);

    }
}
