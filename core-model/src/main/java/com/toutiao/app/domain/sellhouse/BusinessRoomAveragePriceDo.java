package com.toutiao.app.domain.sellhouse;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

/**
 * 商圈均价Do
 */
@Data
public class BusinessRoomAveragePriceDo {
	/**
	 * 商圈Id
	 */
	@ChangeName("areaId")
	private Integer area_id;

	/**
	 * 几室
	 */
	private Integer room;

	/**
	 * 几厅
	 */
	private Integer hall;

	/**
	 * 商圈户型均价
	 */
	@ChangeName("averagePrice")
	private Double average_price;
}
