package com.toutiao.web.dao.mapper.activity;

import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNewBuildingActivityMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserNewBuildingActivity record);

    int insertSelective(UserNewBuildingActivity record);

    UserNewBuildingActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserNewBuildingActivity record);

    int updateByPrimaryKey(UserNewBuildingActivity record);

    /**
     * 查询用户活动信息
     * @param buildingId
     * @return
     */
//    UserNewBuildingActivity selectActivityByUser(@Param("userId")Integer userId,@Param("activityBuildingId")Integer buildingId,
//                                                 @Param("activityId")Integer activityId);

    List<UserNewBuildingActivity> selectActivityByUser(@Param("userPhone")String userPhone, @Param("activityBuildingId")Integer buildingId,
                                                       @Param("activityId")Integer activityId);

    /**
     *  查询用户活动信息--城市后台使用
     * @param userNewBuildingActivityDoQuery
     * @return
     */
    List<UserNewBuildingActivityDo> selectUserActivityMsg(UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery);
}