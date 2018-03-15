package com.toutiao.web.service.rent.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * 业务逻辑层
 *
 */

@Service
public class RentHouseServiceImpl implements RentHouseService{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    @Value("${tt.zufang.agent.index}")
    private String agentIndex;
    @Value("${tt.zufang.agent.type}")
    private String agentType;
    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 1;//出租:1
    private static final Integer APARTMENT = 2;//公寓:2


    /**
     * 根据距离范围查询并按距离排序
     * @param rentHouseQuery
     * @return
     */
    @Override
    public Map queryNearHouseByDistance(RentHouseQuery rentHouseQuery) {
        List list = new ArrayList();
        Map result = new HashMap();
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //从该坐标查询距离为distance的点
            GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(rentHouseQuery.getLat(), rentHouseQuery.getLon()).distance(rentHouseQuery.getNear(), DistanceUnit.KILOMETERS);
            srb.setPostFilter(location1).setSize(6);
            // 按距离排序
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", rentHouseQuery.getLat(), rentHouseQuery.getLon());
            sort.unit(DistanceUnit.KILOMETERS);
            sort.order(SortOrder.ASC);
            srb.addSort(sort);
            //小区/公寓
            if (rentHouseQuery.getRentSign()==1){
                boolQueryBuilder.must(QueryBuilders.termQuery("rent_sign",RENT));
            }
            //是否删除
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del", IS_DEL));
            //发布状态
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status", RELEASE_STATUS));
            //价格上下浮动20%
            if (rentHouseQuery.getBeginPrice()>0&&rentHouseQuery.getEndPrice()>0&&rentHouseQuery.getEndPrice()>rentHouseQuery.getBeginPrice()){
                boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentHouseQuery.getBeginPrice()).lte(rentHouseQuery.getEndPrice()));
            }
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] searchHists = searchResponse.getHits().getHits();
            if(searchHists.length>0){
                for (SearchHit hit:searchHists){
                    Map source = hit.getSource();
                    if((source.get("house_id"))!=Integer.valueOf(rentHouseQuery.getHouseId())){
                        list.add(source);
                    }
                }
                result.put("nearHouse",list);
                result.put("total",searchResponse.getHits().getTotalHits());
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据houseId查询详情
     * @param rentHouseQuery
     * @return
     */
    @Override
    public Map queryHouseById(RentHouseQuery rentHouseQuery) {
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //出租房源ID
            if(StringUtils.isNotBlank(rentHouseQuery.getHouseId())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("house_id",rentHouseQuery.getHouseId()));
            }
            //是否删除
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del",IS_DEL));
            //发布状态
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status",RELEASE_STATUS));
            SearchResponse response = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] searchHists = response.getHits().getHits();
            if(searchHists.length>0){
                Map source = searchHists[0].getSource();
                return source;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询该公寓所在小区下的公寓
     * @param rentHouseQuery
     * @return
     */
    @Override
    public Map queryHouseByparentId(RentHouseQuery rentHouseQuery) {
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //上级公寓ID
            if (StringUtils.isNotBlank(rentHouseQuery.getApartmentParentId())){
                boolQueryBuilder.must(QueryBuilders.termsQuery("apartment_parent_id",rentHouseQuery.getApartmentParentId()));
            }
            //按价格由低到高排序
            srb.addSort("rent_house_price",SortOrder.ASC);
            //是否删除
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del",IS_DEL));
            //发布状态
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status",RELEASE_STATUS));
            //小区/公寓
            boolQueryBuilder.must(QueryBuilders.termQuery("rent_sign",APARTMENT));
            srb.setSize(4);
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map source = hit.getSource();
                    if((source.get("house_id"))!=Integer.valueOf(rentHouseQuery.getHouseId())){
                        list.add(source);
                    }
                }
                result.put("total",searchResponse.getHits().getTotalHits());
                result.put("nearHouse",list);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据房源的id查询该房源所有的经纪人
     * @param houseId
     * @return
     */
    @Override
    public Map queryAgentByHouseId(String houseId) {
        Map result = new HashMap();
        List list = new ArrayList();
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(agentIndex).setTypes(agentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map source = hit.getSource();
                    list.add(source);
                }
                result.put("agent",list);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public String queryHouseNumByparentId(Integer parentId) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //上级公寓ID
//        if (parentId>0){
//            boolQueryBuilder.must(QueryBuilders.termQuery("apartment_parent_id",parentId));
//        }
//        //是否删除
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
//        //发布状态
//        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
//        String totalHits = String.valueOf(searchResponse.getHits().getTotalHits());
//        return totalHits;
//    }






    /**
     * 据筛选条件查询(普租、公寓)
     * @param rentHouseQuery
     * @return
     */
    @Override
    public Map<String, Object> getRentHouseList(RentHouseQuery rentHouseQuery) {
        //建立连接
        TransportClient client = esClientTools.init();
        //查询置顶数据
        String keys = "";
        if(rentHouseQuery.getSubwayLineId() !=null && rentHouseQuery.getSubwayLineId()!=0){
            keys = rentHouseQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if(rentHouseQuery.getSubwayStationId()!=null && rentHouseQuery.getSubwayStationId()!=0){
            keys = keys+"$"+rentHouseQuery.getSubwayStationId().toString();
        }
        Map<String, Object> result = new HashMap<>();
        SearchResponse searchTopRentResponse= getTopRentHouseList(rentHouseQuery,client);
        long top_size = 0;
        Map<String,Object> zufangMap = new HashMap<>();
        if(searchTopRentResponse != null){
            top_size = searchTopRentResponse.getHits().getTotalHits();
            int topInfo = searchTopRentResponse.getHits().getHits().length;
            if(topInfo == 10){
                zufangMap = computeZufangList(searchTopRentResponse,rentHouseQuery,keys);
            }else if(topInfo < 10 && topInfo > 0){
                List zufangList = new ArrayList();
                Map<String,Object> topTypeMaps = computeZufangList(searchTopRentResponse,rentHouseQuery,keys);
                Map<String,Object> maps = computeZufangList_1(searchTopRentResponse, rentHouseQuery, client, keys);
                Integer topTypeCount = Integer.valueOf(topTypeMaps.get("dataCount").toString());
                zufangList.add(topTypeMaps.get("data"));
                zufangList.add(maps.get("data"));
                Integer count = Integer.valueOf(maps.get("dataCount").toString());
                zufangMap.put("data",zufangList);
                zufangMap.put("dataCount",topTypeCount+count);
            }else if(topInfo == 0){
                zufangMap = computeZufangList_2(rentHouseQuery, client, keys,top_size);
            }
        }
        result.put("data",zufangMap.get("data"));
        result.put("pageNum",rentHouseQuery.getPageNum());
        ArrayList<Object> strings = new ArrayList<>((Collection<?>) zufangMap.get("data"));
        result.put("total", strings.size());
        result.put("dataCount",zufangMap.get("dataCount"));
        return result;
    }


    /**
     * 整合置顶和普通房源集合方法 1
     * size == 10
     * @param searchTopRentResponse
     * @return
     */
    public Map<String,Object> computeZufangList(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery, String keys){

        SearchHits hits = searchTopRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        Map<String,Object> result = new HashMap<>();
        result.put("dataCount",hits.totalHits);
        List<Map<String,Object>> zufanglist = getZufangList(searchHists,keys);
        result.put("data",zufanglist);
        return result;
    }

    /**
     * 整合置顶和普通房源集合方法 2
     * 10<size<0
     * @param searchTopRentResponse
     * @param rentHouseQuery
     * @param client
     * @param keys
     * @return
     */
    public Map<String,Object> computeZufangList_1(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery,
                                    TransportClient client, String keys){

        SearchResponse searchFreeRentResponse= getFreeRentHouseList(searchTopRentResponse, rentHouseQuery,
                client);
        SearchHits hits = searchFreeRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        Map<String,Object> result = new HashMap<>();
        result.put("dataCount",hits.totalHits);
        List<Map<String,Object>> zufanglist = getZufangList(searchHists,keys);
        result.put("data",zufanglist);
        return result;
    }

    /**
     * 整合置顶和普通房源集合方法 3
     * size==0
     * @param rentHouseQuery
     * @param client
     * @param keys
     * @param top_size
     * @return
     */
    public Map<String,Object> computeZufangList_2(RentHouseQuery rentHouseQuery,
                                    TransportClient client, String keys, long top_size){

        SearchResponse searchFreeRentResponse= getFreeRentHouseList_1(rentHouseQuery,
                client,top_size);
        SearchHits hits = searchFreeRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
//        List<Map<String,Object>> zufanglist = new ArrayList<>();
        Map<String,Object> result = new HashMap<>();
        result.put("dataCount",hits.totalHits);
        List<Map<String,Object>> zufanglist = getZufangList(searchHists,keys);
        result.put("data",zufanglist);
        return result;
    }

    public List<Map<String,Object>> getZufangList(SearchHit[] searchHists, String keys){

        List<Map<String,Object>> zufanglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,String> nearbysubway = (Map<String, String>) hit.getSource().get("nearby_subway");
            String nearbysubwayKey = nearbysubway.get(keys);
            Map<String,Object> zufangMap = hit.getSourceAsMap();
            zufangMap.put("nearsubway",nearbysubwayKey);
            zufanglist.add(zufangMap);
        }
        return zufanglist;
    }



    /**
     * 普通房源过滤条件 and 返回结果集合(10<topInfo<0)
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getFreeRentHouseList(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery,
                                               TransportClient client){

        int pageNum = 1;
        int pageSize = 10;
        int count = searchTopRentResponse.getHits().getHits().length;
        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }
        BoolQueryBuilder booleanQueryBuilder = boolQuery();


        Map<String,Object> filter = getFilterCondition(rentHouseQuery);
        booleanQueryBuilder = (BoolQueryBuilder) filter.get("booleanQueryBuilder");
        booleanQueryBuilder.mustNot(QueryBuilders.termQuery("is_top", 1));
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("zufang_score",SortOrder.DESC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price","rent_sign","rent_type","rent_type_name"},
                        null)
                .setFrom(((0) * pageSize)*rentHouseQuery.getPageSize())
                .setSize(pageSize-count)
                .execute().actionGet();

        return searchresponse;
    }


    /**
     * 普通房源过滤条件 and 返回结果集合(topInfo==0)
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getFreeRentHouseList_1(RentHouseQuery rentHouseQuery, TransportClient client, long top_size){

        int pageNum = 1;
        int pageSize = 10;
        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }

        long zufang_from = (pageNum-1)*pageSize - top_size;
        BoolQueryBuilder booleanQueryBuilder = boolQuery();


        Map<String,Object> filter = getFilterCondition(rentHouseQuery);
        booleanQueryBuilder = (BoolQueryBuilder) filter.get("booleanQueryBuilder");
        booleanQueryBuilder.mustNot(QueryBuilders.termQuery("is_top", 1));
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("zufang_score",SortOrder.DESC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price","rent_sign","rent_type","rent_type_name"},
                        null)
                .setFrom(Integer.valueOf((int) zufang_from))
                .setSize(pageSize)
                .execute().actionGet();

        return searchresponse;
    }


    /**
     * 置顶房源过滤条件 and 返回结果集合
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getTopRentHouseList(RentHouseQuery rentHouseQuery,TransportClient client){

        int pageNum = 1;

        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }

        BoolQueryBuilder booleanQueryBuilder=QueryBuilders.boolQuery();;

        //置顶数据只根据区域、商圈确定
        List<String> topKeywords = new ArrayList<>();
        if (StringTool.isNotBlank(rentHouseQuery.getDistrictId())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistrict(rentHouseQuery.getDistrictId().toString()))) {
                topKeywords.add(DistrictMap.getDistrict(rentHouseQuery.getDistrictId().toString()));
            }
        }
        if (StringTool.isNotBlank(rentHouseQuery.getAreaId())) {
            if (StringUtil.isNotNullString(AreaMap.getArea(rentHouseQuery.getAreaId().toString()))) {
                topKeywords.add(AreaMap.getArea(rentHouseQuery.getAreaId().toString()));
            }
        }
        if(StringTool.isNotBlank(rentHouseQuery.getKeyword())){
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseQuery.getKeyword()))) {
                topKeywords.add(rentHouseQuery.getKeyword());
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseQuery.getKeyword()))){
                topKeywords.add(rentHouseQuery.getKeyword());
            }
        }
        //匹配置顶关键字（区域、商圈）查询
        if(null!=topKeywords && !topKeywords.isEmpty()){
            String topkey = String.join(",", topKeywords);
            String[] key = topkey.split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("top_keyword", key));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_top", 1));

        //获取公共筛选条件
        Map<String,Object> filter = getFilterCondition(rentHouseQuery);

        booleanQueryBuilder = booleanQueryBuilder.must((BoolQueryBuilder) filter.get("booleanQueryBuilder"));
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("top_time",SortOrder.ASC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price","rent_sign","rent_type","rent_type_name"},
                        null)
                .setFrom((pageNum-1)*rentHouseQuery.getPageSize())
                .setSize(rentHouseQuery.getPageSize())
                .execute().actionGet();



        return searchresponse;
    }

    /**
     * 公共过滤条件
     * @param rentHouseQuery
     * @return
     */
    public Map<String,Object> getFilterCondition(RentHouseQuery rentHouseQuery){


        Map<String,Object> result = new HashMap<>();
        //声明过滤筛选条件查询方法
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        //关键字
        if (StringTool.isNotBlank(rentHouseQuery.getKeyword())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("village_name", rentHouseQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseQuery.getKeyword()).analyzer("ik_smart"))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("village_name_search", rentHouseQuery.getKeyword()).analyzer("ik_smart")));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseQuery.getKeyword()))) {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("village_name", rentHouseQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseQuery.getKeyword()).analyzer("ik_smart").boost(2))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseQuery.getKeyword()).analyzer("ik_max_word"))
                        .should(QueryBuilders.matchQuery("village_name_search", rentHouseQuery.getKeyword()).analyzer("ik_smart").boost(2)));
            } else {
                booleanQueryBuilder.must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("village_name", rentHouseQuery.getKeyword()).boost(2))
                        .should(QueryBuilders.matchQuery("area_name_search", rentHouseQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("district_name_search", rentHouseQuery.getKeyword()))
                        .should(QueryBuilders.matchQuery("village_name_search", rentHouseQuery.getKeyword())));
            }
        }

        //城市
        if(rentHouseQuery.getCityId()!=null && rentHouseQuery.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id", rentHouseQuery.getDistrictId()));
        }
        //区域
        if(rentHouseQuery.getDistrictId()!=null && rentHouseQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",rentHouseQuery.getDistrictId()));
        }
        //商圈
        if(rentHouseQuery.getAreaId()!=null && rentHouseQuery.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", rentHouseQuery.getAreaId()));
        }
        //地铁线id
        if(rentHouseQuery.getSubwayLineId() !=null && rentHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseQuery.getSubwayLineId()}));
        }
        //地铁站id
        if(rentHouseQuery.getSubwayStationId()!=null && rentHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{rentHouseQuery.getSubwayStationId()}));
        }
        //附近1,3,5km
        GeoDistanceQueryBuilder location = null;
        GeoDistanceSortBuilder geoDistanceSort = null;
        if(StringUtil.isNotNullString(rentHouseQuery.getNear())){
            location = QueryBuilders.geoDistanceQuery("location")
                    .point(rentHouseQuery.getLat(), rentHouseQuery.getLon()).distance(rentHouseQuery.getNear()
                            , DistanceUnit.KILOMETERS);

            geoDistanceSort = SortBuilders.geoDistanceSort("location", rentHouseQuery.getLat(),
                    rentHouseQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.order(SortOrder.ASC);
            geoDistanceSort.point(rentHouseQuery.getLat(), rentHouseQuery.getLon());
        }

        //总价
        if(rentHouseQuery.getBeginPrice()!=null && rentHouseQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentHouseQuery.getBeginPrice()).lte(rentHouseQuery.getEndPrice()));
        }
        //租赁方式
        if(rentHouseQuery.getRt()!=null && rentHouseQuery.getRt()!=0){
            booleanQueryBuilder.must(termQuery("rent_type", rentHouseQuery.getRt()));
        }
        //户型
        if(StringUtil.isNotNullString(rentHouseQuery.getLo())){
            String[] room = rentHouseQuery.getLo().split(",");
//            int[] ints= Arrays.stream(layoutId).mapToInt(Integer::valueOf).toArray();
            booleanQueryBuilder.must(termsQuery("room", room));
        }
        //来源
        if(StringUtil.isNotNullString(rentHouseQuery.getSource())){
            String[] source = rentHouseQuery.getSource().split(",");
            booleanQueryBuilder.must(termQuery("data_source_sign", source));
        }
        //面积
        if (StringUtil.isNotNullString(rentHouseQuery.getHouseAreaSize())) {
            String area = rentHouseQuery.getHouseAreaSize().replaceAll("\\[","")
                    .replaceAll("]","").replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //朝向
        if (StringUtil.isNotNullString(rentHouseQuery.getForward())) {
            String[] layoutId = rentHouseQuery.getForward().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("forward_type", layoutId));
        }

        //暖气
        if (StringTool.isNotEmpty(rentHouseQuery.getHt())) {
            String[] ht = rentHouseQuery.getHt().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("heating_type", ht));
        }
        //特色
        if (StringTool.isNotEmpty(rentHouseQuery.getTags())) {
            String[] tag = rentHouseQuery.getTags().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", tag));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_del", IS_DEL));
        booleanQueryBuilder.must(QueryBuilders.termQuery("release_status", RELEASE_STATUS));
        result.put("booleanQueryBuilder",booleanQueryBuilder);
        result.put("location",location);
        result.put("geoDistanceSort",geoDistanceSort);

        return result;
    }

}
