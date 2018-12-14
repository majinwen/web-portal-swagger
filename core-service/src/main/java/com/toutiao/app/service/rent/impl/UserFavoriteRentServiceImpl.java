package com.toutiao.app.service.rent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.mapsearch.RentMapSearchEsDao;
import com.toutiao.app.dao.rent.UserFavoriteRentEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.mapSearch.RentMapSearchRestService;
import com.toutiao.app.service.rent.UserFavoriteRentService;
import com.toutiao.app.service.subway.SubwayLineService;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.subway.SubwayLineDo;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SubwayLineService subwayLineService;

    @Autowired
    private RentMapSearchRestService rentMapSearchRestService;

    @Autowired
    private RentMapSearchEsDao rentMapSearchEsDao;

    private static final String LAYOUT = "5";

    @Override
    public UserFavoriteRentListDomain queryRentListByUserFavorite(UserFavoriteRentListDoQuery rentHouseDoQuery, String city) {
        UserFavoriteRentListDomain userFavoriteRentListDomain = new UserFavoriteRentListDomain();
        List<UserFavoriteRentDetailDo> userFavoriteRentDetailDos = new ArrayList<>();
        Date date = new Date();
        //添加筛选条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getUserFavoriteRentBoolQueryBuilder(rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源

        SearchResponse searchResponse = userFavoriteRentEsDao.queryRentListByUserFavorite(boolQueryBuilder, rentHouseDoQuery.getPageNum(),
                rentHouseDoQuery.getPageSize(), city, rentHouseDoQuery.getSort());

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

    @Override
    public RentCustomConditionDomain querySubwayLineHouse(UserFavoriteRentListDoQuery rentHouseDoQuery, String city) {
        RentCustomConditionDomain rentCustomConditionDomain = new RentCustomConditionDomain();
        List<RentCustomConditionDo> rentCustomConditionDos = new ArrayList<>();
        RentCustomDo rentCustomDo = new RentCustomDo();

        List newList = new ArrayList();
        Hashtable<String, RentCustomConditionDo> allStationDict = new Hashtable<>();
        Date date = new Date();
        //添加筛选条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = getUserFavoriteRentBoolQueryBuilder(rentHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源


        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (null != rentHouseDoQuery.getSubwayLineId()) {
//            for (int i = 0; i < rentHouseDoQuery.getSubwayLineId().length; i++) {
                IncludeExclude includeExclude = null;
                String[] excludeValues = new String[]{};
                //获取地铁线
                Integer subwayLineId = rentHouseDoQuery.getSubwayLineId()[0];
                //获取地铁站
                SearchSourceBuilder searchSubwayLine = new SearchSourceBuilder().size(100);
                BoolQueryBuilder subwayLine = QueryBuilders.boolQuery();
                subwayLine.must(QueryBuilders.termQuery("subway_line_id",subwayLineId));
                subwayLine.must(QueryBuilders.termQuery("city_id", CityUtils.returnCityId(city)));
                searchSubwayLine.query(subwayLine);

                SearchResponse subwayLineAndSubwayStationinfo = rentMapSearchEsDao.getSubwayLineAndSubwayStationinfo(searchSubwayLine);
                if (null!=subwayLineAndSubwayStationinfo) {
                    SearchHit[] hits = subwayLineAndSubwayStationinfo.getHits().getHits();
                    if (ArrayUtils.isNotEmpty(hits)) {
                        Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
                        List<Integer> list = (List) sourceAsMap.get("subway_station_id");
                        for (Integer l : list) {
                            newList.add(String.valueOf(l));
                        }
                        String[] strings = new String[newList.size()];
                        String[] includeValues = (String[]) newList.toArray(strings);
                        includeExclude = new IncludeExclude(includeValues, excludeValues);
                    }
                }
                searchSourceBuilder.query(boolQueryBuilder);

                //对地铁站做聚合
                searchSourceBuilder.aggregation(AggregationBuilders.terms("id").field("subway_station_id")
                        .subAggregation(AggregationBuilders.terms("community").field("zufang_id"))
                        .order(BucketOrder.key(true)).size(1000).includeExclude(includeExclude)).sort("sortingScore", SortOrder.DESC);
                BoolQueryBuilder subwayStation = QueryBuilders.boolQuery();
                subwayStation.must(QueryBuilders.termQuery("line_id",subwayLineId));
                SearchResponse subwayStationinfo = rentMapSearchEsDao.getSubwayStationinfo(subwayStation, city);
                SearchHit[] hits = subwayStationinfo.getHits().getHits();
                if(hits.length>0){
                    for(SearchHit hit : hits){
                        RentCustomConditionDo subwayStationHouseDo = new RentCustomConditionDo();
                        subwayStationHouseDo.setId((Integer) hit.getSourceAsMap().get("station_id"));
                        subwayStationHouseDo.setName((String) hit.getSourceAsMap().get("station_name"));
                        subwayStationHouseDo.setLatitude((Double) hit.getSourceAsMap().get("latitude"));
                        subwayStationHouseDo.setLongitude((Double) hit.getSourceAsMap().get("longitude"));
                        subwayStationHouseDo.setDesc("0套");
//                        allStationDict.put(subwayStationHouseDo.getId().toString(),subwayStationHouseDo);
                    }
                }

                SearchResponse searchResponse = userFavoriteRentEsDao.querySubwayLineHouse(searchSourceBuilder, city);
                if (null!=searchResponse) {
                    Terms ID = searchResponse.getAggregations().get("id");
                    List buckets = ID.getBuckets();
                    for (Object bucket : buckets) {
                        RentCustomConditionDo rentMapSearchDo = new RentCustomConditionDo();
                        int id = Integer.parseInt(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsString());
                        rentMapSearchDo.setId(id);
                        //房源数量
                        rentMapSearchDo.setHouseCount((int) ((ParsedLongTerms.ParsedBucket) bucket).getDocCount());
                        rentMapSearchDo.setDesc(rentMapSearchDo.getHouseCount() + "套");
                        //楼盘数量
                        Integer communityCount = 0;
                        Terms communityTerm = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("community");
                        List communityBuckets = communityTerm.getBuckets();
                        for (Object communityBucket : communityBuckets) {
                            communityCount += (int)((ParsedStringTerms.ParsedBucket) communityBucket).getDocCount();
                        }
                        rentMapSearchDo.setCommunityCount(communityCount);
                        //地铁站信息
                        Map subwayInfo = rentMapSearchRestService.getSubwayInfo(id, CityUtils.returnCityId(city));
                        //名称
                        rentMapSearchDo.setName((String) subwayInfo.get("name"));
                        //经度
                        rentMapSearchDo.setLatitude((Double) subwayInfo.get("lat"));
                        //维度
                        rentMapSearchDo.setLongitude((Double) subwayInfo.get("lon"));
                        rentCustomConditionDos.add(rentMapSearchDo);
                    }
                }
                rentCustomDo.setId(subwayLineId);
                //设置地铁线名称
                SubwayLineDo subwayLineDo = subwayLineService.selectLineInfoByLineId(subwayLineId);
                rentCustomDo.setName(subwayLineDo.getSubwayName());
                rentCustomConditionDomain.setRentCustomDo(rentCustomDo);
                rentCustomConditionDomain.setRentCustomConditionDos(rentCustomConditionDos);
//            }
        }
        if (null != rentHouseDoQuery.getDistrictId()) {
            //获取区县id
            Integer districtId = rentHouseDoQuery.getDistrictId()[0];
            searchSourceBuilder.aggregation(AggregationBuilders.terms("id").field("district_id")
                    .subAggregation(AggregationBuilders.terms("community").field("zufang_id")).size(200)).sort("sortingScore", SortOrder.DESC);
            SearchResponse searchResponse = userFavoriteRentEsDao.querySubwayLineHouse(searchSourceBuilder, city);
            if (null!=searchResponse) {
                Terms ID = searchResponse.getAggregations().get("id");
                List buckets = ID.getBuckets();
                for (Object bucket : buckets) {
                    int id = Integer.parseInt(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsString());
                    if (id == districtId) {
                        RentCustomConditionDo rentMapSearchDo = new RentCustomConditionDo();
                        rentMapSearchDo.setId(id);
                        //区县名称
                        Map map = rentMapSearchRestService.getDistanceAndAreainfo(id, 1);
                        rentMapSearchDo.setName("");
                        if (null != map.get("name")) {
                            rentMapSearchDo.setName(map.get("name").toString());
                        }
                        //区县坐标
                        rentMapSearchDo.setLatitude(0.0);
                        if (null != map.get("lat")) {
                            rentMapSearchDo.setLatitude(Double.valueOf(map.get("lat").toString()));
                        }
                        rentMapSearchDo.setLongitude(0.0);
                        if (null != map.get("lon")) {
                            rentMapSearchDo.setLongitude(Double.valueOf(map.get("lon").toString()));
                        }
                        //房源数量
                        rentMapSearchDo.setHouseCount((int) ((ParsedLongTerms.ParsedBucket) bucket).getDocCount());
                        rentMapSearchDo.setDesc(rentMapSearchDo.getHouseCount() + "套");
                        //楼盘数量
                        Integer communityCount = 0;
                        Terms communityTerm = ((ParsedLongTerms.ParsedBucket) bucket).getAggregations().get("community");
                        List communityBuckets = communityTerm.getBuckets();
                        for (Object communityBucket : communityBuckets) {
                            communityCount += (int)((ParsedStringTerms.ParsedBucket) communityBucket).getDocCount();
                        }
                        rentMapSearchDo.setCommunityCount(communityCount);
                        rentCustomConditionDos.add(rentMapSearchDo);
                    }
                }
            }
            rentCustomConditionDomain.setRentCustomConditionDos(rentCustomConditionDos);
        }
        return rentCustomConditionDomain;
    }

    /**
     * 根据定制条件构建BoolQueryBuilder
     * @param rentHouseDoQuery
     * @return
     */
    public BoolQueryBuilder getUserFavoriteRentBoolQueryBuilder(UserFavoriteRentListDoQuery rentHouseDoQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //区域
        if (StringTool.isNotEmpty(rentHouseDoQuery.getDistrictId()) && rentHouseDoQuery.getDistrictId().length!=0) {
            boolQueryBuilder.must(termsQuery("district_id", rentHouseDoQuery.getDistrictId()));
        }

        //地铁线id
        if (StringTool.isNotEmpty(rentHouseDoQuery.getSubwayLineId()) && rentHouseDoQuery.getSubwayLineId().length != 0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", rentHouseDoQuery.getSubwayLineId()));
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
                String[] roommore = new String[]{"6", "7", "8", "9", "10", "11", "12", "13", "14"};
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
