package com.toutiao.web.dao.mapper.message;

import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePushMapper {
    int countByExample(MessagePushExample example);



    int deleteByExample(MessagePushExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessagePush record);

    int insertSelective(MessagePush record);

    List<MessagePush> selectByExample(MessagePushExample example);

    MessagePush selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessagePush record, @Param("example") MessagePushExample example);

    int updateByExample(@Param("record") MessagePush record, @Param("example") MessagePushExample example);

    int updateByPrimaryKeySelective(MessagePush record);

    int updateByPrimaryKey(MessagePush record);

    List<MessagePush> selectHouseActivity(Integer userId);

    List<MessagePush> selectListRenewal(Integer userId);

}