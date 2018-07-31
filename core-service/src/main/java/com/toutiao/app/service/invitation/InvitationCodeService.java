package com.toutiao.app.service.invitation;


import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;


public interface InvitationCodeService {

    InvitationCodeDo getInvitation(InvitationCodeDoQuery invitationCodeDoQuery);

    int updateInviteTotal(Integer code, Integer inviteTotal);
}