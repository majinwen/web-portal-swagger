package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class LowerPriceShellHouseDoQuery {
	/**
	 * 商圈Id
	 */
	private Integer areaId;

	/**
	 * 最低价
	 */
	private Integer lowestTotalPrice;

	/**
	 * 最高价
	 */
	private Integer highestTotalPrice;

	/**
	 * 排序方式(0-更新时间降序, 1-总价升, 2-总价降)
	 */
	private Integer sort;

	/**
	 * 新导入房源
	 */
	private Integer isNew;
}
