package com.toutiao.web.apiimpl.impl.projhouse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        int i = Math.subtractExact(1, 2);
        System.out.println(i);


        String yyyy = String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())), Integer.valueOf(5)));

        System.out.println(yyyy);

    }
}
