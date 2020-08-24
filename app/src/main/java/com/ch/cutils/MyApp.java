package com.ch.cutils;

import androidx.multidex.MultiDexApplication;

import com.h.cheng.web.WebConfig;

/**
 * @author ch
 * @date 2020/5/26-16:23
 * @desc
 */
public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        WebConfig.init();
    }
}
