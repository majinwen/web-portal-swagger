package com.toutiao.appV2.apiimpl.invitation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import com.toutiao.app.domain.invitation.SuperInviteHistoryDoQuery;
import com.toutiao.app.service.invitation.InvitationCodeService;
import com.toutiao.app.service.invitation.InviteHistoryService;
import com.toutiao.appV2.api.invitation.InviteHistoryApi;
import com.toutiao.appV2.model.invitation.GetInviteHistoryRequest;
import com.toutiao.appV2.model.invitation.InviteHistoryRequest;
import com.toutiao.appV2.model.invitation.SuperInviteHistoryRequest;
import com.toutiao.appV2.model.invitation.GetInviteHistoryListResponse;
import com.toutiao.appV2.model.invitation.GetSuperInviteHistoryResponse;
import com.toutiao.web.dao.entity.invitation.InvitationCode;
import com.toutiao.web.dao.entity.invitation.InviteHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zym
 */
@Controller
public class InviteHistoryApiController implements InviteHistoryApi{

    private static final Logger log = LoggerFactory.getLogger(InviteHistoryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public InviteHistoryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private InviteHistoryService inviteHistoryService;

    @Autowired
    private InvitationCodeService invitationCodeService;

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
