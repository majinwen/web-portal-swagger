package com.toutiao.app.service.invitation.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import com.toutiao.web.dao.mapper.invitation.InviteHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
        Date createTime = superInviteHistoryDoQuery.getCreateTime();
        if (createTime != null){
            Date createTimeStart = getStartOfDay(createTime);
            Date createTimeEnd = getEndOfDay(createTime);
            superInviteHistoryDoQuery.setCreateTimeStart(createTimeStart);
            superInviteHistoryDoQuery.setCreateTimeEnd(createTimeEnd);
        }
        return inviteHistoryMapper.getSuperInviteHistory(superInviteHistoryDoQuery);
    }

    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2017-10-15 00:00:00
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public int getCountByEquipmentNo(String equipmentNo) {
        return inviteHistoryMapper.getCountByEquipmentNo(equipmentNo);
    }
}
