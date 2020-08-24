package com.h.cheng.http.request;

import com.h.cheng.http.request.base.BaseRequest;
import com.h.cheng.http.request.base.Method;

/**
 * @author ch
 * @date 2020/6/10-14:41
 * @desc
 */
public class GetRequest extends BaseRequest {

    public GetRequest(String url) {
        this.url = url;
        this.method = Method.GET;
    }
}
