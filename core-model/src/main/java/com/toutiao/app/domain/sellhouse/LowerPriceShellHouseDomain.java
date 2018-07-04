package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class LowerPriceShellHouseDomain {
	/**
	 * 捡漏房数据
	 */
	private List<LowerPriceShellHouseDo> data;

	/**
	 * 订阅Id(-1代表没有订阅)
	 */
	private Integer subscriptionId;

	private long totalCount;
}
