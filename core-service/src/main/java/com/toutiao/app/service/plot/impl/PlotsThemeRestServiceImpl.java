package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotsThemeEsDao;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.plot.PlotsMarketService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.plot.PlotsThemeRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.app.service.rent.impl.RentRestRestServiceImpl;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 小区主题落地页服务层实现类
 */
@Service
public class PlotsThemeRestServiceImpl implements PlotsThemeRestService {

    @Autowired
    private PlotsThemeEsDao plotsThemeEsDao;
    @Autowired
    private PlotsEsfRestService plotsEsfRestService;
    @Autowired
    private RentRestService rentRestService;

    @Autowired
    private PlotsRestService plotsRestService;

    @Autowired
    private CommunityRestService communityRestService;
    @Autowired
    private PlotsMarketService plotsMarketService;

    public static final String COMMUNITY_FIRST = "首置社区";

    public static final String COMMUNITY_IMPROVE = "改善社区";

    public static final String COMMUNITY_LUXURY = "豪宅社区";

    public static final String COMMUNITY_VILLA = "别墅社区";

    /**
     * 获取小区主题数据
     */
    @Override
    public PlotsThemeDomain getPlotsThemeList(PlotsThemeDoQuery plotsThemeDoQuery, String city) {
        PlotsThemeDomain plotsThemeDomain = new PlotsThemeDomain();
        List<PlotsThemeDo> plotsThemeDos = new ArrayList<>();
        //小区筛选条件
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        String nearestPark = plotsThemeDoQuery.getNearestPark();

        //主题标签
        Integer recommendBuildTagsId = plotsThemeDoQuery.getRecommendBuildTagsId();

        Map<Integer, Map<String, Integer>> countByBuildTagsMap = communityRestService.getCountByBuildTags(CityUtils.returnCityId(city));
        if (recommendBuildTagsId != null && StringTool.isNotEmpty(plotsThemeDoQuery.getDistrictIds()) && plotsThemeDoQuery.getDistrictIds().length != 0) {

            Map<String, Integer> stringIntegerMap = countByBuildTagsMap.get(recommendBuildTagsId);
            plotsThemeDomain.setCommunityCount(stringIntegerMap.get(plotsThemeDoQuery.getDistrictIds()[0].toString()));
        }


        if (recommendBuildTagsId != null) {
            if (recommendBuildTagsId == 6 && StringTool.isNotEmpty(nearestPark)) {
                boolQueryBuilder.must(QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("recommendBuildTagsId", recommendBuildTagsId))
                        .must(QueryBuilders.termQuery("nearestPark", nearestPark)));
            } else {
                boolQueryBuilder.must(QueryBuilders.termQuery("recommendBuildTagsId", recommendBuildTagsId));
            }
        }

//        if (plotsThemeDoQuery.getBeginPrice() != 0 && plotsThemeDoQuery.getEndPrice() != 0) {
//            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("total_price")
//                    .gte(plotsThemeDoQuery.getBeginPrice()).lte(plotsThemeDoQuery.getEndPrice()), ScoreMode.None));
//        } else if (plotsThemeDoQuery.getBeginPrice() != 0 && plotsThemeDoQuery.getEndPrice() == 0) {
//            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("total_price")
//                    .gte(plotsThemeDoQuery.getBeginPrice()), ScoreMode.None));
//        } else if (plotsThemeDoQuery.getBeginPrice() == 0 && plotsThemeDoQuery.getEndPrice() != 0) {
//            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.VILLAGES_CHILD_NAME, QueryBuilders.rangeQuery("total_price")
//                    .lte(plotsThemeDoQuery.getEndPrice()), ScoreMode.None));
//        }

        //区域
        Integer[] districtIds = plotsThemeDoQuery.getDistrictIds();
        if (StringTool.isNotEmpty(districtIds) && districtIds.length != 0) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("areaId", districtIds));
        }
        Integer pageNum = plotsThemeDoQuery.getPageNum();
        Integer pageSize = plotsThemeDoQuery.getPageSize();
        SearchResponse plotsThemeList = plotsThemeEsDao.getPlotsThemeList(boolQueryBuilder, pageNum, pageSize, city);

        SearchHits hits = plotsThemeList.getHits();
        SearchHit[] searchHists = hits.getHits();

        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                PlotsThemeDo plotsThemeDo = JSON.parseObject(details, PlotsThemeDo.class);
                String nearbyDistance = StringTool.nullToString(plotsThemeDo.getArea()) + " " + StringTool.nullToString(plotsThemeDo.getTradingArea());
                String traffic = plotsThemeDo.getTrafficInformation();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
