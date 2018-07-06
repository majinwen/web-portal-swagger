package com.toutiao.app.api.chance.request.sellhouse;

import lombok.Data;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomRequest {
	/**
	 * 商圈
	 */
	private String houseBusinessName;

	/**
	 * 户型
	 */
	private Integer room;

	/**
	 * 详情页房源编号
	 */
	private Integer houseId;

	/**
	 * 页码
	 */
	private Integer pageNum = 1;

	/**
	 * 每页数量
	 */
	private Integer pageSize = 10;
}
