package com.toutiao.web.service.advertisement;

import com.toutiao.web.domain.advertisement.AggAdLandingDo;

import java.util.Map;

public interface AggAdLandingService {


    Map<String,Object> getAdLanding(AggAdLandingDo aggAdLandingDo);

}
