package com.h.cheng.http.request;

import com.h.cheng.http.request.base.BaseRequest;
import com.h.cheng.http.request.base.Method;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author ch
 * @date 2020/6/9-18:17
 * @desc post 请求
 */
public class FormRequest extends BaseRequest {

    public FormRequest(String url, Method method) {
        this.url = url;
        this.method = method;
        urlParams = new HashMap<>();
    }

    @Override
    public RequestBody getRequestBody() {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : urlParams.keySet()) {
            bodyBuilder.addEncoded(key, urlParams.get(key) == null ? "" : urlParams.get(key));
        }
        return bodyBuilder.build();
    }
}
