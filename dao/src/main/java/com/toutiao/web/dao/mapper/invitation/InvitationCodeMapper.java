package com.toutiao.web.dao.mapper.invitation;

import com.toutiao.web.dao.entity.invitation.InvitationCode;
import com.toutiao.web.dao.entity.invitation.InvitationCodeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationCodeMapper {
    int countByExample(InvitationCodeExample example);

    int deleteByExample(InvitationCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvitationCode record);

    int insertSelective(InvitationCode record);

    List<InvitationCode> selectByExample(InvitationCodeExample example);

    InvitationCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvitationCode record, @Param("example") InvitationCodeExample example);

    int updateByExample(@Param("record") InvitationCode record, @Param("example") InvitationCodeExample example);

    int updateByPrimaryKeySelective(InvitationCode record);

    int updateByPrimaryKey(InvitationCode record);

    InvitationCode getInvitation(String userId);

    int updateInviteTotal(@Param("code") Integer code, @Param("inviteTotal") Integer inviteTotal);
}