package com.toutiao.web.dao.mapper.officeweb.user;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;

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


}