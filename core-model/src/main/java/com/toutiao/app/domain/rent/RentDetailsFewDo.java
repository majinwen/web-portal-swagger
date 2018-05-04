package com.toutiao.app.domain.rent;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class RentDetailsFewDo {
    /**
     * 出租房源Id
     */
    private String houseId;
    /**
     * 小区Id
     */
    @ChangeName("buildingId")
    private Integer zufangId;
    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String zufangName;
    /**
     * 房源面积
     */
    private Double houseArea;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 出租房源标签名称
     */
    @ChangeName("tags")
    private String[] rentHouseTagsName;
    /**
     * 租金(元/月)
     */
    @ChangeName("rentPrice")
    private Double rentHousePrice;
    /**
     * 总数
     */
    private Integer totalNum;
    /**
     * 房源标题图
     */
    @ChangeName("housePhotoTitle")
    private String houseTitleImg;
    /**
     * 录入/导入房源标题
     */
    private String houseTitle;
    /**
     * 房源来源类型(录入/导入)
     */
    private Integer rentHouseType;
    /**
     * 租房推优查询uid
     */
    private String uid;

    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    private Integer userId;
}
