/*
package com.toutiao.web.apiimpl.rest.invitation;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.app.service.invitation.impl.InvitationCodeServiceImpl;
import com.toutiao.web.api.chance.request.invitation.GetInviteHistoryRequest;
import com.toutiao.web.api.chance.request.invitation.InvitationRequest;
import com.toutiao.web.api.chance.response.invitation.InvitationResponse;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("rest/invitation")
public class InvitationController {
    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

    */
/**
     * 获取邀请码
     *
     * @param invitationRequest
     * @return
     *//*

    @RequestMapping(value = "/getInvitation", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getInvitation(InvitationRequest invitationRequest) {
        InvitationCodeDoQuery invitationCodeDoQuery = new InvitationCodeDoQuery();
        BeanUtils.copyProperties(invitationRequest, invitationCodeDoQuery);
        InvitationCodeDo invitationCodeDo = invitationCodeService.getInvitation(invitationCodeDoQuery);
        InvitationResponse invitationResponse = new InvitationResponse();
        BeanUtils.copyProperties(invitationCodeDo, invitationResponse);
        return NashResult.build(invitationResponse);
    }

    */
/**
     * 获取验证码信息
     *
     * @param getInviteHistoryRequest
     * @return
     *//*

    @RequestMapping(value = "/getCodeInfo", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getCodeInfo(GetInviteHistoryRequest getInviteHistoryRequest) {
        List<InvitationCodeDo> invitationCodeDos = invitationCodeService.getCodeInfo(getInviteHistoryRequest.getCode(),
                getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
        return NashResult.build(invitationCodeDos);
    }

}
*/
