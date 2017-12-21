package com.toutiao.web.api.chance.request;

import lombok.Data;

@Data
public class ProjHouseInfoRequest{

	// 房源id
	private Integer houseId;

	/**
	 页面需要传递的字段
	*/
	//房源面积(60以下 60-90 [0-60] [60-90]  )
	private String[] houseArea;
	
	//房源户型
	private String houseTypeId;
	
	//房源朝向
	private String houseOrientation;
	
	
	//房源总价(200万以下 [0-200万])
	private String houseTotalPrices;
	
	
	//房源标签(满二)
	private String houseLabelId;
	
	//房源楼层
	private String houseFloor;
	
	//房源楼龄[0-5]
	private String[] houseYear;
	
	//房源用途
	private String housePurpose;
	
	//房源电梯
	private String houseLift;
	
	//房源供暖
	private String houseHeating;
	
	//房源权属
	private String houseOwnership;
		
	//物业类型
	private String houseManagementType ;
		
	
	//房源小区名称
	private String housePlotName;
	
	//商圈名称
	private String houseBusinessName;
	/**
	 * id=23008613&name=朝阳
	 * id=611100323&name=奥林匹克公园
	 * id=648&name=2号线
	 * id=20556&name=古城
	 * 
	 */
	//区域id
	private String areaId;
	//区域
	private String areaName;
	//地铁id
	private String subwayId;	
	//地铁名称
	private String subwayName;

	private String buildingNname;
}