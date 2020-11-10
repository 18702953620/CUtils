package com.h.cheng.http.request;

import com.h.cheng.http.request.base.BaseRequest;
import com.h.cheng.http.request.base.Method;

import java.util.HashMap;

/**
 * @author ch
 * @date 2020/9/4-16:15
 * @desc post 请求
 */
public class PostRequest extends BaseRequest {

    public PostRequest(String url) {
        this.url = url;
        this.method = Method.POST;
    }

    /**
     * body 参数
     */
    protected HashMap<String, String> bodyParams;


    /**
     * 添加 body 参数
     * POST 请求时，该参数会添加在请求体中
     *
     * @param key   key
     * @param value value
     */
    public PostRequest addParam(String key, Object value) {
        if (bodyParams == null) {
            bodyParams = new HashMap<>();
        }
        bodyParams.put(key, String.valueOf(value));
        return this;
    }

    /**
     * 添加 body 参数
     * POST 请求时，该参数会添加在请求体中
     *
     * @param map map
     */
    public PostRequest addParam(HashMap<String, String> map) {
        if (bodyParams == null) {
            bodyParams = new HashMap<>();
        }
        if (map != null && map.size() > 0) {
            bodyParams.putAll(map);
        }
        return this;
    }
}
