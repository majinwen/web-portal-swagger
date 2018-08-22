package com.toutiao.web.apiimpl.rest.message;

import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.web.dao.entity.message.MessagePush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 消息推送定时任务
 */
@Controller
public class MessagePushSchedulerTask {
    @Autowired
    private MessagePushService messagePushService;

    /**
     * 符合找房条件的房源上新，每天凌晨0点执行
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    private void newHousesTask() {
        MessagePushDoQuery messagePushDoQuery = new MessagePushDoQuery();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        messagePushDoQuery.setCreateTime(zero);
        List<MessagePush> message = messagePushService.getMessage(messagePushDoQuery);
        System.out.println("符合找房条件的房源上新，now time:" + zero + ",信息：" + message.toString());
    }

    /**
     * 关注小区房源上新，每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    private void favoritePlotsTask() {
        System.out.println("关注小区房源上新，now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 关注房源价格变动，每天凌晨2点执行
     */
    @Scheduled(cron = "0 0 2 * * ? ")
    private void favoriteHousesTask() {
        System.out.println("关注房源价格变动，now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 订阅的降价房有更新，每周五凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 0 0 5 * ")
    private void subscribeCutPriceTask() {
        System.out.println("订阅的降价房有更新，now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 订阅的捡漏房有更新，每周六凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 0 0 6 * ")
    private void subscribeLowerPriceTask() {
        System.out.println("订阅的捡漏房有更新，now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 订阅的抢手房有更新，每周日凌晨4点执行
     */
    @Scheduled(cron = "0 0 4 0 0 7 * ")
    private void subscribeMustRobTask() {
        System.out.println("订阅的抢手房有更新，now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
