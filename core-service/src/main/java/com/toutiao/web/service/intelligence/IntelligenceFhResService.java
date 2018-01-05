package com.toutiao.web.service.intelligence;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;

import java.util.List;

public interface IntelligenceFhResService {

    List<IntelligenceFhRes> queryUserReport(String usePhone);

    IntelligenceFhRes queryResById(Integer id);


    Integer deleteMyReport(String reportId,String phone);

    int updateMyReportCollectStatus(String reportId, String usePhone);
}
