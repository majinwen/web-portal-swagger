package com.toutiao.web.apiimpl.rest.sellhouse;

import com.toutiao.app.api.chance.request.sellhouse.HouseBusinessAndRoomRequest;
import com.toutiao.app.api.chance.response.sellhouse.HouseBusinessAndRoomResponse;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDomain;
import com.toutiao.app.service.sellhouse.HouseBusinessAndRoomService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhaiyanming
 * @date : 2018/7/6 15:29
 * @desc :
 */
@RestController
@RequestMapping("/rest/esf/houseBusinessAndRoom")
public class HouseBusinessAndRoomRestController {
	@Autowired
	private HouseBusinessAndRoomService houseBusinessAndRoomService;

	/**
	 * 获取商圈+户型房源专题列表
	 */
	@RequestMapping(value = "getHouseBusinessAndRoomHouses", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getHouseBusinessAndRoomHouses(HouseBusinessAndRoomRequest houseBusinessAndRoomRequest) {
		HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery = new HouseBusinessAndRoomDoQuery();
		BeanUtils.copyProperties(houseBusinessAndRoomRequest, houseBusinessAndRoomDoQuery);
		HouseBusinessAndRoomDomain houseBusinessAndRoomHouses = houseBusinessAndRoomService
				.getHouseBusinessAndRoomHouses(houseBusinessAndRoomDoQuery);
		HouseBusinessAndRoomResponse houseBusinessAndRoomResponse = new HouseBusinessAndRoomResponse();
		BeanUtils.copyProperties(houseBusinessAndRoomHouses, houseBusinessAndRoomResponse);
		return NashResult.build(houseBusinessAndRoomResponse);
	}
}
