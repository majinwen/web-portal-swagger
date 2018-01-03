package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.domain.intelligenceFh.DistictInfo;
import com.toutiao.web.domain.query.IntelligenceQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    int queryPlotCount(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd);

    int queryPlotCountByCategoryAndPrice(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd,@Param("categoryId") Integer categoryId);
    List<DistictInfo> queryPlotCountByCategoryAndPrice1(@Param("plotTotalFirst")Double plotTotalFirst, @Param("plotTotalEnd")Double plotTotalEnd, @Param("categoryId") Integer categoryId);

    int queryPlotCountByCategoryAndPriceAndDistict(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd,
                                                   @Param("layOut")Integer layOut, @Param("distictId")Integer distictId);

    List<IntelligenceFindhouse> selectByUserPrice(String totlaPrice);

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

    /**
     * 根据用户画像2 A匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType2A(IntelligenceQuery intelligenceQuery);

    /**
     * 根据用户画像2 B匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType2B(IntelligenceQuery intelligenceQuery);

    /**
     * 根据用户画像2 C匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType2C(IntelligenceQuery intelligenceQuery);

    /**
     * 根据用户画像3 A匹配
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByUserType3A(IntelligenceQuery intelligenceQuery);

    /**
     * 搜索量前200
     * @param intelligenceQuery
     * @return
     */
    List<IntelligenceFindhouse> queryByStarProperty(IntelligenceQuery intelligenceQuery);
}