package com.toutiao.app.dao.sellhouse;


import org.springframework.context.annotation.Configuration;

@Configuration
public interface SellHouseEsDao {


    int queryNearByProjHouseInfo(Integer houseId);


}
