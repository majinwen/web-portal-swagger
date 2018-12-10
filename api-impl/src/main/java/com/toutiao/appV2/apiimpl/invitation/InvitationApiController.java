package com.toutiao.appV2.apiimpl.invitation;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InvitationCodeService;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.app.service.invitation.impl.InvitationCodeServiceImpl;
import com.toutiao.appV2.api.invitation.InvitationApi;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.invitation.*;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.dao.entity.invitation.InvitationCode;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zym
 */
@Controller
public class InvitationApiController implements InvitationApi {

    private static final Logger log = LoggerFactory.getLogger(InvitationApiController.class);

    private final HttpServletRequest request;

    @Autowired
    public InvitationApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

    @Autowired
    private InviteHistoryService inviteHistoryService;

    @Override
    public ResponseEntity<InvitationResponse> getInvitation(InvitationRequest invitationRequest) {
        InvitationCodeDoQuery invitationCodeDoQuery = new InvitationCodeDoQuery();
        BeanUtils.copyProperties(invitationRequest, invitationCodeDoQuery);
        InvitationCodeDo invitationCodeDo = invitationCodeService.getInvitation(invitationCodeDoQuery);
        InvitationResponse invitationResponse = new InvitationResponse();
        BeanUtils.copyProperties(invitationCodeDo, invitationResponse);
        return new ResponseEntity<>(invitationResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetCodeInfoListResponse> getCodeInfo(GetInviteHistoryRequest getInviteHistoryRequest) {
        List<InvitationCodeDo> invitationCodeDos = invitationCodeService.getCodeInfo(getInviteHistoryRequest.getCode(),
                getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
        GetCodeInfoListResponse response = GetCodeInfoListResponse.builder()
                .data(invitationCodeDos)
                .totalNum(invitationCodeDos.size())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<StringDataResponse> saveInviteHistory(@RequestBody @Validated InviteHistoryRequest inviteHistoryRequest) {

        InviteHistory inviteHistory = new InviteHistory();
        BeanUtils.copyProperties(inviteHistoryRequest, inviteHistory);
        InvitationCode invitationValid = invitationCodeService.getInvitationValid(inviteHistoryRequest.getInvitationCode());
        if (invitationValid == null) {
            throw new BaseException(UserInterfaceErrorCodeEnum.INVITATION_CODE_NOT_EXITS);
        }
        int i = inviteHistoryService.saveInviteHistory(inviteHistory);
        List<InviteHistory> inviteHistoryByCode = inviteHistoryService.getInviteHistoryByCode(inviteHistoryRequest.getInvitationCode());
        if (!CollectionUtils.isEmpty(inviteHistoryByCode)) {
            invitationCodeService.updateInviteTotal(inviteHistoryRequest.getInvitationCode(), inviteHistoryByCode.size());
        }
        if (i > 0) {
            StringDataResponse stringDataResponse = new StringDataResponse();
            stringDataResponse.setData("添加成功");
            return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.INVITATION_CODE_ADD_ERROR);
        }
    }

    @Override
    public ResponseEntity<GetInviteHistoryListResponse> getInviteHistoryList(GetInviteHistoryRequest getInviteHistoryRequest) {
        List<InviteHistory> inviteHistoryList = inviteHistoryService.getInviteHistoryList(getInviteHistoryRequest.getCode(),
                getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
        GetInviteHistoryListResponse response = GetInviteHistoryListResponse.builder()
                .data(inviteHistoryList)
                .totalNum(inviteHistoryList.size())
                .build();
        return new ResponseEntity<GetInviteHistoryListResponse>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetSuperInviteHistoryResponse> getSuperInviteHistory(SuperInviteHistoryRequest superInviteHistoryRequest) {
        SuperInviteHistoryDoQuery superInviteHistoryDoQuery = new SuperInviteHistoryDoQuery();
        BeanUtils.copyProperties(superInviteHistoryRequest, superInviteHistoryDoQuery);
        List<SuperInviteHistoryDo> superInviteHistorys = inviteHistoryService.getSuperInviteHistory(superInviteHistoryDoQuery);
        GetSuperInviteHistoryResponse response = GetSuperInviteHistoryResponse.builder()
                .data(superInviteHistorys)
                .totalNum(superInviteHistorys.size())
                .build();
        return new ResponseEntity<GetSuperInviteHistoryResponse>(response, HttpStatus.OK);
    }

}
