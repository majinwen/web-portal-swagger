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

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {
    @Autowired
    private InvitationCodeMapper invitationCodeMapper;

    @Autowired
    private InviteHistoryMapper inviteHistoryMapper;

    /**
     * 生成8位数字邀请码
     *
     * @param length
     * @return
     */
    private static String randomDigits(int length) {
        if (0 > length) {
            return "";
        }
        String[] randomNums = new String[]{"0", "1", "2", "3", "4", "5", "6",
                "7", "8", "9"};
        List<String> randomNumList = Arrays.asList(randomNums);
        // 随机排列
        Collections.shuffle(randomNumList);
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomNum.append(randomNumList.get(i));
        }
        return randomNum.toString();
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
        if (invitation == null) {
            invitation = new InvitationCode();
            invitation.setCode(Integer.valueOf(randomDigits(8)));
            invitation.setCreateTime(new Date());
            invitation.setUserId(invitationCodeDoQuery.getUserId());
            invitation.setInviteTotal(0);
            invitationCodeMapper.insertSelective(invitation);
        }
        BeanUtils.copyProperties(invitation, invitationCodeDo);
        List<InviteHistoryDo> inviteHistorys = inviteHistoryMapper.getInviteHistorys(invitationCodeDoQuery.getEquipmentNo());
        invitationCodeDo.setInvateHistoryDos(inviteHistorys);
        return invitationCodeDo;
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
