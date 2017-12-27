package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.domain.query.IntelligenceQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *
 * 功能描述：智能找房接口
 * @author
 * @date 2017/12/25 19:09
 * @param
 * @return
 */
@Repository
public interface IntelligenceFindhouseMapper extends BaseDao {
    int insert(IntelligenceFindhouse record);

    int insertSelective(IntelligenceFindhouse record);

    int queryPlotCount(Double plotTotal);

    int queryPlotCountByCategoryAndPrice(@PathVariable("plotTotal") Double plotTotal,@PathVariable("categoryId") Integer categoryId);

    int queryPlotCountByCategoryAndPriceAndDistict(@PathVariable("plotTotal") Double plotTotal, @PathVariable("categoryId")Integer categoryId,@PathVariable("distinctId")String distinctId);

    List<IntelligenceFindhouse> selectByUserPrice(double totlaPrice);

    List<IntelligenceFindhouse> selectByTypeTwoA(IntelligenceQuery intelligenceQuery);

    List<IntelligenceFindhouse> selectByTypeTwoB(IntelligenceQuery intelligenceQuery);

    List<IntelligenceFindhouse> selectByTypeTwoC(IntelligenceQuery intelligenceQuery);

    /**
     * 根据用户画像1 A匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType1A(IntelligenceQuery intelligenceQuery);
    /**
     * 根据用户画像1 B匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType1B(IntelligenceQuery intelligenceQuery);
    /**
     * 根据用户画像1 C匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType1C(IntelligenceQuery intelligenceQuery);
}