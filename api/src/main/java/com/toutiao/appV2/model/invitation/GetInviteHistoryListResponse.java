package com.toutiao.appV2.model.invitation;

import com.toutiao.web.dao.entity.invitation.InviteHistory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zym
 */
@Data
@Builder
public class GetInviteHistoryListResponse {
    private List<InviteHistory> data;

    private Integer totalNum;
}
