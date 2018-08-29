package com.toutiao.app.service.message.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.message.*;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import com.toutiao.web.dao.mapper.message.MessagePushMapper;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessagePushServiceImpl implements MessagePushService {
    @Autowired
    private MessagePushMapper messagePushMapper;

    @Autowired
    private SellHouseService sellHouseService;

    private static final Integer CONDITIONHOUSE = 3;
    private static final Integer FAVORITEPLOT = 4;
    private static final Integer FAVORITEHOUSE = 5;
    private static final Integer SUBSCRIBETHEME = 6;

    /**
     * 常用汉字
     */
    private static final String YOURFAVORITE = "您关注的";
    private static final String YOURFAVORITEPLOT = "您关注的小区";
    private static final String YOURSUBSCRIBE = "您订阅的";
    private static final String ADD = "新增";
    private static final String HOUSECOUNT = "套房源";
    private static final String SPACE = " ";
    private static final String MIDLINE = "-";
    private static final String ALLBEIJING = "全北京";
    private static final String ANYPRICE = "价格不限";
    private static final String WANUP = "万以上";
    private static final String WANDOWN = "万以下";
    private static final String WAN = "万";
    private static final String ROOM = "居";
    private static final String LIVINGROOM = "居室";
    private static final String ANYLIVINGROOM = "居室不限";
    private static final String ADDONE = "新上一套";
    private static final String SQUAREMETER = "㎡";
    private static final String CHINESESQUAREMETER = "平米";
    private static final String OF = "的";
    private static final String RISE = "涨";
    private static final String DROP = "降";



    /**
     * 房源类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    @Override
    public MessagePushDomain getHouseTypeMessage(MessagePushDoQuery messagePushQuery, String userId) {
        MessagePushExample example = new MessagePushExample();
        example.setOrderByClause("create_time DESC");
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (StringTool.isNotEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }

        //内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动)
        if (messagePushQuery.getContentType() != null) {
            criteria.andContentTypeEqualTo(messagePushQuery.getContentType());
        }
        //消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
        criteria.andMessageTypeEqualTo(2);
        //推送类型(0-系统消息, 1-定向推送)
        criteria.andPushTypeEqualTo(1);

        if (messagePushQuery.getLastMessageId() != null) {
            criteria.andIdLessThan(messagePushQuery.getLastMessageId());
        }

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
        List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        if (CollectionUtils.isEmpty(messagePushDos)) {
            return messagePushDomain;
        }

        int messageHouseCount = 0;
        Integer lastMessageId = null;
        List<MessagePushDo> message = new ArrayList<>();
        for (MessagePushDo messagePushDo : messagePushDos) {
            String houseIds = messagePushDo.getHouseId();
            if (!"{}".equals(houseIds)) {
                String[] split = houseIds.substring(1, houseIds.length() - 1).split(",");
                //配置房源展示数量
                split = subStrings(split, 0, 10);
                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(split);
                messagePushDo.setMessageSellHouseDos(messageSellHouseDos);
                messageHouseCount += split.length;
                message.add(messagePushDo);
                if (messageHouseCount > 10) {
                    lastMessageId = messagePushDo.getId();
                    break;
                } else {
                    lastMessageId = messagePushDo.getId();
                }
            }
        }
        messagePushDomain.setData(message);
        messagePushDomain.setTotalCount(message.size());
        messagePushDomain.setLastMessageId(lastMessageId);

        return messagePushDomain;
    }

    /**
     * 截取数组指定长度元素
     *
     * @param src
     * @param begin
     * @param count
     * @return
     */
    private String[] subStrings(String[] src, int begin, int count) {
        if (src.length <= count) {
            return src;
        }
        String[] bs = new String[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0 || str.equals("") || "0".equals(str);
    }

    /**
     * 添加区域名称
     *
     * @param message
     */
    private void getDistrictNameById(List<MessagePushDo> message) {
        for (MessagePushDo messagePushDo : message) {
            JSONObject jsStr = JSONObject.parseObject(messagePushDo.getMessageTheme());
            String districtIdStr = jsStr.get("districtId").toString();
            if (StringTool.isEmpty(districtIdStr)) {
                return;
            }
            String[] districtIds = districtIdStr.substring(1, districtIdStr.length() - 1).split(",");
            StringBuilder stringBuilder = new StringBuilder();
            for (String districtId : districtIds) {
                if (StringTool.isEmpty(districtId)) {
                    continue;
                }
                districtId = districtId.substring(1, districtId.length() - 1);
                String district = DistrictMap.getDistrict(districtId);
                stringBuilder.append(district).append(",");
            }
            String district = stringBuilder.substring(0, stringBuilder.length() - 1).toString();
            jsStr.put("districtName", district);
            messagePushDo.setMessageTheme(jsStr.toString());
        }

    }

    private static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 专题类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    @Override
    public MessagePushDomain getThemeTypeMessage(MessagePushDoQuery messagePushQuery, String userId) {
        MessagePushExample example = new MessagePushExample();
        example.setOrderByClause("create_time DESC");
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (StringTool.isNotEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        //内容类型(6-订阅的主题有更新)
        criteria.andContentTypeEqualTo(6);
        //消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
        criteria.andMessageTypeEqualTo(3);
        //推送类型(0-系统消息, 1-定向推送)
        criteria.andPushTypeEqualTo(1);

        if (messagePushQuery.getLastMessageId() != null) {
            criteria.andIdLessThan(messagePushQuery.getLastMessageId());
        }

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
        List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        if (CollectionUtils.isEmpty(messagePushDos)) {
            return messagePushDomain;
        }

        Integer lastMessageId = null;
        //配置专题展示数量
        if (messagePushDos.size() > 10) {
            List<MessagePushDo> message = messagePushDos.subList(0, 10);
//            getDistrictNameById(message);
            messagePushDomain.setData(message);
            lastMessageId = message.get(message.size() - 1).getId();
        } else {
//            getDistrictNameById(messagePushDos);
            messagePushDomain.setData(messagePushDos);
            lastMessageId = messagePushDos.get(messagePushDos.size() - 1).getId();
        }
        messagePushDomain.setLastMessageId(lastMessageId);
        messagePushDomain.setTotalCount(messagePushDomain.getData().size());
        return messagePushDomain;
    }

    /**
     * 添加区域名称
     *
     * @param ids
     */
    private String getDistrictNameById(String ids) {
        if (StringTool.isEmpty(ids) || "[\"\"]".equals(ids)) {
            return ALLBEIJING;
            }
        String[] districtIds = ids.replace("[", "").replace("]", "").split(",");
            StringBuilder stringBuilder = new StringBuilder();
            for (String districtId : districtIds) {
                if (StringTool.isEmpty(districtId)) {
                    continue;
                }
                districtId = districtId.substring(1, districtId.length() - 1);
                String district = DistrictMap.getDistrict(districtId);
                stringBuilder.append(district).append(",");
            }
        return stringBuilder.substring(0, stringBuilder.length() - 1).toString();
    }

    /**
     * 首页消息列表
     *
     * @param homeMessageDoQuery
     * @param userId
     * @return
     */
    @Override
    public List<HomeMessageDo> getHomeMessage(HomeMessageDoQuery homeMessageDoQuery, String userId) {
        ArrayList<HomeMessageDo> homeMessageDos = new ArrayList<>();
        for (int i = 3; i < 7; i++) {
            MessagePushExample example = new MessagePushExample();
            example.setOrderByClause("create_time DESC");
            MessagePushExample.Criteria criteria = example.createCriteria();
            if (StringTool.isNotEmpty(userId)) {
                criteria.andUserIdEqualTo(Integer.valueOf(userId));
            }
            //推送类型(0-系统消息, 1-定向推送)
            criteria.andPushTypeEqualTo(1);
            criteria.andContentTypeEqualTo(i);
            if (i == CONDITIONHOUSE && homeMessageDoQuery.getConditionHouseDate() != 0) {
                criteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getConditionHouseDate()));
            } else if (i == FAVORITEPLOT && homeMessageDoQuery.getFavoritePlotDate() != 0) {
                criteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getFavoritePlotDate()));
            } else if (i == FAVORITEHOUSE && homeMessageDoQuery.getFavoriteHouseDate() != 0) {
                criteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getFavoriteHouseDate()));
            } else if (i == SUBSCRIBETHEME && homeMessageDoQuery.getSubscribeThemeDate() != 0) {
                criteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getSubscribeThemeDate()));
            }
            Date date = new Date();
            criteria.andCreateTimeLessThan(date);
            List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
            HomeMessageDo homeMessageDo = new HomeMessageDo();
            homeMessageDo.setContentType(i);
            if (CollectionUtils.isEmpty(messagePushes)) {
                continue;
            }
            JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
            List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
