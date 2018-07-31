package com.toutiao.app.service.invitation;


import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.web.dao.entity.invitation.InvitationCode;


public interface InvitationCodeService {
    int saveInvitationCode(InvitationCode record);

    InvitationCodeDo getInvitation(InvitationCodeDoQuery invitationCodeDoQuery);

    int updateInviteTotal(Integer code, Integer inviteTotal);
}