package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class PlotsThemeDo {
	/**
	 * 小区编号
	 */
	@ChangeName("buildingId")
	private Integer id;

	/**
	 * 小区名称
	 */
	@ChangeName("buildingName")
	private String rc;

	/**
	 * 区域编号
	 */
	private String areaId;

	/**
	 * 区域
	 */
	private String area;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 均价
	 */
	@ChangeName("averagePrice")
	private Double avgPrice;

	/**
	 * 距离最近的大型公园
	 */
	private String nearestPark;

	/**
	 * 距最近公园的距离
	 */
	private Double nearestParkDistance;

	/**
	 * 小区推荐标签Id
	 */
	private Integer[] recommendBuildTagsId;

	/**
	 * 小区推荐标签名称
	 */
	private String[] recommendBuildTagsName;
}
