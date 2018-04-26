package com.toutiao.app.service.newhouse.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.service.map.MapService;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.apache.commons.lang3.StringUtils;
import com.unboundid.util.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

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
     * @param newcode
     * @return
     */
    @Override
    public NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode) {
        NewHouseDetailDo newHouseDetailDo = new NewHouseDetailDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("building_name_id",newcode));
        SearchResponse bulidResponse =newHouseEsDao.getNewHouseBulid(boolQueryBuilder);
        SearchHit[] searchHists = bulidResponse.getHits().getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        if (StringUtils.isNotEmpty(details))
        {
            newHouseDetailDo = JSON.parseObject(details,NewHouseDetailDo.class);
        }
        return  newHouseDetailDo;

    }


    @Override
    public NewHouseListDomain getNewHouseList(NewHouseListDo newHouseListDo) {
        NewHouseListDomain newHouseListVo=new NewHouseListDomain();
        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        if(StringUtil.isNotNullString(newHouseListDo.getKeyword())){
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseListDo.getKeyword()))){
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseListDo.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("area_name", newHouseListDo.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("district_name", newHouseListDo.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name_accurate", newHouseListDo.getKeyword()).boost(2))
                        .add(QueryBuilders.matchQuery("building_name", newHouseListDo.getKeyword()).analyzer("ik_max_word"))
                        .add(QueryBuilders.matchQuery("area_name", newHouseListDo.getKeyword()))
                        .add(QueryBuilders.matchQuery("district_name", newHouseListDo.getKeyword())).tieBreaker(0.3f);
            }

            booleanQueryBuilder.must(queryBuilder);
            //    booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("building_name_accurate", newHouseQuery.getKeyword()))).boost(2);
        }

        //城市
        if(newHouseListDo.getCityId()!=null && newHouseListDo.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id",newHouseListDo.getCityId()));
            booleanQueryBuilder.must(termQuery("city_id", newHouseListDo.getCityId()));
        }
        //区域
        if(newHouseListDo.getDistrictId()!=null && newHouseListDo.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseListDo.getDistrictId()));
        }
        //商圈
        if(newHouseListDo.getAreaId()!=null && newHouseListDo.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", newHouseListDo.getAreaId()));
        }
        //地铁线id
        String keys = "";
        if(newHouseListDo.getSubwayLineId() !=null && newHouseListDo.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseListDo.getSubwayLineId()}));
            keys = newHouseListDo.getSubwayLineId().toString();
        }
        //地铁站id
        if(newHouseListDo.getSubwayStationId()!=null && newHouseListDo.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{newHouseListDo.getSubwayStationId()}));
            keys = keys+"$"+newHouseListDo.getSubwayStationId().toString();
        }
        //总价
        if(newHouseListDo.getMinPrice()!=null && newHouseListDo.getMaxPrice()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseListDo.getMinPrice()).lte(newHouseListDo.getMaxPrice())));
        }

        //标签
        if(null!=newHouseListDo.getLabelId() && newHouseListDo.getLabelId().length!=0){

            Integer[] longs = newHouseListDo.getLabelId();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }

        //户型
        if(newHouseListDo.getLayout()!=null && newHouseListDo.getLayout().length!=0 ){

            Integer[] longs =  newHouseListDo.getLayout();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }

        //面积
        if(newHouseListDo.getHouseMinArea()!=null && newHouseListDo.getHouseMaxArea()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseListDo.getHouseMinArea())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseListDo.getHouseMaxArea())));
        }


        //销售状态
        if(StringUtil.isNotNullString(newHouseListDo.getSaleStatusName())){
            booleanQueryBuilder.must(termQuery("sale_status_id", newHouseListDo.getSaleStatusName()));
        }else{
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        }
        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve",IS_APPROVE ));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));
        try{
            SearchResponse searchResponse=newHouseEsDao.getNewHouseList(booleanQueryBuilder,newHouseListDo.getPageNum(),newHouseListDo.getPageSize());
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                NewHouseListDo newHouseListDos=JSON.parseObject(details,NewHouseListDo.class);
                //获取新房下户型的数量
                NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseListDos.getBuildingNameId());
                if (null!=newHouseLayoutCountDomain.getTotalCount())
                {
                    newHouseListDos.setRoomTotalCount(newHouseLayoutCountDomain.getTotalCount());
                }
                else
                {
                    newHouseListDos.setRoomTotalCount(0);
                }
                //获取新房的收藏数量
                int newHouseFavoriteCount=favoriteRestService.newHouseFavoriteByNewCode(newHouseListDos.getBuildingNameId());
                newHouseListDos.setNewHouseFavorite(newHouseFavoriteCount);
                newHouseListDoList.add(newHouseListDos);
            }
            newHouseListVo.setListDoList(newHouseListDoList);
            newHouseListVo.setTotalCount(hits.getTotalHits());
        }catch (Exception e)
        {
            logger.error("获取新房列表信息异常信息={}",e.getStackTrace());
        }
        return newHouseListVo ;
    }

    @Override
    public List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDo newHouseDynamicDo) {
        List<NewHouseDynamicDo> newHouseDynamicDoList=new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode",newHouseDynamicDo.getNewCode()));
        try {
            SearchResponse  dynamicResponse =newHouseEsDao.getDynamicByNewCode(booleanQueryBuilder,newHouseDynamicDo.getPageNum(),newHouseDynamicDo.getPageSize());
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
            logger.error("获取新房动态信息异常信息"+newHouseDynamicDo.getNewCode().toString()+"={}",e.getStackTrace());
        }

        return  newHouseDynamicDoList;
    }

    @Override
    public NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode) {
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
        newHouseTrafficDo.setBusStation(businfo.get("name").toString());
        newHouseTrafficDo.setBusLines(Integer.valueOf(businfo.get("lines").toString()));
        //获取地铁和环线位置
        NewHouseDetailDo newHouseDetailDo=newHouseService.getNewHouseBulidByNewcode(newCode);
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


}
