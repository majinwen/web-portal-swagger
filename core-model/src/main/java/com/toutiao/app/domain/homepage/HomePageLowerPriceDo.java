package com.toutiao.app.domain.homepage;

import lombok.Data;

/**
 * 首页价格洼地Do
 *
 * @author : zhaiyanming
 */
@Data
public class HomePageLowerPriceDo {
	/**
	 * 房源id
	 */
	private Integer houseId;

	/**
	 * 房源面积(单位:平方米)
	 */
	private Double buildArea;

	/**
	 * 朝向名称
	 */
	private String forwardName;

	/**
	 * 房源价格(单位:万)
	 */
	private Double houseTotalPrices;

	/**
	 * 房源标题图片
	 */
	private String housePhotoTitle;

	/**
	 * 小区
	 */
	private String plotName_accurate;

	/**
	 * 户型
	 */
	private Integer layout;

	/**
	 * 标签
	 */
	private Integer[] tags;

	/**
	 * 标签名称
	 */
	private String[] tagsName;

	/**
	 * 是否主力户型(0-否,1-是)
	 */
	private Integer isMainLayout;

	/**
	 * 是否成交户型(0-否,1-是)
	 */
	private Integer isDealLayout;

	/**
	 * 是否价格洼地(0-否,1-是)
	 */
	private Integer isLowPrice;

	/**
	 * 是否同户型小区均价最低(0-否,1-是)
	 */
	private Integer isLowest;

	/**
	 * 是否新导入房源(0-否,1-是)
	 */
	private Integer isNew;

	/**
	 * 在同商圈同户型范围内做低价排名
	 */
	private Integer rankLowInBizcircleLayout;

	/**
	 * 同小区同户型范围内做低价排名
	 */
	private Integer rankInLowCommunityLayout;

	/**
	 * 与小区平均单价的绝对值差
	 */
	private Double avgAbsoluteWithCommunity;

	/**
	 * 与商圈平均单价的绝对值差
	 */
	private Double avgAbsoluteWithBizcircle;

	/**
	 * 与区县平均单价的绝对值差
	 */
	private Double avgAbsoluteWithDistrict;

	/**
	 * 与小区平均单价的相对值(百分比)
	 */
	private Double avgRelativeWithCommunity;

	/**
	 * 与商圈平均单价的相对值(百分比)
	 */
	private Double avgRelativeWithBizcircle;

	/**
	 * 与区县平均单价的相对值(百分比)
	 */
	private Double avgRelativeWithDistrict;

	/**
	 * 与小区平均总价的绝对值差
	 */
	private Double totalAbsoluteWithCommunity;

	/**
	 * 与商圈平均总价的绝对值差
	 */
	private Double totalAbsoluteWithBizcircle;

	/**
	 * 与区县平均总价的绝对值差
	 */
	private Double totalAbsoluteWithDistrict;

	/**
	 * 与小区平均总价的相对值(百分比)
	 */
	private Double totalRelativeWithCommunity;

	/**
	 * 与商圈平均总价的相对值(百分比)
	 */
	private Double totalRelativeWithBizcircle;

	/**
	 * 与区县平均总价的相对值(百分比)
	 */
	private Double totalRelativeWithDistrict;
}
