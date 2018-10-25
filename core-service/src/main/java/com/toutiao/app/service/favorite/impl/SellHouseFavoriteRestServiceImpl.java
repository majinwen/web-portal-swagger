package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.app.service.favorite.SellHouseFavoriteRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellHouseFavoriteRestServiceImpl implements SellHouseFavoriteRestService {


    @Autowired
    private UserFavoriteEsHouseMapper userFavoriteEsHouseMapper;
    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private SellHouseService sellHouseService;
    /**
     * 二手房房源列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    @Override
    public SellHouseFavoriteDomain queryNewHouseFavoriteListByUserId(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery) {
        Date date = new Date();
        SellHouseFavoriteDomain sellHouseFavoriteDomain = new SellHouseFavoriteDomain();
        sellHouseFavoriteListDoQuery.setFrom((sellHouseFavoriteListDoQuery.getPageNum()-1)*sellHouseFavoriteListDoQuery.getSize());
        List<SellHouseFavoriteDo> sellHouseFavoriteDos = userFavoriteEsHouseMapper.selectSellHouseFavoriteByUserId(sellHouseFavoriteListDoQuery);

        for(int i=0; i<sellHouseFavoriteDos.size(); i++){

            BoolQueryBuilder booleanQueryBuilder = new BoolQueryBuilder();

            booleanQueryBuilder.must(QueryBuilders.termQuery("_id", sellHouseFavoriteDos.get(i).getHouseId()));
            SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder, sellHouseFavoriteDos.get(i).getCity());

            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            if (searchHists.length>0){

                for (SearchHit searchHit : searchHists) {

                    //判断3天内导入，且无图片，默认上显示默认图
                    String importTime = "";
                    if (StringTool.isNotEmpty(searchHit.getSource().get("import_time"))){
                        importTime = searchHit.getSource().get("import_time").toString();
                    }
                    String housePhotoTitle = "";
                    if (StringTool.isNotEmpty(searchHit.getSource().get("housePhotoTitle"))){
                        housePhotoTitle = searchHit.getSource().get("housePhotoTitle").toString();
                    }

                    int isDefault = sellHouseService.isDefaultImage(importTime ,date, housePhotoTitle);
                    if(isDefault==1){
                        sellHouseFavoriteDos.get(i).setIsDefaultImage(1);
                    }
                    sellHouseFavoriteDos.get(i).setHousePhotoTitle(housePhotoTitle);
                }
            }
        }

        if(null!=sellHouseFavoriteDos && sellHouseFavoriteDos.size()>0){
            sellHouseFavoriteDomain.setData(sellHouseFavoriteDos);
            int esfFavourite = userFavoriteEsHouseMapper.selectEsHouseFavoriteByUserId(sellHouseFavoriteListDoQuery.getUserId());
            sellHouseFavoriteDomain.setTotalNum(Long.valueOf(esfFavourite));
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_NOT_FOUND,"二手房收藏列表为空");
        }
        return sellHouseFavoriteDomain;
    }
}
