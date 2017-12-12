package com.toutiao.web.domain.query;

import lombok.Data;

@Data
public class ProjHouseInfoQuery {

	// 房源id
	private Integer houseId;
	/**
	 页面需要传递的字段
	*/
	//房源面积(1,2,3,4)
	private String houseAreaId;

	//房源户型id（1，2，3，4）
	private String houseTypeId;
	
	//房源朝向id（1，2，3，4）
	private String houseOrientationId;

	//房源总价 起始价
	private Double beginPrice;
	//房源总价 结束价
	private Double endPrice;

	//房源标签(满二)（1，2，3，4）
	private String houseLabelId;
	
	//房源楼层（1，2，3，4）
	private String houseFloorId;

	//房源楼龄[0-5]
	private String houseYearId;

	//房源用途（1，2，3，4）
	private String housePurposeId;
	
	//房源电梯（1，2，3，4）
	private String houseLiftId;
	
	//房源供暖（1，2，3，4）
	private String houseHeatingId;
	
	//房源权属（1，2，3，4）
	private String houseOwnershipId;
		
	//物业类型（1，2，3，4）
	private String houseManagementTypeId ;

	//建筑类型（1，2，3，4）
	private String buildingTypeId ;

	/**
	 * 排序  0--默认（按房源级别（广告优先））--1总价升排序--2总价降排序
	 */
	private Integer sort;

	//房源小区名称
	private String housePlotName;
	
	//商圈名称
	private String houseBusinessName;

	//商圈id
	private Integer houseBusinessId;

	//区域id
	private Integer areaId;
	//区域
	private String areaName;
	// 地铁线id
	private Integer[] subwayLineId;

	// 地铁站id
	private Integer[] subwayStationId;

	/**
	 * 页码
	 */
	private Integer pageNum;
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 经度
	 */
	private double lat;
	/**
	 * 维度
	 */
	private double lon;


}