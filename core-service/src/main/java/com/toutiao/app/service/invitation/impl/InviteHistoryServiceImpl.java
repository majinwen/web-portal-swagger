package com.toutiao.app.service.invitation.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import com.toutiao.web.dao.mapper.invitation.InviteHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class InviteHistoryServiceImpl implements InviteHistoryService {
    private static final Short INVITE_HISTORY_VALID = 0;

    private static final Short INVITE_HISTORY_NOT_VALID = 1;

    @Autowired
    private InviteHistoryMapper inviteHistoryMapper;

    /**
     * 保存邀请记录
     *
     * @param inviteHistory
     * @return
     */
    @Override
    public int saveInviteHistory(InviteHistory inviteHistory) {
        inviteHistory.setCreateTime(new Date());
        inviteHistory.setIsValid(INVITE_HISTORY_VALID);
        return inviteHistoryMapper.insertSelective(inviteHistory);
    }

    /**
     * 通过邀请码获取邀请记录
     *
     * @param code
     * @return
     */
    @Override
    public List<InviteHistory> getInviteHistoryByCode(Integer code) {
        List<InviteHistory> inviteHistoryByCode = inviteHistoryMapper.getInviteHistoryByCode(code);
        return inviteHistoryByCode;
    }

    @Override
    public List<InviteHistory> getInviteHistoryList(Integer code, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<InviteHistory> inviteHistoryByCode = inviteHistoryMapper.getInviteHistoryByCode(code);
        return inviteHistoryByCode;
    }

    @Override
    public List<SuperInviteHistoryDo> getSuperInviteHistory(SuperInviteHistoryDoQuery superInviteHistoryDoQuery) {
        PageHelper.startPage(superInviteHistoryDoQuery.getPageSize(), superInviteHistoryDoQuery.getPageNum());
        return inviteHistoryMapper.getSuperInviteHistory(superInviteHistoryDoQuery);
    }

    @Override
    public int getCountByEquipmentNo(String equipmentNo) {
        return inviteHistoryMapper.getCountByEquipmentNo(equipmentNo);
    }
}
