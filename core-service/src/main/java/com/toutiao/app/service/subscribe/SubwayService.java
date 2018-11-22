package com.toutiao.app.service.subscribe;

import com.toutiao.web.dao.entity.subscribe.SubwayLineData;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
public interface SubwayService {
    List<SubwayLineData> getSubwayLineInfos(Integer cityId);
}
