package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotsThemeEsDao;
import com.toutiao.app.domain.plot.PlotsThemeDo;
import com.toutiao.app.domain.plot.PlotsThemeDoQuery;
import com.toutiao.app.domain.plot.PlotsThemeDomain;
import com.toutiao.app.service.plot.PlotsThemeRestService;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 小区主题落地页服务层实现类
 */
@Service
public class PlotsThemeRestServiceImpl implements PlotsThemeRestService {
    @Autowired
    private PlotsThemeEsDao plotsThemeEsDao;

    /**
     * 获取小区主题数据
     */
    @Override
    public PlotsThemeDomain getPlotsThemeList(PlotsThemeDoQuery plotsThemeDoQuery) {
        List<PlotsThemeDo> plotsThemeDos = new ArrayList<>();
        //小区筛选条件
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        String nearestPark = plotsThemeDoQuery.getNearestPark();

        //主题标签
        Integer recommendBuildTagsId = plotsThemeDoQuery.getRecommendBuildTagsId();
        if (recommendBuildTagsId != null) {
            if (recommendBuildTagsId == 6 && StringTool.isNotEmpty(nearestPark)) {
                boolQueryBuilder.must(QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("recommendBuildTagsId", recommendBuildTagsId))
                        .must(QueryBuilders.termQuery("nearestPark", nearestPark)));
            } else {
                boolQueryBuilder.must(QueryBuilders.termQuery("recommendBuildTagsId", recommendBuildTagsId));
            }
        }

        //区域
        Integer[] districtIds = plotsThemeDoQuery.getDistrictIds();
        if (districtIds != null) {
            boolQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("areaId", districtIds)));
        }
        Integer pageNum = plotsThemeDoQuery.getPageNum();
        Integer pageSize = plotsThemeDoQuery.getPageSize();
        SearchResponse plotsThemeList = plotsThemeEsDao.getPlotsThemeList(boolQueryBuilder, pageNum, pageSize);

        SearchHits hits = plotsThemeList.getHits();
        SearchHit[] searchHists = hits.getHits();
        PlotsThemeDomain plotsThemeDomain = new PlotsThemeDomain();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                PlotsThemeDo plotsThemeDo = JSON.parseObject(details, PlotsThemeDo.class);
                plotsThemeDos.add(plotsThemeDo);
            }
        }
        plotsThemeDomain.setData(plotsThemeDos);
        plotsThemeDomain.setTotalCount(hits.totalHits);
        return plotsThemeDomain;
    }
}