//                    int i = Integer.parseInt(trafficArr[2]);
                    DecimalFormat df = new DecimalFormat("0.0");
//                    nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                }



                plotsThemeDo.setTrafficInformation(nearbyDistance);

                //推荐理由
                CommunityReviewDo communityReviewDo = plotsRestService.getReviewById(plotsThemeDo.getId(), city);

                plotsThemeDo.setRecommendReason(communityReviewDo);

                //查询小区房源最大最小面积
                SearchResponse searchResponse = plotsThemeEsDao.getHouseMaxAndMinArea(plotsThemeDo.getId(), city);
                Map aggMap = searchResponse.getAggregations().asMap();
                Max maxHouse = (Max) aggMap.get("max");
                Min minHouse = (Min) aggMap.get("min");


                plotsThemeDo.setHouseMaxArea(maxHouse.getValue());
                plotsThemeDo.setHouseMinArea(minHouse.getValue());
                //二手房房源数量
                PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryHouseCountByPlotsId(plotsThemeDo.getId(), city);

                if (plotsEsfRoomCountDomain.getTotalCount() != null) {
                    plotsThemeDo.setHouseCount(plotsEsfRoomCountDomain.getTotalCount().intValue());
                } else {
                    plotsThemeDo.setHouseCount(0);
                }
                //租房房源数量
                RentNumListDo rentNumListDo = rentRestService.queryRentNumByPlotId(plotsThemeDo.getId(), city);
                if (rentNumListDo != null) {
                    plotsThemeDo.setRentCount(rentNumListDo.getTotalNum());
                } else {
                    plotsThemeDo.setRentCount(0);
                }

                List<String> tagsName = new ArrayList<>();
               // plotsThemeDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                Map<Integer, Map<String, Integer>> countByBuildTags = communityRestService.getCountByBuildTags(CityUtils.returnCityId(city));
                if(StringTool.isNotEmpty(recommendBuildTagsId) && recommendBuildTagsId!=6){
                    String communityType = "";
                    if(recommendBuildTagsId==2){
                        communityType = COMMUNITY_FIRST;
                    }else if(recommendBuildTagsId==3){
                        communityType = COMMUNITY_IMPROVE;
                    }else if(recommendBuildTagsId==4){
                        communityType = COMMUNITY_LUXURY;
                    }else if(recommendBuildTagsId==5){
                        communityType = COMMUNITY_VILLA;
                    }
                    Map<String, Integer> stringIntegerMap = countByBuildTags.get(recommendBuildTagsId);
                    tagsName.add(plotsThemeDo.getArea()+stringIntegerMap.get(plotsThemeDo.getAreaId())+"大"+communityType);
                }

                PlotMarketDo plotMarketDo = plotsMarketService.queryPlotMarketByPlotId(plotsThemeDo.getId());

                if (null != plotMarketDo) {
                    tagsName.add(plotsThemeDo.getArea()+"热度榜第"+plotMarketDo.getTotalSort()+"名");
                    plotsThemeDo.setDistrictHotSort(plotMarketDo.getTotalSort());
                }



                List<String> recommendTags = (List<String>) searchHit.getSourceAsMap().get("recommendBuildTagsName");
                List<String> label = (List<String>) searchHit.getSourceAsMap().get("label");
//                List<String> districtHotList = (List<String>) searchHit.getSourceAsMap().get("districtHotSort");
                if(StringTool.isNotEmpty(recommendTags) && recommendTags.size() > 0){
                    tagsName.addAll(recommendTags);
                }
//                if(StringTool.isNotEmpty(districtHotList) && districtHotList.size() > 0){
//                    tagsName.addAll(districtHotList);
//                }
                if(StringTool.isNotEmpty(label) && label.size() > 0){
                    tagsName.addAll(label);
                }
                String tagName = StringUtils.join(tagsName, " ");
                plotsThemeDo.setTagsName(tagName.trim());
                if (plotsThemeDo.getPhoto().length > 0) {
                    String titlePhoto = plotsThemeDo.getPhoto()[0];
                    if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                        titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                    }
                    plotsThemeDo.setTitlePhoto(titlePhoto);
                }


                plotsThemeDos.add(plotsThemeDo);
            }
        }
        plotsThemeDomain.setData(plotsThemeDos);
        plotsThemeDomain.setTotalCount(hits.totalHits);
        return plotsThemeDomain;
    }
}
