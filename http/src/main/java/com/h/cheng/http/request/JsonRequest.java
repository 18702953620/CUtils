package com.h.cheng.http.request;

import com.alibaba.fastjson.JSON;
import com.h.cheng.http.request.base.BaseRequest;
import com.h.cheng.http.request.base.Method;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author ch
 * @date 2020/8/10-10:44
 * @desc json
 */
public class JsonRequest extends BaseRequest {

    public JsonRequest(String url) {
        this.url = url;
        this.method = Method.POST;
    }

    @Override
    public RequestBody getRequestBody() {
        return RequestBody.create(JSON.toJSONString(bodyParams), MediaType.parse("application/json; charset=utf-8"));
    }
}
