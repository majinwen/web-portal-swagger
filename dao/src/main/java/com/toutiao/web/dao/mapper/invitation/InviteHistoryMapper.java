package com.toutiao.web.dao.mapper.invitation;

import com.toutiao.app.domain.invitation.InviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import com.toutiao.web.dao.entity.invitation.InviteHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteHistoryMapper {
    int countByExample(InviteHistoryExample example);

    int deleteByExample(InviteHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InviteHistory record);

    int insertSelective(InviteHistory record);

    List<InviteHistory> selectByExample(InviteHistoryExample example);

    InviteHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InviteHistory record, @Param("example") InviteHistoryExample example);

    int updateByExample(@Param("record") InviteHistory record, @Param("example") InviteHistoryExample example);

    int updateByPrimaryKeySelective(InviteHistory record);

    int updateByPrimaryKey(InviteHistory record);

    List<InviteHistoryDo> getInviteHistorys(String equipmentNo);

    List<InviteHistory> getInviteHistoryByCode(@Param("code") Integer invitationCode);

    List<SuperInviteHistoryDo> getSuperInviteHistory(SuperInviteHistoryDoQuery superInviteHistoryDoQuery);

    int getCountByEquipmentNo(String equipmentNo);
}