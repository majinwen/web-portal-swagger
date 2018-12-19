package com.toutiao.web.common.util.mapSearch;


import com.toutiao.web.common.constant.map.MapGroupEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName mapGroupUtil
 * @Author jiangweilong
 * @Date 2018/11/23 4:03 PM
 * @Description:
 **/
public class MapGroupUtil {


    public static Integer returnMapGrouId(String groupType){

        if(StringUtils.isNoneEmpty(groupType)){
            Integer groupId = MapGroupEnum.getValue(groupType);
            return groupId;
        }else{
            return MapGroupEnum.DISTRICT.getValue();
        }

    }


}
