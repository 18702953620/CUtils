package com.h.cheng.http.request.base;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author ch
 * @date 2020/6/9-17:59
 * @desc
 */
public interface IRequest {

    /**
     * 请求地址
     *
     * @return url
     */
    String url();

    /**
     * 请求方法
     *
     * @return GET、POST等
     */
    Method getMethod();

    /**
     * 请求体
     *
     * @return RequestBody
     */
    RequestBody getRequestBody();

    /**
     * 根据以上定义的方法构建一个请求
     *
     * @return Request
     */
    Request buildRequest();
}
