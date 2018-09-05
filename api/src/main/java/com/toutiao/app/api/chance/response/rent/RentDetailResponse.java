package com.toutiao.app.api.chance.response.rent;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class RentDetailResponse {

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
     * 区域名称
     */
    @ChangeName("districtName")
    private String districtName;

    /**
     * 商圈名称
     */
    @ChangeName("areaName")
    private String areaName;
    /**
     * 出租房源Id
     */
    private String houseId;
    /**
     * 租赁方式(1-整租，2-合租)
     */
    private Integer rentType;
    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rentSign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rentSignName;
    /**
     * 需求
     */
    private String demand;
    /**
     * 坐标(lat,lon)
     */
    private String location;
    /**
     * 房源面积
     */
    private Double houseArea;
    /**
     * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 几卫
     */
    private Integer toilet;
    /**
     * 几厨
     */
    private Integer kitchen;
    /**
     * 阳台
     */
    private Integer balcony;
    /**
     * 房源所在楼层(地下室为负数)
     */
    private Integer floor;
    /**
     * 总楼层数
     */
    private Integer totalFloor;
    /**
     * 最近的地铁
     */
    @ChangeName("nearBySubway")
    private String nearestSubway;

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
     * 房源标题图片
     */
    @ChangeName("housePhotoTitle")
    private String houseTitleImg;
    /**
     * 房源图片
     */
//    @ChangeName("buildingImages")
//    private List<Map<String,String>> rentHouseImg;

    private String[] housePhoto;

    /**
     * 付款方式名称
     */
    private String payModeName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 房源来源
     */
    private String dataSourceName;
    /**
     * 房源来源标志
     */
    private Integer dataSourceSign;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    @ChangeName("updateTime")
    private String upStringTime;
    /**
     * 发版时间
     */
    private String publishTime;

    /**
     * 是否是推荐
     */
    private Integer isRecommend;

    /**
     * 类型
     */
    private  Integer rentHouseType;

    /**
     * 录入/导入房源标题
     */
    private String houseTitle;

    /**
     * 电梯情况
     */
    private  String hasLift;

    /**
     * 楼层级别
     */
    private  String  floorLevel;

   /**
    * 配套设施(字符串数组)
    */
   private String[] supportingFacilities;
   /**
    * 经纪人信息
    */
   @ChangeName("agent")
   private AgentBaseDo agentBaseDo;

  /**
   * 是否收藏
   */
  private Boolean isFavorite;

 /**
  * 名片
  */
 private String agentBusinessCard;

}
