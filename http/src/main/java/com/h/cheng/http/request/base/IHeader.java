package com.h.cheng.http.request.base;

import okhttp3.Headers;

/**
 * @author ch
 * @date 2020/6/10-9:28
 * @desc
 */
public interface IHeader {
    /**
     * 获取所有的head
     *
     * @return @Headers
     */
    Headers getHeaders();

    /**
     * 获取header
     *
     * @param key key
     * @return @String
     */
    String getHeader(String key);

    /**
     * 添加 header
     *
     * @param key   key
     * @param value value
     */
    void addHeader(String key, String value);
}
