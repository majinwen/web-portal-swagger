package com.toutiao.app.service.invitation.impl;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.app.domain.invitation.InviteHistoryDo;
import com.toutiao.app.service.invitation.InvitationCodeService;
import com.toutiao.web.dao.entity.invitation.InvitationCode;
import com.toutiao.web.dao.mapper.invitation.InvitationCodeMapper;
import com.toutiao.web.dao.mapper.invitation.InviteHistoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {
    @Autowired
    private InvitationCodeMapper invitationCodeMapper;

    @Autowired
    private InviteHistoryMapper inviteHistoryMapper;

    /**
     * 保存邀请码
     *
     * @param record
     * @return
     */
    @Override
    public int saveInvitationCode(InvitationCode record) {
        return invitationCodeMapper.insertSelective(record);
    }

    /**
     * 获取邀请码
     *
     * @param invitationCodeDoQuery
     * @return
     */
    @Override
    public InvitationCodeDo getInvitation(InvitationCodeDoQuery invitationCodeDoQuery) {
        InvitationCodeDo invitationCodeDo = new InvitationCodeDo();
        InvitationCode invitation = invitationCodeMapper.getInvitation(invitationCodeDoQuery.getUserId());
        if (invitation != null) {
            BeanUtils.copyProperties(invitation, invitationCodeDo);
            List<InviteHistoryDo> inviteHistorys = inviteHistoryMapper.getInviteHistorys(invitationCodeDoQuery.getEquipmentNo());
            invitationCodeDo.setInvateHistoryDos(inviteHistorys);
            return invitationCodeDo;
        }
        return null;
    }

    /**
     * 修改邀请次数
     *
     * @param code
     * @param inviteTotal
     * @return
     */
    @Override
    public int updateInviteTotal(Integer code, Integer inviteTotal) {
        return invitationCodeMapper.updateInviteTotal(code, inviteTotal);
    }
}
