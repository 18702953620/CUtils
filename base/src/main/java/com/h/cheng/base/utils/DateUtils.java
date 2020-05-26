package com.h.cheng.base.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ch
 * 时间： 2018/11/26 0026-上午 11:11
 * 描述： 日期格式工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    private static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat YYYYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat MMSS = new SimpleDateFormat("mm:ss");


    public static String getFormatDateYm(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYm(t);
    }


    public static String getFormatDateYs(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYs(t);
    }

    public static String getFormatDateYd(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateYd(t);
    }

    public static String getFormatDateMs(String time) {
        long t = ParseUtils.parseLong(time) * 1000;
        return getFormatDateMs(t);
    }

    public static String getFormatDateYm(long time) {
        return YYYYMMDDHHMM.format(new Date(time));
    }

    public static String getFormatDateMs(long time) {
        return MMSS.format(new Date(time));
    }

    public static String getFormatDateYs(long time) {
        return YYYYMMDDHHMMSS.format(new Date(time));
    }

    public static String getFormatDateHs(long time) {
        HHMMSS.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return HHMMSS.format(time);
    }

    public static String getFormatDateYd(long time) {
        return YYYYMMDD.format(new Date(time));
    }
}
