package com.toutiao.app.service.activity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toutiao.app.dao.activity.ActivityEsDao;
import com.toutiao.app.domain.activity.*;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.app.service.sys.ShortMessageService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.activity.ActivityStatisticsMapper;
import com.toutiao.web.dao.mapper.activity.UserNewBuildingActivityMapper;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private ActivityEsDao activityEsDao;
    @Autowired
    private ActivityStatisticsMapper activityStatisticsMapper;



    /**
     * 表单提交--用户提交参与的活动楼盘信息
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    @Override
    public NashResult saveUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery) {


        Integer userId = userNewBuildingActivityDoQuery.getUserId();

        Integer buildingId = userNewBuildingActivityDoQuery.getBuildingId();
        Integer activityId = userNewBuildingActivityDoQuery.getActivityId();
        String userPhone = userNewBuildingActivityDoQuery.getUserPhone();
//        UserNewBuildingActivity userNewBuildingActivity = userNewBuildingActivityMapper.selectActivityByUser(userId,userPhone,buildingId,activityId);
        List<UserNewBuildingActivity> userNewBuildingActivityList = userNewBuildingActivityMapper.selectActivityByUser(userPhone,buildingId,activityId);
        if(null != userNewBuildingActivityList && userNewBuildingActivityList.size() > 0){
            return NashResult.Fail("1","已参与此活动！");
        }else{
            UserNewBuildingActivity activity = new UserNewBuildingActivity();
            BeanUtils.copyProperties(userNewBuildingActivityDoQuery,activity);
            activity.setCityId(userNewBuildingActivityDoQuery.getCityId());
            activity.setCreateTime(new Date());
            activity.setActivityBuildingId(buildingId);
            activity.setActivityBuildingName(userNewBuildingActivityDoQuery.getBuildingName());
            try {
                Integer saveResult = userNewBuildingActivityMapper.insertSelective(activity);
                if(saveResult > 0 && StringTool.isNotBlank(userId)){
                    //不论称呼是否一样都更新一下
                    UserBasic userBasic = new UserBasic();
                    userBasic.setUserId(userId.toString());
                    userBasic.setUserCallName(activity.getUserCallName());
                    Integer updateResult = userBasicMapper.updateByPrimaryKeySelective(userBasic);
                    if(updateResult > 0){
                        //todo
                        //shortMessageService.sendSmsByActivity(userPhone,activity);
                        return NashResult.build("保存成功");
                    }else{
                        logger.error("更新用户称呼失败，userId："+userId+"={}");
                        return NashResult.Fail("2","保存失败");
                    }

                }else if(saveResult > 0 && StringTool.isBlank(userId)){
                    //todo
                    //shortMessageService.sendSmsByActivity(userPhone,activity);
                    return NashResult.build("保存成功");
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
     * 查询用户参与新房活动信息--后台使用
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


    /**
     * 查询用户参与新房活动信息--前台个人中心使用
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    @Override
    public PageInfo<UserNewBuildingActivityDo> listActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery) {
        PageHelper.startPage(userNewBuildingActivityDoQuery.getPageNum(), userNewBuildingActivityDoQuery.getPageSize());

        List<UserNewBuildingActivityDo> userActivityMsgList = userNewBuildingActivityMapper.selectUserActivityMsg(userNewBuildingActivityDoQuery);

        PageInfo<UserNewBuildingActivityDo> listUserActivityMsg = new PageInfo<>(userActivityMsgList);
        return listUserActivityMsg;
    }

    /**
     *
     * @return
     */
    @Override
    public ActivityStatisticsDo getActivityCount() {

        ActivityStatisticsDo activityStatisticsDo = new ActivityStatisticsDo();
        //获取参与活动楼盘总量--es查询

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.termQuery("is_active", 1));//优惠楼盘标志
        SearchResponse searchResponse = activityEsDao.getActivityCount(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        int activityCount = 0;
        if (hits.length>0){
            activityCount = (int) searchResponse.getHits().totalHits;
        }
        activityStatisticsDo.setActivityBuildsAmount(activityCount);
        //昨日领取数量

        ActivityStatistics activityStatistics = activityStatisticsMapper.selectActivityCount();
        ActivityStatistics statistics = new ActivityStatistics();
        boolean isToday = false;
        if(null==activityStatistics){

            statistics.setActivityGrandTotal(0);
            statistics.setStatisticalDate(new Date());
            statistics.setYesterdayActivityTotal(0);
            activityStatisticsMapper.insertSelective(statistics);
        }else{
            isToday = DateUtil.isToday(activityStatistics.getStatisticalDate());
        }
        int cumulativeNo =0;
        int randomNo = 0;
        if(!isToday){
            if(null==activityStatistics){
                cumulativeNo = userNewBuildingActivityMapper.selectActivityCount();
            }else{
                Random random = new Random();
                randomNo = random.nextInt(780)%(780-130+1) + 130;
                cumulativeNo = activityStatistics.getActivityGrandTotal()+randomNo;
            }
            activityStatisticsMapper.updateActivityStatistics(cumulativeNo,randomNo);
            activityStatisticsDo.setCumulativeAmount(cumulativeNo);
            activityStatisticsDo.setBeforeCumulativeAmount(randomNo);
        }else{
            ActivityStatistics selectActivityCount = activityStatisticsMapper.selectActivityCount();
//            activityStatisticsMapper.updateActivityGrandTotal(cumulativeNo+selectActivityCount.getYesterdayActivityTotal());

            activityStatisticsDo.setCumulativeAmount(selectActivityCount.getActivityGrandTotal());

            activityStatisticsDo.setBeforeCumulativeAmount(selectActivityCount.getYesterdayActivityTotal());
        }

        return activityStatisticsDo;
    }


}
