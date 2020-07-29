package com.h.cheng.http.request;

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
