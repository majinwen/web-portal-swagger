package com.toutiao.app.service.invitation;

import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.web.dao.entity.invitation.InviteHistory;

import java.util.List;

public interface InviteHistoryService {
    int saveInviteHistory(InviteHistory inviteHistory);

    List<InviteHistory> getInviteHistoryByCode(Integer code);

    List<InviteHistory> getInviteHistoryList(Integer code, Integer pageSize, Integer pageNum);

    List<SuperInviteHistoryDo> getSuperInviteHistory(SuperInviteHistoryDoQuery superInviteHistoryDoQuery);

    int getCountByEquipmentNo(String equipmentNo);
}
