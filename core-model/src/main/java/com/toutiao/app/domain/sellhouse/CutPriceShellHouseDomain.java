package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class CutPriceShellHouseDomain {
	/**
	 * 降价房数据
	 */
	private List<CutPriceShellHouseDo> data;

	/**
	 * 是否被订阅
	 */
	private boolean subscription;
}
