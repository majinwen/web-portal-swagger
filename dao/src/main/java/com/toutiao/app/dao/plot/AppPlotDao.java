package com.toutiao.app.dao.plot;

import com.toutiao.web.domain.query.PlotRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public interface AppPlotDao {
    /**
     * 通过id查询详情
     * @param id
     * @return
     */
    SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 根据坐标和距离查询附近的小区
     * @param lat
     * @param lon
     * @return
     */
    SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort);

    /**
     * 小区条件查询
     * @param plotRequest
     * @return
     */
    Map queryPlotByCondition(PlotRequest plotRequest);
}
