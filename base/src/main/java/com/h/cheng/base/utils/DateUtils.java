package com.h.cheng.base.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 作者： ch
 * 时间： 2018/11/26 0026-上午 11:11
 * 描述： 日期格式工具类
 * 来源：
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    private static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat YYYYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat MMSS = new SimpleDateFormat("mm:ss");


    public static String getFormatDateYM(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYM(t);
    }


    public static String getFormatDateYS(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYS(t);
    }

    public static String getFormatDateYD(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYD(t);
    }

    public static String getFormatDateMS(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateMS(t);
    }

    public static String getFormatDateYM(long time) {
        return YYYYMMDDHHMM.format(new Date(time));
    }

    public static String getFormatDateMS(long time) {
        return MMSS.format(new Date(time));
    }

    public static String getFormatDateYS(long time) {
        return YYYYMMDDHHMMSS.format(new Date(time));
    }

    public static String getFormatDateHS(long time) {
        HHMMSS.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return HHMMSS.format(time);
    }

    public static String getFormatDateYD(long time) {
        return YYYYMMDD.format(new Date(time));
    }
}
