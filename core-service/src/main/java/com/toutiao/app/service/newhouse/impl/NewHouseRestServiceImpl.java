package com.toutiao.app.service.newhouse.impl;
import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("building_name_id",newcode));
        SearchResponse bulidResponse =newHouseEsDao.getNewHouseBulid(boolQueryBuilder);
        SearchHit[] searchHists = bulidResponse.getHits().getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        if (details.isEmpty())
        {
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_NOT_FOUND,"未找到新房信息");
        }

        NewHouseDetailDo newHouseDetailDo = JSON.parseObject(details,NewHouseDetailDo.class);
        return  newHouseDetailDo;

    }


    @Override
    public List<NewHouseListDo> getNewHouseList(NewHouseListDo newHouseListDo) {
        List<NewHouseListDo> newHouseListDoList= new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        SearchResponse searchresponse = new SearchResponse();
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
            booleanQueryBuilder.must(termQuery("city_id", newHouseListDo.getDistrict_id()));
        }
        //区域
        if(newHouseListDo.getDistrict_id()!=null && newHouseListDo.getDistrict_id() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseListDo.getDistrict_id()));
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
        if(newHouseListDo.getMin_price()!=null && newHouseListDo.getMax_price()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseListDo.getMin_price()).lte(newHouseListDo.getMax_price())));
        }

        //户型
        if(null!=newHouseListDo.getLabelId() && newHouseListDo.getLabelId().length!=0){

            Integer[] longs = newHouseListDo.getLabelId();
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }


        //面积
        if(newHouseListDo.getHouse_min_area()!=null && newHouseListDo.getHosue_max_area()!=0)
        {
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseListDo.getHouse_min_area())));
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseListDo.getHosue_max_area())));
        }


        //销售状态
        if(StringUtil.isNotNullString(newHouseListDo.getSale_status_name())){
            booleanQueryBuilder.must(termQuery("sale_status_id", newHouseListDo.getSale_status_name()));
        }else{
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        }
        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve",IS_APPROVE ));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));

        SearchResponse searchResponse=newHouseEsDao.getNewHouseList(booleanQueryBuilder,newHouseListDo.getPageNum(),newHouseListDo.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details=searchHit.getSourceAsString();
            NewHouseListDo newHouseLayoutDo=JSON.parseObject(details,NewHouseListDo.class);
            //计算户型

            newHouseListDoList.add(newHouseLayoutDo);
        }

        return newHouseListDoList;

    }


}
