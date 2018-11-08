package com.toutiao.app.service.advertisement;

import com.toutiao.app.domain.newhouse.NewHouseListDomain;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   15:22
 * Theme:
 */
public interface AdNewHouseRestService {


    /**
     * 获取新房五条
     */
    NewHouseListDomain getAdNewHouseHomePage(Integer[] newHouseIds, String city);

    /**
     *
     * @param newHouseIds
     * @param city
     * @return
     */
    NewHouseListDomain getAdNewHouseList(Integer[] newHouseIds, String city);

}
