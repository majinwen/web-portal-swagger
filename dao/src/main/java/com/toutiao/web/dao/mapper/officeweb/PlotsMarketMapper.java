package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.app.domain.plot.PlotMarketDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wk on 2018/12/12.
 */
@Repository
public interface PlotsMarketMapper {

    PlotMarketDo queryPlotMarketByPlotId(@Param(value = "plotId") Integer plotId);

}
