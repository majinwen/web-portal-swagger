package com.toutiao.web.common.staticonst;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jyl on 17/10/12.
 *
 *  role_code
 */
public class RoleCode {
    /**
     * 企业选址顾问
     */
    public static final String ENTERPRISE_SITE_CONSULTANT = "enterprise_site_consultant";

    /**
     * 城市直销负责人
     */
    public static final String CITY_DIRECT_SALE_CHARGER = "city_direct_sale_charger";

    /**
     * 公海管理员
     */
    public static final String OPEN_SEA_ADMIN = "open_sea_admin";

    /**
     * 资产管理顾问
     */
    public static final String ASSET_MANAGE_CONSULTANT = "asset_manage_consultant";

    /**
     * 社区顾问
     */
    public static final String COMMUNITY_CONSULTANT = "community_consultant";

    /**
     * 直销组长
     */
    public static final String DIRECT_SALE_LEADER = "direct_sale_leader";

    /**
     * 直销顾问
     */
    public static final String DERECT_SALE_CONSULTANT = "direct_sale_consultant";


    public static boolean isChanceDIRECT(String rc){
        if(StringUtils.equals(rc,CITY_DIRECT_SALE_CHARGER) || StringUtils.equals(rc,DIRECT_SALE_LEADER) || StringUtils.equals(rc,DERECT_SALE_CONSULTANT)){
            return true;
        }
        return false;
    }

}
