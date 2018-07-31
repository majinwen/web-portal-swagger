package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.service.favorite.SellHouseFavoriteRestService;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellHouseFavoriteRestServiceImpl implements SellHouseFavoriteRestService {


    @Autowired
    private UserFavoriteEsHouseMapper userFavoriteEsHouseMapper;
    /**
     * 二手房房源列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    @Override
    public SellHouseFavoriteDomain queryNewHouseFavoriteListByUserId(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery, String cityId) {

        SellHouseFavoriteDomain sellHouseFavoriteDomain = new SellHouseFavoriteDomain();
        sellHouseFavoriteListDoQuery.setFrom((sellHouseFavoriteListDoQuery.getPageNum()-1)*sellHouseFavoriteListDoQuery.getSize());
        List<SellHouseFavoriteDo> sellHouseFavoriteDos = userFavoriteEsHouseMapper.selectSellHouseFavoriteByUserId(sellHouseFavoriteListDoQuery);

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
