package com.h.cheng.http;

import com.h.cheng.http.callback.BaseSubscriber;

/**
 * @author ch
 * @date 2020/6/10-9:48
 * @desc
 */
public interface PsSubscribe<T> {
    void subscribe(BaseSubscriber<T> subscriber);
}
