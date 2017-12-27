package com.toutiao.web.service.intelligence.impl;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFindhouseMapper;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntelligenceFhResServiceImpl implements IntelligenceFhResService {

    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;

    /**
     * 功能描述：根据用户的手机号码获取用户报表相关数据信息
     *
     * @param [usePhone]
     * @return com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes
     * @author zhw
     * @date 2017/12/26 15:45
     */
    @Override
    public IntelligenceFhRes queryUserReport(String usePhone) {

        IntelligenceFhRes intelligenceFhRes = intelligenceFhResMapper.selectByUserPhone(usePhone);

        return intelligenceFhRes;
    }

}

