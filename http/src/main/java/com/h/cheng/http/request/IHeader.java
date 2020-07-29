package com.h.cheng.http.request;

import okhttp3.Headers;

/**
 * @author ch
 * @date 2020/6/10-9:28
 * @desc
 */
public interface IHeader {
    Headers getHeaders();

    String getHeader(String key);

    void addHeader(String key, String value);
}
