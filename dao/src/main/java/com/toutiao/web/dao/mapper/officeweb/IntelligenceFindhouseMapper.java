package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;

import java.util.List;

/**
 *  
 * 功能描述：智能找房接口
 * @author
 * @date 2017/12/25 19:09
 * @param 
 * @return 
 */
public interface IntelligenceFindhouseMapper extends BaseDao {
    int insert(IntelligenceFindhouse record);

    int insertSelective(IntelligenceFindhouse record);

    List<IntelligenceFindhouse> selectByUserPrice(double totlaPrice);
}