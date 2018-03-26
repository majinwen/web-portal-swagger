package com.toutiao.app.service.rent.impl;

import com.toutiao.app.dao.rent.AppRentDao;
import com.toutiao.app.service.rent.AppRentService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppRentServiceImpl implements AppRentService {
    @Autowired
    private AppRentDao appRentDao;

    @Override
    public NashResult queryRentDetailByHouseId(RentRequest rentRequest) {
        try {
            Map rent = appRentDao.queryRentByRentId(rentRequest.getHouseId());
            Map agent = appRentDao.queryAgentByRentId(rentRequest.getHouseId());
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return NashResult.Fail("101","未找到该房源");
    }
}
