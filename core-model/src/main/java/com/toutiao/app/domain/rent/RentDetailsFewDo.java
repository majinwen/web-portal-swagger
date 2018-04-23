package com.toutiao.app.domain.rent;

import lombok.Data;

@Data
public class RentDetailsFewDo {
    /**
     * 出租房源Id
     */
    private String house_id;
    /**
     * 小区Id
     */
    private Integer zufang_id;
    /**
     * 小区名称
     */
    private String zufang_name;
    /**
     * 房源面积
     */
    private Double house_area;
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
    private String area_name;
    /**
     * 区域名称
     */
    private String district_name;
    /**
     * 租赁方式名称
     */
    private String rent_type_name;
    /**
     * 出租房源标签名称
     */
    private String[] rent_house_tags_name;
    /**
     * 租金(元/月)
     */
    private Double rent_house_price;
    /**
     * 总数
     */
    private Integer totalNum;
    /**
     * 房源标题图
     */
    private String house_title_img;
    /**
     * 录入房源标题
     */
    private String entry_house_title;
    /**
     * 导入房源标题
     */
    private String import_house_title;
    /**
     * 房源来源类型(录入/导入)
     */
    private Integer rentHouseType;
}
