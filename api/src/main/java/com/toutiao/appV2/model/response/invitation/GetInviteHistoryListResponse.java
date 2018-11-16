package com.toutiao.appV2.model.response.invitation;

import com.toutiao.web.dao.entity.invitation.InviteHistory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetInviteHistoryListResponse {
    private List<InviteHistory> data;

    private Integer totalNum;
}
