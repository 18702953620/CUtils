package com.h.cheng.base.api;


import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author ch
 * @date 2020/4/21-10:20
 * desc ApiServer
 */
public interface ApiServer {
    /**
     * 登录
     *
     * @param map map
     * @return flowable
     */
    @FormUrlEncoded
    @POST("api/login")
    Flowable<String> login(@FieldMap HashMap<String, Object> map);
}
