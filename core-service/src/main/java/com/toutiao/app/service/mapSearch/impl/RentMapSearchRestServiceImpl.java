package com.toutiao.app.service.mapSearch.impl;

import com.toutiao.app.domain.mapSearch.RentMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.RentMapSearchDomain;
import com.toutiao.app.service.mapSearch.RentMapSearchRestService;
import org.springframework.stereotype.Service;

@Service
public class RentMapSearchRestServiceImpl implements RentMapSearchRestService {
    @Override
    public RentMapSearchDomain rentMapSearch(RentMapSearchDoQuery rentMapSearchDoQuery, String city) {
        RentMapSearchDomain rentMapSearchDomain = new RentMapSearchDomain();
        return rentMapSearchDomain;
    }
}
