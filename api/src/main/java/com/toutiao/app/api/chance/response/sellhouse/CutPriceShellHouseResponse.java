package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDo;
import lombok.Data;

import java.util.List;

@Data
public class CutPriceShellHouseResponse {
	/**
	 * 降价房数据
	 */
	private List<CutPriceShellHouseDo> data;

	/**
	 * 是否被订阅
	 */
	private Integer subscribeId;

	private long totalCount;
}
