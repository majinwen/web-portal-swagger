package com.toutiao.web.service.intelligence.impl;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceFhResServiceImpl implements IntelligenceFhResService {

    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;

    /**
     * 功能描述：根据用户的手机号码获取用户报表相关数据信息
     *
     * @param usePhone
     * @return com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes
     * @author zhw
     * @date 2017/12/26 15:45
     */
    @Override
    public List<IntelligenceFhRes> queryUserReport(String usePhone) {

        List<IntelligenceFhRes> intelligenceFhRes = intelligenceFhResMapper.selectByUserPhone(usePhone);

        return intelligenceFhRes;
    }

    /**
     *  
     * 功能描述：根据id查询生成的报告结果
     * @author zengqingzhou
     * @date 2018/1/3 16:02
     * @param
     * @return
     */
    @Override
    public IntelligenceFhRes queryResById(Integer id) {
        IntelligenceFhRes intelligenceFhRes = intelligenceFhResMapper.selectByPrimaryKey(id);
        if (null!=intelligenceFhRes){
            return intelligenceFhRes;
        }
        return null;
    }

    @Override
    public Integer deleteMyReport(String reportId,String phone) {

       int result= intelligenceFhResMapper.deleteMyReport(Integer.valueOf(reportId),phone);
        return result;
    }

}

