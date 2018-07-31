package com.toutiao.app.service.invitation;

import com.toutiao.web.dao.entity.invitation.InviteHistory;

import java.util.List;

public interface InviteHistoryService {
    int saveInviteHistory(InviteHistory inviteHistory);

    List<InviteHistory> getInviteHistoryByCode(Integer code);
}
