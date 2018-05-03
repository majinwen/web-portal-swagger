package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.service.favorite.RentFavoriteRestService;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteRentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentFavoriteRestServiceImpl implements RentFavoriteRestService {

    @Autowired
    private UserFavoriteRentMapper userFavoriteRentMapper;


    @Override
    public RentFavoriteDomain queryRentFavoriteListByUserId(RentFavoriteListDoQuery rentFavoriteListDoQuery) {
        RentFavoriteDomain rentFavoriteDomain = new RentFavoriteDomain();

        rentFavoriteListDoQuery.setFrom((rentFavoriteListDoQuery.getPageNum()-1)*rentFavoriteListDoQuery.getSize());

        List<RentFavoriteDo> rentFavoriteDos = userFavoriteRentMapper.selectRentFavoritesByUserId(rentFavoriteListDoQuery);

        rentFavoriteDomain.setData(rentFavoriteDos);

        return rentFavoriteDomain;
    }
}
