package com.toutiao.appV2.apiimpl.invitation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.domain.invitation.InvitationCodeDoQuery;
import com.toutiao.app.service.invitation.impl.InvitationCodeServiceImpl;
import com.toutiao.appV2.api.invitation.InvitationApi;
import com.toutiao.appV2.model.request.invitation.GetInviteHistoryRequest;
import com.toutiao.appV2.model.request.invitation.InvitationRequest;
import com.toutiao.appV2.model.response.invitation.GetCodeInfoListResponse;
import com.toutiao.appV2.model.response.invitation.InvitationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T03:28:53.819Z")

@Controller
public class InvitationApiController implements InvitationApi {

    private static final Logger log = LoggerFactory.getLogger(InvitationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public InvitationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

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
                return new ResponseEntity<InvitationResponse>(invitationResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<InvitationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<InvitationResponse>(HttpStatus.NOT_IMPLEMENTED);
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
                return new ResponseEntity<GetCodeInfoListResponse>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<GetCodeInfoListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCodeInfoListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
