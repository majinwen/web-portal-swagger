package com.toutiao.app.service.message.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.message.*;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.ToutiaoBeanUtils;
import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import com.toutiao.web.dao.mapper.message.MessagePushMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessagePushServiceImpl implements MessagePushService {
    private static final Map<Integer, String> CITYID2NAME = new HashMap<>();
    static {
        CITYID2NAME.put(12, "北京");
        CITYID2NAME.put(13, "上海");
        CITYID2NAME.put(14, "天津");
    }
    private static final Map<Integer, String> CITYID2ABBREVIATION = new HashMap<>();
    static {
        CITYID2ABBREVIATION.put(12, "bj");
        CITYID2ABBREVIATION.put(13, "sh");
        CITYID2ABBREVIATION.put(14, "tj");
    }

    private static final String URL = "http://fenzhan.toutiaofangchan.com";

    /**
     * 搜索订阅
     */
    private static final Integer CONDITIONHOUSE = 3;
    /**
     * 关注小区
     */
    private static final Integer FAVORITEPLOT = 4;
    /**
     * 关注房源
     */
    private static final Integer FAVORITEHOUSE = 5;
    /**
     * 主题订阅
     */
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
    private static final String ALL = "全";
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

    @Autowired
    private MessagePushMapper messagePushMapper;
    @Autowired
    private SellHouseService sellHouseService;
    @Value("${qiniu.img_domain}")
    private String qinniuImg;
    @Value("${app.domain.name}")
    private String appName;
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
        List<MessagePushDo> messagePushDos = ToutiaoBeanUtils.copyPropertiesToList(messagePushes, MessagePushDo.class);
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
                messagePushDo.setHouseId(Arrays.toString(split));
                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(split);
                //处理房源标题图和详情页Url
                dealPhotoTitleAndDetailUrl(messageSellHouseDos, messagePushDo.getCityId());
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
            List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(messagePushes)) {
                continue;
            }
            List<MessagePushDo> messagePushDos = ToutiaoBeanUtils.copyPropertiesToList(messagePushes, MessagePushDo.class);

            String[] messageContent = getMessageContent(messagePushDos.get(0), i);
            HomeMessageDo homeMessageDo = new HomeMessageDo();
            homeMessageDo.setContentType(i);
            homeMessageDo.setMessageContent(messageContent[0]);
            homeMessageDo.setBoldMessageContent(messageContent[1]);
            homeMessageDo.setCreateTime(messagePushDos.get(0).getCreateTime().getTime());

            //查询一段时间内消息未读数量
            MessagePushExample countExample = new MessagePushExample();
            MessagePushExample.Criteria countCriteria = countExample.createCriteria();
            if (StringTool.isNotEmpty(userId)) {
                countCriteria.andUserIdEqualTo(Integer.valueOf(userId));
            }
            countCriteria.andPushTypeEqualTo(1);
            countCriteria.andContentTypeEqualTo(i);
            if (i == CONDITIONHOUSE && homeMessageDoQuery.getConditionHouseDate() != 0) {
                countCriteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getConditionHouseDate()));
            } else if (i == FAVORITEPLOT && homeMessageDoQuery.getFavoritePlotDate() != 0) {
                countCriteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getFavoritePlotDate()));
            } else if (i == FAVORITEHOUSE && homeMessageDoQuery.getFavoriteHouseDate() != 0) {
                countCriteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getFavoriteHouseDate()));
            } else if (i == SUBSCRIBETHEME && homeMessageDoQuery.getSubscribeThemeDate() != 0) {
                countCriteria.andCreateTimeGreaterThanOrEqualTo(new Date(homeMessageDoQuery.getSubscribeThemeDate()));
            }
            int unreadCount = messagePushMapper.countByExample(countExample);
            if (StringTool.isNotEmpty(messageContent[0])) {
                homeMessageDo.setUnReadCount(unreadCount);
            }
            homeMessageDos.add(homeMessageDo);
        }
        return homeMessageDos;
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
        criteria.andContentTypeEqualTo(SUBSCRIBETHEME);
        //消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
        criteria.andMessageTypeEqualTo(3);
        //推送类型(0-系统消息, 1-定向推送)
        criteria.andPushTypeEqualTo(1);

        if (messagePushQuery.getLastMessageId() != null) {
            criteria.andIdLessThan(messagePushQuery.getLastMessageId());
        }

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        List<MessagePushDo> messagePushDos = ToutiaoBeanUtils.copyPropertiesToList(messagePushes, MessagePushDo.class);
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        if (CollectionUtils.isEmpty(messagePushDos)) {
            return messagePushDomain;
        }

        Integer lastMessageId;
        //配置专题展示数量
        if (messagePushDos.size() > 10) {
            List<MessagePushDo> message = messagePushDos.subList(0, 10);
            dealThemeDetailUrl(message);
            messagePushDomain.setData(message);
            lastMessageId = message.get(message.size() - 1).getId();
        } else {
            dealThemeDetailUrl(messagePushDos);
            messagePushDomain.setData(messagePushDos);
            lastMessageId = messagePushDos.get(messagePushDos.size() - 1).getId();
        }
        messagePushDomain.setLastMessageId(lastMessageId);
        messagePushDomain.setTotalCount(messagePushDomain.getData().size());
        return messagePushDomain;
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
        JSONObject mcJson = messagePushDo.getMessageTheme();
        JSONObject hdJson = messagePushDo.getHouseData();
        if (contentType.equals(CONDITIONHOUSE) || contentType.equals(SUBSCRIBETHEME)) {
            String districtName = dealDistrictName(mcJson.get("districtName").toString(), messagePushDo.getCityId());
            blodMessageContent.append(districtName);
            if (isNotEmpty(mcJson.get("beginPrice")) && isNotEmpty(mcJson.get("endPrice"))) {
                blodMessageContent.append(mcJson.get("beginPrice")).append(MIDLINE).append(mcJson.get("endPrice")).append(WAN);
            } else if (isEmpty(mcJson.get("beginPrice")) && isNotEmpty(mcJson.get("endPrice"))) {
                blodMessageContent.append(mcJson.get("endPrice")).append(WANDOWN);
            } else if (isNotEmpty(mcJson.get("beginPrice")) && isEmpty(mcJson.get("endPrice"))) {
                blodMessageContent.append(mcJson.get("beginPrice")).append(WANUP);
            } else {
                blodMessageContent.append(ANYPRICE);
            }

            if (contentType.equals(CONDITIONHOUSE)) {
                if (mcJson.get("layoutId") != null && !"[]".equals(mcJson.get("layoutId").toString())) {
                    String layoutIdStr = mcJson.get("layoutId").toString().replace("\"", "");
                    String substring = layoutIdStr.substring(1, layoutIdStr.length() - 1);
                    blodMessageContent.append(substring).append(ROOM);
                } else {
                    blodMessageContent.append(ANYLIVINGROOM);
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
            if (isNotEmpty(mcJson.get("layoutId"))) {
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
        contentArr[0] = messageContent.toString();
        contentArr[1] = blodMessageContent.toString();
        return contentArr;
    }

    /**
     * 处理房源标题图
     *
     * @param messageSellHouseDos
     * @param cityId
     */
    private void dealPhotoTitleAndDetailUrl(List<MessageSellHouseDo> messageSellHouseDos, Integer cityId) {
        if (CollectionUtils.isEmpty(messageSellHouseDos)) {
            return;
        }
        for (MessageSellHouseDo messageSellHouseDo : messageSellHouseDos) {
            String houseDetailUrl = null;
            if (StringTool.isNotEmpty(CITYID2ABBREVIATION.get(cityId))) {
                houseDetailUrl = String.format(URL + "/#/%s/details/secondHand?houseId=%s",
                        CITYID2ABBREVIATION.get(cityId), messageSellHouseDo.getHouseId());
            }
            messageSellHouseDo.setHouseDetailUrl(houseDetailUrl);

            String housePhotoTitle = messageSellHouseDo.getHousePhotoTitle();
            if (StringTool.isNotEmpty(housePhotoTitle)) {
                if (!(housePhotoTitle.contains("http://") || housePhotoTitle.contains("https://"))) {
                    housePhotoTitle = qinniuImg + "/" + housePhotoTitle + "-dongfangdi400x300";
                }
            } else {
                housePhotoTitle = "isNotExists";
            }
            messageSellHouseDo.setHousePhotoTitle(housePhotoTitle);
        }
    }

    /**
     * 处理专题列表Url
     *
     * @param messagePushDos
     */
    private void dealThemeDetailUrl(List<MessagePushDo> messagePushDos) {
        for (MessagePushDo messagePushDo : messagePushDos) {
            String city = CITYID2ABBREVIATION.get(messagePushDo.getCityId());
            if (StringTool.isNotEmpty(city)) {
                String themeDetailUrl = null;
                Integer subscribeType = messagePushDo.getSubscribeType();
                if (subscribeType == 1) {
                    themeDetailUrl = URL + String.format("/#/%s/topics/reduction", city);
                } else if (subscribeType == 2) {
                    themeDetailUrl = URL + String.format("/#/%s/topics/low", city);
                } else if (subscribeType == 3) {
                    themeDetailUrl = URL + String.format("/#/%s/topics/mustbuy", city);
                }
                JSONObject messageTheme = messagePushDo.getMessageTheme();
                String districtIdArr = messageTheme.get("districtId").toString().replace("\"", "").replace("[", "")
                        .replace("]", "");
                String params = String.format("?districtId=%s&beginPrice=%s&endPrice=%s", districtIdArr,
                        messageTheme.get("beginPrice").toString(), messageTheme.get("endPrice").toString());
                messagePushDo.setThemeDetailUrl(themeDetailUrl + params);
            }
        }
    }

    /**
     * 处理区域名称
     *
     * @param districtName
     * @param cityId
     */
    private String dealDistrictName(String districtName, Integer cityId) {
        if (StringTool.isEmpty(districtName) || "[\"\"]".equals(districtName) || "[]".equals(districtName)) {
            if (StringTool.isNotEmpty(CITYID2NAME.get(cityId))) {
                return ALL + CITYID2NAME.get(cityId);
            } else {
                return "";
            }
        }
        return districtName.replace("\"", "").replace("[", "")
                .replace("]", "");
    }

    private static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    private static boolean isEmpty(Object o) {
        return o == null || (Integer) o == 0;
    }
}
