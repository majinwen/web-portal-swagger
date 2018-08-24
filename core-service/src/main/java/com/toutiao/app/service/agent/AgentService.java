package com.toutiao.app.service.agent;

import com.toutiao.app.domain.agent.AgentBaseDo;

public interface AgentService {
    /**
     * 根据userId获取经纪人信息
     * @param userId
     * @return
     */
    AgentBaseDo queryAgentInfoByUserId(String userId, String city);
}
