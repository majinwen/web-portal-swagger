package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDo;
import lombok.Data;

import java.util.List;

@Data
public class LowerPriceShellHouseResponse {
	/**
	 * 捡漏房数据
	 */
	private List<LowerPriceShellHouseDo> data;

	/**
	 * 是否被订阅
	 */
	private boolean subscription;
}
