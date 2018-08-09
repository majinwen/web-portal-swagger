package com.toutiao.app.service.invitation;


import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.web.dao.entity.invitation.InvitationCode;

import java.util.List;


public interface InvitationCodeService {

    InvitationCodeDo getInvitation(InvitationCodeDoQuery invitationCodeDoQuery);

    int updateInviteTotal(Integer code, Integer inviteTotal);

    InvitationCode getInvitationValid(Integer code);

    List<InvitationCodeDo> getCodeInfo(Integer code);
}