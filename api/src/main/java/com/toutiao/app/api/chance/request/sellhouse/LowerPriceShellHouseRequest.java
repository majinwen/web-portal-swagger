package com.toutiao.app.api.chance.request.sellhouse;

import lombok.Data;

@Data
public class LowerPriceShellHouseRequest {
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
	 * 排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-面积升, 4-面积降)
	 */
	private Integer sort = 0;

	/**
	 * 页码
	 */
	private Integer pageNum = 1;

	/**
	 * 每页数量
	 */
	private Integer pageSize = 10;
}
