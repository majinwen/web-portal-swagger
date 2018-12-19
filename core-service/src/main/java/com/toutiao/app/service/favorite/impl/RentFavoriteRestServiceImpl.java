package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.service.favorite.RentFavoriteRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteRentMapper;
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
public class RentFavoriteRestServiceImpl implements RentFavoriteRestService {

    @Autowired
    private UserFavoriteRentMapper userFavoriteRentMapper;
    @Autowired
    private RentEsDao rentEsDao;
    @Autowired
    private RentRestService rentRestService;


    @Override
    public RentFavoriteDomain queryRentFavoriteListByUserId(RentFavoriteListDoQuery rentFavoriteListDoQuery) {
        RentFavoriteDomain rentFavoriteDomain = new RentFavoriteDomain();
        Date date = new Date();
        rentFavoriteListDoQuery.setFrom((rentFavoriteListDoQuery.getPageNum()-1)*rentFavoriteListDoQuery.getSize());

        List<RentFavoriteDo> rentFavoriteDos = userFavoriteRentMapper.selectRentFavoritesByUserId(rentFavoriteListDoQuery);

        for(int i=0; i< rentFavoriteDos.size(); i++){
            BoolQueryBuilder booleanQueryBuilder = new BoolQueryBuilder();

            booleanQueryBuilder.must(QueryBuilders.termQuery("_id", rentFavoriteDos.get(i).getHouseId()));
            SearchResponse searchResponse = rentEsDao.queryRentByRentId(booleanQueryBuilder, rentFavoriteDos.get(i).getCity());
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();

            if (searchHists.length>0){

                for (SearchHit searchHit : searchHists) {

                    //判断3天内导入，且无图片，默认上显示默认图
                    String importTime = "";
                    if (StringTool.isNotEmpty(searchHit.getSourceAsMap().get("create_time"))){
                        importTime = searchHit.getSourceAsMap().get("create_time").toString();
                    }
                    String housePhotoTitle = "";
                    if (StringTool.isNotEmpty(searchHit.getSourceAsMap().get("house_title_img"))){
                        housePhotoTitle = searchHit.getSourceAsMap().get("house_title_img").toString();
                    }

                    int isDefault = rentRestService.isDefaultImage(importTime ,date, housePhotoTitle);
                    if(isDefault==1){
                        rentFavoriteDos.get(i).setIsDefaultImage(1);
                    }
                    rentFavoriteDos.get(i).setHousePhotoTitle(housePhotoTitle);
                }
            }
        }


        if(null!=rentFavoriteDos && rentFavoriteDos.size()>0){
            rentFavoriteDomain.setData(rentFavoriteDos);
            int rentFavourite = userFavoriteRentMapper.selectRentFavoriteByUserId(rentFavoriteListDoQuery.getUserId());
            rentFavoriteDomain.setTotalNum(Long.valueOf(rentFavourite));

        }else{
            throw new BaseException(RentInterfaceErrorCodeEnum.RENT_FAVORITE_NOT_FOUND,"租房收藏列表为空");
        }
        return rentFavoriteDomain;
    }
}
