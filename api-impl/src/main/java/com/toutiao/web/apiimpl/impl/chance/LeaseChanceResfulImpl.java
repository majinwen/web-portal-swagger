package com.toutiao.web.apiimpl.impl.chance;


import com.toutiao.web.api.chance.LeaseChanceService;
import com.toutiao.web.apiimpl.authentication.Permission;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.service.chance.LeaseChanceCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyl on 17/9/12.
 */
@RestController
@RequestMapping("leaseChance")
public class LeaseChanceResfulImpl implements LeaseChanceService{

    @Autowired
    private LeaseChanceCoreService leaseChanceCoreService;


    /**
     * 出租机会列表查询
     * @param page
     * @param size
     * @param circleId  商圈ID
     * @param productTypeId 产品类型（枚举）
     * @param stage 阶段
     * @param name  机会名称
     * @param phone 电话
     * @return
     */
    @Override
    @Permission(value = {"57"})
    @RequestMapping(value = {"/findLeaseChanceListByRange"}, method = {RequestMethod.GET})
    @ResponseBody
    public NashResult findLeaseChanceListByRange(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15")Integer size,
            @RequestParam(value = "cityId", required = false, defaultValue = "-1")Integer cityId,
            @RequestParam(value = "districtId", required = false, defaultValue = "-1")Integer districtId,
            @RequestParam(value = "circleId", required = false, defaultValue = "-1")Integer circleId,
            @RequestParam(value = "projectId", required = false, defaultValue = "-1")Integer projectId,
            Integer productTypeId, Integer stage, String name, String phone,Integer status) {


        return NashResult.build(0);
    }


    public static void main(String[] args) {
        System.out.print("111");
    }



}
