package com.toutiao.appV2.apiimpl.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDomain;
import com.toutiao.app.service.sellhouse.HouseBusinessAndRoomService;
import com.toutiao.appV2.api.sellhouse.HouseBusinessAndRoomRestApi;
import com.toutiao.appV2.model.sellhouse.HouseBusinessAndRoomRequest;
import com.toutiao.appV2.model.sellhouse.HouseBusinessAndRoomResponse;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 商圈户型房源专题页控制层
 */
@RestController
@Slf4j
public class HouseBusinessAndRoomRestController implements HouseBusinessAndRoomRestApi {

    @Autowired
    private HouseBusinessAndRoomService houseBusinessAndRoomService;

    private final HttpServletRequest request;

    @Autowired
    public HouseBusinessAndRoomRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取商圈+户型房源专题列表
     *
     * @param houseBusinessAndRoomRequest
     * @return
     */
    @Override
    public ResponseEntity<HouseBusinessAndRoomResponse> getHouseBusinessAndRoomHouses(@ApiParam(value = "houseBusinessAndRoomRequest", required = true) @Validated HouseBusinessAndRoomRequest houseBusinessAndRoomRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(houseBusinessAndRoomRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {

                HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery = new HouseBusinessAndRoomDoQuery();
                BeanUtils.copyProperties(houseBusinessAndRoomRequest, houseBusinessAndRoomDoQuery);
                HouseBusinessAndRoomDomain houseBusinessAndRoomHouses = houseBusinessAndRoomService
                        .getHouseBusinessAndRoomHouses(houseBusinessAndRoomDoQuery, CityUtils.getCity());
                HouseBusinessAndRoomResponse houseBusinessAndRoomResponse = new HouseBusinessAndRoomResponse();
                BeanUtils.copyProperties(houseBusinessAndRoomHouses, houseBusinessAndRoomResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(houseBusinessAndRoomResponse));
                return new ResponseEntity<HouseBusinessAndRoomResponse>(houseBusinessAndRoomResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<HouseBusinessAndRoomResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseBusinessAndRoomResponse>(HttpStatus.NOT_IMPLEMENTED);
    }
}
