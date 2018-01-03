package com.toutiao.web.service.intelligence;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;

import java.util.List;

public interface IntelligenceFhResService {

    IntelligenceFhRes queryUserReport(String usePhone);

    List<IntelligenceFhRes> queryResById(Integer id);

}
