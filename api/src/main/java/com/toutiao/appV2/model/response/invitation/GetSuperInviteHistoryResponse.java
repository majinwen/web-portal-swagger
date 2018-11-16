package com.toutiao.appV2.model.response.invitation;

import com.toutiao.app.domain.invitation.SuperInviteHistoryDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetSuperInviteHistoryResponse {
    private List<SuperInviteHistoryDo> data;

    private Integer totalNum;
}
