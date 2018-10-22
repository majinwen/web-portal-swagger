package com.toutiao.app.service.homepage;

import com.toutiao.app.domain.homepage.HomePageEsfCountDo;
import com.toutiao.app.domain.homepage.HomePageNewCountDo;

/**
 * Created by CuiShihao on 2018/10/19
 */
public interface HomePageCountService {

    //获取新房相应数量
    HomePageNewCountDo getNewCount(String city);

    HomePageEsfCountDo getEsfCount(String city);
}
