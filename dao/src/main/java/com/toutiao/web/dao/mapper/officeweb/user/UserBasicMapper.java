package com.toutiao.web.dao.mapper.officeweb.user;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicMapper extends BaseDao {
    int deleteByPrimaryKey(String userId);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKey(UserBasic record);

    UserBasic selectUserByExample(UserBasic record);

    UserBasic selectUserByPhone(UserBasic record);

    UserBasic selectUserBasicByRcId(UserBasic record);

    Integer getUserFavoriteCount(Integer userId);

    Integer getUserSubscribeCount(Integer userId);

    Integer unbindweixin(String userId);

    UserBasic getUserBasicByunionId(String unionId);

}