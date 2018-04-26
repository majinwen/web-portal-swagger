package com.toutiao.app.service.homepage.impl;

import com.toutiao.app.domain.homepage.HomePageEsfDo;
import com.toutiao.app.service.homepage.HomePageRestService;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageRestService {


    @Override
    public List<HomePageEsfDo> getHomePageEsf(Integer cityId) {

    }
}
