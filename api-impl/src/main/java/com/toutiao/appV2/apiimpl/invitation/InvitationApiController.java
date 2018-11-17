package com.toutiao.appV2.apiimpl.invitation;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InvitationCodeService;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.app.service.invitation.impl.InvitationCodeServiceImpl;
import com.toutiao.appV2.api.invitation.InvitationApi;
import com.toutiao.appV2.model.invitation.*;
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
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                InvitationCodeDoQuery invitationCodeDoQuery = new InvitationCodeDoQuery();
                BeanUtils.copyProperties(invitationRequest, invitationCodeDoQuery);
                InvitationCodeDo invitationCodeDo = invitationCodeService.getInvitation(invitationCodeDoQuery);
                InvitationResponse invitationResponse = new InvitationResponse();
                BeanUtils.copyProperties(invitationCodeDo, invitationResponse);
                return new ResponseEntity<>(invitationResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetCodeInfoListResponse> getCodeInfo(GetInviteHistoryRequest getInviteHistoryRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                List<InvitationCodeDo> invitationCodeDos = invitationCodeService.getCodeInfo(getInviteHistoryRequest.getCode(),
                        getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
                GetCodeInfoListResponse response = GetCodeInfoListResponse.builder()
                        .data(invitationCodeDos)
                        .totalNum(invitationCodeDos.size())
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity saveInviteHistory(@Validated InviteHistoryRequest inviteHistoryRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                InviteHistory inviteHistory = new InviteHistory();
                BeanUtils.copyProperties(inviteHistoryRequest, inviteHistory);
                InvitationCode invitationValid = invitationCodeService.getInvitationValid(inviteHistoryRequest.getInvitationCode());
                if (invitationValid == null) {
                    return new ResponseEntity("邀请码不存在", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                int i = inviteHistoryService.saveInviteHistory(inviteHistory);
                List<InviteHistory> inviteHistoryByCode = inviteHistoryService.getInviteHistoryByCode(inviteHistoryRequest.getInvitationCode());
                if (!CollectionUtils.isEmpty(inviteHistoryByCode)) {
                    invitationCodeService.updateInviteTotal(inviteHistoryRequest.getInvitationCode(), inviteHistoryByCode.size());
                }
                if (i > 0) {
                    return new ResponseEntity("添加成功", HttpStatus.OK);
                } else {
                    return new ResponseEntity("添加失败", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetInviteHistoryListResponse> getInviteHistoryList(GetInviteHistoryRequest getInviteHistoryRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                List<InviteHistory> inviteHistoryList = inviteHistoryService.getInviteHistoryList(getInviteHistoryRequest.getCode(),
                        getInviteHistoryRequest.getPageSize(), getInviteHistoryRequest.getPageNum());
                GetInviteHistoryListResponse response = GetInviteHistoryListResponse.builder()
                        .data(inviteHistoryList)
                        .totalNum(inviteHistoryList.size())
                        .build();
                return new ResponseEntity<GetInviteHistoryListResponse>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<GetInviteHistoryListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetInviteHistoryListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetSuperInviteHistoryResponse> getSuperInviteHistory(SuperInviteHistoryRequest superInviteHistoryRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SuperInviteHistoryDoQuery superInviteHistoryDoQuery = new SuperInviteHistoryDoQuery();
                BeanUtils.copyProperties(superInviteHistoryRequest, superInviteHistoryDoQuery);
                List<SuperInviteHistoryDo> superInviteHistorys = inviteHistoryService.getSuperInviteHistory(superInviteHistoryDoQuery);
                GetSuperInviteHistoryResponse response = GetSuperInviteHistoryResponse.builder()
                        .data(superInviteHistorys)
                        .totalNum(superInviteHistorys.size())
                        .build();
                return new ResponseEntity<GetSuperInviteHistoryResponse>(response, HttpStatus.OK );
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<GetSuperInviteHistoryResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetSuperInviteHistoryResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