//            JSONObject contentJson = JSON.parseObject(messagePushDos.get(0).getMessageTheme());
//            homeMessageDo.setContent(contentJson);
//            JSONObject houseDataJson = JSON.parseObject(messagePushDos.get(0).getHouseData());
//            homeMessageDo.setHouseDate(houseDataJson);
            String[] messageContent = getMessageContent(messagePushDos.get(0), i);
            homeMessageDo.setMessageContent(messageContent[0]);
            homeMessageDo.setBoldMessageContent(messageContent[1]);

            if (StringTool.isEmpty(messageContent[0]) || StringTool.isEmpty(messageContent[1])) {
                homeMessageDo.setUnReadCount(0);
            } else {
                homeMessageDo.setUnReadCount(messagePushDos.size());
            }

            homeMessageDo.setCreateTime(messagePushDos.get(0).getCreateTime().getTime());
            homeMessageDos.add(homeMessageDo);
        }
        return homeMessageDos;
    }

    /**
     * 生成消息内容
     *
     * @param messagePushDo
     * @param contentType
     * @return
     */
    private String[] getMessageContent(MessagePushDo messagePushDo, Integer contentType) {
        String[] contentArr = new String[2];
        StringBuilder messageContent = new StringBuilder("");
        StringBuilder blodMessageContent = new StringBuilder("");
        JSONObject mcJson = JSON.parseObject(messagePushDo.getMessageTheme());
        JSONObject hdJson = JSON.parseObject(messagePushDo.getHouseData());
        if (contentType.equals(CONDITIONHOUSE) || contentType.equals(SUBSCRIBETHEME)) {
            String districtName = getDistrictNameById(mcJson.get("districtId").toString());
            blodMessageContent.append(districtName);

            String beginPriceStr = "";
            if (StringTool.isNotEmpty(mcJson.get("beginPrice"))) {
                beginPriceStr = mcJson.get("beginPrice").toString();
            }
            String endPriceStr = "";
            if (StringTool.isNotEmpty(mcJson.get("endPrice"))) {
                endPriceStr = mcJson.get("endPrice").toString();
            }
            if (isNotEmpty(beginPriceStr) && isNotEmpty(endPriceStr)) {
                blodMessageContent.append(beginPriceStr).append(MIDLINE).append(endPriceStr).append(WAN);
            } else if (isEmpty(beginPriceStr) && isNotEmpty(endPriceStr)) {
                blodMessageContent.append(endPriceStr).append(WANDOWN);
            } else if (isNotEmpty(beginPriceStr) && isEmpty(endPriceStr)) {
                blodMessageContent.append(beginPriceStr).append(WANUP);
            } else {
                blodMessageContent.append(ANYPRICE);
            }

            if (contentType.equals(CONDITIONHOUSE)) {
                if (!"[]".equals(mcJson.get("layoutId")) || !"null".equals(mcJson.get("layoutId"))) {
                    String layoutIdStr = mcJson.get("layoutId").toString().replace("\"", "");
                    String substring = layoutIdStr.substring(1, layoutIdStr.length() - 1);
                    blodMessageContent.append(substring).append(ROOM);
                }
            }

            messageContent.append(YOURSUBSCRIBE).append(SPACE).append(blodMessageContent)
                    .append(SPACE).append(ADD).append(hdJson.get("count")).append(HOUSECOUNT);

        } else if (contentType.equals(FAVORITEPLOT)) {
            JSONArray buildArea = (JSONArray) mcJson.get("buildArea");
            JSONArray layoutIds = (JSONArray) mcJson.get("layoutId");
            if (!buildArea.isEmpty() && !layoutIds.isEmpty()) {
                blodMessageContent.append(mcJson.get("plotName"));
                messageContent.append(YOURFAVORITEPLOT).append(SPACE).append(blodMessageContent).append(SPACE)
                        .append(ADDONE).append(buildArea.get(0).toString()).append(SQUAREMETER).append(OF)
                        .append(layoutIds.get(0).toString()).append(LIVINGROOM);
            }
        } else if (contentType.equals(FAVORITEHOUSE)) {
            blodMessageContent.append(mcJson.get("building_name")).append(mcJson.get("build_area")).append(CHINESESQUAREMETER);
            if (StringTool.isNotEmpty(mcJson.get("layoutId"))) {
                blodMessageContent.append(mcJson.get("layoutId")).append(LIVINGROOM);
            } else {
                blodMessageContent.append(ANYLIVINGROOM);
            }

            if ("1".equals(mcJson.get("flag"))) {
                blodMessageContent.append(SPACE).append(RISE).append(mcJson.get("money")).append(WAN);
            } else {
                blodMessageContent.append(SPACE).append(DROP).append(mcJson.get("money")).append(WAN);
            }

            messageContent.append(YOURFAVORITE).append(blodMessageContent);
        }
        contentArr[0] = blodMessageContent.toString();
        contentArr[1] = messageContent.toString();
        return contentArr;
    }
}
