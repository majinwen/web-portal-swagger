package com.toutiao.appV2.model.invitation;

import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zym
 */
@Data
@Builder
public class GetSuperInviteHistoryResponse {
    private List<SuperInviteHistoryDo> data;

    private Integer totalNum;
}
