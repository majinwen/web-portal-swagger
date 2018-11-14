package com.toutiao.web.apiimpl.rest.invitation;

import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InvitationCodeService;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.web.api.chance.request.invitation.GetInviteHistoryRequest;
import com.toutiao.web.api.chance.request.invitation.InviteHistoryRequest;
import com.toutiao.web.api.chance.request.invitation.SuperInviteHistoryRequest;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.invitation.InvitationCode;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("rest/inviteHistory")
public class InviteHistoryController {
    @Autowired
    private InviteHistoryService inviteHistoryService;

    @Autowired
    private InvitationCodeService invitationCodeService;

    /**
     * 保存邀请记录
     *
     * @param inviteHistoryRequest
     * @return
     */
    @RequestMapping(value = "/saveInviteHistory", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveInviteHistory(@Validated InviteHistoryRequest inviteHistoryRequest) {
        InviteHistory inviteHistory = new InviteHistory();
        BeanUtils.copyProperties(inviteHistoryRequest, inviteHistory);
        InvitationCode invitationValid = invitationCodeService.getInvitationValid(inviteHistoryRequest.getInvitationCode());
        if (invitationValid == null) {
            return NashResult.Fail("邀请码无效！");
        }
        int i = inviteHistoryService.saveInviteHistory(inviteHistory);
        List<InviteHistory> inviteHistoryByCode = inviteHistoryService.getInviteHistoryByCode(inviteHistoryRequest.getInvitationCode());
        if (!CollectionUtils.isEmpty(inviteHistoryByCode)) {
            invitationCodeService.updateInviteTotal(inviteHistoryRequest.getInvitationCode(), inviteHistoryByCode.size());
        }
        if (i > 0) {
            return NashResult.build(0);
        } else {
            return NashResult.Fail("添加失败");
        }
    }

    /**
     * 获取邀请码邀请记录
     *
     * @param getInviteHistoryRequest
     * @return
     */
    @RequestMapping(value = "/getInviteHistoryList", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getInviteHistoryList(GetInviteHistoryRequest getInviteHistoryRequest) {
        List<InviteHistory> inviteHistoryList = inviteHistoryService.getInviteHistoryList(getInviteHistoryRequest.getCode(),
                getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
        return NashResult.build(inviteHistoryList);
    }

    /**
     * 获取三级邀请信息
     *
     * @param superInviteHistoryRequest
     * @return
     */
    @RequestMapping(value = "/getSuperInviteHistory", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getSuperInviteHistory (SuperInviteHistoryRequest superInviteHistoryRequest) {
        SuperInviteHistoryDoQuery superInviteHistoryDoQuery = new SuperInviteHistoryDoQuery();
        BeanUtils.copyProperties(superInviteHistoryRequest, superInviteHistoryDoQuery);
        List<SuperInviteHistoryDo> superInviteHistory = inviteHistoryService.getSuperInviteHistory(superInviteHistoryDoQuery);
        return NashResult.build(superInviteHistory);
    }
}
