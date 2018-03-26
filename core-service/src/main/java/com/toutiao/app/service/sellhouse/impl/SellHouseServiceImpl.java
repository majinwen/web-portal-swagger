package com.toutiao.app.service.sellhouse.impl;


import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.service.sellhouse.SellHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;

    //业务接口实现类

    @Override
    public int queryNearByProjHouseInfo(Integer houseId) {

        int ss = sellHouseEsDao.queryNearByProjHouseInfo(houseId);

        return ss;
    }


}
