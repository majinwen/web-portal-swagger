package com.toutiao.appV2.model.invitation;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zym
 */
@Data
@Builder
public class GetCodeInfoListResponse {
    private List<InvitationCodeDo> data;

    private Integer totalNum;
}
