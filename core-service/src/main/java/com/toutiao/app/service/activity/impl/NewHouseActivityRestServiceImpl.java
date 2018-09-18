package com.toutiao.app.service.activity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.activity.UserNewBuildingActivityMapper;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   14:23
 * Theme:
 */
@Service
public class NewHouseActivityRestServiceImpl implements NewHouseActivityRestService {

    private Logger logger = LoggerFactory.getLogger(NewHouseActivityRestServiceImpl.class);

    @Autowired
    private UserNewBuildingActivityMapper userNewBuildingActivityMapper;
    @Autowired
    private UserBasicMapper userBasicMapper;



    /**
     * 表单提交--用户提交参与的活动楼盘信息
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    @Override
    public NashResult saveUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery) {


        Integer userId = userNewBuildingActivityDoQuery.getUserId();

        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userId.toString());

        if(userBasic==null){
            logger.error("查询用户信息错误,无此用户，userId："+userId+"={}");
            return NashResult.Fail("2","保存失败");
        }
        Integer buildingId = userNewBuildingActivityDoQuery.getBuildingId();
        Integer activityId = userNewBuildingActivityDoQuery.getActivityId();
        UserNewBuildingActivity userNewBuildingActivity = userNewBuildingActivityMapper.selectActivityByUser(userId,buildingId,activityId);
        if(null!=userNewBuildingActivity){
            return NashResult.Fail("1","已参与此活动！");
        }else{
            String city = CityUtils.getCity();

            UserNewBuildingActivity activity = new UserNewBuildingActivity();
            BeanUtils.copyProperties(userNewBuildingActivityDoQuery,activity);
            activity.setCityId(CityUtils.returnCityId(city));
            activity.setCreateTime(new Date());
            activity.setActivityBuildingId(buildingId);
            activity.setActivityBuildingName(userNewBuildingActivityDoQuery.getBuildingName());
            try {
                Integer saveResult = userNewBuildingActivityMapper.insertSelective(activity);
                if(saveResult > 0){
                    //不论称呼是否一样都更新一下
//                    UserBasic userBasic = new UserBasic();
                    userBasic.setUserId(userId.toString());
                    userBasic.setUserCallName(activity.getUserCallName());
                    Integer updateResult = userBasicMapper.updateByPrimaryKeySelective(userBasic);
                    if(updateResult > 0){
                        return NashResult.build("保存成功");
                    }else{
                        logger.error("更新用户称呼失败，userId："+userId+"={}");
                        return NashResult.Fail("2","保存失败");
                    }

                }else{
                    logger.error("保存用户活动信息失败，userId："+userId+"={}");
                    return NashResult.Fail("2","保存失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("新房提交活动表单,buildingId:"+userNewBuildingActivityDoQuery.getBuildingId()+
                        ", 用户id:"+userNewBuildingActivityDoQuery.getUserId()+"={}",e.getStackTrace());
            }
            return NashResult.Fail("2","保存失败");
        }

    }


    /**
     * 查询用户参与新房活动信息
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    @Override
    public PageInfo<UserNewBuildingActivityDo> listUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery) {

        PageHelper.startPage(userNewBuildingActivityDoQuery.getPageNum(), userNewBuildingActivityDoQuery.getPageSize());

        List<UserNewBuildingActivityDo> userActivityMsgList = userNewBuildingActivityMapper.selectUserActivityMsg(userNewBuildingActivityDoQuery);

        PageInfo<UserNewBuildingActivityDo> listUserActivityMsg = new PageInfo<>(userActivityMsgList);
        return listUserActivityMsg;
    }



}
