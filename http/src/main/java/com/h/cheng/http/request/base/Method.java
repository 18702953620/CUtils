package com.h.cheng.http.request.base;

/**
 * 请求方法
 *
 * @author ch
 * @date 2020/6/9-17:13
 */
public enum Method {
    /**
     * get
     */
    GET,
    /**
     * post
     */
    POST;

    public boolean isGet() {
        return "GET".equals(name());
    }

    public boolean isPost() {
        return "POST".equals(name());
    }
}
