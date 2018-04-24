package com.toutiao.app.service.favorite.impl;

import com.toutiao.app.service.favorite.FavoriteRestService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteRestServiceImpl implements FavoriteRestService {


    @Override
    public Integer newHouseFavoriteByNewCode(Integer newCode) {
        return 1;
    }
}
